package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsMaterialStockDO;

@Service
public class MaterialStockService {
	@Resource
	MaterialService materialService;

	public void initStock(String materialCode) {
		TkMappers.inst().getStoreMapper().selectAll()//
				.forEach((store) -> {
					materialService.initMaterialStock(materialCode, store.getStoreCode());
				});
		TkMappers.inst().getWarehouseMapper().selectAll()//
				.forEach((warehouse) -> {
					materialService.initMaterialStock(materialCode, warehouse.getWarehouseCode());
				});
	}

	public WmsMaterialStockDO queryMaterialStock(String cabinCode, String materialCode) {
		if (CommonUtils.isAnyBlank(materialCode, cabinCode)) {
			return null;
		}
		WmsMaterialStockDO cond = new WmsMaterialStockDO();
		cond.setMaterialCode(materialCode);
		cond.setCabinCode(cabinCode);
		cond.setIsDeleted("N");
		return TkMappers.inst().getMaterialStockMapper().selectOne(cond);
	}
}
