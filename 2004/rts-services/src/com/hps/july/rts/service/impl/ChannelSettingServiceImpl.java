package com.hps.july.rts.service.impl;

import com.hps.july.rts.object.channelsetting.ChannelSetting;
import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.april.common.StoreObjectException;

import com.hps.july.rts.service.ChannelSettingService;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 07.12.2005
 * Time: 20:01:04
 */
public class ChannelSettingServiceImpl extends DefaultProcess implements ChannelSettingService {

    public Collection findGroupChannelSettings(Integer groupId) {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT  cs.id, cs.name, ics.groupid FROM rts_channelstlmnt cs, outer (rts_init2chanstlmnt ics) ");
            sql.append(" WHERE cs.id = ics.chanstlmntid AND ics.groupid = ?");
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt, 1, groupId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelSetting setting = new ChannelSetting();
                setting.setId(getInteger(rs, "id"));
                setting.setGroupId(getInteger(rs, "groupid"));
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

    public void saveGroupsChannelSettings(Integer groupId, Collection channels) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO rts_init2chanstlmnt (groupid, chanstlmntid) VALUES (? , ?)");
            pstmt1 = connection.prepareStatement("DELETE FROM rts_init2chanstlmnt WHERE groupid = ? ");
            pstmt = connection.prepareStatement(sql.toString());
            setInteger(pstmt1, 1, groupId);
            pstmt1.executeUpdate();
            for(Iterator iterator = channels.iterator(); iterator.hasNext();) {
                ChannelSetting setting = (ChannelSetting) iterator.next();
                if(setting != null && setting.getId() != null) {
                    pstmt.clearParameters();
                    setInteger(pstmt, 1, groupId);
                    setInteger(pstmt, 2, setting.getId());
                    pstmt.executeUpdate();
                }
            }
            connection.commit();
        } catch(Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {}
            e.printStackTrace();
            throw new StoreObjectException(new Integer(-2), e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(connection != null) connection.close(); } catch(Exception e) {}
        }
    }


    public Collection findAllChannelSettings() {
        ArrayList types = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelstlmnt ORDER BY id");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ChannelSetting setting = new ChannelSetting();
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

    public ChannelSetting findChannelSetting(Integer ident) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM rts_channelstlmnt WHERE id = ?");
            setInteger(pstmt, 1, ident);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ChannelSetting setting = new ChannelSetting();
                setting.setId(getInteger(rs, "id"));
                String id = rs.getString("id");
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

    public void saveChannelSetting(ChannelSetting channelSetting) throws CreateObjectException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            //Integer serial = getNextKey(connection, "tables.rts");
            //channelSetting.setId(serial);
            //pstmt = connection.prepareStatement("INSERT INTO rts_channelstlmnt (id, name) VALUES (?, ?)");
            pstmt = connection.prepareStatement("INSERT INTO rts_channelstlmnt (name) VALUES (?)");
            //setInteger(pstmt, 1, channelSetting.getId());
            setString(pstmt, 1, channelSetting.getName());
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

    public void updateChannelSetting(ChannelSetting channelSetting) throws StoreObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("UPDATE rts_channelstlmnt SET name = ? WHERE id = ?");
            setString(pstmt, 1, channelSetting.getName());
            setInteger(pstmt, 2, channelSetting.getId());
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

    public void removeChannelSetting(ChannelSetting channelSetting) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelstlmnt WHERE id = ?");
            setInteger(pstmt, 1, channelSetting.getId());
            int result = pstmt.executeUpdate();
            if(result != 1) throw new RemoveObjectException("Cannot delete ChannelSetting ["+channelSetting.getId()+"]", new Integer(-3));
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

    public void removeChannelSetting(Integer ident) throws RemoveObjectException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement("DELETE FROM rts_channelstlmnt WHERE id = ?");
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
