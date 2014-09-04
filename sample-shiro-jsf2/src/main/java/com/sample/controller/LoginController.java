/**
 * Very simple bean that authenticates the user via Apache Shiro, using JSF
 * @author Daniel Mascarenhas
 */
package com.sample.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	private String username;
	private String password;
	private Boolean rememberMe;

	public LoginController() {
	}

	/**
	 * Try and authenticate the user
	 */
	public String doLogin() {
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());

		try {

			subject.login(token);

			if (subject.hasRole("ROLE_ADMIN")) {
				// FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
				return "admin/index.xhtml";
			} else {
				//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
				return "index.xhtml";
			}
		} catch (UnknownAccountException ex) {
			facesError("Unknown account");
			log.error(ex.getMessage(), ex);
		} catch (IncorrectCredentialsException ex) {
			facesError("Wrong password");
			log.error(ex.getMessage(), ex);
		} catch (LockedAccountException ex) {
			facesError("Locked account");
			log.error(ex.getMessage(), ex);
		} catch (AuthenticationException ex) {
			facesError("Unknown error: " + ex.getMessage());
			log.error(ex.getMessage(), ex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			token.clear();
		}
		return "index.xhtml";
	}

	/**
	 * Adds a new SEVERITY_ERROR FacesMessage for the ui
	 * 
	 * @param message
	 *            Error Message
	 */
	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean lembrar) {
		this.rememberMe = lembrar;
	}
}
