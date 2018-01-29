package com.xjh.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.xjh.commons.DateBuilder;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.commons.TaskUtils;
import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsSupplierDO;
import com.xjh.dao.dataobject.WmsUploadFilesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.mapper.WmsInventoryApplyDetailMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyDetailMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyMapper;
import com.xjh.service.CabinService;
import com.xjh.service.DatabaseService;
import com.xjh.service.Mappers;
import com.xjh.service.MaterialSpecService;
import com.xjh.service.PriceService;
import com.xjh.service.SequenceService;
import com.xjh.service.StockHistoryScheduleTask;
import com.xjh.service.SupplierService;
import com.xjh.service.TkMappers;
import com.xjh.service.UserService;
import com.xjh.service.handlers.PostCheckStockJobHandler;
import com.xjh.support.enums.PaidStatus;
import com.xjh.support.excel.CfWorkbook;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;
import com.xjh.valueobject.CabinVo;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/inventoryApply")
@Slf4j
public class InventoryApplyController {
	@Resource
	HttpServletRequest request;
	@Resource
	CabinService cabinService;
	@Resource
	DatabaseService database;
	@Resource
	MaterialSpecService materialSpecService;
	@Resource
	PriceService priceService;
	@Resource
	TkWmsInventoryApplyMapper tkWmsInventoryApplyMapper;
	@Resource
	TkWmsInventoryApplyDetailMapper tkWmsInventoryApplyDetailMapper;
	@Resource
	WmsInventoryApplyDetailMapper wmsInventoryApplyDetailMapper;
	@Resource
	SequenceService sequenceService;
	@Resource
	UserService userService;
	@Resource
	SupplierService supplierService;

	@RequestMapping(value = "/queryInventoryApply", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryInventoryApply() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		String paidStatus = CommonUtils.get(request, "paidStatus");
		String applyTypes = CommonUtils.get(request, "applyTypes");
		String applyNum = CommonUtils.get(request, "applyNum");
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		List<String> types = CommonUtils.splitAsList(applyTypes, ",");
		Example example = new Example(WmsInventoryApplyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		if (types.size() > 0) {
			cri.andIn("applyType", types);
		}
		cri.andEqualTo("status", status);
		cri.andEqualTo("paidStatus", paidStatus);
		cri.andEqualTo("applyNum", applyNum);
		cri.andIn("cabinCode", this.cabinService.getMyCabinCodes(user));
		int totalRows = tkWmsInventoryApplyMapper.selectCountByExample(example);
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsInventoryApplyDO> list = tkWmsInventoryApplyMapper.selectByExample(example);

		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyPurchaseOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyPurchaseOrder() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setStatus(status);
		cond.setApplyType("purchase");
		if (!"1".equals(user.getIsSu())) {
			cond.setProposer(user.getUserCode());
		}
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = tkWmsInventoryApplyMapper.select(cond);
		for (WmsInventoryApplyDO dd : list) {
			dd.setProposerName(this.userService.getUserName(dd.getProposer()));
		}
		int totalRows = tkWmsInventoryApplyMapper.selectCount(cond);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyAllocate", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyAllocate() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		String status = CommonUtils.get(request, "status");
		Example example = new Example(WmsInventoryApplyDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("status", status);
		cri.andIn("applyType", Arrays.asList("allocation"));
		if (!"1".equals(user.getIsSu()))
			cri.andEqualTo("proposer", user.getUserCode());
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = tkWmsInventoryApplyMapper.selectByExample(example);
		int totalRows = tkWmsInventoryApplyMapper.selectCountByExample(example);
		for (WmsInventoryApplyDO dd : list) {
			dd.setProposerName(this.userService.getUserName(dd.getProposer()));
		}
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyLossApply", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyLossApply() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String status = CommonUtils.get(request, "status");
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setStatus(status);
		cond.setApplyType("claim_loss");
		cond.setProposer(user.getUserCode());
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc");
		List<WmsInventoryApplyDO> list = tkWmsInventoryApplyMapper.select(cond);
		int totalRows = tkWmsInventoryApplyMapper.selectCount(cond);
		PageResult<WmsInventoryApplyDO> page = new PageResult<>();
		page.setTotalRows(totalRows);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryMyLossApplyDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryMyLossApplyDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyType("claim_loss");
		//超管可以查看所有的记录
		if (!"1".equals(user.getIsSu()))
			cond.setCreator(user.getUserCode());
		cond.setPageNo(CommonUtils.getPageNo(request));
		cond.setPageSize(CommonUtils.getPageSize(request));
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsInventoryApplyDetailDO> list = tkWmsInventoryApplyDetailMapper.select(cond);
		List<JSONObject> ret = new ArrayList<>();
		for (WmsInventoryApplyDetailDO dd : list) {
			List<String> images = new ArrayList<>();
			JSONObject json = new JSONObject();
			ret.add(json);
			json.put("cabinCode", dd.getCabinCode());
			json.put("cabinName", dd.getCabinName());
			json.put("materialCode", dd.getMaterialCode());
			json.put("materialName", dd.getMaterialName());
			json.put("stockAmt", dd.getStockAmt());
			json.put("stockUnit", dd.getStockUnit());
			json.put("creator", dd.getCreator());
			json.put("creatorName", this.userService.getUserName(dd.getCreator()));
			json.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dd.getGmtCreated()));
			json.put("images", images);
			if (StringUtils.isNotBlank(dd.getImgBusiNo())) {
				WmsUploadFilesDO img = new WmsUploadFilesDO();
				img.setBusiNo(dd.getImgBusiNo());
				List<WmsUploadFilesDO> imgs = TkMappers.inst().getUploadFilesMapper().select(img);
				for (WmsUploadFilesDO ii : imgs) {
					images.add(ii.getFileName());
				}
			}
		}
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}

