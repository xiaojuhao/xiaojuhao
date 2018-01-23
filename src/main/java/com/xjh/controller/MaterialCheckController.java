package com.xjh.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialStockCheckDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockCheckMainDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockCheckMainMapper;
import com.xjh.service.MaterialCheckService;
import com.xjh.service.UserService;
import com.xjh.support.excel.CfWorkbook;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/check")
@Slf4j
public class MaterialCheckController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsMaterialStockCheckMainMapper checkMainMapper;
	@Resource
	TkWmsMaterialStockCheckDetailMapper checkDetailMapper;
	@Resource
	MaterialCheckService materialCheckService;
	@Resource
	UserService userService;

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
		try {
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
			List<WmsMaterialStockCheckDetailDO> list = checkDetailMapper.selectByExample(example);
			if (list.size() == 0) {
				return ResultBaseBuilder.fails("未修改到任何记录").rb(request);
			}
			if (list.size() > 1) {
				return ResultBaseBuilder.fails("数据错误，查到的数据超过1条").rb(request);
			}

			WmsMaterialStockCheckDetailDO value = list.get(0);
			value.setStockAmt(stockAmt);
			value.setDetail(detail);
			value.setDeltaAmt(value.getStockAmt() - value.getOriStockAmt());
			value.setStatus("1");
			int i = checkDetailMapper.updateByPrimaryKeySelective(value);
			if (i == 0) {
				return ResultBaseBuilder.fails("未修改到任何记录").rb(request);
			}
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception e) {
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/queryCheckMain", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryCheckMain() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		Date startDate = CommonUtils.getDate(request, "startDate");
		Date endDate = CommonUtils.getDate(request, "endDate");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		Example example = new Example(WmsMaterialStockCheckMainDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		if (endDate != null) {
			cri.andLessThanOrEqualTo("startTime", DateBuilder.base(endDate).futureDays(1).date());
		}
		if (startDate != null) {
			cri.andGreaterThanOrEqualTo("endTime", startDate);
		}
		cri.andEqualTo("id", id);
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("status ,start_time desc");
		List<WmsMaterialStockCheckMainDO> list = checkMainMapper.selectByExample(example);
		for (WmsMaterialStockCheckMainDO main : list) {
			main.setCheckerName(userService.getUserName(main.getChecker()));
		}
		int totalRows = this.checkMainMapper.selectCountByExample(example);
		PageResult<WmsMaterialStockCheckMainDO> page = new PageResult<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setValues(list);
		page.setTotalRows(totalRows);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryCheckDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryCheckDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long mainId = CommonUtils.getLong(request, "mainId");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String materialCode = CommonUtils.get(request, "materialCode");
		String category = CommonUtils.get(request, "category");
		String searchKey = CommonUtils.get(request, "searchKey");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		Example example = new Example(WmsMaterialStockCheckDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andEqualTo("mainId", mainId);
		cri.andEqualTo("materialCode", materialCode);
		cri.andEqualTo("category", category);
		if (StringUtils.isNotBlank(searchKey)) {
			cri.andLike("materialName", "%" + searchKey + "%");
		}
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("status desc, delta_amt desc, stock_amt desc, id");
		List<WmsMaterialStockCheckDetailDO> list = checkDetailMapper.selectByExample(example);
		int totalRows = this.checkDetailMapper.selectCountByExample(example);
		PageResult<WmsMaterialStockCheckDetailDO> page = new PageResult<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setValues(list);
		page.setTotalRows(totalRows);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/downloadCheckDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object downloadCheckDetail(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			Long mainId = CommonUtils.getLong(request, "mainId");
			String cabinCode = CommonUtils.get(request, "cabinCode");
			String materialCode = CommonUtils.get(request, "materialCode");
			String category = CommonUtils.get(request, "category");
			String searchKey = CommonUtils.get(request, "searchKey");
			Example example = new Example(WmsMaterialStockCheckDetailDO.class, false, false);
			Example.Criteria cri = example.createCriteria();
			cri.andEqualTo("cabinCode", cabinCode);
			cri.andEqualTo("mainId", mainId);
			cri.andEqualTo("materialCode", materialCode);
			cri.andEqualTo("category", category);
			if (StringUtils.isNotBlank(searchKey)) {
				cri.andLike("materialName", "%" + searchKey + "%");
			}
			PageHelper.orderBy("status desc, delta_amt desc, stock_amt desc, id");
			List<WmsMaterialStockCheckDetailDO> list = checkDetailMapper.selectByExample(example);

			//导出excel
			CfWorkbook wb = new CfWorkbook();
			CfSheet sheet = wb.newSheet("data");
			for (WmsMaterialStockCheckDetailDO dd : list) {
				CfRow row = sheet.newRow();
				row.appendEx("原料名称", dd.getMaterialName(), //
						"初始库存", dd.getOriStockAmt(), //
						"盘点库存", dd.getStockAmt(), //
						"分类", dd.getCategory(), //
						"差额", dd.getOriStockAmt() - dd.getStockAmt());
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=checkresult" + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
			wb.toHSSFWorkbook().write(response.getOutputStream());
			response.getOutputStream().close();
			return null;
		} catch (IOException e) {
			log.error("", e);
			return ResultBaseBuilder.fails(e.getMessage()).rb(request);
		}
	}
}
