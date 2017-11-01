package com.xjh.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMaterialStockHistoryDO extends PageDTO{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
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
