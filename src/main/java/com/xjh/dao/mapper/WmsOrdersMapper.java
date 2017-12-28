package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsOrdersDO;

public interface WmsOrdersMapper {
public List<WmsOrdersDO> query(WmsOrdersDO example);
	
	public int count(WmsOrdersDO example);
}
