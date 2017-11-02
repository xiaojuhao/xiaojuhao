package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;
	@Resource
	HttpServletRequest request;
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletResponse response){
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");//密码原文
		//
		if(CommonUtils.isAnyBlank(userCode,password)){
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsUserDO userDO = new WmsUserDO();
		userDO.setUserCode(userCode);
		userDO.setPassword(CommonUtils.md5(password));
		ResultBase<WmsUserDO> loginRs = userService.login(userDO,request,response);
		if(loginRs.getIsSuccess() == false){
			return ResultBaseBuilder.fails("登录失败").rb(request);
		}
		return ResultBaseBuilder.succ().data("登录成功").rb(request);
	}
}
