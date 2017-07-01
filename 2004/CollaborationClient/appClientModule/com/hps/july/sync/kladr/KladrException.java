package com.hps.july.sync.kladr;

/**
 * Date: 05.04.2007
 * Time: 11:58:00
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class KladrException extends RuntimeException {
    Throwable cause;
    
    public KladrException(Throwable cause) {
        super();
    }

    public String toString() {
        return super.toString();
    }
}
