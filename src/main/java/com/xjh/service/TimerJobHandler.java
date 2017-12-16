package com.xjh.service;

import com.xjh.dao.dataobject.WmsTimerJobDO;

/**
 * 定时任务处理器
 * @author yinguoliang
 *
 */
public interface TimerJobHandler {
	/**
	 * 在系统启动的时候执行
	 */
	public void onSystemStart();

	/**
	 * 是否可处理指定的任务
	 * @param job
	 * @return
	 */
	public boolean accept(WmsTimerJobDO job);

	/**
	 * 处理任务
	 * @param job
	 */
	public void handle(WmsTimerJobDO job);

	/**
	 * 任务处理完成之后调用(无论handle成功还是失败)
	 * @param job
	 */
	public void postHandle(WmsTimerJobDO job);
}
