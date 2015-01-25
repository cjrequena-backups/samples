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
import com.sample.model.dao.IArtistDAO;
import com.sample.model.entity.ArtistEntity;
import com.sample.service.IArtistService;

/**
 * 
 * @author cjrequena
 *
 */
@Service("artistService")
@Transactional
public class ArtistService implements IArtistService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);
	


	@Autowired
	private IArtistDAO artistDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countAll() throws Exception {
		return this.artistDAO.countAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ArtistEntity save(ArtistEntity entity) throws Exception {
		ArtistEntity entityAux = this.artistDAO.findByPk(entity.getArtistId());
		if (entityAux != null) {
			logger.info("already.exists");
			throw new IllegalStateException("already.exists");
		}
		
		ArtistEntity entitySaved = this.artistDAO.merge(entity);
		return entitySaved;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ArtistEntity update(ArtistEntity entity) throws Exception {
		ArtistEntity entityAux = this.artistDAO.findByPk(entity.getArtistId());
		if (entityAux == null) {
			logger.info("does.not.exists");
			throw new IllegalStateException("does.not.exists");
		}
		ArtistEntity entityUpdated = this.artistDAO.merge(entity);
		return entityUpdated;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(ArtistEntity entity) throws Exception {
		this.artistDAO.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByPk(Integer pk) throws Exception {
		this.artistDAO.deleteByPk(pk);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ArtistEntity findByPk(Integer pk) throws Exception {
		ArtistEntity entity = this.artistDAO.findByPk(pk);
		return entity;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistEntity> findEntries(int firstResult, int maxResults) throws Exception {
		List<ArtistEntity> entities = new ArrayList<ArtistEntity>(this.artistDAO.findEntries(firstResult, maxResults));
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistEntity> findAll() throws Exception {
		List<ArtistEntity> entities = new ArrayList<ArtistEntity>(this.artistDAO.findAll());
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		List<ArtistEntity> entities = this.artistDAO.executeQueryFilter(filters, firstResult, maxResult);
		return entities;
	}
}
