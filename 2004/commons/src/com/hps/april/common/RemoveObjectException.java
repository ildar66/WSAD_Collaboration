/*
 *  $Id: RemoveObjectException.java,v 1.2 2006/11/23 16:50:40 vglushkov Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $
 *          18.09.2006 13:49:17
 */
public class RemoveObjectException extends DatabaseException {
	private static final long serialVersionUID = 1L;

	public RemoveObjectException() {
    }

    public RemoveObjectException(String message) {
        super(message);
    }

    public RemoveObjectException(Throwable cause) {
        super(cause);
    }

    public RemoveObjectException(String message, Throwable cause) {
        super(message, cause);
    }
    
	public RemoveObjectException(Integer code, Throwable cause) {
		super(code, cause);
	}
    
	public RemoveObjectException(String message, Integer code) {
		super(message, code);
    	
	}
    
}
