package com.hps.july.rts.service;

import java.util.Collection;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.april.common.Service;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.InitiatorRequest;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * 
 *  @author abaykov
 *  Created on 17.01.2006
 */
public interface InitiatorRequestService extends Service {

    String SERVICE_NAME = "july.rts.service.initiatorRequestService";

    public Collection findAllInitiatorRequests(AuthInfo currentInitiator) throws SystemException ;

    public Collection findInitiatorRequests(Boolean isSerching,
                                            Boolean isNumber,String number,
                                            Boolean isDateType,String dateTypeSelect,java.sql.Date dateType,
                                            Boolean isRtsRequestType,String rtsRequestType,
                                            Boolean isRtsStatus,String rtsStatus,
                                            Boolean isChannelSetting,String channelSetting,
                                            Boolean isChannelType,String channelType,
                                            String priority,
                                            Boolean isInitiator,String initiator,
                                            Boolean isRegMan,Integer regMan,
                                            Boolean isExecutor,Integer executor,
                                            Boolean isSearchAddrA,String searchAddrA,
                                            Boolean isSearchAddrB,String searchAddrB,
                                            AuthInfo currentInitiator) throws SystemException;

    public void saveInitiatorRequests(AuthInfo authInfo, InitiatorRequest initiatorRequest) throws SystemException ;

    public void updateInitiatorRequest(AuthInfo authInfo, InitiatorRequest initiatorRequest) throws SystemException;

    public void removeInitiatorRequest(InitiatorRequest initiatorRequest) throws SystemException ;

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus) throws SystemException;
    /**
     * method for getting all initiators request which
     * ready for including in consolidated request
     * @return Collection of Initiators request
     * @throws SystemException
     */
    public Collection findInitiatorRequestsReadyForConsolidate(AuthInfo authInfo, String currentRole) throws SystemException;

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus, Person currentInitiator) throws SystemException;

    public Collection findInitiatorRequestsByConsolidatedRequest(Integer consolidatedRequest) throws SystemException;

    public InitiatorRequest findInitiatorRequestById(Person currentInitiator, Integer id) throws SystemException ;

    public InitiatorRequest findInitiatorRequest(Integer id) throws SystemException;

}
