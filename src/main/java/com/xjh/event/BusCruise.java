package com.xjh.event;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class BusCruise {
	static Executor executor = Executors.newFixedThreadPool(20);
	static EventBus bus1 = new AsyncEventBus(executor);
	static EventBus bus2 = new EventBus();

	public static void registerHandler(Object handler) {
		bus1.register(handler);
		bus2.register(handler);
	}

	public static void post(IEvent event) {
		post(event, false);// 同步处理
	}
	/**
	 * 异步处理任务
	 * @param event
	 * @param async
	 */
	public static void post(IEvent event, boolean async) {
		if (async) {
			bus1.post(event);
		} else {
			bus2.post(event);
		}
	}
}
