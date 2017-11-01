package com.xjh.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.Constants;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsSessionDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.mapper.WmsSessionMapper;
import com.xjh.dao.mapper.WmsUserMapper;
import com.xjh.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	WmsUserMapper wmsUserMapper;
	@Resource
	WmsSessionMapper wmsSessionMapper;
	
	@Override
	public ResultBase<WmsUserDO> queryUser(String userId) {
		return null;
	}

	@Override
	public ResultBase<Boolean> isValidSession(String sessionId) {
		boolean b = wmsSessionMapper.isValidSession(sessionId);
		return ResultBaseBuilder.succ().data(b).rb();
	}
	
	@Override
	public ResultBase<JSONObject> login(WmsUserDO userDO) {
		if(userDO == null){
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		if(StringUtils.isAnyBlank(userDO.getUserCode(),userDO.getPassword())){
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsUserDO user = wmsUserMapper.selectByUserCode(userDO.getUserCode());
		if(user == null){
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		if(StringUtils.equals(user.getPassword(), userDO.getPassword())){
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsSessionDO sessionDO = new WmsSessionDO();
		sessionDO.setSessionId(CommonUtils.uuid());
		sessionDO.setExpiredTime(CommonUtils.future(Constants.default_session_expired_seconds));
		wmsSessionMapper.createSession(sessionDO);
		JSONObject data = new JSONObject();
		data.put("JSESSIONID", sessionDO.getSessionId());
		return ResultBaseBuilder.succ().msg("登录成功").data(data).rb();
	}

}
