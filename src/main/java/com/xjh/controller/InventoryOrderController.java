package com.xjh.controller;

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
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

@Controller
@RequestMapping("/inventoryOrder")
public class InventoryOrderController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;

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
			order.setApplyNum(CommonUtils.uuid());
			order.setCabinCode(cabinCode);
			order.setCabinName(cabinVo.getName());
			order.setSerialNo(CommonUtils.stringOfNow());
			order.setApplyType("purchase");
			order.setProposer(user.getUserName());
			order.setGmtCreated(new Date());
			order.setGmtModified(new Date());
			order.setCreator(user.getUserCode());
			order.setModifier(user.getUserCode());
			order.setStatus(status);
			order.setRemark(remark);
			//
			String dataJson = CommonUtils.get(request, "dataJson");
			JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
				JSONObject j = dataArr.getJSONObject(i);
				Double storageLifeNum = j.getDouble("storageLifeNum");
				String storageLifeUnit = j.getString("storageLifeUnit");
				WmsInventoryApplyDetailDO d = new WmsInventoryApplyDetailDO();
				d.setCabinCode(order.getCabinCode());
				d.setCabinName(order.getCabinName());
				d.setApplyNum(order.getApplyNum());
				d.setApplyType(order.getApplyType());
				d.setMaterialCode(j.getString("materialCode"));
				d.setMaterialName(j.getString("materialName"));
				d.setSupplierCode(j.getString("supplierCode"));
				d.setSupplierName(j.getString("supplierName"));
				d.setSpecAmt(j.getDouble("specAmt"));
				if (d.getSpecAmt() == null) {
					d.setSpecAmt(0D);
				}
				d.setSpecUnit(j.getString("specUnit"));
				if (d.getSpecUnit() == null) {
					d.setSpecUnit("无");
				}
				d.setSpecPrice(j.getDouble("specPrice"));
				if (d.getSpecPrice() == null) {
					d.setSpecPrice(0D);
				}
				d.setStockAmt(d.getSpecAmt() * j.getDouble("specQty"));
				d.setStockUnit("个");
				d.setRealStockAmt(d.getStockAmt());
				d.setTotalPrice(d.getStockAmt() * d.getSpecPrice());
				d.setProdDate(CommonUtils.parseDate(j.getString("prodDate")));
				d.setExpDate(CommonUtils.parseDate("expDate"));
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
				d.setStatus("0");
				details.add(d);
			}
			// 插入数据库
			TkMappers.inst().getPurchaseOrderMapper().insert(order);
			for (WmsInventoryApplyDetailDO detail : details) {
				TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
			}
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/queryMyPurchaseOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyPurchaseOrder() {
		String status = CommonUtils.get(request, "status");
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setStatus(status);
		List<WmsInventoryApplyDO> list = TkMappers.inst().getPurchaseOrderMapper().select(cond);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(list.size());
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryPurchaseOrderDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryPurchaseOrderDetail() {
		String ApplyNum = CommonUtils.get(request, "ApplyNum");
		if (StringUtils.isBlank(ApplyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(ApplyNum);
		List<WmsInventoryApplyDetailDO> list = TkMappers.inst().getPurchaseOrderDetailMapper().select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/confirmInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirmInventory() {
		String dataJson = CommonUtils.get(request, "dataJson");
		String ApplyNum = CommonUtils.get(request, "ApplyNum");
		if (StringUtils.isBlank(dataJson) || StringUtils.isBlank(ApplyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDO order = new WmsInventoryApplyDO();
		order.setApplyNum(ApplyNum);
		order = TkMappers.inst().getPurchaseOrderMapper().selectOne(order);
		if (order == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if (!"4".equals(order.getStatus())) {
			return ResultBaseBuilder.fails("采购单已处理").rb(request);
		}
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStock = j.getDouble("realStockAmt");
			ApplyNum = j.getString("ApplyNum");
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = TkMappers.inst().getPurchaseOrderDetailMapper().selectOne(detail);
			if (detail == null || StringUtils.equals("1", detail.getStatus())) {
				continue;
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
			h.setUnitPrice(detail.getSpecPrice());
			h.setProductDate(detail.getProdDate());
			h.setStockUnit(detail.getStockUnit());
			h.setAmt(realStock);
			h.setOperator(detail.getModifier());
			h.setGmtCreated(new Date());
			h.setStatus("0");
			h.setRelateCode(detail.getApplyNum());
			TkMappers.inst().getMaterialStockHistoryMapper().insert(h);
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("1");
			update.setRealStockAmt(realStock);
			TkMappers.inst().getPurchaseOrderDetailMapper().updateByPrimaryKeySelective(update);
		}
		// 采购单状态修改

		order.setStatus("5");
		TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKeySelective(order);
		return ResultBaseBuilder.succ().rb(request);
	}
}
