package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IGenreDAO;
import com.sample.model.entity.GenreEntity;

@Component("genreDAO")
public class GenreDAO extends AbstractDAO<GenreEntity, Integer> implements IGenreDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(GenreDAO.class);

	protected GenreDAO() {
		super(GenreEntity.class);
	}

	protected GenreDAO(Class<GenreEntity> targetClass) {
		super(targetClass);
	}

}