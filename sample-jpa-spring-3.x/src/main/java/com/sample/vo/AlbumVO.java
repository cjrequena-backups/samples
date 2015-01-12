package com.sample.vo;

import java.io.Serializable;

public class AlbumVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int albumId;

	private String title;

	private ArtistVO artist;

	public AlbumVO() {
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

	public ArtistVO getArtist() {
		return this.artist;
	}

	public void setArtist(ArtistVO artist) {
		this.artist = artist;
	}

}