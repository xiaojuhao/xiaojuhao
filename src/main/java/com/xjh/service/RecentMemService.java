package com.xjh.service;

import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsDictDO;

import tk.mybatis.mapper.entity.Example;

@Service
public class RecentMemService {
	public String recentValue(String group, String code) {
		if (CommonUtils.isAnyBlank(group, code)) {
			return null;
		}
		WmsDictDO cond = new WmsDictDO();
		cond.setDictGroup("recent_mem");
		cond.setDictCode(group + "_" + code);
		WmsDictDO dict = TkMappers.inst().getDictMapper().selectOne(cond);
		if (dict == null) {
			return null;
		}
		return dict.getDictVal();
	}

	public void saveRecent(String group, String code, String val) {
		if (CommonUtils.isAnyBlank(group, code, val)) {
			return;
		}
		Example example = new Example(WmsDictDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("dictGroup", "recent_mem");
		cri.andEqualTo("dictCode", group + "_" + code);
		WmsDictDO value = new WmsDictDO();
		value.setDictVal(val);
		int i = TkMappers.inst().getDictMapper().updateByExampleSelective(value, example);
		if (i == 0) {
			WmsDictDO insert = new WmsDictDO();
			insert.setDictCode(group + "_" + code);
			insert.setDictGroup("recent_mem");
			insert.setDictName("最近记录");
			insert.setDictVal(val);
			TkMappers.inst().getDictMapper().insert(insert);
		}
	}
}
