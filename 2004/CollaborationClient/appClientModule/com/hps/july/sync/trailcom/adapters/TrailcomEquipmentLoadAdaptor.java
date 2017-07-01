package com.hps.july.sync.trailcom.adapters;

import java.sql.*;

import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;
import upload.Equipment;


/**
 * @author Dmitry Dneprov
 * ������� ��� ������ ��������� ��������� ������.
 * �������� ������� ������ �������� ������ �� Trailcom
 */
public class TrailcomEquipmentLoadAdaptor extends DefaultCollaboration {

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	public TrailcomEquipmentLoadAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB) {
		super(targetDB, sourceDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
	}

	protected boolean doBeforeTask(Query qry) {
		boolean result = true;
		Connection conn = null;
		try {
			DB db = getTargetDb();
			conn = DB.getConnection(db);
			Equipment eq = new Equipment(new Integer(qry.getQueryId()), conn, conn, true);
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
