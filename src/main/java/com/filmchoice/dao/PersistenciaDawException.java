package com.filmchoice.dao;

import br.edu.ifpb.es.daw.DawException;

public class PersistenciaDawException extends DawException {

	private static final long serialVersionUID = 7159282553688713660L;

	public PersistenciaDawException(String message) {
		super(message);
	}

	public PersistenciaDawException(String message, Throwable cause) {
		super(message, cause);
	}

}
