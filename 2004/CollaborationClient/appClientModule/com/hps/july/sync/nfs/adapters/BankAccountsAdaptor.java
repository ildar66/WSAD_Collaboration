package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Ildar
 * Адаптер для переноса поставщиков.
 */
public class BankAccountsAdaptor extends DefaultCollaboration {

	private static class BankAccountsMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		BankAccountsMap() {
			super();
			//         NFS              NRI            isKey
			addMap("bank_Account_Id", "idbankaccountnfs", true);
			addMap("bank_branch_id", "idbanknfs", false);
			addMap("org_id", "idorgnfs", false);
			addMap("bank_account_num", "accountnum", false);
			addMap("currency_code", "curracc", false);
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

	public BankAccountsAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_bankAccounts", "APPS.XX_NRI_BanksAccounts_VW", null, new BankAccountsMap());
	}
}
