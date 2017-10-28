package com.xjh.dao.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMaterialStockHistoryDO extends PageDTO{
	Long id;
	String materialName;
	String materialCode;
	String opType;
	Double stockChg;
	Double currStock;
	String operator;
	String storeCode;
	String remark;
}
