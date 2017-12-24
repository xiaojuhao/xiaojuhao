package com.xjh.dao.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_material_spec_detail")
public class WmsMaterialSpecDetailDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String specName;
	String specCode;
	String materialName;
	String materialCode;
	BigDecimal transRate;
	String specUnit;
	String stockUnit;
	String brandName;
	String weight;
	String homeplace;
	String isDeleted;
	String isDefault;
	Integer utilizationRatio;
}
