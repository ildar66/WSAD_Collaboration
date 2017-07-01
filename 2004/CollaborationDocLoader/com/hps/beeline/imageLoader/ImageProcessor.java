package com.hps.beeline.imageLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LoaderException;
import com.hps.beeline.LogHelper;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.framework.log.MessageService;
import com.hps.beeline.imageLoader.data.ImageDirInfo;
import com.hps.beeline.imageLoader.helper.PhotoDatabaseHelper;
import com.hps.beeline.loader.DocConfig;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.MessageService;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;
import java.util.HashMap;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:34:59
 * To change this template use File | Settings | File Templates.
 */
public class ImageProcessor {

    private ImageProcessor() {
    }

    /**
     * �������� �������� �� ����������
     * @param idQuery -  ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� � ����� ������
     * @param imageDirPath - ���� � ���������� � ������� ����������
     * @param maxSize - ������������ ���������� �������� (������� 1200), ���� <=0, �� �������� ������������
     //* @param maxLoadCount - ������������ ���������� ����������� ������, ���� <=0, �� ������� ���
     * @throws LoaderException
     */

    public static void loadPhotos(Integer idQuery, Connection connection, Connection logConnection,  String imageDirPath, int maxSize) throws LoaderException {
        ImageDirInfo dirInfo = new ImageDirInfo();
        PhotoDatabaseHelper.getInstance().init(maxSize);

        PositionLoader.getInstance().loadPositionFiles(idQuery, connection, logConnection, imageDirPath,
                dirInfo,"�������� ���������� �� ����������", PhotoDatabaseHelper.getInstance());
    }

    /**
     * �������� �������� �� ����������
     * @param idQuery -  ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� � ����� ������
     * @param photoId - ������������� ����������
     * @throws LoaderException
     */

    public static void shrinkPhoto(Integer idQuery, Connection connection, Connection logConnection, Integer photoId) throws LoaderException {
        MessageService.getInstance().init();
        PhotoDatabaseHelper helper = PhotoDatabaseHelper.getInstance();

        try {
            Config.getInstance().init(connection, logConnection);
            helper.initSession(idQuery);
            helper.updateSmallImage(photoId);            
        } catch (BaseException e) {
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            LogHelper.logError(idQuery, ex.getMessage());
            throw new LoaderException(ex.getMessage());
        } finally {
            Config.getInstance().clearInfo();
            helper.closeSession();
            AppLog.setDefaultLogLevel();
        }
    }


    public static void main(String[] args) throws BaseException, LoaderException {
        try {
            System.out.println("load photos!!!");
            if (args.length == 0) {
                loadPhotos(new Integer(3),
                        Config.getTestDatabaseConnection(),
                        Config.getTestDatabaseConnection(),
                        "F:\\Beeline\\docs\\fotos\\files", 1200);
                //"F:\\",1200);
            } else {
                Connection conn = Config.getTestDatabaseConnection(args[0], args[1], args[2]);
                loadPhotos(new Integer(3),
                        conn,
                        conn,
                        args[3], 1200);
            }
        } catch (LoaderException e) {
            e.printStackTrace(System.out);  //To change body of catch statement use File | Settings | File Templates.
            //"F:\\Beeline\\docs\\mdbData\\�������.mdb"
            throw e;
        }

    }
}
