package com.rlima.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{ //classe de excecao personalizada
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg);
	}
	

}
