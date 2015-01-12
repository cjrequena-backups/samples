package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * The primary key class for the playlisttrack database table.
 * 
 */
@Configurable
@Embeddable
public class PlaylisttrackEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int playlistId;

	private int trackId;

	public PlaylisttrackEntityPK() {
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
		if (!(other instanceof PlaylisttrackEntityPK)) {
			return false;
		}
		PlaylisttrackEntityPK castOther = (PlaylisttrackEntityPK)other;
		return 
			(this.playlistId == castOther.playlistId)
			&& (this.trackId == castOther.trackId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.playlistId;
		hash = hash * prime + this.trackId;
		
		return hash;
	}
}