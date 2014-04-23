package com.sample.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import com.sample.controller.converter.ArtistConverter;
import com.sample.model.jpa.Album;
import com.sample.model.jpa.Artist;
import com.sample.service.IAlbumService;
import com.sample.service.IArtistService;

@Named("AlbumControllerTx")
@Scope("session")
public class AlbumControllerTx extends AbstractControllerTx<Album> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AlbumControllerTx.class);

	// SERVICES
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IArtistService artistService;

	// COMPONENTS
	private HtmlPanelGrid actionsButtonsComponent;

	// COMVERTERS
	private ArtistConverter artistConverter;

	@Override
	public String onCreate() throws Exception {
		try {
			this.dataObject = new Album();
			this.flagCreate = true;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Album");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "album-tx";
	}

	@Override
	public String onEdit() throws Exception {
		try {
			this.flagCreate = false;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Album");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "album-tx";
	}

	@Override
	public String persist() throws Exception {
		String message = this.validate();
		if (message == null) {
			try {
				if (dataObject != null && this.albumService.findByPk(dataObject.getAlbumId()) != null) {
					this.albumService.update(dataObject);
					message = "message_successfully_updated";
				} else {
					this.albumService.save(dataObject);
					message = "message_successfully_created";
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = "message_error";
				FacesMessage facesMessage = MessageFactory.getMessage(message, "Album");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				return this.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Album");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "album-tx";
		}
	}

	@Override
	public String delete() throws Exception {
		String message = "message_successfully_deleted";
		try {
			this.flagCreate = false;
			Integer pk = this.dataObject.getAlbumId();
			this.albumService.deleteByPk(pk);
		} catch (Exception e) {
			e.printStackTrace();
			message = "message_error";
			FacesMessage facesMessage = MessageFactory.getMessage(message, "Album");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			throw new Exception(e);
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Album");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "album-tx";
		}

	}

	@Override
	public String validate() throws Exception {
		String message = null;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Album>> constraintViolations = validator.validate(this.dataObject);

		for (ConstraintViolation<Album> constraintViolation : constraintViolations) {
			message = constraintViolation.getPropertyPath() + " -> " + constraintViolation.getMessage();
		}
		return message;
	}

	public List<Artist> autocompleteArtist(String query) {
		List<Artist> suggestions = new ArrayList<Artist>();
		try {
			for (Artist artist : artistService.findAll()) {

				String obj = String.valueOf(artist.getName());

				if (obj.toLowerCase().contains(query.toLowerCase())) {
					suggestions.add(artist);
				}
			}
		} catch (Exception e) {
			logger.error("Error executing artist autocomplete " + e.getMessage());
		}
		return suggestions;
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
		// Album album = this.dataObject;

		// if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
		// if (action.equalsIgnoreCase("CREATE")) {
		// this.dataObject = new Album();
		// return this.onCreate();
		// }
		// if (action.equalsIgnoreCase("SAVE")) {
		// return this.persist();
		// }
		// if (action.equalsIgnoreCase("DELETE")) {
		// return this.delete();
		// }
		// }
		// FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Album");
		// FacesContext.getCurrentInstance().addMessage(null, facesMessage);
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
			this.actionsButtonsComponent = super.getActionsButtonsComponent(AlbumControllerQry.class.getSimpleName(), this.getClass().getSimpleName());

			CommandButton commandButton = null;

			// CREATE
			commandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			commandButton.setId("createCommandButtonId");
			commandButton.setValue(MessageFactory.getStringMessage("i18n", "label_Create"));
			commandButton.setUpdate(":txForm  :growlForm:growl");
			commandButton.setImmediate(true);
			commandButton.setAjax(false);
			commandButton.setIcon("ui-icon-plus");
			commandButton.setRendered(this.dataObject != null && this.dataObject.getAlbumId() != null);
			// commandButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " +
			// this.getClass().getSimpleName() + ".dataObject.albumId!=null}  ", boolean.class));
			commandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".onCreate()}", String.class, new Class[0]));
			this.actionsButtonsComponent.getChildren().add(commandButton);

			// SAVE
			commandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			commandButton.setId("saveCommandButtonId");
			commandButton.setValue(MessageFactory.getStringMessage("i18n", "label_Save"));
			commandButton.setUpdate(":txForm :growlForm:growl");
			commandButton.setImmediate(false);
			commandButton.setAjax(false);
			commandButton.setIcon("ui-icon-disk");
			commandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".persist()}", String.class, new Class[0]));
			this.actionsButtonsComponent.getChildren().add(commandButton);

			// DELETE
			commandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			commandButton.setId("deleteCommandButtonId");
			commandButton.setValue(MessageFactory.getStringMessage("i18n", "label_Delete"));
			commandButton.setUpdate(":txForm :growlForm:growl");
			commandButton.setImmediate(true);
			commandButton.setAjax(false);
			commandButton.setIcon("ui-icon-disk");
			commandButton.setRendered(this.dataObject != null && this.dataObject.getAlbumId() != null);
			// commandButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " +
			// this.getClass().getSimpleName() + ".dataObject.albumId!=null}  ", boolean.class));
			commandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".delete()}", String.class, new Class[0]));
			this.actionsButtonsComponent.getChildren().add(commandButton);

			// VIEW ALL ALBUMS
			commandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			commandButton.setId("listAlbumsCommandButtonId");
			commandButton.setValue(MessageFactory.getStringMessage("i18n", "label_View_albums"));
			commandButton.setUpdate(":txForm :growlForm:growl");
			commandButton.setImmediate(true);
			commandButton.setAjax(false);
			commandButton.setIcon("ui-icon-disk");
			commandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + AlbumControllerQry.class.getSimpleName() + ".onPaginate()}", String.class, new Class[0]));
			this.actionsButtonsComponent.getChildren().add(commandButton);

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
	public Album getDataObject() {
		if (dataObject == null) {
			dataObject = new Album();
		}
		return dataObject;
	}

	public void setDataObject(Album dataObject) {
		this.dataObject = dataObject;
	}

	public ArtistConverter getArtistConverter() {
		if (this.artistConverter == null) {
			this.artistConverter = new ArtistConverter(this.artistService);
		}
		return artistConverter;
	}

	public void setArtistConverter(ArtistConverter artistConverter) {
		this.artistConverter = artistConverter;
	}

}