package com.xjh.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.service.CabinService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

@Service
public class CabinServiceImpl implements CabinService {

	@Override
	public CabinVo getCabinByCode(String cabinCode) {
		if (StringUtils.isBlank(cabinCode)) {
			return null;
		}
		CabinVo vo = new CabinVo();
		if (cabinCode.startsWith("WH")) {
			WmsWarehouseDO ware = new WmsWarehouseDO();
			ware.setWarehouseCode(cabinCode);
			ware = TkMappers.inst().getWarehouseMapper().selectOne(ware);
			if (ware != null) {
				vo.setType("1");
				vo.setName(ware.getWarehouseName());
				vo.setCode(cabinCode);
				return vo;
			}
		} else {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			if (store != null) {
				vo.setType("2");
				vo.setName(store.getStoreName());
				vo.setCode(cabinCode);
				return vo;
			}
		}
		return null;
	}

}
