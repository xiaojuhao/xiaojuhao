package com.xjh.eventbus.evthandles;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.eventbus.BusCruise;
import com.xjh.service.Mappers;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.MaterialStockChangeVo;

@Service
public class InStockEventHandler implements InitializingBean {
	@Subscribe
	public void handle(InStockEvent e) {
		try {
			//material
			WmsMaterialDO material = new WmsMaterialDO();
			material.setMaterialCode(e.getMaterialCode());
			material = TkMappers.inst().getMaterialMapper().selectOne(material);
			//warehouse
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(e.getWarehouseCode());
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			//stock
			WmsMaterialStockDO stock = new WmsMaterialStockDO();
			stock.setMaterialCode(e.getMaterialCode());
			stock.setWarehouseCode(e.getWarehouseCode());
			stock.setStockType("2");//分库
			stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
			if(stock == null){ //第一次入库
				stock = new WmsMaterialStockDO();
				stock.setMaterialCode(e.getMaterialCode());
				stock.setWarehouseCode(e.getWarehouseCode());
				stock.setStockType("2");//分库
				stock.setCurrStock(0D);
				stock.setUsedStock(0D);
				stock.setStockUnit(material.getStockUnit());
				stock.setMaterialName(material.getMaterialName());
				stock.setWarehouseName(warehouse.getWarehouseName());
				TkMappers.inst().getMaterialStockMapper().insert(stock);
				//history
				WmsMaterialStockHistoryDO history = new WmsMaterialStockHistoryDO();
				history.setMaterialCode(e.getMaterialCode());
				history.setStoreCode(e.getStoreCode());
				history.setMaterialName(material.getMaterialName());
				history.setOpType("create");
				history.setWarehouseCode(warehouse.getWarehouseCode());
				history.setCurrStock(stock.getCurrStock());
				history.setStockChg(-1 * e.getInstockAmt());
				history.setGmtCreated(new Date());
				history.setOperator(e.getOperator());
				history.setRemark("创建库存记录");
				TkMappers.inst().getMaterialStockHistoryMapper().insert(history);
			}
			//history
			WmsMaterialStockHistoryDO hisotry = new WmsMaterialStockHistoryDO();
			hisotry.setMaterialCode(e.getMaterialCode());
			hisotry.setStoreCode(e.getStoreCode());
			hisotry.setMaterialName(material.getMaterialName());
			hisotry.setOpType("in_stock");
			hisotry.setWarehouseCode(warehouse.getWarehouseCode());
			hisotry.setCurrStock(stock.getCurrStock());
			hisotry.setStockChg(-1 * e.getInstockAmt());
			hisotry.setGmtCreated(new Date());
			hisotry.setOperator(e.getOperator());
			hisotry.setRemark(e.getRemark());
			TkMappers.inst().getMaterialStockHistoryMapper().insert(hisotry);
			//increase stock
			MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
			changeVo.setMaterialCode(material.getMaterialCode());
			changeVo.setWarehouseCode(warehouse.getWarehouseCode());
			changeVo.setStockChgAmt(e.getInstockAmt());
			changeVo.setOperator(e.getOperator());
			Mappers.inst().getStockMapper().increaseStock(changeVo);
		} catch (Exception ex) {
			e.setSuccess(false);
			e.setResultMsg("系统异常");
			ex.printStackTrace(System.err);
		} finally {
			e.end();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		BusCruise.registerHandler(this);
	}

}
