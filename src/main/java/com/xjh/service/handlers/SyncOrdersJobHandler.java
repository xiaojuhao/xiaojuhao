package com.xjh.service.handlers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.DiandanSystemService;
import com.xjh.service.OrderMaterialService;
import com.xjh.service.TimerJobHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SyncOrdersJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "sync_order_daily";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	DiandanSystemService diandanService;
	@Resource
	OrderMaterialService orderMaterialService;

	@Override
	public void onSystemStart() {
		this.setNextJobIfNessesary(null);
	}

	@Override
	public boolean accept(WmsTimerJobDO job) {
		return JOB_TYPE.equals(job.getJobType());
	}

	@Override
	public void handle(WmsTimerJobDO job) {
		log.info("开始同步订单。。。。。");
		diandanService.syncOrders(CommonUtils.todayDate(), false);
		orderMaterialService.handleOrders();//处理订单原料数据
	}

	@Override
	public void postHandle(WmsTimerJobDO job) {
		this.setNextJobIfNessesary(job.getScheduledTime());
	}

	private void setNextJobIfNessesary(Date prevDate) {
		WmsTimerJobDO cond = new WmsTimerJobDO();
		cond.setJobType(JOB_TYPE);
		cond.setStatus("0");
		List<WmsTimerJobDO> list = timerJobMapper.select(cond);
		if (list == null || list.size() == 0) {
			//增加一条任务(00:10执行）
			Date scheduledTime = DateBuilder//
					.base(prevDate)//默认当天
					.zeroAM() // 凌晨
					.futureDays(1)//推迟一天
					.minute(0)//
					.date();
			WmsTimerJobDO record = new WmsTimerJobDO();
			record.setJobType(JOB_TYPE);
			record.setJobName("同步订单");
			record.setScheduledTime(scheduledTime);
			record.setStatus("0");
			record.setGmtCreated(new Date());
			record.setVersion(0);
			timerJobMapper.insert(record);
		}
	}
}
