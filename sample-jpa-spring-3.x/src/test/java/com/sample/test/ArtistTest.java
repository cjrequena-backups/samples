package com.sample.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.service.IArtistService;
import com.sample.vo.ArtistVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class ArtistTest {

	@Autowired
	private IArtistService artistService;
	
	@Test
	public void create() {
		ArtistVO artist = new ArtistVO();
		artist.setName("LTS4");
		try {
			this.artistService.save(artist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}