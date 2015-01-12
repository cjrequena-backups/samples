package com.sample.vo;

import java.io.Serializable;
import java.util.List;

public class PlaylistVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int playlistId;

	private String name;

	private List<PlaylisttrackVO> playlisttracks;

	public PlaylistVO() {
	}

	public int getPlaylistId() {
		return this.playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlaylisttrackVO> getPlaylisttracks() {
		return this.playlisttracks;
	}

	public void setPlaylisttracks(List<PlaylisttrackVO> playlisttracks) {
		this.playlisttracks = playlisttracks;
	}

	public PlaylisttrackVO addPlaylisttrack(PlaylisttrackVO playlisttrack) {
		getPlaylisttracks().add(playlisttrack);
		playlisttrack.setPlaylist(this);

		return playlisttrack;
	}

	public PlaylisttrackVO removePlaylisttrack(PlaylisttrackVO playlisttrack) {
		getPlaylisttracks().remove(playlisttrack);
		playlisttrack.setPlaylist(null);

		return playlisttrack;
	}

}