package com.xjh.dao.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WmsUserDO extends PageDTO{
	Long id;
	String userCode;
	String userName;
	String userMobile;
	String storeCode;
	String userRole;
	String password;
}
