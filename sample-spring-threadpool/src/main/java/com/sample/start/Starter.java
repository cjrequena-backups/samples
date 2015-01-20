package com.sample.start;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.sample.executor.ITestThreadPoolExecutorService;
import com.sample.handler.TestRejectedExecutionHandler;
import com.sample.monitor.IThreadPoolMonitorService;
import com.sample.task.TestTask;

public class Starter {
	
	private static Logger log = Logger.getLogger(TestRejectedExecutionHandler.class);
	
	IThreadPoolMonitorService threadPoolMonitorService;
	ITestThreadPoolExecutorService testThreadPoolExecutorService;
	
	public void start() {
		
		ThreadPoolExecutor executor = testThreadPoolExecutorService.createNewThreadPool();
		executor.allowCoreThreadTimeOut(true);
		
		threadPoolMonitorService.setExecutor(executor);
		
		Thread monitor = new Thread(threadPoolMonitorService);
		monitor.start();

		for(int i=1;i<10;i++) {
			executor.execute(new TestTask("Task"+i));
		}
		
		try	{
		    Thread.sleep(40000);
		} catch (Exception e)	{
		    log.error(e.getMessage());
		}
		
		for(int i=10;i<19;i++) {
			executor.execute(new TestTask("Task"+i));
		}
		
		executor.shutdown();
	}	

	public IThreadPoolMonitorService getThreadPoolMonitorService() {
		return threadPoolMonitorService;
	}

	public void setThreadPoolMonitorService(IThreadPoolMonitorService threadPoolMonitorService) {
		this.threadPoolMonitorService = threadPoolMonitorService;
	}

	public ITestThreadPoolExecutorService getTestThreadPoolExecutorService() {
		return testThreadPoolExecutorService;
	}

	public void setTestThreadPoolExecutorService(ITestThreadPoolExecutorService testThreadPoolExecutorService) {
		this.testThreadPoolExecutorService = testThreadPoolExecutorService;
	}
}
