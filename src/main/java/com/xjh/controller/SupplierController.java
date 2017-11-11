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
import com.xjh.dao.dataobject.WmsSupplierDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Resource
	HttpServletRequest request;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/saveSupplier", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveSupplier() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String supplierCode = CommonUtils.get(request, "supplierCode");
		String supplierName = CommonUtils.get(request, "supplierName");
		String supplierTel = CommonUtils.get(request, "supplierTel");
		String supplierPhone = CommonUtils.get(request, "supplierPhone");
		String supplierEmail = CommonUtils.get(request, "supplierEmail");
		String status = CommonUtils.get(request, "status");

		WmsSupplierDO supplier = new WmsSupplierDO();
		if (StringUtils.isBlank(supplierCode)) {
			long seq = sequenceService.next("wms_supplier");
			supplierCode = "S" + StringUtils.leftPad(seq + "", 4, "0");
			supplier.setSupplierCode(supplierCode);
			supplier.setSupplierName(supplierName);
			supplier.setSupplierTel(supplierTel);
			supplier.setSupplierPhone(supplierPhone);
			supplier.setSupplierEmail(supplierEmail);
			supplier.setStatus("1");
			TkMappers.inst().getSupplierMapper().insert(supplier);
		} else {
			supplier.setSupplierCode(supplierCode);
			supplier = TkMappers.inst().getSupplierMapper().selectOne(supplier);
			if (supplier == null) {
				return ResultBaseBuilder.fails("供应商修改失败:no data").rb(request);
			}
			supplier.setSupplierName(supplierName);
			supplier.setSupplierTel(supplierTel);
			supplier.setSupplierPhone(supplierPhone);
			supplier.setSupplierEmail(supplierEmail);
			supplier.setStatus(status);
		}
		return null;
	}

	@RequestMapping(value = "/querySupplierPage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object querySupplierPage() {
		return null;
	}

	@RequestMapping(value = "/queryAllSupplier", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryAllSupplier() {
		return null;
	}

	@RequestMapping(value = "/querySupplierByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object querySupplierByCode() {
		return null;
	}
}
