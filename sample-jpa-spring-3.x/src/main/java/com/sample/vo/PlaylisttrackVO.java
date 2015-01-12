package com.sample.vo;

import java.io.Serializable;



public class PlaylisttrackVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private PlaylisttrackPkVO id;

	
	private PlaylistVO playlist;

	public PlaylisttrackVO() {
	}

	public PlaylisttrackPkVO getId() {
		return this.id;
	}

	public void setId(PlaylisttrackPkVO id) {
		this.id = id;
	}

	public PlaylistVO getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(PlaylistVO playlist) {
		this.playlist = playlist;
	}

}