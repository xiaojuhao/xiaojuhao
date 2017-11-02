package com.xjh.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.Constants;
import com.xjh.commons.CookieUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsSessionDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;
import com.xjh.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	TkWmsSessionMapper sessionMapper;
	@Resource
	TkWmsUserMapper userMapper;
	
	@Override
	public ResultBase<WmsUserDO> queryUser(String userId) {
		return null;
	}

	@Override
	public ResultBase<Boolean> isValidSession(String sessionId) {
		if(StringUtils.isBlank(sessionId)){
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		WmsSessionDO t = new WmsSessionDO();
		t.setSessionId(sessionId);
		WmsSessionDO session = sessionMapper.selectOne(t);
		if(session == null){
			return ResultBaseBuilder.fails(ResultCode.no_login).rb();
		}
		boolean b = CommonUtils.partiallyOrder(new Date(), session.getExpiredTime());
		if(b){
			return ResultBaseBuilder.succ().rb();
		}else{
			return ResultBaseBuilder.fails("回话过期").rb();
		}
	}
	
	@Override
	public ResultBase<WmsUserDO> login(WmsUserDO userDO,HttpServletRequest request,HttpServletResponse response) {
		if(userDO == null){
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		if(StringUtils.isAnyBlank(userDO.getUserCode(),userDO.getPassword())){
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsUserDO t = new WmsUserDO();
		t.setUserCode(userDO.getUserCode());
		WmsUserDO user = userMapper.selectOne(t);
		if(user == null){
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		if(!StringUtils.equals(user.getPassword(), userDO.getPassword())){
			CookieUtils.deleteCookie(request, Constants.WMS_LOGIN_KEY);
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsSessionDO sessionDO = new WmsSessionDO();
		sessionDO.setSessionId("WmsLogin"+CommonUtils.uuid().toUpperCase());
		sessionDO.setExpiredTime(CommonUtils.future(Constants.default_session_expired_seconds));
		sessionDO.setUserInfo(CommonUtils.toJsonString(userDO));
		sessionMapper.insert(sessionDO);
		CookieUtils.addCookie(request, response, Constants.WMS_LOGIN_KEY, sessionDO.getSessionId(), null);
		return ResultBaseBuilder.succ().msg("登录成功").data(user).rb();
	}

}
