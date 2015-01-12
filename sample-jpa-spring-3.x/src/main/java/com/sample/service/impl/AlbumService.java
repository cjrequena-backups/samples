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
import com.sample.model.dao.IAlbumDAO;
import com.sample.model.entity.AlbumEntity;
import com.sample.service.IAlbumService;
import com.sample.vo.AlbumVO;

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
	
	private Mapper<AlbumEntity, AlbumVO> mapper = new Mapper<AlbumEntity, AlbumVO>(AlbumEntity.class, AlbumVO.class);


	@Autowired
	private IAlbumDAO albumDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countAll() throws Exception {
		return this.albumDAO.countAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlbumVO save(AlbumVO vo) throws Exception {
		AlbumEntity entity = this.albumDAO.findByPk(vo.getAlbumId());
		if (entity != null) {
			logger.info("already.exists");
			throw new IllegalStateException("already.exists");
		}
		entity = new AlbumEntity();
		mapper.mapVOToEntity(vo, entity);
		AlbumEntity entitySaved = this.albumDAO.merge(entity);
		return mapper.mapEntityToVO(entitySaved);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlbumVO update(AlbumVO vo) throws Exception {
		AlbumEntity entity = this.albumDAO.findByPk(vo.getAlbumId());
		if (entity == null) {
			logger.info("does.not.exists");
			throw new IllegalStateException("does.not.exists");
		}
		entity = new AlbumEntity();
		mapper.mapVOToEntity(vo, entity);
		AlbumEntity entityUpdated = this.albumDAO.merge(entity);
		return mapper.mapEntityToVO(entityUpdated);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(AlbumVO vo) throws Exception {
		AlbumEntity entity = new AlbumEntity();
		this.mapper.mapVOToEntity(vo, entity);
		this.albumDAO.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByPk(Integer pk) throws Exception {
		this.albumDAO.deleteByPk(pk);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public AlbumVO findByPk(Integer pk) throws Exception {
		AlbumEntity entity = this.albumDAO.findByPk(pk);
		AlbumVO vo = this.mapper.mapEntityToVO(entity);
		return vo;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumVO> findEntries(int firstResult, int maxResults) throws Exception {
		Iterable<AlbumEntity> entities = this.albumDAO.findEntries(firstResult, maxResults);
		List<AlbumVO> vos = new ArrayList<AlbumVO>();
		for (AlbumEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumVO> findAll() throws Exception {
		Iterable<AlbumEntity> entities = this.albumDAO.findAll();
		List<AlbumVO> vos = new ArrayList<AlbumVO>();
		for (AlbumEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<AlbumVO> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception {
		List<AlbumEntity> entities = this.albumDAO.executeQueryFilter(filters, firstResult, maxResult);
		List<AlbumVO> vos = new ArrayList<AlbumVO>();
		for (AlbumEntity entity : entities) {
			vos.add(mapper.mapEntityToVO(entity));
		}
		return vos;
	}

	

}
