package com.hps.april.common.utils.jdbc;

/**
 * @author dimitry
 * Created on 02.12.2005
 */
public class UnknownDiscriminatorException extends DAOException {
	private static final long serialVersionUID = 1L;

	public UnknownDiscriminatorException() {
		super();
	}

	public UnknownDiscriminatorException(String arg0) {
		super(arg0);
	}

}
