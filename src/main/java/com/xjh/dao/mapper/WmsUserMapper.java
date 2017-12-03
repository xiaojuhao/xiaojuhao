package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMenuDO;

public interface WmsUserMapper {
	public List<WmsMenuDO> getUserMenus(String userCode);
}
