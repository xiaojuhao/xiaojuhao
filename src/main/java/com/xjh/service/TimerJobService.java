package com.xjh.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class TimerJobService {
	@Resource
	TkWmsTimerJobMapper jobMapper;

	public WmsTimerJobDO nextJob() {
		for (int i = 0; i < 100; i++) {
			WmsTimerJobDO cond = new WmsTimerJobDO();
			cond.setStatus("0"); ///待处理
			PageHelper.startPage(1, 1);
			PageHelper.orderBy("scheduledTime desc");
			List<WmsTimerJobDO> list = jobMapper.select(cond);
			if (list == null || list.size() == 0) {
				return null;
			}
			WmsTimerJobDO job = list.get(0);
			Example example = new Example(WmsTimerJobDO.class, false, false);
			Example.Criteria cri = example.createCriteria();
			cri.andEqualTo("id", job.getId());
			cri.andEqualTo("version", job.getVersion());
			WmsTimerJobDO update = new WmsTimerJobDO();
			update.setStatus("1"); ///处理中
			update.setStartTime(new Date());
			int effected = jobMapper.updateByExampleSelective(update, example);
			if (effected == 1) {
				return job;
			}
		}
		return null;
	}
}
