package com.xjh.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.mapper.StockReportMapper;
import com.xjh.dao.mapper.WmsInventoryApplyDetailMapper;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.vo.InventoryReportVo;
import com.xjh.service.vo.StockReportVo;
import com.xjh.support.excel.CfWorkbook;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {
	@Resource
	HttpServletRequest request;
	@Resource
	StockReportMapper stockReportMapper;
	@Resource
	WmsInventoryApplyDetailMapper inventoryDetailMapper;
	@Resource
	MaterialSpecService materialSpecService;

	@RequestMapping(value = "/stockReport", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object stockReport(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			String cabinCode = CommonUtils.get(request, "cabinCode");
			String materialCode = CommonUtils.get(request, "materialCode");
			String searchKey = CommonUtils.get(request, "searchKey");
			String startDate = CommonUtils.get(request, "startDate");
			String endDate = CommonUtils.get(request, "endDate");
			String download = CommonUtils.get(request, "download");
			if (CommonUtils.isAllBlank(cabinCode, materialCode, searchKey)) {
				return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
			}
			StockReportVo input = new StockReportVo();
			input.setCabinCode(cabinCode);
			input.setSearchKey(searchKey);
			input.setMaterialCode(materialCode);
			input.setStartDate(CommonUtils.parseDate(startDate));
			if (StringUtils.isNotBlank(endDate)) {
				input.setEndDate(DateBuilder.base(CommonUtils.parseDate(endDate)).futureDays(1).date());
			}
			List<StockReportVo> list = stockReportMapper.reportData(input);
			for (StockReportVo vo : list) {
				Double saleAmtRatio = 0D;
				Double totalAmt = vo.getSale() + vo.getInStockLoss() + vo.getClaimLoss() + vo.getCorrectDelta();
				if (Math.abs(totalAmt) > 0.01) {
					saleAmtRatio = new BigDecimal(vo.getSale() / totalAmt * 100)//
							.setScale(2, RoundingMode.CEILING).doubleValue();
				}
				vo.setCurrStockAndUnit(vo.getCurrstock() + vo.getStockUnit());
				vo.setSaleAmtRatio(saleAmtRatio);
				//将食材库存转换为第一个采购单位
				WmsMaterialSpecDetailDO spec = materialSpecService.queryFirstSpecDetail(vo.getMaterialCode());
				if (spec != null && spec.getTransRate() != null //
						&& spec.getTransRate().doubleValue() > 0.001//
						&& spec.getUtilizationRatio() > 0) {
					double specAmt = new BigDecimal(vo.getCurrstock())
							.divide(spec.getTransRate(), 2, RoundingMode.CEILING) //
							.divide(new BigDecimal(spec.getUtilizationRatio()), 2, RoundingMode.CEILING)//
							.multiply(new BigDecimal(100))//
							.setScale(2).doubleValue();
					vo.setCurrSpecAndUnit(specAmt + spec.getSpecUnit());
				}
			}
			if ("excel".equals(download)) {
				//导出excel
				CfWorkbook wb = new CfWorkbook();
				CfSheet sheet = wb.newSheet("data");
				for (StockReportVo dd : list) {
					CfRow row = sheet.newRow();
					row.appendEx("原料名称", dd.getMaterialName(), //
							"仓库名称", dd.getCabinName(), //
							"库存单位", dd.getStockUnit(), //
							"采购库存", dd.getCurrSpecAndUnit(), //
							"食材库存", dd.getCurrStockAndUnit(), //
							"入库数量", dd.getInStock(), //
							"销售数量", dd.getSale(), //
							"入库损失", dd.getInStockLoss(), //
							"报损数量", dd.getClaimLoss(), //
							"盘点损耗", dd.getCorrectDelta());
				}
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment; filename=tongji" + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
				wb.toHSSFWorkbook().write(response.getOutputStream());
				response.getOutputStream().close();
				return null;
			}
			return ResultBaseBuilder.succ().data(list).rb(request);
		} catch (IOException e) {
			log.error("", e);
			return ResultBaseBuilder.fails(e.getMessage()).rb(request);
		}
	}

	@RequestMapping(value = "/inventoryReport", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object inventoryReport(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			String materialCode = CommonUtils.get(request, "materialCode");
			String cabinCode = CommonUtils.get(request, "cabinCode");
			String supplierCode = CommonUtils.get(request, "supplierCode");
			String groupBySupplier = CommonUtils.get(request, "groupBySupplier");
			String searchKey = CommonUtils.get(request, "searchKey");
			String startDate = CommonUtils.get(request, "startDate");
			String endDate = CommonUtils.get(request, "endDate");
			String applyType = CommonUtils.get(request, "applyType");
			String download = CommonUtils.get(request, "download");
			if (StringUtils.isBlank(cabinCode)) {
				return ResultBaseBuilder.fails("仓库必输").rb(request);
			}
			InventoryReportVo input = new InventoryReportVo();
			if (StringUtils.equals("true", groupBySupplier)) {
				input.setGroupBySupplier("Y");
			}
			input.setMaterialCode(materialCode);
			input.setSupplierCode(supplierCode);
			input.setCabinCode(cabinCode);
			input.setSearchKey(searchKey);
			input.setApplyType(applyType);
			input.setStatus("2");//暂时只允许查询已入库的数据
			input.setStartCreatedDate(CommonUtils.parseDate(startDate));
			if (StringUtils.isNotBlank(endDate)) {
				Date endDateD = CommonUtils.parseDate(endDate);
				input.setEndCreatedDate(DateBuilder.base(endDateD).futureDays(1).date());
			}
			List<InventoryReportVo> list = inventoryDetailMapper.statistics(input);

			//导出excel
			if ("excel".equals(download)) {
				//导出excel
				CfWorkbook wb = new CfWorkbook();
				CfSheet sheet = wb.newSheet("data");
				for (InventoryReportVo dd : list) {
					CfRow row = sheet.newRow();
					row.appendEx("原料名称", dd.getMaterialName(), //
							"仓库名称", dd.getCabinName(), //
							"库存单位", dd.getStockUnit(), //
							"供应商", dd.getSupplierName(), //
							"采购数量", dd.getInventoryAmt() + dd.getStockUnit(), //
							"采购金额", dd.getTotalPrice() + "元", //
							"支付金额", dd.getTotalPaidAmt() + "元");
				}
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment; filename=inventory" + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
				wb.toHSSFWorkbook().write(response.getOutputStream());
				response.getOutputStream().close();
				return null;
			}
			return ResultBaseBuilder.succ().data(list).rb(request);
		} catch (

		Exception ex) {
			log.error("", ex);
			return ResultBaseBuilder.fails(ex.getMessage()).rb(request);
		}
	}
}
