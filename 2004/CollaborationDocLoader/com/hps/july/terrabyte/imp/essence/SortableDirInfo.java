package com.hps.july.terrabyte.imp.essence;

import com.hps.beeline.imageLoader.helper.ImageHelper;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 19.07.2005
 * Time: 11:48:52
 */
public abstract class SortableDirInfo extends SimpleDirInfo {

    private String sortType = null;

    public SortableDirInfo(File dir) {
        super(dir);
    }

    public String getSortType() {
        return sortType;
    }

    
}
