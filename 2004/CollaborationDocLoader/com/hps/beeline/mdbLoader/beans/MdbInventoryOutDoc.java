package com.hps.beeline.mdbLoader.beans;

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
public class MdbInventoryOutDoc {
	private Integer id_doc = null;
	private Date doc_delivery_date = null;
	private String id_ttn = null;
	private Date date_ttn = null;
	private String id_order = null;
	private Date date_order = null;
	private String last_name_compiler = null;
	private Date delivery_date = null;
	private String ferryman = null;
	private String recipient_type = null;
	private String recipient = null;
	private String store_name = null;
	private String complete_flag = null;
	private String id_complete_set = null;
	private String nfs_code = null;
	private String code_goods = null;
	private String marking_goods = null;
	private String name_goods = null;
	private BigDecimal qty = null;
	private String used_flag = null;
	private String waste_flag = null;
	private String is_complete = null;
	private String additional_data = null;
	private String store_code = null;
	private String sheet_name = null;

    private String avia_bag_flag = null; // varchar(60,0)      -- ������� ����-��������
	private String  delivery_flag = null; // varchar(60,0)      -- ������� ��������

    public MdbInventoryOutDoc() {
    }

    private Integer getInt(String s) {
        if(s!=null)
            return new Integer(s.trim());
        else
            return null;
    }

    private BigDecimal getBigDecimal(String s) {
        if(s!=null)
            return new BigDecimal(s);
        else
            return null;
    }

    public void init(ResultSet set) throws SQLException {
        id_doc = getInt(set.getString("� ���� ������"));
        doc_delivery_date = set.getDate("���� ���� ������");

        id_order = set.getString("� ����������");
        date_order = set.getDate("���� ����������");
        last_name_compiler = set.getString("����������� ����������");
        additional_data = set.getString("�������������� ��������");
        delivery_date = set.getDate("���� ������");
        ferryman = set.getString("����������");
        recipient = set.getString("����������");
        //recipient_type = set.getString("��� ����������");
        store_code = set.getString("��� ������");
        complete_flag = set.getString("������� ���������");
        id_complete_set = set.getString("� ���������");
        nfs_code = set.getString("��� ���");
        code_goods = set.getString("��� ������");
        marking_goods = set.getString("���������� ������");
        name_goods = set.getString("������������ ������");
        qty = getBigDecimal(set.getString("���-�� ��������� ������"));
        used_flag = set.getString("������� ��");
        waste_flag = set.getString("������� ����� ������");
        //new fields
        avia_bag_flag = set.getString("������� ����-��������");
        delivery_flag = set.getString("������� ��������");
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
