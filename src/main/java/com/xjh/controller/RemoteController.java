package com.xjh.controller;

import java.io.IOException;
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
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
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
	SequenceService sequenceService;
	@Resource
	DiandanSystemService diandanSystemService;

	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	@RequestMapping(value = "/syncStores", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncStores() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String nonce = CommonUtils.uuid().toLowerCase();
		String sign = CommonUtils.md5(nonce + "&key=" + api_key).toLowerCase();
		Map<String, String> params = new HashMap<>();
		params.put("nonStr", nonce);
		params.put("sign", sign);
		params.put("jsonParameter", CommonUtils.asJSONObject("API_TYPE", "getStores").toJSONString());
		try {
			String resp = HttpUtils.post(api_url, params);
			JSONObject json = CommonUtils.parseJSON(resp);
			if (!"0".equals(json.getString("status"))) {
				return ResultBaseBuilder.fails(ResultCode.error).msg(json.getString("message")).rb(request);
			}
			JSONArray stores = json.getJSONArray("allStore");
			Observable.fromArray(stores.toArray()) //
					.map((o) -> (JSONObject) o) //
					.filter((jsonObj) -> jsonObj != null && jsonObj.containsKey("store_num")) //
					.subscribe((jsonObj) -> {
						WmsStoreDO cond = new WmsStoreDO();
						cond.setOutCode(jsonObj.getString("store_num"));
						WmsStoreDO store = TkMappers.inst().getStoreMapper().selectOne(cond);
						if (store == null) {
							long val = sequenceService.next("wms_store");
							String storeCode = "MD" + StringUtils.leftPad(val + "", 4, "0");
							store = new WmsStoreDO();
							store.setStoreName(jsonObj.getString("store_name"));
							store.setOutCode(jsonObj.getString("store_num"));
							store.setStoreCode(storeCode);
							store.setStatus("1");
							TkMappers.inst().getStoreMapper().insert(store);
						} else {
							WmsStoreDO update = new WmsStoreDO();
							update.setId(store.getId());
							update.setStoreName(jsonObj.getString("store_num"));
							TkMappers.inst().getStoreMapper().updateByPrimaryKeySelective(update);
						}
					});
		} catch (IOException e) {
			return ResultBaseBuilder.fails(ResultCode.error).msg(e.getMessage()).rb(request);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/syncRecipes", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object syncRecipes() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().select(new WmsStoreDO());

		for (WmsStoreDO store : stores) {

			String nonce = CommonUtils.uuid().toLowerCase();
			String sign = CommonUtils.md5(nonce + "&key=" + api_key).toLowerCase();
			Map<String, String> params = new HashMap<>();
			params.put("nonStr", nonce);
			params.put("sign", sign);
			params.put("jsonParameter",
					CommonUtils.asJSONObject(//
							"API_TYPE", "getDishes", //
							"store_num", store.getOutCode()//
					).toJSONString());
			try {
				String resp = HttpUtils.post(api_url, params);
				JSONObject json = CommonUtils.parseJSON(resp);
				if (!"0".equals(json.getString("status"))) {
					//return ResultBaseBuilder.fails(ResultCode.error).msg(json.getString("message")).rb(request);
					continue;
				}
				JSONArray dishes = json.getJSONArray("allDishes");
				Observable.fromArray(dishes.toArray()) //
						.map((o) -> (JSONObject) o) //
						.filter((jsonObj) -> jsonObj != null && jsonObj.containsKey("dishes_id")) //
						.subscribe((jsonObj) -> {
							WmsRecipesDO cond = new WmsRecipesDO();
							cond.setOutCode(jsonObj.getString("dishes_id"));
							WmsRecipesDO recipes = TkMappers.inst().getRecipesMapper().selectOne(cond);
							if (recipes == null) {
								long val = sequenceService.next("wms_recipes");
								String recipesCode = "CD" + StringUtils.leftPad(val + "", 6, "0");
								recipes = new WmsRecipesDO();
								recipes.setRecipesCode(recipesCode);
								recipes.setOutCode(jsonObj.getString("dishes_id"));
								recipes.setRecipesName(jsonObj.getString("dishes_name"));
								recipes.setStoreCode("000000");
								recipes.setRecipesType(jsonObj.getString("dishes_type_name"));
								recipes.setStatus("1");
								recipes.setSrc("auto_sync");
								recipes.setSearchKey(CommonUtils.genSearchKey(//
										recipes.getRecipesName() + "," + recipes.getRecipesType(), ""));
								TkMappers.inst().getRecipesMapper().insert(recipes);
							} else {
								WmsRecipesDO update = new WmsRecipesDO();
								update.setId(recipes.getId());
								update.setRecipesName(jsonObj.getString("dishes_name"));
								TkMappers.inst().getRecipesMapper().updateByPrimaryKeySelective(update);
							}
						});

			} catch (IOException e) {
				return ResultBaseBuilder.fails(ResultCode.error).msg(e.getMessage()).rb(request);
			}
		}
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
}
