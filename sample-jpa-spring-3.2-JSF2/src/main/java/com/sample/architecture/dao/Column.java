/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.dao;

import java.io.Serializable;

/**
 * 
 * @author cjrequena
 *
 */
public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object value;
	private Class<?> type;
	private String name;
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

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