package com.hps.july.rts.object.request;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.hps.july.rts.object.capacity.Capacity;
import com.hps.july.rts.object.channelsetting.ChannelSetting;
import com.hps.july.rts.object.channeltype.ChannelType;
import com.hps.july.rts.object.RTSRequestType;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.july.rts.service.RTSRequestTypeService;
import com.hps.july.rts.service.RTSStatusService;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.storageplace.object.Equipment;
import com.hps.july.storageplace.object.Position;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * Консолидированная заявка
 *
 *  @author abaykov
 *  Created on 26.12.2005
 */
public class ConsolidatedRequest extends AbstractRequest implements Request {

	private Person keyManager;
	private Person regManager;
    //руководитель
    private Person head;

    private RTSStatus rtsStatus;
    private Integer statusId;

    private RTSRequestType rtsRequestType;
    private Integer rtsRequestTypeId;

    private ChannelType channelType;
    private ChannelSetting channelSetting;

	// assosiation to Equipment
	private Equipment equipmentA;
	private Equipment equipmentB;

    private String portA;
    private String portB;

    private String contactA;
    private String contactB;

    private String initLabel;
    private String comment;

    //vad
    private Person owner;
    private String ownerName;
    private String ownerPhone;

    private RTSGroup ownerGroup; //is it necessary

    private Date requiredDate;
    private Date planComplDate;
    private Date factComplDate;
    // arriveddate - "Дата поступления". При статусе "На рассмотрении". Для РМ.
    private Date arrivedDate;
    private Date closed;
    //Код канала
    private String channelCode;
    //метка канал
    private String channelLabel;

    private Date tested;
    private Person testedBy;

    private Collection initiatorRequests;
    private Collection elementaryRequests;

    private Integer arendaType;

    //региональный менеджер
    private Person regMan;
    //утверждающий
    private Person confirmativeMan;

    // Оборудование транспортной сети
    private Equipment transEquipmentA;
    private Equipment transEquipmentB;
    private String transPortA;
    private String transPortB;
    // Ёмкость (TN_Capacities)
    private Capacity capacity;


    public ConsolidatedRequest() {
        arendaType = new Integer(0);
    }

