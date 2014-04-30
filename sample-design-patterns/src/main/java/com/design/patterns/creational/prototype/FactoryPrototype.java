package com.design.patterns.creational.prototype;

import java.util.HashMap;

public class FactoryPrototype {
	private static HashMap<String, Object> mapObjets;
	private String name;

	public FactoryPrototype() {
		mapObjets = new HashMap<String, Object>();
		// Se incluyen en el mapa todos los productos prototipo
		mapObjets.put("product1", new ProductImpl("Arina"));
		mapObjets.put("product2", new ProductImpl("Pollo"));
		mapObjets.put("product3", new ProductImpl("Carne"));
	}

	public Object create() {
		return create(name);
	}

	public Object create(String name) {
		this.name = name;
		IProduct object = (IProduct) mapObjets.get(name);
		return object != null ? object.clone() : null;
	}
}
