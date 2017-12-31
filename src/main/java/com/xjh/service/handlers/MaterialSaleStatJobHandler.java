package com.xjh.service.handlers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.mapper.WmsOrdersMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.StockDailyService;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.vo.MaterialSaleStatVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 统计原料销售
 * @author yinguoliang
 *
 */
@Component
@Slf4j
public class MaterialSaleStatJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "material_sale_stat";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	WmsOrdersMaterialMapper wmsOrdersMaterialMapper;
	@Resource
	StockDailyService stockDailyService;

	@Override
	public void onSystemStart() {

	}

	@Override
	public boolean accept(WmsTimerJobDO job) {
		return JOB_TYPE.equals(job.getJobType());
	}

	@Override
	public void handle(WmsTimerJobDO job) {
		log.info("任务{}开始执行:{}", job.getId(), job.getJobName());
		JSONObject param = CommonUtils.parseJSON(job.getJobParam());
		String storeCode = param.getString("storeCode");
		Date startDate = CommonUtils.parseDate(param.getString("startDate"));
		Date endDate = CommonUtils.parseDate(param.getString("endDate"));

		if (startDate == null) {
			startDate = DateBuilder.today().date();
		}
		if (endDate == null) {
			endDate = startDate;
		}
		MaterialSaleStatVo input = new MaterialSaleStatVo();
		input.setStartDate(startDate);
		input.setEndDate(endDate);
		if (StringUtils.isNotBlank(storeCode)) {
			input.setStoreList(Arrays.asList(storeCode));
		}
		log.info("任务{}:统计销售数据", job.getId());
		List<MaterialSaleStatVo> statList = wmsOrdersMaterialMapper.stat(input);
		log.info("任务{}:统计销售数据完成，获得{}条结果", job.getId(), statList.size());
		log.info("任务{}:统计销售数据完成，开始更新统计表", job.getId());
		for (MaterialSaleStatVo vo : statList) {
			stockDailyService.setConsumeAmt2( //
					vo.getMaterialCode(), //
					vo.getStoreCode(), //
					CommonUtils.formatDate(vo.getSaleDate(), "yyyyMMdd"), //
					vo.getSaleAmt());
		}
		log.info("任务{}:统计销售数据完成，统计表更新完成", job.getId());
		log.info("任务{}执行结束:{}", job.getId(), job.getJobName());
	}

	@Override
	public void postHandle(WmsTimerJobDO job) {

	}
}
