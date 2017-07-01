package com.hps.framework.sql;

import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.utils.AdvHashMap;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 11.11.2004
 * Time: 14:24:17
 * To change this template use File | Settings | File Templates.
 */
public class ProcedureUtils {

    private ProcedureUtils() {
    }

    public static void processStoredProcException(Map rs) throws BaseException {
        Integer code = (Integer) rs.get(StoredProc.RESULT1);
        Integer text = (Integer) rs.get(StoredProc.RESULT2);
        if(code.intValue()!=0)
            throw new BaseException(code+":"+text);
    }

    public static String getStrBoolean(String s) {
        if(s==null)
            return null;
        try{
            double res = Double.parseDouble(s);
            if(res!=0 && res!=1)
                return "0";
            return ""+(int)res;
        }catch(Exception err) {
            err.printStackTrace();
            return "0";
        }
    }

    public static void main(String[] args) {
        System.out.println("res="+getStrBoolean("1.5"));
    }

}
