package com.test.structural.facade;

import org.junit.Test;

import com.design.patterns.structural.facade.ComputerFacade;

/*
 * The facade pattern (or façade pattern) is a software design pattern commonly used with object-oriented programming. The name is by analogy to an 
 * architectural facade.
 * 
 * A facade is an object that provides a simplified interface to a larger body of code, such as a class library. A facade can:
 * 
 * 	make a software library easier to use, understand and test, since the facade has convenient methods for common tasks;
 * 	make the library more readable, for the same reason;
 * 	reduce dependencies of outside code on the inner workings of a library, since most code uses the facade, thus allowing more flexibility in developing the system;
 * 	wrap a poorly designed collection of APIs with a single well-designed API (as per task needs).
 */
public class FacadeTest {
	
	@Test
	public void test() {
		ComputerFacade computer = new ComputerFacade();
		computer.start();
	}
}
