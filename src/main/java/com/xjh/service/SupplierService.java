package com.xjh.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xjh.dao.dataobject.WmsSupplierDO;

@Service
public class SupplierService {
	private static Cache<String, WmsSupplierDO> cache = CacheBuilder.newBuilder() //
			.maximumSize(100) // 最多缓存的条数
			.expireAfterWrite(5, TimeUnit.MINUTES) // 缓存时间
			.build();

	public WmsSupplierDO getSupplierByCode(String supplierCode) {
		if (StringUtils.isBlank(supplierCode)) {
			return null;
		}
		String key = "getSupplierByCode_" + supplierCode;
		WmsSupplierDO dd = cache.getIfPresent(key);
		if (dd != null) {
			return dd;
		}
		WmsSupplierDO cond = new WmsSupplierDO();
		cond.setSupplierCode(supplierCode);
		dd = TkMappers.inst().supplierMapper.selectOne(cond);
		if (dd != null) {
			cache.put(key, dd);
		}
		return dd;
	}
}
