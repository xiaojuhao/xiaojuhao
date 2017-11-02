package com.xjh.commons;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	int totalRows;
	int pageSize;
	int pageNo;
	int totalPages;
	List<T> values;
}
