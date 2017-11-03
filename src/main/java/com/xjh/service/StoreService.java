package com.xjh.service;

import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsStoreDO;

public interface StoreService {
	public ResultBase<WmsStoreDO> addStore(WmsStoreDO storeDO);
	
	public ResultBase<Integer> updateStore(WmsStoreDO storeDO);
	
	public PageResult<WmsStoreDO> queryStore(WmsStoreDO example);
	
	public WmsStoreDO queryByStoreCode(String storeCode);
}
