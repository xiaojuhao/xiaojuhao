package com.xjh.service;

import com.xjh.commons.PageResult;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

public interface MaterialService {
	public PageResult<WmsMaterialVo> queryMaterials(WmsMaterialDO example);
	
	public PageResult<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example);
	
	public int addMaterial(WmsMaterialDO example);
}
