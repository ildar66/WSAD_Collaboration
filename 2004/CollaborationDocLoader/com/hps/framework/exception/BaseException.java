package com.hps.framework.exception;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 05.11.2004
 * Time: 16:21:30
 * To change this template use File | Settings | File Templates.
 */
public class BaseException extends Exception{
    private Exception originalException = null;
    public BaseException(String s) {
        super(s);
    }

    public BaseException(String s, Exception originalException) {
        super(s);
        this.originalException = originalException;
    }

    public Exception getOriginalException() {
        return originalException;
    }

}
