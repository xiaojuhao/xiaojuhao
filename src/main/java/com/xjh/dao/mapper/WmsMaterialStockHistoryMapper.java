package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;

public interface WmsMaterialStockHistoryMapper {
	public List<WmsMaterialStockHistoryDO> selectByExample(WmsMaterialStockHistoryDO example);
}
