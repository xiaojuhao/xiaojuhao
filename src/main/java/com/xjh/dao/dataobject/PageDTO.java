package com.xjh.dao.dataobject;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.Data;

@Data
public class PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Transient
	int pageSize = 20;
	@Transient
	int pageNo = 1;

	public int getStart() {
		return (pageNo - 1) * pageSize;
	}
}
