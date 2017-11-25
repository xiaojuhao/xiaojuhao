package com.xjh.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMenuDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyDetailMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyMapper;
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
	@Resource
	TkWmsInventoryApplyMapper inventoryMapper;
	@Resource
	TkWmsInventoryApplyDetailMapper inventoryDetailMapper;

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
			m.setType(r.getType());
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
				sv.setType(s.getType());
				subs.add(sv);
			}
			m.setSubs(subs);
			menus.add(m);
		}

		return ResultBaseBuilder.succ().data(menus).rb(request);
	}

	@RequestMapping(value = "/print", produces = "text/html;charset=UTF-8")
	public ModelAndView print() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		ModelAndView mv = new ModelAndView("print");
		if (user == null) {
			mv.setViewName("error");
			return mv;
		}
		String applyNum = request.getParameter("applyNum");
		if (StringUtils.isBlank(applyNum)) {
			mv.setViewName("error");
			return mv;
		}
		WmsInventoryApplyDO apply = new WmsInventoryApplyDO();
		apply.setApplyNum(applyNum);
		apply = this.inventoryMapper.selectOne(apply);
		WmsInventoryApplyDetailDO detailCond = new WmsInventoryApplyDetailDO();
		detailCond.setApplyNum(applyNum);
		detailCond.setPageSize(1000);
		List<WmsInventoryApplyDetailDO> list = this.inventoryDetailMapper.select(detailCond);
		List<JSONObject> list2 = new ArrayList<>();
		String applyType = apply.getApplyType();
		if ("purchase".equals(applyType)) {
			applyType = "采购单";
		} else if ("allocate_in".equals(applyType)) {
			applyType = "调拨入库";
		} else if ("allocate_out".equals(applyType)) {
			applyType = "调拨出库";
		} else if ("claim_loss".equals(applyType)) {
			applyType = "报损";
		}
		int index = 1;
		for (WmsInventoryApplyDetailDO dd : list) {
			JSONObject j = new JSONObject();
			j.put("sno", index++);
			j.put("cabinName", dd.getCabinName());
			j.put("materialName", dd.getMaterialName());
			j.put("specInfo", dd.getSpecAmt() + dd.getSpecUnit());
			j.put("stockInfo", dd.getStockAmt() + dd.getStockUnit());
			j.put("realStockInfo", dd.getRealStockAmt() + dd.getStockUnit());
			if ("无".equals(dd.getSpecUnit())) {
				j.put("specInfo", j.getString("stockInfo"));
			}
			j.put("totalPrice", dd.getTotalPrice());
			list2.add(j);
		}
		mv.addObject("record", apply);
		mv.addObject("list", list2);
		mv.addObject("totalRows", list.size());
		mv.addObject("createDate", new SimpleDateFormat("yyyy-MM-dd").format(apply.getGmtCreated()));
		mv.addObject("applyType", applyType);
		mv.addObject("printer", user.getUserName());
		mv.addObject("currentDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return mv;
	}

}
