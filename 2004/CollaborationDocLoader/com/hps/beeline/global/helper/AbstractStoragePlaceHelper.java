package com.hps.beeline.global.helper;

import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.beeline.LogHelper;

import java.sql.Types;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 31.03.2005
 * Time: 11:50:47
 * To change this template use File | Settings | File Templates.
 */
public class AbstractStoragePlaceHelper {
    private StoredProc getStoragePlaceProc;
    private Integer autoLoaderId = null;

    public AbstractStoragePlaceHelper() {
    }

    public void initSession(Integer idQuery) throws BaseException {
        getStoragePlaceProc = new StoredProc("{call dbsFindImagePosition(?,?,?)}");
        createTempTables(idQuery);
    }

    public void closeSession(){
        try{
            getStoragePlaceProc.close();
        }catch(BaseException err) {
            err.printStackTrace();
        }
    }

    public Integer getStoragePlaceId(Integer idQuery, Integer gsmId, Integer dampsId,Integer wlanId) throws BaseException {
       getStoragePlaceProc.setObject(1, gsmId, Types.INTEGER);
       getStoragePlaceProc.setObject(2, dampsId, Types.INTEGER);
       getStoragePlaceProc.setObject(3, wlanId, Types.INTEGER);
       Map result = getStoragePlaceProc.executeFunctionCall();

       Integer errorCode = (Integer) result.get(StoredProc.RESULT1);
       String errorText = (String) result.get(StoredProc.RESULT2);
       Integer storagePlace = (Integer) result.get(StoredProc.RESULT3);
       String positionType = (String) result.get(StoredProc.RESULT4);
       if(errorCode.intValue()!=0)
           throw new BaseException(errorText);
       else {
           if(positionType.equals("P"))
               LogHelper.logWarning(idQuery,"ѕозици€ с gsmId="+gsmId+" dumpsId="+dampsId+" wlanId="+wlanId+" найдена среди планируемого оборудовани€!");
           if(positionType.equals("D"))
               LogHelper.logWarning(idQuery,"ѕозици€ с gsmId="+gsmId+" dumpsId="+dampsId+" wlanId="+wlanId+" найдена среди демонтированного оборудовани€!");
           return storagePlace;
       }
   }

    public void createTempTables(Integer idQuery) throws BaseException {
        StoredProc proc = new StoredProc("{call dbsFillRegions(?)}");
        proc.setObject(1, idQuery, Types.INTEGER);
        proc.executeFunctionCall();
    }

    public static Integer getNamedValueAsInteger(String alias) throws BaseException {
        StoredProc getNamedValueProc = new StoredProc("select intvalue from namedvalues " +
                                " where lower(id) = lower(?);");
        getNamedValueProc.setObject(1, alias, Types.CHAR);
        Map res = getNamedValueProc.executeQuery();
        if(res==null)
            throw new BaseException("¬ таблице NamedValues не найдена переменна€ "+alias);
        return (Integer)res.get("intvalue");
    }

    public Integer getAutoLoaderId() throws BaseException {
        if(autoLoaderId==null)
            autoLoaderId = getNamedValueAsInteger("AutoLoaderPeople");
        return autoLoaderId;
    }


}
