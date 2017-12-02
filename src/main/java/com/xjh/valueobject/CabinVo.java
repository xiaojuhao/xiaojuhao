package com.xjh.valueobject;

import java.io.Serializable;

import lombok.Data;

@Data
public class CabinVo implements Serializable{
	private static final long serialVersionUID = 1L;
	String code;
	String name;
	String type;
	String status;
}
