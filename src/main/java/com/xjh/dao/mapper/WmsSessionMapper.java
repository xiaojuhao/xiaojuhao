package com.xjh.dao.mapper;

import com.xjh.dao.dataobject.WmsSessionDO;

public interface WmsSessionMapper {
	public boolean isValidSession(String sessionId);
	public void createSession(WmsSessionDO sessionDO);
}
