package com.hps.july.rts.object.request;

import java.util.Date;

import com.hps.july.rts.object.capacity.Capacity;
import com.hps.july.rts.object.channelinterface.ChannelInterface;
import com.hps.july.rts.object.channelinterfacetype.ChannelInterfaceType;
import com.hps.july.rts.object.channelsetting.ChannelSetting;
import com.hps.july.rts.object.channeltype.ChannelType;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.july.rts.service.RTSRequestTypeService;
import com.hps.july.rts.service.RTSStatusService;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.object.RTSRequestType;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.storageplace.object.Equipment;

/**
 * RTS ("Заявки на расширение региональной ТС")
 *
 *  @author abaykov
 *  Created on 26.12.2005
 */
public class InitiatorRequest extends AbstractRequest implements Request {

    private Integer requestId;

	private Person keyManager;

	private Person regManager;

    //vad - ident of status and requesttype
    // (must be wrapped in visual object )
    // translated in RTSStatus and RTSRequestType
    private Integer statusId;
    private Integer rtsRequestTypeId;

    private RTSStatus rtsStatus;
    private RTSRequestType rtsRequestType;
    private ChannelSetting channelSetting;
//	private String beenetIdA;
//	private String extraddrA;
//    private Integer positionA;
//	private Integer positionB;
//    private Integer transPositionA;
//	private Integer transPositionB;
//	private String extraddrB;
    private String initLabel;
    private String comment;

    // assisiation to Equipment
    private Equipment equipmentA;
    private Equipment equipmentB;

//	private Equipment transEquipmentA;
//	private Equipment transEquipmentB;

//	private Integer equipmentAId;
//	private Integer equipmentBId;
//
//	private Integer transEquipmentAId;
//	private Integer transEquipmentBId;

    // TODO порты зависят от equipment?
    private String portA;
    private String portB;
//    private String transPortA;
//	private String transPortB;



//    private Integer equipmentB;
//    private Integer equipmentA;
//    private Integer transEquipmentB;
//    private Integer transEquipmentA;

/*
    private String equipmentNameB;
    private String equipmentNameA;

    private String positionAddressB;
    private String positionAddressA;
*/

    //комментарий представителя инициатора, в случае отправки заявки на доработку !
    private String revisionComment;

    private ConsolidatedRequest consolidatedRequest;

