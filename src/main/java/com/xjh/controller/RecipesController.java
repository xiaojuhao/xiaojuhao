package com.xjh.controller;

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
import com.xjh.dao.mapper.WmsRecipesMapper;
import com.xjh.service.RecipesService;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/recipes")
@Slf4j
public class RecipesController {
	@Resource
	HttpServletRequest request;
	@Resource
	RecipesService recipesService;
	@Resource
	WmsRecipesMapper wmsRecipesMapper;

	@RequestMapping(value = "/addRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String formula = request.getParameter("formulaJson");
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String recipesName = CommonUtils.get(request, "recipesName");
		String outCode = CommonUtils.get(request, "outCode");
		//菜单信息
		WmsRecipesDO recipes = new WmsRecipesDO();
		recipes.setRecipesCode(recipesCode);
		recipes.setRecipesName(recipesName);
		recipes.setOutCode(outCode);
		recipes.setStatus("1");
		//配料信息
		JSONArray arr = CommonUtils.parseJSONArray(formula);
		List<WmsRecipesFormulaDO> formulas = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			JSONObject j = arr.getJSONObject(i);
			WmsRecipesFormulaDO f = new WmsRecipesFormulaDO();
			formulas.add(f);
			f.setMaterialCode(j.getString("materialCode"));
			f.setMaterialName(j.getString("materialName"));
			f.setMaterialAmt(j.getString("materialAmt"));
			if (!CommonUtils.isDecimalOrFraction(f.getMaterialAmt())//
					|| f.getMaterialAmt().indexOf("-") >= 0) {
				return ResultBaseBuilder.fails("配料" + f.getMaterialName() + "数量不合法,请检查").rb(request);
			}
			if (CommonUtils.isDecimal(f.getMaterialAmt()) //
					&& CommonUtils.parseDouble(f.getMaterialAmt(), 0D) <= 0) {
				return ResultBaseBuilder.fails("配料" + f.getMaterialName() + "数量不合法,请检查").rb(request);
			}
			f.setMaterialUnit(j.getString("materialUnit"));
			f.setRecipesName(recipesName);
		}

		if (formulas.size() > 0) {
			recipes.setHadFormula("Y");
		} else {
			recipes.setHadFormula("N");
		}
		//保存菜单
		this.recipesService.saveRecipes(recipes);
		recipesCode = recipes.getRecipesCode();
		if (StringUtils.isBlank(recipesCode)) {
			return ResultBaseBuilder.fails("保存失败").rb(request);
		}
		//配料对象补充字段
		final String finalRecipesCode = recipesCode;
		formulas.forEach((d) -> d.setRecipesCode(finalRecipesCode));
		//先删除现有配料，再插入新记录
		WmsRecipesFormulaDO formulaExample = new WmsRecipesFormulaDO();
		formulaExample.setRecipesCode(recipesCode);
		TkMappers.inst().getRecipesFormulaMapper().delete(formulaExample);
		for (WmsRecipesFormulaDO fm : formulas) {
			fm.setIsDeleted("N");
			TkMappers.inst().getRecipesFormulaMapper().insert(fm);
		}
		//
		return ResultBaseBuilder.succ().data(recipes).rb(request);
	}

	@RequestMapping(value = "/deleteRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String recipesCode = CommonUtils.get(request, "recipesCode");
		if (StringUtils.isBlank(recipesCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRecipesDO cond = new WmsRecipesDO();
		cond.setRecipesCode(recipesCode);
		WmsRecipesDO recipes = TkMappers.inst().getRecipesMapper().selectOne(cond);
		if (recipes == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		recipes.setIsDeleted("Y");
		TkMappers.inst().getRecipesMapper().updateByPrimaryKeySelective(recipes);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/queryRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String hadFormula = CommonUtils.get(request, "hadFormula");
		String searchKey = CommonUtils.get(request, "searchKey");
		WmsRecipesDO cond = new WmsRecipesDO();
		cond.setRecipesCode(recipesCode);
		cond.setHadFormula(hadFormula);
		cond.setSearchKey(searchKey);
		cond.setIsDeleted("N");
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageResult<WmsRecipesDO> page = new PageResult<>();
		List<WmsRecipesDO> list = wmsRecipesMapper.query(cond);
		int totalRows = wmsRecipesMapper.count(cond);
		page.setPageNo(CommonUtils.getPageNo(request));
		page.setPageSize(CommonUtils.getPageSize(request));
		page.setValues(list);
		page.setTotalRows(totalRows);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryAllRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryAllRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		WmsRecipesDO wmsRecipesDO = new WmsRecipesDO();
		wmsRecipesDO.setPageSize(1000);
		wmsRecipesDO.setIsDeleted("N");
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
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String recipesCode = CommonUtils.get(request, "recipesCode");
		if (StringUtils.isBlank(recipesCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRecipesFormulaDO example = new WmsRecipesFormulaDO();
		example.setRecipesCode(recipesCode);
		example.setIsDeleted("N");
		List<WmsRecipesFormulaDO> list = TkMappers.inst().getRecipesFormulaMapper().select(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
