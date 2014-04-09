package com.sample.architecture.exceptions;

public enum ECodeExceptions {
	/**
	 * GLOBAL_RESULT
	 */
	UNKNOWN_ERROR(-1),
	/***/
	UNSECCESFUL_PROCESS(0),
	/***/
	SUCCESSFUL_PROCESS(1),
	/***/
	WARNING_PROCESS(2);

	private int code;

	private ECodeExceptions(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

}
