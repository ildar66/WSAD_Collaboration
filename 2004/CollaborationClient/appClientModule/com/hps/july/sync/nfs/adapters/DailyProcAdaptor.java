package com.hps.july.sync.nfs.adapters;

import java.sql.*;
import java.util.ListIterator;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Dmitry Dneprov
 * Вызов процедуры переноса данных в таблицы NRI.
 */
public class DailyProcAdaptor extends DefaultCollaboration {

	private static class DailyProcMap extends ColumnMap {
		DailyProcMap() {
			super();
		}
	}

	public DailyProcAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "dummy", "dummy", null, new DailyProcMap(), sconLOG_DB);
	}

	protected boolean doBeforeTask(Query qry) {
		boolean result = false;
		DB db = getTargetDb();
		CDBCResultSet rs = new CDBCResultSet();
		Connection conn = DB.getConnection(db);
		rs.executeQuery(conn, "EXECUTE PROCEDURE makerelnfs2nri()", new Object []{}, 1);
		try {conn.close();} catch (Exception e) {}
		ListIterator it = rs.listIterator();
		if (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			Integer res = (Integer)ro.getColumn("col1").asObject();
			System.out.println("Result=" + res);
			result = new Integer(0).equals(res);
		}
		return result;
	}

}
