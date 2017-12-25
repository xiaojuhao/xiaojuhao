package com.xjh.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUtils {
	static ExecutorService es = Executors.newFixedThreadPool(1);

	public static void schedule(Runnable r) {
		es.submit(new SafeRunnable(r));
	}

	static class SafeRunnable implements Runnable {
		Runnable run = null;

		public SafeRunnable(Runnable r) {
			this.run = r;
		}

		public void run() {
			try {
				if (run != null) {
					run.run();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
