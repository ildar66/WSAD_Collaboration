package com.hps.july.rts.service.impl.dao;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;
import java.util.Date;

import org.hibernate.type.Type;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.02.2006
 * Time: 19:01:13
 */
public interface ConsolidatedRequestDAO {

    public Collection findAllConsolidatedRequests(Person keyManager) throws SystemException;

    public ConsolidatedRequest findConsolidatedRequest(Integer id, AuthInfo currentAuthInfo) throws SystemException;

    public Collection findConsolidatedRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException;

    public void saveConsolidatedRequest(ConsolidatedRequest request) throws SystemException;

    public void updateConsolidatedRequest(ConsolidatedRequest request) throws SystemException;

    public void removeConsolidatedRequest(ConsolidatedRequest request) throws SystemException;

	public Collection findConsolidatedRequests(Boolean isSerching,
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
                                               AuthInfo currentAuthInfo, Boolean isAdmin);

	public ConsolidatedRequest findConsolidatedRequest(Integer consolidatedRequestId);

}
