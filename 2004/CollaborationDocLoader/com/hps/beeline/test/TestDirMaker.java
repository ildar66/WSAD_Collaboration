package com.hps.beeline.test;

import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.sql.StoredProc;
import com.hps.beeline.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.util.Iterator;
import java.util.Collection;
import java.sql.Types;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.03.2005
 * Time: 14:21:57
 * To change this template use File | Settings | File Templates.
 */
public class TestDirMaker {
    public static void main(String[] args) throws IOException, BaseException {

        Config.getInstance().init(Config.getTestDatabaseConnection(), Config.getTestDatabaseConnection());
        String basePath = "F:\\Beeline\\test\\";
        String dirPath;
        StoredProc proc1 = new StoredProc("{call dbsFillRegions(?)}");
        proc1.setObject(1, new Integer(3), Types.INTEGER);
        proc1.executeFunctionCall();


        StoredProc proc = new StoredProc("select * from positions where gsmId is not null " +
                "and regionid IN (select regionId from tmpListReg)  ");
        Collection list = proc.executeQueryVector();
        Iterator iter = list.iterator();
        StringBuffer buff = new StringBuffer();
        for(int i=0;i<10000;i++) {
            buff.append("TEST TEST TEST");
        }
        byte[] array  = buff.toString().getBytes();

        while(iter.hasNext()) {
            AdvHashMap map = (AdvHashMap) iter.next();
            Object gsmId = map.get("gsmid");

            dirPath = basePath+gsmId+"_test";
            File dir = new File(dirPath);
            dir.mkdir();

            for(int i1=0;i1<5;i1++) {
                FileOutputStream fos = new FileOutputStream(dirPath+"\\"+i1+".txt");
                fos.write(array);
                fos.close();
            }
        }

    }
}
