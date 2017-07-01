package com.hps.beeline.initialDataLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LoaderException;
import com.hps.beeline.initialDataLoader.data.SiteDocDirInfo;
import com.hps.beeline.initialDataLoader.data.TreeSiteDocDirInfo;
import com.hps.beeline.initialDataLoader.data.SiteDocTypeInfo;
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
public class MayMapLoader {

    private MayMapLoader() {
    }

    /**
     * �������� ����
     * @param idQuery -  ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� � ����� ������
     * @param initialDataDirPath - ���� � ���������� � �������
     * @throws com.hps.beeline.LoaderException
     */

    public static void loadWayMapData(Integer idQuery, Connection connection, Connection logConnection,  String initialDataDirPath) throws LoaderException {
        TreeSiteDocDirInfo dirInfo = new TreeSiteDocDirInfo(new SiteDocTypeInfo[] {
            new SiteDocTypeInfo("����������_�����", "WayMapDocType"),
        });        
        PositionLoader.getInstance().loadPositionFiles(idQuery, connection, logConnection, initialDataDirPath,
                dirInfo,"�������� ����", SiteDocHelper.getInstance());
    }


    public static void main(String[] args) throws BaseException, LoaderException {
        loadWayMapData(new Integer(3),
                Config.getTestDatabaseConnection(),
                Config.getTestDatabaseConnection(),
                //"M:\\Base map\\ALLMAP");
                "F:\\Beeline\\testData");
    }
}
