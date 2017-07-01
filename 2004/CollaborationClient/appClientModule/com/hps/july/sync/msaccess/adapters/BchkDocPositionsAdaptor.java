/*
 * Created on 27.01.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.msaccess.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BchkDocPositionsAdaptor extends DefaultCollaboration {

	private static class BchkDocPositionsMap extends ColumnMap {
		/**
		 * Конструктор ColumnMap
		 */
		BchkDocPositionsMap() {
			super();
			// NRI(bchkdocpositions), msAccess(Состав_оборудования), isKey

			addMap("document", "Номер док-та NRI", false);
			addMap("resourcename", "Тип аппаратуры", false);
			addMap("qty", "Кол-во станций", false);
			addMap("name", "Наименование", false);
			addMap("serials", "Серийный номер", false);
			addMap("comment", "Примечание", false);
		}
	}

	/**
	 * Constructor for BchkDocPositionsAdaptor.
	 * @param sourseTargerDB
	 * @param sourseJoinDB
	 * @param sconLOG_DB
	 */
	public BchkDocPositionsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "Состав_оборудования", "bchkdocpositions", null, new BchkDocPositionsMap(), sconLOG_DB);
	}

}
