package com.hps.april.auth.service.dao;

import java.util.Collection;
import java.util.List;

import com.hps.april.auth.object.JulyRole;

/**
 * DAO ��� �������� ���� 
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public interface RoleDAO {

    String SERVICE_NAME = "april.auth.dao.roleDAO";

    /**
     * ��������� ���� �� �����. ���� ���� �� �������,
     * ���������� null
     * @param roleName ��� ����
     * @return ����
     */
    JulyRole load(String roleName);

    /**
     * ��������� ������ ����� �� �������������� ������������
     * @param operatorId
     * @return
     */
    Collection loadRoles(Integer operatorId);
    /**
     * ��������� ������ �����
     * @return
     */
    List getRoleList();
    
    List getRoleList(String taskName);

}
