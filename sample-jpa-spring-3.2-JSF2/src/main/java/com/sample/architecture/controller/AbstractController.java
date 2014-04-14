package com.sample.architecture.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * @author cjrequena
 * 
 * @param <T>
 */
public abstract class AbstractController<T> implements IController<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${firstResult}")
	protected Integer firstResult;
	@Value("${maxResults}")
	protected Integer maxResults;

	protected IController<?> parentController;
	protected T dataObject;

	// MAP FILTER PARAMETERS
	protected Map<Object, Object> mapParameters = new HashMap<Object, Object>();

	/**
	 * 
	 * @param facesContext
	 * @param controllerName
	 *            -- Controlador de tipo Qry.
	 * @param parameterValue
	 *            -- El valor u objeto u parametro a insertar.
	 * @param parameterKey
	 *            -- El nombre del campo en la entidad JPA del dataObject
	 *            (Represanta el objeto JPA contra quien se ejecuta la query).
	 * @return
	 */
	public MethodExpressionActionListener addToMapParameretersListener(FacesContext facesContext, String controllerQryName, Object parameterValue, Object parameterKey) throws Exception {
		try {
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			MethodExpressionActionListener methodExpressionActionListener;
			if (parameterValue instanceof String) {
				methodExpressionActionListener = new MethodExpressionActionListener(expressionFactory.createMethodExpression(elContext, "#{" + controllerQryName + ".addToMapParamereters('" + parameterValue + "','" + parameterKey + "')}", null, new Class[] { Object.class, String.class }));
			} else {
				methodExpressionActionListener = new MethodExpressionActionListener(expressionFactory.createMethodExpression(elContext, "#{" + controllerQryName + ".addToMapParamereters(" + parameterValue + ",'" + parameterKey + "')}", null, new Class[] { Object.class, String.class }));
			}
			return methodExpressionActionListener;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	public MethodExpressionActionListener addToMapParameretersListenerByElExpression(FacesContext facesContext, String controllerQryName, Object parameterValue, Object parameterKey) throws Exception {
		try {
			Application application = facesContext.getApplication();
			ExpressionFactory expressionFactory = application.getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			MethodExpressionActionListener methodExpressionActionListener;
			methodExpressionActionListener = new MethodExpressionActionListener(expressionFactory.createMethodExpression(elContext, "#{" + controllerQryName + ".addToMapParamereters(" + parameterValue + ",'" + parameterKey + "')}", null, new Class[] { Object.class, String.class }));
			return methodExpressionActionListener;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void addToMapParamereters(Object obj) throws Exception {
		try {
			String keyParameter = "P" + (this.mapParameters.size() + 1);
			this.mapParameters.put(keyParameter, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void addToMapParamereters(String obj) throws Exception {
		try {
			String keyParameter = "P" + (this.mapParameters.size() + 1);
			this.mapParameters.put(keyParameter, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void addToMapParamereters(Object obj, String key) throws Exception {
		try {
			this.mapParameters.put(key, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	public void addToMapParamereters(Object obj, String key, Class<?> type) throws Exception {
		try {
			this.mapParameters.put(key, type.cast(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	// public void addToMapParamereters(String obj, String key) {
	// this.mapParameters.put(key, obj);
	// }
	//
	// public void addToMapParamereters(BigDecimal obj, String key) {
	// this.mapParameters.put(key, obj);
	// }

	public void clearMapParamereters() throws Exception {
		try {
			this.mapParameters = new HashMap<Object, Object>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	/* #################################### */
	/* ######## GETTERS AND SETTERS ####### */
	/* #################################### */
	/**
	 * 
	 * @return
	 */
	public Object[] getParameterList() {

		Object parameterList[] = new Object[this.mapParameters.size()];

		if (this.mapParameters != null) {
			int pos = 0;
			for (Iterator<Entry<Object, Object>> iterator = this.mapParameters.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				parameterList[pos++] = entry.getValue();
			}
		}
		return parameterList;
	}

	public Map<Object, Object> getMapParameters() {
		if (this.mapParameters == null) {
			this.mapParameters = new HashMap<Object, Object>();
		}
		return mapParameters;
	}

	public void setMapParameters(Map<Object, Object> mapParameters) {
		this.mapParameters = mapParameters;
	}

	public IController<?> getParentController() {
		return parentController;
	}

	public void setParentController(IController<?> parentController) {
		this.parentController = parentController;
	}

}
