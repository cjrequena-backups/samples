package com.sample.handler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;


public class TestRejectedExecutionHandler implements RejectedExecutionHandler {
	
	private static Logger log = Logger.getLogger(TestRejectedExecutionHandler.class);
	
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		log.debug(runnable.toString() + " : has been rejected");
	}
}
