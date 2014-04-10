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
import org.primefaces.model.MenuModel;
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
import com.sample.service.IAlbumService;

@Named("AlbumControllerQry")
@Scope("session")
public class AlbumControllerQry extends AbstractControllerQry<Album> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(AlbumControllerQry.class);
	
	//SERVICES
	@Autowired
	IAlbumService albumService;

	// COMPONENTS
	private HtmlPanelGrid paginateFilterComponent;
	private HtmlPanelGrid actionsButtonsComponent;
	private MenuModel paginateContextMenuComponent;

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
				this.resultObjectsFiltered = this.albumService.executeQueryFilter(filters, firstResult, maxResults);

			} else {
				findEntries(firstResult, maxResults);
			}
			reset();

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Album");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "album-qry";
	}

	@Override
	public List<Column> getListColumns() throws Exception {
		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setName("title");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_album_title"));
		column.setType(String.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("artistId.name");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_artist_name"));
		column.setType(String.class);
		listColumns.add(column);
		
		
		
		return listColumns;
	}

	@Override
	public List<Album> executeQueryFilter(List<Filter> listFilter, Integer firstResult, Integer maxResults) throws Exception {
		try {
			return this.albumService.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Album> findEntries(int firstResult, int maxResults) throws Exception {
		try {
			this.resultObjectsFiltered = this.albumService.findEntries(firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return this.resultObjectsFiltered;
	}

	@Override
	public String runFromContextMenu(Album item, String value, String action) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String runFromActionsButtons(String value, String action) throws Exception {

//		Tempre tempre = this.dataObject;
//		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
//			if (action.equalsIgnoreCase("CREATE")) {
//				this.tempreControllerTx.setDataObject(new Tempre());
//				this.tempreControllerTx.setParent(this);
//				return this.tempreControllerTx.onCreate();
//			}
//		}
//
//		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Tempre");
//		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
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
			HtmlPanelGrid htmlPanelGrid = super.getActionsButtonsComponent(this.getClass().getSimpleName(), AlbumControllerTx.class.getSimpleName());

			// CREATE
			CommandButton createButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			createButton.setId("createButtonId");
			createButton.setValue(MessageFactory.getStringMessage("i18n", "label_create_new"));
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

	public MenuModel getPaginateContextMenuComponent() throws Exception {
		if (this.paginateContextMenuComponent == null) {
			MenuModel menuModel = super.getPaginateContextMenuComponent(this.getClass().getSimpleName(), AlbumControllerTx.class.getSimpleName());

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();

			return menuModel;
		} else {
			return this.paginateContextMenuComponent;
		}

	}

	public void setPaginateContextMenuComponent(MenuModel paginateContextMenuComponent) {
		this.paginateContextMenuComponent = paginateContextMenuComponent;
	}

	// ----------------------------------------------------------------
	// --------------------- GETTERS AND SETTERS ----------------------
	// ----------------------------------------------------------------

	public Album getDataObject() {
		if (dataObject == null) {
			dataObject = new Album();
		}
		return dataObject;
	}

	public void setDataObject(Album dataObject) {
		this.dataObject = dataObject;
	}
}
