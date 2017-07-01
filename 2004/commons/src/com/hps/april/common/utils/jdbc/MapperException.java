package com.hps.april.common.utils.jdbc;

/**
 * @author dimitry
 * Created on 16.01.2006
 */
public class MapperException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 */
	public MapperException(String message) {
		super(message);
	}

}
