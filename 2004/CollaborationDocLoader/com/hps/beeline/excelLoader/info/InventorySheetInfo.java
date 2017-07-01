package com.hps.beeline.excelLoader.info;

import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 20.01.2005
 * Time: 14:40:53
 * To change this template use File | Settings | File Templates.
 */
public class InventorySheetInfo {
    private String name;
    private boolean isNew;

    public InventorySheetInfo(String name) {
        name = name.substring(1, name.length()-2);

        this.name = name;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }


    public void synchronize() throws BaseException {
        StoredProc findProc = new StoredProc("select id, sheet_name, is_new from inventoryoutsheets where sheet_name=?");
        findProc.setObject(1,name);
        Map map = findProc.executeQuery();
        if(map!=null) {
            Short isNew = (Short) map.get("is_new");
            this.isNew = isNew.intValue()==1 ? true : false;            
        } else {
            isNew = true;
            addSheet();
        }
    }

    public String getName() {
        return name;
    }

    private void addSheet() throws BaseException {
        StoredProc proc = new StoredProc("insert into inventoryoutsheets(id, sheet_name, is_new) values(0,?,?)");
        proc.setObject(1, name);
        proc.setObject(2, new Short((short)1));
        proc.executeUpdate();
    }
}
