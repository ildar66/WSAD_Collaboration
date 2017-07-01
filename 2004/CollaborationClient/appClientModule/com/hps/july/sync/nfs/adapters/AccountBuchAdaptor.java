package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Ildar
 * ������� ��� �������� �����������.
 */
public class AccountBuchAdaptor extends DefaultCollaboration {

	private static class AccountBuchMap extends ColumnMap {
		/**
		 * �����������
		 */
		AccountBuchMap() {
			super();
			//         NFS              NRI            isKey
			addMap("account", "accountBuch", true);
			addMap("description", "description", false);

			// �������, ���������������� � ������� NRI
			addPredefinedColumnTargetDb("flagworknri", "N");
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public AccountBuchAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_AccountBuch", "APPS.XX_NRI_ACCOUNTS_VW", null, new AccountBuchMap());
	}
}
