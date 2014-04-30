package com.design.patterns.structural.bridge;

public class AbstractImpl implements IAbstract {
	private IImplementer implementer;

	public AbstractImpl(IImplementer implementer) {
		this.implementer = implementer;
	}

	public void operacion() {
		implementer.operacion();
	}
}
