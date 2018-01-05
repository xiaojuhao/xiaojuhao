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
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.DatabaseService;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.StockHistoryScheduleTask;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

@Controller
@RequestMapping("/diaobo")
public class DiaoboController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;
	@Resource
	DatabaseService database;
	@Resource
	MaterialSpecService materialSpecService;

	@RequestMapping(value = "/commit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object commit() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		String inCabinCode = CommonUtils.get(request, "inCabinCode");
		String outCabinCode = CommonUtils.get(request, "outCabinCode");
		String dataJson = CommonUtils.get(request, "dataJson");
		if (StringUtils.isBlank(inCabinCode)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		try {
			CabinVo inCabin = cabinService.getCabinByCode(inCabinCode);
			CabinVo outCabin = cabinService.getCabinByCode(outCabinCode);
			String status = CommonUtils.get(request, "status");
			if (StringUtils.isBlank(status)) {
				status = "4";// 配送中
			}
			if (inCabin == null || outCabin == null) {
				return ResultBaseBuilder.fails(ResultCode.info_missing).msg("请输入拨出/拨入仓库信息").rb(request);
			}
			String remark = CommonUtils.get(request, "remark");
			WmsInventoryApplyDO apply = new WmsInventoryApplyDO();
			List<WmsInventoryApplyDetailDO> indetails = new ArrayList<>();
			// 保存采购单
			apply.setApplyNum(CommonUtils.uuid());
			apply.setCabinCode(inCabinCode);
			apply.setCabinName(inCabin.getName());
			apply.setFromCabinCode(outCabinCode);
			apply.setFromCabinName(outCabin.getName());
			apply.setSerialNo(CommonUtils.stringOfNow());
			apply.setApplyType("allocation");
			apply.setProposer(user.getUserCode());
			apply.setGmtCreated(new Date());
			apply.setGmtModified(new Date());
			apply.setCreator(user.getUserCode());
			apply.setModifier(user.getUserCode());
			apply.setStatus(status);
			apply.setRemark(remark);
			apply.setPaidStatus("1");
			apply.setPaidAmt(0D);
			apply.setPayables(0D);
			apply.setTotalPrice(0D);
			//
			JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
			List<WmsMaterialStockHistoryDO> historyList = new ArrayList<>();
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
				WmsMaterialStockHistoryDO his = new WmsMaterialStockHistoryDO();

				JSONObject j = dataArr.getJSONObject(i);
				WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
				detail.setCabinCode(apply.getCabinCode());
				detail.setCabinName(apply.getCabinName());
				detail.setFromCabinCode(apply.getFromCabinCode());
				detail.setFromCabinName(apply.getFromCabinName());
				detail.setApplyNum(apply.getApplyNum());
				detail.setApplyType(apply.getApplyType());
				detail.setMaterialCode(j.getString("materialCode"));
				detail.setMaterialName(j.getString("materialName"));
				detail.setSupplierCode(j.getString("supplierCode"));
				detail.setSupplierName(j.getString("supplierName"));
				detail.setTotalPrice(CommonUtils.parseDouble(j.getString("totalPrice"), null));
				detail.setSpecAmt(CommonUtils.parseDouble(j.getString("specAmt"), null));
				if (detail.getSpecAmt() == null) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】拨出数量为空").rb(request);
				}
				if (detail.getSpecAmt() <= 0) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】拨出数量不能小于等于0").rb(request);
				}
				detail.setSpecPrice(CommonUtils.parseDouble(j.getString("specPrice"), null));
				if (detail.getSpecPrice() == null) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】采购金额为空").rb(request);
				}
				if (detail.getSpecPrice() <= 0) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】采购金额不能小于等于0").rb(request);
				}
				detail.setSpecCode(j.getString("specCode"));
				WmsMaterialSpecDetailDO spec = this.materialSpecService.querySpecDetailByCode(//
						detail.getMaterialCode(), detail.getSpecCode());
				detail.setTransRate(CommonUtils.parseDouble(j.getString("transRate"), 1D));
				detail.setUtilizationRatio(spec.getUtilizationRatio());
				detail.setStockAmt(detail.getSpecAmt() * detail.getTransRate() * detail.getUtilizationRatio() / 100);
				detail.setSpecUnit(j.getString("specUnit"));
				detail.setStockUnit(j.getString("stockUnit"));
				detail.setRealStockAmt(detail.getStockAmt());
				detail.setGmtCreated(new Date());
				detail.setGmtModified(new Date());
				detail.setCreator(user.getUserCode());
				detail.setModifier(user.getUserCode());
				detail.setStatus("0");
				detail.setRemark(j.getString("remark"));
				detail.setInStockAmt(detail.getRealStockAmt());
				// 库存
				his.setMaterialCode(detail.getMaterialCode());
				his.setMaterialName(detail.getMaterialName());
				his.setCabinCode(detail.getFromCabinCode());
				his.setCabinName(detail.getFromCabinName());
				his.setOpType("out_stock");
				his.setCabinType(his.getCabinCode().startsWith("WH") ? "1" : "2");
				his.setRelateCode(detail.getApplyNum());
				his.setOperator(user.getUserCode());
				his.setGmtCreated(new Date());
				his.setAmt(-1 * detail.getStockAmt());
				his.setStockUnit(detail.getStockUnit());
				his.setStatus("0");
				his.setRemark("调往:" + detail.getCabinName() + "(" + detail.getCabinCode() + ")");
				indetails.add(detail);
				historyList.add(his);
			}
			// 插入数据库
			this.database.diaoboCommit(apply, indetails, historyList);
			StockHistoryScheduleTask.startTask();
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/confirm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirm() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

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
			return ResultBaseBuilder.fails("调拨单已处理").rb(request);
		}
		List<WmsMaterialStockHistoryDO> historyList = new ArrayList<>();
		List<WmsInventoryApplyDetailDO> updateList = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStock = j.getDouble("realStockAmt");
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
			h.setRemark("从" + order.getFromCabinName() + "(" + order.getFromCabinCode() + ")调入");
			historyList.add(h);
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("1");
			update.setRealStockAmt(realStock);
			updateList.add(update);
		}

		order.setStatus("5");
		database.diaoboConfirm(order, updateList, historyList);
		return ResultBaseBuilder.succ().rb(request);
	}
}
