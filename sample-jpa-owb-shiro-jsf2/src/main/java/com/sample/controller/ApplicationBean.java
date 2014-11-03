package com.sample.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.menubar.Menubar;

import com.sample.architecture.commons.utils.MessageFactoryUtils;

@Named("ApplicationBean")
@RequestScoped
public class ApplicationBean {

	public String getColumnName(String column) {
		if (column == null || column.length() == 0) {
			return column;
		}
		final Pattern p = Pattern.compile("[A-Z][^A-Z]*");
		final Matcher m = p.matcher(Character.toUpperCase(column.charAt(0))
				+ column.substring(1));
		final StringBuilder builder = new StringBuilder();
		while (m.find()) {
			builder.append(m.group()).append(" ");
		}
		return builder.toString().trim();
	}

	private ValueExpression targetExpression;
	private ValueExpression valueExpression;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application
				.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

	}

	public ValueExpression getTargetExpression() {
		return targetExpression;
	}

	public void setTargetExpression(ValueExpression targetExpression) {
		this.targetExpression = targetExpression;
	}

	public ValueExpression getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(ValueExpression valueExpression) {
		this.valueExpression = valueExpression;
	}

	public String getAppName() {
		return "Sample P5";
	}
}