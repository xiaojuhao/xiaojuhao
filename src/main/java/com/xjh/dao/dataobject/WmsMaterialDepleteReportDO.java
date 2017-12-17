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
@Table(name = "wms_material_deplete_report")
public class WmsMaterialDepleteReportDO extends PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String reportNo;
	String reportTitle;
	String cabinCode;
	String cabinName;
	String materialCode;
	String materialName;
	String stockUnit;
	Date reportTime;
	Double depleteAmt;
	Double claimLoss;
	Double claimLossRatio;
	Double saleAmt;
	Double saleAmtRatio;
	Double correctLoss;
	Double correctLossRatio;
	Double otherLoss;
	Double otherLossRatio;
	Date gmtCreated;

}
