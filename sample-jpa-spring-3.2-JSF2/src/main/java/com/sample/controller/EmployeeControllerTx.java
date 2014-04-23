package com.sample.controller;

import java.io.Serializable;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sample.architecture.commons.utils.MessageFactory;
import com.sample.architecture.controller.AbstractControllerTx;
import com.sample.controller.converter.EmployeeConverter;
import com.sample.model.jpa.Employee;
import com.sample.service.IEmployeeService;

@Named("EmployeeControllerTx")
@Scope("session")
public class EmployeeControllerTx extends AbstractControllerTx<Employee> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTx.class);

	// SERVICES
	@Autowired
	private IEmployeeService employeeService;

	// COMPONENTS
	private HtmlPanelGrid actionsButtonsComponent;

	// COMVERTERS
	private EmployeeConverter employeeConverter;

	@Override
	public String onCreate() throws Exception {
		try {
			this.dataObject = new Employee();
			this.flagCreate = true;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "employee-tx";
	}

	@Override
	public String onEdit() throws Exception {
		try {
			this.flagCreate = false;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "employee-tx";
	}

	@Override
	public String persist() throws Exception {
		String message = this.validate();
		if (message == null) {
			try {
				if (dataObject != null && this.employeeService.findByPk(dataObject.getEmployeeId()) != null) {
					this.employeeService.update(dataObject);
					message = "message_successfully_updated";
				} else {
					this.employeeService.save(dataObject);
					message = "message_successfully_created";
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = "message_error";
				FacesMessage facesMessage = MessageFactory.getMessage(message, "Employee");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				return this.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Employee");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "employee-tx";
		}
	}

	@Override
	public String delete() throws Exception {
		String message = "message_successfully_deleted";
		try {
			this.flagCreate = false;
			Integer pk = this.dataObject.getEmployeeId();
			this.employeeService.deleteByPk(pk);
		} catch (Exception e) {
			e.printStackTrace();
			message = "message_error";
			FacesMessage facesMessage = MessageFactory.getMessage(message, "Employee");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			throw new Exception(e);
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Employee");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "employee-tx";
		}

	}

	@Override
	public String validate() throws Exception {
		String message = null;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(this.dataObject);

		for (ConstraintViolation<Employee> constraintViolation : constraintViolations) {
			message = constraintViolation.getPropertyPath() + " -> " + constraintViolation.getMessage();
		}
		return message;
	}

	public void handleAutocompleteSelect(SelectEvent event) {
		// if (event.getObject() instanceof Tpais) {
		// Tpais tpais = (Tpais) event.getObject();
		// this.dataObject.setFk_tpostal_2(new Tpostal());
		// this.dataObject.getFk_tpostal_2().setFk_tprov_2(new Tprov());
		// this.dataObject.getFk_tpostal_2().getFk_tprov_2().setFk_tpais_2(tpais);
		// } else if (event.getObject() instanceof Tprov) {
		// Tpais tpais =
		// this.dataObject.getFk_tpostal_2().getFk_tprov_2().getFk_tpais_2();
		// Tprov tprov = (Tprov) event.getObject();
		// this.dataObject.setFk_tpostal_2(new Tpostal());
		// this.dataObject.getFk_tpostal_2().setFk_tprov_2(tprov);
		// this.dataObject.getFk_tpostal_2().getFk_tprov_2().setFk_tpais_2(tpais);
		// } else if (event.getObject() instanceof Tpostal) {
		// logger.debug("COD. POSTAL SELECTED: " + ((Tpostal)
		// event.getObject()).getCpostal());
		// }
	}

	@Override
	public String runFromActionsButtons(String value, String action) throws Exception {
		Employee employee = this.dataObject;

		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.dataObject = new Employee();
				return this.onCreate();
			}
			if (action.equalsIgnoreCase("SAVE")) {
				return this.persist();
			}
			if (action.equalsIgnoreCase("DELETE")) {
				return this.delete();
			}
		}

		// else if (value.equalsIgnoreCase("P5_Employee")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// return this.employeeControllerQry.onPaginate();
		// }
		// } else if (value.equalsIgnoreCase("P5_TEMPLE")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// this.templeControllerQry.clearMapParamereters();
		// this.templeControllerQry.addToMapParamereters(employee.geEmployeesa(),
		// "empresa");
		// this.templeControllerQry.addToMapParamereters(employee.getPais(),
		// "pais");
		// return this.templeControllerQry.onPaginate();
		// }
		// }
		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Employee");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return null;
	}

	// -------------------------------------------------------------
	// ---------------------- COMPONENTS ---------------------------
	// -------------------------------------------------------------

	public HtmlPanelGrid getActionsButtonsComponent() throws Exception {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			this.actionsButtonsComponent = super.getActionsButtonsComponent(EmployeeControllerQry.class.getSimpleName(), this.getClass().getSimpleName());

			// CREATE
			CommandButton createButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			createButton.setId("createTempleButtonId");
			createButton.setValue(MessageFactory.getStringMessage("i18n", "label_Create_new"));
			createButton.setUpdate(":txForm  :growlForm:growl");
			createButton.setImmediate(true);
			createButton.setAjax(false);
			createButton.setIcon("ui-icon-plus");
			createButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.employeeId!=null}  ", boolean.class));
			createButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','CREATE')}", String.class, new Class[] { String.class, String.class }));
			this.actionsButtonsComponent.getChildren().add(createButton);

			// SAVE
			CommandButton saveCommandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			saveCommandButton.setId("saveCommandButtonId");
			saveCommandButton.setValue(MessageFactory.getStringMessage("i18n", "label_Save"));
			saveCommandButton.setUpdate(":txForm :growlForm:growl");
			saveCommandButton.setImmediate(false);
			saveCommandButton.setAjax(false);
			saveCommandButton.setIcon("ui-icon-disk");
			saveCommandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','SAVE')}", String.class, new Class[] { String.class, String.class }));
			this.actionsButtonsComponent.getChildren().add(saveCommandButton);

			// DELETE
			CommandButton deleteCommandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			deleteCommandButton.setId("deleteCommandButtonId");
			deleteCommandButton.setValue(MessageFactory.getStringMessage("i18n", "label_Delete"));
			deleteCommandButton.setUpdate(":txForm :growlForm:growl");
			deleteCommandButton.setImmediate(true);
			deleteCommandButton.setAjax(true);
			deleteCommandButton.setIcon("ui-icon-disk");
			deleteCommandButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.employeeId!=null}  ", boolean.class));
			deleteCommandButton.setOncomplete("deleteDialogWidget.show()");
			this.actionsButtonsComponent.getChildren().add(deleteCommandButton);

			// LIST ALL Employee
			// CommandButton listEmployeeButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listEmployeeButton.setId("listEmployeeButtonId");
			// listEmployeeButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_Employee_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listEmployeeButton.setUpdate(":growlForm:growl");
			// listEmployeeButton.setImmediate(true);
			// listEmployeeButton.setAjax(false);
			// listEmployeeButton.setIcon("ui-icon-document");
			// listEmployeeButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_Employee','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listEmployeeButton);

			// LIST TEMPLE BY EMPRESA
			// CommandButton listTempleByEmployeeButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listTempleByEmployeeButton.setId("listTempleByEmployeeButtonId");
			// listTempleByEmployeeButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_TEMPLE_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listTempleByEmployeeButton.setUpdate(":growlForm:growl");
			// listTempleByEmployeeButton.setImmediate(true);
			// listTempleByEmployeeButton.setAjax(false);
			// listTempleByEmployeeButton.setIcon("ui-icon-document");
			// listTempleByEmployeeButton.setValueExpression("rendered",
			// expressionFactory.createValueExpression(elContext, "#{" +
			// this.getClass().getSimpleName() + ".dataObject.pkObject!=null}",
			// boolean.class));
			// listTempleByEmployeeButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_TEMPLE','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listTempleByEmployeeButton);
			return this.actionsButtonsComponent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void setActionsButtonsComponent(HtmlPanelGrid actionsButtonsComponent) {
		this.actionsButtonsComponent = actionsButtonsComponent;
	}

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

	public EmployeeConverter getEmployeeConverter() {
		if (this.employeeConverter == null) {
			this.employeeConverter = new EmployeeConverter(this.employeeService);
		}
		return employeeConverter;
	}

	public void setEmployeeConverter(EmployeeConverter employeeConverter) {
		this.employeeConverter = employeeConverter;
	}

}