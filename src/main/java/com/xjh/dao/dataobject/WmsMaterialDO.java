package com.xjh.dao.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMaterialDO extends PageDTO{
	Long id;
	String materialName;
	String materialCode;
	String canSplit;
	String StockUnit;
	Integer status;
}
