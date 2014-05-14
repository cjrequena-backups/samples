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

@Entity
@Table(name = "MediaType")
@Configurable
public class MediaType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MediaTypeId")
	private Integer mediaTypeId;

	@Column(name = "Name", length = 120)
	private String name;

	@OneToMany(mappedBy = "mediaTypeId")
	private Set<Track> tracks;

	public Integer getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(Integer id) {
		this.mediaTypeId = id;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
