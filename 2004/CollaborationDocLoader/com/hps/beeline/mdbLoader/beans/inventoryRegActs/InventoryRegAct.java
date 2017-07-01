package com.hps.beeline.mdbLoader.beans.inventoryRegActs;

import com.hps.beeline.excelLoader.helpers.InventoryHelper;
import com.hps.beeline.mdbLoader.beans.LoadableBean;
import com.hps.beeline.mdbLoader.Helper.MdbInventoryHelper;
import com.hps.framework.sql.ProcedureUtils;
import com.hps.framework.sql.ProcedureUtils;
import com.hps.framework.exception.BaseException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 21.01.2005
 * Time: 10:09:11
 * To change this template use File | Settings | File Templates.
 */
public class InventoryRegAct implements LoadableBean{
	private String id_doc = null;
	private Date doc_delivery_date = null;
    private String id_order = null;
	private Date date_order = null;
    private String store_code = null;
    private String nfs_code = null;
    private String code_goods = null;
	private String marking_goods = null;
    private String name_goods = null;
    private String serial_number = null;
    private String inventory_number = null;

	private Integer external_id = null; // varchar(60,0)      -- Признак доставки

    public InventoryRegAct() {
    }

    private Integer getInt(String s) {
        if(s!=null)
            return new Integer(s.trim());
        else
            return null;
    }

    public void init(ResultSet set) throws SQLException {
        id_doc = set.getString("№ акта выдачи");
        doc_delivery_date = set.getDate("Дата акта выдачи");
        id_order = set.getString("№ требования");
        date_order = set.getDate("Дата требования");
        store_code = set.getString("Код склада");
        nfs_code = set.getString("Код НФС");
        code_goods = set.getString("Код товара");
        marking_goods = set.getString("Маркировка товара");
        name_goods = set.getString("Наименование товара");
        serial_number = set.getString("Серийный №");
        inventory_number = set.getString("Инвентарный №");

        external_id = (Integer) set.getObject("Номер по порядку");
    }


    public void storeInDb() throws BaseException {
        MdbInventoryHelper.getInstance().insertRegAct(this);
    }

    public String getId_doc() {
        return id_doc;
    }

    public Date getDoc_delivery_date() {
        return doc_delivery_date;
    }

    public String getId_order() {
        return id_order;
    }

    public Date getDate_order() {
        return date_order;
    }

    public String getStore_code() {
        return store_code;
    }

    public String getNfs_code() {
        return nfs_code;
    }

    public String getCode_goods() {
        return code_goods;
    }

    public String getMarking_goods() {
        return marking_goods;
    }

    public String getName_goods() {
        return name_goods;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public String getInventory_number() {
        return inventory_number;
    }

    public Integer getExternal_id() {
        return external_id;
    }
}
