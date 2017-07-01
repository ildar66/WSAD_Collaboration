package com.hps.july.terrabyte.imp.essence;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 19.07.2005
 * Time: 12:05:25
 */
public class DescSortByNameAndLastModifiedDirInfo extends SortableDirInfo {

    public DescSortByNameAndLastModifiedDirInfo(File dir) {
        super(dir);
    }

    public String getSortType() {
        return "desc";
    }

    public IFileInfo [] getFiles() {
        SimpleFileInfo [] list = new SimpleFileInfo[0];
        if(getDir() != null) {
            File [] fileList = getDir().listFiles(this);
            int count = 0;
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(!file.isDirectory()) count++;
            }
            list = new SimpleFileInfo [count];
            count = 0;
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(!file.isDirectory()) list[count++] = new SimpleFileInfo(file);
            }
            Arrays.sort(list, new Comparator() {
                public int compare(Object o, Object o1) {
                    SimpleFileInfo a = (SimpleFileInfo)o;
                    SimpleFileInfo b = (SimpleFileInfo)o1;
                    if(a.getLastModified() > b.getLastModified())
                        return 1;
                    else if(a.getLastModified() == b.getLastModified())
                        return 0;
                    else
                        return -1;
                }
            });
            if(list.length > 0) list[list.length - 1].setActive(true);
        }
        return list;
    }
}
