package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;


/**
 * The persistent class for the Playlist database table.
 * 
 */
@Configurable
@Entity
@Table(name="Playlist")
@NamedQuery(name="PlaylistEntity.findAll", query="SELECT p FROM PlaylistEntity p")
public class PlaylistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int playlistId;

	private String name;

	//bi-directional many-to-one association to PlaylisttrackEntity
	@OneToMany(mappedBy="playlist")
	private List<PlaylisttrackEntity> playlisttracks;

	public PlaylistEntity() {
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

	public List<PlaylisttrackEntity> getPlaylisttracks() {
		return this.playlisttracks;
	}

	public void setPlaylisttracks(List<PlaylisttrackEntity> playlisttracks) {
		this.playlisttracks = playlisttracks;
	}

	public PlaylisttrackEntity addPlaylisttrack(PlaylisttrackEntity playlisttrack) {
		getPlaylisttracks().add(playlisttrack);
		playlisttrack.setPlaylist(this);

		return playlisttrack;
	}

	public PlaylisttrackEntity removePlaylisttrack(PlaylisttrackEntity playlisttrack) {
		getPlaylisttracks().remove(playlisttrack);
		playlisttrack.setPlaylist(null);

		return playlisttrack;
	}

}