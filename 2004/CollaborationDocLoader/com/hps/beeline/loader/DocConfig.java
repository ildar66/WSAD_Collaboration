/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:31:01
 * To change this template use File | Settings | File Templates.
 */
package com.hps.beeline.loader;

import com.hps.framework.exception.BaseException;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DocConfig {
    private static DocConfig ourInstance = new DocConfig();

    public static DocConfig getInstance() {
        return ourInstance;
    }

    private DocConfig() {
    }

    private static final String WAY_MAP_PATH = "L:\\BaseSt\\Base map\\ALLMAP1";
    private Date lastUpdateTime = null;
    private String blobDirPath = WAY_MAP_PATH;

    public String getFilePath() {
        return blobDirPath;
    }

    public void init(Date lastUpdateTime, String blobDirPath) {
        this.lastUpdateTime = lastUpdateTime;
        this.blobDirPath = blobDirPath;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
}
