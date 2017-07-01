package com.hps.july.terrabyte.imp.essence;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 14.07.2005
 * Time: 12:20:11
 */
public interface IFileInfo {

    public Integer getCreator();

    public void setCreator(Integer creator);

    public Integer getIdent();

    public void setIdent(Integer ident);

    public String getName();

    public void setName(String name);

    public Integer getType();

    public void setType(Integer type);

    public String getDescr();

    public void setDescr(String descr);

    public String getExphoto();

    public void setExphoto(String exphoto);

    public String getPhoto();

    public void setPhoto(String photo);

    public Integer getStoragePlace();

    public void setStoragePlace(Integer storagePlace);

    public boolean isActive();
    public void setActive(boolean active);

    public Integer getFileSize();
    public long getLastModified();
    public File getFile();


}
