package com.sample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class SampleWrapperService2 implements Runnable {

	static Logger logger = Logger.getLogger(SampleWrapperService2.class);

	/**
	 * Le indica al metodo runService que necesita que detenga el servicio.
	 */
	private volatile boolean m_stopping;

	/**
	 * Especifica los Argumentos pasados a la aplicacion.
	 */
	private String appArgs[];

	public SampleWrapperService2(String[] args) {
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

		int corePoolSize = 10;
		int maxPoolSize = 20;
		long keepAliveTime = 5000;
		BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(50);

		ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, blockingQueue);

		//while (!this.m_stopping) {
		for(int ii=0;ii<10;ii++){

			for (int i = 0; i < threads1.length; i++) {
				if (!m_stopping && (threads1[i] == null || !threads1[i].isAlive())) {
					threads1[i] = new SampleThread1(workGroup1, "WorkerG1[" + i + "]");
					threadPoolExecutor.execute(threads1[i]);
				}
			}

			for (int i = 0; i < threads2.length; i++) {
				if (!m_stopping && (threads2[i] == null || !threads2[i].isAlive())) {
					threads2[i] = new SampleThread2(workGroup2, "WorkerG2[" + i + "]");
					threadPoolExecutor.execute(threads2[i]);
				}
			}

			try {
				threadPoolExecutor.awaitTermination(60 * 1000, TimeUnit.MILLISECONDS); // 1min
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " Imterrupted.");
			}
			try {
				System.gc();
			} catch (Exception ignore) {
			}
		}

		threadPoolExecutor.shutdown();
		while (!threadPoolExecutor.isTerminated()) {
		}
		System.out.println("Finished all threads");
	}

	public void setAppArgs(String m_appArgs[]) {
		this.appArgs = m_appArgs;
	}

	public String[] getAppArgs() {
		return this.appArgs;
	}

}
