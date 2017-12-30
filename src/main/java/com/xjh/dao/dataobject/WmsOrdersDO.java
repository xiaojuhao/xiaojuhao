package com.xjh.dao.dataobject;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	String searchKey;
	
	@Transient
	Date saleDateStart;
	@Transient
	Date saleDateEnd;
	@Transient
	List<String> mystores;
	
}
