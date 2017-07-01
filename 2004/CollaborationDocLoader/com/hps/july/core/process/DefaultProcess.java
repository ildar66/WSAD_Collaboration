package com.hps.july.core.process;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 06.04.2005
 * Time: 17:31:35
 */
public class DefaultProcess extends com.hps.july.core.process.Process {

    public Integer getPeopleIdByLogin(String userName) {
        Integer result = null;
        Connection connection = null;
        try {
            connection = getConnection();
            result = getPeopleIdByLogin(connection, userName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (Exception e) {}
        }
        return result;
    }

    public Integer getPeopleIdByLogin(Connection connection, String userName) {
        Integer result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("SELECT p.man FROM operators o, people p WHERE o.man = p.man AND o.loiginid = ?");
            setString(pstmt, 1, userName);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = getInteger(rs, "man");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
        }
        return result;
    }

}
