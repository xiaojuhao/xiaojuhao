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
@Table(name = "wms_orders")
public class WmsOrdersDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String recipesName;
	String recipesOutCode;
	String recipesCode;
	Integer saleNum;
	Double totalPrice;
	String storeOutCode;
	String storeCode;
	String storeName;
	Date saleDate;
	String status;
	String handleState;
	String remark;
	Date gmtCreated;
	Date gmtModified;
	String isDeleted;
}
