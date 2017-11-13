package com.xjh.dao.mapper;

import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;

public interface WmsMaterialStockHistoryMapper {
	public Long getStartId();

	public WmsMaterialStockHistoryDO selectOneToDeal();
}
