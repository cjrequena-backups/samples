package com.sample.architecture.controller;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

/**
 * 
 * @author cjrequena
 * 
 * @param <T>
 */
public abstract class AbstractControllerTx<T> extends AbstractController<T> implements IControllerTx<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean flagCreate = false;

	@Override
	public HtmlPanelGrid getActionsButtonsComponent(String controllerQryName, String controllerTxName) throws Exception {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

			// CREATE
			// CommandButton createButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// createButton.setId("createTempleButtonId");
			// createButton.setValue(MessageFactory.getStringMessage("messages",
			// "label_create_new"));
			// createButton.setUpdate(":crudActionsForm  :growlForm:growl");
			// createButton.setImmediate(true);
			// createButton.setAjax(false);
			// createButton.setIcon("ui-icon-plus");
			// createButton.setValueExpression("rendered",
			// expressionFactory.createValueExpression(elContext, "#{" +
			// this.getClass().getSimpleName() + ".dataObject.pkObject!=null}",
			// boolean.class));
			// createButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('COMMONS_ACTIONS','CREATE')}",
			// String.class, new Class[] { String.class, String.class }));
			// htmlPanelGrid.getChildren().add(createButton);

			// SAVE
			// CommandButton saveCommandButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// saveCommandButton.setId("saveCommandButtonId");
			// saveCommandButton.setValue(MessageFactory.getStringMessage("messages",
			// "label_save"));
			// saveCommandButton.setUpdate(":crudActionsForm :growlForm:growl");
			// saveCommandButton.setImmediate(false);
			// saveCommandButton.setAjax(false);
			// saveCommandButton.setIcon("ui-icon-disk");
			// saveCommandButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('COMMONS_ACTIONS','SAVE')}",
			// String.class, new Class[] { String.class, String.class }));
			// htmlPanelGrid.getChildren().add(saveCommandButton);

			// DELETE
			// CommandButton deleteCommandButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// deleteCommandButton.setId("deleteCommandButtonId");
			// deleteCommandButton.setValue(MessageFactory.getStringMessage("messages",
			// "label_delete"));
			// deleteCommandButton.setUpdate(":crudActionsForm :growlForm:growl");
			// deleteCommandButton.setImmediate(true);
			// deleteCommandButton.setAjax(true);
			// deleteCommandButton.setIcon("ui-icon-disk");
			// deleteCommandButton.setValueExpression("rendered",
			// expressionFactory.createValueExpression(elContext, "#{" +
			// this.getClass().getSimpleName() + ".dataObject.pkObject !=null}",
			// boolean.class));
			// deleteCommandButton.setOncomplete("deleteDialogWidget.show()");
			// htmlPanelGrid.getChildren().add(deleteCommandButton);

			return htmlPanelGrid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public boolean isFlagCreate() {
		return flagCreate;
	}

	public void setFlagCreate(boolean flagCreate) {
		this.flagCreate = flagCreate;
	}
	
	@Override
	public String returnToParentController() throws Exception {
		return this.onEdit();
	}
}
