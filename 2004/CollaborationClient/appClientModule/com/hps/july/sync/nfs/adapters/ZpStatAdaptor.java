package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса поставщиков.
 */
public class ZpStatAdaptor extends DefaultCollaboration {

	private static class ZpStatMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		ZpStatMap() {
			super();
			//         NFS              NRI            isKey
			addMap("po_header_id", "idzpnfs", true);
			addMap("po_line_id", "po_line_id", true);
			addMap("account", "accountbuch", true);
			addMap("category_id", "categorybuch", false);
			addMap("amount", "amount", false);

			// Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("flagworknri", "N");
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public ZpStatAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "nfs_zp_stat", "APPS.XX_NRI_ZP_STAT_VW", "last_update_date", new ZpStatMap());
	}
	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#getSqlChangeInSource()
	 */
	protected String getSqlChangeInSource() {
		StringBuffer result = new StringBuffer();
		result.append("SELECT * FROM ");
		result.append(getSourceTable());
		java.sql.Timestamp lastdate = getLastUpdateDate();
		if (lastdate != null) {
			result.append(" WHERE ");
			result.append("po_header_id in (SELECT zp.po_header_id FROM APPS.XX_NRI_ZP_VW zp WHERE zp.last_update_date > ?) ");
		}
		return result.toString();
	}

}
