package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.Constants;
import com.xjh.commons.CookieUtils;
import com.xjh.commons.ResultBaseBuilder;
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
	public Object login(){
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		String sessionId = CookieUtils.getCookie(request, Constants.JSESSIONID);
		
		if(userService.isValidSession(sessionId).getIsSuccess()){
			return ResultBaseBuilder.succ().rb(request);
		}
		//
		return ResultBaseBuilder.succ().data("登录成功").rb(request);
		
	}
}
