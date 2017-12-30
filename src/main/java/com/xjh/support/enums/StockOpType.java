package com.xjh.support.enums;

public enum StockOpType {
	IN_STOCK("instock", "instock", "入库"), //
	OUT_STOCK("out_stock", "out_stock", "出库"), //
	SALE("sale", "sale", "销售"), //
	CLAIM_LOSS("claim_loss", "claim_loss", "报损"), //
	CORRECT("correct", "correct", "盘点"), //
	CORRECT_DELTA("correct_delta", "correct_delta", "盘点损耗"), //
	UNKONWN("unkown", "unknown", "未知类型");
	String code = null;
	String code2 = null;

	StockOpType(String code, String code2, String title) {
		this.code = code;
		this.code2 = code2;
	}

	public static StockOpType fromCode(String code) {
		for (StockOpType type : StockOpType.values()) {
			if (type.code.equals(code)) {
				return type;
			}
		}
		return StockOpType.UNKONWN;
	}
}
