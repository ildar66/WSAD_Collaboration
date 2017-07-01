package com.hps.july.rts.service.impl;

import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.common.StoreObjectException;
import com.hps.july.rts.object.capacity.Capacity;
import com.hps.july.rts.service.CapacityService;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: ABaykov
 * Date: 27.04.2006
 * Time: 14:00:01
 * To change this template use File | Settings | File Templates.
 */
public class CapacityServiceImpl extends DefaultProcess implements CapacityService  {


    public Collection findAllCapacities() {
        ArrayList capacities = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM TN_Capacities ORDER BY capacityid");
            rs = pstmt.executeQuery();
			capacities.add(new Capacity(new Integer(0), ""));
            while(rs.next()) {
                Capacity setting = new Capacity();
                setting.setId(getInteger(rs, "capacityid"));
                String name = getString(rs, "capacityval");
                if(name != null) name = name.trim();
                setting.setName(name);
                setting.setIndexCap(getString(rs, "index_cap"));
                capacities.add(setting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return capacities;
    }

    public Capacity findCapacity(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM TN_Capacities WHERE capacityid = ?");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Capacity capacity = new Capacity();
                capacity.setId(getInteger(rs, "capacityid"));
                String name = getString(rs, "capacityval");
                if(name != null) name = name.trim();
                capacity.setName(name);
                capacity.setIndexCap(getString(rs, "index_cap"));
                return capacity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return null;
    }

    public void saveCapacity(Capacity capacity) throws CreateObjectException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            Integer id;
            rs = pstmt.executeQuery("select first 1 *  from tn_capacities order by capacityid desc");
            if(rs.next()) {
                id = getInteger(rs, "capacityid");
                id = new Integer(id.intValue() +1);
            } else id = new Integer(1);
            pstmt = connection.prepareStatement("INSERT INTO TN_Capacities (capacityid, capacityval, index_cap) VALUES (?,?,?)");
            setInteger(pstmt, 1, id);
            setString(pstmt, 2, capacity.getName());
            setString(pstmt, 3, capacity.getIndexCap());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new CreateObjectException("Cannot insert new Capacity (TN_Capacities) ", new Integer(-1));
        } catch (CreateObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreateObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateObjectException(new Integer(-255), e);
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public void updateCapacity(Capacity capacity) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE TN_Capacities SET capacityval = ?, index_cap = ? WHERE capacityid = ?");
            setString(pstmt, 1, capacity.getName());
            setString(pstmt, 2, capacity.getIndexCap());
            setInteger(pstmt, 3, capacity.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new StoreObjectException("Cannot update Capacity (TN_Capacities) with ID = "+capacity.getId(), new Integer(-2));
        } catch (StoreObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public void removeCapacity(Capacity capacity) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM TN_Capacities WHERE capacityid = ?");
            setInteger(pstmt, 1, capacity.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete Capacity ["+capacity.getId()+"]", new Integer(-3));
        } catch (RemoveObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public void removeCapacity(Integer ident) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM TN_Capacities WHERE capacityid = ?");
            setInteger(pstmt, 1, ident);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete Capacity ["+ident+"]", new Integer(-3));
        } catch (RemoveObjectException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-254), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoveObjectException(new Integer(-255), e);
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
    }
}
