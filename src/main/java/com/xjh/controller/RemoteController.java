package com.xjh.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DiandanSystemService;
import com.xjh.service.OrderMaterialService;

@Controller
@RequestMapping("/remote")
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
//		if (user == null) {
//			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
//		}
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
		diandanSystemService.syncRecipes();//同步菜单
		ResultBase<String> rb = diandanSystemService.syncOrders(saleDate);//同步销售订单
		executor.submit(() -> orderMaterialService.handleOrders());
		return ResultBaseBuilder.wrap(rb).rb(request);
	}
}
