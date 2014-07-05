package com.arshu.roommate.exception;

public class RMException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RMException(String message, Throwable throwable) {
		super(throwable);
	}
	public RMException(Throwable throwable) {
		super(throwable);
	}
	
	public RMException(String message) {
		super(message);
	}
	

}
