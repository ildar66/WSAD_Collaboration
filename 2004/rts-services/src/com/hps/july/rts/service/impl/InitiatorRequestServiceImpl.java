package com.hps.july.rts.service.impl;

import java.util.Collection;
import java.sql.*;

import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.april.common.FinderException;
import com.hps.july.rts.core.service.ServiceFactory;
import com.hps.july.rts.core.service.ServiceException;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.object.request.utils.RequestUtils;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.service.*;
import com.hps.july.rts.service.impl.dao.InitiatorRequestDAO;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.april.auth.object.AuthInfo;
import org.apache.log4j.Logger;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * 
 *  @author abaykov
 *  Created on 17.01.2006
 */
public class InitiatorRequestServiceImpl
                extends DefaultProcess  implements InitiatorRequestService {

    private static Logger logger;
    private InitiatorRequestDAO initiatorRequestDAO;
    private RTSRequestNumerationService numerationService;
    private RTSOperatorService operatorService;
    private RTSAuthService authService;

    public InitiatorRequestDAO getInitiatorRequestDAO() {
        return initiatorRequestDAO;
    }

    public void setInitiatorRequestDAO(InitiatorRequestDAO initiatorRequestDAO) {
        this.initiatorRequestDAO = initiatorRequestDAO;
    }

    public RTSRequestNumerationService getNumerationService() {
        return numerationService;
    }

    public void setNumerationService(RTSRequestNumerationService numerationService) {
        this.numerationService = numerationService;
    }

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public RTSAuthService getAuthService() {
        return authService;
    }

    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }

    /* (non-Javadoc)
	 * @see com.hps.july.rts.service.InitiatorRequestService#findAllInitiatorRequests()
	 */
    public Collection findAllInitiatorRequests(AuthInfo authInfo) throws SystemException {

        Person currentInitiator = authService.getPerson(authInfo);
        RTSRole keyManagerRole = null;
        try {
            keyManagerRole = authService.getRTSRole(new Integer(4));
        } catch (FinderException e) {
        }
        //add check on NRI roles !
        boolean isAdmin = ( authService.isUserInRtsRole(authInfo, keyManagerRole)
                || authService.isUserInRole(authInfo, "administrator")
                || authService.isUserInRole(authInfo, "developer"));
        RTSGroup rtsGroup = getGroup(currentInitiator);
        return initiatorRequestDAO.findInitiatorRequests(Boolean.FALSE,
                Boolean.FALSE,  null,
                Boolean.FALSE,  null,  null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                Boolean.FALSE, null,
                rtsGroup, new Boolean(isAdmin));
    }

    /**
     * Метод поиска Заявок инициатора по заданным условиям
     */
    public Collection findInitiatorRequests(Boolean isSearching,
                                            Boolean isNumber, String number,
                                            Boolean isDateType, String dateTypeSelect,Date dateType,
                                            Boolean isRtsRequestType, String rtsRequestType,
                                            Boolean isRtsStatus, String rtsStatus,
                                            Boolean isChannelSetting, String channelSetting,
                                            Boolean isChannelType, String channelType,
                                            String priority,
                                            Boolean isInitiator, String initiator,
                                            Boolean isRegMan, Integer regMan,
                                            Boolean isExecutor, Integer executor,
                                            Boolean isSearchAddrA, String searchAddrA,
                                            Boolean isSearchAddrB, String searchAddrB,
                                            AuthInfo authInfo) throws SystemException {

        Person currentInitiator = authService.getPerson(authInfo);
        RTSRole keyManagerRole = null;
        try {
            keyManagerRole = authService.getRTSRole(new Integer(4));
        } catch (FinderException e) {
        }
        //add check on NRI roles !
        boolean isAdmin = ( authService.isUserInRtsRole(authInfo, keyManagerRole)
                || authService.isUserInRole(authInfo, "administrator")
                || authService.isUserInRole(authInfo, "developer"));
        RTSGroup rtsGroup = getGroup(currentInitiator);
        return initiatorRequestDAO.findInitiatorRequests(isSearching,
                isNumber,  number,
                isDateType,  dateTypeSelect,  dateType,
                isRtsRequestType, rtsRequestType,
                isRtsStatus, rtsStatus,
                isChannelSetting, channelSetting,
                isChannelType, channelType,
                priority,
                isInitiator, initiator,
                isRegMan, regMan,
                isExecutor, executor,
                isSearchAddrA, searchAddrA,
                isSearchAddrB, searchAddrB,
                rtsGroup, new Boolean(isAdmin));

    }

    /* (non-Javadoc)
       * @see com.hps.july.rts.service.InitiatorRequestService#saveInitiatorRequests(com.hps.july.rts.object.request.InitiatorRequest)
       */
    public void saveInitiatorRequests(AuthInfo authInfo, InitiatorRequest request) throws SystemException {
        if(request == null)
            throw new SystemException("Cannot save InitiatorRequest, cause null");

        Person currentPerson = authService.getPerson(authInfo);
        request.setCreatedBy(currentPerson);
        request.setModifiedBy(currentPerson);
        Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setCreateDate(currentDate);
        request.setCreated(currentDate);
        request.setModified(currentDate);

        initiatorRequestDAO.saveInitiatorRequests(request);

// deprecated        
//        if(initiatorRequestDAO != null) {
//			  initiatorRequestDAO.saveInitiatorRequests(request);
//        } else {
//            Session session = SessionFactoryProvider.currentSession();
//            session.save(request);
//            session.flush();
//        }
    }

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus) throws SystemException {
        return this.initiatorRequestDAO.findInitiatorRequestsByStatus(rtsStatus);
    }

    /**
     * method for getting all initiators request which
     * ready for including in consolidated request
     * @return Collection of Initiators request
     * @throws com.hps.july.rts.SystemException
     */
    public Collection findInitiatorRequestsReadyForConsolidate(AuthInfo authInfo, String currentRole) throws SystemException {
        return this.initiatorRequestDAO.
                        findInitiatorRequestsByStatusAndInConsRequest( new Integer(RTSStatus.statusId_2), authInfo, currentRole);
    }


    /* (non-Javadoc)
       * @see com.hps.july.rts.service.InitiatorRequestService#updateInitiatorRequest(com.hps.july.rts.object.request.InitiatorRequest)
       */
    public void updateInitiatorRequest(AuthInfo authInfo, InitiatorRequest request) throws SystemException {
        if(request == null)
            throw new SystemException("Cannot update InitiatorRequest, cause null");

        Timestamp currentDate = RequestUtils.getTimestampFromUtilDate(new java.util.Date());
        request.setModified(currentDate);

        Person person = authService.getPerson(authInfo);
        request.setModifiedBy(person);

        if(request.getRtsStatus().getId().intValue()==RTSStatus.statusId_2 &&
            ( request.getNumber()==null || request.getNumber().trim().equalsIgnoreCase(""))) {
            String number = null;
            if(numerationService != null) number = numerationService.generateInitiatorRequestNumber(request.getInitiatorPerson());
            System.out.println("init number ["+number+"]");
            request.setNumber(number);
        }

        initiatorRequestDAO.updateInitiatorRequest(request);
    }

    /* (non-Javadoc)
       * @see com.hps.july.rts.service.InitiatorRequestService#removeInitiatorRequest(com.hps.july.rts.object.request.InitiatorRequest)
       */
    public void removeInitiatorRequest(InitiatorRequest initiatorRequest) throws SystemException {
        initiatorRequestDAO.removeInitiatorRequest(initiatorRequest);
    }

    private RTSGroup getGroup(Person person) {
        if(person==null) return null;
        Collection groups = null;
        RTSGroup rtsGroup = null;
        try{
            System.out.println("OPERATOR SERVICE ["+operatorService+"]");
            if(operatorService != null) {
                groups = operatorService.findGroupByPerson(person);
                if(groups!=null && groups.size()>0) rtsGroup = (RTSGroup)groups.toArray()[0];
            } else {
                RTSOperatorService rtsOperatorService =
                    (RTSOperatorService)ServiceFactory.getService(ServiceFactory.RTS_OPERATOR);
                groups = rtsOperatorService.findGroupByPerson(person);
                if(groups!=null && groups.size()>0) rtsGroup = (RTSGroup)groups.toArray()[0];
            }
        } catch(ServiceException se) {
            rtsGroup = null;
            logger.error("Cannot initialize Initiator (RTSGroup) by Person (id = '"+person.getId()+"')! ");
        }
        return rtsGroup;
    }

    public Collection findInitiatorRequestsByConsolidatedRequest(Integer consolidatedRequest) throws SystemException {
        return null;
    }

    public InitiatorRequest findInitiatorRequestById(Person currentInitiator, Integer id)
                throws SystemException{

        InitiatorRequest request = null;
        if (id == null || id.intValue() == 0) return request;
        if(initiatorRequestDAO != null) request = initiatorRequestDAO.findInitiatorRequestById(currentInitiator, id);
        return request;
    }

    public InitiatorRequest findInitiatorRequest(Integer id) throws SystemException {

        InitiatorRequest request = null;
        if (id == null || (id.intValue() == 0)) return request;
        if(initiatorRequestDAO != null) request = initiatorRequestDAO.findInitiatorRequest(id);
        return request;
    }

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus, Person currentInitiator) throws SystemException {
        return null;
    }

}
