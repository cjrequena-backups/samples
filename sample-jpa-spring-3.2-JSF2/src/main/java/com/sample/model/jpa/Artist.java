package com.sample.model.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name = "Artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ArtistId")
	private Integer artistId;

	@Column(name = "Name", length = 120)
	private String name;

	@OneToMany(mappedBy = "artistId")
	private Set<Album> albums;

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArtistId() {
		return this.artistId;
	}

	public void setArtistId(Integer id) {
		this.artistId = id;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
