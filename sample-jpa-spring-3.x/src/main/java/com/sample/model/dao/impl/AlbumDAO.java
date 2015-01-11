package com.sample.model.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.sample.architecture.dao.AbstractDAO;
import com.sample.model.dao.IAlbumDAO;
import com.sample.model.entity.AlbumEntity;

@Component("albumDAO")
public class AlbumDAO extends AbstractDAO<AlbumEntity, Integer> implements IAlbumDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AlbumDAO() {
		super(AlbumEntity.class);
	}

	protected AlbumDAO(Class<AlbumEntity> targetClass) {
		super(targetClass);
	}

}
