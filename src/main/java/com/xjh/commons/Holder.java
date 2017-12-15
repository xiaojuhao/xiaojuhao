package com.xjh.commons;

/**
 * 对象Holder类
 * @author yinguoliang
 *
 * @param <T>
 */
public class Holder<T> {
	T t = null;
	public Holder(T t){
		this.t = t;
	}
	public Holder(){
		// nothing to do 
	}
	public T get(){
		return t;
	}
	public void set(T t){
		this.t= t;
	}
}
