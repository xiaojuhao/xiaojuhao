package com.xjh.support.excel.model;
public class CfCell<T> {
	public CfSheet sheet;
	public CfRow row;
	public boolean isReadOnly;
	public int width = 0;
	public T value;
}
