package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.entity.ArtistEntity;

public interface IArtistService {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract long countAll() throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void save(ArtistEntity object) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void update(ArtistEntity obj) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void delete(ArtistEntity obj) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public void deleteByPk(Integer pk) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract ArtistEntity findByPk(Integer pk) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<ArtistEntity> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract List<ArtistEntity> findAll() throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<ArtistEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;
}
