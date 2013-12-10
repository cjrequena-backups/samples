package com.sample.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.sample.service.IAlbumService;


@Configurable
@ManagedBean(name = "CityController")
@SessionScoped
public class AlbumController implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Autowired
	IAlbumService albumService;
	
	@PostConstruct
    public void init() {
    }
	public String test(){
		//System.out.println(this.cityService.test());
		return "album";
	}
}
