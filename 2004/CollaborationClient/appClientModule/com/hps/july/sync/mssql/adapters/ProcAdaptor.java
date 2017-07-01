package com.hps.july.sync.mssql.adapters;

import java.sql.*;
import java.util.ListIterator;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для вызова процедуры обработки данных.
 */
public class ProcAdaptor extends DefaultCollaboration {

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	public ProcAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
	}

	protected boolean doBeforeTask(Query qry) {
		boolean result = false;
		try {
			DB db = getTargetDb();
			CDBCResultSet rs = new CDBCResultSet();
			Connection conn = DB.getConnection(db);
			rs.executeQuery(conn, "EXECUTE PROCEDURE glchPositions(?, 1, 999999)", new Object []{new Integer(qry.getQueryId())}, 1);
			try {conn.close();} catch (Exception e) {}
			ListIterator it = rs.listIterator();
			if (it.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject)it.next();
				Integer res = (Integer)ro.getColumn("col1").asObject();
				System.out.println("Result=" + res);
				result = new Integer(0).equals(res);
			}
		} catch (Throwable t) {
			t.printStackTrace(System.out);
		}
		return result;
	}

}
