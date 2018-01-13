package com.xjh.service.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class StockReportVo implements Serializable{
	private static final long serialVersionUID = 1L;
	String cabinCode;
	String cabinName;
	String materialCode;
	String materialName;
	String stockUnit;
	Double currstock;
	Double sale;
	Double inStock;
	Double inStockLoss;
	Double claimLoss;
	Double correctDelta;
	
	String category;
	String searchKey;
	Date startDate;
	Date endDate;
}
