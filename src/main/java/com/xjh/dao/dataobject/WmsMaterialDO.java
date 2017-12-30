package com.xjh.dao.dataobject;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_material")
public class WmsMaterialDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String specGroup;
	String materialName;
	String materialCode;
	String category;
	String specUnit;
	Double specQty;
	String stockUnit;
	Integer status;
	String searchKey;
	Integer utilizationRatio;
	String storageLife;
	Integer orderBy;
	String isDeleted;
}
