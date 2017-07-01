package com.hps.july.rts.core.service;

import java.util.HashMap;

import org.springframework.beans.BeansException;

import com.hps.july.rts.RTSContextProvider;
import com.hps.april.common.Service;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 20.01.2006
 * Time: 16:21:15
 */
public class ServiceFactory {

    public static final String RTS_CHANNEL_INTERFACE = "july.rts.service.channelInterfaceService";
    public static final String RTS_CHANNEL_INTERFACE_TYPE = "july.rts.service.channelInterfaceTypeService";
    public static final String RTS_CHANNEL_SETTINGS = "july.rts.service.channelSettingService";
    public static final String RTS_CAPACITY = "july.rts.service.capacityService";
    public static final String RTS_CHANNEL_TYPE = "july.rts.service.channelTypeService";
    public static final String RTS_INITIATOR_REQUEST = "july.rts.service.initiatorRequestService";
    public static final String RTS_OPERATOR = "july.rts.service.operatorService";
    public static final String RTS_REQUEST_TYPE = "july.rts.service.requestTypeService";
    public static final String RTS_STATUS = "july.rts.statusService";
    public static final String RTS_CONSOLIDATED_REQUEST = "july.rts.service.consolidatedRequest";
    public static final String RTS_COMMENTS = "rts.commentService";
    public static final String RTS_EQUIPMENT = "rts.equipmentService";
    public static final String RTS_NUMERATION = "july.rts.RTSRequestNumerationService";
    public static final String RTS_ELEMENTARY_REQUEST = "july.rts.service.elementaryRequest";
    public static final String RTS_RENT_ELEMENTARY_REQUEST = "july.rts.service.rentElementaryRequest";
    public static final String RTS_COUNTER_AGENT = "july.rts.service.counterAgentService";

    private static HashMap services;

    static {
        services = new HashMap();
        services.put(RTS_CHANNEL_INTERFACE, "com.hps.july.rts.service.impl.ChannelInterfaceServiceImpl");
        services.put(RTS_CHANNEL_INTERFACE_TYPE, "com.hps.july.rts.service.impl.ChannelInterfaceTypeServiceImpl");
        services.put(RTS_CHANNEL_SETTINGS, "com.hps.july.rts.service.impl.ChannelSettingServiceImpl");
        services.put(RTS_CAPACITY, "com.hps.july.rts.service.impl.CapacityServiceImpl");
        services.put(RTS_CHANNEL_TYPE, "com.hps.july.rts.service.impl.ChannelTypeServiceImpl");
        services.put(RTS_INITIATOR_REQUEST, "com.hps.july.rts.service.impl.InitiatorRequestServiceImpl");
        services.put(RTS_OPERATOR, "com.hps.july.rts.service.impl.RTSOperatorServiceImpl");
        services.put(RTS_REQUEST_TYPE, "com.hps.july.rts.service.impl.RTSRequestTypeServiceImpl");
        services.put(RTS_STATUS, "com.hps.july.rts.service.impl.RTSStatusServiceImpl");
        services.put(RTS_CONSOLIDATED_REQUEST, "com.hps.july.rts.service.impl.ConsolidatedRequestServiceImpl");
        services.put(RTS_COMMENTS, "com.hps.july.rts.service.impl.CommentServiceImpl");
        services.put(RTS_EQUIPMENT, "com.hps.july.rts.service.impl.EquipmentServiceImpl");
        services.put(RTS_ELEMENTARY_REQUEST, "com.hps.july.rts.service.impl.ElementaryRequestServiceImpl");
        services.put(RTS_RENT_ELEMENTARY_REQUEST, "com.hps.july.rts.service.impl.RentElementaryRequestServiceImpl");
        services.put(RTS_COUNTER_AGENT, "com.hps.july.rts.service.impl.CounterAgentServiceImpl");
    }

    public static Service getService(String name) throws ServiceException {

        if(name == null) throw new ServiceException("not valid service name");

        // lookup in the String context
        try {
            Object bean = RTSContextProvider.getBean(name);
            if (bean != null) return (Service) bean;
        } catch(BeansException e) {
            // not found in spring
        }

        String serviceClazzName = (String)services.get(name);
        try {
            Class serviceClazz = Class.forName(serviceClazzName);
            Service service = (Service)serviceClazz.newInstance();
            return service;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ServiceException("cannot find service by name ["+name+"]");
    }
}
