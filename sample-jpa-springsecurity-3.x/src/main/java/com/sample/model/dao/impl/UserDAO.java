package com.sample.model.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IUserDAO;
import com.sample.model.jpa.User;


@Component("userDAO")
public class UserDAO extends AbstractDAO<User, Integer> implements IUserDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected UserDAO() {
		super(User.class);
	}

	protected UserDAO(Class<User> targetClass) {
		super(targetClass);
	}

	@Override
	public User findByEmail(String email) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		Query query = this.entityManager.createQuery("SELECT u FROM User u where u.email = ?1");
		return this.executeQueryResult(query, params);
	}

}
