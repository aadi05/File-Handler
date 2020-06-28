package com.app.exceptions;

public class EmptyListException extends RuntimeException{

	public EmptyListException() {
		super();
	}

	public EmptyListException(final String message) {
		super(message);
	}

}
