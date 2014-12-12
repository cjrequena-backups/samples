/*
 * 
 * 
 */
package com.sample;

public class ConfigurationManager extends AbstractConfigurationManager {
	/****/
	final public static String PROPERTY = "test.property";

	static boolean loaded = false;

	static {
		ConfigurationManager.init();
	}

	static public synchronized void init() {
		if (!loaded) {
			putdefault(ConfigurationManager.PROPERTY, "-1");

			loaded = true;

		}
		return;
	}
}
