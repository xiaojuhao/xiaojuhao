package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsDictDO;

public interface DictService {
	public int addDict(WmsDictDO dict);
	
	public WmsDictDO query(String group,String code);
	
	public List<WmsDictDO> queryList(WmsDictDO example);
	
	public int count(WmsDictDO example);
}
