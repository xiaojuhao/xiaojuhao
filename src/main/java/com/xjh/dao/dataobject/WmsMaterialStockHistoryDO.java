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
@Table(name = "wms_material_stock_history")
public class WmsMaterialStockHistoryDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String materialName;
	String materialCode;
	String opType;
	String cabinCode;
	String cabinName;
	String cabinType;
	String keepDays;// 保质期
	Double totalPrice;
	Double unitPrice;
	Double amt;// 变化量
	Double preStock;
	Double postStock;
	String stockUnit;
	Date productDate;
	String operator;
	String remark;
	String relateCode;
	String status;
	Date gmtCreated;
	String affectStock;
}
