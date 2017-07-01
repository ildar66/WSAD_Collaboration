package com.hps.july.terrabyte.imp.essence.status;

import com.hps.july.terrabyte.imp.essence.ExtensibleObject;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 08.11.2005
 * Time: 17:19:03
 */
public class BasestationStatusRequestAndPermit extends ExtensibleObject {
    private Integer ident;//Id определяем сами !

    private String type;//тип GSM-900 -"1"/GSM-1800 - "2"/DCS-900 -"3"/DSC-1800 - "4"
    private String bsNumber;//BS number
    private String bsName;//BS number
    private String supRegCode; //код региона определяем сами
    private Integer positionId;//Id определяем сами !
    private String on;//Включена
    private String declarated; //заявлено
    private String decisionNumber; //№ Заключения ГРЧЦ
    private String licenceNumber; //№ Разрешения

    private boolean is900 = false;
    private boolean is1800 = false;
    private boolean isGSM = false;
    private boolean isDCS = false;

    private int current9001800 = -1;
    private int currentGSMDCS = -1;

    private String nriNumber = null;
    private Integer used = null;

    private String regionName = null;

    public BasestationStatusRequestAndPermit() {
        ident = null;
        type = null;
        bsNumber = null;
        positionId = null;
        on = "";
        declarated ="";
        decisionNumber= "";
        licenceNumber = "";
        regionName = "";
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public String getBsNumber() {
        return bsNumber;
    }

    public void setBsNumber(String bsNumber) {
        this.bsNumber = bsNumber;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        if(isValid(decisionNumber)) this.decisionNumber = decisionNumber;
    }

    public String getDeclarated() {
        return declarated;
    }

    public void setDeclarated(String declarated) {
        if(isValid(declarated)) this.declarated = declarated;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        if(isValid(licenceNumber)) this.licenceNumber = licenceNumber;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        if(isValid(on)) this.on = on;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getSupRegCode() {
        return supRegCode;
    }

    public void setSupRegCode(String supRegCode) {
        this.supRegCode = supRegCode;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public boolean is1800() {
        return is1800;
    }

    public void set1800(boolean is1800) {
        this.is1800 = is1800;
    }

    public boolean is900() {
        return is900;
    }

    public void set900(boolean is900) {
        this.is900 = is900;
    }

    public boolean isDCS() {
        return isDCS;
    }

    public void setDCS(boolean DCS) {
        isDCS = DCS;
    }

    public boolean isGSM() {
        return isGSM;
    }

    public void setGSM(boolean GSM) {
        isGSM = GSM;
    }

    public int getCurrent9001800() {
        return current9001800;
    }

    public void setCurrent9001800(int current9001800) {
        this.current9001800 = current9001800;
    }

    public int getCurrentGSMDCS() {
        return currentGSMDCS;
    }

    public void setCurrentGSMDCS(int currentGSMDCS) {
        this.currentGSMDCS = currentGSMDCS;
    }

    public String getNriNumber() {
        return nriNumber;
    }

    public void setNriNumber(String nriNumber) {
        this.nriNumber = nriNumber;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
