package com.xjh.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.HttpUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsTaskDO;

import io.reactivex.Observable;

@Service
public class DiandanSystemService {
	static BigDecimal ZERO = BigDecimal.ZERO;
	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	public ResultBase<String> syncOrders(Date date) {
		if (date == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		List<WmsRecipesDO> recipes = TkMappers.inst().getRecipesMapper().select(new WmsRecipesDO());
		Map<String, WmsRecipesDO> recipesMap = new HashMap<>();
		recipes.stream().forEach((item) -> recipesMap.put(item.getOutCode(), item));
		ResultBase<String> rb = ResultBaseBuilder.succ().msg("").rb();
		List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().select(new WmsStoreDO());
		String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Date todayDate = CommonUtils.parseDate(today, "yyyy-MM-dd");
		String shortToday = today.replaceAll("-", "");
		for (WmsStoreDO store : stores) {
			//初始化任务
			ResultBase<WmsTaskDO> task = TaskService.initTask("sync_order", shortToday + "_" + store.getStoreCode(),
					"同步订单:" + store.getStoreName());
			if (task.getIsSuccess() == false) {
				rb.setMessage(rb.getMessage() + store.getStoreName() + ":" + task.getMessage());
				continue;
			}
			//启动任务
			task = TaskService.startTask(task.getValue());
			if (task.getIsSuccess() == false) {
				rb.setMessage(rb.getMessage() + store.getStoreName() + ":" + task.getMessage());
				continue;
			}
			//开始执行任务
			String nonce = CommonUtils.uuid().toLowerCase();
			String sign = CommonUtils.md5(nonce + "&key=" + api_key).toLowerCase();
			Map<String, String> params = new HashMap<>();
			params.put("nonStr", nonce);
			params.put("sign", sign);
			params.put("jsonParameter",
					CommonUtils.asJSONObject(//
							"API_TYPE", "getOrderDishesCounts", //
							"store_num", store.getOutCode(), //
							"startDate", today, //
							"endDate", today).toJSONString());
			try {
				String resp = HttpUtils.post(api_url, params);
				JSONObject json = CommonUtils.parseJSON(resp);
				if (!"0".equals(json.getString("status"))) {
					continue;
				}
				JSONArray dishesOrders = json.getJSONArray("orderDishesCounts");
				Observable.fromArray(dishesOrders.toArray()) //
						.map((o) -> (JSONObject) o) //
						.filter((jsonObj) -> jsonObj != null && jsonObj.containsKey("id")) //
						.subscribe((jsonObj) -> {
							WmsOrdersDO order = new WmsOrdersDO();
							order.setStatus("0");
							order.setGmtCreated(new Date());
							order.setGmtModified(order.getGmtCreated());
							order.setStoreOutCode(store.getOutCode());
							order.setStoreCode(store.getStoreCode());
							order.setStoreName(store.getStoreName());
							order.setRecipesOutCode(jsonObj.getString("id"));
							WmsRecipesDO reci = recipesMap.get(order.getRecipesOutCode());
							if (reci != null) {
								order.setRecipesCode(reci.getRecipesCode());
							}
							order.setRecipesName(jsonObj.getString("name"));
							String saleNum = jsonObj.getString("saleNums");
							order.setSaleNum(CommonUtils.parseBigDecimal(saleNum, ZERO).intValue());
							String allPrice = jsonObj.getString("allPrice");
							order.setTotalPrice(CommonUtils.parseBigDecimal(allPrice, ZERO).doubleValue());
							order.setSaleDate(todayDate);
							TkMappers.inst().getOrdersMapper().insert(order);
						});
				task.getValue().setRemark("同步成功");
				task = TaskService.finishTask(task.getValue());
			} catch (Exception e) {
				TaskService.onError(task.getValue());
				e.printStackTrace();
			}
		}
		if (StringUtils.isBlank(rb.getMessage())) {
			rb.setMessage("同步成功");
		}
		return rb;
	}
}
