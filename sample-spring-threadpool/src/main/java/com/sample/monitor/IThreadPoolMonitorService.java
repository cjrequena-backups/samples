package com.sample.monitor;

import java.util.concurrent.ThreadPoolExecutor;

public interface IThreadPoolMonitorService extends Runnable {

	public void monitorThreadPool();
	
	public ThreadPoolExecutor getExecutor();
	
	public void setExecutor(ThreadPoolExecutor executor);
	
}
