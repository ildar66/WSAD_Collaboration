package com.hps.july.terrabyte.imp.essence;

import java.io.File;
import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 24.06.2005
 * Time: 12:23:52
 */
public class AbstractFileInfo extends FileInfo {

    private Integer fileSize;
    private Date lastModified = new Date();

    public AbstractFileInfo() {
        fileSize = null;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public File getFile() {
        throw new RuntimeException("Not implemented yet");
    }

    public long getLastModified() {
        return lastModified.getTime();
    }
}
