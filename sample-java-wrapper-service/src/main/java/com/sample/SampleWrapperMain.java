package com.sample;

import org.tanukisoftware.wrapper.WrapperActionServer;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

public class SampleWrapperMain implements WrapperListener, Runnable {

	/**
	 * Command line arguments to be passed on to the application
	 */
	static protected String[] m_appArgs;

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

	/**
	 * 
	 */
	private SampleWrapperService sampleWrapperService;

	public SampleWrapperMain() {
		m_mainThread = null;
	}

	/**
	 * 
	 * @return
	 */
	static final boolean isDebugEnabled() {
		return wrapperDebugEnabled;
	}

	/**
	 * 
	 * @param debugString
	 */
	static final void printDebug(String debugString) {
		if (wrapperDebugEnabled) {
			System.out.println((Thread.currentThread().getName() + "::" + debugString).replace('\0', ' '));
		}
	}

	/**
	 * 
	 * @param errorString
	 */
	static final void printError(String errorString) {
		if (WrapperManager.isControlledByNativeWrapper()) {
			WrapperManager.log(WrapperManager.WRAPPER_LOG_LEVEL_ERROR, (Thread.currentThread().getName() + "::" + errorString).replace('\0', ' '));
		} else {
			System.out.println((Thread.currentThread().getName() + "::" + errorString).replace('\0', ' '));
		}
	}

	/**
	 * 
	 * @param infoString
	 */
	static final void printInfo(String infoString) {
		if (WrapperManager.isControlledByNativeWrapper()) {
			WrapperManager.log(WrapperManager.WRAPPER_LOG_LEVEL_INFO, (Thread.currentThread().getName() + "::" + infoString).replace('\0', ' '));
		} else {
			System.out.println((Thread.currentThread().getName() + "::" + infoString).replace('\0', ' '));
		}
	}

