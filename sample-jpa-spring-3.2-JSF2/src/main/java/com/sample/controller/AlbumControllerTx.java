package com.sample.controller;

import java.io.Serializable;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
import com.sample.model.jpa.Album;
import com.sample.service.IAlbumService;

@Named("AlbumControllerTx")
@Scope("session")
public class AlbumControllerTx extends AbstractControllerTx<Album> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(AlbumControllerTx.class);

	// SERVICES
	@Autowired
	private IAlbumService albumService;

//	@Inject
//	AlbumControllerQry albumControllerQry;

	// COMPONENTS
	private HtmlPanelGrid actionsButtonsComponent;

	// COMVERTERS
	// private CountryConverter countryConverter;

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

		return "album-tx";

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

		return "album-tx";

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
		Album album = this.dataObject;
		if (value.equalsIgnoreCase("COMMONS_ACTIONS")) {
			if (action.equalsIgnoreCase("CREATE")) {
				this.dataObject = new Album();
				return this.onCreate();
			}
			if (action.equalsIgnoreCase("SAVE")) {
				return this.persist();
			}
			if (action.equalsIgnoreCase("DELETE")) {
				return this.delete();
			}
		}

		// else if (value.equalsIgnoreCase("P5_Album")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// return this.albumControllerQry.onPaginate();
		// }
		// } else if (value.equalsIgnoreCase("P5_TEMPLE")) {
		// if (action.equalsIgnoreCase("LIST")) {
		// this.templeControllerQry.clearMapParamereters();
		// this.templeControllerQry.addToMapParamereters(album.geAlbumsa(),
		// "empresa");
		// this.templeControllerQry.addToMapParamereters(album.getPais(),
		// "pais");
		// return this.templeControllerQry.onPaginate();
		// }
		// }
		FacesMessage facesMessage = MessageFactory.getMessage("message_error", "Album");
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
			this.actionsButtonsComponent = super.getActionsButtonsComponent(AlbumControllerQry.class.getSimpleName(), this.getClass().getSimpleName());

			// CREATE
			CommandButton createButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			createButton.setId("createTempleButtonId");
			createButton.setValue(MessageFactory.getStringMessage("i18n", "label_create_new"));
			createButton.setUpdate(":txForm  :growlForm:growl");
			createButton.setImmediate(true);
			createButton.setAjax(false);
			createButton.setIcon("ui-icon-plus");
			createButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.albumId!=null}  ", boolean.class));
			createButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','CREATE')}", String.class, new Class[] { String.class, String.class }));
			this.actionsButtonsComponent.getChildren().add(createButton);

			// SAVE
			CommandButton saveCommandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			saveCommandButton.setId("saveCommandButtonId");
			saveCommandButton.setValue(MessageFactory.getStringMessage("i18n", "label_save"));
			saveCommandButton.setUpdate(":txForm :growlForm:growl");
			saveCommandButton.setImmediate(false);
			saveCommandButton.setAjax(false);
			saveCommandButton.setIcon("ui-icon-disk");
			saveCommandButton.setActionExpression(expressionFactory.createMethodExpression(elContext, "#{" + this.getClass().getSimpleName() + ".runFromActionsButtons('COMMONS_ACTIONS','SAVE')}", String.class, new Class[] { String.class, String.class }));
			this.actionsButtonsComponent.getChildren().add(saveCommandButton);

			// DELETE
			CommandButton deleteCommandButton = (CommandButton) application.createComponent(CommandButton.COMPONENT_TYPE);
			deleteCommandButton.setId("deleteCommandButtonId");
			deleteCommandButton.setValue(MessageFactory.getStringMessage("i18n", "label_delete"));
			deleteCommandButton.setUpdate(":txForm :growlForm:growl");
			deleteCommandButton.setImmediate(true);
			deleteCommandButton.setAjax(true);
			deleteCommandButton.setIcon("ui-icon-disk");
			deleteCommandButton.setValueExpression("rendered", expressionFactory.createValueExpression(elContext, "#{" + this.getClass().getSimpleName() + ".dataObject!=null && " + this.getClass().getSimpleName() + ".dataObject.albumId!=null}  ", boolean.class));
			deleteCommandButton.setOncomplete("deleteDialogWidget.show()");
			this.actionsButtonsComponent.getChildren().add(deleteCommandButton);

			// LIST ALL Album
			// CommandButton listAlbumButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listAlbumButton.setId("listAlbumButtonId");
			// listAlbumButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_Album_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listAlbumButton.setUpdate(":growlForm:growl");
			// listAlbumButton.setImmediate(true);
			// listAlbumButton.setAjax(false);
			// listAlbumButton.setIcon("ui-icon-document");
			// listAlbumButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_Album','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listAlbumButton);

			// LIST TEMPLE BY EMPRESA
			// CommandButton listTempleByAlbumButton = (CommandButton)
			// application.createComponent(CommandButton.COMPONENT_TYPE);
			// listTempleByAlbumButton.setId("listTempleByAlbumButtonId");
			// listTempleByAlbumButton.setValue(MessageFactory.getStringMessage("messages",
			// "P5_TEMPLE_PROCESSTRANSACTION_DISPLAY_LABEL"));
			// listTempleByAlbumButton.setUpdate(":growlForm:growl");
			// listTempleByAlbumButton.setImmediate(true);
			// listTempleByAlbumButton.setAjax(false);
			// listTempleByAlbumButton.setIcon("ui-icon-document");
			// listTempleByAlbumButton.setValueExpression("rendered",
			// expressionFactory.createValueExpression(elContext, "#{" +
			// this.getClass().getSimpleName() + ".dataObject.pkObject!=null}",
			// boolean.class));
			// listTempleByAlbumButton.setActionExpression(expressionFactory.createMethodExpression(elContext,
			// "#{" + this.getClass().getSimpleName() +
			// ".runFromActionsButtons('P5_TEMPLE','LIST')}", String.class, new
			// Class[] {
			// String.class, String.class }));
			// htmlPanelGrid.getChildren().add(listTempleByAlbumButton);
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

}