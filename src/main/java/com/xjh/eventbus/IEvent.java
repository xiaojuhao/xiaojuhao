package com.xjh.eventbus;

public interface IEvent {
	/**
	 * 事件结束（包括处理成功和失败）
	 */
	public void end();
	/**
	 * 等待事件结束
	 * @param millisseconds
	 * @throws InterruptedException
	 */
	public void await(long millisseconds) throws InterruptedException;
}
