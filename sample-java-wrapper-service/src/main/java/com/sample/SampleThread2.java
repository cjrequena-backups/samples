package com.sample;

public class SampleThread2 extends Thread {

	

	@Override
	public void run() {
		System.out.println(this.getClass().getCanonicalName());
	}

	

}
