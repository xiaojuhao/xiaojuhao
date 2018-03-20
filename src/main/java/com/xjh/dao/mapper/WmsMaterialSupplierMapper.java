package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.WmsMaterialSupplierDO;

public interface WmsMaterialSupplierMapper {
	public List<WmsMaterialSupplierDO> query(WmsMaterialSupplierDO example);

	public int count(WmsMaterialSupplierDO example);
}
