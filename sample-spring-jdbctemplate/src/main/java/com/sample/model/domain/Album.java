package com.sample.model.domain;

import java.io.Serializable;
import java.util.Set;

public class Album implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer albumId;

	private String title;

	private Artist artistId;

	private Set<Track> tracks;

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public Artist getArtistId() {
		return artistId;
	}

	public void setArtistId(Artist artistId) {
		this.artistId = artistId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Integer id) {
		this.albumId = id;
	}
}
