package com.sample.model.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IRightDAO;
import com.sample.model.jpa.Right;
import com.sample.model.jpa.User;

@Component("rightDAO")
public class RightDAO extends AbstractDAO<Right, Integer> implements IRightDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RightDAO() {
		super(Right.class);
	}

	protected RightDAO(Class<Right> targetClass) {
		super(targetClass);
	}

	@Override
	public Collection<Right> findRightsByUser(User user) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(user.getIdUser());
		Query query = this.entityManager.createQuery("SELECT right FROM Right right JOIN right.roles role JOIN role.users user where user.idUser =?1");
		return this.executeQueryResults(query, params);
	}

}
