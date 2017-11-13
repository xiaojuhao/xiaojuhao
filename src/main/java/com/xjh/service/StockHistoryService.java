package com.xjh.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.mapper.WmsMaterialStockHistoryMapper;
import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.valueobject.MaterialStockChangeVo;

@Service
public class StockHistoryService implements InitializingBean {
	public static AtomicBoolean isRunning = new AtomicBoolean(false);
	@Resource
	WmsMaterialStockHistoryMapper wmsMaterialStockHistoryMapper;
	@Resource
	MaterialService materialService;
	@Resource
	WmsMaterialStockMapper wmsMaterialStockMapper;
	@Resource
	WarehouseService WarehouseService;
	@Resource
	StoreService storeService;

	public void handleInStock(WmsMaterialStockHistoryDO record) {
		if (!"in_stock".equals(record.getOpType())) {
			return;
		}
		// 更新状态为处理中.....
		WmsMaterialStockHistoryDO status = new WmsMaterialStockHistoryDO();
		status.setId(record.getId());
		status.setStatus("9");
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
		// 初始化库存
		materialService.initMaterialStock(record.getMaterialCode(), record.getCabinCode());
		MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
		changeVo.setMaterialCode(record.getMaterialCode());
		changeVo.setCabinCode(record.getCabinCode());
		changeVo.setStockChgAmt(record.getAmt());
		wmsMaterialStockMapper.increaseStock(changeVo);
		// 处理完成。。。
		status.setId(record.getId());
		status.setStatus("1");
		if (record.getCabinCode().startsWith("WH")) {
			WmsWarehouseDO warehouse = this.WarehouseService.getWarehouseByCode(record.getCabinCode());
			status.setCabinName(warehouse.getWarehouseName());
		} else if (record.getCabinCode().startsWith("MD")) {
			WmsStoreDO store = this.storeService.queryByStoreCode(record.getCabinCode());
			status.setCabinName(store.getStoreName());
		}
		WmsMaterialDO material = materialService.getMaterialByCode(record.getMaterialCode());
		status.setMaterialName(material.getMaterialName());
		status.setStockUnit(material.getStockUnit());
		status.setOperator(record.getOperator());
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
	}

	public void start() {
		if (!isRunning.compareAndSet(false, true)) {
			return;
		}
		try {
			while (true) {
				WmsMaterialStockHistoryDO dd = wmsMaterialStockHistoryMapper.selectOneToDeal();
				if (dd == null) {
					break;
				}
				if (dd.getOpType().equals("in_stock")) {
					handleInStock(dd);
				}
			}
		} finally {
			isRunning.set(false);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("start afterPropertiesSet..............");
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("start..............");
					start();
				} catch (Exception ex) {

				}
			}
		}, 0, 5, TimeUnit.SECONDS);
	}
}
