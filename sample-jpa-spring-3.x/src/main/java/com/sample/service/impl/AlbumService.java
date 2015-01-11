package com.sample.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.architecture.exceptions.ECodeExceptions;
import com.sample.model.dao.IAlbumDAO;
import com.sample.model.entity.AlbumEntity;
import com.sample.service.IAlbumService;

@Service
@Transactional
public class AlbumService implements IAlbumService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);

	@Inject
	private IAlbumDAO albumDAO;

	@Override
	@Transactional
	public long countAll() throws BusinessExceptions {
		try {
			return this.albumDAO.countAll();
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void save(AlbumEntity object) throws BusinessExceptions {
		try {
			this.albumDAO.persist(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void update(AlbumEntity object) throws BusinessExceptions {
		try {
			this.albumDAO.merge(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void delete(AlbumEntity object) throws BusinessExceptions {
		try {
			this.albumDAO.delete(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public void deleteByPk(Integer pk) throws BusinessExceptions {
		try {
			this.albumDAO.deleteByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public AlbumEntity findByPk(Integer pk) throws BusinessExceptions {
		try {
			return this.albumDAO.findByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<AlbumEntity> findEntries(int firstResult, int maxResults) throws BusinessExceptions {
		try {
			return new ArrayList<AlbumEntity>(this.albumDAO.findEntries(firstResult, maxResults));
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<AlbumEntity> findAll() throws BusinessExceptions {
		try {
			return new ArrayList<AlbumEntity>(this.albumDAO.findAll());
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<AlbumEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions {
		try {
			return this.albumDAO.executeQueryFilter(filters, firstResult, maxResult);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}
}
