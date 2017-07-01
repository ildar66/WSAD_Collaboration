package com.hps.july.terrabyte.imp.essence;

import com.hps.beeline.imageLoader.helper.ImageHelper;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:15:22
 */
public class PositionImageDirInfo extends PositionDirInfo {

    public PositionImageDirInfo(File dir) {
        super(dir);
    }

    public IDirInfo [] getDirList() {
        ImageDirInfo [] list = new ImageDirInfo[0];
        if(getDir() != null) {
            int countDir = 0;
            File [] fileList = getDir().listFiles();
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) countDir++;
            }
            list = new ImageDirInfo [countDir];
            countDir = 0;
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) list[countDir++] = new ImageDirInfo(file);
            }
        }
        return list;
    }

    public boolean accept(File dir, String name) {
      String fileName = new File(name).getName();
      return ImageHelper.isImage(fileName);
    }
}
