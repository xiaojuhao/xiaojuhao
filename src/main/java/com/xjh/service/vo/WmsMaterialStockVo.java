package com.xjh.service.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class WmsMaterialStockVo implements Serializable{
	private static final long serialVersionUID = 1L;
	Long id;
	String materialName;
	String materialCode;
	String storeCode;

}
