/*
 *  $Id: StoreObjectException.java,v 1.2 2006/11/23 16:50:40 vglushkov Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $
 *          18.09.2006 13:48:31
 */
public class StoreObjectException extends DatabaseException {
	private static final long serialVersionUID = 7713541032795767331L;

	public StoreObjectException() {
    }

    public StoreObjectException(String message) {
        super(message);
    }

    public StoreObjectException(Throwable cause) {
        super(cause);
    }

    public StoreObjectException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public StoreObjectException(Integer code, Throwable cause) {
		super(code, cause);
	}
    
	public StoreObjectException(String message, Integer code) {
		super(message, code);
    	
	}
    
}

