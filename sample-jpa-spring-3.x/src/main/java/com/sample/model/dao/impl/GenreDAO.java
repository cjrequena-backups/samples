package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IGenreDAO;
import com.sample.model.jpa.Genre;

@Component("genreDAO")
public class GenreDAO extends AbstractDAO<Genre, Integer> implements IGenreDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(GenreDAO.class);

	protected GenreDAO() {
		super(Genre.class);
	}

	protected GenreDAO(Class<Genre> targetClass) {
		super(targetClass);
	}

}