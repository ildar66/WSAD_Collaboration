package com.hps.july.sync.trailcom.adapters;

import java.sql.*;

import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;
import upload.Equipment;


/**
 * @author Dmitry Dneprov
 * Адаптер для вызова процедуры обработки данных.
 * Вызывает внешний модуль загрузки данных от Trailcom
 */
public class TrailcomEquipmentCalcAdaptor extends DefaultCollaboration {

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	public TrailcomEquipmentCalcAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB) {
		super(targetDB, sourceDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
	}

	protected boolean doBeforeTask(Query qry) {
		boolean result = true;
		Connection conn = null;
		try {
			DB db = getTargetDb();
			conn = DB.getConnection(db);
			System.out.println("Sending connection to trailcom: " + conn);
			Equipment eq = new Equipment(new Integer(qry.getQueryId()), conn, conn, false);
			result = eq.execute();
		} catch (Throwable e) {
			e.printStackTrace(System.out);
			result = false;
		} finally {
			try {conn.close();} catch (Exception e) {}
		}
		return result;
	}

}
