package com.hps.beeline.initialDataLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LoaderException;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.initialDataLoader.data.SiteDocDirInfo;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.framework.exception.BaseException;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:34:59
 * To change this template use File | Settings | File Templates.
 */
public class InitialDataLoader {

    private InitialDataLoader() {
    }

    /**
     * �������� ��������� ������
     * @param idQuery -  ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� � ����� ������
     * @param initialDataDirPath - ���� � ���������� � ������� ����������
     * @throws com.hps.beeline.LoaderException
     */

    public static void loadInitialData(Integer idQuery, Connection connection, Connection logConnection,  String initialDataDirPath) throws LoaderException {
        SiteDocDirInfo dirInfo = new SiteDocDirInfo(null, "InitialDataDocType");
        PositionLoader.getInstance().loadPositionFiles(idQuery, connection, logConnection, initialDataDirPath,
                dirInfo,"�������� ��������� ������", SiteDocHelper.getInstance());
    }


    public static void main(String[] args) throws BaseException, LoaderException {
        loadInitialData(new Integer(3),
                Config.getTestDatabaseConnection(),
                Config.getTestDatabaseConnection(),
                "O:\\�������� ������");
                //"F:\\Beeline\\test");
                //"F:\\Beeline\\docs\\initialData\\data");
    }
}
