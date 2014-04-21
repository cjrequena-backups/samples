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

import com.sample.model.jpa.Playlist;
import com.sample.service.IPlaylistService;

@FacesConverter("com.samplecontroller.converter.PlaylistConverter")
@RequestScoped
public class PlaylistConverter implements Converter, Serializable {

	/**
	 * 
	 * 
	 *
	 **/
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PlaylistConverter.class);

	private IPlaylistService playlistService;

	public PlaylistConverter(IPlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) throws ConverterException {
		if (value == null || value.length() == 0) {
			return null;
		}

		try {
			return playlistService.findByPk(Integer.valueOf(value));
		} catch (Exception e) {
			logger.error("Error getting object from playlist converter");
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) throws ConverterException {
		String valueReturn = "";
		if (value instanceof Playlist) {
			try {
				valueReturn = ((Playlist) value).getPlaylistId().toString();
			} catch (Exception e) {

			}
		}
		return valueReturn;

	}

}
