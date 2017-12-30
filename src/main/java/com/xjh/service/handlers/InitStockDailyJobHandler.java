package com.xjh.service.handlers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.CabinService;
import com.xjh.service.StockDailyService;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.TkMappers;

@Component
public class InitStockDailyJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "init-daily-stock";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	StockDailyService stockDailyService;
	@Resource
	CabinService cabinService;

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
		cabinService.getAllCabins().forEach((cabin) -> {
			//每个仓库执行一次
			try {
				WmsMaterialStockDO cond = new WmsMaterialStockDO();
				cond.setIsDeleted("N");
				cond.setCabinCode(cabin.getCode());
				//查询仓库里面所有的库存，看看是否需要初始化
				List<WmsMaterialStockDO> stocks = TkMappers.inst().getMaterialStockMapper().select(cond);
				stocks.forEach((d) -> {
					//初始化每日库存
					stockDailyService.getOrInitStockDaily( //
							d.getMaterialCode(), //
							d.getCabinCode(), //
							CommonUtils.stringOfToday("yyyyMMdd"));
					//保存当前最新库存
					stockDailyService.setCurrentStockAmt( //
							d.getMaterialCode(), //
							d.getCabinCode(), //
							CommonUtils.stringOfToday("yyyyMMdd"));
				});
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

	}

	@Override
	public void postHandle(WmsTimerJobDO job) {
		setNextJobIfNessesary(job);
	}

	private void setNextJobIfNessesary(WmsTimerJobDO prev) {
		WmsTimerJobDO cond = new WmsTimerJobDO();
		cond.setJobType(JOB_TYPE);
		cond.setStatus("0");
		List<WmsTimerJobDO> list = timerJobMapper.select(cond);
		if (list == null || list.size() == 0) {
			Date scheduledTime = DateBuilder//
					.now()//
					.futureMinutes(10)//
					.date();
			WmsTimerJobDO record = new WmsTimerJobDO();
			record.setJobType(JOB_TYPE);
			record.setJobName("初始化每日库存记录");
			record.setScheduledTime(scheduledTime);
			record.setStatus("0");
			record.setGmtCreated(new Date());
			record.setVersion(0);
			timerJobMapper.insert(record);
		}
	}
}
