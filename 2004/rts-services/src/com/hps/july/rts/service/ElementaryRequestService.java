package com.hps.july.rts.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.common.Service;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;
import java.util.Date;

/*
 * RTS ("Заявки на расширение региональной ТС")
 *
 * @author ABaykov
 * Created on 21.02.2006
 */
public interface ElementaryRequestService extends Service {

	public static final String SERVICE_NAME = "july.rts.service.elementaryRequest";

	public Collection findAllElementaryRequests(Person regManager) throws SystemException;

	public ElementaryRequest findElementaryRequest(AuthInfo authInfo, Integer integer) throws SystemException;

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
											AuthInfo regManager) throws SystemException;

    public Collection findElementaryRequests(AuthInfo executor, Boolean isSerching,
                                            Boolean isNumber,String number,
                                            Boolean isDateType,String dateTypeSelect,Date dateType,
                                            Boolean isRtsRequestType,String rtsRequestType,
                                            Boolean isRtsStatus,String rtsStatus,
                                            Boolean isChannelSetting,String channelSetting,
                                            Boolean isChannelType,String channelType,
                                            String priority,
                                            Boolean isSearchAddrA,String searchAddrA,
                                            Boolean isSearchAddrB,String searchAddrB) throws SystemException;

    public Collection findElementaryRequestsByConsolidatedRequest(Integer requestId, AuthInfo authInfo) throws SystemException;

	public void saveElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException;

	public void updateElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException;

	public void removeElementaryRequest(AuthInfo authInfo, ElementaryRequest request) throws SystemException;
    // При генерации кода канала - последние 2 цифры - порядковый номер
    // всех элем. заявок с ТАКИМ!!! кодом (т.е. совпадают все цифры кроме последних двух)
    public Integer getChannelCodeOrderNumber(String channelCode);

    // Признак, связаны ли последовательно все адреса/позиции (Position, не устройства - Equipment)
    // на всех элементарных заявках данной Консолидированной
    public boolean isRelatedElementaryRequests(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException;

    // Признак заполненности Исполнителей
    // на всех элементарных заявках данной Консолидированной
    public boolean isExecutorsFilled(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException;

    // Перенумерация при удалении заявки
    // Изменение номеров заявок - установление последовательности, если была удалена заявка в списке
    public void reOrderElementaryRequestNumbers(ConsolidatedRequest consolidatedRequest, AuthInfo authInfo) throws SystemException;

}
