package com.xjh.controller;

import java.util.ArrayList;
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
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;
import com.xjh.service.SequenceService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsWarehouseMapper warehouseMapper;
	@Resource
	SequenceService sequence;

	@RequestMapping(value = "/saveWarehouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveWarehouse() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		String warehouseName = CommonUtils.get(request, "warehouseName");
		String warehouseAddr = CommonUtils.get(request, "warehouseAddr");
		String warehouseManager = CommonUtils.get(request, "warehouseManager");
		String managerPhone = CommonUtils.get(request, "managerPhone");
		String managerEmail = CommonUtils.get(request, "managerEmail");
		String relatedStoresStr = CommonUtils.get(request, "relatedStoresStr");
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		warehouse.setWarehouseCode(warehouseCode);
		warehouse.setWarehouseName(warehouseName);
		warehouse.setWarehouseAddr(warehouseAddr);
		warehouse.setWarehouseManager(warehouseManager);
		warehouse.setManagerPhone(managerPhone);
		warehouse.setManagerEmail(managerEmail);
		warehouse.setRelatedStore(relatedStoresStr);
		warehouse.setId(id);
		if (id == null) {
			long seq = sequence.next("wms_warehouse");
			warehouseCode = "WH" + StringUtils.leftPad(seq + "", 4, "0");
			warehouse.setWarehouseCode(warehouseCode);
			this.warehouseMapper.insert(warehouse);
		} else {
			this.warehouseMapper.updateByPrimaryKey(warehouse);
		}
		return ResultBaseBuilder.succ().data(warehouse).rb(request);
	}

	@RequestMapping(value = "/queryWarehouses", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryWarehouses() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		String warehouseName = CommonUtils.get(request, "warehouseName");
		String warehouseAddr = CommonUtils.get(request, "warehouseAddr");
		String warehouseManager = CommonUtils.get(request, "warehouseManager");
		String managerPhone = CommonUtils.get(request, "managerPhone");
		String managerEmail = CommonUtils.get(request, "managerEmail");
		int pageSize = CommonUtils.getPageSize(request);
		int pageNo = CommonUtils.getPageNo(request);
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		warehouse.setWarehouseCode(warehouseCode);
		warehouse.setWarehouseName(warehouseName);
		warehouse.setWarehouseAddr(warehouseAddr);
		warehouse.setWarehouseManager(warehouseManager);
		warehouse.setManagerPhone(managerPhone);
		warehouse.setManagerEmail(managerEmail);
		warehouse.setId(id);

		PageResult<WmsWarehouseDO> page = new PageResult<>();
		int totalRows = this.warehouseMapper.selectCount(warehouse);
		PageHelper.startPage(pageNo, pageSize);
		List<WmsWarehouseDO> list = this.warehouseMapper.select(warehouse);
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryWarehouseByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryWarehouseByCode() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String warehouseCode = CommonUtils.get(request, "warehouseCode");
		if (StringUtils.isBlank(warehouseCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		warehouse.setWarehouseCode(warehouseCode);
		warehouse = this.warehouseMapper.selectOne(warehouse);
		return ResultBaseBuilder.succ().data(warehouse).rb(request);
	}

	@RequestMapping(value = "/queryMyWarehouse", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyWarehouse() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		final String auths = user.getAuthWarehouse() == null ? "" : user.getAuthWarehouse();
		WmsWarehouseDO warehouse = new WmsWarehouseDO();
		List<WmsWarehouseDO> list = this.warehouseMapper.select(warehouse);
		if (!"1".equals(user.getUserRole())) {
			List<WmsWarehouseDO> list2 = new ArrayList<>();
			for (WmsWarehouseDO t : list) {
				if (auths.contains(t.getWarehouseCode())) {
					list2.add(t);
				}
			}
			list = list2;
		}
		return ResultBaseBuilder.succ().data(list).rb(request);
	}
}
