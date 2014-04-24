/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.exceptions;

import java.util.Locale;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.architecture.commons.constants.Constants;



/**
 * 
 * @author cjrequena
 *
 */
public class BaseExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static Logger LOG = null;
	/**
	 * 
	 */
	protected int errorCode = -1;

	/**
	 * 
	 */
	private void initLogs() {
		LOG = LoggerFactory.getLogger(BaseExceptions.class);
	}

	/**
	 * 
	 * @param message
	 */
	public BaseExceptions(String message) {
		super(message);
		initLogs();
		LOG.error(message);
	}

	/**
	 * 
	 * @param errorCode
	 */
	public BaseExceptions(int errorCode) {
		super(PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(errorCode)));
		this.errorCode = errorCode;
		initLogs();

		LOG.error(PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(errorCode)));
	}

	/**
	 * 
	 * @param errorCode
	 * @param ex
	 */
	public BaseExceptions(int errorCode, Exception ex) {
		super(PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(errorCode)), ex);
		this.errorCode = errorCode;
		initLogs();

		LOG.error(PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(errorCode)), ex);
	}

	/**
	 * 
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}
}
