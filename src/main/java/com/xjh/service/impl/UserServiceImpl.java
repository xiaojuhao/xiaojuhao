package com.xjh.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.Constants;
import com.xjh.commons.CookieUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsSessionDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;
import com.xjh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	TkWmsSessionMapper sessionMapper;
	@Resource
	TkWmsUserMapper userMapper;

	@Override
	public ResultBase<WmsUserDO> queryUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userId);
		user = userMapper.selectOne(user);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb();
		}
		return ResultBaseBuilder.succ().data(user).rb();
	}

	@Override
	public PageResult<WmsUserDO> queryUsers(WmsUserDO userDO) {
		if (userDO == null) {
			userDO = new WmsUserDO();
		}
		PageResult<WmsUserDO> page = new PageResult<>();
		List<WmsUserDO> list = this.userMapper.select(userDO);
		page.setValues(list);
		return page;
	}

	@Override
	public ResultBase<WmsUserDO> login(WmsUserDO userDO, HttpServletRequest request, HttpServletResponse response) {
		if (userDO == null) {
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		if (StringUtils.isAnyBlank(userDO.getUserCode(), userDO.getPassword())) {
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsUserDO t = new WmsUserDO();
		t.setUserCode(userDO.getUserCode());
		WmsUserDO user = userMapper.selectOne(t);
		if (user == null) {
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		if (!StringUtils.equals(user.getPassword(), userDO.getPassword())) {
			CookieUtils.deleteCookie(request, Constants.WMS_LOGIN_KEY);
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		if (!StringUtils.equals(user.getStatus(), "1")) {
			CookieUtils.deleteCookie(request, Constants.WMS_LOGIN_KEY);
			return ResultBaseBuilder.fails("登录失败").rb();
		}
		WmsSessionDO sessionDO = new WmsSessionDO();
		sessionDO.setSessionId("WmsLogin" + CommonUtils.uuid().toUpperCase());
		sessionDO.setExpiredTime(CommonUtils.future(Constants.default_session_expired_seconds));
		sessionDO.setUserInfo(CommonUtils.toJsonString(userDO));
		sessionMapper.insert(sessionDO);
		CookieUtils.addCookie(request, response, Constants.WMS_LOGIN_KEY, sessionDO.getSessionId(), null);
		return ResultBaseBuilder.succ().msg("登录成功").data(user).rb();
	}

}
