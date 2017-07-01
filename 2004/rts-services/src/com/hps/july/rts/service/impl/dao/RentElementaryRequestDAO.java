package com.hps.july.rts.service.impl.dao;

import java.util.Collection;

import org.hibernate.type.Type;

import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.RentElementaryRequest;

/**
 * DAO
 *
 * ������������ ��������� (�� ������)
 * ����������� ����� ��������� ��. ������ �� ���������
 * �� ���� ���������� ����� ����� ����������� ����� ������
 * ���. ����������
 *
 *
 * @author ABaykov
 * Created on 04.09.2006
 */
public interface RentElementaryRequestDAO {

    public Collection findRentElemenaryRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException;

    public RentElementaryRequest findRentElementaryRequest(Integer id) throws SystemException;

    public void saveRentElementaryRequest(RentElementaryRequest request) throws SystemException;

    public void updateRentElementaryRequest(RentElementaryRequest request) throws SystemException;

    public void removeRentElementaryRequest(RentElementaryRequest request) throws SystemException;

}
