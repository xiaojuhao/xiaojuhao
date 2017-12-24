package com.xjh.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialSplitDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockDailyDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.dataobject.WmsUnitGroupDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.mapper.WmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.service.CabinService;
import com.xjh.service.MaterialService;
import com.xjh.service.MaterialSpecService;
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
	WmsMaterialMapper wmsMaterialMapper;
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
	@Resource
	MaterialSpecService materialSpecService;
	@Resource
	TkWmsMaterialSpecDetailMapper detailMapper;

	@RequestMapping(value = "/deleteMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");

		if (StringUtils.isBlank(materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		assert StringUtils.isNotBlank(materialCode);

		WmsMaterialDO cond = new WmsMaterialDO();
		cond.setMaterialCode(materialCode);
		cond = TkMappers.inst().getMaterialMapper().selectOne(cond);
		cond.setStatus(2);//无效状态
		TkMappers.inst().getMaterialMapper().updateByPrimaryKeySelective(cond);
		return ResultBaseBuilder.succ().rb(request);
	}

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
		String specDetail = CommonUtils.get(request, "specDetail");
		JSONArray specList = CommonUtils.parseJSONArray(specDetail);
		if (specList.size() == 0) {
			return ResultBaseBuilder.fails("至少需要一个采购单元").rb(request);
		}
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
		if (material.getUtilizationRatio() == null) {
			material.setUtilizationRatio(100);
		}
		material.setStatus(1);

		if (StringUtils.isBlank(material.getMaterialName())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		List<WmsMaterialSpecDetailDO> specDetailList = new ArrayList<>();
		for (int i = 0; i < specList.size(); i++) {
			WmsMaterialSpecDetailDO sd = new WmsMaterialSpecDetailDO();
			JSONObject json = specList.getJSONObject(i);
			sd.setMaterialCode(material.getMaterialCode());
			sd.setMaterialName(material.getMaterialName());
			sd.setIsDefault("N");
			sd.setIsDeleted("N");
			sd.setStockUnit(material.getStockUnit());
			sd.setSpecUnit(json.getString("specUnit"));
			sd.setSpecCode(json.getString("specCode"));
			sd.setSpecName(json.getString("specName"));
			sd.setWeight(json.getString("weight"));
			sd.setHomeplace(json.getString("homeplace"));
			sd.setBrandName(json.getString("brandName"));
			Integer utilizationRatio = CommonUtils.parseInt(json.getString("utilizationRatio"), -1);
			if (utilizationRatio <= 0 || utilizationRatio > 100) {
				return ResultBaseBuilder.fails(sd.getSpecName() + "利用率值不合法,必须在[0,100]内").rb(request);
			}
			sd.setUtilizationRatio(utilizationRatio);
			if (StringUtils.isBlank(sd.getSpecName())) {
				return ResultBaseBuilder.fails("规格名称不能为空").rb(request);
			}
			BigDecimal transRate = CommonUtils.parseBigDecimal(json.getString("transRate"));
			if (transRate == null) {
				return ResultBaseBuilder.fails(sd.getSpecName() + "没有填写正确的入库数量").rb(request);
			}
			sd.setTransRate(transRate);

			specDetailList.add(sd);
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
		//补充specCode
		specDetailList.forEach((o) -> {
			if (StringUtils.isBlank(o.getSpecCode())) {
				o.setSpecCode(materialSpecService.nextSpecCode());
			}
		});
		assert StringUtils.isNotBlank(material.getMaterialCode());
		WmsMaterialSpecDetailDO deleteCond = new WmsMaterialSpecDetailDO();
		deleteCond.setMaterialCode(material.getMaterialCode());
		detailMapper.delete(deleteCond);
		for (WmsMaterialSpecDetailDO sd : specDetailList) {
			detailMapper.insert(sd);
		}
		return ResultBaseBuilder.succ().data(material).rb(request);
	}

	@RequestMapping(value = "/queryMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		String materialCode = CommonUtils.get(request, "materialCode");
		String searchKey = CommonUtils.get(request, "searchKey");
		Long id = CommonUtils.getLong(request, "id");
		Integer status = CommonUtils.getInt(request, "status");
		PageResult<WmsMaterialDO> page = new PageResult<WmsMaterialDO>();
		WmsMaterialDO cond = new WmsMaterialDO();
		cond.setId(id);
		cond.setMaterialCode(materialCode);
		cond.setSearchKey(searchKey);
		cond.setStatus(status);
		cond.setPageNo(pageNo);
		cond.setPageSize(pageSize);
		int totalRows = this.wmsMaterialMapper.count(cond);
		List<WmsMaterialDO> list = wmsMaterialMapper.query(cond);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMaterialSplitByMaterialCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialSplitByMaterialCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
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
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
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
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
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
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

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
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

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
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

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

	@RequestMapping(value = "/queryWmsOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryWmsOrder() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String storeCode = CommonUtils.get(request, "storeCode");
		String saleDate = CommonUtils.get(request, "saleDate");
		String recipesCode = CommonUtils.get(request, "recipesCode");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		WmsOrdersDO cond = new WmsOrdersDO();
		cond.setStoreCode(storeCode);
		cond.setRecipesCode(recipesCode);
		cond.setSaleDate(CommonUtils.parseDate(saleDate));
		cond.setPageNo(pageNo);
		cond.setPageSize(pageSize);
		cond.setIsDeleted("N");
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("sale_date desc, id desc");
		List<WmsOrdersDO> list = TkMappers.inst().getOrdersMapper().select(cond);
		int totalRows = TkMappers.inst().getOrdersMapper().selectCount(cond);
		PageResult<WmsOrdersDO> page = new PageResult<>();
		page.setValues(list);
		page.setTotalRows(totalRows);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryRecentDaysTendency", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecentDaysTendency() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = CommonUtils.todayDate();
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String materialCode = CommonUtils.get(request, "materialCode");
		Integer days = CommonUtils.getInt(request, "days");
		if (days == null) {
			days = 7;
		}
		List<String> daysData = new ArrayList<>();
		List<Double> stockData = new ArrayList<>();
		List<Double> saleData = new ArrayList<>();
		JSONObject ret = new JSONObject();

		if (CommonUtils.isAnyBlank(cabinCode, materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}

		CabinVo cabin = this.cabinService.getCabinByCode(cabinCode);
		WmsMaterialDO material = this.materialService.getMaterialByCode(materialCode);
		if (cabin == null || material == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		WmsMaterialStockDailyDO cond = new WmsMaterialStockDailyDO();
		cond.setMaterialCode(materialCode);
		cond.setCabinCode(cabinCode);
		Example example = new Example(WmsMaterialStockDailyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo(cond);
		Date time = CommonUtils.futureDays(today, -1 * days);
		cri.andGreaterThanOrEqualTo("statDate", time);
		ret.put("daysData", daysData);
		ret.put("stockData", stockData);
		ret.put("saleData", saleData);
		ret.put("cabinName", cabin.getName());
		ret.put("stockUnit", material.getStockUnit());
		ret.put("materialName", material.getMaterialName());
		Map<String, WmsMaterialStockDailyDO> dataMap = new HashMap<>();
		List<WmsMaterialStockDailyDO> list = TkMappers.inst().getStockDailyMapper().selectByExample(example);
		list.forEach((it) -> dataMap.put(sdf.format(it.getStatDate()), it));
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		for (int i = 0; i <= days; i++) {
			String day = CommonUtils.formatDate(c.getTime(), "yyyy-MM-dd");
			daysData.add(day);
			WmsMaterialStockDailyDO dd = dataMap.get(day);
			if (dd == null) {
				stockData.add(new Double(CommonUtils.randomNumber(20, 22)));
				saleData.add(new Double(CommonUtils.randomNumber(0, 10)));
			} else {
				stockData.add(dd.getInitAmt() - dd.getConsumeAmt() - dd.getLossAmt());
				saleData.add(dd.getConsumeAmt());
			}
			c.add(Calendar.DATE, 1);//next day
		}
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}

	@RequestMapping(value = "/queryOrderMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryOrderMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long orderId = CommonUtils.getLong(request, "orderId");
		String storeCode = CommonUtils.get(request, "storeCode");
		String recipesCode = CommonUtils.get(request, "recipesCode");
		if (orderId == null || CommonUtils.isAnyBlank(storeCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsOrdersMaterialDO cond = new WmsOrdersMaterialDO();
		cond.setOrderId(orderId);
		cond.setStoreCode(storeCode);
		cond.setRecipesCode(recipesCode);
		List<WmsOrdersMaterialDO> list = TkMappers.inst().getOrdersMaterialMapper().select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryUnitByGroup", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUnitByGroup() {
		String groupCode = CommonUtils.get(request, "groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUnitGroupDO cond = new WmsUnitGroupDO();
		cond.setGroupCode(groupCode);
		PageHelper.orderBy("id");
		List<WmsUnitGroupDO> list = TkMappers.inst().getUnitGroupMapper().select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