	@Override
	public void controlEvent(int event) {
		if ((event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT) && WrapperManager.isLaunchedAsService()) {
			// Ignore
			if (WrapperManager.isDebugEnabled()) {
				System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": controlEvent(" + event + ") Ignored");
			}
		} else {
			if (WrapperManager.isDebugEnabled()) {
				System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": controlEvent(" + event + ") Stopping");
			}
			WrapperManager.stop(0);
			// Will not get here.
		}
	}

	/**
	 * 
	 * @param warmString
	 */
	static final void printWarm(String warmString) {
		if (WrapperManager.isControlledByNativeWrapper()) {
			WrapperManager.log(WrapperManager.WRAPPER_LOG_LEVEL_WARN, (Thread.currentThread().getName() + "::" + warmString).replace('\0', ' '));
		} else {
			System.out.println((Thread.currentThread().getName() + "::" + warmString).replace('\0', ' '));
		}
	}

	@Override
	public Integer start(String[] args) {
		if (WrapperManager.isDebugEnabled()) {
			System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": start(args)");
		}
		try {
			if (ConfigurationManager.getIntProperty(ConfigurationManager.SERVICE_LOCALPORT, 0) != 0) {
				int port = ConfigurationManager.getIntProperty(ConfigurationManager.SERVICE_LOCALPORT);
				WrapperActionServer server = new WrapperActionServer(port);
				server.enableShutdownAction(true);
				server.enableHaltExpectedAction(true);
				server.enableRestartAction(true);
				server.enableThreadDumpAction(true);
				server.enableHaltUnexpectedAction(true);
				server.enableAccessViolationAction(true);
				server.start();

				System.out.println("ActionServer Enabled. ");
				System.out.println("  Telnet localhost " + port);
				System.out.println("  Commands: ");
				System.out.println("    S: Shutdown");
				System.out.println("    H: Expected Halt");
				System.out.println("    R: Restart");
				System.out.println("    D: Thread Dump");
				System.out.println("    U: Unexpected Halt (Simmulate crash)");
				System.out.println("    V: Access Violation (Actual crash)");
			}
		} catch (java.io.IOException e) {
			System.out.println("Unable to open the action server socket: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unable to open the action server socket: " + e.getMessage());
		}

		if (sampleWrapperService == null) {
			sampleWrapperService = new SampleWrapperService(args);
		}

		synchronized (this) {
			if (m_mainThread == null) {
				m_mainThread = new Thread(this, this.getClass().getName());
			}
			m_appArgs = args;
			m_mainThread.start();
			// Wait for two seconds to give the application a chance to have failed.
			try {
				this.wait(2000);
			} catch (InterruptedException e) {
				;
			}

			m_waitTimedOut = true;

			if (WrapperManager.isDebugEnabled()) {
				System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": start(args) end.  Main Completed=" + m_mainComplete + ", exitCode=" + m_mainExitCode);
			}
			return m_mainExitCode;
		}
	}

	@Override
	public int stop(int exitCode) {
		if (WrapperManager.isDebugEnabled()) {
			System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": stop(" + exitCode + ")");
		}

		Throwable t = null;
		try {

			sampleWrapperService.stop();

			if (m_mainThread != null && Thread.currentThread() != m_mainThread) {

				while (m_mainThread.isAlive()) {
					if (WrapperManager.isDebugEnabled()) {
						System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": stopping.  Waiting for Thread '" + m_mainThread.getName() + "' to complete.");
					}

					// Interrupt the thread.
					try {
						m_mainThread.interrupt();
					} catch (Exception e) {
						;
					}

					try {
						m_mainThread.join(1000);
					} catch (InterruptedException e) {
						;
					}
				}

				m_mainThread = null;
			}

			// Success
			return exitCode;
		} catch (Exception e) {
			t = e;
		}

		// If we get here, then an error was thrown.
		System.out.println("Encountered an error running stop main: " + t);

		// We should print a stack trace here, because in the case of an
		// InvocationTargetException, the user needs to know what exception
		// their app threw.
		t.printStackTrace();

		// Master thread
		m_mainThread = null;

		// Return a failure exit code
		return 1;
	}

	@Override
	public void run() {
		Throwable t = null;
		try {
			if (WrapperManager.isDebugEnabled()) {
				System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": executing runService method");
			}

			sampleWrapperService.run();

			if (WrapperManager.isDebugEnabled()) {
				System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": runService method completed");
			}

			synchronized (this) {
				// Let the start() method know that the runService method
				// returned, in case it is
				// still waiting.
				m_mainComplete = true;
				this.notifyAll();
			}

			return;
		} catch (Exception e) {
			t = e;
		}

		// If we get here, then an error was thrown. If this happened quickly
		// enough, the start method should be allowed to shut things down.
		System.out.println();
		System.out.println(ConfigurationManager.getProperty(ConfigurationManager.SERVICE_NAME) + ": Encountered an error running runService method: " + t);

		// We should print a stack trace here, because in the case of an
		// InvocationTargetException, the user needs to know what exception
		// their app threw.
		t.printStackTrace();

		synchronized (this) {
			if (m_waitTimedOut) {
				// Shut down here.
				WrapperManager.stop(1);
				return; // Will not get here.
			} else {
				// Let start method handle shutdown.
				m_mainComplete = true;
				m_mainExitCode = new Integer(1);
				this.notifyAll();
				return;
			}
		}
	}

	public static void main(String[] args) {
		m_appArgs = args;
		/* Create the WrapperSimpleApp */
		SampleWrapperMain app = new SampleWrapperMain();

		/*
		 * Start the application. If the JVM was launched from the native Wrapper then the application will wait for the
		 * native Wrapper to call the application's start method. Otherwise the start method will be called immediately.
		 */
		WrapperManager.start(app, args);

		// This thread ends, the WrapperManager will start the application after
		// the Wrapper has been propperly initialized by calling the start
		// method above.
	}

}
