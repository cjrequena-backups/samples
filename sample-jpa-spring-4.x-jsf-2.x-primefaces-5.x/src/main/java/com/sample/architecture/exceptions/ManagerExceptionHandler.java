/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.exceptions;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * 
 * @author cjrequena
 *
 */
public class ManagerExceptionHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;

	public ManagerExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			NavigationHandler nav = fc.getApplication().getNavigationHandler();

			
			if (t instanceof ViewExpiredException) {
				ViewExpiredException vee = (ViewExpiredException) t;
				FacesContext facesContext = FacesContext.getCurrentInstance();
				NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
				try {
					// Push some useful stuff to the request scope for use in the page
					requestMap.put("currentViewId", vee.getViewId());
					navigationHandler.handleNavigation(facesContext, null, "/viewExpired");
					facesContext.renderResponse();
				} finally {
					i.remove();
				}
			} else if (t instanceof FacesException) {
				FacesException fe = (FacesException) t;

				try {

					// Push some useful stuff to the request scope for use in the page
					StringBuffer message = new StringBuffer();

					if (fe.getCause() != null) {
						message.append(" Cause: " + fe.getCause() + "\n");
					}

//					if (fe.getMessage() != null) {
//						message.append(" Detail: " + fe.getMessage());
//					}
					
					requestMap.put("cause", message.toString());

					// Navigate to the viewExpired outcome. Use viewExpired.xhtml for implicit handling or
					// configure a navigation rule in the faces-config.xml for this outcome
					nav.handleNavigation(fc, null, "/error.xhtml");

					// Skip to rendering the response
					fc.renderResponse();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// Remove this unhandled exception
					i.remove();
				}
			}
		}

		// At this point, the queue will not contain any ViewExpiredEvents. Therefore, let the parent handle them.
		getWrapped().handle();
	}
}
