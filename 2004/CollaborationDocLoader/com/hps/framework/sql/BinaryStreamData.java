package com.hps.framework.sql;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.03.2005
 * Time: 16:49:22
 * To change this template use File | Settings | File Templates.
 */
public class BinaryStreamData {
    private InputStream inputStream;
    private int length;

    public BinaryStreamData(InputStream inputStream, int length) {
        this.inputStream = inputStream;
        this.length = length;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public int getLength() {
        return length;
    }
}
