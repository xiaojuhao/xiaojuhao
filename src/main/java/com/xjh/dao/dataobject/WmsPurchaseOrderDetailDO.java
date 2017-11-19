package com.xjh.dao.dataobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_purchase_order_detail")
public class WmsPurchaseOrderDetailDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	Long orderId;
	String orderNum;
	String cabinCode;
	String cabinName;
	String materialCode;
	String materialName;
	String supplierCode;
	String supplierName;
	Double specAmt;
	String specUnit;
	Double specPrice;
	Double stockAmt;
	Double realStockAmt;
	String stockUnit;
	Double totalPrice;
	String remark;
	Date prodDate;
	Date expDate;
	String keepTime;//保质期，如10天
	String creator;
	String modifier;
	Date gmtCreated;
	Date gmtModified;
}
