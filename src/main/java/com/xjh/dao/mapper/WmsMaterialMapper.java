package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialDO;

public interface WmsMaterialMapper {
	public List<WmsMaterialDO> query(WmsMaterialDO example);

	public int count(WmsMaterialDO example);
}
