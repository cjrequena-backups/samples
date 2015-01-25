package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.model.entity.AlbumEntity;

public interface IAlbumService {

	public long countAll() throws Exception;

	public AlbumEntity save(AlbumEntity entity) throws Exception;

	public AlbumEntity update(AlbumEntity entity) throws Exception;

	public void delete(AlbumEntity entity) throws Exception;

	public void deleteByPk(Integer pk) throws Exception;

	public AlbumEntity findByPk(Integer pk) throws Exception;

	public List<AlbumEntity> findEntries(int firstResult, int maxResults) throws Exception;

	public List<AlbumEntity> findAll() throws Exception;

	public List<AlbumEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}
