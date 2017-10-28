package com.xjh.dao.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsMenuDO extends PageDTO{
	Long id;
	String menuName;
	String menuCode;
	String menuIndex;
	Integer menuLevel;
	String parentCode;
	Integer status;
}
