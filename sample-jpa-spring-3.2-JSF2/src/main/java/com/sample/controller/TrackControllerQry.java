package com.sample.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.sample.model.jpa.Track;
import com.sample.service.ITrackService;

@Named("TrackControllerQry")
@Scope("session")
public class TrackControllerQry extends AbstractControllerQry<Track> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(TrackControllerQry.class);

	// SERVICES
	@Autowired
	ITrackService trackService;
	@Autowired
	TrackControllerTx trackControllerTx;
	@Autowired
	MediaTypeControllerTx mediaTypeControllerTx;
	@Autowired
	MediaTypeControllerQry mediaTypeControllerQry;
	
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
				this.resultObjectsFiltered = this.trackService.executeQueryFilter(filters, firstResult, maxResults);

			} else {
				findEntries(firstResult, maxResults);
			}
			reset();

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "track-qry";
	}

	@Override
	public List<Column> getListColumns() throws Exception {
		List<Column> listColumns = new ArrayList<Column>();

		Column column = new Column();
		column.setName("name");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_track_name"));
		column.setType(String.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("composer");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_track_composer"));
		column.setType(String.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("milliseconds");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_track_milliseconds"));
		column.setType(Integer.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("bytes");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_track_bytes"));
		column.setType(Integer.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("unitPrice");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_track_unitPrice"));
		column.setType(BigDecimal.class);
		listColumns.add(column);
		
		column = new Column();
		column.setName("albumId.title");
		column.setLabel(MessageFactory.getStringMessage("i18n", "label_album_title"));
		column.setType(String.class);
		listColumns.add(column);

		return listColumns;
	}

	@Override
	public List<Track> executeQueryFilter(List<Filter> listFilter, Integer firstResult, Integer maxResults) throws Exception {
		try {
			return this.trackService.executeQueryFilter(listFilter, firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Track> findEntries(int firstResult, int maxResults) throws Exception {
		try {
			this.resultObjectsFiltered = this.trackService.findEntries(firstResult, maxResults);
		} catch (BusinessExceptions e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return this.resultObjectsFiltered;
	}

	@Override
	public String runFromContextMenu(Track item, String value, String action) throws Exception {
		Track track = (Track) item;
		if (value.equalsIgnoreCase("TRACK")) {
			this.trackControllerTx.setDataObject(track);
			if (action.equalsIgnoreCase("EDIT")) {
				return this.trackControllerTx.onEdit();
			} else if (action.equalsIgnoreCase("DELETE")) {
				return this.trackControllerTx.delete();
			}
		}
		
		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return null;
	}

	@Override
	public String runFromActionsButtons(String value, String action) throws Exception {

		Track track = this.dataObject;
		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.trackControllerTx.setParentController(this);
				this.trackControllerTx.setDataObject(new Track());
				return this.trackControllerTx.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
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
			HtmlPanelGrid htmlPanelGrid = super.getActionsButtonsComponent(this.getClass().getSimpleName(), TrackControllerTx.class.getSimpleName());

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
			MenuModel menuModel = super.getPaginateContextMenuComponent(this.getClass().getSimpleName(), TrackControllerTx.class.getSimpleName());

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();

			
			MenuItem menuItemEdit = new MenuItem();
			menuItemEdit.setId("menuItemEditId");
			menuItemEdit.setTitle(MessageFactory.getStringMessage("i18n", "label_edit"));
			menuItemEdit.setValue(MessageFactory.getStringMessage("i18n", "label_edit"));
			menuItemEdit.setUpdate(":buttonsComponentForm :filterForm :activeFilterForm :paginateForm :growlForm:growl");
			menuItemEdit.setIcon("ui-icon-pencil");
			menuItemEdit.setImmediate(true);
			menuItemEdit.setAjax(false);
			menuItemEdit.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromContextMenu(item,'TRACK','EDIT')}", String.class, new Class[] { Object.class, String.class, String.class }));
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

	public Track getDataObject() {
		if (dataObject == null) {
			dataObject = new Track();
		}
		return dataObject;
	}

	public void setDataObject(Track dataObject) {
		this.dataObject = dataObject;
	}
}
