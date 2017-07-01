package com.hps.beeline.mdbLoader.beans.inventory;

import com.hps.beeline.mdbLoader.beans.LoadFactory;
import com.hps.beeline.mdbLoader.beans.LoadableBean;
import com.hps.beeline.mdbLoader.Helper.MdbInventoryHelper;
import com.hps.framework.exception.BaseException;

import java.sql.ResultSet;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.05.2005
 * Time: 11:37:29
 * To change this template use File | Settings | File Templates.
 */
public class MdbInventoryLoadFactory implements LoadFactory{
    public LoadableBean getBean() {
        return new MdbInventoryOutDoc();
    }

    public Integer getLastLoadedId() throws BaseException {
        return MdbInventoryHelper.getInstance().getMaxExtIdOutDoc();
    }

    public ResultSet loadBeans(Integer lastId) throws BaseException {
        return MdbInventoryHelper.getInstance().getAllOutDocs(lastId);
    }
}
