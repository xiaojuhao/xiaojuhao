package com.xjh.commons;

import java.util.Calendar;
import java.util.Date;

public class DateBuilder {
	private Calendar calendar = Calendar.getInstance();

	public static DateBuilder newInstance() {
		return new DateBuilder();
	}

	public DateBuilder base(Date date) {
		if (date != null)
			calendar.setTime(date);
		return this;
	}

	public DateBuilder zeroAM() {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return this;
	}

	public DateBuilder futureDays(int i) {
		calendar.add(Calendar.DATE, i);
		return this;
	}

	public DateBuilder futureHours(int i) {
		calendar.add(Calendar.HOUR, i);
		return this;
	}

	public DateBuilder futureMinutes(int i) {
		calendar.add(Calendar.MINUTE, i);
		return this;
	}

	public DateBuilder futureSeconds(int i) {
		calendar.add(Calendar.SECOND, i);
		return this;
	}

	public DateBuilder year(int year) {
		calendar.set(Calendar.YEAR, year);
		return this;
	}

	public DateBuilder month(int month) {
		if (month <= 0 || month > 12) {
			throw new RuntimeException("月份值不对:" + month);
		}
		calendar.set(Calendar.MONTH, month - 1);
		return this;
	}

	public DateBuilder day(int day) {
		if (day < 0 || day > 31) {
			throw new RuntimeException("day值不对:" + day);
		}
		calendar.set(Calendar.DATE, day);
		return this;
	}

	public DateBuilder hour(int hour) {
		if (hour < 0 || hour > 23) {
			throw new RuntimeException("hour值不对:" + hour);
		}
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		return this;
	}

	public DateBuilder minute(int minute) {
		if (minute < 0 || minute > 59) {
			throw new RuntimeException("minute值不对:" + minute);
		}
		calendar.set(Calendar.MINUTE, minute);
		return this;
	}

	public DateBuilder second(int second) {
		if (second < 0 || second > 59) {
			throw new RuntimeException("second值不对:" + second);
		}
		calendar.set(Calendar.SECOND, second);
		return this;
	}

	public Date date() {
		return calendar.getTime();
	}
}
