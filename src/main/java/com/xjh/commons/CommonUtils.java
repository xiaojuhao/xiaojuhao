package com.xjh.commons;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}
	
	public static Date future(long seconds){
		Date date = new Date();
		date.setTime(date.getTime()+seconds*1000);
		return date;
	}
	
	public static Long parseLong(String str,Long defaultVal){
		if(StringUtils.isBlank(str)){
			return defaultVal;
		}
		try{
			return Long.parseLong(str);
		}catch(Exception ex){
			return defaultVal;
		}
	}
}
