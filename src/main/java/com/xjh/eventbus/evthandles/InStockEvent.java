//package com.xjh.eventbus.evthandles;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import com.xjh.eventbus.IEvent;
//
//import lombok.Data;
//
//@Data
//public class InStockEvent implements IEvent {
//	CountDownLatch latch = new CountDownLatch(1);
//	// request
//	String materialCode;
//	double instockAmt;
//	String operator;
//	String cabCode;
//	String remark;
//	// response
//	boolean success;
//	String resultMsg;
//
//	@Override
//	public void await(long millisseconds) {
//		try {
//			latch.await(millisseconds, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	public void end() {
//		latch.countDown();
//	}
//}
