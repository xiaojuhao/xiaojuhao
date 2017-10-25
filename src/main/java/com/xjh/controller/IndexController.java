package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.ResultBaseBuilder;

@Controller
public class IndexController {
	@Resource HttpServletRequest request;
	
	@RequestMapping("/index")
	@ResponseBody
	public Object index(){
		return ResultBaseBuilder.succ().data("index page").rb(request);
	}
}
