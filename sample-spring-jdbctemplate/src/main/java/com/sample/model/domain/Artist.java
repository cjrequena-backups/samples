package com.sample.model.domain;

import java.io.Serializable;
import java.util.Set;

public class Artist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer artistId;

	private String name;

	private Set<Album> albums;

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArtistId() {
		return this.artistId;
	}

	public void setArtistId(Integer id) {
		this.artistId = id;
	}

}
