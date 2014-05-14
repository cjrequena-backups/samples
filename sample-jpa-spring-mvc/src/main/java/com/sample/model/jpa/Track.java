package com.sample.model.jpa;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Table(name = "Track")
@Configurable
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TrackId")
	private Integer trackId;

	@Column(name = "Name", length = 200)
	@NotNull
	private String name;

	@Column(name = "Composer", length = 220)
	private String composer;

	@Column(name = "Milliseconds")
	@NotNull
	private Integer milliseconds;

	@Column(name = "Bytes")
	private Integer bytes;

	@Column(name = "UnitPrice", precision = 10, scale = 2)
	@NotNull
	private BigDecimal unitPrice;

	@ManyToMany
	@JoinTable(name = "PlaylistTrack", joinColumns = { @JoinColumn(name = "TrackId", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "PlaylistId", nullable = false) })
	private Set<Playlist> playlists;

	@OneToMany(mappedBy = "trackId")
	private Set<InvoiceLine> invoiceLines;

	@ManyToOne
	@JoinColumn(name = "MediaTypeId", referencedColumnName = "MediaTypeId", nullable = false)
	private MediaType mediaTypeId;

	@ManyToOne
	@JoinColumn(name = "AlbumId", referencedColumnName = "AlbumId")
	private Album albumId;

	@ManyToOne
	@JoinColumn(name = "GenreId", referencedColumnName = "GenreId")
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

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
