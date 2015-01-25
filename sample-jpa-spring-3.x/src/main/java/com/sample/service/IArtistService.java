package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.model.entity.ArtistEntity;

public interface IArtistService {

	public long countAll() throws Exception;

	public ArtistEntity save(ArtistEntity entity) throws Exception;

	public ArtistEntity update(ArtistEntity entity) throws Exception;

	public void delete(ArtistEntity entity) throws Exception;

	public void deleteByPk(Integer pk) throws Exception;

	public ArtistEntity findByPk(Integer pk) throws Exception;

	public List<ArtistEntity> findEntries(int firstResult, int maxResults) throws Exception;

	public List<ArtistEntity> findAll() throws Exception;

	public List<ArtistEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}
