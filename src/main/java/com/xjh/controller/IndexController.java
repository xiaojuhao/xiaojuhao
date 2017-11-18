package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMenuDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.MaterialService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;
import com.xjh.valueobject.MenuVo;

@Controller
public class IndexController {
	@Resource
	HttpServletRequest request;
	@Resource
	MaterialService materialService;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/menu", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object menu() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		List<MenuVo> menus = new ArrayList<>();
		WmsMenuDO rootCond = new WmsMenuDO();
		rootCond.setParentCode("root");
		rootCond.setStatus(1);
		List<WmsMenuDO> root = TkMappers.inst().getMenuMapper().select(rootCond);
		for (WmsMenuDO r : root) {
			if (!r.getRoles().contains(user.getUserRole()) && !r.getRoles().contains("all")) {
				continue;
			}
			MenuVo m = new MenuVo();
			m.setId(r.getId());
			m.setMenuCode(r.getMenuCode());
			m.setMenuName(r.getMenuName());
			m.setMenuIndex(r.getMenuIndex());
			m.setMenuIcon(r.getMenuIcon());
			WmsMenuDO subCond = new WmsMenuDO();
			subCond.setParentCode(r.getMenuCode());
			subCond.setStatus(1);
			List<WmsMenuDO> sub = TkMappers.inst().getMenuMapper().select(subCond);
			List<MenuVo> subs = new ArrayList<>();
			for (WmsMenuDO s : sub) {
				MenuVo sv = new MenuVo();
				sv.setId(s.getId());
				sv.setMenuCode(s.getMenuCode());
				sv.setMenuName(s.getMenuName());
				sv.setMenuIndex(s.getMenuIndex());
				sv.setMenuIcon(s.getMenuIcon());
				subs.add(sv);
			}
			m.setSubs(subs);
			menus.add(m);
		}
		
		return ResultBaseBuilder.succ().data(menus).rb(request);
	}
}
