package com.xjh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.HttpUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DatabaseService;
import com.xjh.service.DiandanSystemService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;

import io.reactivex.Observable;

@Controller
@RequestMapping("/remote")
public class RemoteController {
	@Resource
	HttpServletRequest request;

	@Resource
	DiandanSystemService diandanSystemService;
	@Resource
	DatabaseService database;

	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	@RequestMapping(value = "/syncStores", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncStores() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		this.diandanSystemService.syncStores();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		this.diandanSystemService.syncRecipes();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncOrders", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncOrders() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String date = CommonUtils.get(request, "date");
		Date saleDate = CommonUtils.parseDate(date, "yyyy-MM-dd");
		return ResultBaseBuilder.wrap(diandanSystemService.syncOrders(saleDate)).rb(request);
	}
}
