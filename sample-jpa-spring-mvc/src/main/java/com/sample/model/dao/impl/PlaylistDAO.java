package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IPlaylistDAO;
import com.sample.model.jpa.Playlist;

@Component("playlistDAO")
public class PlaylistDAO extends AbstractDAO<Playlist, Integer> implements IPlaylistDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PlaylistDAO.class);

	protected PlaylistDAO() {
		super(Playlist.class);
	}

	protected PlaylistDAO(Class<Playlist> targetClass) {
		super(targetClass);
	}

}