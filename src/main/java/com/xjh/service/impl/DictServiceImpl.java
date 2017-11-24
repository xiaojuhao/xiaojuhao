package com.xjh.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsDictDO;
import com.xjh.service.DictService;
import com.xjh.service.TkMappers;

@Service
public class DictServiceImpl implements DictService {
	private static Cache<String, Object> cache = CacheBuilder.newBuilder() //
			.maximumSize(5000) // 最多缓存的条数
			.expireAfterWrite(5, TimeUnit.MINUTES) // 缓存时间
			.build();

	@Override
	public int addDict(WmsDictDO dict) {
		return TkMappers.inst().getDictMapper().insert(dict);
	}

	@Override
	public WmsDictDO query(String group, String code) {
		String key = "query_" + group + "_" + code;
		if (CommonUtils.isAnyBlank(group, code)) {
			return null;
		}
		WmsDictDO t = (WmsDictDO) cache.getIfPresent(key);
		if (t != null) {
			return t;
		}
		WmsDictDO cond = new WmsDictDO();
		cond.setDictGroup(group);
		cond.setDictCode(code);
		t = TkMappers.inst().getDictMapper().selectOne(cond);
		if (t != null) {
			cache.put(key, t);
		}
		return t;
	}

	@Override
	public List<WmsDictDO> queryList(WmsDictDO example) {
		if (example == null) {
			example = new WmsDictDO();
		}
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsDictDO> list = TkMappers.inst().getDictMapper().select(example);
		return list;
	}

	@Override
	public int count(WmsDictDO example) {
		if (example == null) {
			example = new WmsDictDO();
		}
		return TkMappers.inst().getDictMapper().selectCount(example);
	}

}
