package com.hps.july.mail.text;

import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 23.05.2006
 * Time: 11:36:30
 */
public class TextFormatter {

    private Map parameters;

    public TextFormatter() {
        this.parameters = new HashMap();
    }

    public void addParameter(String name, Object value) {
        if(name != null && value != null)
            this.parameters.put(name, value);
    }

    public String format(InputSource source, Map parameters) {
        if(parameters != null) this.parameters = parameters;
        return format(source);
    }

    public String format(InputSource source) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.out.println("TextFormatter::format ["+source.getByteStream()+"]");
        //пока простым поиском
        if(source.getByteStream() != null) {
            InputStream stream = source.getByteStream();
            int c = 0;
            try {
                while(c != -1) {
                    c = stream.read();
                    if((char)c == '$') {
                        int cWait = stream.read();
                        if(cWait == -1) break;
                        if((char)cWait != '{') result.write(c);
                        else {
                            //read name
                            StringBuffer objectName = new StringBuffer();
                            int cObj = 0;
                            boolean bracketFound = false;
                            while(cObj != -1 && !bracketFound) {
                                cObj = stream.read();
                                if(cObj == -1) continue;
                                if((char)cObj != '}') objectName.append((char)cObj);
                                else bracketFound = true;
                            }
                            if(bracketFound) {
                                //found
                                Object realObj = ObjectUtil.obtainObject(objectName.toString(), this.parameters);
                                if(realObj != null) result.write(realObj.toString().getBytes());
                            } else {
                                //nothing found
                                result.write(c);
                                result.write((byte)'{');
                                result.write(objectName.toString().getBytes());
                            }
                        }
                    } else {
                        if(c != -1) result.write(c);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {}
                }
            }
        } else {

        }
        return new String(result.toByteArray());
    }
}
