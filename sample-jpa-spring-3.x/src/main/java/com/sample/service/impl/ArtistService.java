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
import com.sample.architecture.mapping.Mapper;
import com.sample.model.dao.IArtistDAO;
import com.sample.model.entity.ArtistEntity;
import com.sample.service.IArtistService;
import com.sample.vo.ArtistVO;

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
	
	private Mapper<ArtistEntity, ArtistVO> mapper = new Mapper<ArtistEntity, ArtistVO>(ArtistEntity.class, ArtistVO.class);


	@Autowired
	private IArtistDAO artistDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countAll() throws Exception {
		return this.artistDAO.countAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ArtistVO save(ArtistVO vo) throws Exception {
		ArtistEntity entity = this.artistDAO.findByPk(vo.getArtistId());
		if (entity != null) {
			logger.info("already.exists");
			throw new IllegalStateException("already.exists");
		}
		entity = new ArtistEntity();
		mapper.mapVOToEntity(vo, entity);
		ArtistEntity entitySaved = this.artistDAO.merge(entity);
		return mapper.mapEntityToVO(entitySaved);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ArtistVO update(ArtistVO vo) throws Exception {
		ArtistEntity entity = this.artistDAO.findByPk(vo.getArtistId());
		if (entity == null) {
			logger.info("does.not.exists");
			throw new IllegalStateException("does.not.exists");
		}
		entity = new ArtistEntity();
		mapper.mapVOToEntity(vo, entity);
		ArtistEntity entityUpdated = this.artistDAO.merge(entity);
		return mapper.mapEntityToVO(entityUpdated);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(ArtistVO vo) throws Exception {
		ArtistEntity entity = new ArtistEntity();
		this.mapper.mapVOToEntity(vo, entity);
		this.artistDAO.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByPk(Integer pk) throws Exception {
		this.artistDAO.deleteByPk(pk);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ArtistVO findByPk(Integer pk) throws Exception {
		ArtistEntity entity = this.artistDAO.findByPk(pk);
		ArtistVO vo = this.mapper.mapEntityToVO(entity);
		return vo;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistVO> findEntries(int firstResult, int maxResults) throws Exception {
		Iterable<ArtistEntity> entities = this.artistDAO.findEntries(firstResult, maxResults);
		List<ArtistVO> vos = new ArrayList<ArtistVO>();
		for (ArtistEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistVO> findAll() throws Exception {
		Iterable<ArtistEntity> entities = this.artistDAO.findAll();
		List<ArtistVO> vos = new ArrayList<ArtistVO>();
		for (ArtistEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ArtistVO> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		List<ArtistEntity> entities = this.artistDAO.executeQueryFilter(filters, firstResult, maxResult);
		List<ArtistVO> vos = new ArrayList<ArtistVO>();
		for (ArtistEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	

}
