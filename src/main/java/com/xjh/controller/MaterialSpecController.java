package com.xjh.controller;

import java.util.ArrayList;
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
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialSpecDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.SequenceService;

@Controller
@RequestMapping("/spec")
public class MaterialSpecController {
	@Resource
	HttpServletRequest request;
	@Resource
	MaterialSpecService specService;
	@Resource
	TkWmsMaterialSpecDetailMapper detailMapper;

	@Resource
	SequenceService sequence;

	@RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object save() {
		String specCode = CommonUtils.get(request, "specCode");
		String specName = CommonUtils.get(request, "specName");
		String isDeleted = CommonUtils.get(request, "isDeleted");
		if (isDeleted == null)
			isDeleted = "N";
		String brandName = CommonUtils.get(request, "brandName");
		String specUnit = CommonUtils.get(request, "specUnit");
		String detail = CommonUtils.get(request, "detail");
		WmsMaterialSpecDO spec = new WmsMaterialSpecDO();
		spec.setSpecCode(specCode);
		spec.setSpecName(specName);
		spec.setIsDeleted(isDeleted);
		spec.setBrandName(brandName);
		spec.setSpecUnit(specUnit);

		if (StringUtils.isBlank(spec.getSpecCode())) {
			specService.updateByCode(spec);
		} else {
			specCode = specService.nextSpecCode();
			spec.setSpecCode(specCode);
			specService.insert(spec);
		}
		assert StringUtils.isNotBlank(specCode);
		WmsMaterialSpecDetailDO deleteCond = new WmsMaterialSpecDetailDO();
		deleteCond.setSpecCode(specCode);
		detailMapper.delete(deleteCond);
		//原料
		JSONArray detailArr = CommonUtils.parseJSONArray(detail);
		List<WmsMaterialSpecDetailDO> detailList = new ArrayList<>();
		for (int i = 0; i < detailArr.size(); i++) {
			JSONObject j = detailArr.getJSONObject(i);
			WmsMaterialSpecDetailDO d = new WmsMaterialSpecDetailDO();
			d.setSpecCode(specCode);
			d.setSpecName(specName);
			d.setMaterialCode(j.getString("materialCode"));
			d.setMaterialName(j.getString("materialName"));
			d.setTransRate(j.getBigDecimal("transRate"));
			d.setIsDefault(j.getString("isDefault"));
			d.setIsDeleted("N");
			d.setStockUnit(j.getString("stockUnit"));
			if (StringUtils.isBlank(d.getIsDefault())) {
				d.setIsDefault("N");
			}
			detailList.add(d);
		}

		for (WmsMaterialSpecDetailDO dd : detailList) {
			detailMapper.insert(dd);
		}
		return ResultBaseBuilder.succ().data(spec).rb(request);
	}

	@RequestMapping(value = "/query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object query() {
		String specCode = CommonUtils.get(request, "specCode");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		if (StringUtils.isBlank(specCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialSpecDO cond = new WmsMaterialSpecDO();
		cond.setPageNo(pageNo);
		cond.setPageSize(pageSize);
		cond.setSpecCode(specCode);
		List<WmsMaterialSpecDO> list = specService.queryByExample(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryByCode() {
		String specCode = CommonUtils.get(request, "specCode");
		if (StringUtils.isBlank(specCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialSpecDO spec = specService.queryByCode(specCode);
		if (spec == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		return ResultBaseBuilder.succ().data(spec).rb(request);
	}

	@RequestMapping(value = "/queryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryDetail() {
		String specCode = CommonUtils.get(request, "specCode");
		String materialCode = CommonUtils.get(request, "materialCode");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		WmsMaterialSpecDetailDO cond = new WmsMaterialSpecDetailDO();
		cond.setSpecCode(specCode);
		cond.setMaterialCode(materialCode);
		cond.setPageNo(pageNo);
		cond.setPageSize(pageSize);
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("id");
		List<WmsMaterialSpecDetailDO> list = this.detailMapper.select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
