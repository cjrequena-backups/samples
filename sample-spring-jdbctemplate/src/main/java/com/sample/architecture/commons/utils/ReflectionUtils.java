package com.sample.architecture.commons.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils<T> {
	public T setAllSetters(T target, Object[] data) throws Exception {
		
		Class<? extends Object> targetClass = target.getClass();
		for (int i = 0; i < data.length; i++) {

			for (Field field : targetClass.getDeclaredFields()) {
				for (Method method : targetClass.getMethods()) {
					if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
							try {
								method.setAccessible(true);
								method.invoke(target, data[i]);
								i++;
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
						}
					}
				}
			}
		}
		return target;
	}

	@SuppressWarnings("unchecked")
	public List<T> setAllSetters(T target, ResultSet rs) throws Exception {

		List<T> list = new ArrayList<T>();
		Class<? extends Object> targetClass = target.getClass();
		while (rs.next()) {
			for (Field field : targetClass.getDeclaredFields()) {
				for (Method method : targetClass.getMethods()) {
					if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))) {
						if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
							try {
								method.setAccessible(true);
								if (field.getType().getSimpleName().toLowerCase().endsWith("integer")) {
									method.invoke(target, rs.getInt(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("long")) {
									method.invoke(target, rs.getLong(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("string")) {
									method.invoke(target, rs.getString(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("boolean")) {
									method.invoke(target, rs.getBoolean(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("timestamp")) {
									method.invoke(target, rs.getTimestamp(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("date")) {
									method.invoke(target, rs.getDate(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("double")) {
									method.invoke(target, rs.getDouble(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("float")) {
									method.invoke(target, rs.getFloat(field.getName().toLowerCase()));
								} else if (field.getType().getSimpleName().toLowerCase().endsWith("time")) {
									method.invoke(target, rs.getTime(field.getName().toLowerCase()));
								} else {
									method.invoke(target, rs.getObject(field.getName().toLowerCase()));
								}
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
						}
					}
				}
			}
			list.add(target);
			Class<?> clazz = Class.forName(targetClass.getName());
			Constructor<?> constructor = clazz.getConstructor();
			T newInstance = (T) constructor.newInstance(new Object[] {});
			target = newInstance;
		}
		return list;
	}

	public T setAllSetters(T target, Object source) throws Exception {
		Class<? extends Object> targetClass = target.getClass();
		Class<? extends Object> sourceClass = source.getClass();

		for (Method sourceMethod : sourceClass.getMethods()) {
			String sourceMethodName = sourceMethod.getName();
			if ((sourceMethodName.startsWith("get"))) {
				for (Method targetMethod : targetClass.getMethods()) {
					String targetMethodName = targetMethod.getName();
					if ((targetMethodName.startsWith("set"))) {
						if (sourceMethodName.substring(3, sourceMethodName.length()).equals(targetMethodName.substring(3, targetMethodName.length()))) {
							targetMethod.invoke(target, sourceMethod.invoke(source));
						}
					}

				}

			}

		}
		return target;
	}
}