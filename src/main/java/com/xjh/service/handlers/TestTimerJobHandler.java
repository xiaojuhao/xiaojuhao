package com.xjh.service.handlers;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestTimerJobHandler implements TimerJobHandler {

	@Override
	public boolean accept(WmsTimerJobDO job) {
		if ("test".equals(job.getJobType())) {
			return true;
		}
		return false;
	}

	@Override
	public void handle(WmsTimerJobDO job) {

	}

	@Override
	public void postHandle(WmsTimerJobDO job) {
		WmsTimerJobDO newJob = new WmsTimerJobDO();
		log.info("执行任务后置逻辑:{}", job.getId());
		newJob.setId(null);
		newJob.setScheduledTime(CommonUtils.future(new Date(), 20));
		newJob.setJobName(job.getJobName());
		newJob.setJobType(job.getJobType());
		newJob.setGmtCreated(new Date());
		newJob.setStatus("0");
		newJob.setVersion(0);
		TkMappers.inst().getTimerJobMapper().insert(newJob);
	}
}
