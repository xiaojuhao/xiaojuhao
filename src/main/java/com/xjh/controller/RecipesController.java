package com.xjh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.RecipesService;

@Controller
@RequestMapping("/recipes")
public class RecipesController {
	@Resource
	HttpServletRequest request;
	@Resource
	RecipesService recipesService;
	
	@RequestMapping(value="/addRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addStore(){
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if(user == null){
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String recipesName = CommonUtils.get(request, "recipesName");
		WmsRecipesDO recipes = new WmsRecipesDO();
		recipes.setRecipesCode(recipesCode);
		recipes.setRecipesName(recipesName);
		this.recipesService.saveRecipes(recipes);
		return ResultBaseBuilder.succ().data(recipes).rb(request);
	}
	
	
	@RequestMapping(value="/queryRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipes(){
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if(user == null){
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
	
	@RequestMapping(value="/queryRecipesByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRecipesByCode(){
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if(user == null){
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String recipesCode = CommonUtils.get(request, "recipesCode");
		WmsRecipesDO recipes = this.recipesService.queryRecipesByCode(recipesCode);
		return ResultBaseBuilder.succ().data(recipes).rb(request);
	}
}
