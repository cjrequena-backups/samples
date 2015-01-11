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
import com.sample.model.dao.ITrackDAO;
import com.sample.model.entity.TrackEntity;
import com.sample.service.ITrackService;

@Service
@Transactional
public class TrackService implements ITrackService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(TrackService.class);

	@Inject
	private ITrackDAO trackDAO;

	@Override
	@Transactional
	public long countAll() throws BusinessExceptions {
		try {
			return this.trackDAO.countAll();
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void save(TrackEntity object) throws BusinessExceptions {
		try {
			this.trackDAO.persist(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void update(TrackEntity object) throws BusinessExceptions {
		try {
			this.trackDAO.merge(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void delete(TrackEntity object) throws BusinessExceptions {
		try {
			this.trackDAO.delete(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public void deleteByPk(Integer pk) throws BusinessExceptions {
		try {
			this.trackDAO.deleteByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public TrackEntity findByPk(Integer pk) throws BusinessExceptions {
		try {
			return this.trackDAO.findByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<TrackEntity> findEntries(int firstResult, int maxResults) throws BusinessExceptions {
		try {
			return new ArrayList<TrackEntity>(this.trackDAO.findEntries(firstResult, maxResults));
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<TrackEntity> findAll() throws BusinessExceptions {
		try {
			return new ArrayList<TrackEntity>(this.trackDAO.findAll());
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<TrackEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions {
		try {
			return this.trackDAO.executeQueryFilter(filters, firstResult, maxResult);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}
}
