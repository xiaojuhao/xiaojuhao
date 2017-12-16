package com.xjh.service.handlers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockDailyDO;
import com.xjh.dao.dataobject.WmsNoticeDO;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsMaterialStockDailyMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.TkMappers;

import tk.mybatis.mapper.entity.Example;

/**
 * 计算库存告警值
 * @author yinguoliang
 *
 */
@Component
public class SetWarningStockJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "set_warning_stock";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	TkWmsMaterialStockMapper materialStockMapper;
	@Resource
	TkWmsMaterialStockDailyMapper stockDailyMapper;

	@Override
	public void onSystemStart() {
		//保证定时任务表里面至少有个计算任务
		setNextJobIfNessesary();
	}

	@Override
	public boolean accept(WmsTimerJobDO job) {
		return job != null && JOB_TYPE.equals(job.getJobType());
	}

	@Override
	public void handle(WmsTimerJobDO job) {
		Date today = CommonUtils.todayDate();
		Long preId = null;
		while (true) {
			WmsMaterialStockDO stock = this.nextToHandle(preId);
			if (stock == null) {
				break;
			}
			doBusiness(stock, today);
			preId = stock.getId(); //下一个
		}
	}

	@Override
	public void postHandle(WmsTimerJobDO job) {
		setNextJobIfNessesary();//设置下一次任务
	}

	private WmsMaterialStockDO nextToHandle(Long preId) {
		if (preId == null)
			preId = 0L;
		Example example = new Example(WmsMaterialStockDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andGreaterThan("id", preId);
		PageHelper.orderBy("id asc");
		PageHelper.startPage(1, 1);
		List<WmsMaterialStockDO> list = this.materialStockMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 计算库存的告警值
	 * @param stock
	 */
	private void doBusiness(WmsMaterialStockDO stock, Date date) {
		boolean isBusy = this.isBusiDay(date);
		//以3天销售额作为预警值
		Example example = new Example(WmsMaterialStockDailyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andLessThanOrEqualTo("saleDate", date);
		cri.andEqualTo("cabinCode", stock.getCabinCode());
		cri.andEqualTo("materialCode", stock.getMaterialCode());
		//按销售时间倒叙排，取3条记录
		PageHelper.orderBy("stat_date desc");
		PageHelper.startPage(1, 3);
		List<WmsMaterialStockDailyDO> list = stockDailyMapper.selectByExample(example);
		double warningStock = 0D;
		for (WmsMaterialStockDailyDO dd : list) {
			warningStock += dd.getConsumeAmt();
		}
		WmsMaterialStockDO update = new WmsMaterialStockDO();
		update.setId(stock.getId());
		update.setWarningStock(warningStock);
		update.setGmtSetWarningStock(new Date());
		materialStockMapper.updateByPrimaryKeySelective(update);
		//设置告警信息
		if (stock.getCurrStock() < warningStock) {
			WmsNoticeDO notice = new WmsNoticeDO();
			notice.setStatus("1");
			notice.setTitle("库存预警");
			notice.setContent(stock.getCabinName() + "【" + stock.getMaterialName() + "】库存不足,请及时补货");
			notice.setGmtCreated(new Date());
			notice.setGmtExpired(CommonUtils.futureDays(new Date(), 7));
			notice.setMsgType("warning");
			TkMappers.inst().getNoticeMapper().insert(notice);
		}
	}

	/**
	 * 初始化下一次的任务
	 */
	private void setNextJobIfNessesary() {
		WmsTimerJobDO cond = new WmsTimerJobDO();
		cond.setJobType(JOB_TYPE);
		cond.setStatus("0");
		List<WmsTimerJobDO> list = timerJobMapper.select(cond);
		if (list == null || list.size() == 0) {
			//增加一条任务(00:10执行）
			Date scheduledTime = DateBuilder//
					.newInstance()//默认当天
					.zeroAM() // 凌晨
					.futureDays(1)//推迟一天
					.minute(10)//
					.date();
			WmsTimerJobDO record = new WmsTimerJobDO();
			record.setJobType(JOB_TYPE);
			record.setJobName("智能预警计算");
			record.setScheduledTime(scheduledTime);
			record.setStatus("0");
			record.setGmtCreated(new Date());
			record.setVersion(0);
			timerJobMapper.insert(record);
		}
	}

	/**
	 * 是否忙时
	 * @param date
	 * @return
	 */
	private boolean isBusiDay(Date date) {
		if (date == null) {
			return false;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK);
		if (i == Calendar.SUNDAY || i == Calendar.SATURDAY) {
			return true;
		}
		return false;
	}

}
