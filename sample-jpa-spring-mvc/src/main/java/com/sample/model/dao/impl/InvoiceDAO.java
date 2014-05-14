package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IInvoiceDAO;
import com.sample.model.jpa.Invoice;

@Component("invoiceDAO")
public class InvoiceDAO extends AbstractDAO<Invoice, Integer> implements IInvoiceDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceDAO.class);

	protected InvoiceDAO() {
		super(Invoice.class);
	}

	protected InvoiceDAO(Class<Invoice> targetClass) {
		super(targetClass);
	}

}