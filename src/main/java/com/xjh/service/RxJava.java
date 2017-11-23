//package com.xjh.service;
//
//import io.reactivex.Observable;
//import io.reactivex.schedulers.Schedulers;
//
//public class RxJava {
//	public static void run() throws Exception {
//		Observable.just("aaaa") //
//				.map(s -> {
//					System.out.println(Thread.currentThread().getName() + ":" + s);
//					return s;
//				}).observeOn(Schedulers.newThread()) //
//				.map(s -> {
//					System.out.println(Thread.currentThread().getName() + ":" + s);
//					return s;
//				}).observeOn(Schedulers.computation())//
//				.map(s -> {
//					System.out.println(Thread.currentThread().getName() + ":" + s);
//					return s;
//				}).observeOn(Schedulers.io())//
//				.map(s -> {
//					System.out.println(Thread.currentThread().getName() + ":" + s);
//					return s;
//				}).subscribeOn(Schedulers.newThread())//
//				.subscribe(s -> {
//					System.out.println(Thread.currentThread().getName() + ":" + s);
//				});
//	}
//
//	public static void main(String[] args) throws Exception {
//		run();
//		Thread.sleep(1000);
//	}
//}
