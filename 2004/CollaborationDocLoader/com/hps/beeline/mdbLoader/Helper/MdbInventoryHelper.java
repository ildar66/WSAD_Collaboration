package com.hps.beeline.mdbLoader.Helper;

import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.Config;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryOutDoc;
import com.hps.beeline.mdbLoader.beans.inventoryRegActs.InventoryRegAct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 19.05.2005
 * Time: 14:06:06
 * To change this template use File | Settings | File Templates.
 */
public class MdbInventoryHelper {
    public static MdbInventoryHelper instance = new MdbInventoryHelper();
    private Connection mdbConnection;
    private StoredProc getAllOutDocs = null;
    private StoredProc getAllOutDocsByMaxId = null;
    private StoredProc insertOutDoc = null;
    private StoredProc getMaxExtIdOutDoc = null;

    //RegistryActs

    private StoredProc getAllRegActs = null;
    private StoredProc getAllRegActsByMaxId = null;
    private StoredProc insertRegAct = null;
    private StoredProc getMaxExtIdRegAct = null;

    private File tempFile=null;


    public static MdbInventoryHelper getInstance() {
        return instance;
    }



    private String getFileName(String filePath) throws BaseException {
        if(!filePath.endsWith("/") && !filePath.endsWith("\\"))
            filePath+="\\";
        File testFile = new File(filePath+"расходы1.mdb");
        if(testFile.exists())
            return filePath+"расходы1.mdb";
        else {
            testFile = new File(filePath+"расходы.mdb");
            if(testFile.exists())
                return filePath+"расходы.mdb";
            else
                throw new BaseException("По указанному пути '"+filePath+"' не найден файл расходы.mdb");
        }
    }


    private void initTempFile(String mdbFilePath) throws BaseException {
        try {
            tempFile = File.createTempFile("mdb_temp", ".mdb");
            //tempFile.deleteOnExit();
            System.out.println("create temp file="+tempFile.getAbsolutePath());
            System.out.println("coping started!");

            FileInputStream fis = new FileInputStream(mdbFilePath);
            FileOutputStream fos = new FileOutputStream(tempFile);
            byte[] array = new byte[10000];
            int c = 0;
            while(c!=-1) {
                c = fis.read(array);
                if(c!=-1) {
                    fos.write(array, 0, c);
                }
            }
            fis.close();
            fos.close();

            System.out.println("file was copied!");

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Невозможно создать копию файла!");
        }
    }


