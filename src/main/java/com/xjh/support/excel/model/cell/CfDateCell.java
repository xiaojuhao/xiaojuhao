package com.xjh.support.excel.model.cell;

import java.util.Date;

import com.xjh.support.excel.model.CfCell;

public class CfDateCell extends CfCell<Date>{
	public CfDateCell(Date date){
		this.value = date;
	}
}
