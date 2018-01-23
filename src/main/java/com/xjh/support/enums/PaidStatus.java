package com.xjh.support.enums;

public enum PaidStatus {
	not_paid("0", "待支付"), //
	paid_success("1", "支付成功"), //
	paid_fail("2", "支付失败"), //
	not_need_paid("3", "无需支付"), //
	unknown("999", "未知状态");

	String code = null;
	String remark = null;

	PaidStatus(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public String code() {
		return this.code;
	}

	public String remark() {
		return this.remark;
	}

	public static PaidStatus from(String code) {
		for (PaidStatus v : PaidStatus.values()) {
			if (v.code.equals(code)) {
				return v;
			}
		}
		return PaidStatus.unknown;
	}
}
