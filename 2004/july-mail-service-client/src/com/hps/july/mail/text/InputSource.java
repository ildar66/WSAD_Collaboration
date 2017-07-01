package com.hps.july.mail.text;

import java.io.Reader;
import java.io.InputStream;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 23.05.2006
 * Time: 12:40:22
 */
public class InputSource {

    private InputStream byteStream;
    private Reader characterStream;
    private String encoding;

    public InputSource() {
    }


    public InputSource(InputStream byteStream) {
        setByteStream(byteStream);
    }


    public InputSource(Reader characterStream) {
        setCharacterStream(characterStream);
    }


    public void setByteStream(InputStream byteStream) {
        this.byteStream = byteStream;
    }


    public InputStream getByteStream() {
        return byteStream;
    }


    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }


    public String getEncoding() {
        return encoding;
    }


    public void setCharacterStream(Reader characterStream) {
        this.characterStream = characterStream;
    }


    public Reader getCharacterStream() {
        return characterStream;
    }
}
