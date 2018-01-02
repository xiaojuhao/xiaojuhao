package com.xjh.dao.mapper;

import com.xjh.dao.dataobject.WmsPaymentDO;

public interface WmsPaymentMapper {
	public WmsPaymentDO queryByPayNo(String payNo);
}
