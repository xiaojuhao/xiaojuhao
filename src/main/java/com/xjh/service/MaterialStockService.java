package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
}
