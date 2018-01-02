package com.xjh.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsPaymentDO;
import com.xjh.dao.tkmapper.TkWmsPaymentMapper;

@Service
public class PaymentService {
	@Resource
	TkWmsPaymentMapper tkWmsPaymentMapper;

	public WmsPaymentDO getPaymentByPayNo(String payNo) {
		if (StringUtils.isBlank(payNo)) {
			return null;
		}
		WmsPaymentDO cond = new WmsPaymentDO();
		cond.setPayNo(payNo);
		cond.setIsDeleted("N");
		List<WmsPaymentDO> list = tkWmsPaymentMapper.select(cond);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public ResultBase<WmsPaymentDO> savePayment(WmsPaymentDO payment) {
		if (payment == null) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		String payNo = payment.getPayNo();
		Long id = payment.getId();
		if (StringUtils.isNotBlank(payNo) && id == null //
				|| id != null && StringUtils.isBlank(payNo)) {
			return ResultBaseBuilder.fails("id和payno必须同时提供").rb();
		}
		WmsPaymentDO paymentRecord = null;
		if (id != null && StringUtils.isNotBlank(payNo)) {
			WmsPaymentDO cond = new WmsPaymentDO();
			cond.setId(id);
			cond.setPayNo(payNo);
			cond.setIsDeleted("N");
			paymentRecord = tkWmsPaymentMapper.selectOne(cond);
		}
		//保存新的记录
		if (paymentRecord == null) {
			payment.setPayNo("P" + CommonUtils.uuid());
			tkWmsPaymentMapper.insert(payment);
		} else {
			tkWmsPaymentMapper.updateByPrimaryKeySelective(payment);
		}
		return ResultBaseBuilder.succ().data(payment).rb();
	}
}
