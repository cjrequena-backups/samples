package com.sample.model.dao.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.ITrackDAO;
import com.sample.model.entity.TrackEntity;

@Component("trackDAO")
public class TrackDAO extends AbstractDAO<TrackEntity, Integer> implements ITrackDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TrackDAO.class);

	protected TrackDAO() {
		super(TrackEntity.class);
	}

	protected TrackDAO(Class<TrackEntity> targetClass) {
		super(targetClass);
	}

}