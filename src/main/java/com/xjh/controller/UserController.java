package com.xjh.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.Constants;
import com.xjh.commons.CookieUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.TkMappers;
import com.xjh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;
	@Resource
	HttpServletRequest request;

	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login(HttpServletResponse response) {
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");// 密码原文
		//
		if (CommonUtils.isAnyBlank(userCode, password)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		if (userCode.equals("administrator") && password.equals("123456")) {
			WmsUserDO userDO = new WmsUserDO();
			userDO.setUserCode(userCode);
			userDO.setUserName("超级管理员");
			return ResultBaseBuilder.succ().data(userDO).rb(request);
		}
		WmsUserDO userDO = new WmsUserDO();
		userDO.setUserCode(userCode);
		userDO.setPassword(CommonUtils.md5(password));
		ResultBase<WmsUserDO> loginRs = userService.login(userDO, request, response);
		if (loginRs.getIsSuccess() == false) {
			return ResultBaseBuilder.fails("登录失败").rb(request);
		}
		WmsUserDO ret = new WmsUserDO();
		ret.setUserName(loginRs.getValue().getUserName());
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}

	@RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object logout(HttpServletResponse response) {
		CookieUtils.addCookie(request, response, Constants.WMS_LOGIN_KEY, null, null);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/queryUsers", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUsers(WmsUserDO userDO) {
		PageResult<WmsUserDO> page = this.userService.queryUsers(userDO);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryUserByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUserByCode() {
		String userCode = CommonUtils.get(request, "userCode");
		if (StringUtils.isBlank(userCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userCode);
		user = TkMappers.inst().getUserMapper().selectOne(user);
		if (user == null) {
			return ResultBaseBuilder.fails("用户信息不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(user).rb(request);
	}

	@RequestMapping(value = "/saveUser", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveUser(WmsUserDO input) {
		if (CommonUtils.isAnyBlank(input.getUserCode())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		// 密码保存为MD5
		if (StringUtils.isNotBlank(input.getPassword())) {
			input.setPassword(CommonUtils.md5(input.getPassword()));
		}
		// 查询用户是否存在
		ResultBase<WmsUserDO> rs = userService.queryUser(input.getUserCode());
		if (rs.getIsSuccess() == false) {
			// 用户不存在，新建用户
			if (CommonUtils.isAnyBlank(input.getUserRole())) {
				return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
			}
			if (StringUtils.isBlank(input.getPassword())) {
				input.setPassword(CommonUtils.md5("123456"));
			}
			if (CommonUtils.isBlank(input.getUserName())) {
				input.setUserName(input.getUserCode());// 默认username=usercode
			}
			input.setStatus("1");
			TkMappers.inst().getUserMapper().insert(input);
		} else {
			// 更新数据
			WmsUserDO user = rs.getValue();
			if (StringUtils.isNotBlank(input.getUserName()))
				user.setUserName(input.getUserName());
			if (StringUtils.isNotBlank(input.getPassword()))
				user.setPassword(input.getPassword());
			if (StringUtils.isNotBlank(input.getUserMobile()))
				user.setUserMobile(input.getUserMobile());
			if (StringUtils.isNotBlank(input.getUserRole()))
				user.setUserRole(input.getUserRole());
			if (StringUtils.isNotBlank(input.getStoreCode()))
				user.setStoreCode(input.getStoreCode());
			if (StringUtils.isNotBlank(input.getStatus()))
				user.setStatus(input.getStatus());
			TkMappers.inst().getUserMapper().updateByPrimaryKeySelective(user);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/resetPassword", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object resetPassword() {
		String userCode = CommonUtils.get(request, "userCode");
		if (CommonUtils.isAnyBlank(userCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userCode);
		user = TkMappers.inst().getUserMapper().selectOne(user);
		if (user != null) {
			WmsUserDO update = new WmsUserDO();
			update.setId(user.getId());
			update.setPassword(CommonUtils.md5("123456"));
			TkMappers.inst().getUserMapper().updateByPrimaryKeySelective(update);
		}
		return ResultBaseBuilder.succ().rb(request);
	}
}
