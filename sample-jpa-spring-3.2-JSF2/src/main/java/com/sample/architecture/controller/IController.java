package com.sample.architecture.controller;

public interface IController<T> {
	
	public String returnToParentController() throws Exception;

}
