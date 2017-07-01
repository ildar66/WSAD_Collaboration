package com.hps.july.rts.object.request;

import java.sql.Date;

import com.hps.july.rts.object.capacity.Capacity;
import com.hps.july.rts.object.channelinterface.ChannelInterface;
import com.hps.july.rts.object.channelinterfacetype.ChannelInterfaceType;
import com.hps.july.rts.object.channelsetting.ChannelSetting;
import com.hps.july.rts.object.counteragent.CounterAgent;
import com.hps.july.rts.object.RTSRequestType;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.july.rts.service.RTSRequestTypeService;
import com.hps.july.rts.service.RTSStatusService;
import com.hps.july.rts.object.RTSStatus;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * Елементарная Заявка (Своя ТС, Аренда)
 *
 *  @author abaykov
 *  Created on 26.12.2005
 */
public class ElementaryRequest extends AbstractRequest implements Request, Comparable {

    private Person keyManager;
    private Person regManager;
    private Person initiatorPerson;
    private Person executor;

    private RTSStatus rtsStatus;
    private RTSRequestType rtsRequestType;

    private Integer rtsStatusId;
    private Integer rtsRequestTypeId;

    private Integer arendaType; //field - elemReqType

    private Date completeDate;
    private Date planComplDate;
    private Date factComplDate;

    private ChannelSetting channelSetting;
    private ChannelInterface channelInterface; //interfaceid
    private ChannelInterfaceType channelInterfaceType;

    private String channelLabel;
    private String channelCode;

    private String interfaceOm;
    private Capacity capacity;

    private CounterAgent counterAgent;


// VERIFY deprecated
//    private String beenetIdA;
//    private String beenetIdB;
//	private Integer positionA;
//	private Integer positionB;
//    private Integer equipmentA;
//	private Integer equipmentB;
//	private String extaddrA;
//    private String extaddrB;

    // assosiation to Equipment
    private String portA;
    private String portB;

    private String contactA;
    private String contactB;

    private String commentKM;
    private String commentRM;
    private String comment;

    private String siteLabel;
    private String connectParams;
    private String actNumber;
    private Date actDate;
    private String docNumber;
    private Date docDate;

    private Date channelClosingDate;
	// Дата отправки запроса оператору. Для арендуемых заявко
    private Date operatorDate;
    private Date orderDate;
    private Date planStartDate;

    private String order;
    private String contract;

    private Date actClosingDate;
    private String agreement;

    private ConsolidatedRequest consolidatedRequest;
    // ID ёмкости (TN_Capacities)
    private Integer capacityId;


    //constructor
    public ElementaryRequest() {
    }

    public String getRequestType() {
        return "E";
    }

    public Person getKeyManager() {
        return keyManager;
    }

    public void setKeyManager(Person keyManager) {
        this.keyManager = keyManager;
    }

    public Person getRegManager() {
        return regManager;
    }

    public void setRegManager(Person regManager) {
        this.regManager = regManager;
    }

    public Person getInitiatorPerson() {
        return initiatorPerson;
    }

    public void setInitiatorPerson(Person initiatorPerson) {
        this.initiatorPerson = initiatorPerson;
    }

    public Person getExecutor() {
        return executor;
    }

    public void setExecutor(Person executor) {
        this.executor = executor;
    }

