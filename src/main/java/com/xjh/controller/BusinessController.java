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
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
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
		WmsMaterialDO material = new WmsMaterialDO();
		material.setId(CommonUtils.getLong(request, "id"));
		material.setMaterialCode(CommonUtils.get(request, "materialCode"));
		material.setMaterialName(CommonUtils.get(request, "materialName"));
		material.setSearchKey(CommonUtils.get(request, "searchKey"));
		material.setStockUnit(CommonUtils.get(request, "stockUnit"));
		material.setStatus(1);

		if (StringUtils.isBlank(material.getMaterialName())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		String boolCanSplit = CommonUtils.get(request, "boolCanSplit");
		if ("true".equals(boolCanSplit)) {
			material.setCanSplit("Y");
		} else {
			material.setCanSplit("N");
		}
		if (material.getId() == null) {
			long nextVal = this.sequenceService.next("wms_material");
			String materialCode = "M" + StringUtils.leftPad(nextVal + "", 5, '0');
			material.setMaterialCode(materialCode);
			material.setStatus(1);
			this.materialService.addMaterial(material);
		} else {
			this.materialService.updateMaterial(material);
		}
		materialService.initMaterials();
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
		String materialCode = CommonUtils.get(request, "materialCode");
		String outstockAmtStr = CommonUtils.get(request, "outstockAmt");
		BigDecimal outstockAmt = CommonUtils.parseBigDecimal(outstockAmtStr);
		String storeCode = CommonUtils.get(request, "storeCode");
		if (outstockAmt == null) {
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
		stock.setCurrStock(stock.getCurrStock() - outstockAmt.doubleValue());
		stock.setUsedStock(stock.getUsedStock() + outstockAmt.doubleValue());
		stock.setModifier(user.getUserCode());
		this.stockMapper.updateByPrimaryKeySelective(stock);
		// 记录history
		WmsMaterialStockHistoryDO history = new WmsMaterialStockHistoryDO();
		history.setMaterialCode(stock.getMaterialCode());
		history.setMaterialName(stock.getMaterialName());
		history.setCurrStock(stock.getCurrStock());
		history.setWarehouseCode(stock.getWarehouseCode());
		history.setStockChg(outstockAmt.doubleValue());
		history.setOpType("out_stock");
		history.setRemark("出库");
		history.setStoreCode(storeCode);
		history.setOperator(user.getUserCode());
		this.stockHistoryMapper.insert(history);
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
		WmsMaterialDO material = new WmsMaterialDO();
		material.setMaterialCode(materialCode);
		material = tkWmsMaterialMapper.selectOne(material);
		if (material == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		warehouse.setWarehouseCode(warehouseCode);
		warehouse = this.warehouseMapper.selectOne(warehouse);
		if (warehouse == null) {
			return ResultBaseBuilder.fails("仓库信息缺失").rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setMaterialCode(materialCode);
		t.setWarehouseCode(warehouseCode);
		t.setStockType("2");
		WmsMaterialStockDO stock = stockMapper.selectOne(t);
		if (stock == null) {
			// return
			// ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
			t.setMaterialName(material.getMaterialName());
			t.setWarehouseName(warehouse.getWarehouseName());
			t.setCurrStock(0D);
			t.setUsedStock(0D);
			stockMapper.insert(t);
			stock = t;
		}
		WmsMaterialStockDO zk = new WmsMaterialStockDO();
		zk.setMaterialCode(materialCode);
		zk.setStockType("1");
		zk.setWarehouseCode("WH0000");
		zk.setWarehouseName("总库");
		zk = this.stockMapper.selectOne(zk);
		zk.setCurrStock(zk.getCurrStock() + instockAmt.doubleValue());
		zk.setModifier(user.getUserCode());
		this.stockMapper.updateByPrimaryKey(zk);
		// 更新库存（分库）
		stock.setCurrStock(stock.getCurrStock() + instockAmt.doubleValue());
		stock.setModifier(user.getUserCode());
		this.stockMapper.updateByPrimaryKeySelective(stock);
		// 记录history
		WmsMaterialStockHistoryDO history = new WmsMaterialStockHistoryDO();
		history.setMaterialCode(stock.getMaterialCode());
		history.setMaterialName(stock.getMaterialName());
		history.setCurrStock(stock.getCurrStock());
		history.setWarehouseCode(warehouseCode);
		history.setStockChg(-instockAmt.doubleValue());
		history.setOpType("in_stock");
		history.setRemark("入库");
		history.setOperator(user.getUserCode());
		this.stockHistoryMapper.insert(history);
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
		String storeCode = CommonUtils.get(request, "storeCode");// 拨出门店
		String toStoreCode = CommonUtils.get(request, "toStoreCode");// 拨入门店
		Double diaoboAmt = CommonUtils.getDbl(request, "diaoboAmt", null);

		return ResultBaseBuilder.succ().msg("调拨成功").rb(request);
	}
}
