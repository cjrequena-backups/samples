package com.test.structural.wrapper;

import org.junit.Test;

import com.design.patterns.structural.wrapper.ElectricAcousticGuitar;
import com.design.patterns.structural.wrapper.ElectricGuitar;
import com.design.patterns.structural.wrapper.Guitar;

/*
 * Convert the interface of a class into another interface clients expect. An adapter lets classes work together that could not otherwise because
 *  of incompatible interfaces. The enterprise integration pattern equivalent is the translator.
 * 
 *
 */
public class AdapterWrapperTest {
	@Test
	public void test() {
		Guitar eGuitar = new ElectricGuitar();
		eGuitar.onGuitar();
		eGuitar.offGuitar();
		
		Guitar eAGuitar = new ElectricAcousticGuitar();
		eAGuitar.onGuitar();
		eAGuitar.offGuitar();
	}
}
