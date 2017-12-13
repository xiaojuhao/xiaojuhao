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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import tk.mybatis.mapper.entity.Example;

@Service
public class DiandanSystemService {
	static BigDecimal ZERO = BigDecimal.ZERO;
	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";

	public ResultBase<String> syncOrders(Date syncDate) {
		if (syncDate == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		List<WmsRecipesDO> recipes = TkMappers.inst().getRecipesMapper().select(new WmsRecipesDO());
		Map<String, WmsRecipesDO> recipesMap = new HashMap<>();
		recipes.stream().forEach((item) -> recipesMap.put(item.getOutCode(), item));
		ResultBase<String> rb = ResultBaseBuilder.succ().msg("").rb();
		List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().select(new WmsStoreDO());
		String syncDateStr = new SimpleDateFormat("yyyy-MM-dd").format(syncDate);
		Date saleDate = CommonUtils.parseDate(syncDateStr, "yyyy-MM-dd");
		String shortSyncDate = syncDateStr.replaceAll("-", "");
		for (WmsStoreDO store : stores) {
			//初始化任务
			ResultBase<WmsTaskDO> task = TaskService.initTask("sync_order", shortSyncDate + "_" + store.getStoreCode(),
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
			try {
				Example orderExample = new Example(WmsOrdersDO.class, false, false);
				Example.Criteria cri = orderExample.createCriteria();
				cri.andEqualTo("storeCode", store.getStoreCode());
				cri.andEqualTo("saleDate", saleDate);
				cri.andEqualTo("isDeleted", "N");
				WmsOrdersDO orderUpdate = new WmsOrdersDO();
				orderUpdate.setHandleState("0");//当日所有的数据都设置为初始态
				TkMappers.inst().getOrdersMapper().updateByExampleSelective(orderUpdate, orderExample);

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
								"startDate", syncDateStr, //
								"endDate", syncDateStr).toJSONString());

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
							cond.setSaleDate(saleDate);
							cond.setIsDeleted("N");
							WmsOrdersDO order = TkMappers.inst().getOrdersMapper().selectOne(cond);
							//销售数据不存在，继续处理
							if (order == null) {
								jsonObj.put("_hasDealed", false);
								return jsonObj;
							}
							BigDecimal saleNum = CommonUtils.parseBigDecimal(jsonObj.getString("saleNums"), ZERO);
							BigDecimal allPrice = CommonUtils.parseBigDecimal(jsonObj.getString("allPrice"), ZERO);
							//如果销售数据已经处理过了，需要特别处理
							if ("2".equals(order.getStatus())) {
								WmsOrdersDO update = new WmsOrdersDO();
								update.setId(order.getId());
								update.setIsDeleted("Y");
								update.setGmtModified(new Date());
								update.setRemark("删除记录");
								update.setHandleState("1");
								TkMappers.inst().getOrdersMapper().updateByPrimaryKeySelective(update);
								jsonObj.put("_hasDealed", false);
							} else {
								WmsOrdersDO update = new WmsOrdersDO();
								update.setId(order.getId());
								update.setStatus("1");
								update.setHandleState("1");
								order.setRecipesName(jsonObj.getString("name"));
								update.setSaleNum(saleNum.intValue());
								update.setTotalPrice(allPrice.doubleValue());
								update.setGmtModified(new Date());
								update.setRemark("重新拉取");
								TkMappers.inst().getOrdersMapper().updateByPrimaryKeySelective(update);
								jsonObj.put("_hasDealed", true);
							}
							return jsonObj;
						}).filter((o) -> !o.getBoolean("_hasDealed")) //
						.map((jsonObj) -> {
							WmsOrdersDO newDO = new WmsOrdersDO();
							newDO.setStatus("1");
							newDO.setGmtCreated(new Date());
							newDO.setGmtModified(newDO.getGmtCreated());
							newDO.setStoreOutCode(store.getOutCode());
							newDO.setStoreCode(store.getStoreCode());
							newDO.setStoreName(store.getStoreName());
							newDO.setRecipesOutCode(jsonObj.getString("id"));
							WmsRecipesDO reci = recipesMap.get(newDO.getRecipesOutCode());
							if (reci != null) {
								newDO.setRecipesCode(reci.getRecipesCode());
							}
							newDO.setRecipesName(jsonObj.getString("name"));
							String saleNum = jsonObj.getString("saleNums");
							newDO.setSaleNum(CommonUtils.parseBigDecimal(saleNum, ZERO).intValue());
							String allPrice = jsonObj.getString("allPrice");
							newDO.setTotalPrice(CommonUtils.parseBigDecimal(allPrice, ZERO).doubleValue());
							newDO.setSaleDate(saleDate);
							newDO.setHandleState("1");
							newDO.setIsDeleted("N");
							TkMappers.inst().getOrdersMapper().insert(newDO);
							return jsonObj;
						}).doOnComplete(() -> {
							//结束时，把不在返回结果里面的多余数据清理掉
							WmsOrdersDO cond = new WmsOrdersDO();
							cond.setStoreCode(store.getStoreCode());
							cond.setSaleDate(saleDate);
							cond.setHandleState("0");
							Example example = new Example(WmsOrdersDO.class, false, false);
							Example.Criteria cri2 = example.createCriteria();
							cri2.andEqualTo(cond);
							WmsOrdersDO update = new WmsOrdersDO();
							update.setIsDeleted("Y");
							update.setHandleState("1");
							TkMappers.inst().getOrdersMapper().updateByExampleSelective(update, example);
						}).subscribe();
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
