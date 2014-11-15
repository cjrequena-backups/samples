package com.sample.model.domain;

import java.math.BigDecimal;
import java.util.Set;

public class Track {

	private Integer trackId;

	private String name;

	private String composer;

	private Integer milliseconds;

	private Integer bytes;

	private BigDecimal unitPrice;

	private Set<Playlist> playlists;

	private Set<InvoiceLine> invoiceLines;

	private MediaType mediaTypeId;

	private Album albumId;

	private Genre genreId;

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public MediaType getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(MediaType mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public Album getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Album albumId) {
		this.albumId = albumId;
	}

	public Genre getGenreId() {
		return genreId;
	}

	public void setGenreId(Genre genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Integer getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(Integer milliseconds) {
		this.milliseconds = milliseconds;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getTrackId() {
		return this.trackId;
	}

	public void setTrackId(Integer id) {
		this.trackId = id;
	}

}
