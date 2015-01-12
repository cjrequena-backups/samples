package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;


/**
 * The persistent class for the Genre database table.
 * 
 */
@Configurable
@Entity
@Table(name="Genre")
@NamedQuery(name="GenreEntity.findAll", query="SELECT g FROM GenreEntity g")
public class GenreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int genreId;

	private String name;

	//bi-directional many-to-one association to TrackEntity
	@OneToMany(mappedBy="genre")
	private List<TrackEntity> tracks;

	public GenreEntity() {
	}

	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
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
		track.setGenre(this);

		return track;
	}

	public TrackEntity removeTrack(TrackEntity track) {
		getTracks().remove(track);
		track.setGenre(null);

		return track;
	}

}