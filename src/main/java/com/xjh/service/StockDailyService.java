package com.xjh.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockDailyDO;
import com.xjh.dao.mapper.WmsMaterialStockDailyMapper;

@Service
public class StockDailyService {
	@Resource
	CabinService cabinService;
	@Resource
	MaterialService materialService;
	@Resource
	WmsMaterialStockDailyMapper stockDailyMapper;

	public WmsMaterialStockDailyDO getOrInitStockDaily(//
			String materialCode, //
			String cabinCode, //
			String date) {
		Date statDate = CommonUtils.parseDate(date);
		if (statDate == null) {
			return null;
		}
		if (CommonUtils.isAnyBlank(cabinCode, materialCode)) {
			return null;
		}
		statDate = DateBuilder.base(statDate).zeroAM().date();
		WmsMaterialStockDailyDO cond = new WmsMaterialStockDailyDO();
		cond.setMaterialCode(materialCode);
		cond.setCabinCode(cabinCode);
		cond.setStatDate(statDate);
		WmsMaterialStockDailyDO dailyDO = TkMappers.inst().getStockDailyMapper().selectOne(cond);
		if (dailyDO != null) {
			return dailyDO;
		}
		//如果不存在，则查询stock表
		WmsMaterialStockDO stockDO = new WmsMaterialStockDO();
		stockDO.setMaterialCode(materialCode);
		stockDO.setCabinCode(cabinCode);
		stockDO.setIsDeleted("N");
		stockDO = TkMappers.inst().getMaterialStockMapper().selectOne(stockDO);
		if (stockDO == null) {
			return null;//stock表也没有数据
		}
		dailyDO = new WmsMaterialStockDailyDO();
		dailyDO.setMaterialCode(materialCode);
		dailyDO.setMaterialName(stockDO.getMaterialName());
		dailyDO.setCabinCode(cabinCode);
		dailyDO.setCabinName(stockDO.getCabinName());
		dailyDO.setStatDate(statDate);
		dailyDO.setInitAmt(stockDO.getCurrStock());
		dailyDO.setConsumeAmt(0D);
		dailyDO.setLossAmt(0D);
		dailyDO.setBusyDay("N");
		dailyDO.setGmtCreated(new Date());
		dailyDO.setIsDeleted("N");
		TkMappers.inst().getStockDailyMapper().insert(dailyDO);
		return dailyDO;
	}

	public void setCurrentStockAmt(//
			String materialCode, //
			String cabinCode, //
			String date) {
		WmsMaterialStockDailyDO dd = this.getOrInitStockDaily(materialCode, cabinCode, date);
		WmsMaterialStockDO record = new WmsMaterialStockDO();
		record.setMaterialCode(materialCode);
		record.setCabinCode(cabinCode);
		WmsMaterialStockDO stock = TkMappers.inst().getMaterialStockMapper().selectOne(record);
		if (stock != null) {
			WmsMaterialStockDailyDO update = new WmsMaterialStockDailyDO();
			update.setId(dd.getId());
			update.setCurrentStockAmt(stock.getCurrStock());
			TkMappers.inst().getStockDailyMapper().updateByPrimaryKeySelective(update);
		}
	}

	public int addConsume(//
			String materialCode, //
			String cabinCode, //
			String date, //
			Double consumeAmt, //
			Double lossAmt) {
		if (CommonUtils.isAnyBlank(materialCode, cabinCode, date)) {
			return 0;
		}
		this.getOrInitStockDaily(materialCode, cabinCode, date);
		Date statDate = CommonUtils.parseDate(date);
		WmsMaterialStockDailyDO stockDailyDO = new WmsMaterialStockDailyDO();
		stockDailyDO.setMaterialCode(materialCode);
		stockDailyDO.setCabinCode(cabinCode);
		stockDailyDO.setStatDate(statDate);
		stockDailyDO.setConsumeAmt(consumeAmt);
		stockDailyDO.setLossAmt(lossAmt);
		return this.stockDailyMapper.addConsumeAmt(stockDailyDO);
	}

	public int setConsumeAmt2(//
			String materialCode, //
			String cabinCode, //
			String date, //
			Double consumeAmt2) {
		if (CommonUtils.isAnyBlank(materialCode, cabinCode, date)) {
			return 0;
		}
		WmsMaterialStockDailyDO dd = getOrInitStockDaily(materialCode, cabinCode, date);
		if (dd == null) {
			return 0;
		}
		WmsMaterialStockDailyDO update = new WmsMaterialStockDailyDO();
		update.setId(dd.getId());
		update.setConsumeAmt2(consumeAmt2);

		return TkMappers.inst().getStockDailyMapper().updateByPrimaryKeySelective(update);
	}
}
