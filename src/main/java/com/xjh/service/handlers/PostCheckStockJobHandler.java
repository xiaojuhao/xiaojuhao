package com.xjh.service.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsMaterialDepleteReportDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsMaterialDepleteReportMapper;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.TkMappers;

import tk.mybatis.mapper.entity.Example;

/**
 * 任务盘点统计
 * @author yinguoliang
 *
 */
@Component
public class PostCheckStockJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "post_check_stock";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	TkWmsMaterialDepleteReportMapper reportMapper;

	public static void addNewJob(String date, String cabinCode) {
		WmsTimerJobDO job = new WmsTimerJobDO();
		job.setJobType(JOB_TYPE);
		job.setJobName("库存盘点报告" + date);
		job.setScheduledTime(DateBuilder.now().date());
		JSONObject param = new JSONObject();
		param.put("reportName", "库存盘点报告" + date);
		param.put("cabinCode", cabinCode);
		job.setJobParam(param.toJSONString());
		job.setStatus("0");
		job.setVersion(0);
		TkMappers.inst().getTimerJobMapper().insert(job);
	}

	@Override
	public void onSystemStart() {

	}

	@Override
	public boolean accept(WmsTimerJobDO job) {
		return JOB_TYPE.equals(job.getJobType());
	}

	@Override
	public void handle(WmsTimerJobDO job) {
		JSONObject param = CommonUtils.parseJSON(job.getJobParam());
		String cabinCode = param.getString("cabinCode");
		String materialCode = param.getString("materialCode");
		String reportName = param.getString("reportName");
		List<WmsMaterialDepleteReportDO> reports = new ArrayList<>();

		WmsMaterialStockDO cond = new WmsMaterialStockDO();
		cond.setCabinCode(cabinCode);
		cond.setMaterialCode(materialCode);
		List<WmsMaterialStockDO> list = TkMappers.inst().getMaterialStockMapper().select(cond);
		list.forEach((stock) -> {
			WmsMaterialDepleteReportDO report = this.getReport(stock);
			if (report != null)
				reports.add(report);
		});
		//输出报告
		String reportNo = CommonUtils.uuid();
		Date reportTime = new Date();
		reports.forEach((it) -> {
			it.setReportNo(reportNo);
			it.setReportTitle(reportName);
			it.setReportTime(reportTime);
			reportMapper.insert(it);
		});
	}

	@Override
	public void postHandle(WmsTimerJobDO job) {

	}

	public WmsMaterialDepleteReportDO getReport(WmsMaterialStockDO stock) {
		final WmsMaterialDepleteReportDO report = new WmsMaterialDepleteReportDO();
		report.setCabinCode(stock.getCabinCode());
		report.setCabinName(stock.getCabinName());
		report.setMaterialCode(stock.getMaterialCode());
		report.setMaterialName(stock.getMaterialName());
		report.setStockUnit(stock.getStockUnit());
		report.setGmtCreated(new Date());
		report.setSaleAmt(0D);
		report.setSaleAmtRatio(0D);
		report.setClaimLoss(0D);
		report.setClaimLossRatio(0D);
		report.setDepleteAmt(0D);
		report.setCorrectLoss(0D);
		report.setCorrectLossRatio(0D);
		report.setOtherLoss(0D);
		report.setOtherLossRatio(0D);
		//找到最近连续两次盘点的记录
		WmsMaterialStockHistoryDO cond = new WmsMaterialStockHistoryDO();
		cond.setCabinCode(stock.getCabinCode());
		cond.setMaterialCode(stock.getMaterialCode());
		cond.setOpType("correct");
		PageHelper.startPage(1, 2);
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsMaterialStockHistoryDO> correctRange = //
				TkMappers.inst().getMaterialStockHistoryMapper().select(cond);
		if (correctRange.size() == 0) {
			return null;
		}
		long startId = 0L;
		long endId = 0L;
		if (correctRange.size() == 1) {
			endId = correctRange.get(0).getId();
		}
		if (correctRange.size() == 2) {
			endId = correctRange.get(0).getId();
			startId = correctRange.get(1).getId();
		}
		//查出两次盘点之间的库存消耗的记录（amt<0)
		Example example = new Example(WmsMaterialStockHistoryDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", stock.getCabinCode());
		cri.andEqualTo("materialCode", stock.getMaterialCode());
		cri.andGreaterThan("id", startId);
		cri.andLessThan("id", endId);
		cri.andLessThan("amt", 0.0);
		List<WmsMaterialStockHistoryDO> list = //
				TkMappers.inst().getMaterialStockHistoryMapper().selectByExample(example);
		if (list.size() == 0) {
			return null;
		}
		for (WmsMaterialStockHistoryDO it : list) {
			double amt = Math.abs(it.getAmt());
			report.setDepleteAmt(report.getDepleteAmt() + amt);
			switch (it.getOpType()) {
			case "claim_loss":
				report.setClaimLoss(report.getClaimLoss() + amt);
				break;
			case "sale":
				report.setSaleAmt(report.getSaleAmt() + amt);
				break;
			case "correct_delta":
				report.setCorrectLoss(report.getCorrectLoss() + amt);
				break;
			default:
				report.setOtherLoss(report.getOtherLoss() + amt);
				break;
			}
		}
		if (report.getDepleteAmt() > 0) {
			report.setClaimLossRatio(report.getClaimLoss() / report.getDepleteAmt());
			report.setSaleAmtRatio(report.getSaleAmt() / report.getDepleteAmt());
			report.setCorrectLossRatio(report.getCorrectLoss() / report.getDepleteAmt());
			report.setOtherLossRatio(report.getOtherLoss() / report.getDepleteAmt());
		}
		return report;
	}

}
