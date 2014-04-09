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
import com.sample.model.dao.IPlaylistDAO;
import com.sample.model.jpa.Playlist;
import com.sample.service.IPlaylistService;

@Service
@Transactional
public class PlaylistService implements IPlaylistService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

	@Inject
	private IPlaylistDAO playlistDAO;

	@Override
	@Transactional
	public long countAll() throws BusinessExceptions {
		try {
			return this.playlistDAO.countAll();
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void save(Playlist object) throws BusinessExceptions {
		try {
			this.playlistDAO.save(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void update(Playlist object) throws BusinessExceptions {
		try {
			this.playlistDAO.update(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public void delete(Playlist object) throws BusinessExceptions {
		try {
			this.playlistDAO.delete(object);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public void deleteByPk(Integer pk) throws BusinessExceptions {
		try {
			this.playlistDAO.deleteByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}

	}

	@Override
	@Transactional
	public Playlist findByPk(Integer pk) throws BusinessExceptions {
		try {
			return this.playlistDAO.findByPk(pk);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<Playlist> findEntries(int firstResult, int maxResults) throws BusinessExceptions {
		try {
			return new ArrayList<Playlist>(this.playlistDAO.findEntries(firstResult, maxResults));
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<Playlist> findAll() throws BusinessExceptions {
		try {
			return new ArrayList<Playlist>(this.playlistDAO.findAll());
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}

	@Override
	@Transactional
	public List<Playlist> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions {
		try {
			return this.playlistDAO.executeQueryFilter(filters, firstResult, maxResult);
		} catch (Exception e) {
			// TODO ADD CODE EXCEPTION
			throw new BusinessExceptions(ECodeExceptions.UNKNOWN_ERROR.getCode(), e);

		}
	}
}
