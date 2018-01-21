package com.xjh.service.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class InventoryReportVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String materialCode;
	String materialName;
	String cabinCode;
	String cabinName;
	String supplierCode;
	String supplierName;
	String stockUnit;
	Double inventoryAmt;
	Double totalPrice;
	Double totalPaidAmt;
	
	//extra condition
	Date startCreatedDate;
	Date endCreatedDate;
	String searchKey;
	String groupBySupplier;
	String applyType;
	String status;
}