    public void initSession(String mdbFilePath) throws BaseException {
        String realFileName = getFileName(mdbFilePath);
        initTempFile(realFileName);
        String url = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ="+tempFile.getAbsolutePath();
        System.out.println("url="+url);
        mdbConnection = Config.initSpecificConnection(new sun.jdbc.odbc.JdbcOdbcDriver(), url);

        getAllOutDocs = new StoredProc("select * from \"8 - Акты приема-передачи ТМЦ\"", mdbConnection);
        getAllOutDocsByMaxId = new StoredProc("select * from \"8 - Акты приема-передачи ТМЦ\" where \"Номер по порядку\">?", mdbConnection);
        getMaxExtIdOutDoc = new StoredProc("select max(external_id) as max_id from inventoryoutdocs" );
        insertOutDoc = new StoredProc("insert into inventoryoutdocs(" +
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
                "sheet_name," +
                "avia_bag_flag," +
                "delivery_flag," +
                "external_id) values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        //regActs

        getAllRegActs = new StoredProc("select * from \"17 -  Акты регистрации  серийных и инвентарных номеров\"", mdbConnection);
        getAllRegActsByMaxId = new StoredProc("select * from \"17 -  Акты регистрации  серийных и инвентарных номеров\" where \"Номер по порядку\">?", mdbConnection);
        getMaxExtIdRegAct = new StoredProc("select max(external_id) as max_id from inventoryregistryacts" );
        insertRegAct = new StoredProc("insert into inventoryregistryacts(" +
                "id," +
                "id_doc," +
                "doc_delivery_date," +
                "id_order," +
                "date_order," +
                "store_code," +
                "nfs_code," +
                "code_goods," +
                "marking_goods," +
                "name_goods," +
                "serial_number," +
                "inventory_number," +

                "external_id) values (0,?,?,?,?,?,?,?,?,?,?,?,?)");


    }

    public void closeSession() {


        try {
            getAllOutDocs.close();
            getAllOutDocs.close();
            getAllOutDocsByMaxId.close();
            insertOutDoc.close();
            getMaxExtIdOutDoc.close();

            //RegistryActs
            getAllRegActs.close();
            getAllRegActsByMaxId.close();
            insertRegAct.close();
            getMaxExtIdRegAct.close();
            mdbConnection.close();
        } catch (BaseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("try to delete file, success="+tempFile.delete());

        getAllOutDocs = null;
        getAllOutDocs = null;
        getAllOutDocsByMaxId = null;
        insertOutDoc = null;
        getMaxExtIdOutDoc = null;
        getAllRegActs = null;
        getAllRegActsByMaxId = null;
        insertRegAct = null;
        getMaxExtIdRegAct = null;
    }


    public ResultSet getAllOutDocs(Integer maxId) throws BaseException {
        if(maxId==null)
            return getAllOutDocs.executeQueryClassic();
        else {
            getAllOutDocsByMaxId.setObject(1, maxId);
            return getAllOutDocsByMaxId.executeQueryClassic();
        }
    }

    public Integer getMaxExtIdOutDoc() throws BaseException {
        Map result = getMaxExtIdOutDoc.executeQuery();
        return (Integer) result.get("max_id");
    }

    public void insertOutDoc(MdbInventoryOutDoc doc) throws BaseException {
        insertOutDoc.setObject(1, doc.getId_doc(), Types.INTEGER);
        insertOutDoc.setObject(2, doc.getDoc_delivery_date(), Types.DATE);
        insertOutDoc.setObject(3, doc.getId_ttn(), Types.CHAR);
        insertOutDoc.setObject(4, doc.getDate_ttn(), Types.DATE);
        insertOutDoc.setObject(5, doc.getId_order(), Types.CHAR);
        insertOutDoc.setObject(6, doc.getDate_order(), Types.DATE);
        insertOutDoc.setObject(7, doc.getLast_name_compiler(), Types.CHAR);
        insertOutDoc.setObject(8, doc.getDelivery_date(), Types.DATE);
        insertOutDoc.setObject(9, doc.getFerryman(), Types.CHAR);
        insertOutDoc.setObject(10, doc.getRecipient_type(), Types.CHAR);
        insertOutDoc.setObject(11, doc.getRecipient(), Types.CHAR);
        insertOutDoc.setObject(12, doc.getStore_name(), Types.CHAR);
        insertOutDoc.setObject(13, doc.getComplete_flag(), Types.CHAR);
        insertOutDoc.setObject(14, doc.getId_complete_set(), Types.CHAR);
        insertOutDoc.setObject(15, doc.getNfs_code(), Types.CHAR);
        insertOutDoc.setObject(16, doc.getCode_goods(), Types.CHAR);
        insertOutDoc.setObject(17, doc.getMarking_goods(), Types.CHAR);
        insertOutDoc.setObject(18, doc.getName_goods(), Types.CHAR);
        insertOutDoc.setObject(19, doc.getQty(), Types.DECIMAL);
        insertOutDoc.setObject(20, doc.getUsed_flag(), Types.CHAR);
        insertOutDoc.setObject(21, doc.getWaste_flag(), Types.CHAR);
        insertOutDoc.setObject(22, doc.getIs_complete(), Types.CHAR);
        insertOutDoc.setObject(23, doc.getAdditional_data(), Types.CHAR);
        insertOutDoc.setObject(24, doc.getStore_code(), Types.CHAR);
        insertOutDoc.setObject(25, doc.getSheet_name(), Types.CHAR);
        //new
        insertOutDoc.setObject(26, doc.getAvia_bag_flag(), Types.CHAR);
        insertOutDoc.setObject(27, doc.getDelivery_flag(), Types.CHAR);
        insertOutDoc.setObject(28, doc.getExternal_id(), Types.INTEGER);

        insertOutDoc.executeUpdate();
    }

    //regActs

    public ResultSet getAllRegActs(Integer maxId) throws BaseException {
        if(maxId==null)
            return getAllRegActs.executeQueryClassic();
        else {
            getAllRegActsByMaxId.setObject(1, maxId);
            return getAllRegActsByMaxId.executeQueryClassic();
        }
    }

    public Integer getMaxExtIdRegAct() throws BaseException {
        Map result = getMaxExtIdRegAct.executeQuery();
        return (Integer) result.get("max_id");
    }

    public void insertRegAct(InventoryRegAct doc) throws BaseException {
        insertRegAct.setObject(1, doc.getId_doc(), Types.CHAR);
        insertRegAct.setObject(2, doc.getDoc_delivery_date(), Types.DATE);
        insertRegAct.setObject(3, doc.getId_order(), Types.CHAR);
        insertRegAct.setObject(4, doc.getDate_order(), Types.DATE);
        insertRegAct.setObject(5, doc.getStore_code(), Types.CHAR);
        insertRegAct.setObject(6, doc.getNfs_code(), Types.CHAR);
        insertRegAct.setObject(7, doc.getCode_goods(), Types.CHAR);
        insertRegAct.setObject(8, doc.getMarking_goods(), Types.CHAR);
        insertRegAct.setObject(9, doc.getName_goods(), Types.CHAR);
        insertRegAct.setObject(10, doc.getSerial_number(), Types.CHAR);
        insertRegAct.setObject(11, doc.getInventory_number(), Types.CHAR);
        insertRegAct.setObject(12, doc.getExternal_id(), Types.INTEGER);
        insertRegAct.executeUpdate();
    }
}
