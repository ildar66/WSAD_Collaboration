/*
 *  $Id: CreateObjectException.java,v 1.2 2006/11/23 16:50:40 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $
 *          18.09.2006 13:47:37
 */
public class CreateObjectException extends DatabaseException {
	private static final long serialVersionUID = 1L;

	public CreateObjectException() {
    }

    public CreateObjectException(String message) {
        super(message);
    }

    public CreateObjectException(Throwable cause) {
        super(cause);
    }

    public CreateObjectException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public CreateObjectException(Integer code, Throwable cause) {
		super(code, cause);
	}
    
	public CreateObjectException(String message, Integer code) {
		super(message, code);
    	
	}
    
}