    public String getRequestType() {
        return "I";
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getRequestId()
      */
    public Integer getRequestId() {
        return requestId;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setRequestId(java.lang.String)
      */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

	/* (non-Javadoc)
	  * @see com.hps.july.rts.object.request.Request#getKeyManager()
	  */
	public Person getKeyManager() {
		return keyManager;
	}

	/* (non-Javadoc)
	  * @see com.hps.july.rts.object.request.Request#setKeyManager(com.hps.april.auth.object.Person)
	  */
	public void setKeyManager(Person person) {
		this.keyManager = person;
	}

	/* (non-Javadoc)
	  * @see com.hps.july.rts.object.request.Request#getRegManager()
	  */
	public Person getRegManager() {
		return regManager;
	}

	/* (non-Javadoc)
	  * @see com.hps.july.rts.object.request.Request#setRegManager(com.hps.april.auth.object.Person)
	  */
	public void setRegManager(Person person) {
		this.regManager = person;
	}

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
        setRtsStatus(statusId);
    }



    public Integer getRtsRequestTypeId() {
        return rtsRequestTypeId;
    }

    public void setRtsRequestTypeId(Integer rtsRequestTypeId) {
        this.rtsRequestTypeId = rtsRequestTypeId;
        setRtsRequestType(rtsRequestTypeId);
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getStatus()
      */
    public RTSStatus getRtsStatus() {
        if(rtsStatus == null) {
            try {
                RTSStatusService service = (RTSStatusService)ServiceFactory.getService(ServiceFactory.RTS_STATUS);
                rtsStatus = service.findRTSStatus(statusId);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return rtsStatus;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setStatus(com.hps.july.rts.object.RTSStatus)
      */
    public void setRtsStatus(RTSStatus status) {
        setStatusId(status.getId());
    }

    public void setRtsStatus(Integer status) {
        try {
            RTSStatusService service = (RTSStatusService)ServiceFactory.getService(ServiceFactory.RTS_STATUS);
            this.rtsStatus = service.findRTSStatus(status);
            if(rtsStatus != null) this.statusId = status;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getRTSRequestType()
      */
    public RTSRequestType getRtsRequestType() {
        try {
            RTSRequestTypeService service= (RTSRequestTypeService)ServiceFactory.
                    getService(ServiceFactory.RTS_REQUEST_TYPE);
            rtsRequestType = service.findRTSRequestType(this.rtsRequestTypeId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return rtsRequestType;
    }



    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setRTSRequestType(com.hps.july.rts.object.RTSRequestType)
      */
    public void setRtsRequestType(RTSRequestType type) {
        setRtsRequestTypeId(type.getId());
    }

    public void setRtsRequestType(Integer type) {
        try {
            RTSRequestTypeService service= (RTSRequestTypeService)ServiceFactory.
                    getService(ServiceFactory.RTS_REQUEST_TYPE);
            rtsRequestType = service.findRTSRequestType(type);
            if(rtsRequestType != null) this.rtsRequestTypeId = type;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getChannelSetting()
      */
    public ChannelSetting getChannelSetting() {
        return channelSetting;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setChannelSetting(com.hps.july.rts.object.channelsetting.ChannelSetting)
      */
    public void setChannelSetting(ChannelSetting channelSetting) {
        this.channelSetting = channelSetting;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getBeenetIdA()
      */
    public String getBeenetIdA() {
        return getExtraBeenetInfo(getEquipmentA().getPosition());

//        String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getBeenet(getPositionA());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setBeenetIdA(java.lang.String)
//	 */
//	public void setBeenetIdA(String beenetIdA) {
//		this.beenetIdA = beenetIdA;
//	}

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#getPositionA()
//	 */
//	public Integer getPositionA() {
//		return this.positionA;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setPositionA(int)
//	 */
//	public void setPositionA(Integer positionA) {
//		this.positionA = positionA;
//	}

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getPortA()
      */
    public String getPortA() {
        return this.portA;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setPortA(java.lang.String)
      */
    public void setPortA(String portA) {
        this.portA = portA;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getExtraddrA()
      */
    public String getExtraddrA() {
        if(getEquipmentA()!=null)
            return getExtraEquipmentInfo(getEquipmentA());
        else
            return getExtraEquipmentInfo(getPositionA());

//        String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getFullEquipmentName(getEquipmentA(), getPositionA());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setExtraddrA(java.lang.String)
//	 */
//	public void setExtraddrA(String extraddrA) {
//		this.extraddrA = extraddrA;
//	}

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getBeenetIdB()
      */
    public String getBeenetIdB() {
        return getExtraBeenetInfo(getEquipmentB().getPosition());
    }

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#getPositionB()
//	 */
//	public Integer getPositionB() {
//		return this.positionB;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setPositionB(int)
//	 */
//	public void setPositionB(Integer positionB) {
//		this.positionB = positionB;
//	}

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getPortB()
      */
    public String getPortB() {
        return this.portB;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setPortB(java.lang.String)
      */
    public void setPortB(String portB) {
        this.portB = portB;
    }

    public String getExtraddrB() {
        if(getEquipmentB()!=null)
            return getExtraEquipmentInfo(getEquipmentB());
        else
            return getExtraEquipmentInfo(getPositionB());
    }

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setExtraddrB(java.lang.String)
//	 */
//	public void setExtraddrB(String extraddrB) {
//		this.extraddrB = extraddrB;
//	}
//
    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getInitLabel()
      */
    public String getInitLabel() {
        return this.initLabel;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setChannelTag(java.lang.String)
      */
    public void setInitLabel(String initLabel) {
        this.initLabel = initLabel;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getComment()
      */
    public String getComment() {
        return this.comment;
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#setComment(java.lang.String)
      */
    public void setComment(String comment) {
        this.comment = comment;
    }

/*
	// Идентификатор пользователя
	private Person operator;
*/
/*
	public Person getOperator() {
		return operator;
	}
*/

/*
	public void setOperator(Person person) {
		operator = person;
	}
*/


    // Initiator Request

    // Инициатор
    private RTSGroup initiator;
    // Представитель инициатора
    private Person initiatorPerson;
    // Дата создания
    private Date createDate;
    // Фактическая дата исполнения
    private Date factComplDate;
    // Планируемая дата исполнения
    private Date planComplDate;
    // Исполнить до
    private Date completeDate;
    // Тип канала
    private ChannelType channelType;
    // Тип интерфейса
    private ChannelInterface channelInterface;
    // Тип интерфейса
    private ChannelInterfaceType interfaceType;
    // Емкость
    private String initCapacity;
    // Возможность сжатия
    private Integer canBeCompressed;
    // Основание
    private String reason;
    // Номер конс. заявки
    private Integer consRequestId;
    // Дата приема канала в работу
    private Date inWorkDate;
    // Приоритет
    private Integer priority;
    // Имя контактного лица A
    private String contactNameA;
    // Телефоны контактного лица A
    private String contactPhoneA;
    // Имя контактного лица B
    private String contactNameB;
    // Телефоны контактного лица B
    private String contactPhoneB;
    // ID ёмкости (TN_Capacities)
    private Capacity capacity;



    /**
     * @return
     */
    public RTSGroup getInitiator() {
        return initiator;
    }

    /**
     * @param group
     */
    public void setInitiator(RTSGroup group) {
        initiator = group;
    }

    /**
     * @return
     */
    public Person getInitiatorPerson() {
        return initiatorPerson;
    }

    /**
     * @param person
     */
    public void setInitiatorPerson(Person person) {
        initiatorPerson = person;
    }

    /**
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param date
     */
    public void setCreateDate(Date date) {
        createDate = date;
    }

    /**
     * @return
     */
    public Date getFactComplDate() {
        return factComplDate;
    }

    /**
     * @param date
     */
    public void setFactComplDate(Date date) {
        factComplDate = date;
    }

    /**
     * @return
     */
    public Date getPlanComplDate() {
        return planComplDate;
    }

    /**
     * @param date
     */
    public void setPlanComplDate(Date date) {
        planComplDate = date;
    }

    /**
     * @return
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     * @param date
     */
    public void setCompleteDate(Date date) {
        completeDate = date;
    }

    /**
     * @return
     */
    public ChannelType getChannelType() {
        return channelType;
    }

    /**
     * @param type
     */
    public void setChannelType(ChannelType type) {
        channelType = type;
    }

    /**
     * @return
     */
    public String getInitCapacity() {
        return initCapacity;
    }

    /**
     * @param string
     */
    public void setInitCapacity(String string) {
        initCapacity = string;
    }

    /**
     * @return
     */
    public Integer getCanBeCompressed() {
        return canBeCompressed;
    }

    /**
     * @param i
     */
    public void setCanBeCompressed(Integer i) {
        canBeCompressed = i;
    }

    /**
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param string
     */
    public void setReason(String string) {
        reason = string;
    }

    /**
     * @return
     */
    public Integer getConsRequestId() {
        return consRequestId;
    }

    /**
     * @param consreqid
     */
    public void setConsRequestId(Integer consreqid) {
        consRequestId = consreqid;
    }

    /**
     * @return
     */
    public Date getInWorkDate() {
        return inWorkDate;
    }

    /**
     * @param date
     */
    public void setInWorkDate(Date date) {
        inWorkDate = date;
    }

    /**
     * @return
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param i
     */
    public void setPriority(Integer i) {
        priority = i;
    }



    /**
     * @return
     */
    public ChannelInterfaceType getInterfaceType() {
        return interfaceType;
    }

    /**
     * @param type
     */
    public void setInterfaceType(ChannelInterfaceType type) {
        interfaceType = type;
    }

    /**
     * @return
     */
    public String getContactNameA() {
        return contactNameA;
    }

    /**
     * @return
     */
    public String getContactPhoneA() {
        return contactPhoneA;
    }

    /**
     * @param string
     */
    public void setContactNameA(String string) {
        contactNameA = string;
    }

    /**
     * @param string
     */
    public void setContactPhoneA(String string) {
        contactPhoneA = string;
    }

    /**
     * @return
     */
    public String getContactNameB() {
        return contactNameB;
    }

    /**
     * @return
     */
    public String getContactPhoneB() {
        return contactPhoneB;
    }

    /**
     * @param string
     */
    public void setContactNameB(String string) {
        contactNameB = string;
    }

    /**
     * @param string
     */
    public void setContactPhoneB(String string) {
        contactPhoneB = string;
    }

    public ChannelInterface getChannelInterface() {
        return channelInterface;
    }

    public void setChannelInterface(ChannelInterface channelInterface) {
        this.channelInterface = channelInterface;
    }


    public String getEquipmentNameB() {
		if(getEquipmentB()!=null) {
			return getEquipmentB().getFullName();
		} else if(getPositionB()!=null) { 
			return "[Устройство не определено] Позиция: "+getPositionB().getFullName();
		}
		return "";
    }

    public String getEquipmentNameA() {
    	if(getEquipmentA()!=null) {
			return getEquipmentA().getFullName();
    	} else if(getPositionA()!=null) { 
			return "[Устройство не определено] Позиция: "+getPositionA().getFullName();
    	}
        return "";
    }

//    public Integer getTransPositionA() {
//        return transPositionA;
//    }
//
//    public void setTransPositionA(Integer transPositionA) {
//        this.transPositionA = transPositionA;
//    }
//
//    public Integer getTransPositionB() {
//        return transPositionB;
//    }
//
//    public void setTransPositionB(Integer transPositionB) {
//        this.transPositionB = transPositionB;
//    }

    public ConsolidatedRequest getConsolidatedRequest() {
        return consolidatedRequest;
    }

    public void setConsolidatedRequest(ConsolidatedRequest consolidatedRequest) {
        this.consolidatedRequest = consolidatedRequest;
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

    /**
     * @return Capacity
     */
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * @param Capacity
     */
    public void setCapacity(Capacity cap) {
        capacity = cap;
    }

    public String getRevisionComment() {
        return revisionComment;
    }

    public void setRevisionComment(String revisionComment) {
        this.revisionComment = revisionComment;
    }


}
