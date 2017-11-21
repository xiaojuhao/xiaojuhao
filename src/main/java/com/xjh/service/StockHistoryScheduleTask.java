package com.xjh.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.mapper.WmsMaterialStockHistoryMapper;
import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSplitMapper;
import com.xjh.valueobject.MaterialStockChangeVo;

@Service
public class StockHistoryScheduleTask implements InitializingBean {
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
	@Resource
	TkWmsMaterialSplitMapper splitMapper;

	public void handleInStock(WmsMaterialStockHistoryDO record) {
		if (!"in_stock".equals(record.getOpType())) {
			return;
		}
		WmsMaterialDO material = materialService.getMaterialByCode(record.getMaterialCode());
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
		changeVo.setOperator(record.getOperator());
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

		status.setMaterialName(material.getMaterialName());
		status.setStockUnit(material.getStockUnit());
		status.setOperator(record.getOperator());
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
	}

	public void handleOutStock(WmsMaterialStockHistoryDO record) {
		if (!"out_stock".equals(record.getOpType())) {
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
		changeVo.setStockChgAmt(Math.abs(record.getAmt()));
		changeVo.setOperator(record.getOperator());
		wmsMaterialStockMapper.useStock(changeVo);
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

	public void handleSplitIn(WmsMaterialStockHistoryDO record) {
		if (!"split_in".equals(record.getOpType())) {
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
		changeVo.setOperator(record.getOperator());
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

	public void handleSplitOut(WmsMaterialStockHistoryDO record) {
		if (!"split_out".equals(record.getOpType())) {
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
		changeVo.setOperator(record.getOperator());
		wmsMaterialStockMapper.useStock(changeVo);
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

	public void handleBoru(WmsMaterialStockHistoryDO record) {
		if (!"boru".equals(record.getOpType())) {
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
		changeVo.setOperator(record.getOperator());
		wmsMaterialStockMapper.changeByDelta(changeVo);
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

	public void handleBochu(WmsMaterialStockHistoryDO record) {
		if (!"bochu".equals(record.getOpType())) {
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
		changeVo.setOperator(record.getOperator());
		wmsMaterialStockMapper.changeByDelta(changeVo);
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

	public void handleCorrect(WmsMaterialStockHistoryDO record) {
		if (!"correct".equals(record.getOpType())) {
			return;
		}
		// 更新状态为处理中.....
		WmsMaterialStockHistoryDO status = new WmsMaterialStockHistoryDO();
		status.setId(record.getId());
		status.setStatus("9");
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
		// 初始化库存
		materialService.initMaterialStock(record.getMaterialCode(), record.getCabinCode());
		WmsMaterialStockDO stock = new WmsMaterialStockDO();
		stock.setMaterialCode(record.getMaterialCode());
		stock.setCabinCode(record.getCabinCode());
		stock.setCabinType(record.getCabinType());
		stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);

		WmsMaterialStockDO update = new WmsMaterialStockDO();
		update.setId(stock.getId());
		update.setCurrStock(record.getAmt());
		TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(update);
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
				} else if (dd.getOpType().equals("out_stock")) {
					this.handleOutStock(dd);
				} else if (dd.getOpType().equals("split_out")) {
					this.handleSplitOut(dd);
				} else if (dd.getOpType().equals("split_in")) {
					this.handleSplitIn(dd);
				} else if (dd.getOpType().equals("boru")) {
					this.handleBoru(dd);
				} else if (dd.getOpType().equals("bochu")) {
					this.handleBochu(dd);
				} else if (dd.getOpType().equals("correct")) {
					this.handleCorrect(dd);
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
