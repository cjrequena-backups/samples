package com.sample.controller.converter;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.model.jpa.Artist;
import com.sample.service.IArtistService;

@FacesConverter("com.samplecontroller.converter.ArtistConverter")
@RequestScoped
public class ArtistConverter implements Converter, Serializable {

	/**
	 * 
	 * 
	 *
	 **/
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ArtistConverter.class);

	private IArtistService artistService;

	public ArtistConverter(IArtistService artistService) {
		this.artistService = artistService;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) throws ConverterException {
		if (value == null || value.length() == 0) {
			return null;
		}

		try {
			return artistService.findByPk(Integer.valueOf(value));
		} catch (Exception e) {
			logger.error("Error getting object from artist converter");
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) throws ConverterException {
		String valueReturn = "";
		if (value instanceof Artist) {
			try {
				valueReturn = ((Artist) value).getArtistId().toString();
			} catch (Exception e) {

			}
		}
		return valueReturn;

	}

}
