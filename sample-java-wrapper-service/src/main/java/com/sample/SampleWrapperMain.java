package com.sample;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

public class SampleWrapperMain implements WrapperListener, Runnable {

	/**
	 * Command line arguments to be passed on to the application
	 */
	static protected String[] m_appArgs;

	/**
	 * 
	 */
	private volatile boolean m_stopping;

	/**
	 * Gets set to true when the thread used to launch the application completes.
	 */
	private boolean m_mainComplete;

	/**
	 * Exit code to be returned if the application fails to start.
	 */
	private Integer m_mainExitCode;

	/**
	 * Flag used to signify that the start method is done waiting for the application to start.
	 */
	private boolean m_waitTimedOut;

	/**
	 * 
	 */
	private Thread m_mainThread;
	/**
	 * 
	 */
	private static boolean wrapperDebugEnabled = WrapperManager.isDebugEnabled();

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void controlEvent(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer start(String[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int stop(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
