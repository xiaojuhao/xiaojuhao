package com.xjh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsNoticeDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialRequireMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.valueobject.CabinVo;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class MaterialRequireService {
	@Resource
	CabinService cabinService;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialRequireMapper requireMapper;
	@Resource
	MaterialSpecService materialSpecService;
	@Resource
	TkWmsMaterialStockMapper materialStockMapper;
	@Resource
	SequenceService sequenceService;
	@Resource
	RecentMemService recentMemService;
	
	public void clearUnDealedRecord(String cabinCode) {
		WmsMaterialRequireDO cond = new WmsMaterialRequireDO();
		cond.setStatus("0");
		cond.setCabinCode(cabinCode);
		this.requireMapper.delete(cond);
	}

	public void addRequire(String cabinCode, String materialCode, Double requireAmt, String date) {
		if (requireAmt == null) {
			requireAmt = 0D;
		}
		if (CommonUtils.isAnyBlank(cabinCode, materialCode)) {
			return;
		}
		CabinVo cabin = cabinService.getCabinByCode(cabinCode);
		WmsMaterialDO material = materialService.queryMaterialByCode(materialCode);
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
			r.setPurchaseType("1");
			r.setStatus("0");
			r.setGmtCreated(new Date());
			r.setCreator("system");
			r.setGmtModified(new Date());
			r.setModifier("system");
			r.setStockUnit(material.getStockUnit());
			String recentCabin = recentMemService.recentValue(r.getCabinCode(), r.getMaterialCode());
			if(StringUtils.isNotBlank(recentCabin)){
				r.setFromCabinCode(recentCabin);
			}
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
			if (rd.getSpecAmt() == null || rd.getSpecAmt() <= 0.01) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入采购数量").rb();
			}
			if (rd.getSpecPrice() == null || rd.getSpecPrice() <= 0.01) {
				return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入采购金额").rb();
			}
			if ("1".equals(rd.getPurchaseType())) {
				if (CommonUtils.isAnyBlank(rd.getSupplierCode(), rd.getSupplierName())) {
					return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入供应商信息").rb();
				}
			} else if ("2".equals(rd.getPurchaseType())) {
				if (CommonUtils.isAnyBlank(rd.getFromCabinCode(), rd.getFromCabinName())) {
					return ResultBaseBuilder.fails(rd.getMaterialName() + "没有输入调拨仓库信息").rb();
				}
			} else {
				return ResultBaseBuilder.fails("请选择采购类型").rb();
			}
			String key = null;
			if ("1".equals(rd.getPurchaseType())) {
				key = rd.getCabinCode() + "_" + rd.getSupplierCode() + "_" + rd.getPurchaseType();
			} else if ("2".equals(rd.getPurchaseType())) {
				key = rd.getCabinCode() + "_" + rd.getFromCabinCode() + "_" + rd.getPurchaseType();
			}
			if (!groups.containsKey(key)) {
				groups.put(key, new ArrayList<WmsMaterialRequireDO>());
			}
			groups.get(key).add(rd);
		}
		List<WmsInventoryApplyDetailDO> details = new ArrayList<>();
		List<WmsInventoryApplyDO> applys = new ArrayList<>();
		//每组生成一个申请单
		for (String key : groups.keySet()) {
			List<String> keyFiled = CommonUtils.splitAsList(key, "_");
			List<WmsMaterialRequireDO> glist = groups.get(key);
			if (glist == null || glist.size() == 0) {
				continue;
			}
			String prefix = CommonUtils.stringOfToday("yyyyMMdd") + keyFiled.get(0);
			long num = sequenceService.next(prefix);
			String applyNum = prefix + StringUtils.leftPad(num + "", 3, "0");//申请单号
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
				de.setStockUnit(r.getStockUnit());
				de.setSpecCode(r.getSpecCode());
				de.setSpecUnit(r.getSpecUnit());
				WmsMaterialSpecDetailDO spec = materialSpecService.querySpecDetailByCode(//
						de.getMaterialCode(), de.getSpecCode());
				if (spec == null) {
					return ResultBaseBuilder.fails(r.getMaterialName() + "规格错误").rb();
				}
				de.setUtilizationRatio(spec.getUtilizationRatio());
				de.setTransRate(spec.getTransRate().doubleValue());
				de.setSpecUnit(spec.getSpecUnit());
				if ("1".equals(keyFiled.get(2))) {
					de.setApplyType("purchase");
					de.setSupplierCode(r.getSupplierCode());
					de.setSupplierName(r.getSupplierName());
				} else if ("2".equals(keyFiled.get(2))) {
					de.setApplyType("allocation");
					de.setFromCabinCode(r.getFromCabinCode());
					de.setFromCabinName(r.getFromCabinName());
				}
				de.setSpecAmt(r.getSpecAmt() == null ? 0D : r.getSpecAmt());
				de.setSpecPrice(r.getSpecPrice() == null ? 0D : r.getSpecPrice());
				de.setStockAmt(r.getStockAmt() == null ? 0D : r.getStockAmt());
				de.setRealSpecAmt(de.getSpecAmt());
				de.setInStockAmt(de.getStockAmt() * spec.getUtilizationRatio() / 100);
				de.setRealStockAmt(de.getInStockAmt());
				//如果是调拨，则校验拨出库存是否足够
				if ("2".equals(keyFiled.get(2))) {
					WmsMaterialStockDO cond = new WmsMaterialStockDO();
					cond.setCabinCode(r.getFromCabinCode());
					cond.setMaterialCode(de.getMaterialCode());
					WmsMaterialStockDO stock = TkMappers.inst().getMaterialStockMapper().selectOne(cond);
					if (stock == null) {
						return ResultBaseBuilder
								.fails("仓库" + de.getFromCabinName() + "中没有原料" + de.getMaterialName() + "的库存").rb();
					}
					if ("Y".equals(stock.getIsDeleted())) {
						return ResultBaseBuilder
								.fails("仓库" + de.getFromCabinName() + "的原料" + de.getMaterialName() + "已被删除").rb();
					}
					if (stock.getCurrStock() < de.getInStockAmt()) {
						return ResultBaseBuilder
								.fails("仓库" + de.getFromCabinName() + "的原料" + de.getMaterialName() + "库存不足").rb();
					}
				}
				de.setStatus("1");
				de.setGmtCreated(new Date());
				de.setCreator(user.getUserCode());
				de.setGmtModified(new Date());
				de.setModifier(user.getUserCode());
				de.setTotalPrice(de.getSpecPrice() * de.getSpecAmt());
				de.setRemark("原料需求生成");
				de.setPaidAmt(0D);
				if ("allocation".equals(de.getApplyType())) {
					de.setPaidStatus("3");
					de.setPayables(0D);
				} else {
					de.setPaidStatus("0");
					de.setPayables(de.getTotalPrice());
				}

				totalPrice += de.getTotalPrice();
				details.add(de);
			}
			apply.setApplyNum(applyNum);
			if ("1".equals(keyFiled.get(2))) {
				apply.setApplyType("purchase");
				apply.setSupplierCode(glist.get(0).getSupplierCode());
				apply.setSupplierName(glist.get(0).getSupplierName());
			} else if ("2".equals(keyFiled.get(2))) {
				apply.setApplyType("allocation");
				apply.setFromCabinCode(glist.get(0).getFromCabinCode());
				apply.setFromCabinName(glist.get(0).getFromCabinName());
			}
			apply.setSerialNo(CommonUtils.stringOfToday("yyyyMMddHHmmss"));
			apply.setCabinCode(glist.get(0).getCabinCode());
			apply.setCabinName(glist.get(0).getCabinName());
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
			TkMappers.inst().getInventoryApplyDetailMapper().insert(detail);
		}
		for (WmsInventoryApplyDO apply : applys) {
			TkMappers.inst().getInventoryApplyMapper().insert(apply);
		}
		for (WmsMaterialRequireDO require : requireList) {
			this.requireMapper.updateByPrimaryKeySelective(require);
		}
		return ResultBaseBuilder.succ().rb();
	}

	public void createMaterialRequre(List<String> cabins) {
		List<String> cabinCodes = new ArrayList<>();
		if (cabins == null) {
			cabinService.getAllCabins().forEach((it) -> {
				cabinCodes.add(it.getCode());
			});
			this.clearUnDealedRecord(null);
		} else {
			cabinCodes.addAll(cabins);
		}
		//重新计算需求信息
		cabinCodes.forEach((cabinCode) -> {
			long start = System.currentTimeMillis();
			log.info("创建{}需求信息------开始------", cabinCode);
			WmsMaterialStockDO cond = new WmsMaterialStockDO();
			cond.setCabinCode(cabinCode);
			cond.setIsDeleted("N");
			List<WmsMaterialStockDO> stocks = this.materialStockMapper.select(cond);
			log.info("查询到{}条库存记录，开始遍历库存......", stocks.size());
			int i = 0;
			for (WmsMaterialStockDO stock : stocks) {
				if (i++ % 100 == 0) {
					log.info("已处理{}条记录", i);
				}
				try {
					Double wariningAmt = stock.getWarningValue1();//最低库存预警值
					//没有预警值得时候，不告警
					if (wariningAmt == null || wariningAmt <= 0.01) {
						continue;
					}
					//库存大于预警值，不告警
					if (stock.getCurrStock() >= wariningAmt) {
						continue;
					}
					//生成告警信息
					WmsNoticeDO notice = new WmsNoticeDO();
					notice.setStatus("1");
					notice.setTitle("库存预警");
					notice.setContent(stock.getCabinName() + "【" + stock.getMaterialName() + "】库存不足,请及时补货");
					notice.setGmtCreated(new Date());
					notice.setGmtExpired(DateBuilder.today().futureDays(7).date());
					notice.setMsgType("warning");
					TkMappers.inst().getNoticeMapper().insert(notice);
					//生成原料需求单
					final Double requireAmt = stock.getWarningValue2() - stock.getCurrStock();
					this.addRequire(//
							stock.getCabinCode(), //
							stock.getMaterialCode(), //
							requireAmt, //
							null);

				} catch (Exception e) {
					log.error("", e);
				}
			}
			log.info("创建{}需求信息------结束,耗时{}ms---", cabinCode, System.currentTimeMillis() - start);
		});

	}
}