	@RequestMapping(value = "/queryInventoryDetailPage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryInventoryDetailPage(HttpServletResponse response) {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String onlymy = CommonUtils.get(request, "onlymy");
		String download = CommonUtils.get(request, "download");
		String applyNum = CommonUtils.get(request, "applyNum");
		String searchKey = CommonUtils.get(request, "searchKey");
		String cabinCode = CommonUtils.get(request, "inCabinCode");
		String status = CommonUtils.get(request, "status");
		String applyType = CommonUtils.get(request, "applyType");
		String startCreatedTime = CommonUtils.get(request, "startCreatedTime");
		String endCreatedTime = CommonUtils.get(request, "endCreatedTime");
		String paidStatus = CommonUtils.get(request, "paidStatus");
		String fromSrc = CommonUtils.get(request, "fromSrc");
		String category = CommonUtils.get(request, "category");
		String creatorSearch = CommonUtils.get(request, "creatorSearch");
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		cond.setApplyType(applyType);
		cond.setCabinCode(cabinCode);
		cond.setStatus(status);
		cond.setPaidStatus(paidStatus);
		cond.setSearchKey(searchKey);
		cond.setFromSrc(fromSrc);
		cond.setCategory(category);
		cond.setCreatorSearch(creatorSearch);
		if (!"3".equals(status)) {
			cond.setStatusList(Arrays.asList("1", "2"));//只查询未入库或已入库的记录
		}
		cond.setStartCreatedTime(CommonUtils.parseDate(startCreatedTime, "yyyy-MM-dd"));
		if (StringUtils.isNotBlank(endCreatedTime)) {
			Date endCreatedDateD = CommonUtils.parseDate(endCreatedTime, "yyyy-MM-dd");
			cond.setEndCreatedTime(DateBuilder.base(endCreatedDateD).futureDays(1).date());
		}
		if ("true".equals(onlymy) && !"1".equals(user.getIsSu())) {
			cond.setCreator(user.getUserCode());
		}
		cond.setPageSize(CommonUtils.getPageSize(request));
		cond.setPageNo(CommonUtils.getPageNo(request));
		if (!"1".equals(user.getIsSu())) {
			List<String> mycabins = new ArrayList<>();
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			mycabins.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
			cond.setMycabins(mycabins);
		}
		int totalRows = wmsInventoryApplyDetailMapper.count(cond);
		if ("excel".equals(download) || "excelInventory".equals(download)) {
			if (totalRows > 500) {
				return ResultBaseBuilder.fails("导出数量超过500条,请限制导出条件").rb(request);
			}
			cond.setPageSize(500);
			List<WmsInventoryApplyDetailDO> list = wmsInventoryApplyDetailMapper.query(cond);
			//initCreatorName(list);
			initBasePrice(list);
			//导出excel
			CfWorkbook wb = new CfWorkbook();
			CfSheet sheet = wb.newSheet("data");
			for (WmsInventoryApplyDetailDO dd : list) {
				String supplierOrCabin = null;
				String account = null;
				String accountName = null;
				String payway = null;
				if ("purchase".equals(dd.getApplyType())) {
					WmsSupplierDO sp = supplierService.getSupplierByCode(dd.getSupplierCode());

					if (sp != null) {
						supplierOrCabin = sp.getSupplierName();
						if ("bank".equals(sp.getPayWay())) {
							payway = "银行";
							account = sp.getBankAccount();
							accountName = sp.getBankAccountName();
						} else if ("alipay".equals(sp.getPayWay())) {
							payway = "支付宝";
							account = sp.getAlipayAccount();
							accountName = sp.getAlipayAccountName();
						} else if ("weixin".equals(sp.getPayWay())) {
							payway = "微信";
							account = sp.getWeixinAccount();
							accountName = sp.getWeixinAccountName();
						}
					}
				} else if ("allocation".equals(dd.getApplyType())) {
					supplierOrCabin = dd.getFromCabinName();
				}

				CfRow row = sheet.newRow();
				row.appendEx("ID", dd.getId(), //
						"仓库", dd.getCabinName(), //
						"供应商", supplierOrCabin, //
						"原料", dd.getMaterialName(), //
						"采购数量", dd.getSpecAmt() + dd.getSpecUnit(), //
						//"入库状态", InventoryDetailStatus.from(dd.getStatus()).remark(), //
						"支付状态", PaidStatus.from(dd.getPaidStatus()).remark(), //
						"支付方式", payway, //
						"收款账户", account, //
						"收款户名", accountName, //
						"单价", dd.getSpecPrice(), //
						"基价", dd.getBasePrice(), //
						"总价", dd.getTotalPrice(), //
						"录入时间", dd.getGmtCreated(), //
						"支付时间", dd.getPaidTime(), //
						"采购类型", "purchase".equals(dd.getApplyType()) ? "采购单" : "调拨单");
			}
			String fileName = "excel".equals(download) ? "Payment" : "Inventory";
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + fileName + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
			try {
				wb.toHSSFWorkbook().write(response.getOutputStream());
				response.getOutputStream().close();
				return null;
			} catch (Exception e) {
				log.error("", e);
				return ResultBaseBuilder.fails(e.getMessage()).rb(request);
			} finally {
			}
		} else {
			List<WmsInventoryApplyDetailDO> list = wmsInventoryApplyDetailMapper.query(cond);
			initCreatorName(list);
			initBasePrice(list);
			for (WmsInventoryApplyDetailDO dd : list) {
				if ("purchase".equals(dd.getApplyType())) {
					dd.setSupplierOrFromCabin(dd.getSupplierName());
				} else {
					dd.setSupplierOrFromCabin(dd.getFromCabinName());
				}
			}
			PageResult<WmsInventoryApplyDetailDO> page = new PageResult<>();
			page.setValues(list);
			page.setTotalRows(totalRows);
			page.setPageNo(cond.getPageNo());
			page.setPageSize(cond.getPageSize());
			return ResultBaseBuilder.succ().data(page).rb(request);
		}
	}

	private void initBasePrice(List<WmsInventoryApplyDetailDO> list) {
		for (WmsInventoryApplyDetailDO dd : list) {
			WmsMaterialSpecDetailDO spec = materialSpecService.querySpecDetailByCode(//
					dd.getMaterialCode(), dd.getSpecCode());
			if (spec != null) {
				dd.setBasePrice(spec.getBasePrice());
			} else {
				dd.setBasePrice(0D);
			}
		}
	}

	private void initCreatorName(List<WmsInventoryApplyDetailDO> list) {
		if (list == null) {
			return;
		}
		for (WmsInventoryApplyDetailDO dd : list) {
			dd.setCreatorName(userService.getUserName(dd.getCreator()));
			dd.setPaidOperatorName(userService.getUserName(dd.getPaidOperator()));
			dd.setConfirmUserName(userService.getUserName(dd.getConfirmUser()));
		}
	}

