package com.xjh.commons;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class ResultBaseBuilder {
	private ResultBase<Object> rb = new ResultBase<>();
	public static ResultBaseBuilder succ(){
		return succ("操作成功",null);
	}
	public static ResultBaseBuilder succ(String message){
		return succ(message,null);
	}
	public static ResultBaseBuilder succ(String message, Object data){
		ResultBaseBuilder rbb = new ResultBaseBuilder();
		rbb.rb.setCode("200");
		rbb.rb.setIsSuccess(true);
		rbb.rb.setMessage(message);
		rbb.rb.setValue(data);
		return rbb;
	}
	public static ResultBaseBuilder fails(String message){
		return fails(message,"400",null);
	}
	public static ResultBaseBuilder fails(String message,String code){
		return fails(message,code,null);
	}
	public static ResultBaseBuilder fails(String message,String code,Object data){
		ResultBaseBuilder rbb = new ResultBaseBuilder();
		rbb.rb.setCode(code==null?"400":code);
		rbb.rb.setIsSuccess(false);
		rbb.rb.setMessage(message);
		rbb.rb.setValue(data);
		return rbb;
	}
	
	public static ResultBaseBuilder fails(ResultCode rc){
		return fails(rc.msg(),rc.code());
	}
	
	public static ResultBaseBuilder fails(ResultCode rc,String message){
		return fails(message,rc.code());
	}
	
	public static<T> ResultBaseBuilder wrap(ResultBase<T> rb){
		ResultBaseBuilder rbb = new ResultBaseBuilder();
		rbb.rb.setCode(rb.getCode());
		rbb.rb.setIsSuccess(rb.getIsSuccess());
		rbb.rb.setMessage(rb.getMessage());
		rbb.rb.setValue(rb.getValue());
		return rbb;
	}
	public ResultBaseBuilder toFails(){
		this.rb.setIsSuccess(false);
		if("200".equals(rb.getCode())){
			rb.setCode("400");
		}
		return this;
	}
	public ResultBaseBuilder toFails(String errorMsg){
		this.rb.setIsSuccess(false);
		this.rb.setMessage(errorMsg);
		if("200".equals(rb.getCode())){
			rb.setCode("400");
		}
		return this;
	}
	public ResultBaseBuilder toSucc(){
		this.rb.setIsSuccess(true);
		this.rb.setCode("200");
		return this;
	}
	
	public ResultBaseBuilder code(String code){
		this.rb.setCode(code);
		return this;
	}
	
	public ResultBaseBuilder msg(String msg){
		this.rb.setMessage(msg);
		return this;
	}
	
	public ResultBaseBuilder data(Object data){
		this.rb.setValue(data);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public<T> ResultBase<T> rb(){
//		if(!"200".equals(rb.getCode())){
//			String msg = rb.getMessage();
//			String mcd = "["+rb.getCode()+"]";
//			if(msg != null && !msg.endsWith(mcd)){
//				rb.setMessage(rb.getMessage()+"["+rb.getCode()+"]");
//			}
//		}
		return (ResultBase<T>) rb;
	}
	
	public Object rb(HttpServletRequest request){
		if(request == null){
			return rb();
		}
		String callback = request.getParameter("callback");
		if(StringUtils.isBlank(callback)){
			return rb();
		} else {
			return callback+"("+JSON.toJSONString(rb())+")";
		}
	}
}
