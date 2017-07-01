package com.hps.july.rts.service.impl;

import com.hps.july.rts.object.counteragent.CounterAgent;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.july.rts.service.CounterAgentService;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: ABaykov
 * Date: 11.05.2006
 * Time: 17:00:01
 * To change this template use File | Settings | File Templates.
 */
public class CounterAgentServiceImpl extends DefaultProcess implements CounterAgentService  {

    public Collection findAllCounterAgents() {
        ArrayList counterAgents = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM organizations WHERE organization = 1 OR ischannalrenter = 'Y' ORDER BY organization");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                CounterAgent counterAgent = new CounterAgent();
                counterAgent.setId(getInteger(rs, "organization"));
                counterAgent.setName(getString(rs, "name"));
                counterAgent.setShortName(getString(rs, "shortname"));
                counterAgent.setContactName(getString(rs, "contactlist"));
                counterAgents.add(counterAgent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return counterAgents;
    }

    public CounterAgent findCounterAgent(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CounterAgent counterAgent = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM organizations WHERE organization = ? ");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                counterAgent = new CounterAgent();
                counterAgent.setId(getInteger(rs, "organization"));
                counterAgent.setName(getString(rs, "name"));
                counterAgent.setShortName(getString(rs, "shortname"));
                counterAgent.setContactName(getString(rs, "contactlist"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return counterAgent;
    }

    public CounterAgent findCounterAgent(CounterAgent counterAgent) {
        return findCounterAgent(counterAgent.getId());
    }
/*
    public void saveCounterAgent(CounterAgent counterAgent) throws CreateObjectException {
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
            pstmt = connection.prepareStatement("INSERT INTO TN_Capacities (capacityid, capacityval) VALUES (?,?)");
            setInteger(pstmt, 1, id);
            setString(pstmt, 2, capacity.getName());
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
            pstmt = connection.prepareStatement("UPDATE TN_Capacities SET capacityval = ? WHERE capacityid = ?");
            setString(pstmt, 1, capacity.getName());
            setInteger(pstmt, 2, capacity.getId());
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
*/
}
