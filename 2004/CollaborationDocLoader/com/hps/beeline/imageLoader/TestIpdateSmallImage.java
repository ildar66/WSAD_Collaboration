package com.hps.beeline.imageLoader;

import com.hps.framework.exception.BaseException;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 20.05.2005
 * Time: 17:06:15
 * To change this template use File | Settings | File Templates.
 */
public class TestIpdateSmallImage {
    public static void main(String[] args) throws BaseException, LoaderException {
        try {
            Connection conn = Config.getTestDatabaseConnection();
            ImageProcessor.shrinkPhoto(new Integer(3),
                    conn,
                    conn,
                    new Integer(3193));

        } catch (LoaderException e) {
            e.printStackTrace(System.out);  //To change body of catch statement use File | Settings | File Templates.
            //"F:\\Beeline\\docs\\mdbData\\расходы.mdb"
            throw e;
        }

    }
}
