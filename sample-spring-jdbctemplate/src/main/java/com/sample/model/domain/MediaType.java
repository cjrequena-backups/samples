package com.sample.model.domain;

import java.util.Set;


public class MediaType {

	
	private Integer mediaTypeId;

	
	private String name;

	private Set<Track> tracks;

	public Integer getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(Integer id) {
		this.mediaTypeId = id;
	}

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


}
