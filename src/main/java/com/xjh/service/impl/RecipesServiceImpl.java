package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.PageResult;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.tkmapper.TkWmsRecipesMapper;
import com.xjh.service.RecipesService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;

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
			WmsRecipesDO t = new WmsRecipesDO();
			t.setRecipesCode(recipesCode);
			t.setId(id);
			t = this.recipesMapper.selectOne(t);
			if (t == null) {
				throw new RuntimeException("数据异常");
			}
			wmsRecipesDO.setId(t.getId());
			return recipesMapper.updateByPrimaryKeySelective(wmsRecipesDO);
		} else {
			long val = sequenceService.next("wms_recipes");
			recipesCode = "CD" + StringUtils.leftPad(val + "", 6, "0");
			wmsRecipesDO.setRecipesCode(recipesCode);
			wmsRecipesDO.setIsDeleted("N");
			return recipesMapper.insert(wmsRecipesDO);
		}
	}

	@Override
	public PageResult<WmsRecipesDO> queryRecipes(WmsRecipesDO wmsRecipesDO) {
		if (wmsRecipesDO == null) {
			wmsRecipesDO = new WmsRecipesDO();
			wmsRecipesDO.setIsDeleted("N");
		}
		PageResult<WmsRecipesDO> page = new PageResult<WmsRecipesDO>();
		int totalRows = this.recipesMapper.selectCount(wmsRecipesDO);
		PageHelper.startPage(wmsRecipesDO.getPageNo(), wmsRecipesDO.getPageSize());
		PageHelper.orderBy("id desc");
		List<WmsRecipesDO> list = this.recipesMapper.select(wmsRecipesDO);
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(wmsRecipesDO.getPageNo());
		page.setPageSize(wmsRecipesDO.getPageSize());
		return page;
	}

	@Override
	public WmsRecipesDO queryRecipesByCode(String recipesCode) {
		if (StringUtils.isBlank(recipesCode)) {
			return null;
		}
		WmsRecipesDO record = new WmsRecipesDO();
		record.setRecipesCode(recipesCode);
		record.setIsDeleted("N");
		return recipesMapper.selectOne(record);
	}

	@Override
	public List<WmsRecipesFormulaDO> queryRecipesFormula(String recipesCode) {
		if (StringUtils.isBlank(recipesCode)) {
			return new ArrayList<>();
		}
		WmsRecipesFormulaDO cond = new WmsRecipesFormulaDO();
		cond.setRecipesCode(recipesCode);
		cond.setIsDeleted("N");
		List<WmsRecipesFormulaDO> list = null;
		list = TkMappers.inst().getRecipesFormulaMapper().select(cond);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}
}
