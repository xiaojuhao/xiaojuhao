package com.xjh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockCheckDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockCheckMainDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckMainMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.valueobject.CabinVo;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialCheckService {
	@Resource
	TkWmsMaterialStockCheckMainMapper checkMainMapper;
	@Resource
	TkWmsMaterialStockCheckDetailMapper checkDetailMapper;
	@Resource
	CabinService cabinService;
	@Resource
	TkWmsMaterialStockMapper stockMapper;
	@Resource
	MaterialService materialService;
	@Resource
	DatabaseService database;

	public ResultBase<WmsMaterialStockCheckMainDO> current(String cabinCode) {
		Example example = new Example(WmsMaterialStockCheckMainDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andIn("status", Arrays.asList("0", "1"));
		List<WmsMaterialStockCheckMainDO> list = checkMainMapper.selectByExample(example);
		if (list.size() == 0) {
			return ResultBaseBuilder.fails("没有正在盘点的记录").rb();
		}
		if (list.size() == 1) {
			return ResultBaseBuilder.succ().data(list.get(0)).rb();
		}
		if (list.size() > 1) {
			return ResultBaseBuilder.fails("系统存在多个盘点记录,请检查").rb();
		}
		return ResultBaseBuilder.fails("系统异常").rb();
	}

	public ResultBase<WmsMaterialStockCheckMainDO> startCheck(String cabinCode, WmsUserDO user) {
		Example example = new Example(WmsMaterialStockCheckMainDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andIn("status", Arrays.asList("0", "1"));
		List<WmsMaterialStockCheckMainDO> list = checkMainMapper.selectByExample(example);
		if (list.size() > 0) {
			return ResultBaseBuilder.fails("已有盘点记录").rb();
		}
		CabinVo cabin = cabinService.getCabinByCode(cabinCode);
		if (cabin == null) {
			return ResultBaseBuilder.fails("仓库信息不存在").rb();
		}
		WmsMaterialStockCheckMainDO main = new WmsMaterialStockCheckMainDO();
		main.setCabinCode(cabinCode);
		main.setCabinName(cabin.getName());
		main.setStartTime(new Date());
		main.setStatus("1");
		main.setChecker(user.getUserCode());
		checkMainMapper.insert(main);
		try {
			WmsMaterialStockDO stockCond = new WmsMaterialStockDO();
			stockCond.setCabinCode(cabinCode);
			stockCond.setStatus("1");
			List<WmsMaterialStockDO> stockList = stockMapper.select(stockCond);
			stockList.forEach((it) -> {
				WmsMaterialDO material = materialService.queryMaterialByCode(it.getMaterialCode());
				if (material == null || material.getStatus() != 1) {
					return;
				}
				WmsMaterialStockCheckDetailDO dd = new WmsMaterialStockCheckDetailDO();
				dd.setCabinCode(it.getCabinCode());
				dd.setCabinName(it.getCabinName());
				dd.setMaterialCode(it.getMaterialCode());
				dd.setMaterialName(it.getMaterialName());
				dd.setStockUnit(it.getStockUnit());
				dd.setMainId(main.getId());
				dd.setOriStockAmt(it.getCurrStock());
				dd.setStockAmt(it.getCurrStock());
				dd.setStatus("0");
				dd.setCategory(material.getCategory());
				dd.setDeltaAmt(0D);
				checkDetailMapper.insert(dd);
			});
		} catch (Exception ex) {
			if (main.getId() != null) {
				checkMainMapper.deleteByPrimaryKey(main.getId());
				WmsMaterialStockCheckDetailDO deleteCond = new WmsMaterialStockCheckDetailDO();
				deleteCond.setMainId(main.getId());
				checkDetailMapper.delete(deleteCond);
			}
			return ResultBaseBuilder.fails(ex.getMessage()).rb();
		}
		return ResultBaseBuilder.succ().data(main).rb();
	}

	public ResultBase<WmsMaterialStockCheckMainDO> finishCheck(WmsMaterialStockCheckMainDO main, WmsUserDO user) {
		ResultBase<List<WmsMaterialStockCheckDetailDO>> detail = queryCheckDetail(main);
		if (detail.getIsSuccess() == false) {
			return ResultBaseBuilder.fails(detail.getMessage()).rb();
		}
		detail.getValue().forEach((it) -> {
			if ("1".equals(it.getStatus())) {
				WmsMaterialStockHistoryDO prehis = null;
				if (Math.abs(it.getStockAmt() - it.getOriStockAmt()) > 0.01) {
					prehis = new WmsMaterialStockHistoryDO();
					prehis.setCabinCode(it.getCabinCode());
					prehis.setCabinName(it.getCabinName());
					prehis.setCabinType(it.getCabinCode().startsWith("WH") ? "1" : "2");
					prehis.setAmt(it.getStockAmt() - it.getOriStockAmt());// 库存不一致的量
					prehis.setPreStock(it.getOriStockAmt());
					prehis.setPostStock(it.getStockAmt());
					prehis.setMaterialCode(it.getMaterialCode());
					prehis.setMaterialName(it.getMaterialName());
					prehis.setStockUnit(it.getStockUnit());
					prehis.setOpType("correct_delta");//
					prehis.setAffectStock("N");
					prehis.setStatus("1"); // 记录盘点库存和实际库存的差额，只起记录作用，所以状态直接置为1
					prehis.setRemark("库存盘点差额");
					prehis.setGmtCreated(new Date());
					prehis.setOperator(user.getUserCode());
				}
				// 记录history
				WmsMaterialStockHistoryDO posthis = new WmsMaterialStockHistoryDO();
				posthis.setCabinCode(it.getCabinCode());
				posthis.setCabinName(it.getCabinName());
				posthis.setCabinType(it.getCabinCode().startsWith("WH") ? "1" : "2");
				posthis.setAmt(it.getStockAmt());// 修正库存量
				posthis.setMaterialCode(it.getMaterialCode());
				posthis.setMaterialName(it.getMaterialName());
				posthis.setStockUnit(it.getStockUnit());
				posthis.setAffectStock("Y");
				posthis.setOpType("correct");
				posthis.setStatus("0");
				posthis.setRemark("盘点");
				posthis.setGmtCreated(new Date());
				posthis.setOperator(user.getUserCode());
				database.correctStock(prehis, posthis);
			}
		});
		main.setStatus("2");
		main.setEndTime(new Date());
		this.checkMainMapper.updateByPrimaryKeySelective(main);
		StockHistoryScheduleTask.startTask();
		return ResultBaseBuilder.succ().data(main).rb();
	}

	public ResultBase<List<WmsMaterialStockCheckDetailDO>> queryCheckDetail(WmsMaterialStockCheckMainDO main) {
		List<WmsMaterialStockCheckDetailDO> list = new ArrayList<>();
		WmsMaterialStockCheckDetailDO cond = new WmsMaterialStockCheckDetailDO();
		cond.setMainId(main.getId());
		cond.setCabinCode(main.getCabinCode());
		list = this.checkDetailMapper.select(cond);
		return ResultBaseBuilder.succ().data(list).rb();
	}
}
