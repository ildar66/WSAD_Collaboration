package com.hps.july.rts.service.impl;

import com.hps.july.rts.object.channelinterface.ChannelInterface;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.common.StoreObjectException;

import com.hps.july.rts.service.ChannelInterfaceService;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 20:01:04
 */
public class ChannelInterfaceServiceImpl
        extends DefaultProcess implements ChannelInterfaceService {

    public Collection findAllChannelInterface() {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelinterfaces ORDER BY id");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelInterface inter = new ChannelInterface();
                inter.setId(getInteger(rs, "id"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                inter.setName(name);
                types.add(inter);
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

    public ChannelInterface findChannelInterface(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelinterfaces WHERE id = ?");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ChannelInterface inter = new ChannelInterface();
                inter.setId(getInteger(rs, "id"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                inter.setName(name);
                return inter;
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

    public void saveChannelInterface(ChannelInterface channelInterface) throws CreateObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            //Integer serial = getNextKey(connection, "tables.rts");
            //channelInterface.setId(serial);
            //pstmt = connection.prepareStatement("INSERT INTO rts_channelinterfaces (id, name) VALUES (?, ?)");
            pstmt = connection.prepareStatement("INSERT INTO rts_channelinterfaces (name) VALUES (?)");
            //setInteger(pstmt, 1, channelInterface.getId());
            setString(pstmt, 1, channelInterface.getName());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new CreateObjectException("Cannot insert ChannelInterface", new Integer(-1));
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

    public void updateChannelInterface(ChannelInterface channelInterface) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE rts_channelinterfaces SET name = ? WHERE id = ?");
            setString(pstmt, 1, channelInterface.getName());
            setInteger(pstmt, 2, channelInterface.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new StoreObjectException("Cannot update ChannelInterface", new Integer(-2));
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

    public void removeChannelInterface(ChannelInterface channelInterface) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelinterfaces WHERE id = ?");
            setInteger(pstmt, 1, channelInterface.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelInterface ["+channelInterface.getId()+"]", new Integer(-3));
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

    public void removeChannelInterface(Integer ident) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelinterfaces WHERE id = ?");
            setInteger(pstmt, 1, ident);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelInterface ["+ident+"]", new Integer(-3));
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
