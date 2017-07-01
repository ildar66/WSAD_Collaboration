package com.hps.july.rts.service;

import java.util.Collection;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.common.Service;
import com.hps.july.rts.SystemException;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.object.request.RentElementaryRequest;

/*
 * RTS ("Заявки на расширение региональной ТС")
 * Интерфейс - объекта со служебными методами
 * 
 * Элементарная ПОДзаявка (по аренде)
 * Исполнитель может разбивать Эл. заявку на подзаявки
 * На один арендуемый канал может оформляться более одного
 * доп. соглашения
 *
 * @author ABaykov
 * Created on 24.08.2006
 */
public interface RentElementaryRequestService extends Service {

	public static final String SERVICE_NAME = "july.rts.service.rentElementaryRequest";

	public Collection findAllRentElementaryRequests(Person regManager) throws SystemException;

	public RentElementaryRequest findRentElementaryRequest(AuthInfo authInfo, Integer integer) throws SystemException;

    public Collection findRentElementaryRequestsByElementaryRequest(Integer requestId, AuthInfo authInfo) throws SystemException;

	public void saveRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException;

	public void updateRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException;

	public void removeRentElementaryRequest(AuthInfo authInfo, RentElementaryRequest request) throws SystemException;

    // При генерации кода канала - последние 2 цифры - порядковый номер
    // всех элем. заявок с ТАКИМ!!! кодом (т.е. совпадают все цифры кроме последних двух)
    public Integer getChannelCodeOrderNumber(String channelCode);

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
*/
}
