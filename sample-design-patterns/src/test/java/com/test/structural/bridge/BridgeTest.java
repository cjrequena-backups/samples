package com.test.structural.bridge;

import org.junit.Test;

import com.design.patterns.structural.bridge.AbstractImpl;
import com.design.patterns.structural.bridge.IAbstract;
import com.design.patterns.structural.bridge.ImplementationA;
import com.design.patterns.structural.bridge.ImplementationB;

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
