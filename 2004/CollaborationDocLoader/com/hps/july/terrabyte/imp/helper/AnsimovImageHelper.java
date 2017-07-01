package com.hps.july.terrabyte.imp.helper;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.AppLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 11:00:50
 */
public class AnsimovImageHelper extends DatabaseHelper {

    public static Collection getFiles(Connection connection) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select \n" +
                    "  photo,\n" +
                    "  storageplace,\n" +
                    "  description,\n" +
                    "  date ,\n" +
                    "  photofilename,\n" +
                    "  photoimage,\n" +
                    "  photographer,\n" +
                    "  mime_type,\n" +
                    "  exphotgrname, \n" +
                    "  grpoupname, \n" +
                    "  groupname , \n" +
                    "  size ,\n" +
                    "  modified,\n" +
                    "  created ,\n" +
                    "  createdby,\n" +
                    "  modifiedby,\n" +
                    "  file_size,\n" +
                    "  file_modified,\n" +
                    "  file_created \n" +
                    " from photos\n" +
                    "  where \n" +
                    "   (createdby = 325 or modifiedby = 325) " +
                    " AND photoimage is not null ";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer storagePlace = getInteger(rs, "storageplace");
                String photofilename = getString(rs, "photofilename").trim();
                //AppLog.log("["+storagePlace+"] ["+photofilename+"]");
                String exphotgrname = getString(rs, "exphotgrname");
                //String groupname = getString(rs, "groupname");
                String description = getString(rs, "description");
                Integer photographer = getInteger(rs, "photographer");
                byte [] photo = getByteArray(rs, "photoimage");
                FileOutputStream fos = new FileOutputStream("C:\\Warehouse\\"+photofilename);
                fos.write(photo);
                fos.flush();
                fos.close();
                File file =  new File("C:\\Warehouse\\"+photofilename);
                SimpleFileInfo info = new SimpleFileInfo(file);
                info.setStoragePlace(storagePlace);
                info.setCreator(new Integer(325));
                info.setType(new Integer(1));
                info.setDescr(description);
                list.add(info);
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            return list;
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new BaseException("Cannot insert node ");
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }


}
