package com.hps.framework.exception;

import com.hps.framework.exception.BaseException;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.01.2005
 * Time: 17:51:07
 * To change this template use File | Settings | File Templates.
 */
public class NonFatalException extends BaseException {

    public NonFatalException(String s) {
        super(s);
    }
}
