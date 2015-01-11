package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IPlaylistDAO;
import com.sample.model.entity.PlaylistEntity;

@Component("playlistDAO")
public class PlaylistDAO extends AbstractDAO<PlaylistEntity, Integer> implements IPlaylistDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PlaylistDAO.class);

	protected PlaylistDAO() {
		super(PlaylistEntity.class);
	}

	protected PlaylistDAO(Class<PlaylistEntity> targetClass) {
		super(targetClass);
	}

}