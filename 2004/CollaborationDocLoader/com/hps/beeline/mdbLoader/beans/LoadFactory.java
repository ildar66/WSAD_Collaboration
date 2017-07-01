package com.hps.beeline.mdbLoader.beans;

import com.hps.framework.exception.BaseException;

import java.sql.ResultSet;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.05.2005
 * Time: 11:31:01
 * To change this template use File | Settings | File Templates.
 */
public interface LoadFactory {
    public LoadableBean getBean();
    public Integer getLastLoadedId() throws BaseException;
    public ResultSet loadBeans(Integer lastId) throws BaseException;
}
