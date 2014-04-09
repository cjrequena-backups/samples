package com.sample.architecture.exceptions;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import com.sample.architecture.commons.constants.Constants;


public class BusinessExceptions extends BaseExceptions {

	private static final long serialVersionUID = 1L;
	//private static Logger LOG = LoggerFactory.getLogger(BusinessExceptions.class);

	private static Integer UNKNOWN_ERROR = -1;

	public BusinessExceptions(String message) {
		super(message);
	}

	public BusinessExceptions(int errorCode) {
		super(getMessageError(errorCode));
		this.errorCode = errorCode;
	}

	public BusinessExceptions(int errorCode, Exception ex) {
		super(errorCode, ex);
	}

	protected static String getMessageError(int errorCode) {
		String mensaje = null;
		try {
			mensaje = PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(errorCode));
		} catch (MissingResourceException mre) {
			mensaje = PropertyResourceBundle.getBundle(Constants.BUSINESS_ERROR_FILE, new Locale(Constants.BUSINESS_ERROR_LOCALE)).getString(Integer.toString(UNKNOWN_ERROR));
		}
		return mensaje;
	}

}
