package com.hps.beeline.excelLoader.helpers;

import com.hps.beeline.excelLoader.info.ExcelFileInfo;
import com.hps.beeline.LogHelper;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;

import java.util.Iterator;
import java.util.Map;
import java.sql.Types;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:53:01
 * To change this template use File | Settings | File Templates.
 */
public class BalanceDataHelper {

    private BalanceDataHelper() {
    }

    private static final String[] testKeys = new String[]{"признак комплекта", "№ комплекта",
                "код НФС", "код товара", "маркировка товара", "наименование товара", "кол-во свободного товара", "признак б/у", "тип брака товара"};


    private static void checkRecord(Map record, String fileName) throws BaseException {
        for(int i=0;i<testKeys.length;i++) {
            if(!record.containsKey(testKeys[i]))
                throw new BaseException("Файл "+fileName.trim()+" не содержит колонку "+testKeys[i]);
        }
    }

    public static void updateFileData(Integer idQuery, ExcelFileInfo info) throws BaseException {
        removeOldData(info.getGeneralInfo().getId_file());
        StoredProc insertNewData = new StoredProc("insert into balanceStoreData(" +
                "id_Rec,"+
                "id_file," +
                "set_sign," +
                "set_number," +
                "nfs_code," +
                "goods_code," +
                "good_marking," +
                "goods_name," +
                "qty," +
                "in_use_sign," +
                "waste_sign) values(0,?,?,?,?,?,?,?,?,?,?)");
        Iterator dataIterator = info.getData();
        boolean isTested = false;
        while(dataIterator.hasNext()) {
            AdvHashMap record = (AdvHashMap) dataIterator.next();
            if(!isTested) {
                checkRecord(record, info.getGeneralInfo().getFile_name());
                isTested = true;
            }
            insertNewData.setObject(1, info.getGeneralInfo().getId_file(), Types.VARCHAR);
            insertNewData.setObject(2, record.get("признак комплекта"), Types.VARCHAR);
            insertNewData.setObject(3, record.get("№ комплекта"), Types.INTEGER);
            insertNewData.setObject(4, record.get("код НФС"), Types.VARCHAR );
            insertNewData.setObject(5, record.get("код товара"), Types.VARCHAR);
            insertNewData.setObject(6, record.get("маркировка товара"), Types.VARCHAR);
            insertNewData.setObject(7, record.get("наименование товара"), Types.VARCHAR);
            insertNewData.setObject(8, getDecimal(record.get("кол-во свободного товара")), Types.DECIMAL);
            insertNewData.setObject(9, record.get("признак б/у"), Types.VARCHAR);
            insertNewData.setObject(10, record.get("тип брака товара"), Types.VARCHAR);
            insertNewData.executeUpdate();
        }
        LogHelper.logInfo(idQuery, "Загружена информация о файле "+info.getFile().getName());
    }

    private static BigDecimal getDecimal(Object val) throws BaseException {
        if(val instanceof Double)
            return new BigDecimal(((Double)val).doubleValue());
        if(val instanceof Integer)
            return new BigDecimal(((Integer)val).intValue());
        if(val instanceof Integer)
            return new BigDecimal((String)val);

        System.out.println("Error value class="+val.getClass());
        throw new BaseException("Для колонки количество неправильный тип данных");
    }

    private static void removeOldData(Integer fileId) throws BaseException {
        StoredProc freeData = new StoredProc("delete from balanceStoreData where id_file=?");
        freeData.setObject(1, fileId);
        freeData.executeUpdate();
    }

}
