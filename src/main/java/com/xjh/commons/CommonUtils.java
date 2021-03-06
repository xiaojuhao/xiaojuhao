package com.xjh.commons;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.xjh.dao.dataobject.WmsUserDO;

import net.coobird.thumbnailator.Thumbnails;

public class CommonUtils {
	static Logger log = LoggerFactory.getLogger(CommonUtils.class);
	private static char UNDERLINE = '_';
	private static String digitalPattern = "^[+-]?\\d+$";
	private static String decimalPattern = "^[+-]?\\d+(\\.?\\d*)$";
	private static String fractionPattern = "^[+-]?\\d+/[123456789]+$";
	private static String[] NUMBERS_SRC = new String[] { //
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", //
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	private static String[] LETTERS_SRC = new String[] { //
			"A", "a", "B", "b", "C", "D", "d", "E", "e", "F", "f", //
			"G", "g", "H", "h", "I", "i", "J", "j", "L", "M", "m", //
			"Q", "q", "R", "r", "T", "t", "Y", "y" };

	private static String[] WORDS_SRC = new String[] { //
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", //
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", //
			"A", "a", "B", "b", "C", "D", "d", "E", "e", "F", //
			"f", "G", "g", "H", "h", "I", "i", "J", "j", "L", //
			"M", "m", "Q", "q", "R", "r", "T", "t", "Y", "y" };
	// 默认随机密码长度
	private static int defaultPasswordLenght = 6;

	private static String[] dateFormats = { //
			"yyyy-MM-dd", //
			"yyyyMMdd", //
			"yyyy-MM-dd HH:mm:ss", //
			"yyyyMMdd HH:mm:ss", //
			"yyyy-MM-dd HH:mm:ss.S", //
			"yyyyDDdd HH:mm:ss.S" };

	private static Cache<String, Object> cache = CacheBuilder.newBuilder() //
			.maximumSize(5000) // 最多缓存的条数
			.expireAfterWrite(5, TimeUnit.MINUTES) // 缓存时间
			.build();
	private static Random random = new Random();

	public static Date future(long seconds) {
		Date date = new Date();
		date.setTime(date.getTime() + seconds * 1000);
		return date;
	}

	public static Date future(Date baseDate, long seconds) {
		if (baseDate == null) {
			baseDate = new Date();
		}
		Date date = new Date();
		date.setTime(baseDate.getTime() + seconds * 1000);
		return date;
	}

	public static Date futureYear(Date baseDate, int year) {
		if (baseDate == null) {
			baseDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		c.add(Calendar.YEAR, year);
		return c.getTime();
	}

	public static Date futureMonth(Date baseDate, int month) {
		if (baseDate == null) {
			baseDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	public static Date futureDays(Date baseDate, int days) {
		if (baseDate == null) {
			baseDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static <T> boolean isEqual(T a, T b) {
		if (a == null || b == null) {
			return false;
		} else {
			return a.equals(b);
		}
	}

	public static long intervalSeconds(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return -1;
		}
		return (date2.getTime() - date1.getTime()) / 1000;
	}

	/**
	 * 元素之间是否是偏序关系，即v1<=v2<=v3....
	 * 
	 * @param dates
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> boolean partiallyOrder(T... values) {
		if (values == null || values.length == 0) {
			return false;
		}
		boolean ret = true;
		T pre = values[0];
		for (T v : values) {
			if (v == null || greaterThan(pre, v)) {
				return false;
			}
			pre = v;
		}
		return ret;
	}

	public static <T extends Comparable<T>> boolean greaterThan(T v1, T v2) {
		if (v1 == null || v2 == null) {
			return false;
		}
		return v1.compareTo(v2) >= 1;
	}

	public static boolean isAllBlank(String... inputs) {
		if (inputs == null)
			return true;
		for (String input : inputs) {
			if (!isBlank(input))
				return false;
		}
		return true;
	}

	public static boolean isAnyBlank(String... inputs) {
		if (inputs == null)
			return true;
		for (String input : inputs) {
			if (isBlank(input))
				return true;
		}
		return false;
	}

	public static boolean isBlank(String input) {
		return input == null || input.trim().length() == 0;
	}

	public static boolean isPhone(String phoneNo) {
		if (isBlank(phoneNo)) {
			return false;
		}
		return phoneNo.matches("^1[3-8]\\d{9}$");
	}

	public static String maskPhone(String mobileNo) {
		if (mobileNo == null) {
			return "";
		}
		if (mobileNo.trim().length() == 11) {
			return mobileNo.substring(0, 3) + "****" + mobileNo.substring(7);
		}
		return "unkonwn";
	}

	public static String mask(String str) {
		if (str == null) {
			return str;
		}
		return mask(str, str.length() > 15 ? 15 : str.length());
	}

	public static String mask(String str, int rlen) {
		if (str == null || str.trim().length() == 0) {
			return str;
		}
		int len = str.length();
		if (len == 1)
			return loop("*", rlen);
		if (len == 2)
			return str.substring(0, 1) + loop("*", rlen - 1);
		if (len == 3 || len == 4) {
			return str.substring(0, 1) + loop("*", rlen - 2) + str.substring(len - 1);
		}
		if (len == 5) {
			return str.substring(0, 1) + loop("*", rlen - 3) + str.substring(len - 2);
		}
		if (len == 6 || len == 7 || len == 8 || len == 9) {
			return str.substring(0, 2) + loop("*", rlen - 4) + str.substring(len - 2);
		}
		return str.substring(0, 3) + loop("*", rlen - 7) + str.substring(len - 4);
	}

	public static String loop(String s, int loopSize) {
		StringBuffer buffer = new StringBuffer();
		while (loopSize > 0) {
			buffer.append(s);
			loopSize--;
		}
		return buffer.toString();
	}

	public static String uuid() {
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}

	public static String md5(String data) {
		if (data == null) {
			data = "";
		}
		return CodecUtils.md5(data.getBytes());
	}

	/**
	 * 随机数[min,max)，包括min,不包括max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomNumber(int min, int max) {
		if (min >= max) {
			return 0;
		}
		int delta = max - min;
		// 生成[0,delta)之间一个随机数
		int r = random.nextInt(delta);
		return r + min;
	}

	/**
	 * 随机字符（字母、数字混合）
	 * 
	 * @param len
	 * @return
	 */
	public static String randomString(int len) {
		if (len <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(WORDS_SRC.length);
			sb.append(WORDS_SRC[index]);
		}
		return sb.toString();
	}

	/**
	 * 随机数字
	 * 
	 * @param len
	 * @return
	 */
	public static String randomNumString(int len) {
		if (len <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(NUMBERS_SRC.length);
			sb.append(NUMBERS_SRC[index]);
		}
		return sb.toString();
	}

	/**
	 * 随机字符（仅字母）
	 * 
	 * @param len
	 * @return
	 */
	public static String randomLetters(int len) {
		if (len <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(LETTERS_SRC.length);
			sb.append(LETTERS_SRC[index]);
		}
		return sb.toString();
	}

	/**
	 * 随机密码生成（6位）
	 * 
	 * @return
	 */
	public static String randomPassword() {
		return randomString(defaultPasswordLenght);
	}

	/**
	 * 随机密码生成
	 * 
	 * @param length
	 *            密码长度
	 * @return
	 */
	public static String randomPassword(int length) {
		return randomString(length);
	}

	public static Map<String, PropertyDescriptor> getPD(Class<?> clz) throws IntrospectionException {
		Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
		if (clz == null) {
			return map;
		}
		BeanInfo info = Introspector.getBeanInfo(clz);
		PropertyDescriptor[] pds = info.getPropertyDescriptors();
		if (pds != null) {
			for (PropertyDescriptor pd : pds) {
				map.put(pd.getName(), pd);
			}
		}
		return map;
	}

	public static Map<String, Method> getMethods(Class<?> clz) {
		Map<String, Method> methods = new HashMap<String, Method>();
		Method[] ms = clz.getMethods();
		for (Method m : ms) {
			methods.put(m.getName(), m);
		}
		return methods;
	}

	public static <K, V> void copyMapValue(Map<K, V> dest, Map<K, V> src) {
		if (dest == null || src == null) {
			return;
		}
		for (Map.Entry<K, V> e : src.entrySet()) {
			dest.put(e.getKey(), e.getValue());
		}
	}

	public static void copyPropertiesQuietly(Object dest, Object src) {
		if (dest == null || src == null) {
			return;
		}
		try {
			Map<String, PropertyDescriptor> destPD = getPD(dest.getClass());
			Map<String, PropertyDescriptor> srcPD = getPD(src.getClass());
			for (Map.Entry<String, PropertyDescriptor> e : srcPD.entrySet()) {
				try {
					if (!destPD.containsKey(e.getKey()))
						continue;
					Method getter = e.getValue().getReadMethod();
					Method setter = destPD.get(e.getKey()).getWriteMethod();
					if (setter != null && getter != null) {
						setter.invoke(dest, getter.invoke(src));
					}
				} catch (Exception ex) {
					log.error("copy property error:" + e.getKey(), ex);
				}
			}
		} catch (IntrospectionException e1) {
			log.error("copy property error:", e1);
		}
	}

	public static void setProperty(Object target, String propertyName, Object value) {
		try {
			if (target == null || StringUtils.isBlank(propertyName)) {
				return;
			}
			Field[] fields = target.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (f.getName().equalsIgnoreCase(propertyName)) {
					f.setAccessible(true);
					f.set(target, value);
				}
			}
		} catch (Exception ex) {
			log.error("", ex);
		}
	}

	public static JSONObject toJSONObject(Object pojo) {
		if (pojo == null)
			return new JSONObject();
		if (pojo instanceof JSONObject) {
			return (JSONObject) pojo;
		}
		return JSONObject.parseObject(JSON.toJSONString(pojo));
	}

	public static String toJsonString(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
	}

	public static JSONObject parseJSON(String jsonStr) {
		JSONObject json = null;
		try {
			json = JSON.parseObject(jsonStr);
		} catch (Exception ex) {
			log.error("", ex);
		}
		if (json == null) {
			json = new JSONObject();
		}
		return json;
	}

	public static JSONArray parseJSONArray(String jsonStr) {
		if(StringUtils.isBlank(jsonStr)){
			return new JSONArray();
		}
		JSONArray json = null;
		try {
			json = JSON.parseArray(jsonStr);
		} catch (Exception ex) {
			log.error("", ex);
		}
		if (json == null) {
			json = new JSONArray();
		}
		return json;
	}

	/**
	 * email加密显示
	 * 
	 * @param email
	 * @return
	 */
	public static String replaceEmail(String email) {
		try {
			String userName = email.substring(0, email.indexOf("@"));
			String reg = email.substring(email.indexOf("@"), email.length());
			if (userName.length() <= 3) {
				return "***" + reg;
			} else {
				return email.substring(0, email.indexOf("@") - 3) + "***" + reg;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return email;
		}

	}

	public static String reflectString(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			if (obj instanceof Map) {
				return obj.getClass() + " " + JSONObject.toJSONString(obj);
			}
			return ToStringBuilder.reflectionToString(obj);
		} catch (Exception ex) {
			log.error("", ex);
			return null;
		}
	}

	public static JSONObject asJSONObject(Object... values) {
		if (values == null) {
			return new JSONObject();
		}
		JSONObject json = new JSONObject();
		for (int i = 0; i < values.length; i = i + 2) {
			json.put(values[i].toString(), values[i + 1]);
		}
		return json;
	}

	public static Set<String> splitAsSet(String str, String regex) {
		Set<String> set = new HashSet<String>();
		if (isBlank(str) || isBlank(regex)) {
			return set;
		}
		String[] arr = str.split(regex);
		for (String s : arr) {
			if (!isBlank(s)) {
				set.add(s);
			}
		}
		return set;
	}

	public static List<String> retrieveNumbers(String str) {
		List<String> list = new ArrayList<>();
		if (str == null || str.trim().length() == 0) {
			return list;
		}
		StringBuffer sb = new StringBuffer();
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c);
			} else if (sb.length() != 0) {
				list.add(sb.toString());
				sb.setLength(0);
			}
		}
		if (sb.length() != 0) {
			list.add(sb.toString());
		}
		return list;
	}

	public static List<String> splitAsList(String str, String regex) {
		List<String> list = new ArrayList<String>();
		if (isBlank(str) || isBlank(regex)) {
			return list;
		}
		String[] arr = str.split(regex);
		for (String s : arr) {
			if (!isBlank(s)) {
				list.add(s.trim());
			}
		}
		return list;
	}

	public static <R, T> List<R> cloneList(List<T> list, Class<R> clz) {
		List<R> ret = new ArrayList<R>();
		if (list == null || list.size() == 0) {
			return ret;
		}
		for (T t : list) {
			R r = BeanUtils.instantiate(clz);
			copyPropertiesQuietly(r, t);
			ret.add(r);
		}
		return ret;
	}

	/**
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<T>> T max(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		T max = null;
		for (T t : list) {
			if (max == null) {
				max = t;
				continue;
			}
			if (max.compareTo(t) < 0) {
				max = t;
			}
		}
		return max;
	}

	public static <T extends Comparable<T>> T min(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		T min = null;
		for (T t : list) {
			if (min == null) {
				min = t;
				continue;
			}
			if (min.compareTo(t) > 0) {
				min = t;
			}
		}
		return min;
	}

	public static <T> T max(List<T> list, Comparator<T> cmp) {
		if (list == null || list.size() == 0) {
			return null;
		}
		T max = null;
		for (T t : list) {
			if (max == null) {
				max = t;
				continue;
			}
			if (cmp.compare(max, t) < 0) {
				max = t;
			}
		}
		return max;
	}

	public static <T> T min(List<T> list, Comparator<T> cmp) {
		if (list == null || list.size() == 0) {
			return null;
		}
		T min = null;
		for (T t : list) {
			if (min == null) {
				min = t;
				continue;
			}
			if (cmp.compare(min, t) > 0) {
				min = t;
			}
		}
		return min;
	}

	public static boolean isDigital(String input) {
		if (input == null || input.length() == 0) {
			return false;
		}
		return Pattern.matches(digitalPattern, input);
	}

	public static Boolean parseBoolean(String bool, Boolean defaultValue) {
		if ("true".equalsIgnoreCase(bool)) {
			return true;
		}
		if ("false".equalsIgnoreCase(bool)) {
			return false;
		}
		return defaultValue;
	}

	public static Long parseLong(String str, Long defaultValue) {
		if (!isDigital(str)) {
			return defaultValue;
		}
		return Long.parseLong(str);
	}

	public static String get(HttpServletRequest request, String paramName) {
		String val = request.getParameter(paramName);
		if (StringUtils.isBlank(val)) {
			val = null;
		}
		return val;
	}

	public static List<String> mycabins(WmsUserDO user) {
		if (user == null) {
			return new ArrayList<>();
		}
		List<String> mycabins = new ArrayList<>();
		mycabins.addAll(splitAsList(user.getAuthStores(), ","));
		mycabins.addAll(splitAsList(user.getAuthWarehouse(), ","));
		return mycabins;
	}

	public static Date getDate(HttpServletRequest request, String paramName) {
		String val = request.getParameter(paramName);
		return parseDate(val);
	}

	public static Long getLong(HttpServletRequest request, String paramName) {
		String val = request.getParameter(paramName);
		return parseLong(val, null);
	}

	public static Integer getInt(HttpServletRequest request, String paramName) {
		String val = request.getParameter(paramName);
		return parseInt(val, null);
	}

	public static Double getDbl(HttpServletRequest request, String paramName, Double def) {
		BigDecimal val = parseBigDecimal(request.getParameter(paramName), null);
		if (val == null) {
			return def;
		}
		return val.doubleValue();
	}

	public static int getPageSize(HttpServletRequest request) {
		if (request == null) {
			return 10;
		}
		int pageSize = parseInt(request.getParameter("pageSize"), 10);
		if (pageSize <= 0) {
			pageSize = 10;
		}
		return pageSize;
	}

	public static int getPageNo(HttpServletRequest request) {
		if (request == null) {
			return 1;
		}
		int pageNo = parseInt(request.getParameter("pageNo"), 1);
		if (pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}

	public static Integer parseInt(String str, Integer defaultValue) {
		if (!isDigital(str)) {
			return defaultValue;
		}
		Long val = Long.parseLong(str);
		if (val > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (val < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return val.intValue();
	}

	public static Date parseDate(String dateValue) {
		if (StringUtils.isBlank(dateValue)) {
			return null;
		}
		try {
			return DateUtils.parseDate(dateValue, dateFormats);
		} catch (Exception ex) {
			log.error("format date error:" + dateValue, ex);
		}
		return null;
	}

	public static Date parseDate(String dateValue, String dateFormats) {
		if (StringUtils.isBlank(dateValue)) {
			return null;
		}
		try {
			return DateUtils.parseDate(dateValue, new String[] { dateFormats });
		} catch (Exception ex) {
			log.error("format date error:" + dateValue, ex);
		}
		return null;
	}

	public static String formatDate(Date date, String dateFormats) {
		try {
			return new SimpleDateFormat(dateFormats).format(date);
		} catch (Exception ex) {
			log.error("format date error:" + dateFormats, ex);
		}
		return null;
	}

	public static Double parseDouble(String numberStr, Double defaultVal) {
		BigDecimal r = parseBigDecimal(numberStr);
		//注意这里有自动拆箱，如果defaultVal==null,会自动拆箱，报Null异常
		//return r == null ? defaultVal : r.doubleValue(); 
		if (r != null) {
			return r.doubleValue();
		}
		return defaultVal;
	}

	public static BigDecimal parseBigDecimal(String numberStr, BigDecimal defaultVal) {
		BigDecimal r = parseBigDecimal(numberStr);
		return r == null ? defaultVal : r;
	}

	public static BigDecimal parseBigDecimal(String numberStr) {
		if (numberStr == null || numberStr.trim().length() == 0) {
			return null;
		}
		if (!Pattern.matches(decimalPattern, numberStr)) {
			return null;
		}
		try {
			return new BigDecimal(numberStr);
		} catch (Exception ex) {
			log.error("", ex);
			return null;
		}
	}

	public static boolean isDecimal(String input) {
		if (StringUtils.isBlank(input)) {
			return false;
		}
		return Pattern.matches(decimalPattern, input);
	}

	public static boolean isDecimalOrFraction(String input) {
		if (StringUtils.isBlank(input)) {
			return false;
		}
		return Pattern.matches(decimalPattern, input) //
				|| Pattern.matches(fractionPattern, input);
	}

	public static boolean isFraction(String input) {
		if (StringUtils.isBlank(input)) {
			return false;
		}
		return Pattern.matches(fractionPattern, input);
	}

	@SuppressWarnings("unchecked")
	public static Set<String> localIp() {
		Callable<Set<String>> call = new Callable<Set<String>>() {
			public Set<String> call() throws Exception {
				Set<String> ips = new HashSet<String>();
				Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
				while (allNetInterfaces.hasMoreElements()) {
					NetworkInterface net = allNetInterfaces.nextElement();
					Enumeration<InetAddress> addrEnum = net.getInetAddresses();
					while (addrEnum.hasMoreElements()) {
						InetAddress addr = addrEnum.nextElement();
						String ip = addr.getHostAddress();
						if ("127.0.0.1".equals(ip)) {
							continue;
						}
						ips.add(ip);
					}
				}
				return ips;
			}
		};
		try {
			Set<String> ips = (Set<String>) cache.get("local_ip_set_", call);
			Set<String> copy = new HashSet<String>();
			copy.addAll(ips);
			return copy;
		} catch (ExecutionException ex) {
			log.error("get local ip", ex);
		}
		return new HashSet<String>();
	}

	public static String remoteIp(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 将入参对象转换为string
	 * 
	 * @param obj
	 * @return
	 */
	public static String string(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}

	public static Date todayDate() {
		return parseDate(stringOfToday("yyyyMMdd"), "yyyyMMdd");
	}

	public static String stringOfNow() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String stringOfToday(String datePatten) {
		return new SimpleDateFormat(datePatten).format(new Date());
	}

	public static String stringOfYesterday() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
	}

