package com.xjh.service.handlers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsSessionDO;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.service.TimerJobHandler;
import com.xjh.service.TkMappers;

import tk.mybatis.mapper.entity.Example;

@Service
public class ClearExpiredLoginJobHandler implements TimerJobHandler {
	static String JOB_TYPE = "clear_expired_login";
	@Resource
	TkWmsTimerJobMapper timerJobMapper;

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
		Example example = new Example(WmsSessionDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andLessThan("expiredTime", new Date());
		TkMappers.inst().getSessionMapper().deleteByExample(example);
	}

	@Override
	public void postHandle(WmsTimerJobDO job) {
		this.setNextJobIfNessesary(job);
	}

	private void setNextJobIfNessesary(WmsTimerJobDO prev) {
		WmsTimerJobDO cond = new WmsTimerJobDO();
		cond.setJobType(JOB_TYPE);
		cond.setStatus("0");
		List<WmsTimerJobDO> list = timerJobMapper.select(cond);
		if (list == null || list.size() == 0) {
			//增加一条任务(00:10执行）
			Date scheduledTime = DateBuilder//
					.newInstance()//默认当天
					.base(prev != null ? prev.getScheduledTime() : null)//
					.futureMinutes(1)//
					.date();
			WmsTimerJobDO record = new WmsTimerJobDO();
			record.setJobType(JOB_TYPE);
			record.setJobName("清除过期登录信息");
			record.setScheduledTime(scheduledTime);
			record.setStatus("0");
			record.setGmtCreated(new Date());
			record.setVersion(0);
			timerJobMapper.insert(record);
		}
	}
}
