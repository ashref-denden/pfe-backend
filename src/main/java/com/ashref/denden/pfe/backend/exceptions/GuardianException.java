package com.ashref.denden.pfe.backend.exceptions;

public class GuardianException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorCode;

	public GuardianException(String s, String errorCode) {
		super(s);
		this.errorCode = errorCode;
	}

	public GuardianException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = null;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
