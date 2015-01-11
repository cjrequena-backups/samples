package com.sample.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AlbumId")
	private Integer albumId;

	@Column(name = "Title", length = 160)
	@NotNull
	private String title;

	@ManyToOne
	@JoinColumn(name = "ArtistId", referencedColumnName = "ArtistId", nullable = false)
	private ArtistEntity artistId;

	@OneToMany(mappedBy = "albumId")
	private Set<TrackEntity> tracks;

	public Set<TrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(Set<TrackEntity> tracks) {
		this.tracks = tracks;
	}

	public ArtistEntity getArtistId() {
		return artistId;
	}

	public void setArtistId(ArtistEntity artistId) {
		this.artistId = artistId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Integer id) {
		this.albumId = id;
	}
}
