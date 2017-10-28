package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialStockDO;

public interface WmsMaterialStockMapper {
	public List<WmsMaterialStockDO> selectByExample(WmsMaterialStockDO example);
}
