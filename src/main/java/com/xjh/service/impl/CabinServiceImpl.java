package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
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
				vo.setStatus(ware.getStatus());
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
				vo.setStatus(store.getStatus());
				return vo;
			}
		}
		return null;
	}

	@Override
	public List<String> getMyCabinCodes(WmsUserDO user) {
		List<String> cabins = new ArrayList<>();
		if ("1".equals(user.getIsSu())) {
			TkMappers.inst().getWarehouseMapper().selectAll()//
					.forEach((it) -> cabins.add(it.getWarehouseCode()));
			TkMappers.inst().getStoreMapper().selectAll()//
					.forEach((it) -> cabins.add(it.getStoreCode()));
		} else {
			cabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			cabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
		}
		return cabins;
	}

	@Override
	public List<CabinVo> getAllCabins() {
		List<CabinVo> cabins = new ArrayList<>();
		TkMappers.inst().getWarehouseMapper().selectAll()//
				.forEach((it) -> {
					CabinVo vo = new CabinVo();
					vo.setCode(it.getWarehouseCode());
					vo.setName(it.getWarehouseName());
					vo.setType("1");
					cabins.add(vo);
				});
		TkMappers.inst().getStoreMapper().selectAll()//
				.forEach((it) -> {
					CabinVo vo = new CabinVo();
					vo.setCode(it.getStoreCode());
					vo.setName(it.getStoreName());
					vo.setType("2");
					cabins.add(vo);
				});
		return cabins;
	}

}
