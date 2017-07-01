package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Ildar
 * Адаптер для переноса поставщиков.
 */
public class AccountBuchAdaptor extends DefaultCollaboration {

	private static class AccountBuchMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		AccountBuchMap() {
			super();
			//         NFS              NRI            isKey
			addMap("account", "accountBuch", true);
			addMap("description", "description", false);

			// Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("flagworknri", "N");
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public AccountBuchAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_AccountBuch", "APPS.XX_NRI_ACCOUNTS_VW", null, new AccountBuchMap());
	}
}
