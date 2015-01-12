package com.sample.vo;

import java.io.Serializable;
import java.util.List;

public class GenreVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int genreId;

	private String name;

	private List<TrackVO> tracks;

	public GenreVO() {
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

	public List<TrackVO> getTracks() {
		return this.tracks;
	}

	public void setTracks(List<TrackVO> tracks) {
		this.tracks = tracks;
	}

	public TrackVO addTrack(TrackVO track) {
		getTracks().add(track);
		track.setGenre(this);

		return track;
	}

	public TrackVO removeTrack(TrackVO track) {
		getTracks().remove(track);
		track.setGenre(null);

		return track;
	}

}