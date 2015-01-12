package com.sample.vo;

import java.io.Serializable;
import java.util.List;



public class MediaTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int mediaTypeId;

	private String name;

	private List<TrackVO> tracks;

	public MediaTypeVO() {
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

	public List<TrackVO> getTracks() {
		return this.tracks;
	}

	public void setTracks(List<TrackVO> tracks) {
		this.tracks = tracks;
	}

	public TrackVO addTrack(TrackVO track) {
		getTracks().add(track);
		track.setMediaType(this);

		return track;
	}

	public TrackVO removeTrack(TrackVO track) {
		getTracks().remove(track);
		track.setMediaType(null);

		return track;
	}

}