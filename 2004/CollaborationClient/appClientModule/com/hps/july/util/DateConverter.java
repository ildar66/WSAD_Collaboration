package com.hps.july.util;


import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Date;

/**
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class DateConverter implements Converter {
    private Logger log = Logger.getLogger(DateConverter.class);

    public Object convert(Class clazz, Object object) {
/*
        log.debug("DateConverter.convert");
        log.debug("clazz = " + clazz);
        log.debug("object = " + object);
*/

        if (isNullOrEmpty(object)) {
            return null;
        }

        try {
            if (Date.class.equals(clazz)) {
                return Utils.string2Date((String) object);
            } else if (String.class.equals(clazz)) {
                return Utils.date2String((Date) object);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        throw new IllegalArgumentException("can't convert to class - " + clazz.getName());
    }

    private boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return "".equals(object);
        }

        return false;
    }
}