package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialRequireDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialRequireMapper;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/require")
public class MaterialRequireController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsMaterialRequireMapper requireMapper;

	@RequestMapping(value = "/query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object query() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		String cabinCode = CommonUtils.get(request, "cabinCode");
		String startDate = CommonUtils.get(request, "startDate");
		String endDate = CommonUtils.get(request, "endDate");
		String materialCode = CommonUtils.get(request, "materialCode");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);

		Example example = new Example(WmsMaterialRequireDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andEqualTo("materialCode", materialCode);
		cri.andGreaterThanOrEqualTo("requireDate", CommonUtils.parseDate(startDate));
		cri.andLessThanOrEqualTo("requireDate", CommonUtils.parseDate(endDate));
		if (!"1".equals(user.getIsSu())) {
			List<String> mycabins = new ArrayList<>();
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
			cri.andIn("cabinCode", mycabins);
		}
		PageHelper.orderBy("id desc");
		PageHelper.startPage(pageNo, pageSize);
		List<WmsMaterialRequireDO> list = requireMapper.selectByExample(example);
		int totalRows = requireMapper.selectCountByExample(example);

		PageResult<WmsMaterialRequireDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);

		return ResultBaseBuilder.succ().data(page).rb(request);
	}
}
