package com.sample.architecture.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cjrequena
 * 
 */
public class Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Column column;
	private Criteria criteria;
	private Criteria andOr;
	private Boolean caseSensitive = Boolean.TRUE;

	public Filter() {
		this.column = new Column();
	}

	public List<Criteria> getListCriteria() {
		List<Criteria> listCriterias = new ArrayList<Criteria>();
		listCriterias.add(Criteria.EQUAL);
		listCriterias.add(Criteria.NOT_EQUAL);
		listCriterias.add(Criteria.LIKE);
		listCriterias.add(Criteria.STARTWITH);
		listCriterias.add(Criteria.ENDWITH);
		listCriterias.add(Criteria.GREATER_THAN);
		listCriterias.add(Criteria.GREATER_THAN_EQUAL);
		listCriterias.add(Criteria.LESS_THAN);
		listCriterias.add(Criteria.LESS_THAN_EQUAL);
		listCriterias.add(Criteria.IN);
		listCriterias.add(Criteria.BETWEEN);
		return listCriterias;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Criteria getAndOr() {
		return andOr;
	}

	public void setAndOr(Criteria andOr) {
		this.andOr = andOr;
	}

	public Boolean getCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(Boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

}
