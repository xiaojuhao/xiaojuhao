package com.xjh.commons;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {
	public static String getCookie(HttpServletRequest request, String cookieName) {
		if (request == null) {
			return null;
		}
		if (request.getCookies() == null) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (StringUtils.equals(c.getName(), cookieName)) {
				return c.getValue();
			}
		}
		return null;
	}

	public static Cookie addCookie(HttpServletRequest request, //
			HttpServletResponse response, //
			String cookieName, //
			String cookieValue, //
			String domain) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		if (StringUtils.isNotBlank(domain)) {
			cookie.setDomain(domain);
		}
		String ctx = request.getContextPath();
		cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
		response.addCookie(cookie);
		return cookie;
	}

	public static Cookie deleteCookie(HttpServletRequest request, String cookieName) {
		if (request.getCookies() == null) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (StringUtils.equals(c.getName(), cookieName)) {
				c.setMaxAge(0);
				return c;
			}
		}
		return null;
	}

}
