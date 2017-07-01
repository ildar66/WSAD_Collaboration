package com.hps.july.terrabyte.imp.essence;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:15:22
 */
public class PositionDirInfo extends SimpleDirInfo {

    private Integer storageplace;
    private String number;

    private Integer gsmId;
    private Integer dampsId;
    private Integer wlanId;
    private boolean isPosition = true;

    public PositionDirInfo(File dir) {
        super(dir);

        if(getName().startsWith(FILE_TYPE_DAMPS)) {
            try {
                number = getName().substring(1, getName().indexOf("_"));
                dampsId = new Integer(number);
            } catch(Exception e) {}
        } else if(getName().startsWith(FILE_TYPE_WLAN)) {
            try {
                number = getName().substring(1, getName().indexOf("_"));
                wlanId = new Integer(number);
            } catch(Exception e) {}
        } else if(getName().charAt(0) >= '0' && getName().charAt(0) <= '9') {
            try {
                number = getName().substring(0, getName().indexOf("_"));
                gsmId = new Integer(number);
            } catch(Exception e) {}
        } else {
            isPosition = false;
        }
    }

    public Integer getStorageplace() {
        return storageplace;
    }

    public void setStorageplace(Integer storageplace) {
        this.storageplace = storageplace;
    }

    public Integer getDampsId() {
        return dampsId;
    }

    public void setDampsId(Integer dampsId) {
        this.dampsId = dampsId;
    }

    public Integer getGsmId() {
        return gsmId;
    }

    public void setGsmId(Integer gsmId) {
        this.gsmId = gsmId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getWlanId() {
        return wlanId;
    }

    public void setWlanId(Integer wlanId) {
        this.wlanId = wlanId;
    }

    public boolean isPosition() {
        return isPosition;
    }

    public void setPosition(boolean position) {
        isPosition = position;
    }
}
