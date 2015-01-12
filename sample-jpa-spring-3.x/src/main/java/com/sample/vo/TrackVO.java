package com.sample.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TrackVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int trackId;

	private int albumId;

	private int bytes;

	private String composer;

	private int milliseconds;

	private String name;

	private BigDecimal unitPrice;

	private GenreVO genre;

	private MediaTypeVO mediaType;

	public TrackVO() {
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getBytes() {
		return this.bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public String getComposer() {
		return this.composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return this.milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public GenreVO getGenre() {
		return this.genre;
	}

	public void setGenre(GenreVO genre) {
		this.genre = genre;
	}

	public MediaTypeVO getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaTypeVO mediaType) {
		this.mediaType = mediaType;
	}

}