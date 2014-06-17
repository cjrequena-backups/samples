package com.sample.model.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IRoleDAO;
import com.sample.model.jpa.Role;

@Component("roleDAO")
public class RoleDAO extends AbstractDAO<Role, Integer> implements IRoleDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RoleDAO() {
		super(Role.class);
	}

	protected RoleDAO(Class<Role> targetClass) {
		super(targetClass);
	}

}
