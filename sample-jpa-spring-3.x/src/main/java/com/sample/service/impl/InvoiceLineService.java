package com.sample.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.architecture.exceptions.ECodeExceptions;
import com.sample.model.dao.IInvoiceLineDAO;
import com.sample.model.jpa.InvoiceLine;
import com.sample.service.IInvoiceLineService;

@Service
@Transactional
public class InvoiceLineService implements IInvoiceLineService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

	@Inject
	private IInvoiceLineDAO invoiceDAO;

	@Override
	@Transactional
	public long countAll() throws BusinessExceptions {
		try {
			return this.invoiceDAO.countAll();
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void save(InvoiceLine object) throws BusinessExceptions {
		try {
			this.invoiceDAO.save(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void update(InvoiceLine object) throws BusinessExceptions {
		try {
			this.invoiceDAO.update(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void delete(InvoiceLine object) throws BusinessExceptions {
		try {
			this.invoiceDAO.delete(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public void deleteByPk(Integer pk) throws BusinessExceptions {
		try {
			this.invoiceDAO.deleteByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public InvoiceLine findByPk(Integer pk) throws BusinessExceptions {
		try {
			return this.invoiceDAO.findByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<InvoiceLine> findEntries(int firstResult, int maxResults) throws BusinessExceptions {
		try {
			return new ArrayList<InvoiceLine>(this.invoiceDAO.findEntries(firstResult, maxResults));
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<InvoiceLine> findAll() throws BusinessExceptions {
		try {
			return new ArrayList<InvoiceLine>(this.invoiceDAO.findAll());
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<InvoiceLine> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions {
		try {
			return this.invoiceDAO.executeQueryFilter(filters, firstResult, maxResult);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}
}
