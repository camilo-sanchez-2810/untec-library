package com.untec.user.domain.exception;

public  class UserInactiveException extends RuntimeException {
	public UserInactiveException (String message) {
		super(message);
	}
}
