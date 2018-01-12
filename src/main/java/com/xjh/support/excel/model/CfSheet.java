package com.xjh.support.excel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xjh.support.excel.model.cell.CfStringCell;
/**
 * 
 * 类CfSheet.java的实现描述：TODO 类实现描述 
 * @author yinguoliang 2016年9月21日 下午1:22:34
 */
public class CfSheet {
	public String name = null;
	public int sheetInx = 0;
	public boolean isHidden = false;
	public List<CfRow> rows = new ArrayList<CfRow>();
	public HashMap<Integer,Integer> columnWidth = new HashMap<Integer,Integer>();
	public HashMap<String,Integer> titleIndex = new HashMap<String,Integer>();
	
	public CfSheet addRow(CfRow ... rs){
		if(rs==null || rs.length==0) return this;
		for(CfRow r : rs){
			rows.add(r);
		}
		return this;
	}
	/**
	 * 创建一个Row,并追加到sheet的最后一行
	 * @return
	 */
	public CfRow newRow(){
		CfRow r = new CfRow();
		r.sheet = this;
		rows.add(r);
		return r;
	}
	public CfRow firstRow(){
		return rowAt(0);
	}
	public CfRow lastRow(){
		return rowAt(rows.size()-1);
	}
	public CfRow rowAt(int i){
		return rows.get(i);
	}
	public void minWidth(int column,int width){
		int minWidth = 0;
		if(columnWidth.containsKey(column)){
			minWidth = columnWidth.get(column);
		}
		if(minWidth<width){
			minWidth = width;
		}
		columnWidth.put(column, minWidth);
	}
	public CfSheet hide(boolean isHidden){
		this.isHidden = isHidden;
		return this;
	}
	public HashMap<String,Integer>  getTitleIndex(){
		if(titleIndex.size()>0 || rows.size()==0){
			return titleIndex;
		}
		synchronized(this){
			int i=0;
			for(CfCell cell : rows.get(0).cells){
			    if(cell instanceof CfStringCell){
			        CfStringCell str = (CfStringCell)cell;
	                titleIndex.put(str.value, i++);
			    }
			}
			return titleIndex;
		}
	}
}
