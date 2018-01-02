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
@Table(name = "wms_payment")
public class WmsPaymentDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String payNo;
	String department;
	String departmentName;
	Date accountDate;
	Double payables;
	Double paidAmt;
	String type;
	String typeName;
	String payWay;
	String bankName;
	String depositBank;
	String bankAccount;
	String bankAccountName;
	String alipayAccount;
	String alipayAccountName;
	String weixinAccount;
	String weixinAccountName;
	String paidRemark;
	String remark;
	String status;
	String proposerCode;
	String proposerName;
	String payerCode;
	String payerName;
	String approverCode;
	String approverName;
	String isDeleted;
	Date createdTime;
	Date modifiedTime;
	Date paidTime;
	Date approvedTime;
	Integer version;
}
