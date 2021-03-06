package com.xjh.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.dao.mapper.WmsOrdersMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.eventbus.BusCruise;
import com.xjh.eventbus.evthandles.MaterialChange;
import com.xjh.service.CabinService;
import com.xjh.service.Mappers;
import com.xjh.service.MaterialService;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.MaterialStockService;
import com.xjh.service.SequenceService;
import com.xjh.service.SupplierService;
import com.xjh.service.TkMappers;
import com.xjh.service.UnitGroupService;
import com.xjh.support.excel.CfWorkbook;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;
import com.xjh.valueobject.CabinVo;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/busi")
@Slf4j
public class BusinessController {
	@Resource
	HttpServletRequest request;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;
	@Resource
	TkWmsMaterialSupplierMapper tkWmsMaterialSupplierMapper;
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
	@Resource
	MaterialStockService materialStockService;
	@Resource
	WmsMaterialStockMapper wmsMaterialStockMapper;
	@Resource
	WmsOrdersMapper wmsOrdersMapper;
	@Resource
	SupplierService supplierService;

	@RequestMapping(value = "/refreshMaterialSearchKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object refreshMaterialSearchKey() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		TkMappers.inst().getMaterialMapper().selectAll()//
				.forEach((m) -> {
					String searchKey = CommonUtils.genSearchKey(m.getMaterialName(), null);
					m.setSearchKey(searchKey + "," + m.getMaterialName());
					TkMappers.inst().getMaterialMapper().updateByPrimaryKeySelective(m);
				});
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/deleteMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String materialCode = CommonUtils.get(request, "materialCode");

		if (StringUtils.isBlank(materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		assert StringUtils.isNotBlank(materialCode);

		WmsMaterialDO cond = new WmsMaterialDO();
		cond.setMaterialCode(materialCode);
		cond = TkMappers.inst().getMaterialMapper().selectOne(cond);
		cond.setStatus(2);//无效状态
		cond.setIsDeleted("Y");
		TkMappers.inst().getMaterialMapper().updateByPrimaryKeySelective(cond);
		BusCruise.post(new MaterialChange(cond), true);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/addMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addMaterials() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			String storageLifeNum = CommonUtils.get(request, "storageLifeNum");
			String storageLifeUnit = CommonUtils.get(request, "storageLifeUnit");
			String specUnit = CommonUtils.get(request, "specUnit");
			String specQty = CommonUtils.get(request, "specQty");
			String specDetail = CommonUtils.get(request, "specDetail");
			String category = CommonUtils.get(request, "category");
			Double warningCoeffient1 = CommonUtils.getDbl(request, "warningCoeffient1", 3D);
			Double warningCoeffient2 = CommonUtils.getDbl(request, "warningCoeffient2", 5D);
			JSONArray specList = CommonUtils.parseJSONArray(specDetail);
			JSONArray suppliers = CommonUtils.parseJSONArray(request.getParameter("suppliers"));
			if (specList.size() == 0) {
				return ResultBaseBuilder.fails("至少需要一个采购单元").rb(request);
			}
			WmsUnitGroupDO categoryDO = UnitGroupService.getUnitGroup("material_category", category);
			WmsMaterialDO material = new WmsMaterialDO();
			material.setId(CommonUtils.getLong(request, "id"));
			material.setMaterialCode(CommonUtils.get(request, "materialCode"));
			material.setMaterialName(CommonUtils.get(request, "materialName"));
			String searchKey = CommonUtils.get(request, "searchKey");
			material.setSpecUnit(specUnit);
			material.setCategory(category);
			material.setWarningCoeffient1(warningCoeffient1);
			material.setWarningCoeffient2(warningCoeffient2);
			if (categoryDO != null) {
				material.setOrderBy(categoryDO.getOrderBy() == null ? 0 : categoryDO.getOrderBy());
			}
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
			material.setIsDeleted("N");
			if (StringUtils.isBlank(material.getMaterialName())) {
				return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
			}
			//供应商信息
			List<WmsMaterialSupplierDO> supplierDOList = new ArrayList<>();
			for (int i = 0; i < suppliers.size(); i++) {
				JSONObject json = suppliers.getJSONObject(i);
				WmsMaterialSupplierDO d = new WmsMaterialSupplierDO();
				d.setSupplierCode(json.getString("supplierCode"));
				d.setSupplierName(json.getString("supplierName"));
				d.setIsDeleted("N");
				supplierDOList.add(d);
				if (StringUtils.isAnyBlank(d.getSupplierCode(), d.getSupplierName())) {
					return ResultBaseBuilder.fails("供应商编码或名称必输").rb(request);
				}
			}
			//保存采购规格信息
			//每次都重新生成一个分组,保证数据库只增不删
			//这个区分规格是新增的还是删除，都重新生成
			String specGroup = CommonUtils.uuid();
			material.setSpecGroup(specGroup);
			List<WmsMaterialSpecDetailDO> specDetailList = new ArrayList<>();
			for (int i = 0; i < specList.size(); i++) {
				WmsMaterialSpecDetailDO sd = new WmsMaterialSpecDetailDO();
				JSONObject json = specList.getJSONObject(i);
				sd.setSpecGroup(specGroup);
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
				if (utilizationRatio <= 0) {
					return ResultBaseBuilder.fails(sd.getSpecName() + "利用率值不合法,必须大于0").rb(request);
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
				Double basePrice = CommonUtils.parseDouble(json.getString("basePrice"), 0D);
				sd.setBasePrice(basePrice);
				specDetailList.add(sd);
			}
			//下面开始保存数据
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
					o.setMaterialCode(material.getMaterialCode());
					o.setMaterialName(material.getMaterialName());
				}
			});
			supplierDOList.forEach((o) -> {
				o.setMaterialCode(material.getMaterialCode());
				o.setMaterialName(material.getMaterialName());
			});
			Assert.state(StringUtils.isNotBlank(material.getMaterialCode()), "原料CODE为空");
			WmsMaterialSpecDetailDO deleteCond = new WmsMaterialSpecDetailDO();
			deleteCond.setMaterialCode(material.getMaterialCode());
			detailMapper.delete(deleteCond);
			for (WmsMaterialSpecDetailDO sd : specDetailList) {
				detailMapper.insert(sd);
			}
			//删除原料供应商
			WmsMaterialSupplierDO materialSupplierDeleteCond = new WmsMaterialSupplierDO();
			materialSupplierDeleteCond.setMaterialCode(material.getMaterialCode());
			tkWmsMaterialSupplierMapper.delete(materialSupplierDeleteCond);
			//保存新的供应商
			supplierDOList.forEach((o) -> {
				tkWmsMaterialSupplierMapper.insert(o);
			});
			materialStockService.initStock(material.getMaterialCode());
			BusCruise.post(new MaterialChange(material), true);
			return ResultBaseBuilder.succ().data(material).rb(request);
		} catch (Exception e) {
			log.error("", e);
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/queryMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		String materialCode = CommonUtils.get(request, "materialCode");
		String searchKey = CommonUtils.get(request, "searchKey");
		String category = CommonUtils.get(request, "category");
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
		cond.setCategory(category);
		cond.setIsDeleted("N");
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
	public Object queryMaterialsStock(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			int pageSize = CommonUtils.getPageSize(request);
			int pageNo = CommonUtils.getPageNo(request);
			String download = CommonUtils.get(request, "download");
			Long id = CommonUtils.parseLong(request.getParameter("id"), null);
			String materialCode = CommonUtils.get(request, "materialCode");
			String cabCode = CommonUtils.get(request, "cabinCode");
			String cabType = CommonUtils.get(request, "cabinType");
			String searchKey = CommonUtils.get(request, "searchKey");
			String category = CommonUtils.get(request, "category");
			WmsMaterialStockDO example = new WmsMaterialStockDO();
			example.setId(id);
			example.setMaterialCode(materialCode);
			example.setCabinCode(cabCode);
			example.setCabinType(cabType);
			example.setPageSize(pageSize);
			example.setCategory(category);
			example.setIsDeleted("N");
			example.setPageNo(pageNo);
			example.setSearchKey(searchKey);
			if (!"1".equals(user.getIsSu())) {
				List<String> mycabins = new ArrayList<>();
				mycabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
				mycabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
				example.setMycabins(mycabins);
			}
			PageResult<WmsMaterialStockDO> page = new PageResult<>();
			int totalRows = this.wmsMaterialStockMapper.count(example);
			if ("excel".equals(download) && totalRows > 3000) {
				return ResultBaseBuilder.fails("导出记录不能超过3000").rb(request);
			}
			if ("excel".equals(download)) {
				example.setPageSize(3000);
			}
			List<WmsMaterialStockDO> tempList = this.wmsMaterialStockMapper.query(example);

			if (tempList != null) {
				for (WmsMaterialStockDO stock : tempList) {
					String sk = CommonUtils.genSearchKey(stock.getMaterialName(), "");
					sk += "," + CommonUtils.genSearchKey(stock.getCabinName(), "");
					stock.setSearchKey(sk);
					//需求：根据食材库存，折算为第一个采购单位：currSpecAmtAndUnit
					WmsMaterialSpecDetailDO spec = materialSpecService.queryFirstSpecDetail(stock.getMaterialCode());
					if (spec != null && spec.getTransRate() != null //
							&& spec.getTransRate().doubleValue() > 0.001 //
							&& spec.getUtilizationRatio() > 0//
					) {
						//公式:食材库存/转化率/利用率
						double specAmt = new BigDecimal(stock.getCurrStock())
								.divide(spec.getTransRate(), 2, RoundingMode.CEILING)//
								.divide(new BigDecimal(spec.getUtilizationRatio()), 2, RoundingMode.CEILING) //
								.multiply(new BigDecimal(100))//
								.setScale(2).doubleValue();
						BigDecimal dailyAvg = new BigDecimal(stock.getWarningStock())
								.divide(spec.getTransRate(), 2, RoundingMode.CEILING)//
								.divide(new BigDecimal(spec.getUtilizationRatio()), 2, RoundingMode.CEILING) //
								.multiply(new BigDecimal(100))//
								.setScale(2);
						;
						BigDecimal weekAvg = new BigDecimal(stock.getWarningStock() * 7)
								.divide(spec.getTransRate(), 2, RoundingMode.CEILING)//
								.divide(new BigDecimal(spec.getUtilizationRatio()), 2, RoundingMode.CEILING) //
								.multiply(new BigDecimal(100))//
								.setScale(2);
						;
						BigDecimal monthAvg = new BigDecimal(stock.getWarningStock() * 30)
								.divide(spec.getTransRate(), 2, RoundingMode.CEILING)//
								.divide(new BigDecimal(spec.getUtilizationRatio()), 2, RoundingMode.CEILING) //
								.multiply(new BigDecimal(100))//
								.setScale(2);
						;
						stock.setCurrSpecAmtAndUnit(specAmt + spec.getSpecUnit());
						stock.setFirstSpec(spec);
						stock.setFirstSpecUnit(spec.getSpecUnit());
						stock.setDailySpecAvg(dailyAvg.setScale(0, RoundingMode.CEILING).doubleValue());
						stock.setWeekSpecAvg(weekAvg.setScale(0, RoundingMode.CEILING).doubleValue());
						stock.setMonthSpecAvg(monthAvg.setScale(0, RoundingMode.CEILING).doubleValue());
					}
					//currStockAmtAndUnit
					stock.setCurrStockAmtAndUnit(stock.getCurrStock() + stock.getStockUnit());
				}
			}
			if ("excel".equals(download)) {
				String filename = CommonUtils.get(request, "filename");
				CfWorkbook wb = new CfWorkbook();
				CfSheet sheet = wb.newSheet("data");
				for (WmsMaterialStockDO dd : tempList) {
					WmsMaterialSpecDetailDO spec = dd.getFirstSpec();
					if (spec != null) {

						CfRow row = sheet.newRow();
						row.appendEx("原料名称", dd.getMaterialName(), //
								"仓库名称", dd.getCabinName(), //
								"日均消耗", dd.getWarningStock() + dd.getStockUnit(), //
								"采购单位", spec.getSpecUnit(), //
								"采购规格", spec.getTransRate().intValue() + spec.getStockUnit() + "/" + spec.getSpecUnit(), //
								"利用率", spec.getUtilizationRatio(), //
								"日均", dd.getDailySpecAvg(), //
								"周均", dd.getWeekSpecAvg(), //
								"月均", dd.getMonthSpecAvg());
					}
				}
				if (filename == null || filename.length() == 0) {
					filename = "MaterialStock";
				}
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + filename + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
				wb.toHSSFWorkbook().write(response.getOutputStream());
				response.getOutputStream().close();
				return null;
			} else {
				page.setValues(tempList);
				page.setTotalRows(totalRows);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				return ResultBaseBuilder.succ().data(page).rb(request);
			}
		} catch (IOException e) {
			log.error("", e);
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/queryStockByMaterialCodes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryStockByMaterialCodes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String materialCodes = CommonUtils.get(request, "materialCodes");
		List<String> materialList = CommonUtils.splitAsList(materialCodes, ",");
		if (materialList.size() == 0) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		Example example = new Example(WmsMaterialStockDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("materialCode", materialList);
		cri.andEqualTo("isDeleted", "N");
		List<WmsMaterialStockDO> list = TkMappers.inst().getMaterialStockMapper().selectByExample(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStockById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockById() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
	public Object queryMaterialSupplerByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String materialCode = CommonUtils.get(request, "materialCode");
		String supplierCode = CommonUtils.get(request, "supplierCode");
		if (CommonUtils.isAllBlank(materialCode, supplierCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
		ms.setSupplierCode(supplierCode);
		ms.setMaterialCode(materialCode);
		ms.setIsDeleted("N");
		List<WmsMaterialSupplierDO> list = TkMappers.inst().getMaterialSupplierMapper().select(ms);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/querySuppliersByMaterialCodes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object querySuppliersByMaterialCodes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String materialCodes = CommonUtils.get(request, "materialCodes");
		List<String> materialCodeList = CommonUtils.splitAsList(materialCodes, ",");
		if (materialCodeList.size() == 0) {
			return ResultBaseBuilder.succ().data(new ArrayList<>()).rb(request);
		}
		Example example = new Example(WmsMaterialSupplierDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("materialCode", materialCodeList);
		cri.andEqualTo("isDeleted", "N");
		List<WmsMaterialSupplierDO> list = TkMappers.inst().getMaterialSupplierMapper().selectByExample(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/getCabinByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getCabinByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		WmsMaterialSupplierDO ms = new WmsMaterialSupplierDO();
		ms.setPageSize(3000);
		ms.setIsDeleted("N");
		List<WmsMaterialSupplierDO> list = Mappers.inst().getWmsMaterialSupplierMapper().query(ms);
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String storeCode = CommonUtils.get(request, "storeCode");
		String saleDateStart = CommonUtils.get(request, "saleDateStart");
		String saleDateEnd = CommonUtils.get(request, "saleDateEnd");
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String searchKey = CommonUtils.get(request, "searchKey");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		WmsOrdersDO cond = new WmsOrdersDO();
		cond.setStoreCode(storeCode);
		cond.setRecipesCode(recipesCode);
		cond.setPageNo(pageNo);
		cond.setPageSize(pageSize);
		cond.setIsDeleted("N");
		cond.setSearchKey(searchKey);
		cond.setSaleDateStart(CommonUtils.parseDate(saleDateStart));
		cond.setSaleDateEnd(CommonUtils.parseDate(saleDateEnd));
		if (!"1".equals(user.getIsSu())) {
			List<String> mystores = new ArrayList<>();
			mystores.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			cond.setMystores(mystores);
		}
		List<WmsOrdersDO> list = wmsOrdersMapper.query(cond);
		int totalRows = wmsOrdersMapper.count(cond);
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
		WmsMaterialDO material = this.materialService.queryMaterialByCode(materialCode);
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
				stockData.add(0D);
				saleData.add(0D);
			} else {
				stockData.add(dd.getCurrentStockAmt() == null ? 0D : dd.getCurrentStockAmt());
				saleData.add(dd.getConsumeAmt2() == null ? 0D : dd.getConsumeAmt2());
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
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
