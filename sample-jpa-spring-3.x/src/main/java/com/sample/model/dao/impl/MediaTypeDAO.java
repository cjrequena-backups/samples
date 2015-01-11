package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IMediaTypeDAO;
import com.sample.model.entity.MediaTypeEntity;

@Component("mediaTypeDAO")
public class MediaTypeDAO extends AbstractDAO<MediaTypeEntity, Integer> implements IMediaTypeDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(MediaTypeDAO.class);

	protected MediaTypeDAO() {
		super(MediaTypeEntity.class);
	}

	protected MediaTypeDAO(Class<MediaTypeEntity> targetClass) {
		super(targetClass);
	}

}