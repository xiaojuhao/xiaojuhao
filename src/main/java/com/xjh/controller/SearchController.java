package com.xjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.service.SearchService;

@Controller
@RequestMapping("/s")
public class SearchController {
	@Resource
	HttpServletRequest request;
	@Resource
	SearchService searchService;
	
	@RequestMapping(value = "/w", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object material() {
		String w = request.getParameter("w");
		if (StringUtils.isBlank(w)) {
			return ResultBaseBuilder.succ().rb(request);
		}
		List<JSONObject> list = searchService.search(w);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
