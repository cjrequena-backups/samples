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
