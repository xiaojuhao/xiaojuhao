package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsRecipesDO;

public interface WmsRecipesMapper {
	public List<WmsRecipesDO> query(WmsRecipesDO example);

	public int count(WmsRecipesDO example);
}
