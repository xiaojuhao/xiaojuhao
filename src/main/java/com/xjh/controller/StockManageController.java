package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/storeManage")
@Slf4j
public class StockManageController {
	@Resource
	HttpServletRequest request;

	@RequestMapping(value = "/saveWarning", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveWarning() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String materialCode = CommonUtils.get(request, "materialCode");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		Double val1 = CommonUtils.getDbl(request, "warningValue1", 0D);
		Double val2 = CommonUtils.getDbl(request, "warningValue2", 0D);
		if (CommonUtils.isAnyBlank(materialCode, cabinCode)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsMaterialStockDO stock = new WmsMaterialStockDO();
		stock.setMaterialCode(materialCode);
		stock.setCabinCode(cabinCode);
		stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
		if (stock != null) {
			stock.setWarningValue1(val1);
			stock.setWarningValue2(val2);
			TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(stock);
		}
		return ResultBaseBuilder.succ().rb(request);
	}
}
