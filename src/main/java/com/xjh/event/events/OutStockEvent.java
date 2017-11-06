package com.xjh.event.events;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.xjh.event.IEvent;

import lombok.Data;

@Data
public class OutStockEvent implements IEvent {
	CountDownLatch latch = new CountDownLatch(1);
	String materialCode;
	Double outstockAmt;
	String warehouseCode;
	String storeCode;
	String remark;

	@Override
	public void sync(long millisseconds) {
		try {
			latch.await(millisseconds, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void done() {
		latch.countDown();
	}
}
