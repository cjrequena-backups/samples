package com.design.patterns.creational.prototype;

public class ProductImpl implements IProduct {
	
	private String productName;
	
	public ProductImpl(String productName) {
		this.productName = productName;
	}
	
	@Override
	public Object clone() {
		return new ProductImpl(this.productName);
	}
	
	@Override
	public String getProductName() {
		return this.productName;
	}

	public String toString() {
		return this.getProductName();
	}

	
}
