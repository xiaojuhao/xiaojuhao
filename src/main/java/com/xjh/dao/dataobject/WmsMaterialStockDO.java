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
@Table(name = "wms_material_stock")
public class WmsMaterialStockDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String materialName;
	String materialCode;
	Double currStock;
	String cabinCode;
	String cabinName;
	String cabinType;
	String stockUnit;
	Double unitPrice;
	Double warningValue1;
	Double warningValue2;
	Double warningStock;
	String modifier;
	Date gmtModified;
	Date gmtSetWarningStock;
	String checkOperator;
	Date checkStockTime;
	String status;
	String isDeleted;
	@Transient
	String searchKey;
	@Transient
	List<String> mycabins;
}
