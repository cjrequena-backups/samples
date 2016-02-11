/*
 * AbstractConfigurationManager.java
 *
 * Created on 15 de enero de 2004, 03:18 PM
 */

package com.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author carlos_requena
 * @version 1.0
 */
public abstract class AbstractConfigurationManager {

	final static protected Properties defaults = new Properties();
	final static protected Properties property = new Properties(defaults);
	private static InputStream propsFile = null;
	private static final String CONFIGURATION_FILE = "configuration.properties";

	static {
		File f = null;
		f = new File(CONFIGURATION_FILE);
		if (!f.exists() || !f.isFile()) {
			try {
				ResourcesLocator serviceConf = new ResourcesLocator(CONFIGURATION_FILE);
				propsFile = serviceConf.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (f.exists() && f.isFile()) {
			load(f);
		} else {
			load(propsFile);
		}
	}

	final static boolean load(File file) {
		boolean result = true;
		FileInputStream propsFile = null;
		try {
			propsFile = new FileInputStream(file);
			property.load(propsFile);
		} catch (Exception e) {
			result = false;
			// Ignoramos las
		} finally {
			try {
				propsFile.close();
			} catch (Exception e) {
				// ignoramos
			}
		}
		return result;
	}

	final static boolean load(InputStream propsFile) {
		boolean result = true;
		// FileInputStream propsFile = null;
		try {
			// propsFile = new FileInputStream(file);
			property.load(propsFile);
		} catch (Exception e) {
			result = false;
			// Ignoramos las
		} finally {
			try {
				propsFile.close();
			} catch (Exception e) {
				// ignoramos
			}
		}
		return result;
	}

	final static public void putdefault(Object key, Object value) {
		defaults.put(key, value);
	}

	/** Obtiene una Propiedad como un String si no Existe devuelve "" */
	final public static String getProperty(String key) {
		return property.getProperty(key);
	}

	/** Obtiene una Propiedad como un String sino su valor por defecto */
	final public static String getProperty(String key, String defaultValue) {
		return property.getProperty(key, defaultValue);
	}

	/**
	 * Obtiene la propiedad y la convierte a boolean si la propiedad no existe retorna false
	 */
	final static public boolean getBooleanProperty(String propName) {
		return getBooleanProperty(propName, false);
	}

	/**
	 * Obtiene la propiedad y la convierte a boolean si la propiedad no existe retorna defaultValue
	 */
	final static public boolean getBooleanProperty(String propName, boolean defaultValue) {
		try {
			return Boolean.valueOf(property.getProperty(propName, Boolean.toString(defaultValue))).booleanValue();
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a byte si la propiedad no existe retorna (byte)0
	 */
	final static public byte getByteProperty(String propName) {
		return getByteProperty(propName, (byte) 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a byte si la propiedad no existe retorna defaultValue
	 */
	final static public byte getByteProperty(String propName, byte defaultValue) {
		try {
			return Byte.parseByte(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a int si la propiedad no existe retorna (int)0
	 */
	final static public int getIntProperty(String propName) {
		return getIntProperty(propName, 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a int si la propiedad no existe retorna defaultValue
	 */
	final static public int getIntProperty(String propName, int defaultValue) {
		try {
			return Integer.parseInt(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a long si la propiedad no existe retorna (long)0
	 */
	final static public long getLongProperty(String propName) {
		return getLongProperty(propName, 0L);
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe retorna defaultValue
	 */
	final static public long getLongProperty(String propName, long defaultValue) {
		try {
			return Long.parseLong(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe retorna (short)0
	 */
	final static public short getShortProperty(String propName) {
		return getShortProperty(propName, (short) 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe retorna defaultValue
	 */
	final static public short getShortProperty(String propName, short defaultValue) {
		try {
			return Short.parseShort(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe retorna (short)0
	 */
	final static public double getDoubleProperty(String propName) {
		return getDoubleProperty(propName, 0.0);
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe retorna defaultValue
	 */
	final static public double getDoubleProperty(String propName, double defaultValue) {
		try {
			return Double.parseDouble(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe retorna (short)0
	 */
	final static public float getFloatProperty(String propName) {
		return getFloatProperty(propName, (float) 0.0);
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe retorna defaultValue
	 */
	final static public float getFloatProperty(String propName, float defaultValue) {
		try {
			return Float.parseFloat(property.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}
}
