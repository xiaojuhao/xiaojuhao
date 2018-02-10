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
@Table(name = "wms_warehouse")
public class WmsWarehouseDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String warehouseCode;
	String warehouseName;
	String warehouseManager;
	String warehouseAddr;
	String managerPhone;
	String managerEmail;
	String relatedStore;
	String status;
	String headImg;
}
