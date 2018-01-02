package com.xjh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xjh.dao.dataobject.WmsPaymentDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsPaymentMapper;
import com.xjh.service.PaymentService;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsPaymentMapper tkWmsPaymentMapper;
	@Resource
	PaymentService paymentService;

	@RequestMapping(value = "/savePayment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object savePayment() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String payNo = CommonUtils.get(request, "payNo");
		WmsPaymentDO payment = paymentService.getPaymentByPayNo(payNo);
		if (payment != null && !"0".equals(payment.getStatus())) {
			return ResultBaseBuilder.fails("申请单已提交,不允许修改").rb(request);
		}
		if (id != null && payment != null && !id.equals(payment.getId())) {
			//id和payment.id不匹配
			return ResultBaseBuilder.fails("申请单信息有误,请核实").rb(request);
		}
		if (payment == null) {
			payment = new WmsPaymentDO();
			payment.setAccountDate(DateBuilder.today().date());
			payment.setCreatedTime(new Date());
			payment.setIsDeleted("N");
			payment.setVersion(0);
		}
		//修改版本
		Integer version = CommonUtils.getInt(request, "version");
		if (version != null && !version.equals(payment.getVersion())) {
			return ResultBaseBuilder.fails("记录已被其他人修改,请重新操作").rb(request);
		}
		payment.setVersion(payment.getVersion() + 1);
		//账期
		String accountDateStr = CommonUtils.get(request, "accountDate");
		Date accountDate = CommonUtils.parseDate(accountDateStr);
		if (accountDate != null) {
			payment.setAccountDate(accountDate);
		}
		//部门
		payment.setDepartment(CommonUtils.get(request, "department"));
		payment.setDepartmentName(CommonUtils.get(request, "departmentName"));
		//申请金额
		payment.setPayables(CommonUtils.getDbl(request, "payables", null));
		payment.setPaidAmt(0D);
		if (payment.getPayables() == null) {
			return ResultBaseBuilder.fails("请输入申请金额").rb(request);
		}
		//费用类型
		payment.setType(CommonUtils.get(request, "type"));
		payment.setTypeName(CommonUtils.get(request, "typeName"));
		if (StringUtils.isBlank(payment.getType())) {
			return ResultBaseBuilder.fails("请输入费用类型").rb(request);
		}
		//支付方式
		payment.setPayWay(CommonUtils.get(request, "payWay"));
		if (StringUtils.isBlank(payment.getPayWay())) {
			return ResultBaseBuilder.fails("请输入支付方式").rb(request);
		}
		//支付账号
		payment.setBankName(CommonUtils.get(request, "bankName"));
		payment.setBankAccount(CommonUtils.get(request, "bankAccount"));
		payment.setBankAccountName(CommonUtils.get(request, "bankAccountName"));
		payment.setDepositBank(CommonUtils.get(request, "depositBank"));
		payment.setAlipayAccount(CommonUtils.get(request, "alipayAccount"));
		payment.setAlipayAccountName(CommonUtils.get(request, "alipayAccountName"));
		payment.setWeixinAccount(CommonUtils.get(request, "weixinAccount"));
		payment.setWeixinAccountName(CommonUtils.get(request, "weixinAccountName"));
		payment.setRemark(CommonUtils.get(request, "remark"));
		//状态等字段
		payment.setStatus("0");
		payment.setProposerCode(user.getUserCode());
		payment.setProposerName(user.getUserName());
		payment.setIsDeleted("N");

		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/queryPaymentByPayNo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryPaymentByPayNo() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String payNo = CommonUtils.get(request, "payNo");
		Long id = CommonUtils.getLong(request, "id");
		if (id == null || StringUtils.isBlank(payNo)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsPaymentDO payment = paymentService.getPaymentByPayNo(payNo);
		if (payment == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if (!payment.getId().equals(id)) {
			return ResultBaseBuilder.fails("入参信息不匹配").rb(request);
		}
		return ResultBaseBuilder.succ().data(payment).rb(request);
	}

	@RequestMapping(value = "/queryPayments", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryPayments() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		int pageNo = CommonUtils.getPageNo(request);
		int pageSize = CommonUtils.getPageSize(request);
		String myproposer = CommonUtils.get(request, "myproposer");
		String myapproved = CommonUtils.get(request, "myapproved");
		String mypaid = CommonUtils.get(request, "mypaid");
		String status = CommonUtils.get(request, "status");
		String cabinCode = CommonUtils.get(request, "cabinCode");
		String startDate = CommonUtils.get(request, "startDate");
		String endDate = CommonUtils.get(request, "endDate");
		String payWay = CommonUtils.get(request, "payWay");
		String type = CommonUtils.get(request, "type");
		Example example = new Example(WmsPaymentDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		if ("1".equals(myproposer)) {
			cri.andEqualTo("proposerCode", user.getUserCode());
		}
		if ("1".equals(myapproved)) {
			cri.andEqualTo("approverCode", user.getUserCode());
		}
		if ("1".equals(mypaid)) {
			cri.andEqualTo("payerCode", user.getUserCode());
		}
		cri.andEqualTo("status", status);
		cri.andEqualTo("department", cabinCode);
		cri.andEqualTo("type", type);
		cri.andGreaterThanOrEqualTo("createdTime", CommonUtils.parseDate(startDate));
		cri.andLessThan("createdTime", DateBuilder.base(CommonUtils.parseDate(endDate)).futureDays(1).date());
		cri.andEqualTo("payWay", payWay);
		if (!"1".equals(user.getIsSu())) {
			List<String> mydeparts = new ArrayList<>();
			mydeparts.addAll(CommonUtils.splitAsList(user.getAuthStores(), ","));
			mydeparts.addAll(CommonUtils.splitAsList(user.getAuthWarehouse(), ","));
			cri.andIn("department", mydeparts);
		}
		PageResult<WmsPaymentDO> page = new PageResult<>();
		PageHelper.startPage(pageNo, pageSize);
		List<WmsPaymentDO> list = tkWmsPaymentMapper.selectByExample(example);
		int totalRows = tkWmsPaymentMapper.selectCountByExample(example);
		page.setValues(list);
		page.setTotalRows(totalRows);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/approvePayment", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object approvePayment() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		Long id = CommonUtils.getLong(request, "id");
		String payNo = CommonUtils.get(request, "payNo");
		String department = CommonUtils.get(request, "department");
		Integer version = CommonUtils.getInt(request, "version");
		String approvePass = CommonUtils.get(request, "approvePass"); //Y:通过 N:驳回
		if (id == null //
				|| StringUtils.isBlank(payNo) //
				|| StringUtils.isBlank(department) //
				|| version == null//
				|| StringUtils.isBlank(approvePass)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsPaymentDO payment = paymentService.getPaymentByPayNo(payNo);
		if (payment == null) {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		if (!id.equals(payment.getId()) //
				|| !department.equals(payment.getDepartment())//
				|| !version.equals(payment.getVersion()) //
		) {
			return ResultBaseBuilder.fails("申请单信息不匹配或已被他人修改,请检查").rb(request);
		}
		if (!"1".equals(payment.getStatus())) {
			return ResultBaseBuilder.fails("申请单状态错误,无法审核").rb(request);
		}
		if ("Y".equals(approvePass)) {
			//payment.setStatus("2");
			payment.setStatus("4");//直接变为待支付
		} else if ("N".equals(approvePass)) {
			payment.setStatus("3");
		} else {
			return ResultBaseBuilder.fails(ResultCode.info_missing).rb(request);
		}
		payment.setApprovedTime(new Date());
		payment.setApproverCode(user.getUserCode());
		payment.setApproverName(user.getUserName());
		payment.setVersion(version + 1);
		tkWmsPaymentMapper.updateByPrimaryKeySelective(payment);
		return ResultBaseBuilder.succ().msg("审核成功").rb(request);
	}

	/**
	 * 付款确认
	 * @return
	 */
	@RequestMapping(value = "/paymentConfirm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object paymentConfirm() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String payments = CommonUtils.get(request, "payments");
		JSONArray paymentArr = CommonUtils.parseJSONArray(payments);
		if (paymentArr.size() == 0) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		List<WmsPaymentDO> updates = new ArrayList<>();

		for (int i = 0; i < paymentArr.size(); i++) {
			JSONObject j = paymentArr.getJSONObject(i);
			String payNo = j.getString("payNo");
			WmsPaymentDO p = this.paymentService.getPaymentByPayNo(payNo);
			if (p == null) {
				return ResultBaseBuilder.fails("支付单" + payNo + "不存在").rb(request);
			}
			Double paidAmt = j.getDouble("paidAmt");
			if (paidAmt == null) {
				return ResultBaseBuilder.fails("请输入支付金额").rb(request);
			}
			if (!"4".equals(p.getStatus())) {
				return ResultBaseBuilder.fails("支付单" + payNo + "无法支付").rb(request);
			}
			WmsPaymentDO update = new WmsPaymentDO();
			update.setId(p.getId());
			update.setStatus("5");
			update.setPaidAmt(paidAmt);
			update.setPaidRemark(j.getString("paidRemark"));
			update.setPaidTime(new Date());
			update.setPayerCode(user.getUserCode());
			update.setPayerName(user.getUserName());
			update.setVersion(p.getVersion() + 1);
			updates.add(update);
		}

		for (WmsPaymentDO update : updates) {
			this.tkWmsPaymentMapper.updateByPrimaryKeySelective(update);
		}
		return ResultBaseBuilder.succ().rb(request);
	}
}
