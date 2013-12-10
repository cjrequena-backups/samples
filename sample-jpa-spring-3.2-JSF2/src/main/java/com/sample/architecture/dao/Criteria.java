package com.sample.architecture.dao;

public enum Criteria {

	/****/
	BETWEEN("BETWEEN", " BT "),
	/****/
	EQUAL("EQUAL", " EQ "),
	/****/
	GREATER_THAN("GRATHER_THAN", " GT "),
	/****/
	GREATER_THAN_EQUAL("GREATER_THAN_EQUAL", " GTE "),
	/****/
	IN("CRT_IN", " IN "),
	/****/
	LIKE("LIKE", " LIKE "),
	/****/
	STARTWITH("STARTWITH", " STARTWITH "),
	/****/
	ENDWITH("ENDWITH", " ENDWITH "),
	/****/
	LESS_THAN("LESS_THAN", " LT "),
	/****/
	LESS_THAN_EQUAL("LESS_THAN_EQUAL", " LTE "),
	/****/
	NOT_EQUAL("NOT_EQUAL", " NEQ "),
	/****/
	AND("AND", " AND "),
	/****/
	OR("OR", " AND ");

	private String propertyName;
	private String value;

	private Criteria(String propertyName, String value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}