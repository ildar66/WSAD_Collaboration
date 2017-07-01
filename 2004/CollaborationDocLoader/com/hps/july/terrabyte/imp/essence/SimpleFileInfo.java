package com.hps.july.terrabyte.imp.essence;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:57:30
 */
public class SimpleFileInfo extends FileInfo {

    private File file = null;

    public SimpleFileInfo(File file) {
        this.file = file;
        setName(file.getName());
        setType(new Integer(1));
    }

    public File getFile() {
        return file;
    }

    public Integer getFileSize() {
        return new Integer(new Long(file.length()).intValue());
    }

    public long getLastModified() {
        return this.file.lastModified();
    }
}
