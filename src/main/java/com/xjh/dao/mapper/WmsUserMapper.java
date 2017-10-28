package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsUserDO;

public interface WmsUserMapper {
	public int createUser(WmsUserDO userDO);
	
	public WmsUserDO selectByUserCode(String userCode);
	
	public List<WmsUserDO> selectByExample(WmsUserDO userDO);
	
	public int updateByUserCode(WmsUserDO userDO);
}
