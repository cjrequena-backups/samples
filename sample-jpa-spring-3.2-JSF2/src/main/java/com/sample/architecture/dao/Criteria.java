package com.sample.architecture.dao;

public enum Criteria {

	/****/
	BETWEEN("BETWEEN", "BETWEEN", " BT "),
	/****/
	EQUAL("EQUAL", "EQUAL", " EQ "),
	/****/
	GREATER_THAN("GRATHER_THAN", "GRATHER_THAN", " GT "),
	/****/
	GREATER_THAN_EQUAL("GREATER_THAN_EQUAL", "GREATER_THAN_EQUAL", " GTE "),
	/****/
	IN("CRT_IN", "CRT_IN", " IN "),
	/****/
	LIKE("LIKE", "LIKE", " LIKE "),
	/****/
	STARTWITH("STARTWITH", "STARTWITH", " STARTWITH "),
	/****/
	ENDWITH("ENDWITH", "ENDWITH", " ENDWITH "),
	/****/
	LESS_THAN("LESS_THAN", "LESS_THAN", " LT "),
	/****/
	LESS_THAN_EQUAL("LESS_THAN_EQUAL", "LESS_THAN_EQUAL", " LTE "),
	/****/
	NOT_EQUAL("NOT_EQUAL", "NOT_EQUAL", " NEQ "),
	/****/
	AND("AND", "AND", " AND "),
	/****/
	OR("OR", "OR", " AND ");

	private String criteriaName;
	private String description;
	private String shortDescription;

	private Criteria(String criteriaName, String description, String shortDescription) {
		this.criteriaName = criteriaName;
		this.description = description;
		this.shortDescription = shortDescription;
	}

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	
}