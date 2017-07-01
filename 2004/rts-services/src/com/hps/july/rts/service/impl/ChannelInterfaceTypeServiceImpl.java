package com.hps.july.rts.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.hps.july.rts.object.channelinterfacetype.ChannelInterfaceType;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.common.StoreObjectException;
import com.hps.july.rts.service.ChannelInterfaceTypeService;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 20:01:04
 */
public class ChannelInterfaceTypeServiceImpl
        extends DefaultProcess implements ChannelInterfaceTypeService {

    public Collection findAllChannelInterfaceTypes() {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelinterfacetypes ORDER BY id");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelInterfaceType interfaceType = new ChannelInterfaceType();
                interfaceType.setId(getInteger(rs, "id"));
                interfaceType.setInterfaceId(getInteger(rs, "interfaceid"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                interfaceType.setName(name);
                types.add(interfaceType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return types;
    }

    public Collection findChannelInterfaceTypesByInterfaceId(Integer iId) {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelinterfacetypes WHERE interfaceid = ? ORDER BY id");
            setInteger(pstmt, 1, iId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelInterfaceType interfaceType = new ChannelInterfaceType();
                interfaceType.setId(getInteger(rs, "id"));
                interfaceType.setInterfaceId(getInteger(rs, "interfaceid"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                interfaceType.setName(name);
                types.add(interfaceType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return types;
    }

    public ChannelInterfaceType findChannelInterfaceType(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelinterfacetypes WHERE id = ?");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelInterfaceType interfaceType = new ChannelInterfaceType();
                interfaceType.setId(getInteger(rs, "id"));
                interfaceType.setInterfaceId(getInteger(rs, "interfaceid"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                interfaceType.setName(name);
                return interfaceType;
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

    public void saveChannelInterfaceTypes(ChannelInterfaceType type)  throws CreateObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            //Integer serial = getNextKey(connection, "tables.rts");
            //type.setId(serial);
            //pstmt = connection.prepareStatement("INSERT INTO rts_channelinterfacetypes (id, name, interfaceid) VALUES (?, ?, ?)");
            pstmt = connection.prepareStatement("INSERT INTO rts_channelinterfacetypes (name, interfaceid) VALUES (?, ?)");
            //setInteger(pstmt, 1, type.getId());
            setString(pstmt, 1, type.getName());
            setInteger(pstmt, 2, type.getInterfaceId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new CreateObjectException("Cannot insert ChannelInterfaceType", new Integer(-1));
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

    public void updateChannelInterfaceTypes(ChannelInterfaceType type)  throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE rts_channelinterfacetypes SET name = ? WHERE id = ?");
            setString(pstmt, 1, type.getName());
            setInteger(pstmt, 2, type.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new StoreObjectException("Cannot update ChannelInterfaceTypes", new Integer(-2));
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

    public void removeChannelInterfaceTypes(ChannelInterfaceType type)  throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelinterfacetypes WHERE id = ?");
            setInteger(pstmt, 1, type.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelInterfaceType ["+type.getId()+"]", new Integer(-3));
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

    public void removeChannelInterfaceTypes(Integer ident)  throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelinterfacetypes WHERE id = ?");
            setInteger(pstmt, 1, ident);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelInterfaceType ["+ident+"]", new Integer(-3));
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
