package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.vo.ArtistVO;

public interface IArtistService {

	public long countAll() throws Exception;

	public ArtistVO save(ArtistVO vo) throws Exception;

	public ArtistVO update(ArtistVO vo) throws Exception;

	public void delete(ArtistVO vo) throws Exception;

	public void deleteByPk(Integer pk) throws Exception;

	public ArtistVO findByPk(Integer pk) throws Exception;

	public List<ArtistVO> findEntries(int firstResult, int maxResults) throws Exception;

	public List<ArtistVO> findAll() throws Exception;

	public List<ArtistVO> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws Exception;

}
