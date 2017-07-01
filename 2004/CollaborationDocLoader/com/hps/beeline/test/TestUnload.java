package com.hps.beeline.test;

import com.hps.beeline.Config;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.sql.StoredProc;

import java.sql.Types;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 30.03.2005
 * Time: 17:38:18
 * To change this template use File | Settings | File Templates.
 */
public class TestUnload {
    public static void main(String[] args) throws BaseException, IOException {

        Config.getInstance().init(Config.getTestDatabaseConnection(), Config.getTestDatabaseConnection());
        String basePath = "F:\\Beeline\\ttt.doc";
        StoredProc proc = new StoredProc("select sitedocfilebody from sitedocfiles where sitedocfile=9933");
        Map res =  proc.executeQuery();
        byte[] array = (byte[]) res.get("sitedocfilebody");

        FileOutputStream fos = new FileOutputStream(basePath);
        fos.write(array);
        fos.close();
    }
}