	@RequestMapping(value = "/queryPurchaseOrderDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryPurchaseOrderDetail() {
		String applyNum = CommonUtils.get(request, "applyNum");
		String searchKey = CommonUtils.get(request, "searchKey");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String status = CommonUtils.get(request, "status");
		String applyType = CommonUtils.get(request, "applyType");
		String startCreatedTime = CommonUtils.get(request, "startCreatedTime");
		String endCreatedTime = CommonUtils.get(request, "endCreatedTime");
		String paidStatus = CommonUtils.get(request, "paidStatus");
		if (StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		cond.setApplyType(applyType);
		cond.setCabinCode(cabinCode);
		cond.setStatus(status);
		cond.setPaidStatus(paidStatus);
		cond.setSearchKey(searchKey);
		cond.setStartCreatedTime(CommonUtils.parseDate(startCreatedTime, "yyyy-MM-dd HH:mm:ss"));
		cond.setEndCreatedTime(CommonUtils.parseDate(endCreatedTime, "yyyy-MM-dd HH:mm:ss"));
		cond.setPageSize(100);
		List<WmsInventoryApplyDetailDO> list = wmsInventoryApplyDetailMapper.query(cond);
		return ResultBaseBuilder.succ().data(list).rb(request);
	}

	@RequestMapping(value = "/commitPurchaseOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object commitPurchaseOrder() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			String cabinCode = CommonUtils.get(request, "cabinCode");
			CabinVo cabinVo = cabinService.getCabinByCode(cabinCode);
			String status = CommonUtils.get(request, "status");
			if (StringUtils.isBlank(status)) {
				status = "4";// 配送中
			}
			if (cabinVo == null) {
				return ResultBaseBuilder.fails(ResultCode.info_missing).msg("仓库信息不存在").rb(request);
			}
			List<WmsInventoryApplyDetailDO> details = new ArrayList<>();
			List<WmsInventoryApplyDO> mains = new ArrayList<>();
			//
			String dataJson = CommonUtils.get(request, "dataJson");
			JSONArray dataArr = CommonUtils.parseJSONArray(dataJson);
			// 保存history
			for (int i = 0; i < dataArr.size(); i++) {
				JSONObject j = dataArr.getJSONObject(i);
				Double storageLifeNum = j.getDouble("storageLifeNum");
				String storageLifeUnit = j.getString("storageLifeUnit");
				WmsInventoryApplyDetailDO d = new WmsInventoryApplyDetailDO();
				d.setApplyType("purchase");
				d.setCabinCode(cabinVo.getCode());
				d.setCabinName(cabinVo.getName());
				d.setMaterialCode(j.getString("materialCode"));
				d.setMaterialName(j.getString("materialName"));
				d.setSupplierCode(j.getString("supplierCode"));
				d.setSupplierName(j.getString("supplierName"));
				d.setSpecCode(j.getString("specCode"));
				if (StringUtils.isBlank(d.getSpecCode())) {
					return ResultBaseBuilder.fails(d.getMaterialName() + "没有填写规格信息").rb(request);
				}
				WmsMaterialSpecDetailDO spec = materialSpecService.querySpecDetailByCode(//
						d.getMaterialCode(), d.getSpecCode());
				if (spec == null) {
					return ResultBaseBuilder.fails("规格" + d.getSpecCode() + "不存在").rb(request);
				}
				d.setSpecAmt(CommonUtils.parseDouble(j.getString("specAmt"), null));
				d.setRealSpecAmt(d.getSpecAmt());
				if (d.getSpecAmt() == null || d.getSpecAmt() <= 0.0001) {
					return ResultBaseBuilder.fails(d.getMaterialName() + "采购数量必输").rb(request);
				}
				d.setSpecPrice(j.getDouble("specPrice"));
				if (d.getSpecPrice() == null || d.getSpecPrice() <= 0.0001) {
					return ResultBaseBuilder.fails(d.getMaterialName() + "采购单价必输").rb(request);
				}
				d.setUtilizationRatio(spec.getUtilizationRatio());
				double transRate = j.getDouble("transRate");
				d.setTransRate(transRate);
				d.setSpecUnit(spec.getSpecUnit());
				//入库数量，根据规格折算过来
				d.setStockAmt(d.getSpecAmt() * transRate);
				//理论入库数量,根据stock_amt和利用率折算
				d.setInStockAmt(d.getStockAmt() * d.getUtilizationRatio() / 100);
				//在in_stock_amt的基础上，由用户修正. real_stock_amt和in_stock_amt之间的差额，就是操作损失额
				d.setRealStockAmt(d.getInStockAmt());
				d.setStockUnit(j.getString("stockUnit"));
				d.setTotalPrice(d.getSpecAmt() * d.getSpecPrice());
				d.setProdDate(CommonUtils.parseDate(j.getString("prodDate")));
				d.setExpDate(CommonUtils.parseDate(j.getString("expDate")));
				if (d.getExpDate() == null) {
					switch (storageLifeUnit) {
					case "D":
						d.setExpDate(CommonUtils.futureDays(d.getProdDate(), storageLifeNum.intValue()));
						break;
					case "M":
						d.setExpDate(CommonUtils.futureMonth(d.getProdDate(), storageLifeNum.intValue()));
						break;
					default:
						d.setExpDate(CommonUtils.futureYear(d.getProdDate(), 10));
					}
				}
				d.setKeepTime(storageLifeNum + storageLifeUnit);
				d.setGmtCreated(new Date());
				d.setGmtModified(new Date());
				d.setCreator(user.getUserCode());
				d.setModifier(user.getUserCode());
				d.setStatus("1");
				if ("purchase".equals(d.getApplyType())) {
					d.setPaidStatus(PaidStatus.not_paid.code());
				} else {
					d.setPaidStatus(PaidStatus.not_need_paid.code());
				}
				d.setPayables(d.getTotalPrice());
				d.setPaidAmt(0D);
				TaskUtils.schedule(() -> {
					priceService.updateSpecPrice(d.getSpecCode(), cabinCode, d.getSpecPrice());
				});
				details.add(d);
			}

			//将采购明细按照门店+供应商进行分组
			Map<String, List<WmsInventoryApplyDetailDO>> groups = new HashMap<>();
			for (WmsInventoryApplyDetailDO detail : details) {
				String key = detail.getCabinCode() + "_" + detail.getSupplierCode();
				if (!groups.containsKey(key)) {
					groups.put(key, new ArrayList<>());
				}
				groups.get(key).add(detail);
			}
			//按分组生成采购单
			for (Map.Entry<String, List<WmsInventoryApplyDetailDO>> group : groups.entrySet()) {
				String prefix = CommonUtils.stringOfToday("yyyyMMdd") + cabinCode;
				long num = sequenceService.next(prefix);
				WmsInventoryApplyDO order = new WmsInventoryApplyDO();
				String applyNum = prefix + StringUtils.leftPad(num + "", 3, "0");
				order.setApplyNum(applyNum);
				order.setCabinCode(cabinCode);
				order.setCabinName(cabinVo.getName());
				order.setSerialNo(CommonUtils.stringOfNow());
				order.setApplyType("purchase");
				order.setProposer(user.getUserCode());
				order.setGmtCreated(new Date());
				order.setGmtModified(new Date());
				order.setCreator(user.getUserCode());
				order.setModifier(user.getUserCode());
				order.setStatus(status);
				Double sumPrice = 0D;
				for (WmsInventoryApplyDetailDO detail : group.getValue()) {
					sumPrice += detail.getTotalPrice();
					detail.setApplyNum(applyNum);
				}
				//收款信息
				order.setTotalPrice(sumPrice);
				order.setPayables(sumPrice);
				order.setPaidAmt(0D);
				order.setPaidStatus(PaidStatus.not_paid.code());
				//供应商
				order.setSupplierCode(group.getValue().get(0).getSupplierCode());
				order.setSupplierName(group.getValue().get(0).getSupplierName());
				mains.add(order);
			}
			// 插入数据库
			database.commitPurchaseOrder(mains, details);
			StockHistoryScheduleTask.startTask();
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			log.error("", ex);
			return ResultBaseBuilder.fails("系统异常").rb(request);
		}
	}

