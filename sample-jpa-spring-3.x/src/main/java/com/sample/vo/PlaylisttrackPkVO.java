package com.sample.vo;

import java.io.Serializable;

public class PlaylisttrackPkVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int playlistId;

	private int trackId;

	public PlaylisttrackPkVO() {
	}

	public int getPlaylistId() {
		return this.playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlaylisttrackPkVO)) {
			return false;
		}
		PlaylisttrackPkVO castOther = (PlaylisttrackPkVO) other;
		return (this.playlistId == castOther.playlistId) && (this.trackId == castOther.trackId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.playlistId;
		hash = hash * prime + this.trackId;

		return hash;
	}
}