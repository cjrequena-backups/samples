/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

/**
 * @author cjrequena
 * 
 */
public interface IDAO<E, PK> {

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> Collection<D> executeQueryResults(Query query, Object... params) throws Exception;

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> D executeQueryResult(Query query, Object... params) throws Exception;

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> Collection<D> executeQueryResults(Query query, Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> D executeQueryResult(Query query, Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> Collection<D> executeQueryResults(Query query, List<Object> params) throws Exception;

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract <D> D executeQueryResult(Query query, List<Object> params) throws Exception;

	/**
	 * 
	 * @param select
	 * @param from
	 * @param grouping
	 * @param having
	 * @param where
	 * @param orderBy
	 * @param params
	 * @return
	 * @throws Exception
	 */
	abstract List<Object[]> excuteNativeQuery(String select, String from, String grouping, String having, String where, String orderBy, Map<Integer, Object> params) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int countAll() throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Collection<E> findAll() throws Exception;

	/**
	 * 
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public Collection<E> findEntries(int firstResult, int maxResults) throws Exception;

	/**
	 * 
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public E findByPk(PK pk) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public E persist(E entity) throws Exception;
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public E merge(E entity) throws Exception;


	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(E entity) throws Exception;

	/**
	 * 
	 * @param pk
	 * @throws Exception
	 */
	public void deleteByPk(PK pk) throws Exception;

	
	/**
	 * 
	 * @throws Exception
	 */
	public void flush() throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	public void clear() throws Exception;

	/**
	 * 
	 * @param filters
	 * @return
	 * @throws Exception
	 */
	public Query createQueryFilter(List<Filter> filters) throws Exception;

	/**
	 * 
	 * @param filters
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * @throws Exception
	 */
	public List<E> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}