package com.hps.july.terrabyte.imp.essence.status;

import com.hps.july.terrabyte.imp.essence.ExtensibleObject;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 08.11.2005
 * Time: 17:19:03
 */
public class RRLStatusRequestAndPermit extends ExtensibleObject {
    private Integer ident;//Id определяем сами !

    private String type;//тип GSM-900 -"1"/GSM-1800 - "2"/DCS-900 -"3"/DSC-1800 - "4"
    private String siteNumber;//BS number
    private String siteNumber1;//BS number
    private String siteNumber2;//BS number
    private String address1; //
    private String address2; //

    private String interval;//BS number
    private String supRegCode; //код региона определяем сами
    private String regionName; //город или область
    private Integer positionId1;//Id определяем сами !
    private Integer positionId2;//Id определяем сами !
    private Integer air; //эфир
    private Integer annul; //аннулирована

    private Integer range; //диапазон
    private String equipment; //оборудование
    private String rangeNumber; //оборудование


    private Integer on;//Включена
    private String declarated; //заявлено
    private String decisionNumber; //№ Заключения ГРЧЦ
    private String licenceNumber; //№ Разрешения

    private String nriNumber = null;
    private Integer used = null;

    private String request = null;

    private Integer wasError = new Integer(0);

    private Integer filial = null;

    public RRLStatusRequestAndPermit() {
        ident = null;
        type = null;
        siteNumber = null;
        positionId1 = null;
        on = new Integer(0);
        air = new Integer(0);
        annul = new Integer(0);
        declarated ="";
        decisionNumber= "";
        licenceNumber = "";
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
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

    public Integer getOn() {
        return on;
    }

    public void setOn(Integer on) {
        this.on = on;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPositionId1() {
        return positionId1;
    }

    public void setPositionId1(Integer positionId1) {
        this.positionId1 = positionId1;
    }

    public String getSupRegCode() {
        return supRegCode;
    }

    public void setSupRegCode(String supRegCode) {
        this.supRegCode = supRegCode;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
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

    public String getSiteNumber1() {
        return siteNumber1;
    }

    public void setSiteNumber1(String siteNumber1) {
        this.siteNumber1 = siteNumber1;
    }

    public String getSiteNumber2() {
        return siteNumber2;
    }

    public void setSiteNumber2(String siteNumber2) {
        this.siteNumber2 = siteNumber2;
    }

    public Integer getPositionId2() {
        return positionId2;
    }

    public void setPositionId2(Integer positionId2) {
        this.positionId2 = positionId2;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getAnnul() {
        return annul;
    }

    public void setAnnul(Integer annul) {
        this.annul = annul;
    }

    public Integer getAir() {
        return air;
    }

    public void setAir(Integer air) {
        this.air = air;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRangeNumber() {
        return rangeNumber;
    }

    public void setRangeNumber(String rangeNumber) {
        this.rangeNumber = rangeNumber;
    }

    public Integer getWasError() {
        return wasError;
    }

    public void setWasError(Integer wasError) {
        this.wasError = wasError;
    }

    public Integer getFilial() {
        return filial;
    }

    public void setFilial(Integer filial) {
        this.filial = filial;
    }
}