    public String getRequestType() {
        return "K";
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

    public Person getHead() {
        return head;
    }

    public void setHead(Person head) {
        this.head = head;
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
        setStatusId((status != null)?status.getId():null);
    }

    public void setRtsStatus(Integer status) {
        try {
            RTSStatusService service = (RTSStatusService)ServiceFactory.getService(ServiceFactory.RTS_STATUS);
            this.rtsStatus = service.findRTSStatus(status);
            if(rtsStatus != null)this.statusId = status;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getRTSRequestType()
      */
    public RTSRequestType getRtsRequestType() {
        try {
            RTSRequestTypeService service= (RTSRequestTypeService)ServiceFactory.getService(ServiceFactory.RTS_REQUEST_TYPE);
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
            RTSRequestTypeService service= (RTSRequestTypeService)ServiceFactory.getService(ServiceFactory.RTS_REQUEST_TYPE);
            rtsRequestType = service.findRTSRequestType(type);
            if(rtsRequestType != null) this.rtsRequestTypeId = type;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
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

// VERIFY:
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setBeenetIdA(java.lang.String)
//	 */
//	public void setBeenetIdA(String beenetIdA) {
//		this.beenetIdA = beenetIdA;
//	}
//
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
//
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

//        String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getBeenet(getPositionB());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

// VERIFY
//	/* (non-Javadoc)
//	 * @see com.hps.july.rts.object.request.Request#setBeenetIdB(java.lang.String)
//	 */
//	public void setBeenetIdB(String beenetIdB) {
//		this.beenetIdB = beenetIdB;
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

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getExtraddrB()
      */
    public String getExtraddrB() {
        if(getEquipmentB()!=null)
            return getExtraEquipmentInfo(getEquipmentB());
        else
            return getExtraEquipmentInfo(getPositionB());

//        String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getFullEquipmentName(getEquipmentB(), getPositionB());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

// VERIFY:
//	public void setExtraddrB(String extraddrB) {
//		this.extraddrB = extraddrB;
//	}

    /* (non-Javadoc)
      * @see com.hps.july.rts.object.request.Request#getChannelTag()
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

    // Идентификатор пользователя
    private Person operator;

    /**
     * @return
     */
    public Person getOperator() {
        return operator;
    }

    /**
     * @param person
     */
    public void setOperator(Person person) {
        operator = person;
    }

    //vad
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public RTSGroup getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(RTSGroup ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getPlanComplDate() {
        return planComplDate;
    }

    public void setPlanComplDate(Date planComplDate) {
        this.planComplDate = planComplDate;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelLabel() {
        return channelLabel;
    }

    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    public Collection getInitiatorRequests() {
        return initiatorRequests;
    }

    public void setInitiatorRequests(Collection initiatorRequests) {
        this.initiatorRequests = initiatorRequests;
    }

    public void addInitiatorRequest(InitiatorRequest request) {
        if(initiatorRequests == null) this.initiatorRequests = new HashSet();
        this.initiatorRequests.add(request);
    }

    public Collection getElementaryRequests() {
        return elementaryRequests;
    }

    public void setElementaryRequests(Collection elementaryRequests) {
        this.elementaryRequests = elementaryRequests;
    }

    public void addElementaryRequest(ElementaryRequest request) {
        if(elementaryRequests == null) this.elementaryRequests = new HashSet();
        this.elementaryRequests.add(request);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Date getFactComplDate() {
        return factComplDate;
    }

    public void setFactComplDate(Date factComplDate) {
        this.factComplDate = factComplDate;
    }

    public Date getTested() {
        return tested;
    }

    public void setTested(Date tested) {
        this.tested = tested;
    }

    public Person getTestedBy() {
        return testedBy;
    }

    public void setTestedBy(Person testedBy) {
        this.testedBy = testedBy;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }


    public String getContactA() {
        return contactA;
    }

    public void setContactA(String contactA) {
        this.contactA = contactA;
    }

    public String getContactB() {
        return contactB;
    }

    public void setContactB(String contactB) {
        this.contactB = contactB;
    }

    public Integer getArendaType() {
        return arendaType;
    }

    public void setArendaType(Integer arendaType) {
        this.arendaType = arendaType;
    }

    /**
     * @deprecated use getEquipmentB().getFullName();
     */
    public String getEquipmentNameB() {
        return getEquipmentB().getFullName();
//
//        String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getEquipmentName(getEquipmentB());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

    /**
     * @deprecated use getEquipmentA().getFullName();
     */
    public String getEquipmentNameA() {
       return getEquipmentA().getFullName();
//
//    	String result = null;
//        try {
//            EquipmentService service = (EquipmentService)ServiceFactory.getService(ServiceFactory.RTS_EQUIPMENT);
//            result = service.getEquipmentName(getEquipmentA());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        }
//        return result;
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

    public String getInitiatorRequestsNumber() {
        StringBuffer result = new StringBuffer("");
        Collection requests = getInitiatorRequests();
        for (Iterator i = requests.iterator(); i.hasNext();) {
            InitiatorRequest request = (InitiatorRequest) i.next();
            if(request.getNumber() != null)
                result.append(request.getNumber()).append("\n");
        }
        return result.toString();
    }

    /**
     * @return
     */
    public Person getRegMan() {
        return regMan;
    }

    /**
     * @param person
     */
    public void setRegMan(Person person) {
        regMan = person;
    }

    public Equipment getTransEquipmentA() {
        return transEquipmentA;
    }

    public void setTransEquipmentA(Equipment transEquipmentA) {
        this.transEquipmentA = transEquipmentA;
    }

    public Equipment getTransEquipmentB() {
        return transEquipmentB;
    }

    public void setTransEquipmentB(Equipment transEquipmentB) {
        this.transEquipmentB = transEquipmentB;
    }

    public String getTransPortA() {
        return transPortA;
    }

    public void setTransPortA(String transPortA) {
        this.transPortA = transPortA;
    }

    public String getTransPortB() {
        return transPortB;
    }

    public void setTransPortB(String transPortB) {
        this.transPortB = transPortB;
    }

// ---

   public String getTransExtraddrB() {
       if(getTransEquipmentB()!=null)
           return getExtraEquipmentInfo(getTransEquipmentB());
       else
           return getExtraEquipmentInfo(getPositionB());
   }

   public String getTransBeenetIdB() {
       return getExtraBeenetInfo(getTransEquipmentB().getPosition());
   }

    public String getTransExtraddrA() {
        if(getTransEquipmentA()!=null)
            return getExtraEquipmentInfo(getTransEquipmentA());
        else
            return getExtraEquipmentInfo(getPositionA());
    }

    public String getTransBeenetIdA() {
        return getExtraBeenetInfo(getTransEquipmentA().getPosition());
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }

    public void setArrivedDate(Date arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    /**
     * @return
     */
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * @param integer
     */
    public void setCapacity(Capacity cap) {
        capacity = cap;
    }

    public String getTransEquipmentNameB() {
        if(getTransEquipmentB()!=null) {
            return getTransEquipmentB().getFullName();
        } else if(getPositionB()!=null) {
            return "[Устройство не определено] Позиция: "+getPositionB().getFullName();
        }
        return "";
    }

    public String getTransEquipmentNameA() {
        if(getTransEquipmentA()!=null) {
            return getTransEquipmentA().getFullName();
        } else if(getPositionA()!=null) {
            return "[Устройство не определено] Позиция: "+getPositionA().getFullName();
        }
        return "";
    }

    public Person getConfirmativeMan() {
        return confirmativeMan;
    }

    public void setConfirmativeMan(Person confirmativeMan) {
        this.confirmativeMan = confirmativeMan;
    }

    public InitiatorRequest getInitiatorRequest() {
        if(!getInitiatorRequests().isEmpty()) {
            return (InitiatorRequest) getInitiatorRequests().iterator().next();
        } else return null;
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Not implemented yet !");
        //ConsolidatedRequest request = new ConsolidatedRequest();
    }
    
    public Position getPositionA() {
        if(getTransEquipmentA()!=null) return getTransEquipmentA().getPosition();
        else return positionA;
    }

    public Position getPositionB() {
        if(getTransEquipmentB()!=null) return getTransEquipmentB().getPosition();
        else return positionB;
    }

    public void setPositionA(Position position) {
        this.positionA = position;
    }

    public void setPositionB(Position position) {
        this.positionB = position;
    }
}
