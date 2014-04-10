package com.sample.architecture.commons.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 * 
 * @author cjrequena
 *
 */
public class MessageFactory {

	private static String DEFAULT_DETAIL_SUFFIX = "_detail";

	private MessageFactory() {
	}

	public static FacesMessage getMessage(final Locale locale, final String messageId, final FacesMessage.Severity severity, final Object... params) {
		final FacesMessage facesMessage = getMessage(locale, messageId, params);
		facesMessage.setSeverity(severity);
		return facesMessage;
	}

	public static FacesMessage getMessage(final Locale locale, final String messageId, final Object... params) {
		String summary = null;
		String detail = null;
		final FacesContext context = FacesContext.getCurrentInstance();
		final ResourceBundle bundle = context.getApplication().getResourceBundle(context, "i18n");

		try {
			summary = getFormattedText(locale, bundle.getString(messageId), params);
		} catch (final MissingResourceException e) {
			summary = messageId;
		}

		try {
			detail = getFormattedText(locale, bundle.getString(messageId + DEFAULT_DETAIL_SUFFIX), params);
		} catch (final MissingResourceException e) {
			// e.printStackTrace();
		}

		return new FacesMessage(summary, detail);
	}

	public static FacesMessage getMessage(final String messageId, final FacesMessage.Severity severity, final Object... params) {
		final FacesMessage facesMessage = getMessage(getLocale(), messageId, params);
		facesMessage.setSeverity(severity);
		return facesMessage;
	}

	public static FacesMessage getMessage(final String messageId, final Object... params) {
		return getMessage(getLocale(), messageId, params);
	}
	
	public static String getStringMessage(String resourceBundleName, String resourceBundleKey) throws MissingResourceException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, resourceBundleName);
		return bundle.getString(resourceBundleKey);
	}

	private static String getFormattedText(final Locale locale, final String message, final Object params[]) {
		MessageFormat messageFormat = null;

		if (params == null || message == null) {
			return message;
		}

		messageFormat = locale == null ? new MessageFormat(message) : new MessageFormat(message, locale);
		return messageFormat.format(params);
	}

	private static Locale getLocale() {
		Locale locale = null;
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && facesContext.getViewRoot() != null) {
			locale = facesContext.getViewRoot().getLocale();
			if (locale == null) {
				locale = Locale.getDefault();
			}
		} else {
			locale = Locale.getDefault();
		}

		return locale;
	}

	
}
