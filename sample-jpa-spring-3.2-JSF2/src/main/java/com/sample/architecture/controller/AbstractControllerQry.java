/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.sample.architecture.commons.utils.MessageFactory;
import com.sample.architecture.converter.ColumnConverter;
import com.sample.architecture.dao.Column;
import com.sample.architecture.dao.Criteria;
import com.sample.architecture.dao.Filter;

/**
 * 
 * @author cjrequena
 * 
 * @param <T>
 */
public abstract class AbstractControllerQry<T> extends AbstractController<T> implements IControllerQry<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<T> resultObjectsFiltered;

	protected List<Filter> listFilter;

	protected Filter filter;

	@Override
	public void addFilter() throws Exception {
		try {
			if (this.filter.getColumn().getType().equals(Integer.class)) {

				String value = (String) this.filter.getColumn().getValue();
				this.filter.getColumn().setValue(Integer.valueOf(value));

			} else if (this.filter.getColumn().getType().equals(BigDecimal.class)) {

				String value = (String) this.filter.getColumn().getValue();
				this.filter.getColumn().setValue(new BigDecimal(value));

			}
			this.getListFilter().add(this.filter);

			this.resultObjectsFiltered = this.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		this.filter = new Filter();
	}

	public void removeFilter() throws Exception {
		try {
			if (this.listFilter != null && !this.listFilter.isEmpty()) {
				this.listFilter.remove(this.filter);
			}
			this.resultObjectsFiltered = this.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public List<Column> autoCompleteColumn(String query) throws Exception {
		List<Column> suggestions = new ArrayList<Column>();
		try {
			for (Column Column : this.getListColumns()) {
				String ColumnStr = String.valueOf(Column.getName());
				if (ColumnStr.toLowerCase().startsWith(query.toLowerCase())) {
					suggestions.add(Column);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return suggestions;
	}

	public List<Criteria> autoCompleteCriteria(String query) throws Exception {
		List<Criteria> suggestions = new ArrayList<Criteria>();
		try {
			for (Criteria criteria : this.filter.getListCriteria()) {
				String criteriaStr = String.valueOf(criteria.getCriteriaName());
				if (criteriaStr.toLowerCase().startsWith(query.toLowerCase())) {
					suggestions.add(criteria);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return suggestions;
	}

	public void reset() {
		this.listFilter = null;
		this.filter = null;
	}

	public HtmlPanelGrid getActionsButtonsComponent(String controllerQryName, String controllerTxName) throws Exception {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

			return htmlPanelGrid;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public HtmlPanelGrid getPaginateFilterComponent(String controllerQryName) throws Exception {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);

			/* CREAMOS EL AUTOCOMPLETE DE COLUMNAS DE FILTRADO */
			OutputLabel outputLabelColumn = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
			outputLabelColumn.setFor("autoCompleteColumnId");
			outputLabelColumn.setId("outputLabelColumn");
			outputLabelColumn.setValue(MessageFactory.getStringMessage("i18n", "label_Column"));
			outputLabelColumn.setStyleClass("col1");
			htmlPanelGrid.getChildren().add(outputLabelColumn);

			AutoComplete autoCompleteColumn = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
			autoCompleteColumn.setId("autoCompleteColumnId");
			autoCompleteColumn.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{" + controllerQryName + ".filter.column}", Column.class));
			autoCompleteColumn.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{" + controllerQryName + ".autoCompleteColumn}", List.class, new Class[] { String.class }));
			autoCompleteColumn.setValueExpression("var", expressionFactory.createValueExpression(elContext, "column", String.class));
			autoCompleteColumn.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{column}", Column.class));
			autoCompleteColumn.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{column.label}", String.class));
			autoCompleteColumn.setConverter(new ColumnConverter());
			autoCompleteColumn.setRequired(true);
			autoCompleteColumn.setDropdown(true);
			autoCompleteColumn.setSize(14);
			htmlPanelGrid.getChildren().add(autoCompleteColumn);

			Message messageColumn = (Message) application.createComponent(Message.COMPONENT_TYPE);
			messageColumn.setId("messageDColumnId");
			messageColumn.setFor("autoCompleteColumnId");
			messageColumn.setDisplay("icon");
			htmlPanelGrid.getChildren().add(messageColumn);

			/* CREAMOS EL AUTOCOMPLETE DE CRITERIOS DE FILTRADO */
			OutputLabel outputLabelCriteria = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
			outputLabelCriteria.setId("outputLabelCriteriaId");
			outputLabelCriteria.setFor("autoCompleteCriteriaId");
			outputLabelCriteria.setValue(MessageFactory.getStringMessage("i18n", "label_Criteria"));
			outputLabelCriteria.setStyleClass("col1");
			htmlPanelGrid.getChildren().add(outputLabelCriteria);

			AutoComplete autoCompleteCriteria = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
			autoCompleteCriteria.setId("autoCompleteCriteriaId");
			autoCompleteCriteria.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{" + controllerQryName + ".filter.criteria}", Criteria.class));
			autoCompleteCriteria.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{" + controllerQryName + ".autoCompleteCriteria}", List.class, new Class[] { String.class }));
			autoCompleteCriteria.setValueExpression("var", expressionFactory.createValueExpression(elContext, "criteria", String.class));
			autoCompleteCriteria.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{criteria}", Criteria.class));
			autoCompleteCriteria.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{criteria.shortDescription}", String.class));
			autoCompleteCriteria.setRequired(true);
			autoCompleteCriteria.setDropdown(true);
			autoCompleteCriteria.setSize(14);
			htmlPanelGrid.getChildren().add(autoCompleteCriteria);

			Message messageCriteria = (Message) application.createComponent(Message.COMPONENT_TYPE);
			messageCriteria.setId("messageCriteriaId");
			messageCriteria.setFor("autoCompleteCriteriaId");
			messageCriteria.setDisplay("icon");
			htmlPanelGrid.getChildren().add(messageCriteria);

			/* VALUE */
			OutputLabel outputLabelValue = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
			outputLabelValue.setId("outputLabelValueId");
			outputLabelValue.setFor("inputTextValue");
			outputLabelValue.setValue(MessageFactory.getStringMessage("i18n", "label_Value"));
			outputLabelValue.setStyleClass("col1");
			htmlPanelGrid.getChildren().add(outputLabelValue);

			InputText inputTextValue = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
			inputTextValue.setId("inputTextValue");
			inputTextValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{" + controllerQryName + ".filter.column.value}", Object.class));
			inputTextValue.setValueExpression("var", expressionFactory.createValueExpression(elContext, "value", Object.class));
			inputTextValue.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{value}", Object.class));
			inputTextValue.setRequired(Boolean.TRUE);
			inputTextValue.setSize(14);
			htmlPanelGrid.getChildren().add(inputTextValue);

			Message messageValue = (Message) application.createComponent(Message.COMPONENT_TYPE);
			messageValue.setId("messageValueId");
			messageValue.setFor("inputTextValue");
			messageValue.setDisplay("icon");
			htmlPanelGrid.getChildren().add(messageValue);

			/* ANDOR */
			OutputLabel outputLabelAndOr = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
			outputLabelAndOr.setId("outputLabelAndOrId");
			outputLabelAndOr.setFor("selectBooleanCheckboxtAndOr");
			outputLabelAndOr.setValue(MessageFactory.getStringMessage("i18n", "label_And"));
			outputLabelAndOr.setStyleClass("col1");

			htmlPanelGrid.getChildren().add(outputLabelAndOr);

			SelectBooleanCheckbox selectBooleanCheckboxtAndOr = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
			selectBooleanCheckboxtAndOr.setId("selectBooleanCheckboxtAndOr");
			selectBooleanCheckboxtAndOr.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{" + controllerQryName + ".filter.andOr}", Boolean.class));
			htmlPanelGrid.getChildren().add(selectBooleanCheckboxtAndOr);

			Message messageAndOr = (Message) application.createComponent(Message.COMPONENT_TYPE);
			messageAndOr.setId("messageAndOrId");
			messageAndOr.setFor("selectBooleanCheckboxtAndOr");
			messageAndOr.setDisplay("icon");
			htmlPanelGrid.getChildren().add(messageAndOr);

			/* CASE SENSITIVE */
			OutputLabel outputLabelCaseSensitive = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
			outputLabelCaseSensitive.setId("outputLabelCaseSensitiveId");
			outputLabelCaseSensitive.setFor("selectBooleanCheckboxtCaseSensitive");
			outputLabelCaseSensitive.setValue(MessageFactory.getStringMessage("i18n", "label_Case_sensitive"));
			outputLabelCaseSensitive.setStyleClass("col1");
			htmlPanelGrid.getChildren().add(outputLabelCaseSensitive);

			SelectBooleanCheckbox selectBooleanCheckboxtCaseSensitive = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
			selectBooleanCheckboxtCaseSensitive.setId("selectBooleanCheckboxtCaseSensitive");
			selectBooleanCheckboxtCaseSensitive.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{" + controllerQryName + ".filter.caseSensitive}", Boolean.class));
			htmlPanelGrid.getChildren().add(selectBooleanCheckboxtCaseSensitive);

			Message messageCaseSensitive = (Message) application.createComponent(Message.COMPONENT_TYPE);
			messageCaseSensitive.setId("messageCaseSensitiveId");
			messageCaseSensitive.setFor("selectBooleanCheckboxtCaseSensitive");
			messageCaseSensitive.setDisplay("icon");
			htmlPanelGrid.getChildren().add(messageCaseSensitive);

			return htmlPanelGrid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public MenuModel getPaginateContextMenuComponent(String controllerQryName, String controllerTxName) throws Exception {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();

			MenuModel menuModel = new DefaultMenuModel();

			return menuModel;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	/* #################################### */
	/* ######## GETTERS AND SETTERS ####### */
	/* #################################### */

	public List<Filter> getListFilter() {
		if (this.listFilter == null) {
			this.listFilter = new ArrayList<Filter>();
		}
		return listFilter;
	}

	public void setListFilter(List<Filter> listFilter) {
		this.listFilter = listFilter;
	}

	public List<T> getResultObjectsFiltered() {
		return resultObjectsFiltered;
	}

	public void setResultObjectsFiltered(List<T> resultObjectsFiltered) {
		this.resultObjectsFiltered = resultObjectsFiltered;
	}

	public Filter getFilter() {
		if (this.filter == null) {
			this.filter = new Filter();
		}
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	@Override
	public String returnToParentController() throws Exception {
		return onPaginate();
	}

}
