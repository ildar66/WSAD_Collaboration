package com.hps.beeline.excelLoader.info;

import com.hps.beeline.excelLoader.helpers.InventoryHelper;
import com.hps.framework.sql.ProcedureUtils;
import com.hps.framework.sql.ProcedureUtils;

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
public class InventoryOutDoc {
	private Integer id_doc;
	private Date doc_delivery_date;
	private String id_ttn;
	private Date date_ttn;
	private String id_order;
	private Date date_order;
	private String last_name_compiler;
	private Date delivery_date;
	private String ferryman;
	private String recipient_type;
	private String recipient;
	private String store_name;
	private String complete_flag;
	private String id_complete_set;
	private String nfs_code;
	private String code_goods;
	private String marking_goods;
	private String name_goods;
	private BigDecimal qty;
	private String used_flag;
	private String waste_flag;
	private String is_complete;
	private String additional_data;
	private String store_code;
	private String sheet_name;

    public InventoryOutDoc() {
    }

    private Integer getInt(String s) {
        if(s!=null)
            return new Integer(s);
        else
            return null;
    }

    private BigDecimal getBigDecimal(String s) {
        if(s!=null)
            return new BigDecimal(s);
        else
            return null;
    }

    public void init(ResultSet set, String sheetName, int type) throws SQLException {
        sheet_name = sheetName;
        id_doc = getInt(set.getString("№ акта выдачи"));
        doc_delivery_date = set.getDate("Дата акта выдачи");

        if(type==InventoryHelper.TYPE_OLD) {
            id_ttn = set.getString("№ товарно-транспортной накладной");
            date_ttn = set.getDate("Дата ттн");
            id_order = set.getString("№ заказа на выдачу/требования");
            date_order = set.getDate("Дата заказа на выдачу");
            last_name_compiler = set.getString("Фамилия составителя требования");
            delivery_date = set.getDate("Дата выдачи");
            ferryman = set.getString("Перевозчик");
            recipient_type = set.getString("Тип получателя");
            recipient = set.getString("Получатель");
            store_name = set.getString("Наименование склада");
            complete_flag = set.getString("Признак комплекта");
            id_complete_set = set.getString("№ комплекта");
            nfs_code = set.getString("Код НФС");
            code_goods = set.getString("Код товара");
            marking_goods = set.getString("Маркировка товара");
            name_goods = set.getString("Наименование товара");
            qty = getBigDecimal(set.getString("Кол-во"));
            used_flag = set.getString("Признак Б/У");
            waste_flag = ProcedureUtils.getStrBoolean(set.getString("Признак брака товара"));
            is_complete = set.getString("Сделано");
        } else {
            id_order = set.getString("№ требования");
            date_order = set.getDate("Дата требования");
            last_name_compiler = set.getString("Составитель требования");
            additional_data = set.getString("Дополнительные сведения");
            delivery_date = set.getDate("Дата выдачи");
            ferryman = set.getString("Перевозчик");
            recipient = set.getString("Получатель");
            recipient_type = set.getString("Тип получателя");
            store_code = set.getString("Код склада");
            complete_flag = set.getString("Признак комплекта");
            id_complete_set = set.getString("№ комплекта");
            nfs_code = set.getString("Код НФС");
            code_goods = set.getString("Код товара");
            marking_goods = set.getString("Маркировка товара");
            name_goods = set.getString("Наименование товара");
            qty = getBigDecimal(set.getString("Кол-во выданного товара"));
            used_flag = set.getString("Признак Б/У");
            waste_flag = set.getString("Признак брака товара");
        }
    }

    public Integer getId_doc() {
        return id_doc;
    }

    public Date getDoc_delivery_date() {
        return doc_delivery_date;
    }

    public String getId_ttn() {
        return id_ttn;
    }

    public Date getDate_ttn() {
        return date_ttn;
    }

    public String getId_order() {
        return id_order;
    }

    public Date getDate_order() {
        return date_order;
    }

    public String getLast_name_compiler() {
        return last_name_compiler;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public String getFerryman() {
        return ferryman;
    }

    public String getRecipient_type() {
        return recipient_type;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getComplete_flag() {
        return complete_flag;
    }

    public String getId_complete_set() {
        return id_complete_set;
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

    public BigDecimal getQty() {
        return qty;
    }

    public String getUsed_flag() {
        return used_flag;
    }

    public String getWaste_flag() {
        return waste_flag;
    }

    public String getIs_complete() {
        return is_complete;
    }

    public String getAdditional_data() {
        return additional_data;
    }

    public String getStore_code() {
        return store_code;
    }

    public String getSheet_name() {
        return sheet_name;
    }
}
