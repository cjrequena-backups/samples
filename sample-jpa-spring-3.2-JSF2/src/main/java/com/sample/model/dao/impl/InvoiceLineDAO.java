package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IInvoiceLineDAO;
import com.sample.model.jpa.InvoiceLine;

@Component("invoiceLineDAO")
public class InvoiceLineDAO extends AbstractDAO<InvoiceLine, Integer> implements IInvoiceLineDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceLineDAO.class);

	protected InvoiceLineDAO() {
		super(InvoiceLine.class);
	}

	protected InvoiceLineDAO(Class<InvoiceLine> targetClass) {
		super(targetClass);
	}

}