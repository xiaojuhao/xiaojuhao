package com.test;

import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String args[]){
		List<String> names = Arrays.asList("aaa","bbb","cccc");
		names.forEach(name -> {
			System.out.println(name);
		});
		System.out.println("===================");
		names.forEach(System.out::println);
	}
}
