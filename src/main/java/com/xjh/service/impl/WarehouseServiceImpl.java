package com.xjh.service.impl;

import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.service.TkMappers;
import com.xjh.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Override
	public WmsWarehouseDO getWarehouseByCode(String warehouseCode) {
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		warehouse.setWarehouseCode(warehouseCode);
		warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
		return warehouse;
	}

}
