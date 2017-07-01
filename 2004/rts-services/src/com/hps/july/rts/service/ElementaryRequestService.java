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
 * RTS ("������ �� ���������� ������������ ��")
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
    // ��� ��������� ���� ������ - ��������� 2 ����� - ���������� �����
    // ���� ����. ������ � �����!!! ����� (�.�. ��������� ��� ����� ����� ��������� ����)
    public Integer getChannelCodeOrderNumber(String channelCode);

    // �������, ������� �� ��������������� ��� ������/������� (Position, �� ���������� - Equipment)
    // �� ���� ������������ ������� ������ �����������������
    public boolean isRelatedElementaryRequests(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException;

    // ������� ������������� ������������
    // �� ���� ������������ ������� ������ �����������������
    public boolean isExecutorsFilled(ConsolidatedRequest consRequest, AuthInfo authInfo) throws SystemException;

    // ������������� ��� �������� ������
    // ��������� ������� ������ - ������������ ������������������, ���� ���� ������� ������ � ������
    public void reOrderElementaryRequestNumbers(ConsolidatedRequest consolidatedRequest, AuthInfo authInfo) throws SystemException;

}