    public RTSStatus getRtsStatus() {
        try {
            RTSStatusService service = (RTSStatusService)ServiceFactory.getService(ServiceFactory.RTS_STATUS);
            rtsStatus = service.findRTSStatus(this.rtsStatusId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return rtsStatus;
    }

    public void setRtsStatus(RTSStatus rtsStatus) {
        this.rtsStatus = rtsStatus;
        setRtsStatusId((rtsStatus != null)?rtsStatus.getId():null);
    }

    public void setRtsStatus(Integer status) {
        try {
            RTSStatusService service = (RTSStatusService)ServiceFactory.getService(ServiceFactory.RTS_STATUS);
            this.rtsStatus = service.findRTSStatus(status);
            if(rtsStatus != null) setRtsStatusId(status);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public RTSRequestType getRtsRequestType() {
        try {
            RTSRequestTypeService service= (RTSRequestTypeService)ServiceFactory.getService(ServiceFactory.RTS_REQUEST_TYPE);
            rtsRequestType = service.findRTSRequestType(this.rtsRequestTypeId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return rtsRequestType;
    }

    public void setRtsRequestType(RTSRequestType rtsRequestType) {
        this.rtsRequestType = rtsRequestType;
    }

    public Integer getRtsStatusId() {
        return rtsStatusId;
    }

    public void setRtsStatusId(Integer rtsStatusId) {
        this.rtsStatusId = rtsStatusId;
    }

    public Integer getRtsRequestTypeId() {
        return getRtsRequestType().getId();
    }

    public void setRtsRequestTypeId(Integer rtsRequestTypeId) {
        this.rtsRequestTypeId = rtsRequestTypeId;
    }


	/**
	 * Принимаемые значения
	 *  1	Своя ТС
	 *  2	Аренда
	 * @return тип
	 */
	public Integer getArendaType() {
        return arendaType;
    }

    public void setArendaType(Integer arendaType) {
        this.arendaType = arendaType;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Date getPlanComplDate() {
        return planComplDate;
    }

    public void setPlanComplDate(Date planComplDate) {
        this.planComplDate = planComplDate;
    }

    public Date getFactComplDate() {
        return factComplDate;
    }

    public void setFactComplDate(Date factComplDate) {
        this.factComplDate = factComplDate;
    }

    public ChannelSetting getChannelSetting() {
        return channelSetting;
    }

    public void setChannelSetting(ChannelSetting channelSetting) {
        this.channelSetting = channelSetting;
    }

    public String getChannelLabel() {
        return channelLabel;
    }

    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public ChannelInterface getChannelInterface() {
        return channelInterface;
    }

    public void setChannelInterface(ChannelInterface channelInterface) {
        this.channelInterface = channelInterface;
    }

    public String getInterfaceOm() {
        return interfaceOm;
    }

    public void setInterfaceOm(String interfaceOm) {
        this.interfaceOm = interfaceOm;
    }

    public ChannelInterfaceType getChannelInterfaceType() {
        return channelInterfaceType;
    }

    public void setChannelInterfaceType(ChannelInterfaceType channelInterfaceType) {
        this.channelInterfaceType = channelInterfaceType;
    }

// VERIFY
//    public void setBeenetIdB(String beenetIdB) {
//        this.beenetIdB = beenetIdB;
//    }
//
//
//    public Integer getEquipmentA() {
//        return equipmentA;
//    }
//
//    public void setEquipmentA(Integer equipmentA) {
//        this.equipmentA = equipmentA;
//    }
//
//    public Integer getEquipmentB() {
//        return equipmentB;
//    }
//
//    public void setEquipmentB(Integer equipmentB) {
//        this.equipmentB = equipmentB;
//    }

    public String getPortA() {
        return portA;
    }

    public void setPortA(String portA) {
        this.portA = portA;
    }

    public String getPortB() {
        return portB;
    }

    public void setPortB(String portB) {
        this.portB = portB;
    }

    public String getExtraddrA() {
        if(getEquipmentA()!=null)
            return getExtraEquipmentInfo(getEquipmentA());
        else
            return getExtraEquipmentInfo(getPositionA());
    }

//    public void setExtraddrA(String extaddrA) {
//        this.extaddrA = extaddrA;
//    }

    public String getExtraddrB() {
        if(getEquipmentB()!=null)
            return getExtraEquipmentInfo(getEquipmentB());
        else
            return getExtraEquipmentInfo(getPositionB());
    }

//    public void setExtraddrB(String extaddrB) {
//        this.extaddrB = extaddrB;
//    }

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

    public String getCommentKM() {
        return commentKM;
    }

    public void setCommentKM(String commentKM) {
        this.commentKM = commentKM;
    }

    public String getCommentRM() {
        return commentRM;
    }

    public void setCommentRM(String commentRM) {
        this.commentRM = commentRM;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSiteLabel() {
        return siteLabel;
    }

    public void setSiteLabel(String siteLabel) {
        this.siteLabel = siteLabel;
    }

    public String getConnectParams() {
        return connectParams;
    }

    public void setConnectParams(String connectParams) {
        this.connectParams = connectParams;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getChannelClosingDate() {
        return channelClosingDate;
    }

    public void setChannelClosingDate(Date channelClosingDate) {
        this.channelClosingDate = channelClosingDate;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Date getActClosingDate() {
        return actClosingDate;
    }

    public void setActClosingDate(Date actClosingDate) {
        this.actClosingDate = actClosingDate;
    }

    public ConsolidatedRequest getConsolidatedRequest() {
        return consolidatedRequest;
    }

    public void setConsolidatedRequest(ConsolidatedRequest consolidatedRequest) {
        this.consolidatedRequest = consolidatedRequest;
    }

    /**
     * @return Capacity
     */
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * @param cap
     */
    public void setCapacity(Capacity cap) {
        capacity = cap;
    }

    /**
     * @return CounterAgent
     */
    public CounterAgent getCounterAgent() {
        return counterAgent;
    }

    /**
     * @param agent
     */
    public void setCounterAgent(CounterAgent agent) {
        counterAgent = agent;
    }

    /**
     * @return String
     */
    public String getAgreement() {
        return agreement;
    }

    /**
     * @param string
     */
    public void setAgreement(String string) {
        agreement = string;
    }

    public int compareTo(Object o) {
        if (o == null || !(o instanceof ElementaryRequest)) {
            throw new ClassCastException("The object is not instance of ElementaryRequest.");
        }
        ElementaryRequest request = (ElementaryRequest) o;
        int lastCmp = 0;
        if(getNumber()!=null && request.getNumber()!=null) {
            lastCmp = getNumber().compareTo(request.getNumber());
            if(lastCmp == 0) {
            	lastCmp = (requestId.compareTo(request.getRequestId())<0?1:-1);
        	} 
        } else {
            throw new NullPointerException("The number of ElementaryRequest is null.");
        }
        return lastCmp;
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

}
