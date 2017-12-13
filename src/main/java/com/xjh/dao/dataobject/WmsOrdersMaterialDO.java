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
@Table(name = "wms_ordersMaterial")
public class WmsOrdersMaterialDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	/**
	 * OrderMaterial 包含的元素
	 * id 原料名、原料编号、原料单位、原料使用总量、日期、门店名、门店编号、菜品名、菜品编号、销售数量
	 * */
	Long id;
	String recipesName;
	String recipesCode;
	Integer saleNum;
	String storeCode;
	String storeName;
	Date saleDate;
	String materialCode;
	String materialName;
	Double materialTotalAmt;
	String materialUnit;
}
