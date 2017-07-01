package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса поставщиков.
 */
public class CboAdaptor extends DefaultCollaboration {

	private static class CboMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		CboMap() {
			super();
			//         NFS              NRI            isKey
			addMap("cbo", "cbo", true);
			addMap("description", "description", false);

			// Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("flagworknri", "N");
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public CboAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_cbo", "APPS.XX_NRI_CBO_VW", null, new CboMap());
	}
}
