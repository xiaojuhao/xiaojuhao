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
import com.xjh.dao.dataobject.WmsPurchaseOrderDO;
import com.xjh.dao.dataobject.WmsPurchaseOrderDetailDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

@Controller
@RequestMapping("/purchase")
public class PurcaseController {
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
			WmsPurchaseOrderDO order = new WmsPurchaseOrderDO();
			List<WmsPurchaseOrderDetailDO> details = new ArrayList<>();
			// 保存采购单
			order.setOrderNum(CommonUtils.uuid());
			order.setCabinCode(cabinCode);
			order.setCabinName(cabinVo.getName());
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
				WmsPurchaseOrderDetailDO d = new WmsPurchaseOrderDetailDO();
				d.setCabinCode(order.getCabinCode());
				d.setCabinName(order.getCabinName());
				d.setOrderNum(order.getOrderNum());
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
				d.setStockAmt(j.getDouble("amt"));
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
				details.add(d);
			}
			// 插入数据库
			TkMappers.inst().getPurchaseOrderMapper().insert(order);
			for (WmsPurchaseOrderDetailDO detail : details) {
				detail.setOrderId(order.getId());
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
		WmsPurchaseOrderDO cond = new WmsPurchaseOrderDO();
		List<WmsPurchaseOrderDO> list = TkMappers.inst().getPurchaseOrderMapper().select(cond);
		PageResult<WmsPurchaseOrderDO> page = new PageResult<>();
		page.setTotalRows(list.size());
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}
}
