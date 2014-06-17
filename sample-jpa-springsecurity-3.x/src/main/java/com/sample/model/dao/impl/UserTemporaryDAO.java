package com.sample.model.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IUserTemporaryDAO;
import com.sample.model.jpa.UserTemporary;

@Component("userTemporaryDAO")
public class UserTemporaryDAO extends AbstractDAO<UserTemporary, Integer> implements IUserTemporaryDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected UserTemporaryDAO() {
		super(UserTemporary.class);
	}

	protected UserTemporaryDAO(Class<UserTemporary> targetClass) {
		super(targetClass);
	}

	@Override
	public UserTemporary findByUUID(String uuid) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		Query query = this.entityManager.createNamedQuery("UserTemporary.findUUID");
		return this.executeQueryResult(query, params);
	}

}
