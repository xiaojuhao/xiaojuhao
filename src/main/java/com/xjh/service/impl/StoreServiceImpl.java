package com.xjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	TkWmsStoreMapper storeMapper;

	@Override
	public ResultBase<WmsStoreDO> addStore(WmsStoreDO storeDO) {
		if (CommonUtils.isAnyBlank(storeDO.getStoreCode(), storeDO.getStoreName())) {
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		this.storeMapper.insert(storeDO);
		return ResultBaseBuilder.succ().data(storeDO).rb();
	}

	public ResultBase<WmsStoreDO> updateStore(WmsStoreDO storeDO) {
		if (CommonUtils.isAnyBlank(storeDO.getStoreCode(), storeDO.getStoreName())) {
			return ResultBaseBuilder.fails("入参错误").rb();
		}
		storeMapper.updateByPrimaryKeySelective(storeDO);
		return ResultBaseBuilder.succ().data(storeDO).rb();

	}

	@Override
	public PageResult<WmsStoreDO> queryStore(WmsStoreDO example) {
		if (example == null) {
			example = new WmsStoreDO();
		}
		PageResult<WmsStoreDO> page = new PageResult<>();
		int totalRows = this.storeMapper.selectCount(example);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		PageHelper.orderBy("id");
		List<WmsStoreDO> list = this.storeMapper.select(example);
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(example.getPageNo());
		page.setPageSize(example.getPageSize());
		return page;
	}

	@Override
	public WmsStoreDO queryByStoreCode(String storeCode) {
		if (CommonUtils.isBlank(storeCode)) {
			return null;
		}
		WmsStoreDO record = new WmsStoreDO();
		record.setStoreCode(storeCode);
		return storeMapper.selectOne(record);
	}

}
