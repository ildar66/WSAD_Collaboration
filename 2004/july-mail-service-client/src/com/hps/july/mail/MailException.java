package com.hps.july.mail;

public class MailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public MailException() {
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(Throwable cause) {
        //super(cause);
    }

    public MailException(String message, Throwable cause) {
        //super(message, cause);
		super(message);
    }

}
