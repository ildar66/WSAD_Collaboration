package com.hps.beeline.loader.helpers;

import com.hps.beeline.loader.info.ExtDocInfo;
import com.hps.beeline.loader.info.sqlValueBeans.DbsPositionsBean;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 05.11.2004
 * Time: 17:14:48
 * To change this template use File | Settings | File Templates.
 */
public class PositionsHelper {
    private static final String SQL_WAY_MAPS = "select * from dbsPositions where lastUpdMarshKarta>=?";
    private static final String SQL_POS_LIST = "select * from dbsPositions where lastUpdListProhod>=?";
    private static final String SQL_ALL = "select * from dbsPositions";

    private PositionsHelper() {
    }

    public static Collection findAllWayMaps(Date lastUpdateDate) throws BaseException {
        Collection items = new ArrayList();
        StoredProc proc;

        if(lastUpdateDate!=null ) {
            proc = new StoredProc(SQL_WAY_MAPS);
            proc.setParam(1, lastUpdateDate);
        } else {
            proc = new StoredProc(SQL_ALL);
        }
        Iterator iter = proc.executeQueryVector("com.hps.beeline.loader.info.sqlValueBeans.DbsPositionsBean").iterator();
        while(iter.hasNext()) {
            DbsPositionsBean rs = (DbsPositionsBean) iter.next();
            ExtDocInfo docInfo = new ExtDocInfo(rs);
            items.add(docInfo);
        }
        return items;
    }

    public static Collection findAllPassList(Date lastUpdateDate) throws BaseException {
        Collection items = new ArrayList();
        StoredProc proc;

        if(lastUpdateDate!=null) {
            proc = new StoredProc(SQL_POS_LIST);
            proc.setParam(1, lastUpdateDate);
        } else {
            proc = new StoredProc(SQL_ALL);
        }
        Iterator iter = proc.executeQueryVector("com.hps.beeline.loader.info.sqlValueBeans.DbsPositionsBean").iterator();
        while(iter.hasNext()) {
            DbsPositionsBean rs = (DbsPositionsBean) iter.next();
            ExtDocInfo docInfo = new ExtDocInfo(rs);
            items.add(docInfo);
        }
        return items;
    }

}
