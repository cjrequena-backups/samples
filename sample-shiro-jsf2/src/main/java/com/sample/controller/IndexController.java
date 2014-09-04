/**
 * Example Bean
 * @author Daniel Mascarenhas
 */
package com.sample.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Subject subject = SecurityUtils.getSubject();

	public Subject getSubject() {
		return subject;
	}
}
