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
			task = TaskService.reStartTask(task.getValue());
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
						.map((jsonObj) -> {
							WmsOrdersDO cond = new WmsOrdersDO();
							cond.setStoreOutCode(store.getOutCode());
							cond.setRecipesOutCode(jsonObj.getString("id"));
							cond.setSaleDate(todayDate);
							WmsOrdersDO order = TkMappers.inst().getOrdersMapper().selectOne(cond);
							//销售数据不存在，继续处理
							if (order == null){
								jsonObj.put("_hasDealed", false);
								return jsonObj;
							}
							//销售数据存在，重新处理
							if ("2".equals(order.getStatus())) {
								//如果销售数据已经处理过了，需要特别处理
								// @TODO
							}
							int saleNum = CommonUtils.parseBigDecimal(jsonObj.getString("saleNums"), ZERO).intValue();
							double allPrice = CommonUtils.parseBigDecimal(jsonObj.getString("allPrice"), ZERO)
									.doubleValue();
							//只有销售数据不等，或者销售金额不等时，才进行更新
							if (saleNum != order.getSaleNum() //
									|| Math.abs(allPrice - order.getTotalPrice()) > 0.01) {
								WmsOrdersDO update = new WmsOrdersDO();
								update.setId(order.getId());
								update.setStatus("0");
								order.setRecipesName(jsonObj.getString("name"));
								update.setSaleNum(saleNum);
								update.setTotalPrice(allPrice);
								update.setGmtModified(new Date());
								update.setRemark("重新拉取");
								TkMappers.inst().getOrdersMapper().updateByPrimaryKeySelective(update);
							}
							jsonObj.put("_hasDealed", true);
							return jsonObj;
						}).filter((o) -> !o.getBoolean("_hasDealed")) //
						.map((jsonObj) -> {
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
							return jsonObj;
						}).filter((o) -> o != null) //
						.subscribe();
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
