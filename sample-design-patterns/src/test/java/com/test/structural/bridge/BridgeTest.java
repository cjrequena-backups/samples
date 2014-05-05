package com.test.structural.bridge;

import org.junit.Test;

import com.design.patterns.structural.bridge.AbstractImpl;
import com.design.patterns.structural.bridge.IAbstract;
import com.design.patterns.structural.bridge.ImplementationA;
import com.design.patterns.structural.bridge.ImplementationB;
/*
 * The bridge pattern is a design pattern used in software engineering which is meant to "decouple an abstraction from its implementation so that 
 * the two can vary independently".[1] The bridge uses encapsulation, aggregation, and can use inheritance to separate responsibilities into 
 * different classes.
 * 
 * When a class varies often, the features of object-oriented programming become very useful because changes to a program's code can be made easily
 * with minimal prior knowledge about the program. The bridge pattern is useful when both the class as well as what it does vary often. The class 
 * itself can be thought of as the implementation and what the class can do as the abstraction. The bridge pattern can also be thought of as two 
 * layers of abstraction.
 */
public class BridgeTest {

	@Test
	public void test() {
		IAbstract[] abstracciones = new IAbstract[2];
		abstracciones[0] = new AbstractImpl(new ImplementationA());
		abstracciones[1] = new AbstractImpl(new ImplementationB());
		for (IAbstract abstraccion : abstracciones)
			abstraccion.operacion();
	}
}
