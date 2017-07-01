package com.hps.beeline.mdbLoader.beans;

import com.hps.framework.exception.BaseException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.05.2005
 * Time: 11:25:40
 * To change this template use File | Settings | File Templates.
 */
public interface LoadableBean {
    public void init(ResultSet res) throws SQLException;
    public void storeInDb() throws BaseException;
}
