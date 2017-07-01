package com.hps.framework.utils;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 04.04.2005
 * Time: 16:27:12
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    private DateUtil() {
    }

    public static Date truncDateToSeconds(long time) {
        time = time/1000;
        time = time*1000;
        return new Date(time);
    }
}
