package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.valueobject.MaterialStockChangeVo;

public interface WmsMaterialStockMapper {
	public int changeByDelta(MaterialStockChangeVo changeVo);
	
	public int startCorrect(WmsMaterialStockDO stock);
	
	public int finishCorrect(WmsMaterialStockDO stock);
	
	public List<WmsMaterialStockDO> selectWaiting(WmsMaterialStockDO cond);
	
	public List<WmsMaterialStockDO> query(WmsMaterialStockDO example);
	
	public int count(WmsMaterialStockDO example);
	
}
