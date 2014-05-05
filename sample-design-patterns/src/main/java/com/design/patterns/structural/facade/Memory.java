package com.design.patterns.structural.facade;

public class Memory {
	public void load(long position, byte[] data) {
		System.out.println("load position" + position + " data " + data);
	}
}
