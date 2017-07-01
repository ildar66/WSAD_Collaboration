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
 * RTS ("Заявки на расширение региональной ТС")
 * Базовый интерфейс для всех заявок
 * 
 *  @author abaykov
 *  Created on 23.12.2005
 */

public interface Request extends Serializable, Cloneable {

    /**
     * Номер Заявки системный (SERIAL - autoincrement)
     * @return int
     *
     */
    public Integer getRequestId();

    /**
     * Номер Заявки
     * @param int
     */
//	private void setRequestId(int requestId);

    /**
     * Получение типа заявки
     * K - консолидированная
     * I - заявка инициатора
     * E - элементарная заявка
     * @return
     */
    public String getRequestType();


	/**
	 * Ключевой менеджер
	 * @return {@link Person} 
	 * @see Person
	 */
	public Person getKeyManager();

	/**
	 * Ключевой менеджер
	 * @param {@link Person}
	 */
	public void setKeyManager(Person person);

	/**
	 * Региональный менеджер
	 * @return {@link Person} 
	 * @see Person
	 */
	public Person getRegManager();

	/**
	 * Региональный менеджер
	 * @param {@link Person}
	 */
	public void setRegManager(Person person);

    /**
     * Статус заявки
     * @return {@link RTSStatus}
     * @see RTSStatus
     */
    public RTSStatus getRtsStatus();
    /**
     * Статус заявки
     * @param {@link RTSStatus}
     */
    public void setRtsStatus(RTSStatus status);

    /**
     * Тип Заявки
     * @return {@link com.hps.july.rts.object.RTSRequestType} RTSRequestType
     * @see RTSRequestType
     */
    public RTSRequestType getRtsRequestType();
    /**
     * Тип Заявки
     * @param {@link RTSRequestType}
     */
    public void setRtsRequestType(RTSRequestType rTSRequestType);

    /**
     * Назначение канала
     * @return {@link ChannelSetting} ChannelSetting
     * @see ChannelSetting
     */
    public ChannelSetting getChannelSetting();
    /**
     * Тип канала
     * @param {@link ChannelSetting}
     */
    public void setChannelSetting(ChannelSetting channelSetting);

    /**
     * Код позиции А
     * @return String Код позиции А
     */
    public Beenet getBeenetA();
    /**
     * Код позиции А
     * @param String
     */
    public void setBeenetA(Beenet beenetA);

//	/**
//	 * Адрес А
//	 * @return int
//	 */
//	public Integer getPositionA();
//	/**
//	 * Адрес А
//	 * @param String
//	 */
//	public void setPositionA(Integer positionA);

    /**
     * Порт А
     * @return String
     */
    public String getPortA();
    /**
     * Адрес А
     * @param String
     */
    public void setPortA(String portA);

    /**
     * Контакт А
     * @return String
     */
    public String getExtraddrA();

//	/**
//	 * Адрес А
//	 * @param String
//	 */
//	public void setExtraddrA(String extaddrA);

    /**
     * Код позиции Б
     * @return Beenet
     */
    public Beenet getBeenetB();
    /**
     * Код позиции Б
     * @param String
     */
    public void setBeenetB(Beenet beenetB);

//	/**
//	 * Адрес Б
//	 * @return int
//	 */
//	public Integer getPositionB();
//	/**
//	 * Адрес Б
//	 * @param int
//	 */
//	public void setPositionB(Integer positionB);

    /**
     * Порт Б
     * @return String
     */
    public String getPortB();
    /**
     * Порт Б
     * @param String
     */
    public void setPortB(String portB);

    /**
     * Контакт Б
     * @return String
     */
    public String getExtraddrB();
//	/**
//	 * Контакт Б
//	 * @param String
//	 */
//	public void setExtraddrB(String extaddrB);

    /**
     * Версионность для разграничения доступа к Заявке
     * @return int
     */
    public Integer getVersion();
    /**
     * Версионность для разграничения доступа к Заявке
     * @param int
     */
    public void setVersion(Integer version);

    /**
     * Примечание
     * @return String
     */
    public String getComment();
    /**
     * Примечание
     * @param String
     */
    public void setComment(String comment);


    /**
     * Номер Заявки в терминах RTS - формируются для
     * каждого типа Заявок самостоятельно, по алгоритму
     * @return String
     */
    public String getNumber();
    /**
     * Номер Заявки в терминах RTS - формируются для
     * каждого типа Заявок самостоятельно, по алгоритму
     * @param String
     */
    public void setNumber(String number);

    /*
      * Ёмкость (таблица TN_Capacities)
      */
    public void setCapacity(Capacity capacity);

    /*
      * Ёмкость (таблица TN_Capacities)
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
