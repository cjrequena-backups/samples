/*
 * email: carlosjose.requena@gmail.com 
 * blog:  http://carlosjoserequena.blogspot.com
 *  
 */
package com.sample.architecture.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sample.architecture.dao.Column;

/**
 * 
 * @author cjrequena
 * 
 */
@FacesConverter("com.sample.architecture.converter.ColumnConverter")
public class ColumnConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map<String, Column> columns = new HashMap<String, Column>();

	public Column getAsObject(FacesContext context, UIComponent component, String value) {
		return this.columns.get(value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Column) {
			String columnName = ((Column) value).getName();
			if (columnName != null && !columnName.isEmpty()) {
				this.columns.put(columnName, (Column) value);
				return columnName;
			}
		}
		return "";

	}
}
