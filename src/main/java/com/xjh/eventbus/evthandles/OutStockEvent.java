package com.xjh.eventbus.evthandles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.xjh.eventbus.IEvent;

import lombok.Data;

@Data
public class OutStockEvent implements IEvent {
	CountDownLatch latch = new CountDownLatch(1);
	//输入字段
	String materialCode;
	Double outstockAmt;
	String warehouseCode;
	String storeCode;
	String remark;
	String operator;
	
	//返回结果
	boolean success = false;
	String resultMsg;
	
	@Override
	public void await(long millisseconds) {
		try {
			latch.await(millisseconds, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void end() {
		latch.countDown();
	}
}
