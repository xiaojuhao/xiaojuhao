package com.xjh.service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsUnitGroupDO;

public class UnitGroupService {

	public static WmsUnitGroupDO getUnitGroup(String groupCode, String unitCode) {
		if (CommonUtils.isAnyBlank(groupCode, unitCode)) {
			return null;
		}
		WmsUnitGroupDO cond = new WmsUnitGroupDO();
		cond.setGroupCode(groupCode);
		cond.setUnitCode(unitCode);
		return TkMappers.inst().getUnitGroupMapper().selectOne(cond);
	}
}
