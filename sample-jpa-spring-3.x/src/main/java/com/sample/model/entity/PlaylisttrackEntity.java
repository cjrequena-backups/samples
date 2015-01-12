package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;


/**
 * The persistent class for the playlisttrack database table.
 * 
 */
@Configurable
@Entity
@Table(name="playlisttrack")
@NamedQuery(name="PlaylisttrackEntity.findAll", query="SELECT p FROM PlaylisttrackEntity p")
public class PlaylisttrackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlaylisttrackEntityPK id;

	//bi-directional many-to-one association to PlaylistEntity
	@ManyToOne
	@JoinColumn(name="PlaylistId")
	private PlaylistEntity playlist;

	public PlaylisttrackEntity() {
	}

	public PlaylisttrackEntityPK getId() {
		return this.id;
	}

	public void setId(PlaylisttrackEntityPK id) {
		this.id = id;
	}

	public PlaylistEntity getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(PlaylistEntity playlist) {
		this.playlist = playlist;
	}

}