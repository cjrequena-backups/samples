package com.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
		
		List<String> messages = new ArrayList<String>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// Get principal
		Object principal = auth.getPrincipal();

		// Get credencials
		Object credencials = auth.getCredentials();

		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			String userName = userDetails.getUsername();
			System.out.println(userName);
			for (Iterator iterator = userDetails.getAuthorities().iterator(); iterator.hasNext();) {
				Object authority = (Object) iterator.next();
				System.out.println(authority);
			}
			messages.add("User loged successfuly");
			model.addAttribute("messages",messages);
		}

		return "home";
	}

}
