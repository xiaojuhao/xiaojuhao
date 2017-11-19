package com.xjh.controller;

import java.math.BigDecimal;
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
import com.xjh.dao.dataobject.WmsMaterialSplitDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

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
		material.setCanSplit(CommonUtils.get(request, "canSplit"));
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
		materialService.initMaterialStock(material.getMaterialCode(), null);
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
		String stockType = CommonUtils.get(request, "stockType");

		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		example.setMaterialCode(materialCode);
		example.setCabinCode(cabCode);
		example.setCabinType(cabType);
		example.setPageSize(pageSize);
		example.setPageNo(pageNo);
		example.setStockType(stockType);
		PageResult<WmsMaterialStockDO> page = this.materialService.queryMaterialsStock(example, user);
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

	@RequestMapping(value = "/queryAllMaterialSuppler", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryAllMaterialSuppler() {
		WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
		ms.setPageSize(3000);
		List<WmsMaterialSupplierDO> list = TkMappers.inst().getMaterialSupplierMapper().select(ms);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	/**
	 * 出库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/outstock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object outstock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");
		String outstockAmtStr = CommonUtils.get(request, "outstockAmt");
		BigDecimal outstockAmt = CommonUtils.parseBigDecimal(outstockAmtStr);
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (outstockAmt == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (StringUtils.isAnyBlank(materialCode, cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		String cabinType = cabinCode.startsWith("WH") ? "1" : "2";
		WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
		d.setCabinCode(cabinCode);
		d.setCabinType(cabinType);
		d.setAmt(-1 * outstockAmt.doubleValue());
		d.setMaterialCode(materialCode);
		d.setOpType("out_stock");
		d.setStatus("0");
		d.setGmtCreated(new Date());
		d.setOperator(user.getUserCode());
		TkMappers.inst().getMaterialStockHistoryMapper().insertSelective(d);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/outstockByRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object outstockByRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String storeCode = CommonUtils.get(request, "storeCode");
		String recipesJson = CommonUtils.get(request, "recipesJson");
		if (StringUtils.isAnyBlank(storeCode, recipesJson)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsStoreDO store = new WmsStoreDO();
		store.setStoreCode(storeCode);
		store = this.storeMapper.selectOne(store);
		if (store == null) {
			return ResultBaseBuilder.fails("门店不存在").rb(request);
		}
		JSONArray recipes = CommonUtils.parseJSONArray(recipesJson);
		if (recipes.size() == 0) {
			return ResultBaseBuilder.fails("请输入菜单信息").rb(request);
		}
		for (int i = 0; i < recipes.size(); i++) {
			JSONObject reci = recipes.getJSONObject(i);
			String recipesCode = reci.getString("recipesCode");
			Double amt = reci.getDouble("amt");
			WmsRecipesFormulaDO formula = new WmsRecipesFormulaDO();
			formula.setRecipesCode(recipesCode);
			List<WmsRecipesFormulaDO> formulas = TkMappers.inst().getRecipesFormulaMapper().select(formula);
			for (WmsRecipesFormulaDO f : formulas) {
				WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
				d.setCabinCode(storeCode);
				d.setCabinType("2");
				d.setAmt(-1 * f.getMaterialAmt() * amt);
				d.setMaterialCode(f.getMaterialCode());
				d.setOpType("out_stock");
				d.setStatus("0");
				d.setGmtCreated(new Date());
				d.setOperator(user.getUserCode());
				d.setRemark("按菜单出库:" + recipesCode);
				d.setRelateCode(recipesCode);
				TkMappers.inst().getMaterialStockHistoryMapper().insertSelective(d);
			}
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/instock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object instock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");
		String instockAmtStr = CommonUtils.get(request, "instockAmt");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		BigDecimal instockAmt = CommonUtils.parseBigDecimal(instockAmtStr);
		if (instockAmt == null || Math.abs(instockAmt.doubleValue()) < 0.009) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (CommonUtils.isAnyBlank(materialCode, cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
		d.setCabinCode(cabinCode);
		d.setCabinType(cabinCode.startsWith("WH") ? "1" : "2");
		d.setAmt(instockAmt.doubleValue());
		d.setMaterialCode(materialCode);
		d.setOpType("correct");
		d.setStatus("0");
		d.setGmtCreated(new Date());
		d.setOperator(user.getUserCode());
		this.stockHistoryMapper.insert(d);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/batchInstock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object batchInstock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String dataJson = CommonUtils.get(request, "dataJson");
		JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
		// 保存history
		List<WmsMaterialStockHistoryDO> hisList = new ArrayList<>();
		for (int i = 0; i < dataArr.size(); i++) {
			JSONObject j = dataArr.getJSONObject(i);
			WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
			d.setCabinCode(j.getString("cabinCode"));
			d.setCabinType(j.getString("cabinType"));
			d.setAmt(j.getDouble("amt"));
			d.setUnitPrice(j.getDouble("unitPrice"));
			d.setTotalPrice(d.getAmt() * d.getUnitPrice());
			d.setMaterialCode(j.getString("materialCode"));
			d.setOpType("in_stock");
			d.setStatus("0");
			d.setKeepDays(j.getString("storageLifeNum") + j.getString("storageLifeUnit"));
			d.setProductDate(CommonUtils.parseDate(j.getString("productDate")));
			d.setGmtCreated(new Date());
			d.setOperator(user.getUserCode());
			hisList.add(d);
		}
		for (WmsMaterialStockHistoryDO dd : hisList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(dd);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/correctStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object correctStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if (id == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		String materialCode = request.getParameter("materialCode");
		String realStockStr = request.getParameter("realStock");
		BigDecimal realStock = CommonUtils.parseBigDecimal(realStockStr);
		if (realStock == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setId(id);
		t.setMaterialCode(materialCode);
		WmsMaterialStockDO stock = stockMapper.selectOne(t);
		if (stock == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		// 记录history
		WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
		d.setCabinCode(stock.getCabinCode());
		d.setCabinType(stock.getCabinType());
		d.setAmt(realStock.doubleValue());// 修正库存量
		d.setPreStock(stock.getCurrStock());// 修正前的库存
		d.setPostStock(realStock.doubleValue());// 修正后的库存
		d.setMaterialCode(stock.getMaterialCode());
		d.setMaterialName(stock.getMaterialName());
		d.setOpType("correct");
		d.setStatus("0");
		d.setGmtCreated(new Date());
		d.setOperator(user.getUserCode());
		this.stockHistoryMapper.insert(d);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/diaobo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object diaobo() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");// 调拨材料
		String fromCabCode = CommonUtils.get(request, "fromCabCode");// 拨出门店
		String toCabCode = CommonUtils.get(request, "toCabCode");// 拨入门店
		Double diaoboAmt = CommonUtils.getDbl(request, "diaoboAmt", null);
		if (diaoboAmt == null || CommonUtils.isAnyBlank(fromCabCode, toCabCode, materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 调出
		WmsMaterialStockHistoryDO d = new WmsMaterialStockHistoryDO();
		d.setCabinCode(fromCabCode);
		d.setCabinType(fromCabCode.startsWith("WH") ? "1" : "2");
		d.setAmt(-1 * diaoboAmt.doubleValue());
		d.setMaterialCode(materialCode);
		d.setOpType("bochu");
		d.setStatus("0");
		d.setGmtCreated(new Date());
		d.setOperator(user.getUserCode());
		d.setRemark("调出到" + toCabCode);
		d.setRelateCode(toCabCode);
		this.stockHistoryMapper.insert(d);
		// 调入
		d = new WmsMaterialStockHistoryDO();
		d.setCabinCode(toCabCode);
		d.setCabinType(toCabCode.startsWith("WH") ? "1" : "2");
		d.setAmt(diaoboAmt.doubleValue());
		d.setMaterialCode(materialCode);
		d.setOpType("boru");
		d.setStatus("0");
		d.setGmtCreated(new Date());
		d.setOperator(user.getUserCode());
		d.setRemark("从" + fromCabCode + "拨入");
		d.setRelateCode(fromCabCode);
		this.stockHistoryMapper.insert(d);

		return ResultBaseBuilder.succ().msg("调拨成功").rb(request);
	}
}
