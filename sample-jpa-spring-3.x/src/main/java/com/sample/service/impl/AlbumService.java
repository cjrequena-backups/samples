package com.sample.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sample.architecture.dao.Filter;
import com.sample.model.dao.IAlbumDAO;
import com.sample.model.entity.AlbumEntity;
import com.sample.service.IAlbumService;

/**
 * 
 * @author cjrequena
 *
 */
@Service("albumService")
@Transactional
public class AlbumService implements IAlbumService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);
	


	@Autowired
	private IAlbumDAO albumDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countAll() throws Exception {
		return this.albumDAO.countAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlbumEntity save(AlbumEntity entity) throws Exception {
		AlbumEntity entityAux = this.albumDAO.findByPk(entity.getAlbumId());
		if (entityAux != null) {
			logger.info("already.exists");
			throw new IllegalStateException("already.exists");
		}
		
		AlbumEntity entitySaved = this.albumDAO.merge(entity);
		return entitySaved;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlbumEntity update(AlbumEntity entity) throws Exception {
		AlbumEntity entityAux = this.albumDAO.findByPk(entity.getAlbumId());
		if (entityAux == null) {
			logger.info("does.not.exists");
			throw new IllegalStateException("does.not.exists");
		}
		AlbumEntity entityUpdated = this.albumDAO.merge(entity);
		return entityUpdated;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(AlbumEntity entity) throws Exception {
		this.albumDAO.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByPk(Integer pk) throws Exception {
		this.albumDAO.deleteByPk(pk);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public AlbumEntity findByPk(Integer pk) throws Exception {
		AlbumEntity entity = this.albumDAO.findByPk(pk);
		return entity;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumEntity> findEntries(int firstResult, int maxResults) throws Exception {
		List<AlbumEntity> entities = new ArrayList<AlbumEntity>(this.albumDAO.findEntries(firstResult, maxResults));
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumEntity> findAll() throws Exception {
		List<AlbumEntity> entities = new ArrayList<AlbumEntity>(this.albumDAO.findAll());
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		List<AlbumEntity> entities = this.albumDAO.executeQueryFilter(filters, firstResult, maxResult);
		return entities;
	}
}
