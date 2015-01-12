package com.sample.vo;

import java.io.Serializable;
import java.util.List;

public class ArtistVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int artistId;

	private String name;

	private List<AlbumVO> albums;

	public ArtistVO() {
	}

	public int getArtistId() {
		return this.artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AlbumVO> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<AlbumVO> albums) {
		this.albums = albums;
	}

	public AlbumVO addAlbum(AlbumVO album) {
		getAlbums().add(album);
		album.setArtist(this);

		return album;
	}

	public AlbumVO removeAlbum(AlbumVO album) {
		getAlbums().remove(album);
		album.setArtist(null);

		return album;
	}

}