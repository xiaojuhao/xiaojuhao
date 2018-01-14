package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;

public interface WmsMaterialStockHistoryMapper {
	public Long getStartId();

	public WmsMaterialStockHistoryDO selectOneToDeal();
	
	public List<WmsMaterialStockHistoryDO> selectListToDeal();
}
