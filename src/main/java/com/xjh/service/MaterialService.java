package com.xjh.service;

import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsUserDO;

public interface MaterialService {

	public ResultBase<WmsMaterialStockDO> initMaterialStock(String materialCode, String cabinCode);

	public PageResult<WmsMaterialDO> queryMaterials(WmsMaterialDO example);

	public PageResult<WmsMaterialStockDO> queryMaterialsStock(WmsMaterialStockDO example, WmsUserDO user);

	public int insertMaterial(WmsMaterialDO example);

	public int updateMaterial(WmsMaterialDO example);

	public WmsMaterialDO queryMaterialByCode(String code);
}
