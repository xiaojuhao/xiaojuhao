package com.xjh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsDictDO;
import com.xjh.service.DictService;
import com.xjh.service.TkMappers;

@Service
public class DictServiceImpl implements DictService {

	@Override
	public int addDict(WmsDictDO dict) {
		return TkMappers.inst().getDictMapper().insert(dict);
	}

	@Override
	public WmsDictDO query(String group, String code) {
		if (CommonUtils.isAnyBlank(group, code)) {
			return null;
		}
		WmsDictDO cond = new WmsDictDO();
		cond.setDictGroup(group);
		cond.setDictCode(code);
		return TkMappers.inst().getDictMapper().selectOne(cond);
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
