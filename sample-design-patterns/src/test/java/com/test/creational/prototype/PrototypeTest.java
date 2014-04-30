package com.test.creational.prototype;

import org.junit.Test;

import com.design.patterns.creational.prototype.FactoryPrototype;
import com.design.patterns.creational.prototype.IProduct;

public class PrototypeTest {

	@Test
	public void test() {
		FactoryPrototype factory = new FactoryPrototype();
		IProduct product1 = (IProduct) factory.create("product1");
		IProduct product2= (IProduct) factory.create("product2");
		IProduct product3= (IProduct) factory.create("product3");
		IProduct product4= (IProduct) factory.create("product3");
		
		System.out.println("Este es el objeto creado: " + product1);
		System.out.println("Este es el objeto creado: " + product2);
		System.out.println("Este es el objeto creado: " + product3);
		System.out.println("Este es el objeto creado: " + product4);
		
	}
}
