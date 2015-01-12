package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;


/**
 * The persistent class for the MediaType database table.
 * 
 */
@Configurable
@Entity
@Table(name="MediaType")
@NamedQuery(name="MediaTypeEntity.findAll", query="SELECT m FROM MediaTypeEntity m")
public class MediaTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mediaTypeId;

	private String name;

	//bi-directional many-to-one association to TrackEntity
	@OneToMany(mappedBy="mediaType")
	private List<TrackEntity> tracks;

	public MediaTypeEntity() {
	}

	public int getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrackEntity> getTracks() {
		return this.tracks;
	}

	public void setTracks(List<TrackEntity> tracks) {
		this.tracks = tracks;
	}

	public TrackEntity addTrack(TrackEntity track) {
		getTracks().add(track);
		track.setMediaType(this);

		return track;
	}

	public TrackEntity removeTrack(TrackEntity track) {
		getTracks().remove(track);
		track.setMediaType(null);

		return track;
	}

}