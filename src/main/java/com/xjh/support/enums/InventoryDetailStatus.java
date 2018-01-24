package com.xjh.support.enums;

public enum InventoryDetailStatus {
	initial("0", "待提交"), //
	wait_inventory("1", "待入库"), //
	had_inventory("2", "已入库"), //
	discard("3", "已作废"), //
	unknown("999", "未知");

	String code = null;
	String remark = null;

	InventoryDetailStatus(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public String code() {
		return code;
	}

	public String remark() {
		return remark;
	}

	public static InventoryDetailStatus from(String code) {
		for (InventoryDetailStatus v : InventoryDetailStatus.values()) {
			if (v.code.equals(code)) {
				return v;
			}
		}
		return InventoryDetailStatus.unknown;
	}
}
