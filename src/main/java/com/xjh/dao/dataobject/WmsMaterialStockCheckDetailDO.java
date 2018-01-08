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
@Table(name = "wms_material_stock_check_detail")
public class WmsMaterialStockCheckDetailDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	Long mainId;
	String cabinCode;
	String cabinName;
	String materialCode;
	String materialName;
	String category;
	String stockUnit;
	Double oriStockAmt;
	Double stockAmt;
	Double deltaAmt;
	String status;
	String detail;
	String remark;
}
