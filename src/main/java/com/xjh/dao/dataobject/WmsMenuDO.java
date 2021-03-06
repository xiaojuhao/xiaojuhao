package com.xjh.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "wms_menu")
public class WmsMenuDO extends PageDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	Long id;
	String menuName;
	String menuCode;
	String type;
	String menuIcon;
	String menuIndex;
	String parentCode;
	Integer orderBy;
	Integer status;
}
