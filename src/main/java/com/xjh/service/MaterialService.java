package com.xjh.service;

import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

public interface MaterialService {

	public ResultBase<Boolean> initMaterialStock(String materialCode, String cabinCode);

	public PageResult<WmsMaterialVo> queryMaterials(WmsMaterialDO example);

	public PageResult<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example, WmsUserDO user);

	public int insertMaterial(WmsMaterialDO example);

	public int updateMaterial(WmsMaterialDO example);

	public WmsMaterialDO getMaterialByCode(String code);
}
