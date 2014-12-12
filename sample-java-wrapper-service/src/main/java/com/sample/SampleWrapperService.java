package com.sample;


public class SampleWrapperService implements Runnable {

	/**
	 * Le indica al metodo runService que necesita que detenga el servicio.
	 */
	private volatile boolean m_stopping;

	/**
	 * Especifica los Argumentos pasados a la aplicacion.
	 */
	private String appArgs[];

	public SampleWrapperService(String[] args) {
		appArgs = args;
	}

	public void stop() throws Exception {
		m_stopping = true;
		return;
	}

	@Override
	public void run() {
		this.m_stopping = false;

		while (!this.m_stopping) {
			System.out.println("SAMPLE WRAPPER SERVICE");			
		}

	}

	public void setAppArgs(String m_appArgs[]) {
		this.appArgs = m_appArgs;
	}

	public String[] getAppArgs() {
		return this.appArgs;
	}

}
