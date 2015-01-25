package com.sample.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.model.entity.ArtistEntity;
import com.sample.service.IArtistService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class ArtistTest {

	@Autowired
	private IArtistService artistService;
	
	@Test
	public void create() {
		ArtistEntity artist = new ArtistEntity();
		artist.setName("LTS4");
		try {
			this.artistService.save(artist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}