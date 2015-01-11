package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IEmployeeDAO;
import com.sample.model.entity.EmployeeEntity;

@Component("employeeDAO")
public class EmployeeDAO extends AbstractDAO<EmployeeEntity, Integer> implements IEmployeeDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	protected EmployeeDAO() {
		super(EmployeeEntity.class);
	}

	protected EmployeeDAO(Class<EmployeeEntity> targetClass) {
		super(targetClass);
	}

}