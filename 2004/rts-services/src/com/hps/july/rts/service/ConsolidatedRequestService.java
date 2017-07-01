package com.hps.july.rts.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.common.Service;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;
import java.util.Date;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 31.01.2006
 * Time: 15:57:55
 */
public interface ConsolidatedRequestService extends Service {

    public static final String SERVICE_NAME = "july.rts.service.consolidatedRequest";

    public Collection findAllConsolidatedRequests(AuthInfo authInfo) throws SystemException;

    /**
     * @deprecated use findConsolidatedRequest(AuthInfo authInfo, Integer requestId)
     * @param id
     * @param keyManager
     * @return
     * @throws SystemException
     */
    public ConsolidatedRequest findConsolidatedRequest(Integer id, AuthInfo authInfo) throws SystemException;
/*
	public Collection findConsolidatedRequests(Boolean isSerching, 
											Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
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
											Person keyManager) throws SystemException;
*/
	public Collection findConsolidatedRequests(Boolean isSerching, 
											Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
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
											AuthInfo authInfo) throws SystemException;

    public Collection findConsolidatedRequests(Boolean isSerching,
                                            Boolean isNumber,String number,
											Boolean isDateType,String dateTypeSelect,Date dateType,
                                            Boolean isRtsRequestType,String rtsRequestType,
                                            Boolean isRtsStatus,String rtsStatus,
                                            Boolean isChannelSetting,String channelSetting,
                                            String priority,
                                            Boolean isExecutor, Integer executor,
                                            Boolean isSearchAddrA, String searchAddrA,
                                            Boolean isSearchAddrB, String searchAddrB,
                                            AuthInfo person, RTSRole role) throws SystemException;

    public void saveConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException;

    public void updateConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException;

    public void removeConsolidatedRequest(AuthInfo authInfo, ConsolidatedRequest request) throws SystemException;

	public ConsolidatedRequest findConsolidatedRequest(AuthInfo authInfo, Integer integer);
	
	// При генерации кода канала - последние 2 цифры - порядковый номер 
	// всех конс. заявок с ТАКИМ!!! кодом (т.е. совпадают все цифры кроме последних двух)
	public Integer getChannelCodeOrderNumber(String channelCode);
	public String getInfoAboutBean();
	 
}
