package com.xjh.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMenuDO extends PageDTO{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	Long id;
	String menuName;
	String menuCode;
	String menuIndex;
	Integer menuLevel;
	String parentCode;
	Integer status;
}
