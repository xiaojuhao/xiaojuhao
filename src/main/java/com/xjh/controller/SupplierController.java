package com.xjh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsSupplierDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/supplier")
@Slf4j
public class SupplierController {
	@Resource
	HttpServletRequest request;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/saveSupplier", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveSupplier() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			String supplierCode = CommonUtils.get(request, "supplierCode");
			String supplierName = CommonUtils.get(request, "supplierName");
			String supplierTel = CommonUtils.get(request, "supplierTel");
			String supplierPhone = CommonUtils.get(request, "supplierPhone");
			String supplierEmail = CommonUtils.get(request, "supplierEmail");
			String supplierFullName = CommonUtils.get(request, "supplierFullName");
			String status = CommonUtils.get(request, "status");
			String payMode = CommonUtils.get(request, "payMode");
			String payWay = CommonUtils.get(request, "payWay");
			String bankName = CommonUtils.get(request, "bankName");
			String depositBank = CommonUtils.get(request, "depositBank");
			String bankAccount = CommonUtils.get(request, "bankAccount");
			String bankAccountName = CommonUtils.get(request, "bankAccountName");
			String alipayAccount = CommonUtils.get(request, "alipayAccount");
			String alipayAccountName = CommonUtils.get(request, "alipayAccountName");
			String weixinAccount = CommonUtils.get(request, "weixinAccount");
			String weixinAccountName = CommonUtils.get(request, "weixinAccountName");
			String remark = CommonUtils.get(request, "remark");

			String materialJson = CommonUtils.get(request, "materialJson");
			WmsSupplierDO supplier = new WmsSupplierDO();
			// 如果供应商不存在，则新增供应商
			if (StringUtils.isBlank(supplierCode)) {
				long seq = sequenceService.next("wms_supplier");
				supplierCode = "S" + StringUtils.leftPad(seq + "", 4, "0");
				supplier.setSupplierCode(supplierCode);
				supplier.setSupplierName(supplierName);
				supplier.setSupplierFullName(supplierFullName);
				supplier.setSupplierTel(supplierTel);
				supplier.setSupplierPhone(supplierPhone);
				supplier.setSupplierEmail(supplierEmail);
				supplier.setPayMode(payMode);
				//只有超级管理员才能添加银行账户信息
				if ("1".equals(user.getIsSu())) {
					supplier.setPayWay(payWay);
					supplier.setBankName(bankName);
					supplier.setDepositBank(depositBank);
					supplier.setBankAccount(bankAccount);
					supplier.setBankAccountName(bankAccountName);
					supplier.setAlipayAccount(alipayAccount);
					supplier.setAlipayAccountName(alipayAccountName);
					supplier.setWeixinAccount(weixinAccount);
					supplier.setWeixinAccountName(weixinAccountName);
				}
				supplier.setRemark(remark);
				supplier.setModifer(user.getUserCode());
				supplier.setStatus("1");
				supplier.setGmtModified(new Date());
				supplier.setIsDeleted("N");
				TkMappers.inst().getSupplierMapper().insert(supplier);
			}
			// 否则修改供应商信息
			else {
				WmsSupplierDO cond = new WmsSupplierDO();
				cond.setSupplierCode(supplierCode);
				cond = TkMappers.inst().getSupplierMapper().selectOne(cond);
				// code不存在
				if (cond == null) {
					return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
				}
				supplier.setSupplierCode(supplierCode);
				supplier.setId(cond.getId());
				supplier = TkMappers.inst().getSupplierMapper().selectOne(supplier);
				if (supplier == null) {
					return ResultBaseBuilder.fails("供应商修改失败:no data").rb(request);
				}
				supplier.setSupplierTel(supplierTel);
				supplier.setSupplierPhone(supplierPhone);
				supplier.setSupplierEmail(supplierEmail);
				//只有超级管理员才能修改银行账户信息 + 名字
				if ("1".equals(user.getIsSu())) {
					supplier.setSupplierName(supplierName);
					supplier.setSupplierFullName(supplierFullName);
					supplier.setPayMode(payMode);
					supplier.setPayWay(payWay);
					supplier.setBankName(bankName);
					supplier.setDepositBank(depositBank);
					supplier.setBankAccount(bankAccount);
					supplier.setBankAccountName(bankAccountName);
					supplier.setAlipayAccount(alipayAccount);
					supplier.setAlipayAccountName(alipayAccountName);
					supplier.setWeixinAccount(weixinAccount);
					supplier.setWeixinAccountName(weixinAccountName);
				}
				supplier.setRemark(remark);
				supplier.setModifer(user.getUserCode());
				supplier.setGmtModified(new Date());
				supplier.setStatus(status);
				TkMappers.inst().getSupplierMapper().updateByPrimaryKey(supplier);
			}
			// 保存供应商供应的菜品信息
			JSONArray materials = CommonUtils.parseJSONArray(materialJson);
			List<WmsMaterialSupplierDO> materialSupplierList = new ArrayList<>();
			if (materials.size() > 0) {
				for (int i = 0; i < materials.size(); i++) {
					JSONObject json = materials.getJSONObject(i);
					String materialCode = json.getString("materialCode");
					if (StringUtils.isBlank(materialCode)) {
						continue;
					}
					WmsMaterialDO material = new WmsMaterialDO();
					material.setMaterialCode(materialCode);
					material = TkMappers.inst().getMaterialMapper().selectOne(material);
					if (material == null) {
						continue;
					}
					WmsMaterialSupplierDO ss = new WmsMaterialSupplierDO();
					ss.setSupplierCode(supplier.getSupplierCode());
					ss.setSupplierName(supplier.getSupplierName());
					ss.setMaterialCode(material.getMaterialCode());
					ss.setMaterialName(material.getMaterialName());
					ss.setIsDeleted("N");
					materialSupplierList.add(ss);
				}
			}
			if (materialSupplierList.size() > 0) {
				// 先删除后插入
				WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
				ms.setSupplierCode(supplier.getSupplierCode());
				TkMappers.inst().getMaterialSupplierMapper().delete(ms); // deleteAll
				for (WmsMaterialSupplierDO t : materialSupplierList) {
					TkMappers.inst().getMaterialSupplierMapper().insert(t);
				}
			}
			return ResultBaseBuilder.succ().data(supplier).rb(request);
		} catch (Exception e) {
			log.error("", e);
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/querySupplierPage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object querySupplierPage() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String supplierCode = CommonUtils.get(request, "supplierCode");
		WmsSupplierDO cond = new WmsSupplierDO();
		cond.setSupplierCode(supplierCode);
		cond.setStatus(CommonUtils.get(request, "status"));
		cond.setPageNo(CommonUtils.getPageNo(request));// 分页信息
		cond.setPageSize(CommonUtils.getPageSize(request));// 分页信息
		cond.setIsDeleted("N");
		int totalRows = TkMappers.inst().getSupplierMapper().selectCount(cond);
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		List<WmsSupplierDO> list = TkMappers.inst().getSupplierMapper().select(cond);

		PageResult<WmsSupplierDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setPageNo(CommonUtils.getPageNo(request));
		page.setPageSize(CommonUtils.getPageSize(request));
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/querySupplierByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object querySupplierByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String supplierCode = CommonUtils.get(request, "supplierCode");
		if (StringUtils.isBlank(supplierCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsSupplierDO cond = new WmsSupplierDO();
		cond.setSupplierCode(supplierCode);
		cond.setIsDeleted("N");
		WmsSupplierDO supplier = TkMappers.inst().getSupplierMapper().selectOne(cond);
		if (supplier == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		return ResultBaseBuilder.succ().data(supplier).rb(request);
	}
}
