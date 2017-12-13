package com.xjh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xjh.commons.HttpUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DatabaseService;
import com.xjh.service.DiandanSystemService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;

import io.reactivex.Observable;

@Controller
@RequestMapping("/remote")
public class RemoteController {
	@Resource
	HttpServletRequest request;

	@Resource
	DiandanSystemService diandanSystemService;
	@Resource
	DatabaseService database;

	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	@RequestMapping(value = "/syncStores", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncStores() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		this.diandanSystemService.syncStores();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		this.diandanSystemService.syncRecipes();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncOrders", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncOrders() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String date = CommonUtils.get(request, "date");
		Date saleDate = CommonUtils.parseDate(date, "yyyy-MM-dd");
		return ResultBaseBuilder.wrap(diandanSystemService.syncOrders(saleDate)).rb(request);
	}

	//add by wusk 2017.12.7
	@RequestMapping(value = "/updateMaterial", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object updateMaterial() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		//数据库操作需要集中处理
		List<WmsOrdersMaterialDO> ordersMaterialList = new ArrayList<>();
		List<WmsMaterialStockHistoryDO> materialStockHistoryList = new ArrayList<>();

		//1 获取Order数据,遍历记录
		WmsOrdersDO example = new WmsOrdersDO();
		example.setStatus("1");
		List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(example);
		for (WmsOrdersDO order : orders) {
			//将order中的菜品编号取出查找WmsRecipesFormulaDO表
			/**
			 * Order包含的元素:
			 * 菜品名称、菜品outCode、菜品code、销售数量、总价
			 * 门店outCode、门店名称、门店编号
			 * 销售日期、状态、remark、创建日期、更改日期
			 * */
			String recipesOutCode = order.getRecipesOutCode();
			//			Integer salesNum=order.getSaleNum();
			//根据recipeOutCode查找recipeCode
			/*			WmsRecipesDO recipes = new WmsRecipesDO();
						recipes.setOutCode(recipesOutCode);
						WmsRecipesDO recipes_result = TkMappers.inst().getRecipesMapper().selectOne(recipes);
						if(recipes_result==null)
						{
							//TODO
						}
						String recipeCode=recipes_result.getRecipesCode();
						String recipeName=recipes_result.getRecipesName();
			*/
			String recipeCode = order.getRecipesCode();
			String recipeName = order.getRecipesName();
			WmsRecipesFormulaDO recipesFormula = new WmsRecipesFormulaDO();
			recipesFormula.setRecipesCode(recipeCode);
			List<WmsRecipesFormulaDO> recipesformulas = TkMappers.inst().getRecipesFormulaMapper()
					.select(recipesFormula);
			/**
			 * WmsRecipesFormulaDO包含的元素
			 * 菜品名、菜品编号
			 * 原料名、原料编号、原料单位、原料使用量
			 * */
			for (WmsRecipesFormulaDO recipesformula : recipesformulas) {
				//根据saleNum统计原料消耗存入数据库OrderMaterial
				/**
				 * OrderMaterial 包含的元素
				 * id 原料名、原料编号、原料单位、原料使用总量、日期、门店名、门店编号、菜品名、菜品编号、销售数量
				 * */
				WmsOrdersMaterialDO ordersMaterial = new WmsOrdersMaterialDO();
				ordersMaterial.setMaterialCode(recipesformula.getMaterialCode());
				ordersMaterial.setMaterialName(recipesformula.getMaterialName());
				ordersMaterial.setMaterialUnit(recipesformula.getMaterialUnit());
				ordersMaterial.setRecipesCode(recipeCode);
				ordersMaterial.setRecipesName(recipeName);
				ordersMaterial.setSaleDate(order.getSaleDate());
				ordersMaterial.setSaleNum(order.getSaleNum());
				ordersMaterial.setStoreCode(order.getStoreCode());
				ordersMaterial.setStoreName(order.getStoreName());
				//计算总量
				double total = (double) (recipesformula.getMaterialAmt() * order.getSaleNum());
				ordersMaterial.setMaterialTotalAmt(total);
				ordersMaterialList.add(ordersMaterial);

				//同时更新WmsMaterialStockHistory
				/**
				 * 原料名称、原料编号、减少数量、库存单位、流水类型(出库)、操作人、备注
				 * 创建时间、状态(0)、库存单位、总价
				 * */
				WmsMaterialStockHistoryDO materialStockHistory = new WmsMaterialStockHistoryDO();
				materialStockHistory.setAmt(-1 * total);
				materialStockHistory.setCabinCode(order.getStoreCode());
				materialStockHistory.setCabinName(order.getStoreName());
				materialStockHistory.setCabinType("2");
				materialStockHistory.setGmtCreated(new Date());
				materialStockHistory.setMaterialCode(recipesformula.getMaterialCode());
				materialStockHistory.setMaterialName(recipesformula.getMaterialName());
				materialStockHistory.setOperator(user.getUserCode());
				materialStockHistory.setOpType("出库");
				materialStockHistory.setStatus("0");
				materialStockHistory.setStockUnit(recipesformula.getMaterialUnit());
				materialStockHistory.setTotalPrice(order.getTotalPrice());
				materialStockHistoryList.add(materialStockHistory);
			}
			//同步完成后将Status改为"2"
			order.setStatus("2");
		}
		//集中处理数据库操作
		//		this.database.salesCommit(orders, ordersMaterialList, materialStockHistoryList);
		for (WmsOrdersDO order : orders) {
			TkMappers.inst().getOrdersMapper().updateByPrimaryKey(order);
		}
		// 2.
		for (WmsOrdersMaterialDO ordersMaterial : ordersMaterialList) {
			TkMappers.inst().getOrdersMaterialMapper().insert(ordersMaterial);
		}
		// 3.
		for (WmsMaterialStockHistoryDO insert : materialStockHistoryList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
		/*		
				//获取当前日期
				Date day=new Date();
				String today = new SimpleDateFormat("yyyy-MM-dd").format(day);
				//获取前一天日期
				Calendar c = Calendar.getInstance();
				c.setTime(day); 
				int Day=c.get(Calendar.DATE); 
				c.set(Calendar.DATE,Day-1); 
				String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		*/
		return ResultBaseBuilder.succ().rb(request);
	}
}
