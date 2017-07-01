/*
 *  $Id: NoSuchObjectException.java,v 1.1 2006/10/11 14:52:28 dkrivenko Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 *          18.09.2006 13:45:24
 */
public class NoSuchObjectException extends DatabaseException {
	private static final long serialVersionUID = 1L;

	public NoSuchObjectException() {
    }

    public NoSuchObjectException(String message) {
        super(message);
    }

    public NoSuchObjectException(Throwable cause) {
        super(cause);
    }

    public NoSuchObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
