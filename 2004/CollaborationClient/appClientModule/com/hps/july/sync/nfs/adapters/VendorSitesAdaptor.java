package com.hps.july.sync.nfs.adapters;

import java.sql.*;

import com.hps.july.core.*;


/**
 * @author Dmitry Dneprov
 * Адаптер для переноса отделений поставщиков.
 */
public class VendorSitesAdaptor extends DefaultCollaboration {

    private static class VendorSitesMap extends ColumnMap {
        /**
         * Конструктор
         */
        VendorSitesMap() {
            super();
            //         NFS              NRI            isKey
            addMap("VENDOR_SITE_ID", "idVendorSiteNfs", true);
            addMap("VENDOR_ID", "idVendorNfs", false);
            addMap("ORG_ID", "idOrgNfs", false);
            addMap("VENDOR_SITE_CODE", "vendorSiteCode", false);
            addMap("SITE_NAME", "name", false);
            addMap("MATCH_OPTION", "matchOption", false);
            addMap("addr", "address", false);
            addMap("kpp", "kpp", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public VendorSitesAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_vendorsites", "APPS.XX_NRI_VENDOR_SITES_VW", "last_update_date", new VendorSitesMap());
    }

	/// Установка связи с существующим объектом NRI
	public boolean makeRelationInTargetDb(RowDBObject argObj) {
		boolean result = false;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = getTargetDbConnection().createStatement();
			String idVendorSite = "" + ((java.math.BigDecimal)argObj.getKeysColumns().get("vendor_site_id")).intValue();
			rs = st.executeQuery("EXECUTE PROCEDURE LnkVendorSiteNfs(" + idVendorSite + ")");
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
			System.out.println("Error executing procedure LnkVendorSiteNfs = ");
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
