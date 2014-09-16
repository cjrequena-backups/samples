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
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.sample.architecture.commons.utils.MessageFactoryUtils;

@Named("ApplicationBean")
@RequestScoped
public class ApplicationBean {

	public String getColumnName(String column) {
		if (column == null || column.length() == 0) {
			return column;
		}
		final Pattern p = Pattern.compile("[A-Z][^A-Z]*");
		final Matcher m = p.matcher(Character.toUpperCase(column.charAt(0)) + column.substring(1));
		final StringBuilder builder = new StringBuilder();
		while (m.find()) {
			builder.append(m.group()).append(" ");
		}
		return builder.toString().trim();
	}

	private MenuModel menuModel;
	private Menubar menubar;
	private MenuItem menuItem;
	private Submenu menu;
	private ValueExpression targetExpression;
	private ValueExpression valueExpression;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();

		menubar = new Menubar();
		menuModel = new DefaultMenuModel();

		menu = new Submenu();
		menu.setLabel(MessageFactoryUtils.getStringMessage("i18n", "label_menu"));
		menu.setIcon("ui-icon-document");

//		// MANDATES
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Mandatos");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{MandateControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);
//
//		// MESSAGES
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Mensajes");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{MessageControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);
//
//		// DEBTOR
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Deudores");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{DebtorControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);
//
//		// DIRDEBIT
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Recibos");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{DirdebitControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);
//
//		// CREDITOR
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Acreedores");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{CreditorControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);
//
//		// TRANSFER
//		menuItem = new MenuItem();
//		menuItem.setIcon("ui-icon-contact");
//		menuItem.setValue("Transferecias");
//		menuItem.setAjax(false);
//		menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{TransferControllerQry.onPaginate}", String.class, new Class[0]));
//		menu.getChildren().add(menuItem);

		menuModel.addSubmenu(menu);
		menubar.setModel(menuModel);

	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public Menubar getMenubar() {
		return menubar;
	}

	public void setMenubar(Menubar menubar) {
		this.menubar = menubar;
	}

	public MenuItem getItem() {
		return menuItem;
	}

	public void setItem(MenuItem menuItem) {
		this.menuItem = menuItem;
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

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public String getAppName() {
		return "Sample P5";
	}
}