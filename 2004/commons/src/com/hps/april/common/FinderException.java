/*
 *  $Id: FinderException.java,v 1.1 2006/10/11 14:52:28 dkrivenko Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.april.common;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 *          18.09.2006 13:46:53
 */
public class FinderException extends DatabaseException {
	private static final long serialVersionUID = 1L;

	public FinderException() {
    }

    public FinderException(String message) {
        super(message);
    }

    public FinderException(Throwable cause) {
        super(cause);
    }

    public FinderException(String message, Throwable cause) {
        super(message, cause);
    }
}
