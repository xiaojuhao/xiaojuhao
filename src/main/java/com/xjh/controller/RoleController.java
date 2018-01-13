package com.xjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMenuDO;
import com.xjh.dao.dataobject.WmsRolesDO;
import com.xjh.dao.dataobject.WmsRolesMenusDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsUserRolesDO;
import com.xjh.service.TkMappers;

import io.reactivex.Observable;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	HttpServletRequest request;

	@RequestMapping(value = "/saveRole", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveRole() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String roleName = CommonUtils.get(request, "roleName");
		String status = CommonUtils.get(request, "status");
		String menuCodes = CommonUtils.get(request, "menuCodesStr");
		String roleCode = CommonUtils.get(request, "roleCode");
		if (StringUtils.isBlank(roleName)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (id == null && roleCode == null) {
			roleCode = CommonUtils.toPinYin(roleName);
		}
		WmsRolesDO cond = new WmsRolesDO();
		cond.setRoleCode(roleCode);

		WmsRolesDO roleDO = TkMappers.inst().getRolesMapper().selectOne(cond);
		if (roleDO == null) {
			id = null;
			roleDO = new WmsRolesDO();
		} else {
			id = roleDO.getId();
		}
		roleDO.setRoleName(roleName);
		roleDO.setRoleCode(roleCode);
		roleDO.setStatus(status == null ? "1" : status);
		roleDO.setId(id);
		if (id != null) {
			TkMappers.inst().getRolesMapper().updateByPrimaryKey(roleDO);
		} else {
			TkMappers.inst().getRolesMapper().insert(roleDO);
		}

		assert roleDO.getId() != null;
		final WmsRolesDO role = roleDO;
		WmsRolesMenusDO delete = new WmsRolesMenusDO();
		delete.setRoleCode(roleDO.getRoleCode());
		TkMappers.inst().getRolesMenusMapper().delete(delete);
		if (menuCodes != null) {
			Observable.just(menuCodes) //
					.flatMap((s) -> Observable.fromArray(s.split(","))) //
					.map((menuCode) -> {
						WmsMenuDO menu = new WmsMenuDO();
						menu.setMenuCode(menuCode);
						menu = TkMappers.inst().getMenuMapper().selectOne(menu);
						if ("link".equals(menu.getType())) {
							WmsRolesMenusDO rolemenu = new WmsRolesMenusDO();
							rolemenu.setMenuCode(menuCode);
							rolemenu.setRoleName(role.getRoleName());
							rolemenu.setRoleCode(role.getRoleCode());
							TkMappers.inst().getRolesMenusMapper().insert(rolemenu);
						}
						return true;
					}).subscribe();
		}
		return ResultBaseBuilder.succ().data(roleDO).rb(request);
	}

	@RequestMapping(value = "/queryRoleById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRoleById() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		if (id == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRolesDO roleDO = new WmsRolesDO();
		roleDO.setId(id);
		roleDO = TkMappers.inst().getRolesMapper().selectOne(roleDO);
		if (roleDO == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		return ResultBaseBuilder.succ().data(roleDO).rb(request);
	}

	@RequestMapping(value = "/queryRoles", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRoles() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		WmsRolesDO cond = new WmsRolesDO();
		cond.setId(id);
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		List<WmsRolesDO> list = TkMappers.inst().getRolesMapper().select(cond);
		int totalRows = TkMappers.inst().getRolesMapper().selectCount(cond);
		PageResult<WmsRolesDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryRoleMenuByRoleId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryRoleMenuByRoleId() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long roleId = CommonUtils.getLong(request, "roleId");
		if (roleId == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsRolesDO roleDO = new WmsRolesDO();
		roleDO.setId(roleId);
		roleDO = TkMappers.inst().getRolesMapper().selectOne(roleDO);
		if (roleDO == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		WmsRolesMenusDO roleMenuDO = new WmsRolesMenusDO();
		roleMenuDO.setRoleCode(roleDO.getRoleCode());
		List<WmsRolesMenusDO> list = TkMappers.inst().getRolesMenusMapper().select(roleMenuDO);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/getUserRoles", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUserRoles() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String userCode = CommonUtils.get(request, "userCode");
		if (userCode == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserRolesDO cond = new WmsUserRolesDO();
		cond.setUserCode(userCode);
		List<WmsUserRolesDO> list = TkMappers.inst().getUserRolesMapper().select(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
