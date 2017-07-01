package com.hps.july.terrabyte.imp.essence;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:14:46
 */
public abstract class DirInfo {

    public static final String FILE_TYPE_DAMPS = "a";
    public static final String FILE_TYPE_WLAN = "w";

    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
