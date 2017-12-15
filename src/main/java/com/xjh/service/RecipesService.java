package com.xjh.service;

import java.util.List;

import com.xjh.commons.PageResult;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;

public interface RecipesService {
	public int saveRecipes(WmsRecipesDO wmsRecipesDO);

	public PageResult<WmsRecipesDO> queryRecipes(WmsRecipesDO wmsRecipesDO);

	public WmsRecipesDO queryRecipesByCode(String recipesCode);

	public List<WmsRecipesFormulaDO> queryRecipesFormula(String recipesCode);
}
