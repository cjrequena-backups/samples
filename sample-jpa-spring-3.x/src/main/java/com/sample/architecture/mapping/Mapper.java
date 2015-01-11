/*
 *
 */
package com.sample.architecture.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Mapping between entity beans and display beans VOs.
 */
public class Mapper<E, VO> extends AbstractMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;

	final Class<E> entityClass;

	final Class<VO> voClass;

	/**
	 * Constructor.
	 */
	public Mapper(Class<E> entityClass, Class<VO> voClass) {
		this.entityClass = entityClass;
		this.voClass = voClass;
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'AuthorEntity' to 'Author'
	 * 
	 * @param authorEntity
	 */
	public VO mapEntityToVO(E entity) {
		if (entity == null) {
			return null;
		}
		// --- Generic mapping
		VO vo = map(entity, voClass);

		return vo;
	}

	/**
	 * Mapping from 'Author' to 'AuthorEntity'
	 * 
	 * @param vo
	 * @param entity
	 */

	public void mapVOToEntity(VO vo, E entity) {
		if (vo == null) {
			return;
		}
		// --- Generic mapping
		map(vo, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}