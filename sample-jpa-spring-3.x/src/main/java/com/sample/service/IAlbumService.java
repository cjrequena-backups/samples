package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.vo.AlbumVO;

public interface IAlbumService {

	public long countAll() throws Exception;

	public AlbumVO save(AlbumVO vo) throws Exception;

	public AlbumVO update(AlbumVO vo) throws Exception;

	public void delete(AlbumVO vo) throws Exception;

	public void deleteByPk(Integer pk) throws Exception;

	public AlbumVO findByPk(Integer pk) throws Exception;

	public List<AlbumVO> findEntries(int firstResult, int maxResults) throws Exception;

	public List<AlbumVO> findAll() throws Exception;

	public List<AlbumVO> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}
