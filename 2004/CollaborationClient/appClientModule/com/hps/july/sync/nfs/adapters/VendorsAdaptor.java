package com.hps.july.sync.nfs.adapters;

import java.sql.*;

import com.hps.july.core.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса поставщиков.
 */
public class VendorsAdaptor extends DefaultCollaboration {

    private static class VendorsMap extends ColumnMap {
        /**
         * Конструктор
         */
        VendorsMap() {
            super();
            //         NFS              NRI            isKey
            addMap("vendor_id", "idvendornfs", true);
            addMap("end_date_active", "end_date_active", false);
            addMap("vendor_name", "name", false);
            addMap("inn", "inn", false);
            addMap("vendor_type_lookup_code", "type", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);
			addMap("VENDOR_NUM", "vendorCode", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public VendorsAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_vendors", "APPS.XX_NRI_VENDORS_VW", "last_update_date", new VendorsMap());
    }

	/// Установка связи с существующим объектом NRI
	public boolean makeRelationInTargetDb(RowDBObject argObj) {
		boolean result = false;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = getTargetDbConnection().createStatement();
			String idVendor = "" + ((java.math.BigDecimal)argObj.getKeysColumns().get("vendor_id")).intValue();
			rs = st.executeQuery("EXECUTE PROCEDURE LnkVendorNfs(" + idVendor + ")");
			if (rs.next()) {
				int resCode = rs.getInt(1);
				if (resCode == -1) {
					String resMsg = rs.getString(2);
					QueryProcessing.addLogMessage(getTargetDbConnection(), getQuery(), QueryProcessing.MSG_WARNING, resMsg);
					result = true;
				} else if (resCode != 0) {
					String resMsg = rs.getString(2);
					QueryProcessing.addLogMessage(getTargetDbConnection(), getQuery(), QueryProcessing.MSG_ERROR, resMsg);
				} else {
					result = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error executing procedure LnkVendorNfs = ");
			System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		try {rs.close();} catch (Exception e) {}
		try {st.close();} catch (Exception e) {}
		return result;
	}

}
