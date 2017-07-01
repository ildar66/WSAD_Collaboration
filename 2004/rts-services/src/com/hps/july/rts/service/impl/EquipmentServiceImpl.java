package com.hps.july.rts.service.impl;

import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.service.EquipmentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 10.02.2006
 * Time: 21:13:07
 */
public class EquipmentServiceImpl extends DefaultProcess
    implements EquipmentService {

    public String getFullEquipmentName(Integer equipmentId, Integer postionId)
        throws SystemException {
        StringBuffer result = new StringBuffer();
        Connection connection = null;
        try {
            connection = getConnection();
            result.append(getEquipmentName(connection, equipmentId)).append(" ; ");
            result.append(getPositionAddress(connection, postionId)).append(" ; ");
            result.append(getBeenet(connection, postionId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return result.toString();
    }

    public String getEquipmentName(Integer equipmentId) throws SystemException {
        String name = null;
        Connection connection = null;
        try {
            connection = getConnection();
            name = getEquipmentName(connection, equipmentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return name;
    }

    private String getEquipmentName(Connection connection, Integer equipmentId) throws SystemException {
        String name = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("execute procedure gettypeequipment(?)");
            setInteger(pstmt, 1, equipmentId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                name = getString(rs, 1);
                if(name != null) name = name.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
        return name;
    }

    public String getPositionAddress(Integer positionId) throws SystemException {
        StringBuffer address = new StringBuffer();
        Connection connection = null;
        try {
            connection = getConnection();
            address.append(getPositionAddress(connection, positionId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return address.toString();
    }

    private String getPositionAddress(Connection connection, Integer positionId) throws SystemException {
        StringBuffer address = new StringBuffer();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("SELECT s.address  FROM positions p, storageplaces s WHERE s.storageplace= p.storageplace AND p.storageplace = ?");
            setInteger(pstmt, 1, positionId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String temp = getString(rs, 1);
                if(temp != null) {
                    temp = temp.trim();
                    address.append(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
        return address.toString();
    }

    public String getBeenet(Integer positionId) throws SystemException {
        StringBuffer beenetids = new StringBuffer();
        Connection connection = null;
        try {
            connection = getConnection();
            beenetids.append(getBeenet(connection, positionId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return beenetids.toString();
    }

    private String getBeenet(Connection connection, Integer positionId) throws SystemException {
        StringBuffer beenetids = new StringBuffer();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("SELECT beenetid FROM  beenetobjects WHERE position = ?");
            setInteger(pstmt, 1, positionId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String beenetid = getString(rs, 1);
                if(beenetid != null) {
                    beenetid = beenetid.trim();
                    beenetids.append(beenetid).append(" ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
        return beenetids.toString();
    }
}
