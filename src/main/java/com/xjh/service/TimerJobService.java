package com.xjh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class TimerJobService implements InitializingBean, ApplicationContextAware {
	@Resource
	TkWmsTimerJobMapper jobMapper;
	List<TimerJobHandler> handlers = new ArrayList<>();

	public WmsTimerJobDO nextJob() {
		for (int i = 0; i < 100; i++) {
			Example example = new Example(WmsTimerJobDO.class, false, false);
			Example.Criteria cri = example.createCriteria();
			cri.andLessThanOrEqualTo("version", 30);
			cri.andLessThanOrEqualTo("scheduledTime", new Date());
			cri.andEqualTo("status", "0");//未处理
			PageHelper.startPage(1, 1);
			PageHelper.orderBy("scheduled_time asc");
			List<WmsTimerJobDO> list = jobMapper.selectByExample(example);
			if (list == null || list.size() == 0) {
				return null;
			}
			WmsTimerJobDO job = list.get(0);
			Example example2 = new Example(WmsTimerJobDO.class, false, false);
			Example.Criteria cri2 = example2.createCriteria();
			cri2.andLessThanOrEqualTo("version", 30);
			cri2.andEqualTo("status", "0");//未处理
			cri2.andEqualTo("version", job.getVersion());
			cri2.andEqualTo("id", job.getId());
			WmsTimerJobDO update = new WmsTimerJobDO();
			update.setStatus("1"); ///处理中
			update.setVersion(job.getVersion() + 1);
			update.setStartTime(new Date());
			int effected = jobMapper.updateByExampleSelective(update, example2);
			if (effected == 1) {
				return job;
			}
		}
		return null;
	}

	public void finishJob(WmsTimerJobDO job) {
		if (job == null || job.getId() == null) {
			return;
		}
		Example example = new Example(WmsTimerJobDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("id", job.getId());
		WmsTimerJobDO update = new WmsTimerJobDO();
		update.setStatus("2"); //已处理
		update.setExecuteResult(job.getExecuteResult());
		update.setRemark(job.getRemark());
		update.setEndTime(new Date());
		jobMapper.updateByExampleSelective(update, example);
	}

	public void failJob(WmsTimerJobDO job) {
		if (job == null || job.getId() == null) {
			return;
		}
		Example example = new Example(WmsTimerJobDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("id", job.getId());
		WmsTimerJobDO update = new WmsTimerJobDO();
		update.setStatus("3"); //处理失败
		update.setExecuteResult(job.getExecuteResult());
		update.setRemark(job.getRemark());
		update.setEndTime(new Date());
		jobMapper.updateByExampleSelective(update, example);
	}

	public void jobRound() {
		while (true) {
			WmsTimerJobDO job = null;
			try {
				job = this.nextJob();
				if (job == null) {
					return;//退出循环
				}
				job.setExecuteResult("没有找到处理器");//初始化消息，处理器中会替换
				for (TimerJobHandler h : this.handlers) {
					try {
						if (h.accept(job)) {
							h.handle(job);
							job.setExecuteResult("S1执行成功");
							h.postHandle(job);
							job.setExecuteResult("S1&S2执行成功");
							break;
						}
					} catch (Exception ex) {
						h.postHandle(job);
						job.setExecuteResult(job.getExecuteResult() + ":S2执行失败");
						throw ex;
					}
				}
				finishJob(job);
			} catch (Exception ex) {
				log.error("", ex);
				job.setRemark(ex.getMessage());
				failJob(job);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Executors.newSingleThreadScheduledExecutor()//
				.scheduleAtFixedRate(() -> {
					try {
						jobRound();
					} catch (Exception ex) {
						log.error("", ex);
					}
				}, 0, 10, TimeUnit.SECONDS);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, TimerJobHandler> handlerMap = applicationContext.getBeansOfType(TimerJobHandler.class);
		if (handlerMap != null) {
			this.handlers.addAll(handlerMap.values());
		}
		//系统启动时执行
		this.handlers.forEach((h) -> h.onSystemStart());
	}
}
