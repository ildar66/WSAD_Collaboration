package com.hps.july.terrabyte.imp.essence;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 08.11.2005
 * Time: 17:21:07
 */
public class ExtensibleObject {

    public boolean isValid(String s) {
        return (s != null && s.trim().length() > 0);
    }
}
