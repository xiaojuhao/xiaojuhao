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
@Table(name = "wms_supplier")
public class WmsSupplierDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String supplierCode;
	String supplierName;
	String shortName;
	String supplierAddr;
	String payMode;
	String payWay;
	String payAccount;
	String bankName;
	String supplierTel;
	String supplierPhone;
	String supplierEmail;
	String status;
	String remark;
	String modifer;
	Date gmtModified;

}
