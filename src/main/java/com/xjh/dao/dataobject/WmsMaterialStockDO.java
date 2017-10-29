package com.xjh.dao.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMaterialStockDO extends PageDTO{
	Long id;
	String materialName;
	String materialCode;
	Double currStock;
	Double usedStock;
	String stockType;
	String stockUnit;
	String modifier;
}
