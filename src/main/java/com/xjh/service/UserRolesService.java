package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsUserRolesDO;

public interface UserRolesService {
	public List<WmsUserRolesDO> getUserRoles(String userCode);

	public List<String> getUserMenus(String userCode);
}
