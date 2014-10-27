package com.sample.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.component.commandbutton.CommandButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sample.architecture.commons.utils.MessageFactory;
import com.sample.architecture.controller.AbstractControllerQry;
import com.sample.architecture.dao.Column;
import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.jpa.Employee;
import com.sample.service.IEmployeeService;

@Named("EmployeeControllerQry")
@Scope("session")
public class EmployeeControllerQry extends AbstractControllerQry<Employee> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(EmployeeControllerQry.class);

	// SERVICES
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	EmployeeControllerTx employeeControllerTx;

	// COMPONENTS
	private HtmlPanelGrid paginateFilterComponent;
	private HtmlPanelGrid actionsButtonsComponent;

	@Override
	public String onPaginate() throws Exception {
		try {
			if (this.mapParameters != null && !this.mapParameters.isEmpty()) {
				List<Filter> filters = new ArrayList<Filter>();

				for (Iterator<Entry<Object, Object>> iterator = mapParameters.entrySet().iterator(); iterator.hasNext();) {

					Map.Entry entry = (Map.Entry) iterator.next();
					Column column = new Column();
					column.setName(entry.getKey().toString());
					column.setType(String.class);
					column.setValue(entry.getValue());
					Filter filter = new Filter();
					filter.setColumn(column);
					filters.add(filter);
				}
				this.resultObjectsFiltered = this.employeeService.executeQueryFilter(filters, firstResult, maxResults);

			} else {
				findEntries(firstResult, maxResults);
			}
			reset();

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "employee-qry";
	}

	@Override
	public List<Column> getListColumns() throws Exception {
		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setName("name");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_employee_name"));
		column.setType(String.class);
		listColumns.add(column);
		return listColumns;
	}

	@Override
	public List<Employee> executeQueryFilter(List<Filter> listFilter, Integer firstResult, Integer maxResults) throws Exception {
		try {
			return this.employeeService.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Employee> findEntries(int firstResult, int maxResults) throws Exception {
		try {
			this.resultObjectsFiltered = this.employeeService.findEntries(firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return this.resultObjectsFiltered;
	}

	@Override
	public String runFromContextMenu(Employee item, String value, String action) throws Exception {
		Employee employee = (Employee) item;
		if (value.equalsIgnoreCase("ALBUM")) {
			this.employeeControllerTx.setDataObject(employee);
			if (action.equalsIgnoreCase("EDIT")) {
				return this.employeeControllerTx.onEdit();
			} else if (action.equalsIgnoreCase("DELETE")) {
				return this.employeeControllerTx.delete();
			}
		} else if (value.equalsIgnoreCase("ARTIST")) {
			// this.employeeControllerTx.setParentController(this);
			// if (action.equalsIgnoreCase("CREATE")) {
			// Employee employee = new Employee();
			// employee.setEmployeeId(employee.getEmployeeId())
			// this.employeeControllerTx.setDataObject(ARTIST)
			// return this.employeeControllerTx.onCreate();
			// } else if (action.equalsIgnoreCase("LIST")) {
			// this.employeeControllerQry.clearMapParamereters();
			// this.employeeControllerQry.addToMapParamereters(tempre.getEmpresa(), "empresa");
			// this.employeeControllerQry.addToMapParamereters(tempre.getPais(), "pais");
			// return this.employeeControllerQry.onPaginate();
			// }
		}

		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return null;
	}

	@Override
	public String runFromActionsButtons(String value, String action) throws Exception {

		Employee employee = this.dataObject;
		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.employeeControllerTx.setParentController(this);
				this.employeeControllerTx.setDataObject(new Employee());
				return this.employeeControllerTx.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return null;
	}

	// -------------------------------------------------------------
	// ---------------------- COMPONENTS ---------------------------
	// -------------------------------------------------------------

	public HtmlPanelGrid getPaginateFilterComponent() throws Exception {
		if (this.paginateFilterComponent == null) {
			return super.getPaginateFilterComponent(this.getClass().getSimpleName());
		} else {
			return this.paginateFilterComponent;
		}
	}

	public void setPaginateFilterComponent(HtmlPanelGrid paginateFilterComponent) {
		this.paginateFilterComponent = paginateFilterComponent;
	}

	public HtmlPanelGrid getActionsButtonsComponent() throws Exception {

		if (this.actionsButtonsComponent == null) {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			HtmlPanelGrid htmlPanelGrid = super.getActionsButtonsComponent(this.getClass().getSimpleName(), EmployeeControllerTx.class.getSimpleName());

			// CREATE
			CommandButton createButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			createButton.setId("createButtonId");
			createButton.setValue(MessageFactory.getStringMessage("i18n", "label_Create_new"));
			createButton.setUpdate(":buttonsComponentForm  :growlForm:growl");
			createButton.setImmediate(true);
			createButton.setAjax(false);
			createButton.setIcon("ui-icon-plus");
			createButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','CREATE')}", String.class, new Class[] { String.class, String.class }));

			htmlPanelGrid.getChildren().add(createButton);

			this.actionsButtonsComponent = htmlPanelGrid;
		}
		return this.actionsButtonsComponent;

	}

	public void setActionsButtonsComponent(HtmlPanelGrid actionsButtonsComponent) {
		this.actionsButtonsComponent = actionsButtonsComponent;
	}

	// public MenuModel getPaginateContextMenuComponent() throws Exception {
	// if (this.paginateContextMenuComponent == null) {
	// MenuModel menuModel = super.getPaginateContextMenuComponent(this.getClass().getSimpleName(),
	// EmployeeControllerTx.class.getSimpleName());
	//
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// Application application = facesContext.getApplication();
	// ExpressionFactory expressionFactory = application.getExpressionFactory();
	// ELContext elContext = facesContext.getELContext();
	//
	// // MENU ITEM DE EDICION
	// MenuItem menuItemEdit = new MenuItem();
	// menuItemEdit.setId("menuItemEditId");
	// menuItemEdit.setTitle(MessageFactory.getStringMessage("i18n", "label_Edit"));
	// menuItemEdit.setValue(MessageFactory.getStringMessage("i18n", "label_Edit"));
	// menuItemEdit.setUpdate(":buttonsComponentForm :filterForm :activeFilterForm :paginateForm :growlForm:growl");
	// menuItemEdit.setIcon("ui-icon-pencil");
	// menuItemEdit.setImmediate(true);
	// menuItemEdit.setAjax(false);
	// // menuItemEdit.addActionListener(new
	// // SetPropertyActionListener(expressionFactory.createValueExpression(elContext,
	// // "#{" + controllerTxName + ".dataObject}", Object.class),
	// // expressionFactory.createValueExpression(elContext, "#{item}",
	// // Object.class)));
	// menuItemEdit.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() +
	// ".runFromContextMenu(item,'ALBUM','EDIT')}", String.class, new Class[] { Object.class, String.class, String.class }));
	// menuModel.addMenuItem(menuItemEdit);
	//
	// return menuModel;
	// } else {
	// return this.paginateContextMenuComponent;
	// }
	//
	// }
	//
	// public void setPaginateContextMenuComponent(MenuModel paginateContextMenuComponent) {
	// this.paginateContextMenuComponent = paginateContextMenuComponent;
	// }

	// ----------------------------------------------------------------
	// --------------------- GETTERS AND SETTERS ----------------------
	// ----------------------------------------------------------------

	public Employee getDataObject() {
		if (dataObject == null) {
			dataObject = new Employee();
		}
		return dataObject;
	}

	public void setDataObject(Employee dataObject) {
		this.dataObject = dataObject;
	}
}
