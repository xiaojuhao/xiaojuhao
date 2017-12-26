package com.xjh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
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

	@RequestMapping(value = "/handleRequire", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object handleRequire() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String requires = CommonUtils.get(request, "requires");
		String handleType = CommonUtils.get(request, "handleType");
		JSONArray jsonArr = CommonUtils.parseJSONArray(requires);
		if (jsonArr.size() == 0) {
			return ResultBaseBuilder.fails("请选择记录").rb(request);
		}
		List<WmsMaterialRequireDO> list = new ArrayList<>();
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject json = jsonArr.getJSONObject(i);
			WmsMaterialRequireDO dd = new WmsMaterialRequireDO();
			dd.setId(CommonUtils.parseLong(json.getString("id"), null));
			dd.setSpecCode(json.getString("specCode"));
			dd.setSpecName(json.getString("specName"));
			dd.setSupplierCode(json.getString("supplierCode"));
			dd.setSupplierName(json.getString("supplierName"));
			dd.setSpecAmt(CommonUtils.parseDouble(json.getString("specAmt"), null));
			dd.setSpecUnit(json.getString("specUnit"));
			dd.setGmtModified(new Date());
			dd.setModifier(user.getUserCode());
			if (dd.getId() == null) {
				return ResultBaseBuilder.fails("入参错误:缺少ID字段").rb(request);
			}
			list.add(dd);
			requireMapper.updateByPrimaryKeySelective(dd);
		}
		//生成采购单
		if ("2".equals(handleType)) {
			WmsInventoryApplyDO apply = new WmsInventoryApplyDO();
			List<WmsInventoryApplyDetailDO> applyDetails = new ArrayList<>();
			String applyNum = CommonUtils.uuid();
			String cabinCode = null;
			String cabinName = null;
			String supplierCode = null;
			String supplierName = null;
			for (WmsMaterialRequireDO r : list) {
				cabinCode = r.getCabinCode();
				cabinName = r.getCabinName();
				r.setStatus("1");
				WmsInventoryApplyDetailDO de = new WmsInventoryApplyDetailDO();
				de.setApplyNum(applyNum);
				de.setCabinCode(r.getCabinCode());
				de.setCabinName(r.getCabinName());
				de.setMaterialCode(r.getMaterialCode());
				de.setMaterialName(r.getMaterialName());
				de.setSupplierCode(r.getSupplierCode());
				de.setSupplierName(r.getSupplierName());
				de.setApplyType("purchase");
				de.setSpecCode(r.getSpecCode());
				de.setSpecUnit(r.getSpecUnit());
				de.setSpecAmt(r.getSpecAmt());
				de.setSpecPrice(r.getSpecPrice());
				de.setStockUnit(r.getStockUnit());
				de.setStockAmt(r.getStockAmt());
				de.setStatus("0");
				de.setGmtCreated(new Date());
				de.setCreator(user.getUserCode());
				de.setGmtModified(new Date());
				de.setModifier(user.getUserCode());
				applyDetails.add(de);
			}
			assert applyNum != null;
			apply.setApplyNum(applyNum);
			apply.setApplyType("purchase");
			apply.setSerialNo(CommonUtils.uuid());
			apply.setCabinCode(cabinCode);
			apply.setCabinName(cabinName);
			apply.setSupplierCode(supplierCode);
			apply.setSupplierName(supplierName);
			apply.setProposer(user.getUserCode());
			apply.setStatus("2");
			apply.setTotalPrice(0D);
			apply.setPayables(0D);
			apply.setPaidStatus("0");
			apply.setPaidAmt(0D);
			apply.setGmtCreated(new Date());
			apply.setGmtModified(new Date());
			apply.setCreator(user.getUserCode());
			apply.setModifier(user.getUserCode());
		}
		return ResultBaseBuilder.succ().rb(request);
	}

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
		String status = CommonUtils.get(request, "status");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);

		Example example = new Example(WmsMaterialRequireDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andEqualTo("materialCode", materialCode);
		cri.andEqualTo("status", status);
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
