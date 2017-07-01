package com.hps.april.auth.service.dao;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Person;

/**
 * DAO ��� �������� ��������
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public interface OperatorDAO {

    /**
     * ��� ������� � ��������� Spring
     */
    String SERVICE_NAME = "april.auth.dao.operatorDAO";

    /**
     * ��������� ��������� �� ������. ���� �������� �� ������ ���������� null
     * @param operatorLogin
     * @return
     */
    Operator load(String operatorLogin);

    /**
     * ��������� ��������� �� ��� id. ���� �������� �� ������ ������������ null
     * @param operatorId
     * @return
     */
    Operator load(Integer operatorId);

    public Person loadPerson(Integer personId);

}
