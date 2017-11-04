package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.SequenceService;
import com.xjh.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	@Resource
	HttpServletRequest request;
	@Resource
	StoreService storeService;
	@Resource
	SequenceService sequenceService;
	
	@RequestMapping(value="/addStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addStore(){
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if(user == null){
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String storeName = request.getParameter("storeName");
		String storeAddr = request.getParameter("storeAddr");
		String storeManager = request.getParameter("storeManager");
		String managerPhone = request.getParameter("managerPhone");
		String managerEmail = request.getParameter("managerEmail");
		long val = sequenceService.next("wms_store");
		String storeCode = "MD"+StringUtils.leftPad(val+"", 4, "0");
		WmsStoreDO store = new WmsStoreDO();
		store.setStoreName(storeName);
		store.setStoreCode(storeCode);
		store.setStoreAddr(storeAddr);
		store.setStoreManager(storeManager);
		store.setManagerEmail(managerEmail);
		store.setManagerPhone(managerPhone);
		ResultBase<WmsStoreDO> rs = storeService.addStore(store);
		return ResultBaseBuilder.wrap(rs).rb(request);
	}
	@RequestMapping(value="/updateStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object updateStore(){
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if(user == null){
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if(id == null){
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		String storeName = request.getParameter("storeName");
		String storeAddr = request.getParameter("storeAddr");
		String storeManager = request.getParameter("storeManager");
		String managerPhone = request.getParameter("managerPhone");
		String managerEmail = request.getParameter("managerEmail");
		long val = sequenceService.next("wms_store");
		String storeCode = "M"+StringUtils.leftPad(val+"", 3, "0");
		WmsStoreDO store = new WmsStoreDO();
		store.setId(id);
		store.setStoreName(storeName);
		store.setStoreCode(storeCode);
		store.setStoreAddr(storeAddr);
		store.setStoreManager(storeManager);
		store.setManagerEmail(managerEmail);
		store.setManagerPhone(managerPhone);
		ResultBase<Integer> rs = storeService.updateStore(store);
		return ResultBaseBuilder.wrap(rs).rb(request);
	}
	@RequestMapping(value="/getAllStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAllStore(){
		PageResult<WmsStoreDO> page = storeService.queryStore(null);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}
	
	@RequestMapping(value="/getStoreByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getStoreByCode(){
		String storeCode = request.getParameter("storeCode");
		WmsStoreDO store = storeService.queryByStoreCode(storeCode);
		if(store == null){
			return ResultBaseBuilder.fails("门店不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(store).rb(request);
	}
}
