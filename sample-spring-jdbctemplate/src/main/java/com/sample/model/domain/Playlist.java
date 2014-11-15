package com.sample.model.domain;

import java.util.Set;

public class Playlist {

	private Integer playlistId;

	private String name;

	private Set<Track> tracks;

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

	public Integer getPlaylistId() {
		return this.playlistId;
	}

	public void setPlaylistId(Integer id) {
		this.playlistId = id;
	}

}
