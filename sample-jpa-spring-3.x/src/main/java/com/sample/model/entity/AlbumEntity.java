package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;


/**
 * The persistent class for the album database table.
 * 
 */
@Configurable
@Entity
@Table(name="album")
@NamedQuery(name="AlbumEntity.findAll", query="SELECT a FROM AlbumEntity a")
public class AlbumEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int albumId;

	private String title;

	//bi-directional many-to-one association to ArtistEntity
	@ManyToOne
	@JoinColumn(name="ArtistId")
	private ArtistEntity artist;

	public AlbumEntity() {
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArtistEntity getArtist() {
		return this.artist;
	}

	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}

}