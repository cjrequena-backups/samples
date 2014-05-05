package com.test.creational.prototype;

import org.junit.Test;

import com.design.patterns.creational.prototype.FactoryPrototype;
import com.design.patterns.creational.prototype.IProduct;

/* 
 * The prototype pattern is a creational design pattern in software development. It is used when the type of objects to create is determined by a 
 * prototypical instance, which is cloned to produce new objects. This pattern is used to:
 * 
 * avoid subclasses of an object creator in the client application, like the abstract factory pattern does.
 * avoid the inherent cost of creating a new object in the standard way (e.g., using the 'new' keyword) when it is prohibitively expensive for a given application.
 * 
 * To implement the pattern, declare an abstract base class that specifies a pure virtual clone() method. Any class that needs a "polymorphic constructor" capability derives itself from the abstract base class, and implements the clone() operation.
 * The client, instead of writing code that invokes the "new" operator on a hard-coded class name, calls the clone() method on the prototype, calls a factory method with a parameter designating the particular concrete derived class desired, or invokes the clone() method through some mechanism provided by another design pattern.
 */
public class PrototypeTest {

	@Test
	public void test() {
		FactoryPrototype factory = new FactoryPrototype();
		IProduct product1 = (IProduct) factory.create("product1");
		IProduct product2 = (IProduct) factory.create("product2");
		IProduct product3 = (IProduct) factory.create("product3");
		IProduct product4 = (IProduct) factory.create("product3");

		System.out.println("Este es el objeto creado: " + product1);
		System.out.println("Este es el objeto creado: " + product2);
		System.out.println("Este es el objeto creado: " + product3);
		System.out.println("Este es el objeto creado: " + product4);

	}
}
