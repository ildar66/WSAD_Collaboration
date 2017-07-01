package com.hps.july.sync.nfs.adapters;

import java.sql.*;

import com.hps.july.core.*;

/**
 * @author Ildar
 * Адаптер для переноса поставщиков.
 */
public class VendorSite2BankAccountsAdaptor extends DefaultCollaboration {

	private static class VendorSite2BankAccountsMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		VendorSite2BankAccountsMap() {
			super();
			//         NFS              NRI            isKey
			addMap("bank_account_uses_id", "id", true);
			addMap("bank_account_id", "idbankaccountnfs", false);
			addMap("vendor_site_id", "idvendorsitenfs", false);
			addMap("vendor_id", "idvendornfs", false);
			addMap("org_id", "idorgnfs", false);
			addMap("inactive_date", "inactive_date", false);
			addMap("created_by", "usercreate", false);
			addMap("creation_date", "datecreate", false);
			addMap("last_updated_by", "usermodify", false);
			addMap("last_update_date", "datemodify", false);

			// Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("flagworknri", "N");
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public VendorSite2BankAccountsAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_vendorSite2bankAccount", "APPS.XX_NRI_VENDOR_SITE_ACCOUNTS_VW", null, new VendorSite2BankAccountsMap());
	}

	/// Установка связи с существующим объектом NRI
	public boolean makeRelationInTargetDb(RowDBObject argObj) {
		boolean result = false;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = getTargetDbConnection().createStatement();
			String idAccount = "" + ((java.math.BigDecimal)argObj.getKeysColumns().get("bank_account_uses_id")).intValue();
			rs = st.executeQuery("EXECUTE PROCEDURE LnkAccountNfs(" + idAccount + ")");
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
			System.out.println("Error executing procedure LnkAccountNfs = ");
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
