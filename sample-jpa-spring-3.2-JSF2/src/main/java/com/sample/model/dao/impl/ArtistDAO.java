package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IArtistDAO;
import com.sample.model.jpa.Artist;

@Component("artistDAO")
public class ArtistDAO extends AbstractDAO<Artist, Integer> implements IArtistDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ArtistDAO.class);

	protected ArtistDAO() {
		super(Artist.class);
	}

	protected ArtistDAO(Class<Artist> targetClass) {
		super(targetClass);
	}

}
