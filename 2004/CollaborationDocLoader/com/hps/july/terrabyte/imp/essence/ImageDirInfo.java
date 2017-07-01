package com.hps.july.terrabyte.imp.essence;

import com.hps.beeline.imageLoader.helper.ImageHelper;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:33:23
 */
public class ImageDirInfo extends SimpleDirInfo {


    public ImageDirInfo(File dir) {
        super(dir);
    }

    public boolean accept(File dir, String name) {
      //String fileName = new File(name).getName();
      return ImageHelper.isImage(name);
    }
}
