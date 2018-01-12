package com.xjh.support.excel.read;

/**
 * 单元格可用类型
 * 
 * @author ghliu
 * 
 */
public class ColT {
	/** 不对列进行类型转换 */
	public static final int NO = 0;
	/** 字符串类型 */
	public static final int CSTRING = 1;
	/** 整型 */
	public static final int CINT = 2;
	/** 单精度浮点 */
	public static final int CFLOAT = 3;
	/** 双精度浮点 */
	public static final int CDOUBLE = 4;
	/** 日期类型 */
	public static final int CDATE = 5;
	/** 长整型 */
	public static final int CLONG = 6;
	/** 代码值,使用该类型需要设置codeType */
	public static final int CCODE = 7;
}