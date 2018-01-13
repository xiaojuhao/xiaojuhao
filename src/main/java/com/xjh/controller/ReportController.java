package com.xjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.mapper.StockReportMapper;
import com.xjh.service.vo.StockReportVo;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Resource
	HttpServletRequest request;
	@Resource
	StockReportMapper stockReportMapper;

	@RequestMapping(value = "/stockReport", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object stockReport() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		String cabinCode = CommonUtils.get(request, "cabinCode");
		String materialCode = CommonUtils.get(request, "materialCode");
		String searchKey = CommonUtils.get(request, "searchKey");
		String startDate = CommonUtils.get(request, "startDate");
		String endDate = CommonUtils.get(request, "endDate");
		if (CommonUtils.isAllBlank(cabinCode, materialCode, searchKey)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		StockReportVo input = new StockReportVo();
		input.setCabinCode(cabinCode);
		input.setSearchKey(searchKey);
		input.setMaterialCode(materialCode);
		input.setStartDate(CommonUtils.parseDate(startDate));
		if (StringUtils.isNotBlank(endDate)) {
			input.setEndDate(DateBuilder.base(CommonUtils.parseDate(endDate)).futureDays(1).date());
		}
		List<StockReportVo> list = stockReportMapper.reportData(input);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

}
