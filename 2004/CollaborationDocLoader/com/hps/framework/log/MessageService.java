/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 25.03.2005
 * Time: 11:26:02
 * To change this template use File | Settings | File Templates.
 */
package com.hps.framework.log;

import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;

public class MessageService {
    private static MessageService ourInstance = new MessageService();

    public static MessageService getInstance() {
        return ourInstance;
    }

    private MessageService() {
    }

    private String message = null;

    public void init() {
        message = null;
    }

    public void addMessage(String message) {
        this.message = message;
    }

    public boolean hasMessage() {
        return message!=null;
    }

    public String getMessage() {
        return message;
    }

    public void checkForErrors() throws BaseException {
        String msg = getMessage();
        message = null;
        if(msg!=null) {            
            throw new BaseException(msg);
        }
    }
}
