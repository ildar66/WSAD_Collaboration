package com.hps.beeline;

import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;
import com.hps.beeline.Config;
import com.hps.beeline.Config;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 11.11.2004
 * Time: 14:29:48
 * To change this template use File | Settings | File Templates.
 */
public class LogHelper {

    private LogHelper(){}

    private static final String INFORMATION_MESSAGE = "I";
    private static final String ERROR_MESSAGE = "E";
    private static final String WARNING_MESSAGE = "W";

    public static void logError(Integer idQuery, String text)  {
        AppLog.error(text);
        log(idQuery, ERROR_MESSAGE, text);
    }

    public static void logInfo(Integer idQuery, String text) {
        AppLog.info(text);
        log(idQuery, INFORMATION_MESSAGE, text);
    }

    public static void logWarning(Integer idQuery, String text) {
        AppLog.info(text);
        log(idQuery, WARNING_MESSAGE, text);
    }

    public static void log(Integer idQuery, String type, String text)  {
        try {
            StoredProc proc = new StoredProc("{call fixErrorJoinDB(?,?,?)}", Config.getInstance().getLogConnection());
            proc.setObject(1, idQuery);
            proc.setObject(2, type);
            proc.setObject(3, text);
            proc.executeUpdate();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
