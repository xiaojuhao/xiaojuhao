package com.xjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsUserDO;

public interface UserService {
	public ResultBase<WmsUserDO> queryUser(String userCode);

	public ResultBase<WmsUserDO> login(WmsUserDO userDO, HttpServletRequest request, HttpServletResponse response);
}
