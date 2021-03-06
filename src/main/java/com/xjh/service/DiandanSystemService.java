package com.xjh.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class DiandanSystemService {
	static BigDecimal ZERO = BigDecimal.ZERO;
	final String api_key = "djo3ej38K23hkjsnd!Dkd";
	final String api_url = "http://www.xiaojuhao.org/baoBiaoWeb/api_handle.do";
	@Resource
	SequenceService sequenceService;

	public void initSearchKey(Date saleDate) {
		if (saleDate == null) {
			return;
		}
		WmsOrdersDO record = new WmsOrdersDO();
		record.setSaleDate(saleDate);
		List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(record);
		for (WmsOrdersDO order : orders) {
			String searchKey = order.getRecipesName() + "," + order.getRecipesCode() + "," //
					+ CommonUtils.genSearchKey(order.getRecipesName(), null)//
			;
			WmsOrdersDO update = new WmsOrdersDO();
			update.setId(order.getId());
			update.setSearchKey(searchKey);
			TkMappers.inst().getOrdersMapper().updateByPrimaryKeySelective(update);
		}
	}

	public ResultBase<String> syncOrders(Date syncDate, String storeCode) {
		try {
			log.info("同步{}订单------开始-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			ResultBase<String> rb = interalSyncOrders(syncDate, storeCode, true);
			log.info("同步{}订单------成功-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			return rb;
		} catch (Exception e) {
			log.info("同步{}订单-----失败-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			log.error("", e);
			return ResultBaseBuilder.fails("同步订单异常").rb();
		}
	}

	public ResultBase<String> syncOrders(Date syncDate,  boolean canRedo) {
		try {
			log.info("同步{}订单------开始-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			ResultBase<String> rb = interalSyncOrders(syncDate, null, canRedo);
			log.info("同步{}订单------成功-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			return rb;
		} catch (Exception e) {
			log.error("", e);
			log.info("同步{}订单-----失败-------", CommonUtils.formatDate(syncDate, "yyyyMMdd"));
			return ResultBaseBuilder.fails("同步订单异常").rb();
		}
	}

	private ResultBase<String> interalSyncOrders(Date syncDate,String storeCode, boolean canRedo) {
		if (syncDate == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		//查询菜单信息，保存到map中，供后面使用
		List<WmsRecipesDO> recipes = TkMappers.inst().getRecipesMapper().select(new WmsRecipesDO());
		Map<String, WmsRecipesDO> recipesMap = new HashMap<>();
		recipes.stream().forEach((item) -> recipesMap.put(item.getOutCode(), item));
		//
		ResultBase<String> rb = ResultBaseBuilder.succ().msg("").rb();
		String syncDateStr = new SimpleDateFormat("yyyy-MM-dd").format(syncDate);
		Date saleDate = CommonUtils.parseDate(syncDateStr, "yyyy-MM-dd");
		String shortSyncDate = syncDateStr.replaceAll("-", "");
		WmsStoreDO storeCond = new WmsStoreDO();
		if(StringUtils.isNotBlank(storeCode)){
			storeCond.setStoreCode(storeCode);
		}
		//遍历每个门店，拉取销售数据
		List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().select(storeCond);
		for (WmsStoreDO store : stores) {
			log.info("同步" + store.getStoreName() + "@" + shortSyncDate + "。。。开始。。。");
			//初始化任务
			ResultBase<WmsTaskDO> task = TaskService.initTask("sync_order", shortSyncDate + "_" + store.getStoreCode(),
					"同步订单:" + store.getStoreName());
			if (task.getIsSuccess() == false) {
				rb.setMessage(rb.getMessage() + store.getStoreName() + ":" + task.getMessage());
				continue;
			}
			//启动任务
			if (canRedo) {
				task = TaskService.reStartTask(task.getValue());
			} else {
				task = TaskService.startTask(task.getValue());
			}
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
							cond.setStoreCode(store.getStoreCode());
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
								String searchKey = order.getRecipesName() + "," + order.getRecipesCode() + "," //
										+ CommonUtils.genSearchKey(order.getRecipesName(), null)//
								;

								update.setSearchKey(StringUtils.abbreviate(searchKey, 1000));
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
							String searchKey = newDO.getRecipesName() + "," + newDO.getRecipesCode() + "," //
									+ CommonUtils.genSearchKey(newDO.getRecipesName(), null)//
							;

							newDO.setSearchKey(StringUtils.abbreviate(searchKey, 1000));
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
				log.error("", e);
			}
			log.info("同步" + store.getStoreName() + "@" + shortSyncDate + "。。。结束。。。");
		}
		if (StringUtils.isBlank(rb.getMessage())) {
			rb.setMessage("同步成功");
		}
		return rb;
	}

	public JSONObject getRecipes(String outcode) throws IOException {
		String nonce = CommonUtils.uuid().toLowerCase();
		String sign = CommonUtils.md5(nonce + "&key=" + api_key).toLowerCase();
		Map<String, String> params = new HashMap<>();
		params.put("nonStr", nonce);
		params.put("sign", sign);
		params.put("jsonParameter",
				CommonUtils.asJSONObject(//
						"API_TYPE", "getDishes", //
						"store_num", outcode//
				).toJSONString());
		String resp = HttpUtils.post(api_url, params);
		JSONObject json = CommonUtils.parseJSON(resp);
		return json;
	}

	public JSONObject getSaleOrder() throws IOException {
		String nonce = CommonUtils.uuid().toLowerCase();
		String sign = CommonUtils.md5(nonce + "&key=" + api_key).toLowerCase();
		Map<String, String> params = new HashMap<>();
		params.put("nonStr", nonce);
		params.put("sign", sign);
		params.put("jsonParameter", CommonUtils.asJSONObject("API_TYPE", "getStores").toJSONString());
		String resp = HttpUtils.post(api_url, params);
		JSONObject json = CommonUtils.parseJSON(resp);
		return json;
	}

	public void setAllRecipesDeleted() {
		WmsRecipesDO value = new WmsRecipesDO();
		value.setIsDeleted("Y");
		Example example = new Example(WmsRecipesDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("isDeleted", "N");
		TkMappers.inst().getRecipesMapper().updateByExampleSelective(value, example);
	}

	public void syncRecipes() {
		//先把所有的菜单记录都置为已删除状态，等后面同步的时候在恢复
		setAllRecipesDeleted();
		Example example = new Example(WmsStoreDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("storeCode", Arrays.asList("MD0003","MD0008","MD0012"));//只拉环球港店
		List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().selectByExample(example);
		//List<WmsStoreDO> stores =TkMappers.inst().getStoreMapper().selectAll();
		stores.forEach((store) -> {
			syncRecipes(store.getStoreCode());
		});
	}

	public void syncRecipes(String storeCode) {
		if (StringUtils.isBlank(storeCode)) {
			return;
		}
		log.info("开始同步菜单.....开始......");
		ResultBase<WmsTaskDO> task = TaskService.initTask("sync_recipes", "sync_recipes", "同步菜单");
		if (task.getIsSuccess() == false) {
			return;
		}
		task = TaskService.reStartTask(task.getValue());
		if (task.getIsSuccess() == false) {
			return;
		}
		try {
			WmsStoreDO storeCond = new WmsStoreDO();
			storeCond.setStoreCode(storeCode);
			List<WmsStoreDO> stores = TkMappers.inst().getStoreMapper().select(storeCond);
			//按部门同步菜单（api要求的，实际上可以一起拉过来的）
			stores.forEach((store) -> {
				log.info("同步{}菜单.....开始......", store.getStoreName());
				try {
					JSONObject json = getRecipes(store.getOutCode());
					JSONArray dishes = json.getJSONArray("allDishes");
					log.info(json.toString());

					if (dishes != null && dishes.size() > 0) {
						Observable.fromArray(dishes.toArray()) //
								.map((o) -> (JSONObject) o) //
								//系统按照dishes_id标识唯一的菜单
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
										recipes.setHadFormula("N");
										recipes.setSrc("auto_sync");
										recipes.setIsDeleted("N");
										recipes.setSearchKey(CommonUtils.genSearchKey(//
												recipes.getRecipesName() + "," + recipes.getRecipesType(), ""));
										TkMappers.inst().getRecipesMapper().insert(recipes);
									} else {
										WmsRecipesDO update = new WmsRecipesDO();
										update.setId(recipes.getId());
										update.setRecipesName(jsonObj.getString("dishes_name"));
										update.setIsDeleted("N");
										TkMappers.inst().getRecipesMapper().updateByPrimaryKeySelective(update);
									}
								});
					}
				} catch (Exception e) {
					log.error("", e);
				}

				log.info("同步{}菜单.....结束......", store.getStoreName());
			});
		} finally {
			TaskService.finishTask(task.getValue());
			log.info("开始同步菜单.....结束......");
		}
	}

	public void syncStores() {
		try {
			JSONObject json = getSaleOrder();
			if (!"0".equals(json.getString("status"))) {
				return;
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
			return;
		}
	}
}
