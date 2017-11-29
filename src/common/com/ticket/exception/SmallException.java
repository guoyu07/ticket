package com.ticket.exception;

public class SmallException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SmallException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmallException(String message) {
		super(message);
	}

	public SmallException(Throwable cause) {
		super(cause);
	}

	
}
