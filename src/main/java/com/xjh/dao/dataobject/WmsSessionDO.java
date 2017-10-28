package com.xjh.dao.dataobject;

import java.util.Date;

import lombok.Data;

@Data
public class WmsSessionDO {
	Long id;
	String sessionId;
	Date expiredTime;
}
