package com.xjh.commons;

import java.math.BigDecimal;

public class Fraction {
	int fenzi;
	int fenmu;

	public static Fraction from(int val) {
		Fraction f = new Fraction();
		f.fenmu = 1;
		f.fenzi = val;
		return f;
	}

	public static Fraction from(long val) {
		Fraction f = new Fraction();
		f.fenmu = 1;
		f.fenzi = (int) val;
		return f;
	}

	public static Fraction from(float val) {
		Fraction f = new Fraction();
		if (Math.abs(val) < 0.000000001) {
			f.fenzi = 0;
			f.fenmu = 0;
		}
		return from(val + "");
	}

	public static Fraction from(double val) {
		Fraction f = new Fraction();
		if (Math.abs(val) < 0.000000001) {
			f.fenzi = 0;
			f.fenmu = 0;
		}
		return from(val + "");
	}

	public static Fraction from(String str) {
		if (!CommonUtils.isDecimalOrFraction(str)) {
			return null;
		}
		Fraction f = new Fraction();
		if (CommonUtils.isDigital(str)) {
			f.fenmu = 1;
			f.fenzi = CommonUtils.parseInt(str, 0);
			return f;
		} else {
			int dotidx = str.indexOf(".");
			int dotlen = str.substring(dotidx + 1).length();
			f.fenmu = 1;
			for (int i = 0; i < dotlen; i++) {
				f.fenmu = f.fenmu * 10;
			}
			f.fenzi = new BigDecimal(CommonUtils.parseDouble(str, 0D) * f.fenmu).intValue();
			return f;
		}
	}

	public Fraction mul(Fraction f) {
		if (f == null) {
			return null;
		}
		Fraction ff = new Fraction();
		ff.fenmu = this.fenmu * f.fenmu;
		ff.fenzi = this.fenzi * f.fenzi;
		return ff;
	}

	public BigDecimal toBigDecimal() {
		if (this.fenzi == 0 || this.fenmu == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal fenzi = new BigDecimal(this.fenzi);
		BigDecimal fenmu = new BigDecimal(this.fenmu);
		return fenzi.divide(fenmu);
	}

	public double toDouble() {
		if (this.fenzi == 0 || this.fenmu == 0) {
			return 0D;
		}
		return toBigDecimal().doubleValue();
	}

	public String toString() {
		return fenzi + "/" + fenmu;
	}

	public static void main(String[] args) {
		System.out.println(from("0"));
		System.out.println(from("0.0"));
		System.out.println(from("0.00"));
		System.out.println(from("0.1"));
		System.out.println(from("0.12"));
		System.out.println(from("0.00123"));
		System.out.println(from("1.231"));
		System.out.println(from(0.456));
		System.out.println(from(112.456));
		System.out.println(from("1"));
		System.out.println(from("12"));
		System.out.println(from(12));
		System.out.println(from(12D));
	}
}
