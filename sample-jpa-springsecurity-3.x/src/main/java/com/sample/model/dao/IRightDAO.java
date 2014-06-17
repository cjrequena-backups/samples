package com.sample.model.dao;

import java.util.Collection;

import com.sample.architecture.dao.IDAO;
import com.sample.model.jpa.Right;
import com.sample.model.jpa.User;

public interface IRightDAO extends IDAO<Right, Integer> {
	public Collection<Right> findRightsByUser(User user) throws Exception;
}
