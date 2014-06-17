package com.sample.model.dao;

import com.sample.architecture.dao.IDAO;
import com.sample.model.jpa.User;

public interface IUserDAO extends IDAO<User, Integer> {

	public User findByEmail(String email) throws Exception;

}
