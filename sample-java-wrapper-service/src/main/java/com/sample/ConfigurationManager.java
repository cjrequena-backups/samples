/*
 * 
 * 
 */
package com.sample;


public class ConfigurationManager extends AbstractConfigurationManager {
	/****/
	final public static String SERVICE_NAME = "service.name";
	/** Especifica el puerto TCP para el servicio 0 es desactivado */
	final public static String SERVICE_LOCALPORT = "service.localport";
	/** Especifica el timepo para chequear que todo los hilos esten funcionando */
	final public static String SERVICE_THREAD_CHECKTIMEOUT = "service.thread.checktimeout";

	static boolean loaded = false;

	static {
		ConfigurationManager.init();
	}

	static public synchronized void init() {
		if (!loaded) {
			putdefault(ConfigurationManager.SERVICE_NAME, "SampleWrapperService");
			putdefault(ConfigurationManager.SERVICE_LOCALPORT, "8100");
			putdefault(ConfigurationManager.SERVICE_THREAD_CHECKTIMEOUT, "" + (60 * 1000)); /* 1min */

			loaded = true;

		}
		return;
	}
}
