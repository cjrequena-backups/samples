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
import org.primefaces.component.menuitem.MenuItem;
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
import com.sample.model.jpa.Artist;
import com.sample.service.IArtistService;

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
				this.resultObjectsFiltered = this.artistService.executeQueryFilter(filters, firstResult, maxResults);

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
	public List<Column> getListColumns() throws Exception {
		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setName("name");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_artist_name"));
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

	@Override
	public String runFromContextMenu(Artist item, String value, String action) throws Exception {
		Artist artist = (Artist) item;
		if (value.equalsIgnoreCase("ALBUM")) {
			this.artistControllerTx.setDataObject(artist);
			if (action.equalsIgnoreCase("EDIT")) {
				return this.artistControllerTx.onEdit();
			} else if (action.equalsIgnoreCase("DELETE")) {
				return this.artistControllerTx.delete();
			}
		}else if (value.equalsIgnoreCase("ARTIST")) {
//			this.artistControllerTx.setParentController(this);
//			if (action.equalsIgnoreCase("CREATE")) {
//				Artist artist = new Artist();
//				artist.setArtistId(artist.getArtistId())
//				this.artistControllerTx.setDataObject(ARTIST)
//				return this.artistControllerTx.onCreate();
//			} else if (action.equalsIgnoreCase("LIST")) {
//				this.artistControllerQry.clearMapParamereters();
//				this.artistControllerQry.addToMapParamereters(tempre.getEmpresa(), "empresa");
//				this.artistControllerQry.addToMapParamereters(tempre.getPais(), "pais");
//				return this.artistControllerQry.onPaginate();
//			}
		}
		
		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Artist");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return null;
	}

	@Override
	public String runFromActionsButtons(String value, String action) throws Exception {

		Artist artist = this.dataObject;
		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.artistControllerTx.setParentController(this);
				this.artistControllerTx.setDataObject(new Artist());
				return this.artistControllerTx.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Artist");
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
			HtmlPanelGrid htmlPanelGrid = super.getActionsButtonsComponent(this.getClass().getSimpleName(), ArtistControllerTx.class.getSimpleName());

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
			MenuModel menuModel = super.getPaginateContextMenuComponent(this.getClass().getSimpleName(), ArtistControllerTx.class.getSimpleName());

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();

			// MENU ITEM DE EDICION
			MenuItem menuItemEdit = new MenuItem();
			menuItemEdit.setId("menuItemEditId");
			menuItemEdit.setTitle(MessageFactory.getStringMessage("i18n", "label_edit"));
			menuItemEdit.setValue(MessageFactory.getStringMessage("i18n", "label_edit"));
			menuItemEdit.setUpdate(":buttonsComponentForm :filterForm :activeFilterForm :paginateForm :growlForm:growl");
			menuItemEdit.setIcon("ui-icon-pencil");
			menuItemEdit.setImmediate(true);
			menuItemEdit.setAjax(false);
			// menuItemEdit.addActionListener(new
			// SetPropertyActionListener(expressionFactory.createValueExpression(elContext,
			// "#{" + controllerTxName + ".dataObject}", Object.class),
			// expressionFactory.createValueExpression(elContext, "#{item}",
			// Object.class)));
			menuItemEdit.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromContextMenu(item,'ALBUM','EDIT')}", String.class, new Class[] { Object.class, String.class, String.class }));
			menuModel.addMenuItem(menuItemEdit);

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
