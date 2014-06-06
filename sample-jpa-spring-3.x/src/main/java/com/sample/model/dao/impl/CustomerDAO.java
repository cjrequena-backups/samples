package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.ICustomerDAO;
import com.sample.model.jpa.Customer;

@Component("customerDAO")
public class CustomerDAO extends AbstractDAO<Customer, Integer> implements ICustomerDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	protected CustomerDAO() {
		super(Customer.class);
	}

	protected CustomerDAO(Class<Customer> targetClass) {
		super(targetClass);
	}

}