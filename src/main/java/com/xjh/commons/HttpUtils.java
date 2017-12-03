package com.xjh.commons;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http Utils
 * 
 * @author yinguoliang
 */
public class HttpUtils {
	static OkHttpClient client = new OkHttpClient.Builder() //
			.connectTimeout(3, TimeUnit.SECONDS) // 链接超时时间
			.writeTimeout(3, TimeUnit.SECONDS) // 写超时时间
			.readTimeout(3, TimeUnit.SECONDS) // 读超时时间
			.build();

	/**
	 * get request
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		Request request = new Request.Builder().url(url).build();
		String resp = client.newCall(request).execute().body().string();
		return resp;
	}

	/**
	 * get with params
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> params) throws IOException {
		return get(url, params, null);
	}

	/**
	 * get with params
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		StringBuilder requestUrl = new StringBuilder(url);
		if (!url.contains("?")) {
			requestUrl.append("?");
		}
		if (params != null) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue())) {
					requestUrl.append("&").append(e.getKey()).append("=").append(e.getValue());
				}
			}
		}
		Request.Builder requestBuilder = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> h : headers.entrySet()) {
				String headerKey = h.getKey();
				String headerVal = h.getValue();
				if (CommonUtils.isAnyBlank(headerKey, headerVal)) {
					continue;
				}
				requestBuilder.addHeader(headerKey, headerVal);
			}
		}
		Request request = requestBuilder.url(requestUrl.toString()).build();

		String resp = client.newCall(request).execute().body().string();
		return resp;
	}

	/**
	 * post request
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params) throws IOException {
		return post(url, params, null);
	}

	/**
	 * post request
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params, Map<String, String> headers) throws IOException {

		FormBody.Builder builder = new FormBody.Builder();
		if (params != null) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue())) {
					builder.add(e.getKey(), e.getValue());
				}
			}
		}
		FormBody body = builder.build();

		Request.Builder requestBuilder = new Request.Builder();
		if (headers != null && headers.size() > 0) {
			for (Map.Entry<String, String> h : headers.entrySet()) {
				String headerKey = h.getKey();
				String headerVal = h.getValue();
				if (CommonUtils.isAnyBlank(headerKey, headerVal)) {
					continue;
				}
				requestBuilder.addHeader(headerKey, headerVal);
			}
		}
		Request request = requestBuilder.url(url).post(body).build();
		Response resp = client.newCall(request).execute();
		return resp.body().string();
	}
}
