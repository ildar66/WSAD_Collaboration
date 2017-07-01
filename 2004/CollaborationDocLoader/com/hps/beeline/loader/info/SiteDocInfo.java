package com.hps.beeline.loader.info;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 24.11.2004
 * Time: 14:39:26
 * To change this template use File | Settings | File Templates.
 */
public interface SiteDocInfo {
    Integer getStorageplace();

    Integer getSitedocfile();

    void setSitedocfile(Integer sitedocfile);

    void setBlobUpdatable(boolean blobUpdatable);

    boolean isBlobUpdatable();

    File getFile();
}
