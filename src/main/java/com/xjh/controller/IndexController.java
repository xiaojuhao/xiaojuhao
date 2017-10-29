package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.service.MaterialService;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

@Controller
public class IndexController {
	@Resource HttpServletRequest request;
	@Resource MaterialService materialService;
	
	@RequestMapping("/index")
	@ResponseBody
	public Object index(){
		return ResultBaseBuilder.succ().data("index page").rb(request);
	}
	
	@RequestMapping(value = "/menu", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object menu(){
		List<JSONObject> menus = new ArrayList<>();
		//index
		JSONObject index = new JSONObject();
		index.put("index", "indexPage");
		index.put("title", "首页");
		index.put("icon", "el-icon-menu");
		menus.add(index);
		//sysconfig
		JSONObject sysconfig = new JSONObject();
		sysconfig.put("index", "2");
		sysconfig.put("title", "系统管理");
		sysconfig.put("icon", "el-icon-setting");
		menus.add(sysconfig);
		
		return ResultBaseBuilder.succ().data(menus).rb(request);
	}
	
	@RequestMapping("/queryMaterials")
	@ResponseBody
	public Object queryMaterials(){
		String materialCode = request.getParameter("materialCode");
		String materialName = request.getParameter("materialName");
		WmsMaterialDO example = new WmsMaterialDO();
		List<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	
	@RequestMapping("/insertMaterialsStock")
	@ResponseBody
	public Object insertMaterialsStock(){
		String materialCode = request.getParameter("materialCode");
		String storeCode = request.getParameter("storeCode");
		WmsMaterialDO example = new WmsMaterialDO();
		List<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	
	@RequestMapping("/queryMaterialsStock")
	@ResponseBody
	public Object queryMaterialsStock(){
		String materialCode = request.getParameter("materialCode");
		String storeCode = request.getParameter("storeCode");
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		List<WmsMaterialStockVo> list = this.materialService.queryMaterialsStock(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
