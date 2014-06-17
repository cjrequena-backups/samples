package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.jpa.UserTemporary;

public interface IUserTemporaryService {

	public abstract long countAll() throws BusinessExceptions;

	public abstract void save(UserTemporary object) throws BusinessExceptions;

	public abstract void update(UserTemporary obj) throws BusinessExceptions;

	public abstract void delete(UserTemporary obj) throws BusinessExceptions;

	public void deleteByPk(Integer pk) throws BusinessExceptions;

	public abstract UserTemporary findByPk(Integer pk) throws BusinessExceptions;

	public List<UserTemporary> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	public abstract List<UserTemporary> findAll() throws BusinessExceptions;

	public List<UserTemporary> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;

}
