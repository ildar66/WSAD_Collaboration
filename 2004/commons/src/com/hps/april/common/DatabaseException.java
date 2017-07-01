/*
 *  $Id: DatabaseException.java,v 1.2 2006/11/23 16:50:40 vglushkov Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $
 *          18.09.2006 13:43:45
 */
public class DatabaseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	protected int code;

	public DatabaseException() {
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DatabaseException(Integer code, Throwable cause) {
    	
    }
    
	public DatabaseException(String msg, Integer code) {
    	
	}
    
    
}
