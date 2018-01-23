package com.xjh.dao.dataobject;

import java.io.Serializable;
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
@Table(name = "wms_inventory_apply_detail")
public class WmsInventoryApplyDetailDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String applyNum;
	String applyType;
	String cabinCode;
	String cabinName;
	String fromCabinCode;
	String fromCabinName;
	String materialCode;
	String materialName;
	String supplierCode;
	String supplierName;
	String specCode;
	Double specAmt;
	Double realSpecAmt;
	Double transRate;
	String specUnit;
	Double specPrice;
	Double stockAmt;
	Double realStockAmt;
	Integer utilizationRatio;
	Double inStockAmt;
	String stockUnit;
	Double totalPrice;
	String remark;
	Date prodDate;
	Date expDate;
	String keepTime;//保质期，如10天
	String imgBusiNo;
	String creator;
	String modifier;
	Date gmtCreated;
	Date gmtModified;
	String status;
	String paidStatus;
	Double paidAmt;
	Double payables;
	Date paidTime;
	String paidOperator;
	String paidRemark;
	String confirmUser;
	Date confirmTime;
	
	@Transient
	String searchKey;
	@Transient
	Date startCreatedTime;
	@Transient
	Date endCreatedTime;
	@Transient
	String fromSrc;
	@Transient
	String creatorName;
	@Transient
	String paidOperatorName;
	@Transient
	String confirmUserName;
	@Transient
	List<String> mycabins;
	@Transient
	String category;
	@Transient
	List<String> statusList;
}
