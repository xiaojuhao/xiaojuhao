package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;

@Service
public class PriceService {
	@Resource
	TkWmsMaterialSpecDetailMapper specDetailMapper;

	public void updateSpecPrice(String specCode, String cabinCode, Double price) {
		if (CommonUtils.isAnyBlank(specCode, cabinCode)) {
			return;
		}
		if (price == null || price <= 0.001) {
			return;
		}
		WmsMaterialSpecDetailDO record = new WmsMaterialSpecDetailDO();
		record.setSpecCode(specCode);
		record = specDetailMapper.selectOne(record);
		if (record == null) {
			return;
		}
		JSONObject data = CommonUtils.parseJSON(record.getPriceInfo());
		data.put(cabinCode, price);
		record.setPriceInfo(data.toJSONString());
		if(record.getBasePrice() == null || record.getBasePrice() <= 0.001){
			record.setBasePrice(price);
		}
		specDetailMapper.updateByPrimaryKeySelective(record);
	}
}
