/*
 *
 */
package com.sample.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * 
 * @author cjrequena
 *
 * @param <S>
 * @param <D>
 */
public class GenericMapper<S, D> extends AbstractMapper {

	private ModelMapper modelMapper;

	final Class<S> sourceClass;

	final Class<D> destinationClass;

	/**
	 * Constructor.
	 */
	public GenericMapper(Class<S> sourceClass, Class<D> destinationClass) {
		this.sourceClass = sourceClass;
		this.destinationClass = destinationClass;
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public D mapSourceToDestination(S source) {
		if (source == null) {
			return null;
		}
		// --- Generic mapping
		D destination = map(source, destinationClass);

		return destination;
	}

	public void mapDestinationToSource(S source, D destination) {
		if (destination == null) {
			return;
		}
		// --- Generic mapping
		map(destination, source);
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