package com.xjh.support.excel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xjh.support.excel.model.cell.CfBooleanCell;
import com.xjh.support.excel.model.cell.CfByteCell;
import com.xjh.support.excel.model.cell.CfDateCell;
import com.xjh.support.excel.model.cell.CfDoubleCell;
import com.xjh.support.excel.model.cell.CfIntegerCell;
import com.xjh.support.excel.model.cell.CfLongCell;
import com.xjh.support.excel.model.cell.CfNullCell;
import com.xjh.support.excel.model.cell.CfNumbericCell;
import com.xjh.support.excel.model.cell.CfStringCell;

/**
 * 
 * 类CfRow.java的实现描述：TODO 类实现描述 
 * @author yinguoliang 2016年9月21日 下午1:23:11
 */
public class CfRow {
	public CfSheet sheet = null;
	public int rowIdx = 0;
	public boolean isTitleRow = false;//
	public List<CfCell> cells = new ArrayList<CfCell>();

	public CfRow titled(boolean isTitle) {
		this.isTitleRow = isTitle;
		return this;
	}

	public CfRow addCell(CfCell... cs) {
		if (cs == null || cs.length == 0) {
			return this;
		}
		for (CfCell c : cs) {
			cells.add(c);
		}
		return this;
	}

	/**
	 * 在Row后面追加cell
	 * @param values
	 * @return
	 */
	public CfRow append(Object... values) {
		if (values == null || values.length == 0) {
			return this;
		}
		for (Object value : values) {
			CfCell cell = genCell(value);
			cell.sheet = this.sheet;
			cell.row = this;
			cells.add(cell);
		}
		return this;
	}

	/**
	 * 接受(key,value)的参数组合
	 * @param values
	 * @return
	 */
	public CfRow appendEx(Object... values) {
		if (values == null || values.length == 0) {
			return this;
		}
		if (values.length % 2 != 0) {
			throw new RuntimeException("【appendEx】输入参数不符合规范!");
		}
		//如果sheet如果当前row是第一行，提取标题信息
		if (sheet.rows.size() == 1) {
			//偶数位为标题
			for (int i = 0; i < values.length / 2; i++) {
				CfCell cell = genCell(values[2 * i]);
				cell.sheet = this.sheet;
				cell.row = this;
				cell.row.titled(true);
				cells.add(cell);
			}
			//追加内容
			sheet.newRow().appendEx(values);
			return sheet.lastRow();
		}

		//奇数位为内容
		for (int i = 0; i < values.length / 2; i++) {
			CfCell cell = genCell(values[2 * i + 1]);
			cell.sheet = this.sheet;
			cell.row = this;
			cells.add(cell);
		}
		return this;
	}

	/**
	 * 给当前sheet增加一行
	 * @return
	 */
	public CfRow newRow() {
		return sheet.newRow();
	}

	/**
	 * 读取指定位置的cell的值
	 * @param index
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T valueAt(int index, Class<T> clz) {
		if (index >= cells.size())
			return null;
		CfCell c = cells.get(index);
		if (c == null) {
			return null;
		} else if (c instanceof CfStringCell) {
			return (T) ((CfStringCell) c).value;
		} else if (c instanceof CfDateCell) {
			return (T) ((CfDateCell) c).value;
		} else if (c instanceof CfLongCell) {
			return (T) ((CfLongCell) c).value;
		} else if (c instanceof CfIntegerCell) {
			return (T) ((CfIntegerCell) c).value;
		} else if (c instanceof CfNumbericCell) {
			return (T) ((CfNumbericCell) c).trans(clz);
		} else if (c instanceof CfNullCell) {
			return null;
		} else if (c instanceof CfBooleanCell) {
			return (T) ((CfBooleanCell) c).value;
		} else {
			throw new RuntimeException("unsupported type:" + c.getClass());
		}
	}

	/**
	 * 根据title取cell内容（仅适用于第一行是Title内容的sheet）<br>
	 * 并且<strong>大小写敏感，包括空格</strong>
	 * @param title
	 * @param clz
	 * @return
	 */
	public <T> T valueAt(String title, Class<T> clz) {
		return valueAt(title, clz, null);
	}

	public <T> T valueAt(String title, Class<T> clz, T defVal) {
		Integer index = sheet.getTitleIndex().get(title);
		if (index != null && index >= 0) {
			return valueAt(index, clz);
		}
		return defVal;
	}

	/**
	 * 根据value生成CfCell对象
	 * @param value
	 * @return
	 */
	public CfCell genCell(Object value) {
		CfCell ret = null;
		if (value == null) {
			ret = new CfNullCell();
		} else if (value instanceof CfCell) {
			ret = (CfCell) value;
		} else if (value instanceof String) {
			ret = new CfStringCell((String) value);
		} else if (value instanceof Date) {
			ret = new CfDateCell((Date) value);
		} else if (value instanceof Long) {
			ret = new CfLongCell((Long) value);
		} else if (value instanceof Integer) {
			ret = new CfIntegerCell((Integer) value);
		} else if (value instanceof Double) {
			ret = new CfDoubleCell((Double) value);
		} else if (value instanceof Boolean) {
			ret = new CfBooleanCell((Boolean) value);
		} else if (value instanceof Byte) {
			ret = new CfByteCell((Byte) value);
		} else {
			throw new RuntimeException("unsupported type:" + value.getClass());
		}
		return ret;
	}

	public boolean isEmptyRow() {
		if (this.cells == null || this.cells.size() == 0) {
			return true;
		}
		for (CfCell c : cells) {
			if (c.value != null && !c.value.toString().trim().equals("")) {
				return false;
			}
		}
		return true;
	}
}
