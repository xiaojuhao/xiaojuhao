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
import com.xjh.commons.TaskUtils;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.DatabaseService;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.MaterialStockService;
import com.xjh.service.PriceService;
import com.xjh.service.StockHistoryScheduleTask;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/diaobo")
@Slf4j
public class DiaoboController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;
	@Resource
	DatabaseService database;
	@Resource
	MaterialSpecService materialSpecService;
	@Resource
	PriceService priceService;
	@Resource
	MaterialStockService materialStockService;

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
			if (inCabin.equals(outCabin)) {
				return ResultBaseBuilder.fails("不能自己给自己调拨").rb(request);
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
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
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
				WmsMaterialStockDO stock = materialStockService.queryMaterialStock(//
						detail.getFromCabinCode(), detail.getMaterialCode());
				if (stock == null) {
					return ResultBaseBuilder.fails(detail.getMaterialName() + "没有库存").rb(request);
				}
				detail.setTotalPrice(CommonUtils.parseDouble(j.getString("totalPrice"), null));
				detail.setSpecAmt(CommonUtils.parseDouble(j.getString("specAmt"), null));
				detail.setRealSpecAmt(detail.getSpecAmt());
				if (detail.getSpecAmt() == null || detail.getSpecAmt() <= 0.001) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】拨出数量不能为空").rb(request);
				}
				detail.setSpecPrice(CommonUtils.parseDouble(j.getString("specPrice"), null));
				if (detail.getSpecPrice() == null || detail.getSpecPrice() <= 0) {
					return ResultBaseBuilder.fails("【" + detail.getMaterialName() + "】采购金额不能为空").rb(request);
				}
				detail.setSpecCode(j.getString("specCode"));
				WmsMaterialSpecDetailDO spec = this.materialSpecService.querySpecDetailByCode(//
						detail.getMaterialCode(), detail.getSpecCode());
				detail.setTransRate(CommonUtils.parseDouble(j.getString("transRate"), 1D));
				detail.setUtilizationRatio(spec.getUtilizationRatio());
				detail.setStockAmt(detail.getSpecAmt() * detail.getTransRate());
				detail.setInStockAmt(detail.getStockAmt() * spec.getUtilizationRatio() / 100);
				detail.setRealStockAmt(detail.getInStockAmt());
				if (detail.getRealStockAmt() > stock.getCurrStock()) {
					return ResultBaseBuilder.fails(detail.getMaterialName() + "库存不足").rb(request);
				}
				detail.setSpecUnit(j.getString("specUnit"));
				detail.setStockUnit(j.getString("stockUnit"));
				detail.setGmtCreated(new Date());
				detail.setGmtModified(new Date());
				detail.setCreator(user.getUserCode());
				detail.setModifier(user.getUserCode());
				detail.setTotalPrice(detail.getSpecAmt() * detail.getSpecPrice());
				detail.setStatus("1");//待入库
				detail.setRemark(j.getString("remark"));
				TaskUtils.schedule(() -> {
					priceService.updateSpecPrice(detail.getSpecCode(), detail.getCabinCode(), detail.getSpecPrice());
				});
				indetails.add(detail);
			}
			// 插入数据库
			this.database.diaoboCommit(apply, indetails, new ArrayList<>());
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			log.error("", ex);
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
			String materialName = j.getString("materialName");
			Double realSpecAmt = CommonUtils.parseDouble(j.getString("realSpecAmt"), null);
			if (realSpecAmt == null) {
				return ResultBaseBuilder.fails(materialName + "请输入采购规格").rb(request);
			}
			Double realStock = j.getDouble("realStockAmt");
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = TkMappers.inst().getPurchaseOrderDetailMapper().selectOne(detail);
			if (detail == null) {
				return ResultBaseBuilder.fails(materialName + "记录不存在").rb(request);
			}
			if (!StringUtils.equals("1", detail.getStatus())) {
				return ResultBaseBuilder.fails(materialName + "状态不对,无法处理:" + detail.getStatus()).rb(request);
			}
			// history
			WmsMaterialStockHistoryDO h1 = new WmsMaterialStockHistoryDO();
			h1.setOpType("in_stock");
			h1.setCabinCode(detail.getCabinCode());
			h1.setCabinName(detail.getCabinName());
			h1.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
			h1.setMaterialCode(detail.getMaterialCode());
			h1.setMaterialName(detail.getMaterialName());
			h1.setKeepDays(detail.getKeepTime());
			h1.setTotalPrice(detail.getTotalPrice());
			h1.setUnitPrice(detail.getSpecPrice());
			h1.setProductDate(detail.getProdDate());
			h1.setStockUnit(detail.getStockUnit());
			h1.setTotalPrice(detail.getTotalPrice());
			h1.setAmt(realStock);
			h1.setOperator(detail.getModifier());
			h1.setGmtCreated(new Date());
			h1.setStatus("0");
			h1.setRelateCode(detail.getApplyNum());
			h1.setRemark("从【" + order.getFromCabinName() + "】调入");
			h1.setAffectStock("Y");
			WmsMaterialStockHistoryDO h2 = new WmsMaterialStockHistoryDO();
			h2.setOpType("allocation");
			h2.setCabinCode(detail.getFromCabinCode());
			h2.setCabinName(detail.getFromCabinName());
			h2.setCabinType(detail.getFromCabinCode().startsWith("WH") ? "1" : "2");
			h2.setMaterialCode(detail.getMaterialCode());
			h2.setMaterialName(detail.getMaterialName());
			h2.setKeepDays(detail.getKeepTime());
			h2.setTotalPrice(detail.getTotalPrice());
			h2.setUnitPrice(detail.getSpecPrice());
			h2.setProductDate(detail.getProdDate());
			h2.setStockUnit(detail.getStockUnit());
			h2.setAmt(detail.getInStockAmt());
			h2.setOperator(detail.getModifier());
			h2.setTotalPrice(detail.getTotalPrice());
			h2.setGmtCreated(new Date());
			h2.setStatus("0");
			h2.setRelateCode(detail.getApplyNum());
			h2.setRemark("拨出到【" + detail.getCabinName() + "】");
			h2.setAffectStock("Y");
			historyList.add(h1);
			historyList.add(h2);
			//损失
			if (Math.abs(realStock - detail.getInStockAmt()) > 0.001) {
				WmsMaterialStockHistoryDO h3 = new WmsMaterialStockHistoryDO();
				h3.setOpType("allocation_loss");
				h3.setCabinCode(detail.getFromCabinCode());
				h3.setCabinName(detail.getFromCabinName());
				h3.setCabinType(detail.getFromCabinCode().startsWith("WH") ? "1" : "2");
				h3.setMaterialCode(detail.getMaterialCode());
				h3.setMaterialName(detail.getMaterialName());
				h3.setKeepDays(detail.getKeepTime());
				h3.setTotalPrice(detail.getTotalPrice());
				h3.setUnitPrice(detail.getSpecPrice());
				h3.setProductDate(detail.getProdDate());
				h3.setStockUnit(detail.getStockUnit());
				h3.setAmt(realStock - detail.getInStockAmt());
				h3.setOperator(detail.getModifier());
				h3.setGmtCreated(new Date());
				h3.setStatus("1"); //损耗已经发生，不改变库存
				h3.setRelateCode(detail.getApplyNum());
				h3.setAffectStock("N");
				h3.setRemark("拨出到【" + detail.getCabinName() + "】过程损耗");
				historyList.add(h3);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("2");
			update.setRealSpecAmt(realSpecAmt);
			update.setRealStockAmt(realStock);
			updateList.add(update);
		}

		database.diaoboConfirm(order, updateList, historyList);
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		cond.setStatus("1");
		int notHandleRows = TkMappers.inst().getPurchaseOrderDetailMapper().selectCount(cond);
		if (notHandleRows == 0) {
			order.setStatus("5");
			TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKey(order);
		}
		StockHistoryScheduleTask.startTask();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/deleteInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteInventory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String applyNum = CommonUtils.get(request, "applyNum");
		if (StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setApplyNum(applyNum);
		WmsInventoryApplyDO record = TkMappers.inst().getPurchaseOrderMapper().selectOne(cond);
		if (record == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		record.setStatus("6");
		TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKeySelective(record);
		return ResultBaseBuilder.succ().rb(request);
	}
}
