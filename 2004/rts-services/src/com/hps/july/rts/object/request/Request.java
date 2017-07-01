package com.hps.july.rts.object.request;

import com.hps.july.rts.object.capacity.Capacity;
import com.hps.july.rts.object.channelsetting.ChannelSetting;
import com.hps.july.rts.object.RTSRequestType;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.storageplace.object.Beenet;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * RTS ("������ �� ���������� ������������ ��")
 * ������� ��������� ��� ���� ������
 * 
 *  @author abaykov
 *  Created on 23.12.2005
 */

public interface Request extends Serializable, Cloneable {

    /**
     * ����� ������ ��������� (SERIAL - autoincrement)
     * @return int
     *
     */
    public Integer getRequestId();

    /**
     * ����� ������
     * @param int
     */
//	private void setRequestId(int requestId);

    /**
     * ��������� ���� ������
     * K - �����������������
     * I - ������ ����������
     * E - ������������ ������
     * @return
     */
    public String getRequestType();


	/**
	 * �������� ��������
	 * @return {@link Person} 
	 * @see Person
	 */
	public Person getKeyManager();

	/**
	 * �������� ��������
	 * @param {@link Person}
	 */
	public void setKeyManager(Person person);

	/**
	 * ������������ ��������
	 * @return {@link Person} 
	 * @see Person
	 */
	public Person getRegManager();

	/**
	 * ������������ ��������
	 * @param {@link Person}
	 */
	public void setRegManager(Person person);

    /**
     * ������ ������
     * @return {@link RTSStatus}
     * @see RTSStatus
     */
    public RTSStatus getRtsStatus();
    /**
     * ������ ������
     * @param {@link RTSStatus}
     */
    public void setRtsStatus(RTSStatus status);

    /**
     * ��� ������
     * @return {@link com.hps.july.rts.object.RTSRequestType} RTSRequestType
     * @see RTSRequestType
     */
    public RTSRequestType getRtsRequestType();
    /**
     * ��� ������
     * @param {@link RTSRequestType}
     */
    public void setRtsRequestType(RTSRequestType rTSRequestType);

    /**
     * ���������� ������
     * @return {@link ChannelSetting} ChannelSetting
     * @see ChannelSetting
     */
    public ChannelSetting getChannelSetting();
    /**
     * ��� ������
     * @param {@link ChannelSetting}
     */
    public void setChannelSetting(ChannelSetting channelSetting);

    /**
     * ��� ������� �
     * @return String ��� ������� �
     */
    public Beenet getBeenetA();
    /**
     * ��� ������� �
     * @param String
     */
    public void setBeenetA(Beenet beenetA);

//	/**
//	 * ����� �
//	 * @return int
//	 */
//	public Integer getPositionA();
//	/**
//	 * ����� �
//	 * @param String
//	 */
//	public void setPositionA(Integer positionA);

    /**
     * ���� �
     * @return String
     */
    public String getPortA();
    /**
     * ����� �
     * @param String
     */
    public void setPortA(String portA);

    /**
     * ������� �
     * @return String
     */
    public String getExtraddrA();

//	/**
//	 * ����� �
//	 * @param String
//	 */
//	public void setExtraddrA(String extaddrA);

    /**
     * ��� ������� �
     * @return Beenet
     */
    public Beenet getBeenetB();
    /**
     * ��� ������� �
     * @param String
     */
    public void setBeenetB(Beenet beenetB);

//	/**
//	 * ����� �
//	 * @return int
//	 */
//	public Integer getPositionB();
//	/**
//	 * ����� �
//	 * @param int
//	 */
//	public void setPositionB(Integer positionB);

    /**
     * ���� �
     * @return String
     */
    public String getPortB();
    /**
     * ���� �
     * @param String
     */
    public void setPortB(String portB);

    /**
     * ������� �
     * @return String
     */
    public String getExtraddrB();
//	/**
//	 * ������� �
//	 * @param String
//	 */
//	public void setExtraddrB(String extaddrB);

    /**
     * ������������ ��� ������������� ������� � ������
     * @return int
     */
    public Integer getVersion();
    /**
     * ������������ ��� ������������� ������� � ������
     * @param int
     */
    public void setVersion(Integer version);

    /**
     * ����������
     * @return String
     */
    public String getComment();
    /**
     * ����������
     * @param String
     */
    public void setComment(String comment);


    /**
     * ����� ������ � �������� RTS - ����������� ���
     * ������� ���� ������ ��������������, �� ���������
     * @return String
     */
    public String getNumber();
    /**
     * ����� ������ � �������� RTS - ����������� ���
     * ������� ���� ������ ��������������, �� ���������
     * @param String
     */
    public void setNumber(String number);

    /*
      * ������� (������� TN_Capacities)
      */
    public void setCapacity(Capacity capacity);

    /*
      * ������� (������� TN_Capacities)
      */
    public Capacity getCapacity();


    public Timestamp getModified();
    public void setModified(Timestamp modified);

    public Timestamp getCreated();
    public void setCreated(Timestamp created);

    public Person getCreatedBy();
    public void setCreatedBy(Person createdBy);

    public Person getModifiedBy();
    public void setModifiedBy(Person modifiedBy);

}
