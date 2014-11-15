package com.sample.model.domain;

import java.util.Set;

public class Genre {

	private Integer genreId;

	private Set<Track> tracks;

	private String name;

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGenreId() {
		return this.genreId;
	}

	public void setGenreId(Integer id) {
		this.genreId = id;
	}

}
