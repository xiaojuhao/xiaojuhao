//package com.xjh.eventbus.evthandles;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
//import com.google.common.eventbus.Subscribe;
//import com.xjh.dao.dataobject.WmsMaterialDO;
//import com.xjh.dao.dataobject.WmsMaterialStockDO;
//import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
//import com.xjh.dao.dataobject.WmsStoreDO;
//import com.xjh.dao.dataobject.WmsWarehouseDO;
//import com.xjh.eventbus.BusCruise;
//import com.xjh.service.Mappers;
//import com.xjh.service.TkMappers;
//import com.xjh.valueobject.MaterialStockChangeVo;
//
//@Service
//public class InStockEventHandler implements InitializingBean {
//	@Subscribe
//	public void handle(InStockEvent e) {
//		try {
//			String cabCode = e.getCabCode();
//			String cabName = null;
//			String cabType = null;
//			// material
//			WmsMaterialDO material = new WmsMaterialDO();
//			material.setMaterialCode(e.getMaterialCode());
//			material = TkMappers.inst().getMaterialMapper().selectOne(material);
//			if (cabCode.startsWith("WH")) {
//				// warehouse
//				WmsWarehouseDO warehouse = new WmsWarehouseDO();
//				warehouse.setWarehouseCode(e.getCabCode());
//				warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
//				cabName = warehouse.getWarehouseName();
//				cabType = "1";
//			} else if (cabCode.startsWith("MD")) {
//				// store
//				WmsStoreDO store = new WmsStoreDO();
//				store.setStoreCode(cabCode);
//				store = TkMappers.inst().getStoreMapper().selectOne(store);
//				cabName = store.getStoreName();
//				cabType = "2";
//			}
//			// stock
//			WmsMaterialStockDO stock = new WmsMaterialStockDO();
//			stock.setMaterialCode(e.getMaterialCode());
//			// stock.setCabCode(e.getCabCode());
//			stock.setStockType("2");// 分库
//			stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
//			if (stock == null) { // 第一次入库
//				stock = new WmsMaterialStockDO();
//				stock.setMaterialCode(e.getMaterialCode());
//				// stock.setCabCode(cabCode);
//				// stock.setCabName(cabName);
//				stock.setStockType("2");// 分库
//				stock.setCurrStock(0D);
//				stock.setUsedStock(0D);
//				stock.setStockUnit(material.getStockUnit());
//				stock.setMaterialName(material.getMaterialName());
//				TkMappers.inst().getMaterialStockMapper().insert(stock);
//			}
//			// history
//			WmsMaterialStockHistoryDO hisotry = new WmsMaterialStockHistoryDO();
//			hisotry.setMaterialCode(e.getMaterialCode());
//			// hisotry.setCabCode(cabCode);
//			// hisotry.setCabName(cabName);
//			// hisotry.setCabType(cabType);
//			hisotry.setMaterialName(material.getMaterialName());
//			hisotry.setOpType("in_stock");
//			// hisotry.setCurrStock(stock.getCurrStock());
//			// hisotry.setStockChg(-1 * e.getInstockAmt());
//			hisotry.setGmtCreated(new Date());
//			hisotry.setOperator(e.getOperator());
//			hisotry.setRemark(e.getRemark());
//			hisotry.setStatus("1");
//			TkMappers.inst().getMaterialStockHistoryMapper().insert(hisotry);
//			// increase stock
//			MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
//			changeVo.setMaterialCode(material.getMaterialCode());
////			changeVo.setCabCode(cabCode);
//			changeVo.setStockChgAmt(e.getInstockAmt());
//			changeVo.setOperator(e.getOperator());
//			Mappers.inst().getStockMapper().increaseStock(changeVo);
//		} catch (Exception ex) {
//			e.setSuccess(false);
//			e.setResultMsg("系统异常");
//			ex.printStackTrace(System.err);
//		} finally {
//			e.end();
//		}
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		BusCruise.registerHandler(this);
//	}
//
//}
