package com.hps.july.terrabyte.imp.essence;

import com.hps.beeline.LoaderException;
import com.hps.beeline.imageLoader.helper.ImageHelper;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:15:22
 */
public class PositionSortableDirInfo extends PositionDirInfo {

    private Integer storageplace;
    private String number;

    private Integer gsmId;
    private Integer dampsId;
    private Integer wlanId;
    private boolean isPosition = true;

    public PositionSortableDirInfo(File dir) {
        super(dir);
    }

    public IDirInfo [] getDirList() {
        DescSortByNameAndLastModifiedDirInfo [] list = new DescSortByNameAndLastModifiedDirInfo[0];
        if(getDir() != null) {
            int countDir = 0;
            File [] fileList = getDir().listFiles();
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) countDir++;
            }
            list = new DescSortByNameAndLastModifiedDirInfo [countDir];
            countDir = 0;
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) list[countDir++] = new DescSortByNameAndLastModifiedDirInfo(file);
            }
        }
        return list;
    }
}
