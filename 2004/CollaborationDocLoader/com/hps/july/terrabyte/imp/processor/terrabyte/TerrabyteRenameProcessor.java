package com.hps.july.terrabyte.imp.processor.terrabyte;

import com.hps.july.terrabyte.imp.processor.AbstractFileTreeLoaderProcessor;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.helper.BSProjectFilesHelper;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.essence.BSProjectFileInfo;
import com.hps.july.terrabyte.imp.essence.PositionDirInfo;
//by vad
//import com.hps.july.terrabyte.server.hibernate.FileAccessHelper;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.Collection;
import java.util.Iterator;
import java.io.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Процессор для изменения имен файлов для процедуры переноса хранилища файлов
 * 
 * для работы требуется сервер Террабайт
 * 
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 04.03.2006
 * Time: 20:09:35
 */
public class TerrabyteRenameProcessor  extends AbstractFileTreeLoaderProcessor {


    public TerrabyteRenameProcessor(String hostName, Integer port) {
    }

    String dirName = null;
    String copyTo = null;
    int proceed = 0 ;
    int all = 0 ;


    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        //Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {
             dirName = (String)command.getParameter(CommandNames.FILE_CATALOG);
             copyTo = (String)command.getParameter(CommandNames.FILE_CATALOG + "1");
             copyAllDir(dirName, copyTo);
             start(command.getConnection());
             //copyAllFiles(dirName);

        } catch(Exception e) {
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }

    }

    public void copyAllDir(String dirName, String copyTo) {
        File dir = new File(dirName);
        File copyToDir = new File(copyTo);
        if(!dir.isDirectory()) return;
        if(!copyToDir.isDirectory()) return;
        File [] dirList = dir.listFiles();
        int countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                String newDirName = (dirName + File.separator + currDir.getName());
                String newCopyTo = (copyTo + File.separator + currDir.getName());
                File newCopyToDir = new File(newCopyTo);
                if(!newCopyToDir.exists()) newCopyToDir.mkdir();
                copyAllDir(newDirName, newCopyTo);
            }
        }
/*
        PositionDirInfo[] list = new PositionDirInfo[countDir];
        countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                PositionDirInfo info = new PositionDirInfo(currDir);
                Integer storagePlace = null;
                try {
                    storagePlace = findStoragePlace(info);
                } catch(BaseException e) {}
                //if(info.getNumber() == null || (info.getNumber() != null && info.getNumber().compareTo("a000") < 0)) continue;
                ////AppLog.log("NAME ["+currDir.getName()+"] ST ["+storagePlace+"]" );
                info.setStorageplace(storagePlace);
                list[countDir++] = info;
            }
        }
        dirList = null;
        dir = null;
        return list;
*/

    }

    public void copyAllFiles(String dirName) {
        File dir = new File(dirName);
        if(!dir.isDirectory()) return;
        File [] dirList = dir.listFiles();
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                copyAllFiles(currDir.getAbsolutePath());
            } else {
                //copyfiles !
                String newFileName = transformPath(currDir.getAbsolutePath());
                FileInputStream in = null;
                FileOutputStream out = null;
                try {
                    in = new FileInputStream(currDir.getAbsolutePath());
                    out = new FileOutputStream(newFileName);
                    byte[]  buffer = new byte[4096];
                    int count = 0;
                    while(count!=-1) {
                        count = in.read(buffer);
                        if(count!=-1) {
                            out.write(buffer,0,count);
                        }
                    }
                    out.flush();
                    buffer=null;
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    try { if(in != null) in.close(); } catch (Exception e) {}
                    try { if(out != null) out.close(); } catch (Exception e) {}
                }



                //System.out.println("");
            }
        }
/*
        PositionDirInfo[] list = new PositionDirInfo[countDir];
        countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                PositionDirInfo info = new PositionDirInfo(currDir);
                Integer storagePlace = null;
                try {
                    storagePlace = findStoragePlace(info);
                } catch(BaseException e) {}
                //if(info.getNumber() == null || (info.getNumber() != null && info.getNumber().compareTo("a000") < 0)) continue;
                ////AppLog.log("NAME ["+currDir.getName()+"] ST ["+storagePlace+"]" );
                info.setStorageplace(storagePlace);
                list[countDir++] = info;
            }
        }
        dirList = null;
        dir = null;
        return list;
*/

    }

    public boolean copyFile(String fileName, String newFileName) {

        //copyfiles !
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(fileName);
            out = new FileOutputStream(newFileName);
            byte[]  buffer = new byte[4096];
            int count = 0;
            while(count!=-1) {
                count = in.read(buffer);
                if(count!=-1) {
                    out.write(buffer,0,count);
                }
            }
            out.flush();
            buffer=null;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        } finally {
            try { if(in != null) in.close(); } catch (Exception e) {}
            try { if(out != null) out.close(); } catch (Exception e) {}
        }
        return true;
    }

    public void start(Connection connection) {

        PreparedStatement pstmt = null;
        PreparedStatement update = null;
        ResultSet rs = null;
        int i = 0;
        try {
            String sql = "select versionid, real_path, relative_path_dump  from tbs_versions WHERE iscopy = 0";
            String sqlUpdate = " UPDATE tbs_versions SET real_path_dump = ?, relative_path = ?, relative_path_dump = ?, iscopy = ?  WHERE versionid = ? ";
            pstmt = connection.prepareStatement(sql);
            update = connection.prepareStatement(sqlUpdate);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                Integer id = getInteger(rs, "versionid");
                String realPath = getString(rs, "real_path");
                //String relativePath1 = getString(rs, "relative_path_dump");
                if(realPath == null) continue;
                String realPathDump = transformPath(realPath);
                String relativePath = realPathDump.substring(copyTo.length() + 1);
                relativePath = relativePath.replace('\\', '/');
                update.clearParameters();
                boolean isCopyBool = false;
                //File f = new File(relativePath1);
                //System.out.println("["+f.getAbsolutePath()+"]["+f.delete()+"]");
                isCopyBool = copyFile(realPath, realPathDump);
                Integer isCopy = (isCopyBool)?new Integer(1):new Integer(0);
                setString(update, 1, realPath);
                setString(update, 2, relativePath);
                setString(update, 3, realPathDump);
                setInteger(update, 4, isCopy);
                setInteger(update, 5, id);
                update.executeUpdate();
                System.out.println((++i) + " ["+realPath+"] ["+realPathDump+"] ");
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
        System.out.println("All row ! ["+i+"]");
        return;
    }

    private String transformPath(String path) {
        StringBuffer s = new StringBuffer();
        //s.append("/storehouse/storage1/");
        String s1 = path.substring((dirName + File.separator).length());
        //s1 = s1.replace('\\', '/');
        int lastIndex = s1.lastIndexOf(File.separator);
        String justPath = s1.substring(0, lastIndex + 1);
        String justName = s1.substring(lastIndex + 1);
		// by vad
		//uncomment to work 
        String newName = "";//FileAccessHelper.transformName(justName);  
        s.append(copyTo).append(File.separator);
        s.append(justPath);
        s.append(newName);
        //System.out.println("["+path+"]["+s.toString()+"]");
        return s.toString();
    }

    private String getRelativePath(String path) {
        StringBuffer s = new StringBuffer();
        String s1 = path.substring(0, (copyTo + File.separator).length());
        s.append(s1);
        //System.out.println("["+path+"]["+s.toString()+"]");
        return s.toString();
    }


}