	@RequestMapping(value = "/confirmInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirmInventory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String dataJson = CommonUtils.get(request, "dataJson");
		String applyNum = CommonUtils.get(request, "applyNum");
		if (StringUtils.isBlank(dataJson) || StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDO order = new WmsInventoryApplyDO();
		order.setApplyNum(applyNum);
		order = tkWmsInventoryApplyMapper.selectOne(order);
		if (order == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if (!"4".equals(order.getStatus())) {
			return ResultBaseBuilder.fails("采购单已处理").rb(request);
		}
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		List<WmsMaterialStockHistoryDO> historyInserts = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStock = j.getDouble("realStockAmt");
			Double realSpecAmt = CommonUtils.parseDouble(j.getString("realSpecAmt"), null);
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = tkWmsInventoryApplyDetailMapper.selectOne(detail);
			if (detail == null) {
				continue;
			}
			if (!StringUtils.equals("1", detail.getStatus())) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "状态为" + detail.getStatus() + "，不能处理")
						.rb(request);
			}
			if (realStock == null) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "未输入实际入库数量").rb(request);
			}
			detail.setRealStockAmt(realStock);
			double unitPrice = 0D;// 单价
			if (detail.getTotalPrice() != null && detail.getStockAmt() != null && detail.getStockAmt() > 0.1) {
				unitPrice = detail.getTotalPrice() / detail.getStockAmt();
			}
			// history
			WmsMaterialStockHistoryDO h = new WmsMaterialStockHistoryDO();
			h.setOpType("in_stock");
			h.setCabinCode(detail.getCabinCode());
			h.setCabinName(detail.getCabinName());
			h.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
			h.setMaterialCode(detail.getMaterialCode());
			h.setMaterialName(detail.getMaterialName());
			h.setKeepDays(detail.getKeepTime());
			h.setTotalPrice(detail.getTotalPrice());
			h.setUnitPrice(unitPrice);
			h.setProductDate(detail.getProdDate());
			h.setStockUnit(detail.getStockUnit());
			h.setAmt(detail.getRealStockAmt());
			h.setOperator(detail.getModifier());
			h.setGmtCreated(new Date());
			h.setStatus("0");
			h.setRelateCode(detail.getApplyNum());
			h.setRemark("采购入库,供应商:" + detail.getSupplierName());
			h.setAffectStock("Y");
			historyInserts.add(h);

			if (Math.abs(detail.getInStockAmt() - realStock) > 0.001) {
				WmsMaterialStockHistoryDO loss = new WmsMaterialStockHistoryDO();
				loss.setOpType("in_stock_loss");
				loss.setCabinCode(detail.getCabinCode());
				loss.setCabinName(detail.getCabinName());
				loss.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
				loss.setMaterialCode(detail.getMaterialCode());
				loss.setMaterialName(detail.getMaterialName());
				loss.setKeepDays(detail.getKeepTime());
				loss.setUnitPrice(unitPrice);
				loss.setProductDate(detail.getProdDate());
				loss.setStockUnit(detail.getStockUnit());
				loss.setAmt(realStock - detail.getInStockAmt());//损失是负数
				loss.setOperator(detail.getModifier());
				loss.setGmtCreated(new Date());
				loss.setAffectStock("N");//不参与库存计算
				loss.setStatus("1"); // 入库损耗数据不需要更新库存，仅做记录
				loss.setRelateCode(detail.getApplyNum());
				loss.setRemark("采购入库损耗,供应商:" + detail.getSupplierName());
				historyInserts.add(loss);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("2");
			update.setRealStockAmt(realStock);
			if (realSpecAmt != null) {
				update.setRealSpecAmt(realSpecAmt);
			} else {
				update.setRealSpecAmt(update.getSpecAmt());
			}
			update.setConfirmTime(new Date());
			update.setConfirmUser(user.getUserCode());
			detailUpdateList.add(update);
		}
		// 采购单状态修改
		database.diaoboConfirm(order, detailUpdateList, historyInserts);
		StockHistoryScheduleTask.startTask();

		WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
		cond.setApplyNum(applyNum);
		cond.setStatus("1");
		int notHandleRows = tkWmsInventoryApplyDetailMapper.selectCount(cond);
		if (notHandleRows == 0) {
			order.setStatus("5");
			tkWmsInventoryApplyMapper.updateByPrimaryKey(order);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/modifyInventoryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object modifyInventoryDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String dataJson = CommonUtils.get(request, "dataJson");
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStockAmt = CommonUtils.parseDouble(j.getString("realStockAmt"), null);
			Double realSpecAmt = CommonUtils.parseDouble(j.getString("realSpecAmt"), null);
			Double totalPrice = CommonUtils.parseDouble(j.getString("totalPrice"), null);
			Double specPrice = CommonUtils.parseDouble(j.getString("specPrice"), null);
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = tkWmsInventoryApplyDetailMapper.selectOne(detail);
			if (detail == null) {
				continue;
			}
			if (realStockAmt == null || realSpecAmt == null || totalPrice == null || specPrice == null) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "输入错误").rb(request);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			String remark = "modify by " + user.getUserCode() + "|" + detail.getRealStockAmt() + ":"
					+ detail.getRealSpecAmt() + ":" + detail.getTotalPrice() + ":" + detail.getSpecPrice();
			update.setId(detail.getId());
			update.setRealStockAmt(realStockAmt);
			update.setRealSpecAmt(realSpecAmt);
			update.setTotalPrice(totalPrice);
			update.setSpecPrice(specPrice);
			update.setModifier(user.getUserCode());
			update.setGmtModified(new Date());
			update.setRemark(remark);
			detailUpdateList.add(update);
		}
		// 采购单状态修改
		database.diaoboConfirm(null, detailUpdateList, null);
		StockHistoryScheduleTask.startTask();
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/confirmInventoryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object confirmInventoryDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		final Set<String> applyNums = new HashSet<>();
		String dataJson = CommonUtils.get(request, "dataJson");
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		List<WmsMaterialStockHistoryDO> historyInserts = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			Double realStock = j.getDouble("realStockAmt");
			Double realSpecAmt = CommonUtils.parseDouble(j.getString("realSpecAmt"), null);
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = tkWmsInventoryApplyDetailMapper.selectOne(detail);
			if (detail == null) {
				continue;
			}
			applyNums.add(detail.getApplyNum());
			if (!StringUtils.equals("1", detail.getStatus())) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "状态为" + detail.getStatus() + "，不能处理")
						.rb(request);
			}
			if (realStock == null) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "未输入实际入库数量").rb(request);
			}
			detail.setRealStockAmt(realStock);
			double unitPrice = 0D;// 单价
			if (detail.getTotalPrice() != null && detail.getStockAmt() != null && detail.getStockAmt() > 0.1) {
				unitPrice = detail.getTotalPrice() / detail.getStockAmt();
			}
			// history
			WmsMaterialStockHistoryDO h = new WmsMaterialStockHistoryDO();
			h.setOpType("in_stock");
			h.setCabinCode(detail.getCabinCode());
			h.setCabinName(detail.getCabinName());
			h.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
			h.setMaterialCode(detail.getMaterialCode());
			h.setMaterialName(detail.getMaterialName());
			h.setKeepDays(detail.getKeepTime());
			h.setTotalPrice(detail.getTotalPrice());
			h.setUnitPrice(unitPrice);
			h.setProductDate(detail.getProdDate());
			h.setStockUnit(detail.getStockUnit());
			h.setAmt(detail.getRealStockAmt());
			h.setOperator(detail.getModifier());
			h.setGmtCreated(new Date());
			h.setStatus("0");
			h.setRelateCode(detail.getApplyNum());
			if ("allocation".equals(detail.getApplyType())) {
				h.setRemark("库存调拨,拨出到:" + detail.getCabinName());
			} else {
				h.setRemark("采购入库,供应商:" + detail.getSupplierName());
			}
			h.setAffectStock("Y");
			historyInserts.add(h);
			if ("allocation".equals(detail.getApplyType())) {
				WmsMaterialStockHistoryDO h2 = new WmsMaterialStockHistoryDO();
				h2.setOpType("out_stock");
				h2.setCabinCode(detail.getFromCabinCode());
				h2.setCabinName(detail.getFromCabinName());
				h2.setCabinType(detail.getFromCabinCode().startsWith("WH") ? "1" : "2");
				h2.setMaterialCode(detail.getMaterialCode());
				h2.setMaterialName(detail.getMaterialName());
				h2.setKeepDays(detail.getKeepTime());
				h2.setTotalPrice(detail.getTotalPrice());
				h2.setUnitPrice(unitPrice);
				h2.setProductDate(detail.getProdDate());
				h2.setStockUnit(detail.getStockUnit());
				h2.setAmt(-1 * detail.getInStockAmt());
				h2.setOperator(detail.getModifier());
				h2.setGmtCreated(new Date());
				h2.setStatus("0");
				h2.setRelateCode(detail.getApplyNum());
				h2.setRemark("调拨出库,来源:" + detail.getFromCabinName());
				h2.setAffectStock("Y");
				historyInserts.add(h2);
			}

			if (Math.abs(detail.getInStockAmt() - realStock) > 0.001) {
				WmsMaterialStockHistoryDO loss = new WmsMaterialStockHistoryDO();
				loss.setOpType("in_stock_loss");
				loss.setCabinCode(detail.getCabinCode());
				loss.setCabinName(detail.getCabinName());
				loss.setCabinType(detail.getCabinCode().startsWith("WH") ? "1" : "2");
				loss.setMaterialCode(detail.getMaterialCode());
				loss.setMaterialName(detail.getMaterialName());
				loss.setKeepDays(detail.getKeepTime());
				loss.setUnitPrice(unitPrice);
				loss.setProductDate(detail.getProdDate());
				loss.setStockUnit(detail.getStockUnit());
				loss.setAmt(realStock - detail.getInStockAmt());//损失是负数
				loss.setOperator(detail.getModifier());
				loss.setGmtCreated(new Date());
				loss.setAffectStock("N");//不参与库存计算
				loss.setStatus("1"); // 入库损耗数据不需要更新库存，仅做记录
				loss.setRelateCode(detail.getApplyNum());
				String sp = detail.getSupplierName();
				if (StringUtils.isBlank(sp)) {
					sp = detail.getFromCabinName();
				}
				loss.setRemark("入库损耗,供应商:" + sp);
				historyInserts.add(loss);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("2");
			update.setRealStockAmt(realStock);
			if (realSpecAmt != null) {
				update.setRealSpecAmt(realSpecAmt);
			} else {
				update.setRealSpecAmt(update.getSpecAmt());
			}
			update.setModifier(user.getUserCode());
			update.setGmtModified(new Date());
			update.setConfirmUser(user.getUserCode());
			update.setConfirmTime(new Date());
			detailUpdateList.add(update);
		}
		// 采购单状态修改
		database.diaoboConfirm(null, detailUpdateList, historyInserts);
		StockHistoryScheduleTask.startTask();
		//更新wms_inventory_apply的状态
		TaskUtils.schedule(() -> {
			for (String applyNum : applyNums) {
				WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
				cond.setApplyNum(applyNum);
				cond.setStatus("1");//查询采购单下面的未入库的记录
				int notHandleRows = tkWmsInventoryApplyDetailMapper.selectCount(cond);
				//都入库了，更新主表状态
				if (notHandleRows == 0) {
					Example applyExample = new Example(WmsInventoryApplyDO.class, false, false);
					Example.Criteria applyCri = applyExample.createCriteria();
					applyCri.andEqualTo("applyNum", applyNum);
					WmsInventoryApplyDO val = new WmsInventoryApplyDO();
					val.setStatus("5"); //全部都入库了
					tkWmsInventoryApplyMapper.updateByExampleSelective(val, applyExample);
				}
			}
		});

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/deleteInventoryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteInventoryDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		final Set<String> applyNums = new HashSet<>();
		String dataJson = CommonUtils.get(request, "dataJson");
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = tkWmsInventoryApplyDetailMapper.selectOne(detail);
			if (detail == null) {
				continue;
			}
			applyNums.add(detail.getApplyNum());
			//管理员可以删除任何状态的记录
			if (!"1".equals(user.getIsSu()) && !StringUtils.equals("1", detail.getStatus())) {
				return ResultBaseBuilder.fails( //
						detail.getMaterialName() + "状态为" + detail.getStatus() + "，不能处理").rb(request);
			}
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setStatus("3");
			update.setModifier(user.getUserCode());
			update.setGmtModified(new Date());
			update.setRemark("删除by" + user.getUserCode());
			detailUpdateList.add(update);
		}
		// 采购单状态修改
		database.diaoboConfirm(null, detailUpdateList, null);
		StockHistoryScheduleTask.startTask();
		//更新wms_inventory_apply的状态
		TaskUtils.schedule(() -> {
			for (String applyNum : applyNums) {
				//判断明细是否都是删除状态
				Example allDeletedExample = new Example(WmsInventoryApplyDetailDO.class, false, false);
				Example.Criteria allDeleetedCri = allDeletedExample.createCriteria();
				allDeleetedCri.andEqualTo("applyNum", applyNum);
				allDeleetedCri.andNotEqualTo("status", "3");//是否存在不等于3的记录
				int validRows = tkWmsInventoryApplyDetailMapper.selectCountByExample(allDeletedExample);
				//所有记录都被删除了
				if (validRows == 0) {
					Example applyExample = new Example(WmsInventoryApplyDO.class, false, false);
					Example.Criteria applyCri = applyExample.createCriteria();
					applyCri.andEqualTo("applyNum", applyNum);
					WmsInventoryApplyDO val = new WmsInventoryApplyDO();
					val.setStatus("6"); //删除状态
					tkWmsInventoryApplyMapper.updateByExampleSelective(val, applyExample);
					continue;
				}
				WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
				cond.setApplyNum(applyNum);
				cond.setStatus("1");//查询采购单下面的未入库的记录
				int notHandleRows = tkWmsInventoryApplyDetailMapper.selectCount(cond);
				//都入库了，更新主表状态
				if (notHandleRows == 0) {
					Example applyExample = new Example(WmsInventoryApplyDO.class, false, false);
					Example.Criteria applyCri = applyExample.createCriteria();
					applyCri.andEqualTo("applyNum", applyNum);
					WmsInventoryApplyDO val = new WmsInventoryApplyDO();
					val.setStatus("5"); //全部都入库了
					tkWmsInventoryApplyMapper.updateByExampleSelective(val, applyExample);
				}
			}
		});

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/downloadInventoryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object downloadInventoryDetail(HttpServletResponse response) {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String ids = CommonUtils.get(request, "ids");
		List<Long> idList = CommonUtils.splitAsList(ids, ",").stream()//
				.map(it -> CommonUtils.parseLong(it, null))//
				.collect(Collectors.toList());
		Example example = new Example(WmsInventoryApplyDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andIn("id", idList);
		List<WmsInventoryApplyDetailDO> list = tkWmsInventoryApplyDetailMapper.selectByExample(example);
		//导出excel
		CfWorkbook wb = new CfWorkbook();
		CfSheet sheet = wb.newSheet("data");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		for (WmsInventoryApplyDetailDO dd : list) {
			CfRow row = sheet.newRow();
			row.appendEx("ID", dd.getId(), //
					"仓库", dd.getCabinName(), //
					"供应商", dd.getCabinName(), //
					"原料", dd.getMaterialName(), //
					"采购单位", dd.getSpecUnit(), //
					"采购数量", dd.getSpecAmt(), //
					"单价", dd.getSpecPrice(), //
					"总价", dd.getTotalPrice(), //
					"录入时间", dd.getGmtCreated(), //
					"采购类型", "purchase".equals(dd.getApplyType()) ? "采购单" : "调拨单");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment; filename=Payment" + CommonUtils.stringOfToday("yyyyMMdd") + ".xlsx");
		try {
			wb.toHSSFWorkbook().write(response.getOutputStream());
			response.getOutputStream().close();
			return null;
		} catch (IOException e) {
			log.error("", e);
			return ResultBaseBuilder.fails(e.getMessage()).rb(request);
		}
	}

	@RequestMapping(value = "/paidInventoryDetail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object paidInventoryDetail() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		final Set<String> applyNums = new HashSet<>();
		String dataJson = CommonUtils.get(request, "dataJson");
		List<WmsInventoryApplyDetailDO> detailUpdateList = new ArrayList<>();
		JSONArray array = CommonUtils.parseJSONArray(dataJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject j = array.getJSONObject(i);
			Long id = j.getLong("id");
			WmsInventoryApplyDetailDO detail = new WmsInventoryApplyDetailDO();
			detail.setId(id);
			detail = tkWmsInventoryApplyDetailMapper.selectOne(detail);
			if (detail == null) {
				continue;
			}
			applyNums.add(detail.getApplyNum());
			if (!StringUtils.equals("2", detail.getStatus())) {
				return ResultBaseBuilder.fails(detail.getMaterialName() + "状态为" + detail.getStatus() + "，不能支付")
						.rb(request);
			}
			//
			WmsInventoryApplyDetailDO update = new WmsInventoryApplyDetailDO();
			update.setId(detail.getId());
			update.setPaidStatus(PaidStatus.paid_success.code());
			update.setPaidOperator(user.getUserCode());
			update.setPaidAmt(detail.getPayables());
			update.setPaidTime(new Date());
			update.setModifier(user.getUserCode());
			update.setGmtModified(new Date());
			detailUpdateList.add(update);
		}
		// 采购单状态修改
		database.diaoboConfirm(null, detailUpdateList, null);
		//更新wms_inventory_apply的状态
		TaskUtils.schedule(() -> {
			for (String applyNum : applyNums) {
				WmsInventoryApplyDetailDO cond = new WmsInventoryApplyDetailDO();
				cond.setApplyNum(applyNum);
				cond.setPaidStatus(PaidStatus.not_paid.code());
				int notPaidRows = tkWmsInventoryApplyDetailMapper.selectCount(cond);
				//都支付了，更新主表状态
				if (notPaidRows == 0) {
					Example applyExample = new Example(WmsInventoryApplyDO.class, false, false);
					Example.Criteria applyCri = applyExample.createCriteria();
					applyCri.andEqualTo("applyNum", applyNum);
					WmsInventoryApplyDO val = new WmsInventoryApplyDO();
					val.setPaidStatus(PaidStatus.paid_success.code()); //全部都支付了
					val.setPaidOperator(user.getUserCode());
					val.setPaidTime(new Date());
					tkWmsInventoryApplyMapper.updateByExampleSelective(val, applyExample);
				}
			}
		});
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/deleteInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteInventory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String applyNum = CommonUtils.get(request, "applyNum");
		if (StringUtils.isBlank(applyNum)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setApplyNum(applyNum);
		WmsInventoryApplyDO record = tkWmsInventoryApplyMapper.selectOne(cond);
		if (record == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		record.setStatus("6");
		tkWmsInventoryApplyMapper.updateByPrimaryKeySelective(record);
		Example example = new Example(WmsInventoryApplyDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("applyNum", applyNum);
		WmsInventoryApplyDetailDO detailUpdateVal = new WmsInventoryApplyDetailDO();
		detailUpdateVal.setStatus("3");
		detailUpdateVal.setRemark("作废");
		detailUpdateVal.setModifier(user.getUserCode());
		detailUpdateVal.setGmtModified(new Date());
		tkWmsInventoryApplyDetailMapper.updateByExampleSelective(detailUpdateVal, example);

		//更新detail的状态
		Example exampleDetail = new Example(WmsInventoryApplyDetailDO.class, false, false);
		Example.Criteria criDetail = exampleDetail.createCriteria();
		criDetail.andEqualTo("applyNum", applyNum);
		WmsInventoryApplyDetailDO detailValue = new WmsInventoryApplyDetailDO();
		detailValue.setStatus("3");
		detailValue.setModifier(user.getUserCode());
		detailValue.setGmtModified(new Date());
		detailValue.setRemark("作废by" + user.getUserCode());
		tkWmsInventoryApplyDetailMapper.updateByExampleSelective(detailValue, exampleDetail);

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/paidInventory", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object paidInventory() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String applyNum = CommonUtils.get(request, "applyNum");
		String paidWay = CommonUtils.get(request, "paidWay");
		Double paidAmt = CommonUtils.getDbl(request, "paidAmt", null);
		String paytoBank = CommonUtils.get(request, "paytoBank");
		String paytoAccount = CommonUtils.get(request, "paytoAccount");
		String paytoAccountName = CommonUtils.get(request, "paytoAccountName");
		if (CommonUtils.isAnyBlank(applyNum, paidWay)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		if (paidAmt == null) {
			return ResultBaseBuilder.fails("支付金额不能为空").rb(request);
		}
		if (paidAmt <= 0) {
			return ResultBaseBuilder.fails("支付金额金额必须大于0").rb(request);
		}
		assert StringUtils.isNotBlank(applyNum);
		WmsInventoryApplyDO cond = new WmsInventoryApplyDO();
		cond.setApplyNum(applyNum);
		WmsInventoryApplyDO apply = tkWmsInventoryApplyMapper.selectOne(cond);
		if (apply == null) {
			return ResultBaseBuilder.fails("采购单不存在").rb(request);
		}
		if (!PaidStatus.not_paid.code().equals(apply.getPaidStatus())) {
			return ResultBaseBuilder.fails("采购单已支付,请勿重复操作").rb(request);
		}
		//设置更新信息
		WmsInventoryApplyDO update = new WmsInventoryApplyDO();
		update.setId(apply.getId());
		update.setPaidStatus(PaidStatus.paid_success.code());
		update.setPaidAmt(paidAmt);
		update.setPaytoBank(paytoBank);
		update.setPaytoAccount(paytoAccount);
		update.setPaytoAccountName(paytoAccountName);
		update.setPaidWay(paidWay);
		update.setPaidOperator(user.getUserCode());
		update.setPaidTime(new Date());
		tkWmsInventoryApplyMapper.updateByPrimaryKeySelective(update);
		//更新detail的状态
		Example example = new Example(WmsInventoryApplyDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("applyNum", applyNum);
		WmsInventoryApplyDetailDO value = new WmsInventoryApplyDetailDO();
		value.setPaidStatus(PaidStatus.paid_success.code());
		value.setPaidOperator(user.getUserCode());
		value.setPaidTime(new Date());
		value.setPaidRemark("总单支付" + paidAmt);
		tkWmsInventoryApplyDetailMapper.updateByExampleSelective(value, example);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/startCorrect", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object startCorrect() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = request.getParameter("cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		CabinVo vo = cabinService.getCabinByCode(cabinCode);
		if (vo == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if ("0".equals(vo.getStatus())) {
			return ResultBaseBuilder.fails("仓库状态无效").rb(request);
		}
		//		if ("2".equals(vo.getStatus())) {
		//			return ResultBaseBuilder.fails("仓库正在盘点").rb(request);
		//		}
		if (vo.getCode().startsWith("WH")) {
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(cabinCode);
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			WmsWarehouseDO update = new WmsWarehouseDO();
			update.setId(warehouse.getId());
			update.setStatus("2");
			TkMappers.inst().getWarehouseMapper().updateByPrimaryKeySelective(update);
		} else {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			WmsStoreDO update = new WmsStoreDO();
			update.setId(store.getId());
			update.setStatus("2");
			TkMappers.inst().getStoreMapper().updateByPrimaryKeySelective(update);
		}
		WmsMaterialStockDO stock = new WmsMaterialStockDO();
		stock.setCabinCode(cabinCode);
		stock.setModifier(user.getUserCode());
		Mappers.inst().getStockMapper().startCorrect(stock);

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/finishCorrect", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object finishCorrect() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = request.getParameter("cabinCode");
		if (StringUtils.isBlank(cabinCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		CabinVo vo = cabinService.getCabinByCode(cabinCode);
		if (vo == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if ("0".equals(vo.getStatus())) {
			return ResultBaseBuilder.fails("仓库状态无效").rb(request);
		}
		//		if ("1".equals(vo.getStatus())) {
		//			return ResultBaseBuilder.fails("仓库还未开始盘点").rb(request);
		//		}
		if (vo.getCode().startsWith("WH")) {
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(cabinCode);
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			WmsWarehouseDO update = new WmsWarehouseDO();
			update.setId(warehouse.getId());
			update.setStatus("1");
			TkMappers.inst().getWarehouseMapper().updateByPrimaryKeySelective(update);
		} else {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			WmsStoreDO update = new WmsStoreDO();
			update.setId(store.getId());
			update.setStatus("1");
			TkMappers.inst().getStoreMapper().updateByPrimaryKeySelective(update);
		}
		WmsMaterialStockDO cond = new WmsMaterialStockDO();
		cond.setCabinCode(cabinCode);
		List<WmsMaterialStockDO> list = Mappers.inst().getStockMapper().selectWaiting(cond);
		for (WmsMaterialStockDO stock : list) {
			// 记录history
			WmsMaterialStockHistoryDO posthis = new WmsMaterialStockHistoryDO();
			posthis.setCabinCode(stock.getCabinCode());
			posthis.setCabinName(stock.getCabinName());
			posthis.setCabinType(stock.getCabinType());
			posthis.setAmt(stock.getCurrStock());// 修正库存量
			posthis.setMaterialCode(stock.getMaterialCode());
			posthis.setMaterialName(stock.getMaterialName());
			posthis.setStockUnit(stock.getStockUnit());
			posthis.setOpType("correct");
			posthis.setStatus("0");
			posthis.setGmtCreated(new Date());
			posthis.setOperator(user.getUserCode());
			TkMappers.inst().getMaterialStockHistoryMapper().insert(posthis);
			stock.setStatus("1");
			TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(stock);
		}
		cond = new WmsMaterialStockDO();
		cond.setCabinCode(cabinCode);
		Mappers.inst().getStockMapper().finishCorrect(cond);
		StockHistoryScheduleTask.startTask();
		String today = CommonUtils.stringOfToday("yyyyMMdd");
		PostCheckStockJobHandler.addNewJob(today, cabinCode);//添加库存盘点报告任务
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/claimLoss", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object claimLoss() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String cabinName = CommonUtils.get(request, "cabinName");
		String materialCode = CommonUtils.get(request, "materialCode");
		String materialName = CommonUtils.get(request, "materialName");
		double lossAmt = CommonUtils.getDbl(request, "lossAmt", 0D);
		String stockUnit = CommonUtils.get(request, "stockUnit");
		Double specAmt = CommonUtils.getDbl(request, "specAmt", 0D);
		String specUnit = CommonUtils.get(request, "specUnit");
		Integer utilizationRatio = CommonUtils.getInt(request, "utilizationRatio");
		Double transRate = CommonUtils.getDbl(request, "transRate", null);
		String images = CommonUtils.get(request, "images");
		String remark = CommonUtils.get(request, "remark");
		String imgBusiNo = CommonUtils.get(request, "busiNo");
		if (lossAmt <= 0.00001) {
			return ResultBaseBuilder.fails("报损数量不能为空").rb(request);
		}
		WmsInventoryApplyDO inorder = new WmsInventoryApplyDO();
		inorder.setApplyNum(CommonUtils.uuid());
		inorder.setCabinCode(cabinCode);
		inorder.setCabinName(cabinName);
		inorder.setSerialNo(CommonUtils.stringOfNow());
		inorder.setApplyType("claim_loss");
		inorder.setProposer(user.getUserCode());
		inorder.setGmtCreated(new Date());
		inorder.setGmtModified(new Date());
		inorder.setCreator(user.getUserCode());
		inorder.setModifier(user.getUserCode());
		inorder.setStatus("5");
		inorder.setRemark(remark);
		inorder.setPaidStatus(PaidStatus.paid_success.code());
		inorder.setPaidAmt(0D);
		inorder.setPayables(0D);
		inorder.setTotalPrice(0D);

		WmsInventoryApplyDetailDO indetail = new WmsInventoryApplyDetailDO();
		indetail.setCabinCode(inorder.getCabinCode());
		indetail.setCabinName(inorder.getCabinName());
		indetail.setApplyNum(inorder.getApplyNum());
		indetail.setApplyType(inorder.getApplyType());
		indetail.setMaterialCode(materialCode);
		indetail.setMaterialName(materialName);
		indetail.setSpecAmt(specAmt);
		indetail.setRealSpecAmt(specAmt);
		indetail.setSpecUnit(specUnit);
		indetail.setUtilizationRatio(utilizationRatio);
		indetail.setStockAmt(lossAmt);
		indetail.setStockUnit(stockUnit);
		indetail.setTransRate(transRate);
		indetail.setInStockAmt(lossAmt);
		indetail.setRealStockAmt(lossAmt);
		indetail.setGmtCreated(new Date());
		indetail.setGmtModified(new Date());
		indetail.setCreator(user.getUserCode());
		indetail.setModifier(user.getUserCode());
		indetail.setStatus("1");// 报损自动处理，状态直接置为1
		indetail.setImgBusiNo(imgBusiNo);
		indetail.setUtilizationRatio(100);
		indetail.setRemark(images);
		indetail.setPaidStatus(PaidStatus.not_need_paid.code());
		indetail.setPayables(0D);
		indetail.setPaidAmt(0D);

		WmsMaterialStockHistoryDO h = new WmsMaterialStockHistoryDO();
		h.setOpType("claim_loss");
		h.setCabinCode(indetail.getCabinCode());
		h.setCabinName(indetail.getCabinName());
		h.setCabinType(indetail.getCabinCode().startsWith("WH") ? "1" : "2");
		h.setMaterialCode(indetail.getMaterialCode());
		h.setMaterialName(indetail.getMaterialName());
		h.setKeepDays(indetail.getKeepTime());
		h.setTotalPrice(indetail.getTotalPrice());
		h.setUnitPrice(indetail.getSpecPrice());
		h.setProductDate(indetail.getProdDate());
		h.setStockUnit(indetail.getStockUnit());
		h.setAmt(-1 * lossAmt);
		h.setOperator(indetail.getModifier());
		h.setGmtCreated(new Date());
		h.setStatus("0");
		h.setRemark("报损");
		h.setAffectStock("Y");
		h.setRelateCode(indetail.getApplyNum());

		database.claimLossInsert(inorder, indetail, h);
		StockHistoryScheduleTask.startTask();
		return ResultBaseBuilder.succ().msg("提交成功").rb(request);
	}

	@RequestMapping(value = "/correctStock", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object correctStock() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.parseLong(request.getParameter("id"), null);
		String materialCode = request.getParameter("materialCode");
		if (id == null || StringUtils.isBlank(materialCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}

		String realStockStr = request.getParameter("realStock");
		BigDecimal realStock = CommonUtils.parseBigDecimal(realStockStr);
		if (realStock == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		// 查询数据库
		WmsMaterialStockDO t = new WmsMaterialStockDO();
		t.setId(id);
		t.setMaterialCode(materialCode);
		WmsMaterialStockDO stock = TkMappers.inst().getMaterialStockMapper().selectOne(t);
		if (stock == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		//
		WmsMaterialStockHistoryDO prehis = new WmsMaterialStockHistoryDO();
		prehis.setCabinCode(stock.getCabinCode());
		prehis.setCabinName(stock.getCabinName());
		prehis.setCabinType(stock.getCabinType());
		prehis.setAmt(realStock.doubleValue() - stock.getCurrStock());// 库存不一致的量
		prehis.setPreStock(stock.getCurrStock());
		prehis.setPostStock(realStock.doubleValue());
		prehis.setMaterialCode(stock.getMaterialCode());
		prehis.setMaterialName(stock.getMaterialName());
		prehis.setStockUnit(stock.getStockUnit());
		prehis.setOpType("correct_delta");//
		prehis.setStatus("1"); // 记录盘点库存和实际库存的差额，只起记录作用，所以状态直接置为1
		prehis.setRemark("库存盘点差额");
		prehis.setGmtCreated(new Date());
		prehis.setOperator(user.getUserCode());
		// 记录history
		WmsMaterialStockHistoryDO posthis = new WmsMaterialStockHistoryDO();
		posthis.setCabinCode(stock.getCabinCode());
		posthis.setCabinName(stock.getCabinName());
		posthis.setCabinType(stock.getCabinType());
		posthis.setAmt(realStock.doubleValue());// 修正库存量
		posthis.setMaterialCode(stock.getMaterialCode());
		posthis.setMaterialName(stock.getMaterialName());
		posthis.setStockUnit(stock.getStockUnit());
		posthis.setOpType("correct");
		posthis.setStatus("0");
		posthis.setGmtCreated(new Date());
		posthis.setOperator(user.getUserCode());
		database.correctStock(prehis, posthis);
		StockHistoryScheduleTask.startTask();
		stock.setStatus("3");
		TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(stock);

		return ResultBaseBuilder.succ().rb(request);
	}
}
