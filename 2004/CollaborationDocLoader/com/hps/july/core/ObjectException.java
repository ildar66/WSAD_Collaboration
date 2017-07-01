package com.hps.july.core;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 21.09.2005
 * Time: 11:54:03
 */
public class ObjectException extends Exception {

    /**
     * -1 - ������ �������� �������
     * -2 - ������ ��������� �������
     * -254 - ������ ������ � ��
     * -255 - �������������� ������
     */
    private Integer code = null;

    public ObjectException() {
        super();
    }

    public ObjectException(Integer code, Throwable cause) {
        //super(cause);
        this.code = code;
    }

    public ObjectException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ObjectException(String message, Integer code, Throwable cause) {
        //super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
