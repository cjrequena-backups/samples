package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IInvoiceDAO;
import com.sample.model.entity.InvoiceEntity;

@Component("invoiceDAO")
public class InvoiceDAO extends AbstractDAO<InvoiceEntity, Integer> implements IInvoiceDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceDAO.class);

	protected InvoiceDAO() {
		super(InvoiceEntity.class);
	}

	protected InvoiceDAO(Class<InvoiceEntity> targetClass) {
		super(targetClass);
	}

}