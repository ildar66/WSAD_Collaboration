package com.hps.july.rts.object.request;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

import com.hps.april.auth.object.Person;
import com.hps.july.storageplace.object.Beenet;
import com.hps.july.storageplace.object.Equipment;
import com.hps.july.storageplace.object.Position;

public abstract class AbstractRequest implements Request {

    private Integer version;

    protected Integer requestId;
    private String number;

	protected Equipment equipmentA;
	protected Equipment equipmentB;

	protected Position positionA;
	protected Position positionB;

    // Дата и время последней модификации
    private Timestamp modified;
    // Дата и время создания
    private Timestamp created;
    // Кто создал (people)
    private Person createdBy;
    // Кто изменил (people)
    private Person modifiedBy;

    private Beenet beenetA;
    private Beenet beenetB;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getRequestId() {
        return requestId;
    }
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Equipment getEquipmentA() {
        return equipmentA;
    }
    public void setEquipmentA(Equipment equipmentA) {
        this.equipmentA = equipmentA;
    }
    public Equipment getEquipmentB() {
        return equipmentB;
    }
    public void setEquipmentB(Equipment equipmentB) {
        this.equipmentB = equipmentB;
    }

    public Beenet getBeenetA() {
        return beenetA;
    }

    public void setBeenetA(Beenet beenetA) {
        this.beenetA = beenetA;
    }

    public String getShortBeenetIdA() {
        String beenetId = getExtraBeenetInfo(getEquipmentA().getPosition());
        return getShortForm(beenetId, 8);
    }
    public Beenet getBeenetB() {
        return beenetB;
    }

    public void setBeenetB(Beenet beenetB) {
        this.beenetB = beenetB;
    }

    public String getShortBeenetIdB() {
        String beenetId = getExtraBeenetInfo(getEquipmentB().getPosition());
        return getShortForm(beenetId, 8);
    }


    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public Person getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Person modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    //-- protected
    protected String getExtraEquipmentInfo(Equipment equipment){
        String result = "";
        if (equipment != null){
            Position position = equipment.getPosition();

            result = equipment.getFullName().trim() + " ; " +
                position.getAddress().trim() + " ; " +
                getExtraBeenetInfo(position);
        }

        return result;
    }

    protected String getExtraEquipmentInfo(Position position){
        String result = "";
        if (position != null){

            result = "[Устройство не определено] Позиция: " +
                position.getAddress().trim() + " ; " +
                getExtraBeenetInfo(position);
        }

        return result;
    }

    protected String getExtraBeenetInfo(Position position){
        String result = " ";

        if (position != null) {
            Collection beenetList = position.getBeenetList();
            for (Iterator beenets = beenetList.iterator(); beenets.hasNext();) {
                Beenet beenet = (Beenet) beenets.next();
                result += beenet.getCode().trim() + " ";
            }
        }

        return result;
    }

    protected String getShortForm(String value, int count) {
        StringBuffer result = new StringBuffer("");
        if(value != null && count > 0) {
            if(value.length() > count) {
                result.append(value.substring(0, count)).append("...");
            } else result.append(value);
        }
        return result.toString();
    }

    public String toString() {
           return this.getClass().getName() + ":[id=" + getRequestId() + ",number=" + getNumber() + ",version=" + getVersion() + "]";
    }

	public Position getPositionA() {
		if(getEquipmentA()!=null) return getEquipmentA().getPosition();
		else return positionA;
	}
	public void setPositionA(Position positionA) {
		this.positionA = positionA;
	}
	public Position getPositionB() {
		if(getEquipmentB()!=null) return getEquipmentB().getPosition();
		else return positionB;
	}
	public void setPositionB(Position positionB) {
		this.positionB = positionB;
	}


}
