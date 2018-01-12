package com.xjh.support.excel.model.cell;

import java.math.BigDecimal;
import java.util.Date;

import com.xjh.support.excel.model.CfCell;

public class CfNumbericCell extends CfCell<Double> {
	public Date dateValue;//用户从excel中读取date字段
	
	public CfNumbericCell(Double val){
		this.value = val;
	}
	/**
	 * 根据给定的类型，将Numberic转换为期望的类型值<br>
	 * 这里枚举出各种可能
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T trans(Class<T> clz){
		Object obj = null;
		if(value==null){
			return (T)obj;
		}
		if(clz==Integer.class){
			obj = value.intValue();
		}else if(clz==CfIntegerCell.class){
			obj = new CfIntegerCell(value.intValue());
		}else if(clz==Long.class){
			obj = value.longValue();
		}else if(clz==CfLongCell.class){
			obj = new CfLongCell(value.longValue());
		}else if(clz==Date.class){
			obj = dateValue;
		}else if(clz==CfDateCell.class){
			obj = new CfDateCell(dateValue);
		}else if(clz==String.class){
			obj = value.toString();
		}else if(clz==CfStringCell.class){
			obj = new CfStringCell(value.toString());
		}else if(clz==BigDecimal.class){
		    obj = new BigDecimal(value);
		}else if(clz==Double.class){
			obj = value;
		}else if(clz==CfDoubleCell.class){
			obj = new CfDoubleCell(value);
		}else if(clz==Byte.class){
			obj = value.byteValue();
		}else if(clz==CfByteCell.class){
			obj = new CfByteCell(value.byteValue());
		}else if(clz==CfNullCell.class){
			obj = null;
		}else {
			throw new RuntimeException("unsupported type:"+clz);
		}
		return (T)obj;
	}
	
}
