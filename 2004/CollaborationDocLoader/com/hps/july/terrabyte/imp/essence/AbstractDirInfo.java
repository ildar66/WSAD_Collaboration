package com.hps.july.terrabyte.imp.essence;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:14:46
 */
public abstract class AbstractDirInfo implements IDirInfo, FilenameFilter {

    private static HashMap types;

    static {
        types = new HashMap();
        types.put("Схема_кабельных_связей", new Integer(4));
        types.put("Энергопотребление", new Integer(5));
        types.put("СанПаспорт", new Integer(6));
        types.put("ТУ", new Integer(7));
        types.put("СитПлан", new Integer(8));
        types.put("Списки", new Integer(9));
        types.put("Маршрутные_карты", new Integer(10));
        types.put("Кондиционеры", new Integer(11));
        types.put("РРС", new Integer(12));
    }

    private String name;
    private File dir;

    public String getName() {
        return name;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNRIType() {
        return (Integer)types.get(getName());
    }

    public IDirInfo [] getDirList() {
        SimpleDirInfo [] list = new SimpleDirInfo[0];
        if(getDir() != null) {
            int countDir = 0;
            File [] fileList = getDir().listFiles();
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) countDir++;
            }
            list = new SimpleDirInfo [countDir];
            countDir = 0;
            for(int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if(file.isDirectory()) list[countDir++] = new SimpleDirInfo(file);
            }
        }
        return list;
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
        }
        return list;
    }
}
