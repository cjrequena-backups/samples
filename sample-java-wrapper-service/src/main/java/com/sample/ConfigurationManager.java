/*
 * 
 * 
 */
package com.sample;


public class ConfigurationManager extends AbstractConfigurationManager {
	/****/
	final public static String SERVICE_NAME= "service.name";
	/***/
	final public static String SERVICE_LOCALPORT = "service.localport";

	static boolean loaded = false;

	static {
		ConfigurationManager.init();
	}

	static public synchronized void init() {
		if (!loaded) {
			putdefault(ConfigurationManager.SERVICE_NAME, "SampleWrapperService");
			putdefault(ConfigurationManager.SERVICE_LOCALPORT, "8100");

			loaded = true;

		}
		return;
	}
}
