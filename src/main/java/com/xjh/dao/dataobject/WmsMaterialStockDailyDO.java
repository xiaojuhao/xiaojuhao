package com.xjh.dao.dataobject;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_material_stock_daily")
public class WmsMaterialStockDailyDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String materialCode;
	String materialName;
	String cabinCode;
	String cabinName;
	Date statDate;
	Double initAmt;
	Double currentStockAmt;
	Double consumeAmt;
	Double consumeAmt2;
	Double lossAmt;
	String busyDay;
	String remark;
	Date gmtCreated;
	String isDeleted;
}
