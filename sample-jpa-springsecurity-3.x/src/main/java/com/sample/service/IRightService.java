package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.jpa.Right;

public interface IRightService {

	public abstract long countAll() throws BusinessExceptions;

	public abstract void save(Right object) throws BusinessExceptions;

	public abstract void update(Right obj) throws BusinessExceptions;

	public abstract void delete(Right obj) throws BusinessExceptions;

	public void deleteByPk(Integer pk) throws BusinessExceptions;

	public abstract Right findByPk(Integer pk) throws BusinessExceptions;

	public List<Right> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	public abstract List<Right> findAll() throws BusinessExceptions;

	public List<Right> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;

}
