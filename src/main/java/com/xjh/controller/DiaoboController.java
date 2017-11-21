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
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

@Controller
@RequestMapping("/diaobo")
public class DiaoboController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;

	@RequestMapping(value = "/commit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object commit() {
		String inCabinCode = CommonUtils.get(request, "inCabinCode");
		String outCabinCode = CommonUtils.get(request, "outCabinCode");
		String dataJson = CommonUtils.get(request, "dataJson");
		if (StringUtils.isBlank(inCabinCode)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			CabinVo inCabin = cabinService.getCabinByCode(inCabinCode);
			CabinVo outCabin = cabinService.getCabinByCode(outCabinCode);
			String status = CommonUtils.get(request, "status");
			if (StringUtils.isBlank(status)) {
				status = "4";// 配送中
			}
			if (inCabin == null || outCabin == null) {
				return ResultBaseBuilder.fails(ResultCode.info_missing).msg("货站信息不存在").rb(request);
			}
			String remark = CommonUtils.get(request, "remark");
			WmsInventoryApplyDO inorder = new WmsInventoryApplyDO();
			WmsInventoryApplyDO outorder = new WmsInventoryApplyDO();
			List<WmsInventoryApplyDetailDO> indetails = new ArrayList<>();
			List<WmsInventoryApplyDetailDO> outdetails = new ArrayList<>();
			// 保存采购单
			inorder.setApplyNum(CommonUtils.uuid());
			inorder.setCabinCode(inCabinCode);
			inorder.setCabinName(inCabin.getName());
			inorder.setSerialNo(CommonUtils.stringOfNow());
			inorder.setApplyType("allocate_in");
			inorder.setProposer(user.getUserName());
			inorder.setGmtCreated(new Date());
			inorder.setGmtModified(new Date());
			inorder.setCreator(user.getUserCode());
			inorder.setModifier(user.getUserCode());
			inorder.setStatus(status);
			inorder.setRemark(remark);
			// 出库单
			CommonUtils.copyPropertiesQuietly(outorder, inorder);
			outorder.setApplyType("allocate_out");
			outorder.setApplyNum(CommonUtils.uuid());
			outorder.setCabinCode(outCabinCode);
			outorder.setCabinName(outCabin.getName());
			//
			JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
				JSONObject j = dataArr.getJSONObject(i);
				Double storageLifeNum = j.getDouble("storageLifeNum");
				String storageLifeUnit = j.getString("storageLifeUnit");
				WmsInventoryApplyDetailDO indetail = new WmsInventoryApplyDetailDO();
				WmsInventoryApplyDetailDO outdetail = new WmsInventoryApplyDetailDO();
				indetail.setCabinCode(inorder.getCabinCode());
				indetail.setCabinName(inorder.getCabinName());
				indetail.setApplyNum(inorder.getApplyNum());
				indetail.setApplyType(inorder.getApplyType());
				indetail.setMaterialCode(j.getString("materialCode"));
				indetail.setMaterialName(j.getString("materialName"));
				indetail.setSupplierCode(j.getString("supplierCode"));
				indetail.setSupplierName(j.getString("supplierName"));
				indetail.setSpecAmt(j.getDouble("specAmt"));
				if (indetail.getSpecAmt() == null) {
					indetail.setSpecAmt(0D);
				}
				indetail.setSpecUnit(j.getString("specUnit"));
				if (indetail.getSpecUnit() == null) {
					indetail.setSpecUnit("无");
				}
				indetail.setSpecPrice(j.getDouble("specPrice"));
				if (indetail.getSpecPrice() == null) {
					indetail.setSpecPrice(0D);
				}
				indetail.setStockAmt(indetail.getSpecAmt() * j.getDouble("specQty"));
				indetail.setStockUnit("个");
				indetail.setRealStockAmt(indetail.getStockAmt());
				indetail.setTotalPrice(indetail.getStockAmt() * indetail.getSpecPrice());
				indetail.setProdDate(CommonUtils.parseDate(j.getString("prodDate")));
				indetail.setExpDate(CommonUtils.parseDate("expDate"));
				if (indetail.getExpDate() == null) {
					switch (storageLifeUnit) {
					case "D":
						indetail.setExpDate(CommonUtils.futureDays(indetail.getProdDate(), storageLifeNum.intValue()));
						break;
					case "M":
						indetail.setExpDate(CommonUtils.futureMonth(indetail.getProdDate(), storageLifeNum.intValue()));
						break;
					default:
						indetail.setExpDate(CommonUtils.futureYear(indetail.getProdDate(), 10));
					}
				}
				indetail.setKeepTime(storageLifeNum + storageLifeUnit);
				indetail.setGmtCreated(new Date());
				indetail.setGmtModified(new Date());
				indetail.setCreator(user.getUserCode());
				indetail.setModifier(user.getUserCode());
				indetail.setStatus("0");

				CommonUtils.copyPropertiesQuietly(outdetail, indetail);
				outdetail.setCabinCode(outCabinCode);
				outdetail.setCabinName(outCabin.getName());
				outdetail.setApplyNum(outorder.getApplyNum());
				outdetail.setApplyType(outorder.getApplyType());
				indetails.add(indetail);
				outdetails.add(outdetail);
			}
			// 插入数据库
			TkMappers.inst().getPurchaseOrderMapper().insert(inorder);
			TkMappers.inst().getPurchaseOrderMapper().insert(outorder);
			for (WmsInventoryApplyDetailDO detail : indetails) {
				TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
			}
			for (WmsInventoryApplyDetailDO detail : outdetails) {
				TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
			}
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}
}
