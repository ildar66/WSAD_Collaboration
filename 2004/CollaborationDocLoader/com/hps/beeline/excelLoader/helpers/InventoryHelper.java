package com.hps.beeline.excelLoader.helpers;

import com.hps.beeline.excelLoader.info.InventoryOutDoc;
import com.hps.beeline.excelLoader.info.InventorySheetInfo;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 20.01.2005
 * Time: 14:42:02
 * To change this template use File | Settings | File Templates.
 */
public class InventoryHelper {

    public static final int TYPE_OLD = 0;
    public static final int TYPE_NEW = 1;

    private InventoryHelper() {
    }


    public static Collection getSheetList(File file) throws BaseException{
        try {
            Collection result = new ArrayList();
            Connection excelConnection = ExcelHelper.getExcelConnection(file);
            DatabaseMetaData dbmd = excelConnection.getMetaData();
            ResultSet set = dbmd.getTables(null, null, null, null);
            while (set.next()) {
                String data = set.getString(3);
                if(data.endsWith("$'")) {
                    System.out.println("found sheet="+data);
                    InventorySheetInfo info = new InventorySheetInfo(data);
                    info.synchronize();
                    result.add(info);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new BaseException( "Произошла ошибка при получении списка sheets",e);
        }
    }

    public static int loadSheet(File file,InventorySheetInfo sheet) throws BaseException {
        AppLog.info("Загрузка sheet '"+sheet.getName()+"' началась, идет удаление старых записей");
        StoredProc deleteProc = new StoredProc("delete from inventoryoutdocs where sheet_name=?");
        deleteProc.setObject(1, sheet.getName());
        deleteProc.executeUpdate();
        AppLog.info("Старые записи удалены");
        int count=0;
        InventoryOutDoc doc = new InventoryOutDoc();
        Connection connection = ExcelHelper.getExcelConnection(file);
        ResultSet set = ExcelHelper.loadDataClassic(connection, sheet.getName());
        try {
            int type = getType(set);
            AppLog.info("Sheet type="+type+", началась загрузка");

            while (set.next()) {
                doc.init(set, sheet.getName(), type);
                storeOutDoc(doc);
                count++;
            }
            AppLog.info("Загрузка sheet '"+sheet.getName()+"' прошла успешна, обработано "+count+" записей");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException("Ошибка во время доступа к данным", e);
        } finally{
            try{
            saveInventoryProc.close();    
            set.close();
            connection.close();
            }catch(Exception err){}
        }
        return count;
    }



    private static final StoredProc saveInventoryProc = new StoredProc("insert into inventoryoutdocs(" +
                "id," +
                "id_doc," +
                "doc_delivery_date," +
                "id_ttn," +
                "date_ttn," +
                "id_order," +
                "date_order," +
                "last_name_compiler," +
                "delivery_date," +
                "ferryman," +
                "recipient_type," +
                "recipient," +
                "store_name," +
                "complete_flag," +
                "id_complete_set," +
                "nfs_code," +
                "code_goods," +
                "marking_goods," +
                "name_goods," +
                "qty," +
                "used_flag," +
                "waste_flag," +
                "is_complete," +
                "additional_data," +
                "store_code," +
                "sheet_name) values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    private static void storeOutDoc(InventoryOutDoc doc) throws BaseException {        
        saveInventoryProc.setObject(1, doc.getId_doc(), Types.INTEGER);
        saveInventoryProc.setObject(2, doc.getDoc_delivery_date(), Types.DATE);
        saveInventoryProc.setObject(3, doc.getId_ttn(), Types.CHAR);
        saveInventoryProc.setObject(4, doc.getDate_ttn(), Types.DATE);
        saveInventoryProc.setObject(5, doc.getId_order(), Types.CHAR);
        saveInventoryProc.setObject(6, doc.getDate_order(), Types.DATE);
        saveInventoryProc.setObject(7, doc.getLast_name_compiler(), Types.CHAR);
        saveInventoryProc.setObject(8, doc.getDelivery_date(), Types.DATE);
        saveInventoryProc.setObject(9, doc.getFerryman(), Types.CHAR);
        saveInventoryProc.setObject(10, doc.getRecipient_type(), Types.CHAR);
        saveInventoryProc.setObject(11, doc.getRecipient(), Types.CHAR);
        saveInventoryProc.setObject(12, doc.getStore_name(), Types.CHAR);
        saveInventoryProc.setObject(13, doc.getComplete_flag(), Types.CHAR);
        saveInventoryProc.setObject(14, doc.getId_complete_set(), Types.CHAR);
        saveInventoryProc.setObject(15, doc.getNfs_code(), Types.CHAR);
        saveInventoryProc.setObject(16, doc.getCode_goods(), Types.CHAR);
        saveInventoryProc.setObject(17, doc.getMarking_goods(), Types.CHAR);
        saveInventoryProc.setObject(18, doc.getName_goods(), Types.CHAR);
        saveInventoryProc.setObject(19, doc.getQty(), Types.DECIMAL);
        saveInventoryProc.setObject(20, doc.getUsed_flag(), Types.CHAR);
        saveInventoryProc.setObject(21, doc.getWaste_flag(), Types.CHAR);
        saveInventoryProc.setObject(22, doc.getIs_complete(), Types.CHAR);
        saveInventoryProc.setObject(23, doc.getAdditional_data(), Types.CHAR);
        saveInventoryProc.setObject(24, doc.getStore_code(), Types.CHAR);
        saveInventoryProc.setObject(25, doc.getSheet_name(), Types.CHAR);
        saveInventoryProc.executeUpdate();
    }

    private static int getType(ResultSet set) throws SQLException {
        Set columns = new HashSet();
        ResultSetMetaData meta = set.getMetaData();
        for(int i=1;i<meta.getColumnCount();i++) {
            String name = meta.getColumnName(i);
            //System.out.println("name="+name);
            columns.add(name);
        }

        if(columns.contains("Наименование склада"))
            return TYPE_OLD;
        else
            return TYPE_NEW;
    }
}
