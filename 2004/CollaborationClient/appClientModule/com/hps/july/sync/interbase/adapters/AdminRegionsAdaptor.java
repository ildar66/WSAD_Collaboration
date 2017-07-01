package com.hps.july.sync.interbase.adapters;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

import java.sql.Connection;
import java.util.ListIterator;

/**
 * @author Dmitry Dneprov
 * Вызов процедуры переноса данных в таблицы NRI.
 */
public class AdminRegionsAdaptor extends DefaultCollaboration {

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	public AdminRegionsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
	}

	protected boolean doBeforeTask(Query qry) {
		boolean result = false;
		DB db = getTargetDb();
		CDBCResultSet rs = new CDBCResultSet();
		Connection conn = DB.getConnection(db);
		rs.executeQuery(conn, "EXECUTE PROCEDURE kzlTransferRegions(?)", new Object []{new Integer(qry.getQueryId())}, 1);
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
