package com.xjh.service;

import com.xjh.dao.dataobject.WmsTimerJobDO;

public interface TimerJobHandler {
	public boolean accept(WmsTimerJobDO job);

	public void handle(WmsTimerJobDO job);

	public void postHandle(WmsTimerJobDO job);
}
