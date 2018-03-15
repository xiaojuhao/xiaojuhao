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
		try {
			log.info("开始同步菜单。。。。。");
			diandanService.syncRecipes();//先同步菜单
			log.info("菜单同步成功。。。。。");
		} catch (Exception e) {
			log.error("", e);
		}
		log.info("开始同步订单。。。。。");
		diandanService.syncOrders(DateBuilder.today().futureDays(-1).date(), true);
		log.info("订单同步成功。。。。。");
		log.info("开始处理原料。。。。。");
		orderMaterialService.handleOrders();//处理订单原料数据
		log.info("原料处理完成。。。。。");
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
			//增加一条任务(执行）
			Date scheduledTime = DateBuilder//
					.now().zeroAM() // 凌晨
					.futureDays(1) //
					.hour(8)//
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
