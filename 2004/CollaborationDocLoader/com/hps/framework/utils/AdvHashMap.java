/*
 * User:  Mikhail E Skorik
 * Date: Jul 26, 2002
 * Time: 12:07:33 PM
 */
package com.hps.framework.utils;

import java.util.*;

public class AdvHashMap extends HashMap   {

    public static final String SPECIAL_VALUE_NULL = "null";

    public int size() {
        return super.size();
    }

    public void clear() {
        super.clear();
    }


    public boolean containsKey(Object key) {
        if( key == null )
            throw new RuntimeException("illegal argument value: key = [null]");

        return( super.get(key) != null );
    }

    /**
     * key = null -- not allowed
     * val = null -- allowed (!)
     *
     * if key is null - exception throws
     * key may be associated with either real null value or "null" string (treated as null value)
     */
    public Object put(Object key, Object val) {
        if( key == null )
            throw new RuntimeException("illegal argument value: key = null");

        super.put( key, (val == null) ? SPECIAL_VALUE_NULL : val );
        return null;
    }

    /**
     * key = null -- not allowed
     * val = null -- allowed (!)
     *
     * if key is not present in table - exception throws
     * key may be associated with null value
     */
    public Object get(Object key) {
        if( key == null )
            throw new RuntimeException("illegal argument value: key = [null]");

        Object val = null;
        if( (val = super.get(key)) == null )
            throw new RuntimeException("such key hasn't been stored: key = [" + key + "]");

        val = val.equals(SPECIAL_VALUE_NULL) ? null : val;
        return val;
    }

    public String getAsString(Object key) throws Exception {
        String val = (String)get(key);
        return (val == null) ? "" : val;
    }
    
    public void putAll(Map source) {
        Iterator keys = source.keySet().iterator();
        while(keys.hasNext()) {
            Object key = keys.next();
            Object value = source.get(key);
            put(key, value);
        }
    }

    public AdvHashMap() {
        super();
    }
}
