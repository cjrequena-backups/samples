package com.sample.architecture.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionUtils {
	public static Object setAllSetters(Object ob, Object[] data) throws SQLException {
		Class<? extends Object> cls = ob.getClass();
		for (int i = 0; i < data.length; i++) {

			for (Field field : cls.getDeclaredFields()) {
				for (Method method : cls.getMethods()) {
					if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
							try {
								method.setAccessible(true);
								if (field.getType().getSimpleName().toLowerCase().endsWith("integer")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("long")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("string")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("boolean")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("timestamp")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("date")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("double")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("float")) {
									method.invoke(ob, data[i]);
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("time")) {
									method.invoke(ob, data[i]);
								} else {
									method.invoke(ob, data[i]);
								}
								i++;
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
						}
					}
				}
			}
		}
		return ob;
	}

	public static Object setAllSetters(Object ob, ResultSet rs) throws SQLException {
		Class<? extends Object> cls = ob.getClass();
		while (rs.next()) {
			for (Field field : cls.getDeclaredFields()) {
				for (Method method : cls.getMethods()) {
					if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
							try {
								method.setAccessible(true);
								if (field.getType().getSimpleName().toLowerCase().endsWith("integer")) {
									method.invoke(ob, rs.getInt(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("long")) {
									method.invoke(ob, rs.getLong(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("string")) {
									method.invoke(ob, rs.getString(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("boolean")) {
									method.invoke(ob, rs.getBoolean(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("timestamp")) {
									method.invoke(ob, rs.getTimestamp(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("date")) {
									method.invoke(ob, rs.getDate(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("double")) {
									method.invoke(ob, rs.getDouble(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("float")) {
									method.invoke(ob, rs.getFloat(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("time")) {
									method.invoke(ob, rs.getTime(field.getName().toLowerCase()));
								} else {
									method.invoke(ob, rs.getObject(field.getName().toLowerCase()));
								}
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
						}
					}
				}
			}
		}
		return ob;
	}

}
