package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;

public interface WmsInventoryApplyDetailMapper {
	public List<WmsInventoryApplyDetailDO> query(WmsInventoryApplyDetailDO example);

	public int count(WmsInventoryApplyDetailDO example);
}
