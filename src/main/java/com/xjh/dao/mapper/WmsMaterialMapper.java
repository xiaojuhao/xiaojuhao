package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialDO;

public interface WmsMaterialMapper {
	public List<WmsMaterialDO> selectByExample(WmsMaterialDO example);
}
