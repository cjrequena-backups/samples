package com.sample.architecture.controller;

import java.util.List;

import javax.faces.component.html.HtmlPanelGrid;

import org.primefaces.model.MenuModel;

import com.sample.architecture.dao.Column;
import com.sample.architecture.dao.Criteria;
import com.sample.architecture.dao.Filter;

public interface IControllerQry<T> {

	public String onPaginate() throws Exception;

	public List<Column> getListColumns() throws Exception;

	public List<T> executeQueryFilter(List<Filter> listFilter, Integer firstResult, Integer maxResults) throws Exception;

	public List<T> findEntries(int firstResult, int maxResults) throws Exception;

	public HtmlPanelGrid getActionsButtonsComponent(String controllerQryName, String controllerTxName) throws Exception;

	public HtmlPanelGrid getPaginateFilterComponent(String controllerQryName) throws Exception;

	public MenuModel getPaginateContextMenuComponent(String controllerQryName, String controllerTxName) throws Exception;

	public void addFilter() throws Exception;

	public void removeFilter() throws Exception;

	public List<Column> autoCompleteColumn(String query) throws Exception;

	public List<Criteria> autoCompleteCriteria(String query) throws Exception;

	public String runFromContextMenu(T item, String value, String action) throws Exception;

	public String runFromActionsButtons(String value, String action) throws Exception;


}
