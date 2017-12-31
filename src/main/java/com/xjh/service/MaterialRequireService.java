package com.xjh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialRequireDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialRequireMapper;
import com.xjh.valueobject.CabinVo;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialRequireService {
	@Resource
	CabinService cabinService;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialRequireMapper requireMapper;

	public void addRequire(String cabinCode, String materialCode, Double requireAmt, String date) {
		if (requireAmt == null) {
			requireAmt = 0D;
		}
		if (CommonUtils.isAnyBlank(cabinCode, materialCode)) {
			return;
		}
		CabinVo cabin = cabinService.getCabinByCode(cabinCode);
		WmsMaterialDO material = materialService.getMaterialByCode(materialCode);
		if (cabin == null || material == null) {
			return;
		}
		Date requireDate = CommonUtils.parseDate(date, "yyyyMMdd");
		if (requireDate == null) {
			requireDate = DateBuilder.now().zeroAM().date();
		}
		WmsMaterialRequireDO cond = new WmsMaterialRequireDO();
		cond.setMaterialCode(materialCode);
		cond.setCabinCode(cabinCode);
		cond.setStatus("0");
		cond.setRequireGroup("DEFAULT");
		PageHelper.orderBy("require_date desc");
		List<WmsMaterialRequireDO> list = requireMapper.select(cond);
		if (list.size() == 0) {
			WmsMaterialRequireDO r = new WmsMaterialRequireDO();
			r.setCabinCode(cabinCode);
			r.setCabinName(cabin.getName());
			r.setMaterialCode(materialCode);
			r.setRequireGroup("DEFAULT");
			r.setMaterialName(material.getMaterialName());
			r.setMaterialCate(material.getCategory());
			r.setRequireDate(requireDate);
			r.setRequireAmt(requireAmt);
			r.setStatus("0");
			r.setGmtCreated(new Date());
			r.setCreator("system");
			r.setGmtModified(new Date());
			r.setModifier("system");
			requireMapper.insert(r);
		} else {
			WmsMaterialRequireDO r = list.get(0);
			if (r.getRequireAmt() == null) {
				r.setRequireAmt(0D);
			}
			r.setGmtModified(new Date());
			r.setModifier("system");
			r.setRequireDate(requireDate);
			r.setRequireAmt(r.getRequireAmt() + requireAmt);
			requireMapper.updateByPrimaryKeySelective(r);
		}
	}

	public ResultBase<String> generateApply(List<Long> requireIds, WmsUserDO user) {
		if (requireIds == null || requireIds.size() == 0) {
			return ResultBaseBuilder.fails("入参错误，生成申请单失败").rb();
		}
		Example example = new Example(WmsMaterialRequireDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("id", requireIds);
		List<WmsMaterialRequireDO> requireList = requireMapper.selectByExample(example);
		//将申请记录按照供应商和门店进行分组
		Map<String, List<WmsMaterialRequireDO>> groups = new HashMap<>();
		for (WmsMaterialRequireDO rd : requireList) {
			if (CommonUtils.isAnyBlank(rd.getSpecCode(), rd.getSpecName())) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入规格信息").rb();
			}
			if (CommonUtils.isAnyBlank(rd.getSupplierCode(), rd.getSupplierName())) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入供应商信息").rb();
			}
			if (rd.getSpecAmt() == null) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入采购数量").rb();
			}
			if (rd.getSpecPrice() == null) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入采购金额").rb();
			}
			String key = rd.getCabinCode() + "_" + rd.getSupplierCode();
			if (!groups.containsKey(key)) {
				groups.put(key, new ArrayList<WmsMaterialRequireDO>());
			}
			groups.get(key).add(rd);
		}
		List<WmsInventoryApplyDetailDO> details = new ArrayList<>();
		List<WmsInventoryApplyDO> applys = new ArrayList<>();
		//每组生成一个申请单
		for (String key : groups.keySet()) {
			List<WmsMaterialRequireDO> glist = groups.get(key);
			if (glist == null || glist.size() == 0) {
				continue;
			}
			String applyNum = CommonUtils.uuid();//申请单号
			WmsInventoryApplyDO apply = new WmsInventoryApplyDO();
			double totalPrice = 0D;
			for (WmsMaterialRequireDO r : glist) {
				r.setStatus("1");
				WmsInventoryApplyDetailDO de = new WmsInventoryApplyDetailDO();
				de.setApplyNum(applyNum);
				de.setCabinCode(r.getCabinCode());
				de.setCabinName(r.getCabinName());
				de.setMaterialCode(r.getMaterialCode());
				de.setMaterialName(r.getMaterialName());
				de.setSupplierCode(r.getSupplierCode());
				de.setSupplierName(r.getSupplierName());
				de.setStockUnit(r.getStockUnit());
				de.setSpecCode(r.getSpecCode());
				de.setSpecUnit(r.getSpecUnit());
				de.setApplyType("purchase");
				de.setSpecAmt(r.getSpecAmt() == null ? 0D : r.getSpecAmt());
				de.setSpecPrice(r.getSpecPrice() == null ? 0D : r.getSpecPrice());
				de.setStockAmt(r.getStockAmt() == null ? 0D : r.getStockAmt());
				de.setStatus("1");
				de.setGmtCreated(new Date());
				de.setCreator(user.getUserCode());
				de.setGmtModified(new Date());
				de.setModifier(user.getUserCode());
				de.setRemark("原料需求生成");
				totalPrice += de.getSpecPrice() * de.getSpecAmt();
				details.add(de);
			}
			apply.setApplyNum(applyNum);
			apply.setApplyType("purchase");
			apply.setSerialNo(CommonUtils.stringOfToday("yyyyMMddHHmmss"));
			apply.setCabinCode(glist.get(0).getCabinCode());
			apply.setCabinName(glist.get(0).getCabinName());
			apply.setSupplierCode(glist.get(0).getSupplierCode());
			apply.setSupplierName(glist.get(0).getSupplierName());
			apply.setProposer(user.getUserCode());
			apply.setStatus("4");
			apply.setTotalPrice(totalPrice);
			apply.setPayables(totalPrice);
			apply.setPaidStatus("0");
			apply.setPaidAmt(0D);
			apply.setGmtCreated(new Date());
			apply.setGmtModified(new Date());
			apply.setCreator(user.getUserCode());
			apply.setModifier(user.getUserCode());
			applys.add(apply);
			for (WmsMaterialRequireDO r : glist) {
				r.setStatus("1");
				r.setApplyNum(applyNum);
			}
		}
		//保存数据
		for (WmsInventoryApplyDetailDO detail : details) {
			TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
		}
		for (WmsInventoryApplyDO apply : applys) {
			TkMappers.inst().getPurchaseOrderMapper().insert(apply);
		}
		for (WmsMaterialRequireDO require : requireList) {
			this.requireMapper.updateByPrimaryKeySelective(require);
		}
		return ResultBaseBuilder.succ().rb();
	}

}
