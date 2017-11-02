package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

public interface MaterialService {
	public List<WmsMaterialVo> queryMaterials(WmsMaterialDO example);
	
	public List<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example);
	
	public int addMaterial(WmsMaterialDO example);
}
