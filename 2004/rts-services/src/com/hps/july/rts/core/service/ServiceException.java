package com.hps.july.rts.core.service;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 20.01.2006
 * Time: 16:23:45
 */
public class ServiceException extends Exception {

	private Throwable cause;
	
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
		super(cause.getMessage());
    	this.cause = cause;
        //super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        //super(message, cause);
		super(message);
		this.cause = cause;
    }
}
