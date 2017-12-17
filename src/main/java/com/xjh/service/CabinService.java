package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.valueobject.CabinVo;

public interface CabinService {
	public CabinVo getCabinByCode(String cabinCode);

	public List<String> getMyCabinCodes(WmsUserDO user);
}
