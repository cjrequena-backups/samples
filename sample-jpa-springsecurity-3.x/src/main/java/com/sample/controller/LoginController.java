package com.sample.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
	}

	// Spring Security see this :
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {
		
		List<String> messages = new ArrayList<String>();
		List<String> errors = new ArrayList<String>();
		//List<String> warnings = new ArrayList<String>();
		
		if (error != null) {
			errors.add("Invalid username and password!");
			
		}

		if (logout != null) {
			messages.add("You've been logged out successfully.");
		}
		
		model.addAttribute("errors",errors);
		model.addAttribute("messages",messages);
		
		return "home";


	}

}
