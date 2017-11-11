package com.xjh.eventbus.evthandles;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.eventbus.BusCruise;
import com.xjh.service.Mappers;
import com.xjh.service.MaterialService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.MaterialStockChangeVo;

@Service
public class OutStockEventHandler implements InitializingBean{
	@Resource
	MaterialService materialService;
	
	@Subscribe
	public void handle(OutStockEvent event){
		try{
			//
			WmsMaterialDO material = new WmsMaterialDO();
			material.setMaterialCode(event.getMaterialCode());
			material = TkMappers.inst().getMaterialMapper().selectOne(material);
			//
			WmsMaterialStockDO stock = new WmsMaterialStockDO();
			stock.setMaterialCode(event.getMaterialCode());
			stock.setWarehouseCode(event.getWarehouseCode());
			stock.setStockType("2");//分库
			stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
			if(stock == null){
				materialService.initMaterialStock(event.getMaterialCode(), event.getWarehouseCode());
				stock = new WmsMaterialStockDO();
				stock.setMaterialCode(event.getMaterialCode());
				stock.setWarehouseCode(event.getWarehouseCode());
				stock.setStockType("2");//分库
				stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
			}
			//
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(event.getWarehouseCode());
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			//
			WmsMaterialStockHistoryDO newHistory = new WmsMaterialStockHistoryDO();
			newHistory.setMaterialCode(event.getMaterialCode());
			newHistory.setStoreCode(event.getStoreCode());
			newHistory.setMaterialName(material.getMaterialName());
			newHistory.setOpType("out_stock");
			newHistory.setWarehouseCode(warehouse.getWarehouseCode());
			newHistory.setCurrStock(stock.getCurrStock());
			newHistory.setStockChg(-1 * event.getOutstockAmt());
			newHistory.setGmtCreated(new Date());
			newHistory.setOperator(event.getOperator());
			newHistory.setRemark(event.getRemark());
			TkMappers.inst().getMaterialStockHistoryMapper().insert(newHistory);
			//
			MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
			changeVo.setMaterialCode(material.getMaterialCode());
			changeVo.setWarehouseCode(warehouse.getWarehouseCode());
			changeVo.setStockChgAmt(event.getOutstockAmt());
			changeVo.setOperator(event.getOperator());
			Mappers.inst().getStockMapper().useStock(changeVo);
			event.setSuccess(true);
		}catch(Exception ex){
			event.setSuccess(false);
			event.setResultMsg("出库异常");
			ex.printStackTrace(System.err);
		}finally{
			event.end();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		BusCruise.registerHandler(this);
	}
}
