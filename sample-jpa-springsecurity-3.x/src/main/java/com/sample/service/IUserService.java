package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.jpa.User;

public interface IUserService {

	public abstract long countAll() throws BusinessExceptions;

	public abstract void save(User object) throws BusinessExceptions;

	public abstract void update(User obj) throws BusinessExceptions;

	public abstract void delete(User obj) throws BusinessExceptions;

	public void deleteByPk(Integer pk) throws BusinessExceptions;

	public abstract User findByPk(Integer pk) throws BusinessExceptions;

	public List<User> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	public abstract List<User> findAll() throws BusinessExceptions;

	public List<User> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;

}
