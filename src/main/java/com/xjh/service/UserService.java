package com.xjh.service;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsUserDO;

public interface UserService {
	public ResultBase<JSONObject> queryUser(Long userId);
	
	public ResultBase<Boolean> isValidSession(String sessionId);
	
	public ResultBase<JSONObject> login(WmsUserDO userDO);
}
