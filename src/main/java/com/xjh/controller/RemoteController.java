package com.xjh.controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DiandanSystemService;
import com.xjh.service.OrderMaterialService;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/remote")
@Slf4j
public class RemoteController {
	@Resource
	HttpServletRequest request;

	@Resource
	DiandanSystemService diandanSystemService;
	@Resource
	OrderMaterialService orderMaterialService;

	ExecutorService executor = Executors.newFixedThreadPool(1);

	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	@RequestMapping(value = "/initWmsOrderSearchKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object initWmsOrderSearchKey() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String date = CommonUtils.get(request, "date");
		Date saleDate = CommonUtils.parseDate(date, "yyyyMMdd");
		this.diandanSystemService.initSearchKey(saleDate);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncStores", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncStores() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
		String storeCode = CommonUtils.get(request, "storeCode");
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		this.diandanSystemService.syncRecipes(storeCode);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/getRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getRecipes() throws IOException {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String storeCode = CommonUtils.get(request, "storeCode");
		if (storeCode == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsStoreDO store = new WmsStoreDO();
		store.setStoreCode(storeCode);
		store = TkMappers.inst().getStoreMapper().selectOne(store);
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		JSONObject json = diandanSystemService.getRecipes(store.getOutCode());
		return ResultBaseBuilder.succ().data(json).rb(request);
	}

	@RequestMapping(value = "/syncOrders", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncOrders() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String date = CommonUtils.get(request, "date");
		String storeCode = CommonUtils.get(request, "storeCode");
		if(StringUtils.isBlank(storeCode)){
			return ResultBaseBuilder.fails(ResultCode.param_missing).msg("请选择门店").rb(request);
		}
		if(StringUtils.isBlank(date)){
			return ResultBaseBuilder.fails(ResultCode.param_missing).msg("请选择同步日期").rb(request);
		}
		Date saleDate = CommonUtils.parseDate(date, "yyyy-MM-dd");
		diandanSystemService.syncRecipes();//同步菜单
		ResultBase<String> rb = diandanSystemService.syncOrders(saleDate, storeCode);//同步销售订单
		executor.submit(() -> orderMaterialService.handleOrders());
		return ResultBaseBuilder.wrap(rb).rb(request);
	}
}
