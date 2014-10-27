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
import com.sample.controller.converter.AlbumConverter;
import com.sample.controller.converter.GenreConverter;
import com.sample.controller.converter.MediaTypeConverter;
import com.sample.model.jpa.Album;
import com.sample.model.jpa.Genre;
import com.sample.model.jpa.MediaType;
import com.sample.model.jpa.Track;
import com.sample.service.IAlbumService;
import com.sample.service.IGenreService;
import com.sample.service.IMediaTypeService;
import com.sample.service.ITrackService;

@Named("TrackControllerTx")
@Scope("session")
public class TrackControllerTx extends AbstractControllerTx<Track> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TrackControllerTx.class);

	// SERVICES
	@Autowired
	private ITrackService trackService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IMediaTypeService mediaTypeService;
	@Autowired
	private IGenreService genreService;

	// COMPONENTS
	private HtmlPanelGrid actionsButtonsComponent;

	// COMVERTERS
	private AlbumConverter albumConverter;
	private MediaTypeConverter mediaTypeConverter;
	private GenreConverter genreConverter;
	@Override
	public String onCreate() throws Exception {
		try {
			this.dataObject = new Track();
			this.flagCreate = true;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "track-tx";
	}

	@Override
	public String onEdit() throws Exception {
		try {
			this.flagCreate = false;

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return "track-tx";
	}

	@Override
	public String persist() throws Exception {
		String message = this.validate();
		if (message == null) {
			try {
				if (dataObject != null && this.trackService.findByPk(dataObject.getTrackId()) != null) {
					this.trackService.update(dataObject);
					message = "message_successfully_updated";
				} else {
					this.trackService.save(dataObject);
					message = "message_successfully_created";
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = "message_error";
				FacesMessage facesMessage = MessageFactory.getMessage(message, "Track");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				return this.onCreate();
			}
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Track");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "track-tx";
		}
	}

	@Override
	public String delete() throws Exception {
		String message = "message_successfully_deleted";
		try {
			this.flagCreate = false;
			Integer pk = this.dataObject.getTrackId();
			this.trackService.deleteByPk(pk);
		} catch (Exception e) {
			e.printStackTrace();
			message = "message_error";
			FacesMessage facesMessage = MessageFactory.getMessage(message, "Track");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			throw new Exception(e);
		}

		FacesMessage facesMessage = MessageFactory.getMessage(message, "Track");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		if (this.getParentController() != null) {
			return getParentController().returnToParentController();
		} else {
			return "track-tx";
		}

	}

	@Override
	public String validate() throws Exception {
		String message = null;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Track>> constraintViolations = validator.validate(this.dataObject);

		for (ConstraintViolation<Track> constraintViolation : constraintViolations) {
			message = constraintViolation.getPropertyPath() + " -> " + constraintViolation.getMessage();
		}
		return message;
	}

	public List<Album> autocompleteAlbum(String query) {
		List<Album> suggestions = new ArrayList<Album>();
		try {
			for (Album album : albumService.findAll()) {

				String obj = String.valueOf(album.getTitle());

				if (obj.toLowerCase().contains(query.toLowerCase())) {
					suggestions.add(album);
				}
			}
		} catch (Exception e) {
			logger.error("Error executing album autocomplete " + e.getMessage());
		}
		return suggestions;
	}

	public List<MediaType> autocompleteMediaType(String query) {
		List<MediaType> suggestions = new ArrayList<MediaType>();
		try {
			for (MediaType mediaType : mediaTypeService.findAll()) {

				String obj = String.valueOf(mediaType.getName());

				if (obj.toLowerCase().contains(query.toLowerCase())) {
					suggestions.add(mediaType);
				}
			}
		} catch (Exception e) {
			logger.error("Error executing mediaType autocomplete " + e.getMessage());
		}
		return suggestions;
	}
	
	public List<Genre> autocompleteGenre(String query) {
		List<Genre> suggestions = new ArrayList<Genre>();
		try {
			for (Genre genre : genreService.findAll()) {

				String obj = String.valueOf(genre.getName());

				if (obj.toLowerCase().contains(query.toLowerCase())) {
					suggestions.add(genre);
				}
			}
		} catch (Exception e) {
			logger.error("Error executing genre autocomplete " + e.getMessage());
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
		Track track = this.dataObject;

		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.dataObject = new Track();
				return this.onCreate();
			}
			if (action.equalsIgnoreCase("SAVE")) {
				return this.persist();
			}
			if (action.equalsIgnoreCase("DELETE")) {
				return this.delete();
			}
		}

		// else if (value.equalsIgnoreCase("P5_Track")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// return this.trackControllerQry.onPaginate();
		// }
		// } else if (value.equalsIgnoreCase("P5_TEMPLE")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// this.templeControllerQry.clearMapParamereters();
		// this.templeControllerQry.addToMapParamereters(track.geTracksa(),
		// "empresa");
		// this.templeControllerQry.addToMapParamereters(track.getPais(),
		// "pais");
		// return this.templeControllerQry.onPaginate();
		// }
		// }
		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Track");
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
			this.actionsButtonsComponent = super.getActionsButtonsComponent(TrackControllerQry.class.getSimpleName(), this.getClass().getSimpleName());

			// CREATE
			CommandButton createButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			createButton.setId("createTempleButtonId");
			createButton.setValue(MessageFactory.getStringMessage("i18n", "label_Create_new"));
			createButton.setUpdate(":txForm  :growlForm:growl");
			createButton.setImmediate(true);
			createButton.setAjax(false);
			createButton.setIcon("ui-icon-plus");
			createButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.trackId!=null}  ", boolean.class));
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
			deleteCommandButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.trackId!=null}  ", boolean.class));
			deleteCommandButton.setOncomplete("deleteDialogWidget.show()");
			this.actionsButtonsComponent.getChildren().add(deleteCommandButton);

			// LIST ALL Track
			// CommandButton listTrackButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listTrackButton.setId("listTrackButtonId");
			// listTrackButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_Track_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listTrackButton.setUpdate(":growlForm:growl");
			// listTrackButton.setImmediate(true);
			// listTrackButton.setAjax(false);
			// listTrackButton.setIcon("ui-icon-document");
			// listTrackButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_Track','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listTrackButton);

			// LIST TEMPLE BY EMPRESA
			// CommandButton listTempleByTrackButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listTempleByTrackButton.setId("listTempleByTrackButtonId");
			// listTempleByTrackButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_TEMPLE_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listTempleByTrackButton.setUpdate(":growlForm:growl");
			// listTempleByTrackButton.setImmediate(true);
			// listTempleByTrackButton.setAjax(false);
			// listTempleByTrackButton.setIcon("ui-icon-document");
			// listTempleByTrackButton.setValueExpression("rendered",
			// expressionFactory.createValueExpression(elContext, "#{" +
			// this.getClass().getSimpleName() + ".dataObject.pkObject!=null}",
			// boolean.class));
			// listTempleByTrackButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_TEMPLE','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listTempleByTrackButton);
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
	public Track getDataObject() {
		if (dataObject == null) {
			dataObject = new Track();
		}
		return dataObject;
	}

	public void setDataObject(Track dataObject) {
		this.dataObject = dataObject;
	}

	public AlbumConverter getAlbumConverter() {
		if (this.albumConverter == null) {
			this.albumConverter = new AlbumConverter(this.albumService);
		}
		return albumConverter;
	}

	public void setAlbumConverter(AlbumConverter albumConverter) {
		this.albumConverter = albumConverter;
	}
	
	
	public MediaTypeConverter getMediaTypeConverter() {
		if (this.mediaTypeConverter == null) {
			this.mediaTypeConverter = new MediaTypeConverter(this.mediaTypeService);
		}
		return mediaTypeConverter;
	}

	public void setMediaTypeConverter(MediaTypeConverter mediaTypeConverter) {
		this.mediaTypeConverter = mediaTypeConverter;
	}
	
	public GenreConverter getGenreConverter() {
		if (this.genreConverter == null) {
			this.genreConverter = new GenreConverter(this.genreService);
		}
		return genreConverter;
	}

	public void setGenreConverter(GenreConverter genreConverter) {
		this.genreConverter = genreConverter;
	}

}