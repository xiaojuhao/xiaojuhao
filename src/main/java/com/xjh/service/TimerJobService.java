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
			WmsTimerJobDO cond = new WmsTimerJobDO();
			cond.setStatus("0"); ///待处理
			PageHelper.startPage(1, 1);
			PageHelper.orderBy("scheduled_time desc");
			List<WmsTimerJobDO> list = jobMapper.select(cond);
			if (list == null || list.size() == 0) {
				return null;
			}
			WmsTimerJobDO job = list.get(0);
			Example example = new Example(WmsTimerJobDO.class, false, false);
			Example.Criteria cri = example.createCriteria();
			cri.andEqualTo("id", job.getId());
			cri.andEqualTo("version", job.getVersion());
			cri.andLessThanOrEqualTo("scheduledTime", new Date());
			cri.andLessThan("version", 30);
			WmsTimerJobDO update = new WmsTimerJobDO();
			update.setStatus("1"); ///处理中
			update.setVersion(job.getVersion() + 1);
			update.setStartTime(new Date());
			int effected = jobMapper.updateByExampleSelective(update, example);
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
					if (h.accept(job)) {
						h.handle(job);
						job.setExecuteResult("执行成功【S1】");
						h.postHandle(job);
						job.setExecuteResult("执行成功【S2】");
						break;
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
	}
}
