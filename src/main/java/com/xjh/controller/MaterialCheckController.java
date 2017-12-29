package com.xjh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockCheckDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockCheckMainDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckMainMapper;
import com.xjh.service.MaterialCheckService;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/check")
public class MaterialCheckController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsMaterialStockCheckMainMapper checkMainMapper;
	@Resource
	TkWmsMaterialStockCheckDetailMapper checkDetailMapper;
	@Resource
	MaterialCheckService materialCheckService;

	@RequestMapping(value = "/startCheck", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object startCheck() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = CommonUtils.get(request, "cabinCode");
		ResultBase<WmsMaterialStockCheckMainDO> rb = materialCheckService.startCheck(cabinCode, user);
		return ResultBaseBuilder.wrap(rb).rb(request);
	}

	@RequestMapping(value = "/finishCheck", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object finishCheck() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (id == null || StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialStockCheckMainDO cond = new WmsMaterialStockCheckMainDO();
		cond.setId(id);
		cond.setCabinCode(cabinCode);
		WmsMaterialStockCheckMainDO main = this.checkMainMapper.selectOne(cond);
		ResultBase<WmsMaterialStockCheckMainDO> rb = materialCheckService.finishCheck(main, user);
		return ResultBaseBuilder.wrap(rb).rb(request);
	}

	@RequestMapping(value = "/current", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object current() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		return ResultBaseBuilder.wrap(materialCheckService.current(cabinCode)).rb(request);
	}

	@RequestMapping(value = "/queryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		if (id == null || StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsMaterialStockCheckMainDO cond = new WmsMaterialStockCheckMainDO();
		cond.setId(id);
		cond.setCabinCode(cabinCode);
		WmsMaterialStockCheckMainDO main = this.checkMainMapper.selectOne(cond);
		ResultBase<List<WmsMaterialStockCheckDetailDO>> rb = materialCheckService.queryCheckDetail(main);
		return ResultBaseBuilder.wrap(rb).rb(request);
	}

	@RequestMapping(value = "/checkDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object checkDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = CommonUtils.get(request, "cabinCode");
		Long mainId = CommonUtils.getLong(request, "mainId");
		String detail = CommonUtils.get(request, "detail");
		String materialCode = CommonUtils.get(request, "materialCode");
		Double stockAmt = CommonUtils.getDbl(request, "stockAmt", null);
		if (CommonUtils.isAnyBlank(cabinCode, materialCode) || mainId == null || stockAmt == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		Example example = new Example(WmsMaterialStockCheckDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("materialCode", materialCode);
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andEqualTo("mainId", mainId);

		WmsMaterialStockCheckDetailDO value = new WmsMaterialStockCheckDetailDO();
		value.setStockAmt(stockAmt);
		value.setDetail(detail);
		value.setStatus("1");
		int i = checkDetailMapper.updateByExampleSelective(value, example);
		if (i == 0) {
			return ResultBaseBuilder.fails("未修改到任何记录").rb(request);
		}
		return ResultBaseBuilder.succ().rb(request);
	}
}
