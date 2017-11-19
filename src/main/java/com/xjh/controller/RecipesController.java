package com.xjh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.RecipesService;
import com.xjh.service.TkMappers;

@Controller
@RequestMapping("/recipes")
public class RecipesController {
	@Resource
	HttpServletRequest request;
	@Resource
	RecipesService recipesService;

	@RequestMapping(value = "/addRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String formula = request.getParameter("formulaJson");
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String recipesName = CommonUtils.get(request, "recipesName");
		String outCode = CommonUtils.get(request, "outCode");
		WmsRecipesDO recipes = new WmsRecipesDO();
		recipes.setRecipesCode(recipesCode);
		recipes.setRecipesName(recipesName);
		recipes.setOutCode(outCode);
		this.recipesService.saveRecipes(recipes);
		//
		recipesCode = recipes.getRecipesCode();
		if (StringUtils.isBlank(recipesCode)) {
			return ResultBaseBuilder.fails("保存失败").rb(request);
		}
		JSONArray arr = CommonUtils.parseJSONArray(formula);
		List<WmsRecipesFormulaDO> formulas = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			JSONObject j = arr.getJSONObject(i);
			WmsRecipesFormulaDO f = new WmsRecipesFormulaDO();
			formulas.add(f);
			f.setMaterialCode(j.getString("materialCode"));
			f.setMaterialName(j.getString("materialName"));
			f.setMaterialAmt(CommonUtils.parseDouble(j.getString("materialAmt"), 0D));
			f.setMaterialUnit(j.getString("materialUnit"));
			f.setRecipesCode(recipesCode);
			f.setRecipesName(recipesName);
		}
		// 先删后插
		WmsRecipesFormulaDO formulaExample = new WmsRecipesFormulaDO();
		formulaExample.setRecipesCode(recipesCode);
		TkMappers.inst().getRecipesFormulaMapper().delete(formulaExample);
		for (WmsRecipesFormulaDO fm : formulas) {
			TkMappers.inst().getRecipesFormulaMapper().insert(fm);
		}
		//
		return ResultBaseBuilder.succ().data(recipes).rb(request);
	}

	@RequestMapping(value = "/queryRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String recipesCode = CommonUtils.get(request, "recipesCode");
		WmsRecipesDO wmsRecipesDO = new WmsRecipesDO();
		wmsRecipesDO.setRecipesCode(recipesCode);
		wmsRecipesDO.setPageNo(CommonUtils.getPageNo(request));
		wmsRecipesDO.setPageSize(CommonUtils.getPageSize(request));
		PageResult<WmsRecipesDO> page = recipesService.queryRecipes(wmsRecipesDO);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryAllRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryAllRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsRecipesDO wmsRecipesDO = new WmsRecipesDO();
		wmsRecipesDO.setPageSize(1000);
		PageResult<WmsRecipesDO> page = recipesService.queryRecipes(wmsRecipesDO);
		return ResultBaseBuilder.succ().data(page.getValues()).rb(request);
	}

	@RequestMapping(value = "/queryRecipesByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipesByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String recipesCode = CommonUtils.get(request, "recipesCode");
		WmsRecipesDO recipes = this.recipesService.queryRecipesByCode(recipesCode);
		return ResultBaseBuilder.succ().data(recipes).rb(request);
	}

	@RequestMapping(value = "/queryRecipesFormula", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipesFormula() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String recipesCode = CommonUtils.get(request, "recipesCode");
		if (StringUtils.isBlank(recipesCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRecipesFormulaDO example = new WmsRecipesFormulaDO();
		example.setRecipesCode(recipesCode);
		List<WmsRecipesFormulaDO> list = TkMappers.inst().getRecipesFormulaMapper().select(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
