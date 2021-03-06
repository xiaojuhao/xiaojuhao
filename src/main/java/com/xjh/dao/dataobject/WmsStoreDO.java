package com.xjh.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="wms_store")
public class WmsStoreDO extends PageDTO{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	Long id;
	String storeCode;
	String storeName;
	String storeAddr;
	String storeManager;
	String managerPhone;
	String managerEmail;
	String outCode;
	String status;
	String storeImg;
	String supplierCodes;
}
