package com.xjh.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.SequenceService;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

@Controller
@RequestMapping("/busi")
public class BusinessController {
	@Resource
	HttpServletRequest request;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper stockMapper;
	@Resource
	TkWmsMaterialStockHistoryMapper stockHistoryMapper;
	@Resource
	SequenceService sequenceService;
	
	@RequestMapping(value = "/addMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addyMaterials(WmsMaterialDO material) {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		if (StringUtils.isBlank(material.getMaterialName())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		long nextVal = this.sequenceService.next("wms_material");
		String materialCode = "M" + StringUtils.leftPad(nextVal + "", 5, '0');
		material.setMaterialCode(materialCode);
		material.setStatus(1);
		this.materialService.addMaterial(material);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/queryMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		String materialCode = request.getParameter("materialCode");
		WmsMaterialDO example = new WmsMaterialDO();
		example.setPageNo(pageNo);
		example.setPageSize(pageSize);
		example.setMaterialCode(materialCode);
		PageResult<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		String materialCode = request.getParameter("materialCode");
		String storeCode = request.getParameter("storeCode");
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		example.setMaterialCode(materialCode);
		example.setStoreCode(storeCode);
		PageResult<WmsMaterialStockVo> list = this.materialService.queryMaterialsStock(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStockById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockById() {
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if (id == null) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		PageResult<WmsMaterialStockVo> page = this.materialService.queryMaterialsStock(example);
		if(page.getValues()==null || page.getValues().size()==0){
			return ResultBaseBuilder.fails("数据不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(page.getValues().get(0)).rb(request);
	}
	@RequestMapping(value = "/queryMaterialsStockHistory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStockHistory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 10);
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		String materialCode = request.getParameter("materialCode");
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setMaterialCode(materialCode);
		example.setPageSize(pageSize);
		example.setPageNo(pageNo);
		PageResult<WmsMaterialStockVo> list = this.materialService.queryMaterialsStock(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
	/**
	 * 出库
	 * 
	 * @return
	 */
	@RequestMapping(value = "/outstock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object outstock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if (id == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		String materialCode = request.getParameter("materialCode");
		String outstockAmtStr = request.getParameter("outstockAmt");
		BigDecimal outstockAmt = CommonUtils.parseBigDecimal(outstockAmtStr);
		if(outstockAmt == null){
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setId(id);
		t.setMaterialCode(materialCode);
		WmsMaterialStockDO stock = stockMapper.selectOne(t);
		if(stock == null){
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		//更新库存
		stock.setCurrStock(stock.getCurrStock()-outstockAmt.doubleValue());
		stock.setUsedStock(stock.getUsedStock()+outstockAmt.doubleValue());
		this.stockMapper.updateByPrimaryKeySelective(stock);
		//记录history
		WmsMaterialStockHistoryDO history = new WmsMaterialStockHistoryDO();
		history.setMaterialCode(stock.getMaterialCode());
		history.setMaterialName(stock.getMaterialName());
		history.setCurrStock(stock.getCurrStock());
		history.setStockChg(outstockAmt.doubleValue());
		history.setOperator("out_stock");
		history.setOperator(user.getUserCode());
		this.stockHistoryMapper.insert(history);
		return ResultBaseBuilder.succ().rb(request);
	}
}
