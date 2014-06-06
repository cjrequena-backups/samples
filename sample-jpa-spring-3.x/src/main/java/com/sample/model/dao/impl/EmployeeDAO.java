package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IEmployeeDAO;
import com.sample.model.jpa.Employee;

@Component("employeeDAO")
public class EmployeeDAO extends AbstractDAO<Employee, Integer> implements IEmployeeDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

	protected EmployeeDAO() {
		super(Employee.class);
	}

	protected EmployeeDAO(Class<Employee> targetClass) {
		super(targetClass);
	}

}