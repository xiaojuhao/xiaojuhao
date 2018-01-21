package com.xjh.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.commons.TaskUtils;
import com.xjh.dao.dataobject.WmsMaterialRequireDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialRequireMapper;
import com.xjh.service.CabinService;
import com.xjh.service.LockService;
import com.xjh.service.MaterialRequireService;
import com.xjh.service.MaterialService;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.MaterialStockService;
import com.xjh.service.PriceService;
import com.xjh.service.RecentMemService;
import com.xjh.service.TkMappers;
import com.xjh.support.excel.CfWorkbook;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/require")
@Slf4j
public class MaterialRequireController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsMaterialRequireMapper requireMapper;
	@Resource
	CabinService cabinService;
	@Resource
	MaterialService materialService;
	@Resource
	MaterialSpecService materialSpecService;
	@Resource
	MaterialRequireService materialRequireService;
	@Resource
	PriceService priceService;
	@Resource
	LockService lockService;
	@Resource
	MaterialStockService stockService;
	@Resource
	RecentMemService recentMemService;

	@RequestMapping(value = "/cancelRequire", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object cancelRequire() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String items = CommonUtils.get(request, "items");
		if (StringUtils.isBlank(items)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		List<Long> idList = new ArrayList<>();
		JSONArray itemArr = CommonUtils.parseJSONArray(items);
		for (int i = 0; i < itemArr.size(); i++) {
			JSONObject item = itemArr.getJSONObject(i);
			Long id = CommonUtils.parseLong(item.getString("id"), null);
			idList.add(id);
		}
		Example example = new Example(WmsMaterialRequireDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("id", idList);
		WmsMaterialRequireDO val = new WmsMaterialRequireDO();
		val.setStatus("2");
		val.setRemark("取消需求");
		val.setModifier(user.getUserCode());
		val.setGmtModified(new Date());
		requireMapper.updateByExampleSelective(val, example);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/handleRequire", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object handleRequire() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String requires = CommonUtils.get(request, "requires");
		String handleType = CommonUtils.get(request, "handleType");
		JSONArray jsonArr = CommonUtils.parseJSONArray(requires);
		if (jsonArr.size() == 0) {
			return ResultBaseBuilder.fails("请选择记录").rb(request);
		}
		List<Long> requireIds = new ArrayList<>();
		List<WmsMaterialRequireDO> list = new ArrayList<>();
		//保存信息
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject json = jsonArr.getJSONObject(i);
			WmsMaterialRequireDO dd = new WmsMaterialRequireDO();
			dd.setId(CommonUtils.parseLong(json.getString("id"), null));
			if (dd.getId() == null) {
				return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
			}
			dd = requireMapper.selectByPrimaryKey(dd.getId());
			if (dd == null) {
				return ResultBaseBuilder.fails("记录不存在" + json.getString("id")).rb(request);
			}
			requireIds.add(dd.getId());
			dd.setSpecCode(json.getString("specCode"));
			dd.setSpecName(json.getString("specName"));
			dd.setSupplierCode(json.getString("supplierCode"));
			dd.setSupplierName(json.getString("supplierName"));
			dd.setSpecAmt(CommonUtils.parseDouble(json.getString("specAmt"), null));
			dd.setSpecUnit(json.getString("specUnit"));
			dd.setSpecPrice(CommonUtils.parseDouble(json.getString("specPrice"), null));
			dd.setPurchaseType(json.getString("purchaseType"));
			dd.setFromCabinCode(json.getString("fromCabinCode"));
			dd.setFromCabinName(json.getString("fromCabinName"));
			dd.setGmtModified(new Date());
			dd.setModifier(user.getUserCode());
			if (dd.getSpecAmt() != null && StringUtils.isNotBlank(dd.getSpecCode())) {
				WmsMaterialSpecDetailDO spec = materialSpecService.querySpecDetailByCode( //
						dd.getMaterialCode(), dd.getSpecCode());
				if (spec != null) {
					dd.setStockAmt(dd.getSpecAmt() * spec.getTransRate().doubleValue());
					dd.setStockUnit(spec.getStockUnit());
				}
			}
			if (dd.getId() == null) {
				return ResultBaseBuilder.fails("入参错误:缺少ID字段").rb(request);
			}
			list.add(dd);
			requireMapper.updateByPrimaryKeySelective(dd);
			WmsMaterialRequireDO ddd = dd;
			TaskUtils.schedule(() -> {
				priceService.updateSpecPrice(ddd.getSpecCode(), ddd.getCabinCode(), ddd.getSpecPrice());
				recentMemService.saveRecent(ddd.getCabinCode(), ddd.getMaterialCode(), ddd.getSupplierCode());

			});
		}
		//生成采购单
		if ("2".equals(handleType)) {
			ResultBase<String> rb = materialRequireService.generateApply(requireIds, user);
			if (rb.getIsSuccess() == false) {
				return ResultBaseBuilder.wrap(rb).rb(request);
			}
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/query", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object query() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}

		String cabinCode = CommonUtils.get(request, "cabinCode");
		String startDate = CommonUtils.get(request, "startDate");
		String endDate = CommonUtils.get(request, "endDate");
		String materialCode = CommonUtils.get(request, "materialCode");
		String status = CommonUtils.get(request, "status");
		String searchKey = CommonUtils.get(request, "searchKey");
		String category = CommonUtils.get(request, "category");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);

		Example example = new Example(WmsMaterialRequireDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("cabinCode", cabinCode);
		cri.andEqualTo("materialCode", materialCode);
		cri.andEqualTo("status", status);
		cri.andEqualTo("materialCate", category);
		cri.andGreaterThanOrEqualTo("requireDate", CommonUtils.parseDate(startDate));
		cri.andLessThanOrEqualTo("requireDate", CommonUtils.parseDate(endDate));
		if (StringUtils.isNotBlank(searchKey)) {
			cri.andLike("materialName", "%" + searchKey.replaceAll("'", "").replaceAll("=", "") + "%");
		}
		if (!"1".equals(user.getIsSu())) {
			List<String> mycabins = new ArrayList<>();
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
			cri.andIn("cabinCode", mycabins);
		}
		PageHelper.orderBy("require_date desc, require_amt desc,id desc");
		PageHelper.startPage(pageNo, pageSize);
		List<WmsMaterialRequireDO> list = requireMapper.selectByExample(example);
		int totalRows = requireMapper.selectCountByExample(example);

		PageResult<WmsMaterialRequireDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		this.setCurrentStock(page.getValues());
		this.setSpecSelection(page.getValues());
		this.setSupplierSelection(page.getValues());
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	private void setCurrentStock(List<WmsMaterialRequireDO> list) {
		if (list == null)
			return;
		for (WmsMaterialRequireDO dd : list) {
			WmsMaterialStockDO record = new WmsMaterialStockDO();
			record.setMaterialCode(dd.getMaterialCode());
			record.setCabinCode(dd.getCabinCode());
			record = TkMappers.inst().getMaterialStockMapper().selectOne(record);
			if (record != null) {
				dd.setCurrStock(record.getCurrStock());
			}
		}
	}

	private void setSpecSelection(List<WmsMaterialRequireDO> list) {
		if (list == null)
			return;
		for (WmsMaterialRequireDO dd : list) {
			List<WmsMaterialSpecDetailDO> specs = this.materialSpecService
					.queryMaterialSepcsByMaterialCode(dd.getMaterialCode());
			for (WmsMaterialSpecDetailDO spec : specs) {
				spec.setIsDeleted(null);
				spec.setGmtCreated(null);
				spec.setId(null);
			}
			dd.setSpecSelection(specs);
		}
	}

	private void setSupplierSelection(List<WmsMaterialRequireDO> list) {
		if (list == null)
			return;
		for (WmsMaterialRequireDO dd : list) {
			WmsMaterialSupplierDO record = new WmsMaterialSupplierDO();
			record.setMaterialCode(dd.getMaterialCode());
			record.setIsDeleted("N");
			record.setPageSize(30);
			List<WmsMaterialSupplierDO> sps = TkMappers.inst().getMaterialSupplierMapper().select(record);
			dd.setSupplierSelection(sps);
		}
	}

	@RequestMapping(value = "/createRequire", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object createRequire() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			List<String> mycabins = new ArrayList<>();
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
			String cabinCode = CommonUtils.get(request, "cabinCode");
			if (StringUtils.isNotBlank(cabinCode) && !mycabins.contains(cabinCode)) {
				return ResultBaseBuilder.fails("无权操作门店" + cabinCode).rb(request);
			}
			String lock = "createRequire_" + user.getUserCode();
			if (!lockService.tryLock(lock, 180)) {
				return ResultBaseBuilder.fails("操作太频繁，请稍后").rb(request);
			}
			if (StringUtils.isNotBlank(cabinCode)) {
				this.materialRequireService.createMaterialRequre(Arrays.asList(cabinCode));
			} else {
				if ("1".equals(user.getIsSu())) {
					this.materialRequireService.createMaterialRequre(null);
				} else {
					this.materialRequireService.createMaterialRequre(mycabins);
				}
			}
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception e) {
			log.error("", e);
			return ResultBaseBuilder.fails(e.getMessage()).rb(request);
		}
	}

	@RequestMapping(value = "/downloadRequire", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object downloadRequire(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			String ids = CommonUtils.get(request, "ids");
			List<String> idList = CommonUtils.splitAsList(ids, ",");
			if (idList.size() == 0) {
				return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
			}
			Example example = new Example(WmsMaterialRequireDO.class, false, false);
			Example.Criteria cri = example.createCriteria();
			cri.andIn("id", idList);
			List<WmsMaterialRequireDO> list = this.requireMapper.selectByExample(example);
			//按供应商或调拨仓库排序
			Collections.sort(list, (a, b) -> {
				String s1 = null;
				String s2 = null;
				if ("1".equals(a.getPurchaseType())) {
					s1 = a.getSupplierCode();
				} else {
					s1 = a.getFromCabinCode();
				}
				if ("1".equals(b.getPurchaseType())) {
					s2 = b.getSupplierCode();
				} else {
					s2 = b.getFromCabinCode();
				}
				if (s1 == null)
					s1 = "";
				if (s2 == null)
					s2 = "";
				return s1.compareTo(s2);
			});
			//导出excel
			CfWorkbook wb = new CfWorkbook();
			CfSheet sheet = wb.newSheet("data");
			for (WmsMaterialRequireDO dd : list) {
				WmsMaterialStockDO stock = stockService.queryMaterialStock(dd.getCabinCode(), dd.getMaterialCode());
				CfRow row = sheet.newRow();
				row.appendEx("ID", dd.getId(), //
						"仓库", dd.getCabinName(), //
						"原料", dd.getMaterialName(), //
						"需求量", dd.getRequireAmt(), //
						"库存单位", dd.getStockUnit(), //
						"当前库存", stock == null ? 0 : stock.getCurrStock(), //
						"已选规格", dd.getSpecName(), //
						"单价", dd.getSpecPrice(), //
						"采购类型", "1".equals(dd.getPurchaseType()) ? "采购" : "调拨", //
						"仓库/供应商", "1".equals(dd.getPurchaseType()) ? dd.getSupplierName() : dd.getFromCabinName());
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=YuanLiaoXuQiu" + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
			wb.toHSSFWorkbook().write(response.getOutputStream());
			response.getOutputStream().close();
			return null;
		} catch (Exception ex) {
			log.error("", ex);
			return ResultBaseBuilder.fails(ex.getMessage()).rb(request);
		}
	}
}
