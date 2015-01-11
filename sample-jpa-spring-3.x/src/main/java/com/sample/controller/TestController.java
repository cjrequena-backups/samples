package com.sample.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.architecture.exceptions.BusinessExceptions;
import com.sample.model.entity.AlbumEntity;
import com.sample.service.IAlbumService;

@Controller
public class TestController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	IAlbumService albumService;


	@PostConstruct
	public void init() {
	}

	@RequestMapping(value="/test.html", method = RequestMethod.GET)
	public String test() {
		try {
			List<AlbumEntity> list = this.albumService.findAll();
			for (Iterator<AlbumEntity> iterator = list.iterator(); iterator.hasNext();) {
				AlbumEntity albumEntity = (AlbumEntity) iterator.next();
				System.out.println(albumEntity.getTitle());
			}
		} catch (BusinessExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
	}

	
}
