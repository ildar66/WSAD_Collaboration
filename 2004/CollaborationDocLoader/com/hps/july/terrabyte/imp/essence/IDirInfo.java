package com.hps.july.terrabyte.imp.essence;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 14.07.2005
 * Time: 12:18:25
 */
public interface IDirInfo {

    public static final String FILE_TYPE_DAMPS = "a";
    public static final String FILE_TYPE_WLAN = "w";

    public String getName();

    public Integer getNRIType();

    public IDirInfo [] getDirList();

    public IFileInfo [] getFiles();

}
