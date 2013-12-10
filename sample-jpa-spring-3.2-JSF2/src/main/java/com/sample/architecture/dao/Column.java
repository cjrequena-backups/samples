package com.sample.architecture.dao;

import java.io.Serializable;


public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object value;
	private Class<?> type;
	private String name;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}