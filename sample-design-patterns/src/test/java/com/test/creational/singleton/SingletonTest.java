package com.test.creational.singleton;

import org.junit.Test;

import com.design.patterns.creational.singleton.Singleton;

public class SingletonTest {

	@Test
	public void test(){
		Singleton obj1 = Singleton.getInstance();
		obj1.setName("OBJE1");
		Singleton obj2 = Singleton.getInstance();
		System.out.println( obj2.getName());
	}
}
