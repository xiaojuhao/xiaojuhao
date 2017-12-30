package com.xjh.service.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MaterialSaleStatVo {
	String storeCode;
	String materialCode;
	Date saleDate;
	Double saleAmt;

	//统计条件
	List<String> storeList;
	List<String> materialList;
	Date startDate;
	Date endDate;
}
