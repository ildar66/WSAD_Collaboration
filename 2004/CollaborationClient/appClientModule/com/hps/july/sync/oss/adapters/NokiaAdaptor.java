package com.hps.july.sync.oss.adapters;

import java.math.BigDecimal;
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
public class NokiaAdaptor extends OSSAdaptor {

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
	public NokiaAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB, Integer argPlmn) {
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
		if (rs.executeUpdate(conn, "DELETE FROM nokiahardwareunits WHERE dataloadid = ?", new Object []{dataLoadID})) {
			if (rs.executeUpdate(conn, "DELETE FROM nokiabts WHERE dataloadid = ?", new Object []{dataLoadID})) {
				if (rs.executeUpdate(conn, "DELETE FROM nokiabcf WHERE dataloadid = ?", new Object []{dataLoadID})) {
					if (rs.executeUpdate(conn, "DELETE FROM nokiabsc WHERE dataloadid = ?", new Object []{dataLoadID})) {
						result = true;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Загрузка контроллеров
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadNokiaBSC(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Nokia BSC");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT * FROM objects WHERE object_class=3", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			BigDecimal latit = (BigDecimal)ro.getColumn("vertical_loc").asObject();
			BigDecimal longit = (BigDecimal)ro.getColumn("horizontal_loc").asObject();
			BigDecimal bdLatit = null;
			BigDecimal bdLongit = null;
			if ( (latit != null) && (longit != null) ) {
				bdLatit = new BigDecimal(latit.doubleValue() / 10000000.0);
				bdLongit = new BigDecimal(longit.doubleValue() / 10000000.0);
			}
			if (!rs.executeUpdate(dstConn, "INSERT INTO nokiabsc(dataloadid, object_id, " +
				"name, address, sys_version, latitude, longitude) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID, ro.getColumn("int_id").asObject(),
					ro.getColumn("name").asString(), ro.getColumn("address").asString(),
					ro.getColumn("sys_version").asString(), bdLatit, bdLongit
				}))
					result = false;
		}
		return result;
	}

	/**
	 * Загрузка BCF (Base Control Function)
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadNokiaBCF(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Nokia BCF");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT * FROM objects WHERE object_class=27", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			if (!rs.executeUpdate(dstConn, "INSERT INTO nokiabcf(dataloadid, object_id, bsc_id) " +
				"VALUES (?, ?, ?)",
				new Object[] {dataLoadID, ro.getColumn("int_id").asObject(),
					ro.getColumn("parent_int_id").asObject()
				}))
					result = false;
		}
		return result;
	}

	/**
	 * Загрузка базовых станций,
	 * на самом деле это сектора по нашей терминологии
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadNokiaBTS(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Nokia BTS");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT o.*, b.LA_ID_LAC, b.BSIC_NCC, b.BSIC_BCC, b.CELL_ID " +
			" FROM objects o, c_bts b WHERE o.object_class=4 AND " +
			"b.int_id = o.int_id AND b.conf_name='<ACTUAL>'", new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			// Calculate GSM ID
			Integer gsmID = null;
			String cellID = ro.getColumn("cell_id").asString();
			Integer ciIndex = null;
			if (cellID != null) {
				Integer ci = null;
				try {
					ci = new Integer(Integer.parseInt(cellID));
					gsmID = makeBSIndexFromCellID(dstConn, ci, plmn);
					ciIndex = extractCellIndex(ci);
				} catch (NumberFormatException e) {
					//
				}
				//gsmid = new Integer(Math.round(new Double(Math.floor(ci.floatValue() / (float)10.0)).floatValue()));
				//System.out.println("CI = " + ci + ", ciIndex = " + ciIndex + ", gsmid = " + gsmID);
			}
			/* Old algorithm
			if (cellID != null) {
				try {
					if (cellID.length() >= 4)
						gsmID = new Integer(Integer.parseInt("3" + cellID.substring(0, 4)));
				} catch (NumberFormatException e) {
					// Ignore
				}
			}
			*/
			if (!rs.executeUpdate(dstConn, "INSERT INTO nokiabts(dataloadid, object_id, bcf_id," +
				"lac, cellid, name, address, ncc, bcc, gsmid) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID, ro.getColumn("int_id").asObject(),
					ro.getColumn("parent_int_id").asObject(),
					ro.getColumn("la_id_lac").asObject(),
					ro.getColumn("cell_id").asObject(),
					ro.getColumn("name").asString(),
					ro.getColumn("address").asString(),
					ro.getColumn("bsic_ncc").asObject(),
					ro.getColumn("bsic_bcc").asObject(),
					gsmID
				}))
					result = false;
			if (gsmID != null) {
				if (!addGSMID2Network(dstConn, gsmID, ciIndex, plmn))
					result = false;
			}
		}
		return result;
	}

	/**
	 * Установка соответствия между ресурсом Nokia и типом оборудования (ресурсом NRI)
	 */
	protected boolean addResource(Connection conn, String argModel) {
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(conn, "SELECT * FROM nokiares2nrires WHERE nokiamodel = ?",
			new Object[] {argModel}, 1);
		ListIterator it = rs.listIterator();
		if (!it.hasNext()) {
			return rs.executeUpdate(conn, "INSERT INTO nokiares2nrires(nokiamodel) " +
				"VALUES(?)", new Object[] {argModel});
		} else
			return true;
	}

	/**
	 * Загрузка элементов оборудования
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadNokiaHardwareUnits(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;
		System.out.println("Loading Nokia Hardware Units");
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(srcConn, "SELECT h.*, a.value sernum " +
			"FROM h_hw_hwunit h, h_hw_attribute a " +
			"WHERE a.parent_id = h.object_id AND a.attr_id = 31 ",
			new Object[]{}, 0);
		ListIterator it = rs.listIterator();
		while (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			String rack = null;
			String shelf = null;
			String slot = null;
			CDBCResultSet rs2 = new CDBCResultSet();
			rs2.executeQuery(srcConn, "SELECT a2.value rack, " +
				"a3.value shelf, a4.value slot " +
				"FROM h_hw_attribute a1, h_hw_attribute a2, " +
				"h_hw_attribute a3, h_hw_attribute a4 " +
				"WHERE " +
				"a1.parent_id = ? AND a1.attr_id = 20 AND " +
				"a2.parent_id = a1.object_id AND a2.attr_id = 21 AND " +
				"a3.parent_id = a1.object_id AND a3.attr_id = 22 AND " +
				"a4.parent_id = a1.object_id AND a4.attr_id = 23",
				new Object[]{ro.getColumn("object_id").asObject()}, 0);
			ListIterator it2 = rs2.listIterator();
			if (it2.hasNext()) {
				CDBCRowObject ro2 = (CDBCRowObject)it2.next();
				rack = ro2.getColumn("rack").asString();
				shelf = ro2.getColumn("shelf").asString();
				slot = ro2.getColumn("slot").asString();
			}
			if (!rs.executeUpdate(dstConn, "INSERT INTO nokiahardwareunits(dataloadid, equipment_id, bcf_id, " +
				"parent_id, unit_name, unit_nro, serial, rack, shelf, slot) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {dataLoadID,
					ro.getColumn("object_id").asObject(),
					ro.getColumn("int_id").asObject(),
					ro.getColumn("parent_id").asObject(),
					ro.getColumn("unit_name").asString(),
					ro.getColumn("unit_nro").asString(),
					ro.getColumn("sernum").asString(),
					rack,
					shelf,
					slot
				}))
					result = false;
			else
				if (!addResource(dstConn, ro.getColumn("unit_name").asString()))
					result = false;
		}
		return result;
	}

	/**
	 * Загрузка данных Nokia
	 * @param srcConn
	 * @param dstConn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean LoadNokiaData(Connection srcConn, Connection dstConn, Integer dataLoadID) {
		boolean result = true;

		if (!LoadNokiaBSC(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadNokiaBCF(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadNokiaBTS(srcConn, dstConn, dataLoadID))
			result = false;
		if (!LoadNokiaHardwareUnits(srcConn, dstConn, dataLoadID))
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
					if (!LoadNokiaData(srcConn, dstConn, idLoad))
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
