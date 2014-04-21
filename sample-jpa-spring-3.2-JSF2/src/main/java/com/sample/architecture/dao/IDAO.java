package com.sample.architecture.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

/**
 * @author cjrequena
 * 
 */
public interface IDAO<T, PK> {

	abstract <D> Collection<D> executeQueryResults(Query query, Object... params) throws Exception;

	abstract <D> D executeQueryResult(Query query, Object... params) throws Exception;

	abstract <D> Collection<D> executeQueryResults(Query query, Map<String, Object> params) throws Exception;

	abstract <D> D executeQueryResult(Query query, Map<String, Object> params) throws Exception;

	abstract <D> Collection<D> executeQueryResults(Query query, List<Object> params) throws Exception;

	abstract <D> D executeQueryResult(Query query, List<Object> params) throws Exception;

	abstract List<Object[]> excuteNativeQuery(String select, String from, String grouping, String having, String where, String orderBy, Map<Integer, Object> params) throws Exception;

	public int countAll() throws Exception;

	public Collection<T> findAll() throws Exception;

	public Collection<T> findEntries(int firstResult, int maxResults) throws Exception;

	public T findByPk(PK pk) throws Exception;

	public T save(T object) throws Exception;

	public void delete(T object) throws Exception;

	public void deleteByPk(PK pk) throws Exception;

	public T update(T object) throws Exception;

	public void flush() throws Exception;

	public void clear() throws Exception;

	public Query createQueryFilter(List<Filter> filters) throws Exception;

	public List<T> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}