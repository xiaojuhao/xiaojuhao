package com.xjh.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsRecipesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.CabinService;
import com.xjh.service.RecipesService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.CabinVo;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {
	@Resource
	HttpServletRequest request;
	@Resource
	RecipesService recipesService;
	@Resource
	CabinService cabinService;

	@RequestMapping(value = "/lastSevenDaysSaleData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object lastSevenDaysSaleData() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		String recipesCode = CommonUtils.get(request, "recipesCode");
		String storeCode = CommonUtils.get(request, "storeCode");
		if (CommonUtils.isAnyBlank(recipesCode, storeCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRecipesDO recipes = this.recipesService.queryRecipesByCode(recipesCode);
		CabinVo cabin = this.cabinService.getCabinByCode(storeCode);
		if (recipes == null || cabin == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Example example = new Example(WmsOrdersDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("recipesCode", recipesCode);
		cri.andEqualTo("storeCode", storeCode);
		cri.andEqualTo("isDeleted", "N");
		Date time = CommonUtils.futureDays(new Date(), -8);
		cri.andGreaterThanOrEqualTo("saleDate", time);
		List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().selectByExample(example);
		Map<String, Integer> dailySaleNum = new HashMap<>();
		Observable.fromIterable(orders) //
				.forEach((o) -> {
					dailySaleNum.put(sdf.format(o.getSaleDate()), o.getSaleNum());
				});
		List<Integer> saleNums = new ArrayList<>();
		List<String> saleDays = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		for (int i = 0; i <= 7; i++) {
			String day = sdf.format(c.getTime());
			Integer num = dailySaleNum.get(day);
			if (num == null) {
				num = 0;
			}
			saleNums.add(num);
			saleDays.add(day);
			c.add(Calendar.DATE, 1);
		}
		JSONObject ret = new JSONObject();
		ret.put("saleNums", saleNums);
		ret.put("saleDays", saleDays);
		ret.put("storeName", cabin.getName());
		ret.put("recipesName", recipes.getRecipesName());
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}
}
