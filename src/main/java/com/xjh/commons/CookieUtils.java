package com.xjh.commons;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {
	public static String getCookie(HttpServletRequest request,String cookieName){
		if(request == null){
			return null;
		}
		if(request.getCookies() == null){
			return null;
		}
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			if(StringUtils.equals(c.getName(), cookieName)){
				return c.getValue();
			}
		}
		return null;
	}
}
