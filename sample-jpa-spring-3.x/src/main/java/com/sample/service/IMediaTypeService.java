package com.sample.service;

import java.util.List;

import com.sample.architecture.dao.Filter;
import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.entity.MediaTypeEntity;

public interface IMediaTypeService {
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
	public abstract void save(MediaTypeEntity object) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void update(MediaTypeEntity obj) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract void delete(MediaTypeEntity obj) throws BusinessExceptions;

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
	public abstract MediaTypeEntity findByPk(Integer pk) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<MediaTypeEntity> findEntries(int firstResult, int maxResults) throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public abstract List<MediaTypeEntity> findAll() throws BusinessExceptions;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 **/
	public List<MediaTypeEntity> executeQueryFilter(List<Filter> filters, int firstResult, int maxResult) throws BusinessExceptions;
}
