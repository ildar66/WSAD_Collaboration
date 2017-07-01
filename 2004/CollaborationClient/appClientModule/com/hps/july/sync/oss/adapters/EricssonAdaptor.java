package com.hps.july.sync.oss.adapters;

import java.sql.*;
import java.util.ListIterator;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.ColumnMap;

/**
 * @author Dmitry Dneprov
 * Адаптер для загрузки данных от оборудования Nokia.
 */
public class EricssonAdaptor extends OSSAdaptor {

	private Integer plmn;

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	/**
	 * Конструктор
	 * @param targetDB
	 * @param sourceDB
	 * @param sconLOG_DB
	 * @param argPlmn
	 */
	public EricssonAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB, Integer argPlmn) {
		super(targetDB, sourceDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
		plmn = argPlmn;
	}


	/**
	 * Очистка предыдущих данных загрузки за туже дату
	 * @param conn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean ClearLoad(Connection conn, Integer dataLoadID) {
		boolean result = false;
		CDBCResultSet rs = new CDBCResultSet();
		if (rs.executeUpdate(conn, "DELETE FROM ericssontrx12 WHERE dataloadid = ?", new Object []{dataLoadID})) {
			if (rs.executeUpdate(conn, "DELETE FROM ericssontg12 WHERE dataloadid = ?", new Object []{dataLoadID})) {
				if (rs.executeUpdate(conn, "DELETE FROM ericssonru2 WHERE dataloadid = ?", new Object []{dataLoadID})) {
					if (rs.executeUpdate(conn, "DELETE FROM ericssonru1 WHERE dataloadid = ?", new Object []{dataLoadID})) {
						if (rs.executeUpdate(conn, "DELETE FROM ericssonmru WHERE dataloadid = ?", new Object []{dataLoadID})) {
							if (rs.executeUpdate(conn, "DELETE FROM ericssonsites WHERE dataloadid = ?", new Object []{dataLoadID})) {
								if (rs.executeUpdate(conn, "DELETE FROM ericssonbsc WHERE dataloadid = ?", new Object []{dataLoadID})) {
									result = true;
								}
							}
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Загрузка таблицы BSC
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadEricssonBSC(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Ericsson BSC");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT * FROM dbo.BSC", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			if (!rs.executeUpdate(dstConn, "INSERT INTO ericssonbsc(dataloadid, " +
				"bpset, bpkey, " +
				"bscname, bsctype, bscver) " +
				"VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("bpset").asObject(),
					ro.getColumn("bpkey").asObject(),
					ro.getColumn("bsc").asString(),
					ro.getColumn("type").asString(),
					ro.getColumn("version").asString()
				}))
					result = false;
		}
		return result;
	}

	/**
	 * Загрузка таблицы Sites
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadEricssonSites(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Ericsson Sites");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT s.*, m.parent FROM dbo.SITE s, dbo.BPMIT m " +
			"WHERE m.bpkey = s.bpkey", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			if (!rs.executeUpdate(dstConn, "INSERT INTO ericssonsites(dataloadid, " +
				"bpset, bpkey, parent, " +
				"sitename) " +
				"VALUES (?, ?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("bpset").asObject(),
					ro.getColumn("bpkey").asObject(),
					ro.getColumn("parent").asObject(),
					ro.getColumn("site").asString()
				}))
					result = false;
		}
		return result;
	}

	/**
	 * Установка соответствия между ресурсом Ericsson и типом оборудования (ресурсом NRI)
	 */
	protected boolean addResource(Connection conn, String argModel) {
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(conn, "SELECT * FROM ericres2nrires WHERE productid = ?",
			new Object[] {argModel}, 1);
		ListIterator it = rs.listIterator();
		if (!it.hasNext()) {
			return rs.executeUpdate(conn, "INSERT INTO ericres2nrires(productid) " +
				"VALUES(?)", new Object[] {argModel});
		} else
			return true;
	}

	protected boolean LoadEricssonRU(Connection srcConn, Connection dstConn, Integer dataLoadID, String sRUType) {
		boolean result = true;
		System.out.println("Loading Ericsson " + sRUType);
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT r.*, m.parent FROM dbo." + sRUType + " r, dbo.BPMIT m " +
			"WHERE m.bpkey = r.bpkey", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			if (!rs.executeUpdate(dstConn, "INSERT INTO ericsson" + sRUType + "(dataloadid, " +
				"bpset, bpkey, parent, " +
				"productid, ruserialno, rurevision, ruposition) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("bpset").asObject(),
					ro.getColumn("bpkey").asObject(),
					ro.getColumn("parent").asObject(),
					ro.getColumn("ru").asString(),
					ro.getColumn("ruserialno").asString(),
					ro.getColumn("rurevision").asString(),
					ro.getColumn("ruposition").asString()
				}))
					result = false;
			else
				if (!addResource(dstConn, ro.getColumn("ru").asString()))
					result = false;
		}
		return result;
	}

	/**
	 * Загрузка таблицы TG12
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadEricssonTG12(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Ericsson TG12");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT s.*, m.parent FROM dbo.TG12 s, dbo.BPMIT m " +
			"WHERE m.bpkey = s.bpkey", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			if (!rs.executeUpdate(dstConn, "INSERT INTO ericssontg12(dataloadid, " +
				"bpset, bpkey, parent) " +
				"VALUES (?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("bpset").asObject(),
					ro.getColumn("bpkey").asObject(),
					ro.getColumn("parent").asObject()
				})) {
					// Ignore this error
					//result = false;
			}
		}
		return result;
	}

	/**
	 * Загрузка таблицы TRX12
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadEricssonTRX12(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Ericsson TRX12");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT s.*, m.parent FROM dbo.TRX12 s, dbo.BPMIT m " +
			"WHERE m.bpkey = s.bpkey", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			// Calculate GSMID
			String cellID = ro.getColumn("cell").asString();
			Integer gsmID = null;
			if ( (cellID != null) && (cellID.length() > 5) ) {
				try {
					gsmID = new Integer(Integer.parseInt(cellID.substring(0, 5)));
				} catch (NumberFormatException e) {
					gsmID = null;
				}
			}
			if (!rs.executeUpdate(dstConn, "INSERT INTO ericssontrx12(dataloadid, " +
				"bpset, bpkey, parent, cell, gsmid) " +
				"VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("bpset").asObject(),
					ro.getColumn("bpkey").asObject(),
					ro.getColumn("parent").asObject(),
					ro.getColumn("cell").asString(),
					gsmID
				})) {
					// Ignore this error
					//result = false;
			}
			if (gsmID != null) {
				if (!addGSMID2Network(dstConn, gsmID, gsmID, plmn))
					result = false;
			}
		}
		return result;
	}

	/**
	 * Загрузка данных Ericsson (Sybase)
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadEricssonData(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;

		if (!LoadEricssonBSC(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadEricssonSites(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadEricssonRU(srcConn, dstConn, dataLoadID, "RU1"))
			result = false;
		if (!LoadEricssonRU(srcConn, dstConn, dataLoadID, "RU2"))
			result = false;
		if (!LoadEricssonRU(srcConn, dstConn, dataLoadID, "MRU"))
			result = false;
		if (!LoadEricssonTG12(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadEricssonTRX12(srcConn, dstConn, dataLoadID))
			result = false;
		return result;
	}

	/**
	 * Перекрываем метод в исходном Collaboration
	 */
	protected boolean doBeforeTask(Query qry) {
		boolean result = true;
		Connection srcConn = null;
		Connection dstConn = null;
		try {
			DB srcDB = getSourceDb();
			DB dstDB = getTargetDb();
			srcConn = DB.getConnection(srcDB);
			dstConn = DB.getConnection(dstDB);
			java.util.Date loadDate = new java.util.Date(); //cal.getTime();

			Integer idLoad = CreateLoad(dstConn, loadDate, plmn);
			if (idLoad != null) {
				if (ClearLoad(dstConn, idLoad)) {
					if (!LoadEricssonData(srcConn, dstConn, idLoad))
						result = false;
				} else
					result = false;
			} else
				result = false;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			result = false;
		} finally {
			try {srcConn.close();} catch (Exception e) {}
			try {dstConn.close();} catch (Exception e) {}
		}
		return result;
	}

}
