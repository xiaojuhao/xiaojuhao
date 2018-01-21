package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.RecentMemService;

@Controller
@RequestMapping("/recent")
public class RecentController {
	@Resource
	HttpServletRequest request;
	@Resource
	RecentMemService recentMemService;

	@RequestMapping(value = "/getRecent", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getRecent() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			//return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String group = CommonUtils.get(request, "group");
		String code = CommonUtils.get(request, "code");
		String val = recentMemService.recentValue(group, code);
		if (StringUtils.isBlank(val)) {
			return ResultBaseBuilder.fails("没有信息").rb(request);
		}
		return ResultBaseBuilder.succ().data(val).rb(request);
	}

	@RequestMapping(value = "/saveRecent", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveRecent() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			//return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String group = CommonUtils.get(request, "group");
		String code = CommonUtils.get(request, "code");
		String val = CommonUtils.get(request, "val");
		if (CommonUtils.isAnyBlank(group, code, val)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		recentMemService.saveRecent(group, code, val);
		return ResultBaseBuilder.succ().rb(request);
	}
}
