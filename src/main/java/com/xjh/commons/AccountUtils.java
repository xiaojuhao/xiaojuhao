package com.xjh.commons;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xjh.dao.dataobject.WmsSessionDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.TkMappers;

public class AccountUtils {
	public static WmsUserDO getLoginUser(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String loginSessionKey = CookieUtils.getCookie(request, Constants.WMS_LOGIN_KEY);
		if (loginSessionKey == null) {
			return null;
		}
		WmsSessionDO record = new WmsSessionDO();
		record.setSessionId(loginSessionKey);
		WmsSessionDO session = TkMappers.inst().getSessionMapper().selectOne(record);
		if (session == null) {
			return null;
		}
		JSONObject json = CommonUtils.parseJSON(session.getUserInfo());
		String userCode = json.getString("userCode");
		if (StringUtils.isBlank(userCode)) {
			return null;
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userCode);
		user = TkMappers.inst().getUserMapper().selectOne(user);
		if (user != null && !StringUtils.equals(user.getStatus(), "1")) {
			return null;
		}
		return user;
	}
}
