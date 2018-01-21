package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.service.vo.InventoryReportVo;

public interface WmsInventoryApplyDetailMapper {
	public List<WmsInventoryApplyDetailDO> query(WmsInventoryApplyDetailDO example);

	public int count(WmsInventoryApplyDetailDO example);
	
	public List<InventoryReportVo> statistics(InventoryReportVo input);
}
