package com.hps.april.common.utils.jdbc;

/**
 * @author dimitry
 * Created on 02.12.2005
 */
public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String arg0) {
		super(arg0);
	}
	
	public DAOException(Exception ex){
		super(ex.getMessage(), ex);
	}

}
