package com.sample;

import org.apache.log4j.Logger;

public class SampleWrapperService implements Runnable {

	static Logger logger = Logger.getLogger(SampleWrapperService.class);

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

		/** El Grupo para los hilos de Trbajos */
		ThreadGroup workGroup1 = new ThreadGroup("workGroup1");
		ThreadGroup workGroup2 = new ThreadGroup("workGroup2");

		SampleThread1 threads1[] = new SampleThread1[1];
		SampleThread2 threads2[] = new SampleThread2[10];

		final long threadCheckTimeout = ConfigurationManager.getLongProperty(ConfigurationManager.SERVICE_THREAD_CHECKTIMEOUT);

		while (!this.m_stopping) {

			// Si algun hilo esta detenido lo volvemos a levantar, creando un nuevo session
			for (int i = 0; i < threads2.length; i++) {
				if (!m_stopping && (threads2[i] == null || !threads2[i].isAlive())) {
					threads2[i] = new SampleThread2();
					threads2[i].start();
				}
			}

			// Si algun hilo esta detenido lo volvemos a levantar, creando un nuevo hilo
			for (int i = 0; i < threads1.length; i++) {
				if (!m_stopping && (threads1[i] == null || !threads1[i].isAlive())) {
					threads1[i] = new SampleThread1();
					threads1[i].start();
				}
			}

			// Dormimos el Hilo, quizas sea mejor implementar un mecanismos wait/notify
			// para solo chequear cuando haga falta!! pero bueno todo no es perfecto :-(
			try {
				Thread.sleep(threadCheckTimeout);
			} catch (InterruptedException ie) {
				System.out.println(Thread.currentThread().getName() + " Interrumpido.");
			}

			try {
				System.gc();
			} catch (Exception ignore) {
			}
		}

		// Interrumpimos el grupo de trbajo y la sessiones
		workGroup1.interrupt();
		workGroup2.interrupt();

		// El Proceso de eliminar los Threads.
		for (int i = 0; i < threads1.length; i++) {
			if (threads1[i].isAlive()) {
				try {
					if (logger.isDebugEnabled()) {
						logger.debug("Esperando por " + threads1[i].getName());
					}
					// threads1[i].Stop();
					threads1[i].join();
					threads1[i] = null;
				} catch (InterruptedException ie) {
					try {
						threads1[i].interrupt();
					} catch (Exception e) {
						// ignoramos
					}
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("No Espera por " + threads1[i].getName());
				}
			}
		}

		// El Proceso de eliminar los Threads.
		for (int i = 0; i < threads2.length; i++) {
			if (threads2[i].isAlive()) {
				try {
					if (logger.isDebugEnabled()) {
						logger.debug("Esperando por " + threads2[i].getName());
					}
					// threads2[i].Stop();
					threads2[i].join();
					threads2[i] = null;
				} catch (InterruptedException ie) {
					try {
						threads2[i].interrupt();
					} catch (Exception e) {
						// ignoramos
					}
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("No Espera por " + threads2[i].getName());
				}
			}
		}

		try {
			workGroup1.destroy();
			workGroup2.destroy();
		} catch (Exception e) {
			logger.error("Error in destroy group - ignorado", e);
		}
	}

	public void setAppArgs(String m_appArgs[]) {
		this.appArgs = m_appArgs;
	}

	public String[] getAppArgs() {
		return this.appArgs;
	}

}
