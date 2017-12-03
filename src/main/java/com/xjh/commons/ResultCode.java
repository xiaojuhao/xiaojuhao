package com.xjh.commons;

public enum ResultCode {
	success("200","1","操作成功","操作成功"),
	error("400","2","系统异常,请稍后!","默认错误"),
	no_login("R401","3","请登录","用户未登录"),
	rule_fails("R000","4","系统异常,请稍后","通用业务规则异常"),
	stock_over("R001","4","库存不足","库存不足"),
	personal_limit_over("R002","4","您已参与过","个人限制规则"),
	frequent_visit("600","6","请勿频繁访问","访问系统太频繁"),
	param_missing("601","6","入参错误","入参错误"),
	info_missing("604","6","信息不存在","信息缺失"),
	concurrent_error("602","6","并发操作","并发操作"),
	antifraud_error("603","6","客官请慢点","反欺诈错误"),
	activity_invalid("502","5","活动无效","活动无效"),
	activity_notstart("503","5","活动未开始","活动未开始"),
	activity_finished("504","5","活动已结束","活动已结束"),
	activity_config_error("505","5","活动配置错误","活动配置错误"),
	task_exists("1000","10","任务已经存在", "任务已经存在"),
	task_not_exists("1001","10","任务不存在", "任务不存在"),
	task_is_running("1002","10","任务正在执行", "任务正在执行");

	ResultCode(String code, String codeGroup, String msg1, String msg2) {
		this.code = code;
		this.codeGroup = codeGroup;
		this.msg1 = msg1;
	}

	private String code;
	private String codeGroup;
	private String msg1;

	public String msg() {
		return this.msg1 + " " + this.code;
	}

	public String code() {
		return this.code;
	}

	public String codeGroup() {
		return this.codeGroup;
	}
}
