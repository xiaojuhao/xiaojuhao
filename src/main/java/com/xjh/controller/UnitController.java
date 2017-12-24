package com.xjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUnitGroupDO;
import com.xjh.dao.tkmapper.TkWmsUnitGroupMapper;

@Controller
@RequestMapping("/unit")
public class UnitController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsUnitGroupMapper unitGroupMapper;

	@RequestMapping(value = "/queryUnitByGroup", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUnitByGroup() {
		String groupCode = CommonUtils.get(request, "groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUnitGroupDO cond = new WmsUnitGroupDO();
		cond.setGroupCode(groupCode);
		PageHelper.orderBy("order_by,id");
		List<WmsUnitGroupDO> list = unitGroupMapper.select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
