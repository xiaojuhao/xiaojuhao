package com.xjh.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="wms_material_supplier")
public class WmsMaterialSupplierDO extends PageDTO{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	Long id;
	String materialCode;
	String materialName;
	String supplierCode;
	String supplierName;
	String isDeleted;
	
	@Transient
	String shortName;
	
}
