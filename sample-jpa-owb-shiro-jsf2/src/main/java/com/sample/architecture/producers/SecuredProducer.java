package com.sample.architecture.producers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cjrequena
 *
 */
@RequestScoped
public class SecuredProducer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = LoggerFactory.getLogger(SecuredProducer.class);
	
	private SecurityManager securityManager;

	@PostConstruct
	public void init() {
		final String iniFile = "classpath:shiro.ini";
		logger.info("Initializing Shiro INI SecurityManager using " + iniFile);
		securityManager = new IniSecurityManagerFactory(iniFile).getInstance();
		SecurityUtils.setSecurityManager(securityManager);
	}

	@Produces
	@RequestScoped
	@Named("securityManager")
	public SecurityManager getSecurityManager() {
		return securityManager;
	}

	@Produces
	public Subject getSubject() {
		Subject subject = SecurityUtils.getSubject();
		return subject;
	}
}