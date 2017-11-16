package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.xjh.service.TkMappers;

@Controller
@RequestMapping("/store")
public class StoreController {
	@Resource
	HttpServletRequest request;
	@Resource
	StoreService storeService;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/saveStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveStore() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String storeName = CommonUtils.get(request, "storeName");
		String storeAddr = CommonUtils.get(request, "storeAddr");
		String storeCode = CommonUtils.get(request, "storeCode");
		String storeManager = CommonUtils.get(request, "storeManager");
		String managerPhone = CommonUtils.get(request, "managerPhone");
		String managerEmail = CommonUtils.get(request, "managerEmail");
		String defaultWarehouse = CommonUtils.get(request, "defaultWarehouse");

		WmsStoreDO store = new WmsStoreDO();
		store.setId(id);
		store.setStoreName(storeName);
		store.setStoreCode(storeCode);
		store.setStoreAddr(storeAddr);
		store.setStoreManager(storeManager);
		store.setManagerEmail(managerEmail);
		store.setManagerPhone(managerPhone);
		store.setDefaultWarehouse(defaultWarehouse);
		ResultBase<WmsStoreDO> rs = null;
		if (StringUtils.isBlank(storeCode)) {
			long val = sequenceService.next("wms_store");
			storeCode = "MD" + StringUtils.leftPad(val + "", 4, "0");
			store.setStoreCode(storeCode);
			rs = storeService.addStore(store);
		} else {
			rs = storeService.updateStore(store);
		}
		return ResultBaseBuilder.wrap(rs).rb(request);
	}

	@RequestMapping(value = "/getAllStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAllStore() {

		PageResult<WmsStoreDO> page = storeService.queryStore(null);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/getStoreByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getStoreByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String storeCode = request.getParameter("storeCode");
		WmsStoreDO store = storeService.queryByStoreCode(storeCode);
		if (store == null) {
			return ResultBaseBuilder.fails("门店不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(store).rb(request);
	}

	@RequestMapping(value = "/getMyStore", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getMyStore() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsStoreDO store = new WmsStoreDO();
		final String auths = user.getAuthStores() == null ? "" : user.getAuthStores();

		List<WmsStoreDO> list = TkMappers.inst().getStoreMapper().select(store);
		if (!"1".equals(user.getUserRole())) {
			List<WmsStoreDO> list2 = new ArrayList<>();
			for (WmsStoreDO t : list) {
				if (auths.contains(t.getStoreCode())) {
					list2.add(t);
				}
			}
			list = list2;
		}
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

}
