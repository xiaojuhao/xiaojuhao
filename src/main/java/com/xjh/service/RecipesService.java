package com.xjh.service;

import com.xjh.commons.PageResult;
import com.xjh.dao.dataobject.WmsRecipesDO;

public interface RecipesService {
	public int saveRecipes(WmsRecipesDO wmsRecipesDO);
	
	public PageResult<WmsRecipesDO> queryRecipes(WmsRecipesDO wmsRecipesDO);
	
	public WmsRecipesDO queryRecipesByCode(String recipesCode);
}
