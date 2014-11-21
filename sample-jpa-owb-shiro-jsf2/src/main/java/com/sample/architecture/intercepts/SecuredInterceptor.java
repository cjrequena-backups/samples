package com.sample.architecture.intercepts;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.architecture.bindings.Secured;


/**
 * 
 * @author cjrequena
 * 
 */
@Secured
@Interceptor
public class SecuredInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(SecuredInterceptor.class);

	@Inject
	private transient Subject subject;

	@AroundInvoke
	public Object interceptShiroSecurity(InvocationContext context) throws Exception {
		Class<?> c = context.getTarget().getClass();
		Method m = context.getMethod();

		if (!subject.isAuthenticated() && hasAnnotation(c, m, RequiresAuthentication.class)) {
			throw new UnauthenticatedException("Authentication required");
		}

		if (subject.getPrincipal() != null && hasAnnotation(c, m, RequiresGuest.class)) {
			throw new UnauthenticatedException("Guest required");
		}

		if (subject.getPrincipal() == null && hasAnnotation(c, m, RequiresUser.class)) {
			throw new UnauthenticatedException("User required");
		}

		RequiresRoles roles = getAnnotation(c, m, RequiresRoles.class);

		if (roles != null) {
			subject.checkRoles(Arrays.asList(roles.value()));
		}

		RequiresPermissions permissions = getAnnotation(c, m, RequiresPermissions.class);

		if (permissions != null) {
			subject.checkPermissions(permissions.value());
		}

		return context.proceed();
	}

	private static boolean hasAnnotation(Class<?> c, Method m, Class<? extends Annotation> a) {
		return m.isAnnotationPresent(a) || c.isAnnotationPresent(a) || c.getSuperclass().isAnnotationPresent(a);
	}

	private static <A extends Annotation> A getAnnotation(Class<?> c, Method m, Class<A> a) {
		return m.isAnnotationPresent(a) ? m.getAnnotation(a) : c.isAnnotationPresent(a) ? c.getAnnotation(a) : c.getSuperclass().getAnnotation(a);
	}

}
