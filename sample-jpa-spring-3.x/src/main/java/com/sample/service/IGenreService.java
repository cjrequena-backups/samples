package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.entity.GenreEntity;

public interface IGenreService {
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
	public abstract void save(GenreEntity object) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void update(GenreEntity obj) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void delete(GenreEntity obj) throws BusinessExceptions;

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
	public abstract GenreEntity findByPk(Integer pk) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<GenreEntity> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract List<GenreEntity> findAll() throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<GenreEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;
}
