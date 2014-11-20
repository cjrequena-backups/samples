package com.sample.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
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
import com.sample.model.jpa.Album;
import com.sample.model.jpa.Artist;
import com.sample.service.IArtistService;
import com.sun.faces.taglib.jsf_core.SetPropertyActionListenerImpl;

@Named("ArtistControllerQry")
@Scope("session")
public class ArtistControllerQry extends AbstractControllerQry<Artist> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(ArtistControllerQry.class);

	// SERVICES
	@Autowired
	IArtistService artistService;
	@Autowired
	ArtistControllerTx artistControllerTx;

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
					column.setType(entry.getValue().getClass());
					column.setValue(entry.getValue());
					Filter filter = new Filter();
					filter.setColumn(column);
					filters.add(filter);
				}
				this.resultObjectsFiltered = this.artistService.executeQueryFilter(filters, firstResult, maxResults);
				this.mapParameters.clear();

			} else {
				findEntries(firstResult, maxResults);
			}
			reset();

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Artist");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "artist-qry";
	}

	@Override
	public List<Column> getColumnsFilter() throws Exception {
		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setName("name");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_Name"));
		column.setType(String.class);
		listColumns.add(column);
		return listColumns;
	}

	@Override
	public List<Artist> executeQueryFilter(List<Filter> listFilter, Integer firstResult, Integer maxResults) throws Exception {
		try {
			return this.artistService.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Artist> findEntries(int firstResult, int maxResults) throws Exception {
		try {
			this.resultObjectsFiltered = this.artistService.findEntries(firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return this.resultObjectsFiltered;
	}

	// @Override
	// public String runFromContextMenu(Artist item, String value, String action) throws Exception {
	// Artist artist = (Artist) item;
	// if (value.equalsIgnoreCase("ALBUM")) {
	// this.artistControllerTx.setDataObject(artist);
	// if (action.equalsIgnoreCase("EDIT")) {
	// return this.artistControllerTx.onEdit();
	// } else if (action.equalsIgnoreCase("DELETE")) {
	// return this.artistControllerTx.delete();
	// }
	// } else if (value.equalsIgnoreCase("ARTIST")) {
	//
	// }
	//
	// FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Artist");
	// FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	// return null;
	// }
	//
	// @Override
	// public String runFromActionsButtons(String value, String action) throws Exception {
	//
	// Artist artist = this.dataObject;
	// if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
	// if (action.equalsIgnoreCase("CREATE")) {
	// this.artistControllerTx.setParentController(this);
	// this.artistControllerTx.setDataObject(new Artist());
	// return this.artistControllerTx.onCreate();
	// }
	// }
	//
	// FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Artist");
	// FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	// return null;
	// }

	// -------------------------------------------------------------
	// ---------------------- COMPONENTS ---------------------------
	// -------------------------------------------------------------

	// public HtmlPanelGrid getActionsButtonsComponent() throws Exception {
	//
	// if (this.actionsButtonsComponent == null) {
	//
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// Application application = facesContext.getApplication();
	// ExpressionFactory expressionFactory = application.getExpressionFactory();
	// ELContext elContext = facesContext.getELContext();
	// HtmlPanelGrid htmlPanelGrid =
	// super.getActionsButtonsComponent(this.getClass().getSimpleName(),
	// ArtistControllerTx.class.getSimpleName());
	//
	// // CREATE
	// CommandButton createButton = (CommandButton)
	// application.createComponent(CommandButton.COMPONENT_TYPE);
	// createButton.setId("createButtonId");
	// createButton.setValue(MessageFactory.getStringMessage("i18n", "label_Create_new"));
	// createButton.setUpdate(":buttonsComponentForm  :growlForm:growl");
	// createButton.setImmediate(true);
	// createButton.setAjax(false);
	// createButton.setIcon("ui-icon-plus");
	// createButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" +
	// this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','CREATE')}",
	// String.class, new Class[] { String.class, String.class }));
	//
	// htmlPanelGrid.getChildren().add(createButton);
	//
	// this.actionsButtonsComponent = htmlPanelGrid;
	// }
	// return this.actionsButtonsComponent;
	//
	// }

	// public void setActionsButtonsComponent(HtmlPanelGrid actionsButtonsComponent) {
	// this.actionsButtonsComponent = actionsButtonsComponent;
	// }

	// public MenuModel getPaginateContextMenuComponent() throws Exception {
	// if (this.paginateContextMenuComponent == null) {
	//
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// Application application = facesContext.getApplication();
	// ExpressionFactory expressionFactory = application.getExpressionFactory();
	// ELContext elContext = facesContext.getELContext();
	//
	// MenuModel menuModel = super.getPaginateContextMenuComponent(this.getClass().getSimpleName(),
	// ArtistControllerTx.class.getSimpleName());
	// MenuItem menuItem = null;
	// ValueExpression targetExpression = null;
	// ValueExpression valueExpression = null;
	//
	// // EDIT
	// menuItem = new MenuItem();
	// menuItem.setId("menuItemEditId");
	// menuItem.setTitle(MessageFactory.getStringMessage("i18n", "label_Edit"));
	// menuItem.setValue(MessageFactory.getStringMessage("i18n", "label_Edit"));
	// menuItem.setUpdate(":buttonsComponentForm :filterForm :activeFilterForm :paginateForm :growlForm:growl");
	// menuItem.setIcon("ui-icon-pencil");
	// menuItem.setImmediate(true);
	// menuItem.setAjax(false);
	// targetExpression = expressionFactory.createValueExpression(elContext, "#{" +
	// ArtistControllerTx.class.getSimpleName() + ".dataObject}", Artist.class);
	// valueExpression = expressionFactory.createValueExpression(elContext, "#{item}",
	// Artist.class);
	// menuItem.addActionListener(new SetPropertyActionListenerImpl(targetExpression,
	// valueExpression));
	// menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" +
	// ArtistControllerTx.class.getSimpleName() + ".onEdit()}", String.class, new Class[0]));
	// menuModel.addMenuItem(menuItem);
	//
	// // DELETE
	// menuItem = new MenuItem();
	// menuItem.setId("menuItemDeleteId");
	// menuItem.setTitle(MessageFactory.getStringMessage("i18n", "label_Delete"));
	// menuItem.setValue(MessageFactory.getStringMessage("i18n", "label_Delete"));
	// menuItem.setUpdate(":buttonsComponentForm :filterForm :activeFilterForm :paginateForm :growlForm:growl");
	// menuItem.setIcon("ui-icon-pencil");
	// menuItem.setImmediate(true);
	// menuItem.setAjax(false);
	// targetExpression = expressionFactory.createValueExpression(elContext, "#{" +
	// ArtistControllerTx.class.getSimpleName() + ".dataObject}", Artist.class);
	// valueExpression = expressionFactory.createValueExpression(elContext, "#{item}",
	// Artist.class);
	// menuItem.addActionListener(new SetPropertyActionListenerImpl(targetExpression,
	// valueExpression));
	// menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" +
	// ArtistControllerTx.class.getSimpleName() + ".delete()}", String.class, new Class[0]));
	// menuModel.addMenuItem(menuItem);
	//
	// // VIEW ALBUMS
	// menuItem = new MenuItem();
	// menuItem.setId("menuItemViewAlbumsId");
	// menuItem.setValue(MessageFactory.getStringMessage("i18n", "label_View_artist_albums"));
	// menuItem.setUpdate(":txForm :growlForm:growl");
	// menuItem.setImmediate(true);
	// menuItem.setAjax(false);
	// menuItem.setIcon("ui-icon-disk");
	// menuItem.addActionListener(new
	// MethodExpressionActionListener(expressionFactory.createMethodExpression(elContext, "#{" +
	// AlbumControllerQry.class.getSimpleName() + ".clearMapParamereters()}", String.class, new
	// Class[0])));
	// menuItem.addActionListener(super.addToMapParameretersListenerByElExpression(facesContext,
	// AlbumControllerQry.class.getSimpleName(), "item.artistId", "artistId.artistId"));
	// menuItem.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" +
	// AlbumControllerQry.class.getSimpleName() + ".onPaginate()}", String.class, new Class[0]));
	// menuModel.addMenuItem(menuItem);
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

	public HtmlPanelGrid getFilterComponent() throws Exception {
		if (this.paginateFilterComponent == null) {
			return super.getFilterComponent(this.getClass().getSimpleName());
		} else {
			return this.paginateFilterComponent;
		}
	}

	public void setPaginateFilterComponent(HtmlPanelGrid paginateFilterComponent) {
		this.paginateFilterComponent = paginateFilterComponent;
	}

	// ----------------------------------------------------------------
	// --------------------- GETTERS AND SETTERS ----------------------
	// ----------------------------------------------------------------

	public Artist getDataObject() {
		if (dataObject == null) {
			dataObject = new Artist();
		}
		return dataObject;
	}

	public void setDataObject(Artist dataObject) {
		this.dataObject = dataObject;
	}
}
