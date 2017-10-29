package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.CommonUtils;
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
	
	@RequestMapping(value="/queryMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterials(){
		String materialCode = request.getParameter("materialCode");
		String materialName = request.getParameter("materialName");
		WmsMaterialDO example = new WmsMaterialDO();
		List<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	
	@RequestMapping(value = "/insertMaterialsStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object insertMaterialsStock(){
		String materialCode = request.getParameter("materialCode");
		String storeCode = request.getParameter("storeCode");
		WmsMaterialDO example = new WmsMaterialDO();
		List<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	
	@RequestMapping(value="/queryMaterialsStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStock(){
		String materialCode = request.getParameter("materialCode");
		String storeCode = request.getParameter("storeCode");
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		List<WmsMaterialStockVo> list = this.materialService.queryMaterialsStock(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	
	@RequestMapping(value="/queryMaterialsStockById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockById(){
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if(id == null){
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		List<WmsMaterialStockVo> list = this.materialService.queryMaterialsStock(example);
		if(list==null || list.size()==0){
			return ResultBaseBuilder.fails("数据不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(list.get(0)).rb(request);
	}
}
