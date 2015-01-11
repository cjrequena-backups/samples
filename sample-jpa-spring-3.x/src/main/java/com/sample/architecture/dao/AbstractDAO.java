/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */

package com.sample.architecture.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.openjpa.persistence.NoResultException;
import org.apache.openjpa.util.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * 
 * @author cjrequena
 * 
 * @param <E>
 * @param <PK>
 */
@Configurable
public abstract class AbstractDAO<E, PK> implements IDAO<E, PK>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	private final Class<E> entityClass;

	protected AbstractDAO(Class<E> entityClass) {
		if (entityClass == null) {
			throw new IllegalArgumentException("<Null>");
		}

		this.entityClass = entityClass;
	}

	public EntityManager entityManager() {
		EntityManager em = entityManager;
		if (em == null)
			throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	/**
	 * get collection entitys from process query with arry param values, in jpa params are ?1, ?9 etc example select c
	 * from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> Collection<D> executeQueryResults(Query query, Object... params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Object entity : params) {
			queryTrace.append("Param ").append(index).append(": ").append(entity);
			query.setParameter(index++, entity);
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get entity from process query with arry param values, in jpa params are ?1, ?9 etc example select c from entity c
	 * where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, Object... params) throws Exception {
		try {
			StringBuffer queryTrace = new StringBuffer(query.toString());
			int index = 1;
			for (Object entity : params) {
				queryTrace.append("Param ").append(index).append(": ").append(entity);
				query.setParameter(index++, entity);
			}
			logger.info("Query to execute: " + queryTrace.toString());
			Object result = query.getSingleResult();
			return (D) result;
		} catch (EmptyResultDataAccessException erde) {
			return null;
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nure) {
			logger.error("There is no a unique result executing SingleQueryResult");
			throw nure;
		}
	}

	/**
	 * get collection entitys from process query with Map param values, in jpa params are ?1, ?9 etc example select c
	 * from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> Collection<D> executeQueryResults(Query query, Map<String, Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		for (String key : params.keySet()) {
			queryTrace.append("Param ").append(key).append(": ").append(params.get(key));
			query.setParameter(key, params.get(key));
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get entity from process query with Map param values, in jpa params are ?1, ?9 etc example select c from entity c
	 * where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, Map<String, Object> params) throws Exception {
		try {
			StringBuffer queryTrace = new StringBuffer(query.toString());
			for (String key : params.keySet()) {
				queryTrace.append("Param ").append(key).append(": ").append(params.get(key));
				query.setParameter(key, params.get(key));
			}
			logger.info("Query to execute: " + queryTrace.toString());
			Object result = query.getSingleResult();
			return (D) result;
		} catch (EmptyResultDataAccessException erde) {
			return null;
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nure) {
			logger.error("There is no a unique result executing SingleQueryResult");
			throw nure;
		}
	}

	/**
	 * get collection entitys from process query with List param values, in jpa params are ?1, ?9 etc example select c
	 * from entity c where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> Collection<D> executeQueryResults(Query query, List<Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
			Object entity = (Object) iterator.next();
			queryTrace.append("Param ").append(index).append(": ").append(entity);
			query.setParameter(index++, entity);

		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;

	}

	/**
	 * get entity from process query with List param values, in jpa params are ?1, ?9 etc example select c from entity c
	 * where c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, List<Object> params) throws Exception {
		try {
			StringBuffer queryTrace = new StringBuffer(query.toString());
			int index = 1;
			for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
				Object entity = (Object) iterator.next();
				queryTrace.append("Param ").append(index).append(": ").append(entity);
				query.setParameter(index++, entity);
			}
			logger.info("Query to execute: " + queryTrace.toString());
			Object result = query.getSingleResult();
			return (D) result;
		} catch (EmptyResultDataAccessException erde) {
			return null;
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nure) {
			logger.error("There is no a unique result executing SingleQueryResult");
			throw nure;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> excuteNativeQuery(String select, String from, String grouping, String having, String where, String orderBy, Map<Integer, Object> params) throws Exception {
		StringBuffer sbQuery = new StringBuffer();
		if (select != null)
			sbQuery.append(select).append(" ");
		if (from != null)
			sbQuery.append(from).append(" ");
		if (grouping != null)
			sbQuery.append(grouping).append(" ");
		if (having != null)
			sbQuery.append(having).append(" ");
		if (where != null)
			sbQuery.append(where).append(" ");
		if (orderBy != null)
			sbQuery.append(orderBy);
		logger.debug("Executing native query " + sbQuery.toString());
		Query query = this.entityManager.createNativeQuery(sbQuery.toString());
		for (Integer key : params.keySet()) {
			logger.debug("Param " + key + " Value " + params.get(key));
			query.setParameter(key, params.get(key));
		}
		logger.debug("Native query executed sucessfully");
		return query.getResultList();
	}

	@Override
	public int countAll() throws Exception {
		return ((Number) this.entityManager.createQuery("SELECT COUNT(o) FROM " + this.entityClass.getSimpleName() + " o ", this.entityClass).getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> findEntries(int firstResult, int maxResults) throws Exception {
		String strQuery = "SELECT o FROM " + this.entityClass.getSimpleName() + " o ";
		Query query = this.entityManager.createQuery(strQuery, this.entityClass);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@Override
	public Collection<E> findAll() throws Exception {
		return this.entityManager.createQuery("SELECT o FROM " + this.entityClass.getSimpleName() + " o ", this.entityClass).getResultList();
	}

	@Override
	public E findByPk(PK pk) throws Exception {
		return this.entityManager.find(this.entityClass, pk);
	}

	@Override
	public E persist(E entity) throws Exception {
		try {
			this.entityManager.persist(entity);
			this.flush();
			return entity;
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public E merge(E entity) throws Exception {
		try {
			entity =  this.entityManager.merge(entity);
			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void deleteByPk(PK pk) throws Exception {
		Object entity = this.entityManager.find(this.entityClass, pk);
		if (entity == null) {
			logger.info("Requested entity not exists");
			throw new Exception("Requested entity not exists");
		}
		this.entityManager.remove(entity);
	}

	@Override
	public void delete(E entity) throws Exception {
		try {
			this.entityManager.remove(entity);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

	

	@Override
	public void flush() throws Exception {
		try {
			this.entityManager.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}

	}

	@Override
	public void clear() throws Exception {
		try {
			this.entityManager.clear();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public Query createQueryFilter(List<Filter> filters) throws Exception {

		String fullFilter = "";
		int index = 0;
		Map<String, Object> internalParams = new HashMap<String, Object>();

		if (filters != null) {
			for (Filter filter : filters) {

				Column column = filter.getColumn();
				Criteria criteria = filter.getCriteria();
				Boolean andOr = filter.getAndOr();
				Object value = column.getValue();
				String paramName = ":" + column.getName().replace(".", "_") + "_param_" + index;
				String field = "o." + column.getName();

				boolean caseSensitive = filter.getCaseSensitive();
				if ((!caseSensitive) && (value instanceof String)) {
					value = ((String) value).toUpperCase();
				}

				String strFilter = "";
				if (Criteria.EQUAL.equals(criteria)) {
					if (value == null) {
						strFilter = field + " IS NOT NULL ";
					} else {
						if (caseSensitive) {
							strFilter = field + " = " + paramName;
							internalParams.put(paramName, value);
						} else {
							strFilter = " UPPER(" + field + ") = " + paramName;
							internalParams.put(paramName, value.toString().toUpperCase());
						}
					}
				}
				if (Criteria.NOT_EQUAL.equals(criteria)) {
					if (value == null) {
						strFilter = field + " IS NOT NULL ";
					} else {
						if (caseSensitive) {
							strFilter = field + " <> " + paramName;
							internalParams.put(paramName, value);
						} else {
							strFilter = " UPPER(" + field + ") <> " + paramName;
							internalParams.put(paramName, value.toString().toUpperCase());
						}
					}
				}
				if (Criteria.LIKE.equals(criteria)) {
					if (caseSensitive) {
						strFilter = field + " LIKE " + paramName;
						internalParams.put(paramName, "%" + value + "%");
					} else {
						strFilter = " UPPER(" + field + ") LIKE " + paramName;
						internalParams.put(paramName, "%" + value.toString().toUpperCase() + "%");
					}
				}
				if (Criteria.STARTWITH.equals(criteria)) {
					if (caseSensitive) {
						strFilter = field + " LIKE " + paramName;
						internalParams.put(paramName, value + "%");
					} else {
						strFilter = " UPPER(" + field + ") LIKE " + paramName;
						internalParams.put(paramName, value.toString().toUpperCase() + "%");
					}
				}
				if (Criteria.ENDWITH.equals(criteria)) {
					if (caseSensitive) {
						strFilter = field + " LIKE " + paramName;
						internalParams.put(paramName, "%" + value);
					} else {
						strFilter = " UPPER(" + field + ") LIKE " + paramName;
						internalParams.put(paramName, "%" + value.toString().toUpperCase());
					}
				}
				if (Criteria.GREATER_THAN.equals(criteria)) {
					strFilter = field + " > " + paramName;
					internalParams.put(paramName, value);

				}
				if (Criteria.GREATER_THAN_EQUAL.equals(criteria)) {
					strFilter = field + ">=" + paramName;
					internalParams.put(paramName, value);

				}
				if (Criteria.LESS_THAN.equals(criteria)) {
					strFilter = field + " < " + paramName;
					internalParams.put(paramName, value);

				}
				if (Criteria.LESS_THAN_EQUAL.equals(criteria)) {
					strFilter = field + "<=" + paramName;
					internalParams.put(paramName, value);
				}
				if (Criteria.BETWEEN.equals(criteria)) {
					strFilter = "((" + field + " >= " + paramName + "_1) AND (" + field + " <= " + paramName + "_2))";
					try {
						String params[] = ((String) column.getValue()).split("|");
						if (params != null && params.length > 0) {
							internalParams.put(paramName + "_1", params[0]);
							internalParams.put(paramName + "_2", params[0]);
						} else {
							throw new Exception("There are not valid parameters for query BETWEEN.");
						}
					} catch (Exception e) {
						throw new Exception(e.getMessage(), e);
					}
				}

				if (index++ > 0) {
					if (andOr) {
						fullFilter += " AND (" + strFilter + ")";
					} else {
						fullFilter += " OR  (" + strFilter + ")";
					}
				} else {
					fullFilter += strFilter;
				}
			}
		}

		String queryString = "SELECT o FROM " + this.entityClass.getSimpleName() + " o ";
		boolean isWithWhere = fullFilter != null && !fullFilter.trim().isEmpty();
		if (isWithWhere) {
			queryString += " WHERE " + fullFilter;

		}

		logger.debug("Query created: " + queryString);

		Query query = this.entityManager.createQuery(queryString, this.entityClass);
		if (isWithWhere) {
			for (String key : internalParams.keySet()) {
				query.setParameter(key.substring(1), internalParams.get(key));
			}
		}

		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		Query query = createQueryFilter(filters);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}
}
