package com.hps.april.common.utils.jdbc;


/**
 * @author dimitry
 * Created on 27.12.2005
 */
public class SqlPropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param e
	 */
	public SqlPropertyException(Exception e) {
		super(e.getMessage());
	}

}
