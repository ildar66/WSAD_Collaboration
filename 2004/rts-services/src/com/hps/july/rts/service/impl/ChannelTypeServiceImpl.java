package com.hps.july.rts.service.impl;

import com.hps.july.rts.object.channeltype.ChannelType;
import com.hps.july.rts.core.process.DefaultProcess;

import com.hps.july.rts.service.*;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.april.common.StoreObjectException;

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
public class ChannelTypeServiceImpl extends DefaultProcess implements ChannelTypeService {

    public Collection findAllChannelTypes() {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channeltypes ORDER BY id");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelType setting = new ChannelType();
                setting.setId(getInteger(rs, "id"));
                String name = getString(rs, "name");
                if(name != null) name = name.trim();
                setting.setName(name);
                types.add(setting);
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

    public ChannelType findChannelType(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channeltypes WHERE id = ?");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ChannelType setting = new ChannelType();
                setting.setId(getInteger(rs, "id"));
				String name = getString(rs, "name");
				if(name != null) name = name.trim();
                setting.setName(name);
                return setting;
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

    public void saveChannelType(ChannelType type) throws CreateObjectException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            //Integer serial = getNextKey(connection, "tables.rts");
            //type.setId(serial);
            //pstmt = connection.prepareStatement("INSERT INTO rts_channeltypes (id, name) VALUES (?, ?)");
            pstmt = connection.prepareStatement("INSERT INTO rts_channeltypes (name) VALUES (?)");
            //setInteger(pstmt, 1, type.getId());
            setString(pstmt, 1, type.getName());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new CreateObjectException("Cannot insert ChannelSetting", new Integer(-1));
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

    public void updateChannelType(ChannelType type) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE rts_channeltypes SET name = ? WHERE id = ?");
            setString(pstmt, 1, type.getName());
            setInteger(pstmt, 2, type.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new StoreObjectException("Cannot update ChannelSetting", new Integer(-2));
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

    public void removeChannelType(ChannelType type) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channeltypes WHERE id = ?");
            setInteger(pstmt, 1, type.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelSetting ["+type.getId()+"]", new Integer(-3));
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

    public void removeChannelType(Integer ident) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channeltypes WHERE id = ?");
            setInteger(pstmt, 1, ident);
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelSetting ["+ident+"]", new Integer(-3));
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
