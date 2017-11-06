package com.xjh.event;

public interface IEvent {
	public void done();
	public void sync(long millisseconds) throws InterruptedException;
}
