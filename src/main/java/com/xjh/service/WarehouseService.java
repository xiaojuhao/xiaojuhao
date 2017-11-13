package com.xjh.service;

import com.xjh.dao.dataobject.WmsWarehouseDO;

public interface WarehouseService {
	public WmsWarehouseDO getWarehouseByCode(String warehouseCode);
}
