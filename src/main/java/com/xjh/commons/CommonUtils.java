package com.xjh.commons;

import java.util.Date;
import java.util.UUID;

public class CommonUtils {
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}
	
	public static Date future(long seconds){
		Date date = new Date();
		date.setTime(date.getTime()+seconds*1000);
		return date;
	}
}
