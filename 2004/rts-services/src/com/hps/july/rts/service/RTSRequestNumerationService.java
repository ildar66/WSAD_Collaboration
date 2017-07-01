package com.hps.july.rts.service;

import com.hps.april.auth.object.Person;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;

/**
 * ��������� ������� ����������������� ������
 * @author Dimitry Krivenko 
 * 16.02.2006
 */
public interface RTSRequestNumerationService {

    String SERVICE_NAME = "july.rts.RTSRequestNumerationService";

    /**
     * ��������� ������ ������ ����������. ������ IINNN-YYY,
     * ��� 	II - ����� ����������,
     * 		NNNN - ���������� ����� ������,
     * 		YY - ��� ������.
     * ��������� ���������� ������ ������ ���
     * @param person
     * @return
     */
    String generateInitiatorRequestNumber(Person person);

    /**
     * KNNNN-YY
     * @return
     */
    String generateConsolidatedRequestNumber();

    /**
     * KNNNN.nn-YY
     * @param consolidatedRequest
     * @return
     */
    String generateElementaryRequestNumber(ConsolidatedRequest consolidatedRequest);

    /**
     * ���������� ������ ��. ������ � �������
     * KNNNN.nn-YY
     * @param consolidatedRequest
     * @return
     */
    String generateElementaryRequestNumber(long elementaryCode, ConsolidatedRequest consolidatedRequest);

    /**
     * KNNNN.nn.mm-YY
     * @param consolidatedRequest
     * @return
     */
    String generateRentElementaryRequestNumber(ElementaryRequest elementaryRequest);
    
	String getInfoAboutBean();

}
