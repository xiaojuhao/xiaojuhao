package com.xjh.dao.mapper;

import com.xjh.valueobject.MaterialStockChangeVo;

public interface WmsMaterialStockMapper {
	public int changeByDelta(MaterialStockChangeVo changeVo);
}
