package com.hps.july.terrabyte.imp.essence;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:33:23
 */
public class SimpleDirInfo extends AbstractDirInfo {


    public SimpleDirInfo(File dir) {
        setDir(dir);
        setName(dir.getName());
    }

    public boolean accept(File dir, String name) {
        String changedName = name.toLowerCase();
        boolean result = (!changedName.endsWith(".bak") && !changedName.endsWith(".tmp")
                && !changedName.endsWith(".log") && changedName.indexOf("~") == -1);
        return result;
    }
}
