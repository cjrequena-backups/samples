package com.design.patterns.creational.singleton;

public final class Singleton {

	private String name;

	// Private constructor prevents instantiation from other classes
	private Singleton() {
	}

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the first access to SingletonHolder.INSTANCE, not before.
	 */
	private static class SingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	public synchronized static Singleton getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
