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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;



/**
 * 
 * @author cjrequena
 * 
 */
@Configurable
public abstract class AbstractDAO<T, PK> implements IDAO<T, PK>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	@PersistenceContext
	transient EntityManager entityManager;

	private final Class<T> targetClass;

	protected AbstractDAO(Class<T> targetClass) {
		if (targetClass == null) {
			throw new IllegalArgumentException("<Null>");
		}

		this.targetClass = targetClass;
	}

	/**
	 * get collection objects from process query with arry param values, in jpa params are ?1, ?9 etc example select c from entity c where
	 * c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> Collection<D> executeQueryResults(Query query, Object... params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Object object : params) {
			queryTrace.append("Param ").append(index).append(": ").append(object);
			query.setParameter(index++, object);
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;
	}

	/**
	 * get object from process query with arry param values, in jpa params are ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, Object... params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Object object : params) {
			queryTrace.append("Param ").append(index).append(": ").append(object);
			query.setParameter(index++, object);
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get collection objects from process query with Map param values, in jpa params are ?1, ?9 etc example select c from entity c where c.columname1
	 * = ?1 and c.columname2 = ?2
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
	 * get object from process query with Map param values, in jpa params are ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, Map<String, Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		for (String key : params.keySet()) {
			queryTrace.append("Param ").append(key).append(": ").append(params.get(key));
			query.setParameter(key, params.get(key));
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
	}

	/**
	 * get collection objects from process query with List param values, in jpa params are ?1, ?9 etc example select c from entity c where
	 * c.columname1 = ?1 and c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> Collection<D> executeQueryResults(Query query, List<Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			queryTrace.append("Param ").append(index).append(": ").append(object);
			query.setParameter(index++, object);

		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getResultList();
		return (Collection<D>) result;

	}

	/**
	 * get object from process query with List param values, in jpa params are ?1, ?9 etc example select c from entity c where c.columname1 = ?1 and
	 * c.columname2 = ?2
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public <D> D executeQueryResult(Query query, List<Object> params) throws Exception {
		StringBuffer queryTrace = new StringBuffer(query.toString());
		int index = 1;
		for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			queryTrace.append("Param ").append(index).append(": ").append(object);
			query.setParameter(index++, object);
		}
		logger.info("Query to execute: " + queryTrace.toString());
		Object result = query.getSingleResult();
		return (D) result;
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
		return ((Number) this.entityManager.createQuery("SELECT COUNT(o) FROM " + this.targetClass.getSimpleName() + " o ", this.targetClass).getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> findEntries(int firstResult, int maxResults) throws Exception {
		String strQuery = "SELECT o FROM " + this.targetClass.getSimpleName() + " o ";
		Query query = this.entityManager.createQuery(strQuery, this.targetClass);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@Override
	public Collection<T> findAll() throws Exception {
		return this.entityManager.createQuery("SELECT o FROM " + this.targetClass.getSimpleName() + " o ", this.targetClass).getResultList();
	}

	@Override
	public T findByPk(PK pk) throws Exception {
		return this.entityManager.find(this.targetClass, pk);
	}

	@Override
	public T save(T object) throws Exception {
		try {
			this.entityManager.persist(object);
			return object;
		} catch (Throwable e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void deleteByPk(PK pk) throws Exception {
		Object object = this.entityManager.find(this.targetClass, pk);
		if (object == null) {
			logger.info("Requested object not exists");
			throw new Exception("Requested object not exists");
		}
		this.entityManager.remove(object);
	}

	@Override
	public void delete(T object) throws Exception {
		try {
			this.entityManager.remove(object);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public T update(T object) throws Exception {
		try {
			return this.entityManager.merge(object);
		} catch (Exception e) {
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
				Criteria andOr = filter.getAndOr();
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
					if (andOr.equals(Criteria.AND)) {
						fullFilter += " AND (" + strFilter + ")";
					} else {
						fullFilter += " OR  (" + strFilter + ")";
					}
				} else {
					fullFilter += strFilter;
				}
			}
		}

		String queryString = "SELECT o FROM " + this.targetClass.getSimpleName() + " o ";
		boolean isWithWhere = fullFilter != null && !fullFilter.trim().isEmpty();
		if (isWithWhere) {
			queryString += " WHERE " + fullFilter;

		}

		logger.debug("Query created: " + queryString);

		Query query = this.entityManager.createQuery(queryString, this.targetClass);
		if (isWithWhere) {
			for (String key : internalParams.keySet()) {
				query.setParameter(key.substring(1), internalParams.get(key));
			}
		}

		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		Query query = createQueryFilter(filters);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}

}
