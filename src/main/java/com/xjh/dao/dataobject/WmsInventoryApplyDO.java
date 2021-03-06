package com.xjh.dao.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_inventory_apply")
public class WmsInventoryApplyDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String applyNum;
	String cabinCode;
	String cabinName;
	String fromCabinCode;
	String fromCabinName;
	String applyType;
	String serialNo;
	String proposer;
	String supplierCode;
	String supplierName;
	String status;
	String paidStatus;
	Double totalPrice;
	Double payables;
	Double paidAmt;
	String paidWay;
	String paytoBank;
	String paytoAccount;
	String paytoAccountName;
	String paidOperator;
	String paidRemark;
	Date paidTime;
	String remark;
	String creator;
	String modifier;
	Date gmtCreated;
	Date gmtModified;
	
	@Transient
	String proposerName;

}
