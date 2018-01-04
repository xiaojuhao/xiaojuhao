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
@Table(name = "wms_material_require")
public class WmsMaterialRequireDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String cabinCode;
	String cabinName;
	String materialCode;
	String materialName;
	String materialCate;
	Date requireDate;
	String requireGroup;
	String applyNum;
	Double requireAmt;
	String purchaseType;
	String supplierCode;
	String supplierName;
	String fromCabinCode;
	String fromCabinName;
	String specCode;
	String specName;
	Double specAmt;
	String specUnit;
	Double specPrice;
	Double stockAmt;
	String stockUnit;
	Date gmtCreated;
	String creator;
	Date gmtModified;
	String modifier;
	String remark;
	String status;
}
