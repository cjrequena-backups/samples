package com.design.patterns.structural.facade;

public class CPU {
	public void freeze() {
		System.out.println("freeze CPU");
	}

	public void jump(long position) {
		System.out.println("jump to " + position);
	}

	public void execute() {
		System.out.println("executing CPU");
	}
}
