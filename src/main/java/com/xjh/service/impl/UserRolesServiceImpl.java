package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsMenuDO;
import com.xjh.dao.dataobject.WmsUserRolesDO;
import com.xjh.dao.mapper.WmsUserMapper;
import com.xjh.service.TkMappers;
import com.xjh.service.UserRolesService;

@Service
public class UserRolesServiceImpl implements UserRolesService {
	@Resource
	WmsUserMapper userMapper;

	@Override
	public List<WmsUserRolesDO> getUserRoles(String userCode) {
		if (StringUtils.isBlank(userCode)) {
			return new ArrayList<>();
		}
		List<WmsUserRolesDO> list = new ArrayList<>();
		WmsUserRolesDO cond = new WmsUserRolesDO();
		cond.setUserCode(userCode);
		list = TkMappers.inst().getUserRolesMapper().select(cond);
		return list;
	}

	@Override
	public List<String> getUserMenus(String userCode) {
		if (StringUtils.isBlank(userCode)) {
			return new ArrayList<>();
		}
		List<WmsMenuDO> menus = userMapper.getUserMenus(userCode);
		List<String> list = new ArrayList<>();
		for (WmsMenuDO m : menus) {
			if (!list.contains(m.getMenuCode())) {
				list.add(m.getMenuCode());
			}
			if (!list.contains(m.getParentCode())) {
				list.add(m.getParentCode());
			}
		}
		return list;
	}

}
