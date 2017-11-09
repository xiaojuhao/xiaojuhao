package com.xjh.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.eventbus.BusCruise;
import com.xjh.eventbus.evthandles.InStockEvent;
import com.xjh.eventbus.evthandles.OutStockEvent;
import com.xjh.service.MaterialService;
import com.xjh.service.SequenceService;
import com.xjh.service.TkMappers;
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
	TkWmsStoreMapper storeMapper;
	@Resource
	TkWmsWarehouseMapper warehouseMapper;

	@Resource
	TkWmsMaterialStockHistoryMapper stockHistoryMapper;
	@Resource
	SequenceService sequenceService;

	@RequestMapping(value = "/addMaterials", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addyMaterials() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String formula = request.getParameter("formulaStr");
		WmsMaterialDO material = new WmsMaterialDO();
		material.setId(CommonUtils.getLong(request, "id"));
		material.setMaterialCode(CommonUtils.get(request, "materialCode"));
		material.setMaterialName(CommonUtils.get(request, "materialName"));
		material.setSearchKey(CommonUtils.get(request, "searchKey"));
		material.setStockUnit(CommonUtils.get(request, "stockUnit"));
		material.setCanSplit(CommonUtils.get(request, "canSplit"));
		material.setUtilizationRatio(CommonUtils.getInt(request, "utilizationRatio"));
		material.setStatus(1);

		if (StringUtils.isBlank(material.getMaterialName())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (material.getId() == null) {
			long nextVal = this.sequenceService.next("wms_material");
			String materialCode = "M" + StringUtils.leftPad(nextVal + "", 5, '0');
			material.setMaterialCode(materialCode);
			material.setStatus(1);
			this.materialService.insertMaterial(material);
		} else {
			this.materialService.updateMaterial(material);
		}
		materialService.initMaterialStock(material.getMaterialCode());
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
		String materialCode = CommonUtils.get(request, "materialCode");
		Long id = CommonUtils.getLong(request, "id");
		WmsMaterialDO example = new WmsMaterialDO();
		example.setPageNo(pageNo);
		example.setPageSize(pageSize);
		example.setMaterialCode(materialCode);
		example.setId(id);
		PageResult<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/queryMaterialById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialById() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		Long id = CommonUtils.getLong(request, "id");
		if (id == null) {
			return ResultBaseBuilder.fails("无数据").rb(request);
		}
		WmsMaterialDO example = new WmsMaterialDO();
		example.setId(id);
		PageResult<WmsMaterialVo> list = this.materialService.queryMaterials(example);
		if (list == null || list.getValues() == null || list.getValues().size() == 0) {
			return ResultBaseBuilder.fails("无数据").rb(request);
		}
		return ResultBaseBuilder.succ().data(list.getValues().get(0)).rb(request);
	}

	@RequestMapping(value = "/queryMaterialsStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMaterialsStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {

		}
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), 20);
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		String materialCode = CommonUtils.get(request, "materialCode");
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		String stockType = CommonUtils.get(request, "stockType");
		WmsMaterialStockDO example = new WmsMaterialStockDO();
		example.setId(id);
		example.setMaterialCode(materialCode);
		example.setWarehouseCode(warehouseCode);
		example.setPageSize(pageSize);
		example.setPageNo(pageNo);
		example.setStockType(stockType);
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
		if (page.getValues() == null || page.getValues().size() == 0) {
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
		String materialCode = CommonUtils.get(request, "materialCode");
		String opType = CommonUtils.get(request, "opType");
		WmsMaterialStockHistoryDO example = new WmsMaterialStockHistoryDO();
		example.setMaterialCode(materialCode);
		example.setOpType(opType);
		example.setPageSize(pageSize);
		example.setPageNo(pageNo);
		PageHelper.startPage(pageNo, pageSize);
		List<WmsMaterialStockHistoryDO> list = stockHistoryMapper.select(example);
		int totalRows = this.stockHistoryMapper.selectCount(example);
		PageResult<WmsMaterialStockHistoryDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return ResultBaseBuilder.succ().data(page).rb(request);
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
		String materialCode = CommonUtils.get(request, "materialCode");
		String outstockAmtStr = CommonUtils.get(request, "outstockAmt");
		BigDecimal outstockAmt = CommonUtils.parseBigDecimal(outstockAmtStr);
		String storeCode = CommonUtils.get(request, "storeCode");
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		if (outstockAmt == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (StringUtils.isAnyBlank(materialCode, warehouseCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 提交事件
		OutStockEvent event = new OutStockEvent();
		event.setMaterialCode(materialCode);
		event.setWarehouseCode(warehouseCode);
		event.setOutstockAmt(outstockAmt.doubleValue());
		event.setOperator(user.getUserCode());
		event.setStoreCode(storeCode);
		BusCruise.post(event);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/instock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object instock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");
		String instockAmtStr = CommonUtils.get(request, "instockAmt");
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		BigDecimal instockAmt = CommonUtils.parseBigDecimal(instockAmtStr);
		if (instockAmt == null || Math.abs(instockAmt.doubleValue()) < 0.009) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (CommonUtils.isAnyBlank(materialCode, warehouseCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		InStockEvent event = new InStockEvent();
		event.setMaterialCode(materialCode);
		event.setWarehouseCode(warehouseCode);
		event.setInstockAmt(instockAmt.doubleValue());
		event.setOperator(user.getUserCode());
		BusCruise.post(event);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/correctStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object correctStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		if (id == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		String materialCode = request.getParameter("materialCode");
		String realStockStr = request.getParameter("realStock");
		BigDecimal realStock = CommonUtils.parseBigDecimal(realStockStr);
		if (realStock == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setId(id);
		t.setMaterialCode(materialCode);
		WmsMaterialStockDO stock = stockMapper.selectOne(t);
		if (stock == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		// 更新库存
		Double prevStock = stock.getCurrStock();
		stock.setCurrStock(realStock.doubleValue());
		this.stockMapper.updateByPrimaryKeySelective(stock);
		// 记录history
		WmsMaterialStockHistoryDO history = new WmsMaterialStockHistoryDO();
		history.setMaterialCode(stock.getMaterialCode());
		history.setMaterialName(stock.getMaterialName());
		history.setCurrStock(stock.getCurrStock());
		history.setWarehouseCode(stock.getWarehouseCode());
		history.setStockChg(prevStock - realStock.doubleValue());
		history.setOpType("correct");
		history.setOperator(user.getUserCode());
		history.setGmtCreated(new Date());
		history.setRemark(String.format("库存盘点,%s=>%s", prevStock, realStock));
		this.stockHistoryMapper.insert(history);
		return ResultBaseBuilder.succ().data(stock).rb(request);
	}

	@RequestMapping(value = "/diaobo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object diaobo() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String materialCode = CommonUtils.get(request, "materialCode");// 调拨材料
		String fromWarehouseCode = CommonUtils.get(request, "fromWarehouseCode");// 拨出门店
		String toWarehouseCode = CommonUtils.get(request, "toWarehouseCode");// 拨入门店
		Double diaoboAmt = CommonUtils.getDbl(request, "diaoboAmt", null);
		if (diaoboAmt == null || CommonUtils.isAnyBlank(fromWarehouseCode, toWarehouseCode, materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsWarehouseDO fromWarehouse = new WmsWarehouseDO();
		fromWarehouse.setWarehouseCode(fromWarehouseCode);
		fromWarehouse = TkMappers.inst().getWarehouseMapper().selectOne(fromWarehouse);
		WmsWarehouseDO toWarehouse = new WmsWarehouseDO();
		toWarehouse.setWarehouseCode(toWarehouseCode);
		toWarehouse = TkMappers.inst().getWarehouseMapper().selectOne(toWarehouse);
		if (fromWarehouse == null || toWarehouse == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 出库事件
		OutStockEvent outevent = new OutStockEvent();
		outevent.setMaterialCode(materialCode);
		outevent.setWarehouseCode(fromWarehouse.getWarehouseCode());
		outevent.setOutstockAmt(diaoboAmt);
		outevent.setOperator(user.getUserCode());
		outevent.setRemark("调拨出库");
		// 入库事件
		InStockEvent inevent = new InStockEvent();
		inevent.setMaterialCode(materialCode);
		inevent.setWarehouseCode(toWarehouse.getWarehouseCode());
		inevent.setInstockAmt(diaoboAmt);
		inevent.setOperator(user.getUserCode());
		inevent.setRemark("调拨入库");
		//
		BusCruise.post(outevent, true);
		BusCruise.post(inevent, true);
		outevent.await(2000);
		inevent.await(2000);

		return ResultBaseBuilder.succ().msg("调拨成功").rb(request);
	}
}
