package com.sample.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.sample.architecture.bindings.Secured;

@Named("testController")
@SessionScoped
public class TestController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Secured
	@RequiresRoles({ "ROLE_ADMIN" })
	public String testAdminRole() throws Exception{
		return "Test Admin Role";
	}

	@Secured
	@RequiresRoles({ "ROLE_USER" })
	public String testUserRole() throws Exception{
		return "Test User Role";
	}

}
