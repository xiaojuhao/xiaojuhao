package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialSplitDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.service.CabinService;
import com.xjh.service.MaterialService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/busi")
public class BusinessController {
	@Resource
	HttpServletRequest request;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper stockMapper;
	@Resource
	TkWmsStoreMapper storeMapper;
	@Resource
	TkWmsWarehouseMapper warehouseMapper;
	@Resource
	CabinService cabinService;
	@Resource
	TkWmsMaterialStockHistoryMapper stockHistoryMapper;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/addMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		String storageLifeNum = CommonUtils.get(request, "storageLifeNum");
		String storageLifeUnit = CommonUtils.get(request, "storageLifeUnit");
		String specUnit = CommonUtils.get(request, "specUnit");
		String specQty = CommonUtils.get(request, "specQty");

		WmsMaterialDO material = new WmsMaterialDO();
		material.setId(CommonUtils.getLong(request, "id"));
		material.setMaterialCode(CommonUtils.get(request, "materialCode"));
		material.setMaterialName(CommonUtils.get(request, "materialName"));
		String searchKey = CommonUtils.get(request, "searchKey");
		material.setSpecUnit(specUnit);
		material.setSpecQty(CommonUtils.parseDouble(specQty, 1D));
		material.setSearchKey(CommonUtils.genSearchKey(material.getMaterialName(), searchKey));
		if (!CommonUtils.isAnyBlank(storageLifeNum, storageLifeUnit)) {
			material.setStorageLife(storageLifeNum + storageLifeUnit);
		}
		material.setStockUnit(CommonUtils.get(request, "stockUnit"));
		material.setUtilizationRatio(CommonUtils.getInt(request, "utilizationRatio"));
		material.setStatus(1);

		if (StringUtils.isBlank(material.getMaterialName())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (material.getId() == null) {
			long nextVal = this.sequenceService.next("wms_material");
			String materialCode = "M" + StringUtils.leftPad(nextVal + "", 5, '0');
			material.setMaterialCode(materialCode);
			material.setStatus(1);
			this.materialService.insertMaterial(material);
		} else {
			this.materialService.updateMaterial(material);
		}
		return ResultBaseBuilder.succ().data(material).rb(request);
	}

	@RequestMapping(value = "/queryMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		String materialCode = CommonUtils.get(request, "materialCode");
		Long id = CommonUtils.getLong(request, "id");
		WmsMaterialDO example = new WmsMaterialDO();
		example.setPageNo(pageNo);
		example.setPageSize(pageSize);
		example.setMaterialCode(materialCode);
		example.setId(id);
		PageResult<WmsMaterialDO> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryMaterialSplitByMaterialCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialSplitByMaterialCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		String materialCode = CommonUtils.get(request, "materialCode");
		List<WmsMaterialSplitDO> splits = new ArrayList<>();
		if (StringUtils.isBlank(materialCode)) {
			return ResultBaseBuilder.succ().data(splits).rb(request);
		}
		WmsMaterialSplitDO cond = new WmsMaterialSplitDO();
		cond.setMaterialCode(materialCode);
		splits = TkMappers.inst().getMaterialSplitMapper().select(cond);
		return ResultBaseBuilder.succ().data(splits).rb(request);
	}

	@RequestMapping(value = "/queryMaterialById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialById() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		Long id = CommonUtils.getLong(request, "id");
		if (id == null) {
			return ResultBaseBuilder.fails("无数据").rb(request);
		}
		WmsMaterialDO example = new WmsMaterialDO();
		example.setId(id);
		PageResult<WmsMaterialDO> list = this.materialService.queryMaterials(example);
		if (list == null || list.getValues() == null || list.getValues().size() == 0) {
			return ResultBaseBuilder.fails("无数据").rb(request);
		}
		return ResultBaseBuilder.succ().data(list.getValues().get(0)).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageSize = CommonUtils.getPageSize(request);
		int pageNo = CommonUtils.getPageNo(request);
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		String materialCode = CommonUtils.get(request, "materialCode");
		String cabCode = CommonUtils.get(request, "cabinCode");
		String cabType = CommonUtils.get(request, "cabinType");

		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		example.setMaterialCode(materialCode);
		example.setCabinCode(cabCode);
		example.setCabinType(cabType);
		example.setPageSize(pageSize);
		example.setPageNo(pageNo);
		PageResult<WmsMaterialStockDO> page = this.materialService.queryMaterialsStock(example, user);
		List<WmsMaterialStockDO> tempList = page.getValues();
		if (tempList != null) {
			for (WmsMaterialStockDO stock : tempList) {
				String searchKey = CommonUtils.genSearchKey(stock.getMaterialName(), "");
				searchKey += "," + CommonUtils.genSearchKey(stock.getCabinName(), "");
				stock.setSearchKey(searchKey);
			}
		}
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStockById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockById() {
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if (id == null) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		PageResult<WmsMaterialStockDO> page = this.materialService.queryMaterialsStock(example, null);
		if (page.getValues() == null || page.getValues().size() == 0) {
			return ResultBaseBuilder.fails("数据不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(page.getValues().get(0)).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStockHistory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockHistory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		String materialCode = CommonUtils.get(request, "materialCode");
		String opTypes = CommonUtils.get(request, "opTypes");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (CommonUtils.isAnyBlank(materialCode, cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialStockHistoryDO dd = new WmsMaterialStockHistoryDO();
		dd.setMaterialCode(materialCode);
		dd.setCabinCode(cabinCode);
		dd.setPageSize(pageSize);
		dd.setPageNo(pageNo);

		Example example = new Example(WmsMaterialStockHistoryDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo(dd);
		if (StringUtils.isNotBlank(opTypes)) {
			cri.andIn("opType", CommonUtils.splitAsList(opTypes, ","));
		}
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("id desc");
		List<WmsMaterialStockHistoryDO> list = stockHistoryMapper.selectByExample(example);
		int totalRows = this.stockHistoryMapper.selectCountByExample(example);
		PageResult<WmsMaterialStockHistoryDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMaterialSupplerByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialSuppler() {
		String materialCode = CommonUtils.get(request, "materialCode");
		String supplierCode = CommonUtils.get(request, "supplierCode");
		if (CommonUtils.isAllBlank(materialCode, supplierCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
		ms.setSupplierCode(supplierCode);
		ms.setMaterialCode(materialCode);
		List<WmsMaterialSupplierDO> list = TkMappers.inst().getMaterialSupplierMapper().select(ms);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/getCabinByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCabinByCode() {
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		CabinVo vo = cabinService.getCabinByCode(cabinCode);
		if (vo == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		return ResultBaseBuilder.succ().data(vo).rb(request);
	}

	@RequestMapping(value = "/queryAllMaterialSuppler", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryAllMaterialSuppler() {
		WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
		ms.setPageSize(3000);
		List<WmsMaterialSupplierDO> list = TkMappers.inst().getMaterialSupplierMapper().select(ms);
		List<JSONObject> retList = new ArrayList<>();
		for (WmsMaterialSupplierDO s : list) {
			JSONObject json = CommonUtils.toJSONObject(s);
			String searchKey = CommonUtils.genSearchKey(s.getMaterialName(), "");
			searchKey += "," + CommonUtils.genSearchKey(s.getSupplierName(), "");
			json.put("searchKey", searchKey);
			retList.add(json);
		}
		return ResultBaseBuilder.succ().data(retList).rb(request);
	}
}
