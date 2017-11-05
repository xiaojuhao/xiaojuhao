package com.xjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.PageResult;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.tkmapper.TkWmsRecipesMapper;
import com.xjh.service.RecipesService;
import com.xjh.service.SequenceService;

@Service
public class RecipesServiceImpl implements RecipesService {
	@Resource
	TkWmsRecipesMapper recipesMapper;
	@Resource
	SequenceService sequenceService;
	
	@Override
	public int saveRecipes(WmsRecipesDO wmsRecipesDO) {
		String recipesCode = wmsRecipesDO.getRecipesCode();
		Long id = wmsRecipesDO.getId();
		if (id != null || StringUtils.isNotBlank(recipesCode)) {
			long val = sequenceService.next("wms_recipes");
			recipesCode = "CD"+StringUtils.leftPad(val+"", 6, "0");
			wmsRecipesDO.setRecipesCode(recipesCode);
			return recipesMapper.insert(wmsRecipesDO);
		}else{
			return recipesMapper.updateByPrimaryKeySelective(wmsRecipesDO);
		}
	}

	@Override
	public PageResult<WmsRecipesDO> queryRecipes(WmsRecipesDO wmsRecipesDO) {
		if(wmsRecipesDO == null){
			wmsRecipesDO = new WmsRecipesDO();
		}
		PageResult<WmsRecipesDO> page = new PageResult<WmsRecipesDO>();
		int totalRows = this.recipesMapper.selectCount(wmsRecipesDO);
		PageHelper.startPage(wmsRecipesDO.getPageNo(),wmsRecipesDO.getPageSize());
		List<WmsRecipesDO> list = this.recipesMapper.select(wmsRecipesDO);
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(wmsRecipesDO.getPageNo());
		page.setPageSize(wmsRecipesDO.getPageSize());
		return page;
	}

	@Override
	public WmsRecipesDO queryRecipesByCode(String recipesCode) {
		if(StringUtils.isBlank(recipesCode)){
			return null;
		}
		WmsRecipesDO record = new WmsRecipesDO();
		record.setRecipesCode(recipesCode);
		return recipesMapper.selectOne(record);
	}
}
