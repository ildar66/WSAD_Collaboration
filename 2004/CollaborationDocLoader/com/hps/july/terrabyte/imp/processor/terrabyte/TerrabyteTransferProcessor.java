package com.hps.july.terrabyte.imp.processor.terrabyte;

import com.hps.july.terrabyte.imp.processor.AbstractFileTreeLoaderProcessor;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.helper.BSProjectFilesHelper;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.essence.BSProjectFileInfo;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.Collection;
import java.util.Iterator;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 04.03.2006
 * Time: 20:09:35
 */
public class TerrabyteTransferProcessor  extends AbstractFileTreeLoaderProcessor {


    public TerrabyteTransferProcessor(String hostName, Integer port) {
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        //Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {

             start(command.getConnection());

        } catch(Exception e) {
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }

    }

    public void start(Connection connection) {

        PreparedStatement pstmt = null;
        PreparedStatement update = null;
        ResultSet rs = null;

        try {
            String sql = "select versionid, real_path, real_path_dump  from tbs_versions ";
            String sqlUpdate = " UPDATE tbs_versions SET real_path = ? , real_path_dump = ? WHERE versionid = ? ";
            pstmt = connection.prepareStatement(sql);
            update = connection.prepareStatement(sqlUpdate);
            rs = pstmt.executeQuery();
            int i = 1;
            while(rs.next()) {

                Integer id = getInteger(rs, "versionid");
                String realPath = getString(rs, "real_path");
                String realPathDump = getString(rs, "real_path_dump");
                //if(realPath == null) continue;
                //String realPathDump = transformPath(realPath);
                update.clearParameters();
                setString(update, 1, realPathDump);
                setString(update, 2, realPathDump);
                setInteger(update, 3, id);
                update.executeUpdate();
                //System.out.println((i++) + " ["+realPath+"] ["+realPathDump+"] ");
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(update != null) update.close(); } catch(Exception e) {}
        }
        return;
    }

    private String transformPath(String path) {
        StringBuffer s = new StringBuffer();
        s.append("/storehouse/storage1/");
        String s1 = path.substring("W:\\\\".length());
        s1 = s1.replace('\\', '/');
        s.append(s1);
        //System.out.println("["+path+"]");
        //System.out.println("["+s.toString()+"]");
        return s.toString();
    }


}
