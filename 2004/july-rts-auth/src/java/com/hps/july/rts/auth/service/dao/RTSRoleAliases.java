package com.hps.july.rts.auth.service.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import com.hps.july.rts.auth.object.RTSRole;

public class RTSRoleAliases implements InitializingBean {

    private Map roleNameAliasTable = new HashMap();

    public Map getRoleNameAliasTable() {
        return roleNameAliasTable;
    }

    public void setRoleNameAliasTable(Map roleNameAliasTable) {
        this.roleNameAliasTable = roleNameAliasTable;
    }

    public void afterPropertiesSet() throws Exception {
        roleNameAliasTable.put("rts_initiator", RTSRole.INITIATOR); // ���������
        roleNameAliasTable.put("rts_regionManager", RTSRole.REGION_MANAGER); // ������������ ��������
        roleNameAliasTable.put("rts_performer", RTSRole.PERFORMER); // �����������
        roleNameAliasTable.put("rts_topManager", RTSRole.TOP_MANAGER); // �������� ��������
        roleNameAliasTable.put("rts_assertor", RTSRole.ASSERTOR); // ������������
        roleNameAliasTable.put("rts_testor", RTSRole.TESTER); // �����������
        roleNameAliasTable.put("rts_responsible", RTSRole.SETS_RESPONSIBLE); // ������������� �� ����
        roleNameAliasTable.put("rts_arendaResponsible", RTSRole.ARENDA_RESPONSIBLE); // ������������� �� ������
        roleNameAliasTable.put("rts_arendaPerformer", RTSRole.ARENDA_PERFORMER); // ����������� �� ������
        roleNameAliasTable.put("rts_manager", RTSRole.MANAGER); // ������������
    }

    public String getRoleAlias(Integer roleId){
        Set keySet = roleNameAliasTable.keySet();
        for (Iterator keys = keySet.iterator(); keys.hasNext();) {
            String roleAlias = (String) keys.next();
            if (roleId.equals(roleNameAliasTable.get(roleAlias))) return roleAlias;
        }
        return null;
    }

    public Integer getRoleId(String roleAlias){
        return (Integer)roleNameAliasTable.get(roleAlias);
    }

}
