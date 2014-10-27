package com.sample.controller.converter;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.model.jpa.Invoice;
import com.sample.service.IInvoiceService;

@FacesConverter("com.samplecontroller.converter.InvoiceConverter")
@RequestScoped
public class InvoiceConverter implements Converter, Serializable {

	/**
	 * 
	 * 
	 *
	 **/
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceConverter.class);

	private IInvoiceService invoiceService;

	public InvoiceConverter(IInvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) throws ConverterException {
		if (value == null || value.length() == 0) {
			return null;
		}

		try {
			return invoiceService.findByPk(Integer.valueOf(value));
		} catch (Exception e) {
			logger.error("Error getting object from invoice converter");
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) throws ConverterException {
		String valueReturn = "";
		if (value instanceof Invoice) {
			try {
				valueReturn = ((Invoice) value).getInvoiceId().toString();
			} catch (Exception e) {

			}
		}
		return valueReturn;

	}

}
