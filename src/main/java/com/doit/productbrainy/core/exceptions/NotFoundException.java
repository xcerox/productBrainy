package com.doit.productbrainy.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {	
	private static final long serialVersionUID = 7271711255236144810L;
	private static final String DEFAULT_MESSAGE = "could not be found";
	
	public NotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, boolean withDefault) {
		super(message + DEFAULT_MESSAGE);
	}
	

}
