package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.jpa.Role;

public interface IRoleService {

	public abstract long countAll() throws BusinessExceptions;

	public abstract void save(Role object) throws BusinessExceptions;

	public abstract void update(Role obj) throws BusinessExceptions;

	public abstract void delete(Role obj) throws BusinessExceptions;

	public void deleteByPk(Integer pk) throws BusinessExceptions;

	public abstract Role findByPk(Integer pk) throws BusinessExceptions;

	public List<Role> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	public abstract List<Role> findAll() throws BusinessExceptions;

	public List<Role> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;

}
