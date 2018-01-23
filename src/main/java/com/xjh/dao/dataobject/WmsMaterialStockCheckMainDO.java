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
@Table(name = "wms_material_stock_check_main")
public class WmsMaterialStockCheckMainDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String cabinName;
	String cabinCode;
	Date startTime;
	Date endTime;
	String status;
	String checker;
	String remark;
	
	@Transient
	String checkerName;
}
