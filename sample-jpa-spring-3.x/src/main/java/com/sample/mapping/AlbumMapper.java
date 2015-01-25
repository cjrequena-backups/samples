package com.sample.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.sample.model.entity.AlbumEntity;
import com.sample.vo.AlbumVO;

/**
 * Mapping between entity beans and vo beans.
 */
@Component
public class AlbumMapper extends AbstractMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;

	/**
	 * Constructor.
	 */
	public AlbumMapper() {

	}

	/**
	 * Mapping from 'AlbumEntity' to 'AlbumVO'
	 * 
	 * @param albumEntity
	 */
	public AlbumVO mapAlbumEntityToAlbumVO(AlbumEntity albumEntity) {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		if (albumEntity == null) {
			return null;
		}
		AlbumVO albumVO = map(albumEntity, AlbumVO.class);
		return albumVO;
	}

	public AlbumVO mapAlbumEntityToAlbumVOSkipingAlbumId(AlbumEntity albumEntity) {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		this.modelMapper.addMappings(new PropertyMap<AlbumEntity, AlbumVO>() {
			@Override
			protected void configure() {
				skip().setAlbumId(0);
			}
		});
		if (albumEntity == null) {
			return null;
		}
		AlbumVO albumVO = map(albumEntity, AlbumVO.class);
		return albumVO;
	}

	/**
	 * Mapping from 'AlbumVO' to 'AlbumEntity'
	 * 
	 * @param albumVO
	 * @param albumEntity
	 */
	public void mapAlbumVOToAlbumEntity(AlbumVO albumVO, AlbumEntity albumEntity) {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		if (albumVO == null) {
			return;
		}

		// --- Generic mapping
		map(albumVO, albumEntity);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return this.modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}