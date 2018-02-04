package com.xjh.controller;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/store")
@Slf4j
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		Long id = CommonUtils.getLong(request, "id");
		String storeName = CommonUtils.get(request, "storeName");
		String storeAddr = CommonUtils.get(request, "storeAddr");
		String storeCode = CommonUtils.get(request, "storeCode");
		String storeManager = CommonUtils.get(request, "storeManager");
		String managerPhone = CommonUtils.get(request, "managerPhone");
		String managerEmail = CommonUtils.get(request, "managerEmail");
		String outCode = CommonUtils.get(request, "outCode");
		String storeImg = CommonUtils.get(request, "storeImg");
		WmsStoreDO store = new WmsStoreDO();
		store.setId(id);
		store.setStoreName(storeName);
		store.setStoreCode(storeCode);
		store.setStoreAddr(storeAddr);
		store.setStoreManager(storeManager);
		store.setManagerEmail(managerEmail);
		store.setManagerPhone(managerPhone);
		store.setOutCode(outCode);
		store.setStoreImg(storeImg);
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
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		WmsStoreDO cond = new WmsStoreDO();
		cond.setPageSize(300);
		PageResult<WmsStoreDO> page = storeService.queryStore(cond);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/getStoreByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getStoreByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		WmsStoreDO store = new WmsStoreDO();
		final String auths = user.getAuthStores() == null ? "" : user.getAuthStores();

		List<WmsStoreDO> list = TkMappers.inst().getStoreMapper().select(store);
		if (!"1".equals(user.getIsSu())) {
			list = list.stream().filter((v) -> auths.contains(v.getStoreCode())).collect(Collectors.toList());
		}
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

}
