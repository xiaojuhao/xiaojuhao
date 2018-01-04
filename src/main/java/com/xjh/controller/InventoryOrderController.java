package com.xjh.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUploadFilesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.service.CabinService;
import com.xjh.service.DatabaseService;
import com.xjh.service.Mappers;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.StockHistoryScheduleTask;
import com.xjh.service.TkMappers;
import com.xjh.service.handlers.PostCheckStockJobHandler;
import com.xjh.valueobject.CabinVo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/inventoryApply")
public class InventoryOrderController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;
	@Resource
	DatabaseService database;
	@Resource
	MaterialSpecService materialSpecService;

	@RequestMapping(value = "/queryInventoryApply", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryInventoryApply() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		String paidStatus = CommonUtils.get(request, "paidStatus");
		String applyTypes = CommonUtils.get(request, "applyTypes");
		String applyNum = CommonUtils.get(request, "applyNum");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		List<String> types = CommonUtils.splitAsList(applyTypes, ",");
		Example example = new Example(WmsInventoryApplyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		if (types.size() > 0) {
			cri.andIn("applyType", types);
		}
		cri.andEqualTo("status", status);
		cri.andEqualTo("paidStatus", paidStatus);
		cri.andEqualTo("applyNum", applyNum);
		cri.andIn("cabinCode", this.cabinService.getMyCabinCodes(user));
		int totalRows = TkMappers.inst().getPurchaseOrderMapper().selectCountByExample(example);
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsInventoryApplyDO> list = TkMappers.inst().getPurchaseOrderMapper().selectByExample(example);

		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyPurchaseOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyPurchaseOrder() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setStatus(status);
		cond.setApplyType("purchase");
		cond.setProposer(user.getUserCode());
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = TkMappers.inst().getPurchaseOrderMapper().select(cond);
		int totalRows = TkMappers.inst().getPurchaseOrderMapper().selectCount(cond);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyAllocate", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyAllocate() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		String status = CommonUtils.get(request, "status");
		Example example = new Example(WmsInventoryApplyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("status", status);
		cri.andIn("applyType", Arrays.asList("allocation"));
		cri.andEqualTo("proposer", user.getUserCode());
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = TkMappers.inst().getPurchaseOrderMapper().selectByExample(example);
		int totalRows = TkMappers.inst().getPurchaseOrderMapper().selectCountByExample(example);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyLossApply", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyLossApply() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setStatus(status);
		cond.setApplyType("claim_loss");
		cond.setProposer(user.getUserCode());
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = TkMappers.inst().getPurchaseOrderMapper().select(cond);
		int totalRows = TkMappers.inst().getPurchaseOrderMapper().selectCount(cond);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyLossApplyDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyLossApplyDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyType("claim_loss");
		//超管可以查看所有的记录
		if (!"1".equals(user.getIsSu()))
			cond.setCreator(user.getUserCode());
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsInventoryApplyDetailDO> list = TkMappers.inst().getPurchaseOrderDetailMapper().select(cond);
		List<JSONObject> ret = new ArrayList<>();
		for (WmsInventoryApplyDetailDO dd : list) {
			List<String> images = new ArrayList<>();
			JSONObject json = new JSONObject();
			ret.add(json);
			json.put("cabinCode", dd.getCabinCode());
			json.put("cabinName", dd.getCabinName());
			json.put("materialCode", dd.getMaterialCode());
			json.put("materialName", dd.getMaterialName());
			json.put("stockAmt", dd.getStockAmt());
			json.put("stockUnit", dd.getStockUnit());
			json.put("creator", dd.getCreator());
			json.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dd.getGmtCreated()));
			json.put("images", images);
			if (StringUtils.isNotBlank(dd.getImgBusiNo())) {
				WmsUploadFilesDO img = new WmsUploadFilesDO();
				img.setBusiNo(dd.getImgBusiNo());
				List<WmsUploadFilesDO> imgs = TkMappers.inst().getUploadFilesMapper().select(img);
				for (WmsUploadFilesDO ii : imgs) {
					images.add(ii.getFileName());
				}
			}
		}
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}

	@RequestMapping(value = "/queryPurchaseOrderDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryPurchaseOrderDetail() {
		String applyNum = CommonUtils.get(request, "applyNum");
		if (StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		List<WmsInventoryApplyDetailDO> list = TkMappers.inst().getPurchaseOrderDetailMapper().select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/commitPurchaseOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object commitPurchaseOrder() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			String cabinCode = CommonUtils.get(request, "cabinCode");
			CabinVo cabinVo = cabinService.getCabinByCode(cabinCode);
			String status = CommonUtils.get(request, "status");
			if (StringUtils.isBlank(status)) {
				status = "4";// 配送中
			}
			if (cabinVo == null) {
				return ResultBaseBuilder.fails(ResultCode.info_missing).msg("货站信息不存在").rb(request);
			}
			String remark = CommonUtils.get(request, "remark");
			WmsInventoryApplyDO order = new WmsInventoryApplyDO();
			List<WmsInventoryApplyDetailDO> details = new ArrayList<>();
			// 保存采购单
			String supplierCode = null;
			String supplierName = null;
			order.setApplyNum(CommonUtils.uuid());
			order.setCabinCode(cabinCode);
			order.setCabinName(cabinVo.getName());
			order.setSerialNo(CommonUtils.stringOfNow());
			order.setApplyType("purchase");
			order.setProposer(user.getUserCode());
			order.setGmtCreated(new Date());
			order.setGmtModified(new Date());
			order.setCreator(user.getUserCode());
			order.setModifier(user.getUserCode());
			order.setStatus(status);
			order.setRemark(remark);
			Double sumPrice = 0D;
			//
			String dataJson = CommonUtils.get(request, "dataJson");
			JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
				JSONObject j = dataArr.getJSONObject(i);
				Double storageLifeNum = j.getDouble("storageLifeNum");
				String storageLifeUnit = j.getString("storageLifeUnit");
				WmsInventoryApplyDetailDO d = new WmsInventoryApplyDetailDO();
				d.setApplyType("purchase");
				d.setCabinCode(order.getCabinCode());
				d.setCabinName(order.getCabinName());
				d.setApplyNum(order.getApplyNum());
				d.setApplyType(order.getApplyType());
				d.setMaterialCode(j.getString("materialCode"));
				d.setMaterialName(j.getString("materialName"));
				d.setSupplierCode(j.getString("supplierCode"));
				d.setSupplierName(j.getString("supplierName"));
				d.setSpecCode(j.getString("specCode"));
				if (supplierCode == null) {
					supplierCode = d.getSupplierCode();
					supplierName = d.getSupplierName();
				} else if (!StringUtils.equals(supplierCode, d.getSupplierCode())) {
					return ResultBaseBuilder.fails("目前采购单仅支持录入【一个】供应商").rb(request);
				}
				if (StringUtils.isBlank(d.getSpecCode())) {
					return ResultBaseBuilder.fails(d.getMaterialName() + "没有填写规格信息").rb(request);
				}
				WmsMaterialSpecDetailDO spec = materialSpecService.querySpecDetailByCode(//
						d.getMaterialCode(), d.getSpecCode());
				if (spec == null) {
					return ResultBaseBuilder.fails("规格" + d.getSpecCode() + "不存在").rb(request);
				}
				d.setSpecAmt(j.getDouble("specAmt"));
				if (d.getSpecAmt() == null) {
					d.setSpecAmt(0D);
				}
				d.setSpecPrice(j.getDouble("specPrice"));
				if (d.getSpecPrice() == null) {
					d.setSpecPrice(0D);
				}
				double qty = j.getDouble("specQty");
				if ("无".equals(d.getSpecUnit())) {
					qty = 1;
				}
				d.setSpecUnit(spec.getSpecUnit());
				d.setStockAmt(d.getSpecAmt() * qty);
				d.setStockUnit(j.getString("stockUnit"));
				d.setRealStockAmt(d.getStockAmt());
				d.setTransRate(spec.getTransRate().doubleValue());
				d.setTotalPrice(d.getSpecAmt() * d.getSpecPrice());
				d.setProdDate(CommonUtils.parseDate(j.getString("prodDate")));
				d.setExpDate(CommonUtils.parseDate(j.getString("expDate")));
				if (d.getExpDate() == null) {
					switch (storageLifeUnit) {
					case "D":
						d.setExpDate(CommonUtils.futureDays(d.getProdDate(), storageLifeNum.intValue()));
						break;
					case "M":
						d.setExpDate(CommonUtils.futureMonth(d.getProdDate(), storageLifeNum.intValue()));
						break;
					default:
						d.setExpDate(CommonUtils.futureYear(d.getProdDate(), 10));
					}
				}
				d.setKeepTime(storageLifeNum + storageLifeUnit);
				d.setGmtCreated(new Date());
				d.setGmtModified(new Date());
				d.setCreator(user.getUserCode());
				d.setModifier(user.getUserCode());
				d.setUtilizationRatio(spec.getUtilizationRatio());
				d.setInStockAmt(0D);
				d.setStatus("1");
				sumPrice += d.getTotalPrice();
				details.add(d);
			}
			//收款信息
			order.setTotalPrice(sumPrice);
			order.setPayables(sumPrice);
			order.setPaidAmt(0D);
			order.setPaidStatus("0");
			//供应商
			order.setSupplierCode(supplierCode);
			order.setSupplierName(supplierName);
			// 插入数据库
			database.commitPurchaseOrder(order, details);
			StockHistoryScheduleTask.startTask();
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/confirmInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirmInventory() {
		String dataJson = CommonUtils.get(request, "dataJson");
		String applyNum = CommonUtils.get(request, "applyNum");
		if (StringUtils.isBlank(dataJson) || StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDO order = new WmsInventoryApplyDO();
		order.setApplyNum(applyNum);
		order = TkMappers.inst().getPurchaseOrderMapper().selectOne(order);
		if (order == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if (!"4".equals(order.getStatus())) {
			return ResultBaseBuilder.fails("采购单已处理").rb(request);
		}
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		List<WmsMaterialStockHistoryDO> historyInserts = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStock = j.getDouble("realStockAmt");

			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = TkMappers.inst().getPurchaseOrderDetailMapper().selectOne(detail);
			if (detail == null) {
				continue;
			}
			if (!StringUtils.equals("1", detail.getStatus())) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "状态为" + detail.getStatus() + "，不能处理")
						.rb(request);
			}
			if (realStock == null) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "未输入实际入库数量").rb(request);
			}
			detail.setInStockAmt(detail.getUtilizationRatio() * realStock / 100);
			double unitPrice = 0D;// 单价
			if (detail.getTotalPrice() != null && detail.getStockAmt() != null && detail.getStockAmt() > 0.1) {
				unitPrice = detail.getTotalPrice() / detail.getStockAmt();
			}
			// history
			WmsMaterialStockHistoryDO h = new WmsMaterialStockHistoryDO();
			h.setOpType("in_stock");
			h.setCabinCode(detail.getCabinCode());
			h.setCabinName(detail.getCabinName());
			h.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
			h.setMaterialCode(detail.getMaterialCode());
			h.setMaterialName(detail.getMaterialName());
			h.setKeepDays(detail.getKeepTime());
			h.setTotalPrice(detail.getTotalPrice());
			h.setUnitPrice(unitPrice);
			h.setProductDate(detail.getProdDate());
			h.setStockUnit(detail.getStockUnit());
			h.setAmt(detail.getInStockAmt());
			h.setOperator(detail.getModifier());
			h.setGmtCreated(new Date());
			h.setStatus("0");
			h.setRelateCode(detail.getApplyNum());
			h.setRemark("采购入库,供应商:" + detail.getSupplierName());
			historyInserts.add(h);

			if (Math.abs(detail.getStockAmt() - realStock) > 0.001) {
				WmsMaterialStockHistoryDO loss = new WmsMaterialStockHistoryDO();
				loss.setOpType("in_stock_loss");
				loss.setCabinCode(detail.getCabinCode());
				loss.setCabinName(detail.getCabinName());
				loss.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
				loss.setMaterialCode(detail.getMaterialCode());
				loss.setMaterialName(detail.getMaterialName());
				loss.setKeepDays(detail.getKeepTime());
				loss.setUnitPrice(unitPrice);
				loss.setProductDate(detail.getProdDate());
				loss.setStockUnit(detail.getStockUnit());
				loss.setAmt(realStock - detail.getStockAmt());
				loss.setOperator(detail.getModifier());
				loss.setGmtCreated(new Date());
				loss.setStatus("1"); // 入库损耗数据不需要更新库存，仅做记录
				loss.setRelateCode(detail.getApplyNum());
				loss.setRemark("采购入库损耗,供应商:" + detail.getSupplierName());
				historyInserts.add(loss);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("2");
			update.setRealStockAmt(realStock);
			detailUpdateList.add(update);
		}
		// 采购单状态修改

		//		order.setStatus("5");
		database.diaoboConfirm(order, detailUpdateList, historyInserts);
		StockHistoryScheduleTask.startTask();

		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		cond.setStatus("1");
		int noHandleRows = TkMappers.inst().getPurchaseOrderDetailMapper().selectCount(cond);
		if (noHandleRows == 0) {
			order.setStatus("5");
			TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKey(order);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/paidInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object paidInventory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String applyNum = CommonUtils.get(request, "applyNum");
		String paidWay = CommonUtils.get(request, "paidWay");
		Double paidAmt = CommonUtils.getDbl(request, "paidAmt", null);
		String paytoBank = CommonUtils.get(request, "paytoBank");
		String paytoAccount = CommonUtils.get(request, "paytoAccount");
		String paytoAccountName = CommonUtils.get(request, "paytoAccountName");
		if (CommonUtils.isAnyBlank(applyNum, paidWay, paytoAccount, paytoAccountName)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (paidAmt == null) {
			return ResultBaseBuilder.fails("支付金额不能为空").rb(request);
		}
		if (paidAmt <= 0) {
			return ResultBaseBuilder.fails("支付金额金额必须大于0").rb(request);
		}
		assert StringUtils.isNotBlank(applyNum);
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setApplyNum(applyNum);
		WmsInventoryApplyDO apply = TkMappers.inst().getPurchaseOrderMapper().selectOne(cond);
		if (apply == null) {
			return ResultBaseBuilder.fails("采购单不存在").rb(request);
		}
		if (!"0".equals(apply.getPaidStatus())) {
			return ResultBaseBuilder.fails("采购单已支付,请勿重复操作").rb(request);
		}
		//设置更新信息
		WmsInventoryApplyDO update = new WmsInventoryApplyDO();
		update.setId(apply.getId());
		update.setPaidStatus("1");
		update.setPaidAmt(paidAmt);
		update.setPaytoBank(paytoBank);
		update.setPaytoAccount(paytoAccount);
		update.setPaytoAccountName(paytoAccountName);
		update.setPaidWay(paidWay);
		update.setPaidOperator(user.getUserCode());
		update.setPaidTime(new Date());
		TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKeySelective(update);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/startCorrect", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object startCorrect() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = request.getParameter("cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		CabinVo vo = cabinService.getCabinByCode(cabinCode);
		if (vo == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if ("0".equals(vo.getStatus())) {
			return ResultBaseBuilder.fails("仓库状态无效").rb(request);
		}
		//		if ("2".equals(vo.getStatus())) {
		//			return ResultBaseBuilder.fails("仓库正在盘点").rb(request);
		//		}
		if (vo.getCode().startsWith("WH")) {
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(cabinCode);
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			WmsWarehouseDO update = new WmsWarehouseDO();
			update.setId(warehouse.getId());
			update.setStatus("2");
			TkMappers.inst().getWarehouseMapper().updateByPrimaryKeySelective(update);
		} else {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			WmsStoreDO update = new WmsStoreDO();
			update.setId(store.getId());
			update.setStatus("2");
			TkMappers.inst().getStoreMapper().updateByPrimaryKeySelective(update);
		}
		WmsMaterialStockDO stock = new WmsMaterialStockDO();
		stock.setCabinCode(cabinCode);
		stock.setModifier(user.getUserCode());
		Mappers.inst().getStockMapper().startCorrect(stock);

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/finishCorrect", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object finishCorrect() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = request.getParameter("cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		CabinVo vo = cabinService.getCabinByCode(cabinCode);
		if (vo == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if ("0".equals(vo.getStatus())) {
			return ResultBaseBuilder.fails("仓库状态无效").rb(request);
		}
		//		if ("1".equals(vo.getStatus())) {
		//			return ResultBaseBuilder.fails("仓库还未开始盘点").rb(request);
		//		}
		if (vo.getCode().startsWith("WH")) {
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(cabinCode);
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			WmsWarehouseDO update = new WmsWarehouseDO();
			update.setId(warehouse.getId());
			update.setStatus("1");
			TkMappers.inst().getWarehouseMapper().updateByPrimaryKeySelective(update);
		} else {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			WmsStoreDO update = new WmsStoreDO();
			update.setId(store.getId());
			update.setStatus("1");
			TkMappers.inst().getStoreMapper().updateByPrimaryKeySelective(update);
		}
		WmsMaterialStockDO cond = new WmsMaterialStockDO();
		cond.setCabinCode(cabinCode);
		List<WmsMaterialStockDO> list = Mappers.inst().getStockMapper().selectWaiting(cond);
		for (WmsMaterialStockDO stock : list) {
			// 记录history
			WmsMaterialStockHistoryDO posthis = new WmsMaterialStockHistoryDO();
			posthis.setCabinCode(stock.getCabinCode());
			posthis.setCabinName(stock.getCabinName());
			posthis.setCabinType(stock.getCabinType());
			posthis.setAmt(stock.getCurrStock());// 修正库存量
			posthis.setMaterialCode(stock.getMaterialCode());
			posthis.setMaterialName(stock.getMaterialName());
			posthis.setStockUnit(stock.getStockUnit());
			posthis.setOpType("correct");
			posthis.setStatus("0");
			posthis.setGmtCreated(new Date());
			posthis.setOperator(user.getUserCode());
			TkMappers.inst().getMaterialStockHistoryMapper().insert(posthis);
			stock.setStatus("1");
			TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(stock);
		}
		cond = new WmsMaterialStockDO();
		cond.setCabinCode(cabinCode);
		Mappers.inst().getStockMapper().finishCorrect(cond);
		StockHistoryScheduleTask.startTask();
		String today = CommonUtils.stringOfToday("yyyyMMdd");
		PostCheckStockJobHandler.addNewJob(today, cabinCode);//添加库存盘点报告任务
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/claimLoss", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object claimLoss() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String cabinName = CommonUtils.get(request, "cabinName");
		String materialCode = CommonUtils.get(request, "materialCode");
		String materialName = CommonUtils.get(request, "materialName");
		double lossAmt = CommonUtils.getDbl(request, "lossAmt", 0D);
		String stockUnit = CommonUtils.get(request, "stockUnit");
		String images = CommonUtils.get(request, "images");
		String remark = CommonUtils.get(request, "remark");
		String imgBusiNo = CommonUtils.get(request, "busiNo");
		WmsInventoryApplyDO inorder = new WmsInventoryApplyDO();
		inorder.setApplyNum(CommonUtils.uuid());
		inorder.setCabinCode(cabinCode);
		inorder.setCabinName(cabinName);
		inorder.setSerialNo(CommonUtils.stringOfNow());
		inorder.setApplyType("claim_loss");
		inorder.setProposer(user.getUserCode());
		inorder.setGmtCreated(new Date());
		inorder.setGmtModified(new Date());
		inorder.setCreator(user.getUserCode());
		inorder.setModifier(user.getUserCode());
		inorder.setStatus("5");
		inorder.setRemark(remark);
		inorder.setPaidStatus("1");
		inorder.setPaidAmt(0D);
		inorder.setPayables(0D);
		inorder.setTotalPrice(0D);

		WmsInventoryApplyDetailDO indetail = new WmsInventoryApplyDetailDO();
		indetail.setCabinCode(inorder.getCabinCode());
		indetail.setCabinName(inorder.getCabinName());
		indetail.setApplyNum(inorder.getApplyNum());
		indetail.setApplyType(inorder.getApplyType());
		indetail.setMaterialCode(materialCode);
		indetail.setMaterialName(materialName);
		indetail.setStockAmt(lossAmt);
		indetail.setStockUnit(stockUnit);
		indetail.setRealStockAmt(lossAmt);
		indetail.setGmtCreated(new Date());
		indetail.setGmtModified(new Date());
		indetail.setCreator(user.getUserCode());
		indetail.setModifier(user.getUserCode());
		indetail.setStatus("1");// 报损自动处理，状态直接置为1
		indetail.setImgBusiNo(imgBusiNo);
		indetail.setRemark(images);

		WmsMaterialStockHistoryDO h = new WmsMaterialStockHistoryDO();
		h.setOpType("claim_loss");
		h.setCabinCode(indetail.getCabinCode());
		h.setCabinName(indetail.getCabinName());
		h.setCabinType(indetail.getCabinCode().startsWith("WH") ? "1" : "2");
		h.setMaterialCode(indetail.getMaterialCode());
		h.setMaterialName(indetail.getMaterialName());
		h.setKeepDays(indetail.getKeepTime());
		h.setTotalPrice(indetail.getTotalPrice());
		h.setUnitPrice(indetail.getSpecPrice());
		h.setProductDate(indetail.getProdDate());
		h.setStockUnit(indetail.getStockUnit());
		h.setAmt(-1 * lossAmt);
		h.setOperator(indetail.getModifier());
		h.setGmtCreated(new Date());
		h.setStatus("0");
		h.setRemark("报损");
		h.setRelateCode(indetail.getApplyNum());

		database.claimLossInsert(inorder, indetail, h);
		StockHistoryScheduleTask.startTask();
		return ResultBaseBuilder.succ().msg("提交成功").rb(request);
	}

	@RequestMapping(value = "/correctStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object correctStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		String materialCode = request.getParameter("materialCode");
		if (id == null || StringUtils.isBlank(materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}

		String realStockStr = request.getParameter("realStock");
		BigDecimal realStock = CommonUtils.parseBigDecimal(realStockStr);
		if (realStock == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setId(id);
		t.setMaterialCode(materialCode);
		WmsMaterialStockDO stock = TkMappers.inst().getMaterialStockMapper().selectOne(t);
		if (stock == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		//
		WmsMaterialStockHistoryDO prehis = new WmsMaterialStockHistoryDO();
		prehis.setCabinCode(stock.getCabinCode());
		prehis.setCabinName(stock.getCabinName());
		prehis.setCabinType(stock.getCabinType());
		prehis.setAmt(realStock.doubleValue() - stock.getCurrStock());// 库存不一致的量
		prehis.setPreStock(stock.getCurrStock());
		prehis.setPostStock(realStock.doubleValue());
		prehis.setMaterialCode(stock.getMaterialCode());
		prehis.setMaterialName(stock.getMaterialName());
		prehis.setStockUnit(stock.getStockUnit());
		prehis.setOpType("correct_delta");//
		prehis.setStatus("1"); // 记录盘点库存和实际库存的差额，只起记录作用，所以状态直接置为1
		prehis.setRemark("库存盘点差额");
		prehis.setGmtCreated(new Date());
		prehis.setOperator(user.getUserCode());
		// 记录history
		WmsMaterialStockHistoryDO posthis = new WmsMaterialStockHistoryDO();
		posthis.setCabinCode(stock.getCabinCode());
		posthis.setCabinName(stock.getCabinName());
		posthis.setCabinType(stock.getCabinType());
		posthis.setAmt(realStock.doubleValue());// 修正库存量
		posthis.setMaterialCode(stock.getMaterialCode());
		posthis.setMaterialName(stock.getMaterialName());
		posthis.setStockUnit(stock.getStockUnit());
		posthis.setOpType("correct");
		posthis.setStatus("0");
		posthis.setGmtCreated(new Date());
		posthis.setOperator(user.getUserCode());
		database.correctStock(prehis, posthis);
		StockHistoryScheduleTask.startTask();
		stock.setStatus("3");
		TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(stock);

		return ResultBaseBuilder.succ().rb(request);
	}
}
