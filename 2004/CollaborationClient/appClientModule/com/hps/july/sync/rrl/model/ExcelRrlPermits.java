package com.hps.july.sync.rrl.model;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Модель данных из Excel файла
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class ExcelRrlPermits {
    private String siteNumber;
    private String address;
    private String vkVkr;
    private String equipmentType;
    private Integer count;
    private Integer siteNumberReverse;
    private String addressReverse;
    private String isWork;
    private String number;
    private Date date;
    private String numberCc;
    private Date dateCc;
    private Date endDateCc;
    private String attachedDocumentCc;
    private String numberRes;
    private Date dateRes;
    private Date endDateRes;
    private String numberTempRes;
    private Date dateTempRes;
    private String dateEndTempRes;


    public boolean isEmpty() {
        try {
            //TODO what the fuck???
            Field[] fields = this.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];

                if (!field.getName().startsWith("class$")) {
                    if (!field.getType().equals(String.class)) {
                        if(field.get(this) != null) {
                            //System.out.println("field = " + field.getName() + " class = " + field.getType().getName() + " value = \"" + field.get(this) + "\"");
                            return false;
                        }
                    } else if (field.get(this) != null && !"".equals(((String)field.get(this)).trim())) {
                        //System.out.println("field.get(this) = \"" + field.get(this) + "\"");
                        return false;
                    }
                }
            }

            return true;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVkVkr() {
        return vkVkr;
    }

    public void setVkVkr(String vkVkr) {
        this.vkVkr = vkVkr;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count != null && count.intValue() > 1) {
            throw new RuntimeException("Значение столбца количество не может быть больше 1");
        }
        this.count = count;
    }

    public Integer getSiteNumberReverse() {
        return siteNumberReverse;
    }

    public void setSiteNumberReverse(Integer siteNumberReverse) {
        this.siteNumberReverse = siteNumberReverse;
    }

    public String getAddressReverse() {
        return addressReverse;
    }

    public void setAddressReverse(String addressReverse) {
        this.addressReverse = addressReverse;
    }

    public String getIsWork() {
        return isWork;
    }

    public void setIsWork(String work) {
        isWork = work;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumberCc() {
        return numberCc;
    }

    public void setNumberCc(String numberCc) {
        this.numberCc = numberCc;
    }

    public Date getDateCc() {
        return dateCc;
    }

    public void setDateCc(Date dateCc) {
        this.dateCc = dateCc;
    }

    public Date getEndDateCc() {
        return endDateCc;
    }

    public void setEndDateCc(Date endDateCc) {
        this.endDateCc = endDateCc;
    }

    public String getAttachedDocumentCc() {
        return attachedDocumentCc;
    }

    public void setAttachedDocumentCc(String attachedDocumentCc) {
        this.attachedDocumentCc = attachedDocumentCc;
    }

    public String getNumberRes() {
        return numberRes;
    }

    public void setNumberRes(String numberRes) {
        this.numberRes = numberRes;
    }

    public Date getDateRes() {
        return dateRes;
    }

    public void setDateRes(Date dateRes) {
        this.dateRes = dateRes;
    }

    public Date getEndDateRes() {
        return endDateRes;
    }

    public void setEndDateRes(Date endDateRes) {
        this.endDateRes = endDateRes;
    }

    public String getNumberTempRes() {
        return numberTempRes;
    }

    public void setNumberTempRes(String numberTempRes) {
        this.numberTempRes = numberTempRes;
    }

    public Date getDateTempRes() {
        return dateTempRes;
    }

    public void setDateTempRes(Date dateTempRes) {
        this.dateTempRes = dateTempRes;
    }

    public String getDateEndTempRes() {
        return dateEndTempRes;
    }

    public void setDateEndTempRes(String dateEndTempRes) {
        this.dateEndTempRes = dateEndTempRes;
    }

    public String toString() {
        return "ExcelRrlPermits{" +
                "siteNumber='" + siteNumber + '\'' +
                ", address='" + address + '\'' +
                ", vkVkr='" + vkVkr + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", count=" + count +
                ", siteNumberReverse=" + siteNumberReverse +
                ", addressReverse='" + addressReverse + '\'' +
                ", isWork='" + isWork + '\'' +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", numberCc='" + numberCc + '\'' +
                ", dateCc=" + dateCc +
                ", endDateCc=" + endDateCc +
                ", attachedDocumentCc='" + attachedDocumentCc + '\'' +
                ", numberRes='" + numberRes + '\'' +
                ", dateRes=" + dateRes +
                ", endDateRes=" + endDateRes +
                ", numberTempRes='" + numberTempRes + '\'' +
                ", dateTempRes=" + dateTempRes +
                ", dateEndTempRes='" + dateEndTempRes + '\'' +
                '}';
    }
}
