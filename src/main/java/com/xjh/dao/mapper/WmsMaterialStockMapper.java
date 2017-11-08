package com.xjh.dao.mapper;

import com.xjh.valueobject.MaterialStockChangeVo;

public interface WmsMaterialStockMapper {
	/**
	 * 使用库存（总库、分库一起更新）
	 * @param changeVo
	 * @return
	 */
	public int useStock(MaterialStockChangeVo changeVo);
	/**
	 * 增加库存（总库、分库一起更新）
	 * @param changeVo
	 * @return
	 */
	public int increaseStock(MaterialStockChangeVo changeVo);
}
