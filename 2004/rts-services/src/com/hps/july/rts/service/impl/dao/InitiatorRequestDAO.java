package com.hps.july.rts.service.impl.dao;

import java.sql.Date;
import java.util.Collection;

import org.hibernate.type.Type;

import com.hps.july.rts.SystemException;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.april.auth.object.AuthInfo;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 09.02.2006
 * Time: 14:23:12
 */
public interface InitiatorRequestDAO {

    public Collection findInitiatorRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException;

    public void saveInitiatorRequests(InitiatorRequest initiatorRequest) throws SystemException;

    public void updateInitiatorRequest(InitiatorRequest initiatorRequest) throws SystemException;

    public void removeInitiatorRequest(InitiatorRequest initiatorRequest) throws SystemException;

    public Collection findInitiatorRequestsByStatus(Integer rtsStatus) throws SystemException;

    public Collection findInitiatorRequestsByStatusAndInConsRequest(Integer rtsStatus, AuthInfo authInfo, String currentRole) throws SystemException;

    public Collection findInitiatorRequestsByConsolidatedRequest(Integer consolidatedRequest) throws SystemException;

    public InitiatorRequest findInitiatorRequestById(Person currentInitiator, Integer id) throws SystemException;

    public InitiatorRequest findInitiatorRequest(Integer id) throws SystemException;


    /**
     * TODO: refactor to find qualifier
     */
    Collection findInitiatorRequests(Boolean isSerching,
                                     Boolean isNumber, String number,
                                     Boolean isDateType, String dateTypeSelect, Date dateType,
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
                                     RTSGroup currentInitiator, Boolean isAdmin);


}
