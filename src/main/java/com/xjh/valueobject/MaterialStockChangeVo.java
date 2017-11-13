package com.xjh.valueobject;

import java.io.Serializable;

import lombok.Data;

@Data
public class MaterialStockChangeVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String materialCode;
	String cabinCode;
	double stockChgAmt;
	String operator;
}
