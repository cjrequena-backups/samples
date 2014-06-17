package com.sample.model.dao;

import com.sample.architecture.dao.IDAO;
import com.sample.model.jpa.UserTemporary;

public interface IUserTemporaryDAO extends IDAO<UserTemporary, Integer> {

	public UserTemporary findByUUID(String uuid) throws Exception;

}
