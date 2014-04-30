package com.design.patterns.creational.prototype;

public interface IProduct extends Cloneable {
	Object clone();

	public String getProductName();

}