	public static String endString(String src, int len) {
		if (src == null || src.length() == 0) {
			return src;
		}
		if (src.length() > len) {
			return src.substring(src.length() - len);
		}
		return src;
	}

	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String toDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	public static char toDBC(char c) {
		if (c == '\u3000') {
			c = ' ';
		} else if (c > '\uFF00' && c < '\uFF5F') {
			c = (char) (c - 65248);
		}
		return c;
	}

	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void sleepQuietly(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			log.debug("", e);
		}
	}

	public static HashMap<String, Object> json2Map(String jsonStr) {
		if (isBlank(jsonStr)) {
			return null;
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		for (Entry<String, Object> entry : jsonObject.entrySet()) {
			resultMap.put(entry.getKey(), entry.getValue());
		}
		return resultMap;
	}

	public static boolean isContains(String str, String multiStr, String fomat) {
		String temp[] = StringUtils.split(multiStr, fomat);
		return containString(str, temp);
	}

	public static boolean containString(String keyStr, String... StrArray) {
		if (StrArray == null || StrArray.length == 0) {
			return false;
		}
		for (String str : StrArray) {
			if (StringUtils.equals(str, keyStr)) {
				return true;
			}
		}
		return false;
	}

	public static Date current() {
		return new Date();
	}

	public static void closeQuietly(Closeable... closeables) {
		if (closeables == null || closeables.length == 0) {
			return;
		}
		for (Closeable c : closeables) {
			try {
				if (c != null)
					c.close();
			} catch (IOException e) {
				log.error("close error", e);
			}
		}
	}

	/**
	 * byte转hex
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		return CodecUtils.encodeHexString(bytes);
	}

	/**
	 * hex字符串转byte
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexToBytes(String hex) {
		return CodecUtils.hexStringToByte(hex);
	}

	public static <T> List<T> subList(List<T> input, int start, int end) {
		if (input == null) {
			return Lists.newArrayList();
		}
		List<T> list = new ArrayList<>();
		int total = input.size();
		for (int i = start; i < end && i < total; i++) {
			list.add(input.get(i));
		}
		return list;
	}

	public static String toPinYin(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			return PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String genSearchKey(String input, String base) {
		if (StringUtils.isBlank(input)) {
			return base;
		}
		Set<String> set = new HashSet<>();
		if (StringUtils.isNotBlank(base)) {
			for (String str : base.split(",")) {
				if (StringUtils.isNotBlank(str))
					set.add(str.trim());
			}
		}
		String[] array = input.split(",");
		for (String src : array) {
			if (StringUtils.isBlank(src)) {
				continue;
			}
			try {
				String pinyin = PinyinHelper.convertToPinyinString(src, ",", PinyinFormat.WITHOUT_TONE);
				set.add(pinyin.replaceAll(",", ""));
				StringBuffer hh = new StringBuffer();
				for (String str : pinyin.split(",")) {
					if (StringUtils.isNotBlank(str))
						hh.append(str.substring(0, 1));
				}
				set.add(hh.toString());
			} catch (PinyinException e) {
				e.printStackTrace();
			}
		}
		return set.stream().collect(Collectors.joining(","));
	}

	public static void reduceImg(File src, File dst) {
		try {
			Thumbnails.of(src).scale(0.5).outputQuality(0.8).toFile(dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String fileSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		}
		int index = fileName.lastIndexOf(".");
		if (index <= 0) {
			return null;
		}
		String suffix = fileName.substring(index + 1);
		return StringUtils.isBlank(suffix) ? null : suffix;
	}

	static List<String> imageTypes = Arrays.asList("jpg", "jpeg", "png", "gif", "webp", "bmp", "ico");

	public static boolean isImage(String suffix) {
		if (StringUtils.isBlank(suffix)) {
			return false;
		}
		return imageTypes.contains(suffix.toLowerCase());
	}

	static String letters[] = { //
			"2", "3", "4", "5", "6", "7", "8", "9", //
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", //
			"k", "m", "n", "p", "q", "r", "s", "t", //
			"u", "v", "w", "x", "y", "z" };

	public static int setBit(int val, int pos, boolean b) {
		int mask0 = 0xFFFFFFFF;
		int mask1 = 0x0;
		mask0 = mask0 >> pos;
		mask0 = mask0 & 0xFFFFFFFE;
		if (b) {
			mask1 = mask1 | 0b1;
		}
		while (pos-- > 0) {
			mask0 = mask0 << 1 | 0b1;
			mask1 = mask1 << 1;
		}
		return val & mask0 | mask1;
	}

	/**
	 * 在val的第pos位插入一个bit位
	 * @param val
	 * @param pos
	 * @param b
	 * @return
	 */
	public static long insertBit(final long val, final int pos, final int b) {
		long r = val >> pos;
		r = r << 1 | (b == 0 ? 0 : 1);
		long mask = 0;
		int tmpPos = pos;
		while (tmpPos-- > 0) {
			mask = mask << 1 | 1;
		}
		long tail = val & mask;
		r = r << pos | tail;
		return r;
	}

	public static int getBit(int val, int pos) {
		val = val >> pos;
		return val & 0b1;
	}

	public static int swapbit(int val, int pos1, int pos2) {
		boolean b1 = getBit(val, pos1) == 1;
		boolean b2 = getBit(val, pos2) == 1;
		val = setBit(val, pos2, b1);
		val = setBit(val, pos1, b2);
		return val;
	}

	private static String getLetters(int i) {
		int b1 = i & 0B1;
		int b2 = i >> 1 & 0B1;
		int b3 = i >> 2 & 0B1;
		int b4 = i >> 3 & 0B1;
		int b5 = i >> 4 & 0B1;
		int r = b3 | (b5 << 1) | (b1 << 2) | (b4 << 3) | (b2 << 4);
		return letters[r];
	}

	public static String stirNumber(long num) {
		StringBuilder sb = new StringBuilder();
		long i = 0;
		while (num > 0) {
			i = num & 0B1111;
			num = num >> 4;
			int mask = 0;
			if (random.nextInt() % 2 == 0) {
				mask = 1;
			}
			i = i << 1 | mask;
			boolean upper = random.nextInt() % 2 == 0;
			if (upper) {
				sb.append(getLetters((int) i));
			} else {
				sb.append(getLetters((int) i));
			}
		}
		return sb.reverse().toString();
	}

	public static long randomize(long input) {
		if (input <= 0) {
			return 0L;
		}
		//int len = Long.toBinaryString(input).length();
		long out = input;
		int pos = 5;
		while (input > 0) {
			input = input >> 5;
			//System.out.println("insert bit at " + pos);
			out = insertBit(out, pos, random.nextBoolean() ? 0 : 1);
			pos += 6;
		}
		return out;
	}

	public static void main(String[] args) {
		long base = 21955551L;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			//System.out.println("----------" + i + "---------");
			//base = base << 1 | 1;
			long src = randomize(base + i);
			String encoded = Base62.encode(src);
			long decoded = Base62.decode(encoded);
			System.out.println(src + ":" + encoded + ":" + decoded);
			//			System.out.println(base + "=" + Base62.encode(randomize(base + 1)));
			//			System.out.println(base + "=" + Base62.encode(randomize(base + 2)));
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
