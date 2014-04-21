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

import com.sample.model.jpa.Employee;
import com.sample.service.IEmployeeService;

@FacesConverter("com.samplecontroller.converter.EmployeeConverter")
@RequestScoped
public class EmployeeConverter implements Converter, Serializable {

	/**
	 * 
	 * 
	 *
	 **/
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeConverter.class);

	private IEmployeeService employeeService;

	public EmployeeConverter(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) throws ConverterException {
		if (value == null || value.length() == 0) {
			return null;
		}

		try {
			return employeeService.findByPk(Integer.valueOf(value));
		} catch (Exception e) {
			logger.error("Error getting object from employee converter");
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) throws ConverterException {
		String valueReturn = "";
		if (value instanceof Employee) {
			try {
				valueReturn = ((Employee) value).getEmployeeId().toString();
			} catch (Exception e) {

			}
		}
		return valueReturn;

	}

}
