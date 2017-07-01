package com.hps.july.sync.oss.adapters;

import java.sql.*;
import java.util.ListIterator;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для загрузки данных от оборудования.
 */
public abstract class OSSAdaptor extends DefaultCollaboration {

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	/**
	 * Конструктор по умолчанию
	 *
	 */
	private OSSAdaptor() {
		super(null, null, null, null, null, null, null);
	}

	/**
	 * Конструктор с параметрами
	 * @param targetDB
	 * @param sourceDB
	 * @param argTableNameTARGET_DB
	 * @param argTableNameJOIN_DB
	 * @param lastUpdateDate_nameColumnJOIN_DB
	 * @param colMap
	 * @param sconLOG_DB
	 */
	protected OSSAdaptor(
		DB targetDB,
		DB sourceDB,
		String argTableNameTARGET_DB,
		String argTableNameJOIN_DB,
		String lastUpdateDate_nameColumnJOIN_DB,
		ColumnMap colMap,
		DB sconLOG_DB) {
			super(targetDB, sourceDB, argTableNameTARGET_DB, argTableNameJOIN_DB, lastUpdateDate_nameColumnJOIN_DB, colMap, sconLOG_DB);
	}

	/**
	 * Создание / нахождение записи о загрузке данных
	 * @param conn
	 * @return
	 */
	protected Integer CreateLoad(Connection conn, java.util.Date loadDate, Integer argPLMN) {
		Integer result = null;
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(conn, "SELECT * FROM equipmentdataloads WHERE dataloaddate = ? AND plmn = ?", new Object []{new java.sql.Date(loadDate.getTime()), argPLMN}, 1);
		ListIterator it = rs.listIterator();
		if (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			result = (Integer)ro.getColumn("dataloadid").asObject();
		} else {
			if (rs.executeUpdate(conn, "INSERT INTO equipmentdataloads(dataloaddate, plmn) VALUES(?, ?)", new Object []{new java.sql.Date(loadDate.getTime()), argPLMN})) {
				CDBCResultSet rs2 = new CDBCResultSet();
				rs2.executeQuery(conn, "SELECT DBINFO('sqlca.sqlerrd1') serres FROM dummy", new Object []{}, 1);
				ListIterator it2 = rs2.listIterator();
				if (it2.hasNext()) {
					CDBCRowObject ro2 = (CDBCRowObject)it2.next();
					result = (Integer)ro2.getColumn("serres").asObject();
				}
			}
		}

		return result;
	}

	/**
	 * Установка соответствия между GsmID и типом оборудования (сетью)
	 * @param conn
	 * @param argGSMID
	 * @param argPLMN
	 * @return
	 */
	protected boolean addGSMID2Network(Connection conn, Integer argGSMID, Integer argCellID, Integer argPLMN) {
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeUpdate(conn, "DELETE FROM gsmid2networks WHERE gsmid = ?",
			new Object[] {argGSMID});
		return rs.executeUpdate(conn, "INSERT INTO gsmid2networks(gsmid, plmn, cellid) " +
			"VALUES(?, ?, ?)", new Object[] {argGSMID, argPLMN, argCellID});
	}

	/**
	 * Нахождение префикса региона, в который грузятся данные
	 * @param conn
	 * @param argPLMN
	 * @return
	 */
	protected String findRegionPrefix(Connection conn, Integer argPLMN) {
		String result = null;
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(conn, "SELECT supregcode FROM networks n, superregions s WHERE n.plmn = ? AND s.supregid = n.supregid",
			new Object[] {argPLMN}, 1);
		ListIterator it = rs.listIterator();
		if (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject)it.next();
			result = ro.getColumn("supregcode").asString();
		}
		return result;
	}

	/**
	 * Extracting CellIndex from CellID (first 4 digits from 5)
	 * @param argCellID
	 * @return
	 */
	protected Integer extractCellIndex(Integer argCellID) {
		Integer result = null;

		if (argCellID != null) {
			String strCellID = argCellID.toString();
			if (strCellID.length() > 4) {
				try {
					result = new Integer(Integer.parseInt(strCellID.substring(0, 4)));
				} catch (NumberFormatException e) {
					// Ignore error
				}
			}
		}

		return result;
	}

	/**
	 * Construct BS index (CellName without last number) from CellID
	 * @param conn
	 * @param argCellID
	 * @param argPLMN
	 * @return
	 */
	protected Integer makeBSIndexFromCellID(Connection conn, Integer argCellID, Integer argPLMN) {
		Integer result = null;

		Integer cellIndex = extractCellIndex(argCellID);

		if (cellIndex != null) {
			// Find latest cidnes version
			String cidnesVersion = null;
			CDBCResultSet rs = new CDBCResultSet();
			rs.executeQuery(conn, "SELECT max(version) vers FROM cidnes_request WHERE status='C'",
				new Object[] {}, 1);
			ListIterator it = rs.listIterator();
			if (it.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject)it.next();
				cidnesVersion = ro.getColumn("vers").asString();
			}

			String cellNameRRXX = null;
			// Check Exclusions
			CDBCResultSet rs2 = new CDBCResultSet();
			rs2.executeQuery(conn, "SELECT p.cellname FROM cidnes_supreg2div s, cidnes_division d, " +
				"cidnes_exceptprefix p, networks n " +
				"WHERE s.supregid = n.supregid AND n.plmn = ? AND d.id = s.divid AND d.version = ? AND " +
				"p.divid = s.divid AND p.version = d.version AND p.cellid = ?",
				new Object[] {argPLMN, cidnesVersion, cellIndex.toString()}, 1);
			ListIterator it2 = rs2.listIterator();
			if (it2.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject)it2.next();
				cellNameRRXX = ro.getColumn("cellname").asString();
			} else {
				String strRR = cellIndex.toString().substring(0, 2);
				String cellNameRR = null;
				CDBCResultSet rs3 = new CDBCResultSet();
				rs3.executeQuery(conn, "SELECT UNIQUE p.cellname FROM cidnes_supreg2div s, cidnes_division d, " +
					"cidnes_prefix p, networks n " +
					"WHERE s.supregid = n.supregid AND n.plmn = ? AND d.n1 = s.divid AND d.level =2 AND d.version = ? AND " +
					"p.divid = d.id AND p.version = d.version AND p.cellid = ?",
					new Object[] {argPLMN, cidnesVersion, strRR}, 1);
				ListIterator it3 = rs3.listIterator();
				if (it3.hasNext()) {
					CDBCRowObject ro = (CDBCRowObject)it3.next();
					// Check for more than one corresponding number
					if (it3.hasNext()) {
						// Report all numbers
						System.out.println("Multiple CellName for CI: " + strRR + ", plmn = " + argPLMN);
						System.out.println("  CELLNAME = " + ro.getColumn("cellname").asString());
						while (it3.hasNext()) {
							ro = (CDBCRowObject)it3.next();
							System.out.println("  CELLNAME = " + ro.getColumn("cellname").asString());
						}
					} else {
						cellNameRR = ro.getColumn("cellname").asString();
						cellNameRRXX = cellNameRR + cellIndex.toString().substring(2, 4);
					}
				}
			}
			if (cellNameRRXX != null) {
				if ( (argPLMN.intValue() == 9) || (argPLMN.intValue() == 1) ) {
					result = new Integer(Integer.parseInt(cellNameRRXX));
				} else {
					String supregCode = null;
					// Determine region code
					CDBCResultSet rs4 = new CDBCResultSet();
					rs4.executeQuery(conn, "SELECT supregcode FROM superregions s, networks n " +
						"WHERE n.plmn = ? AND s.supregid = n.supregid",
						new Object[] {argPLMN}, 1);
					ListIterator it4 = rs4.listIterator();
					if (it4.hasNext()) {
						CDBCRowObject ro = (CDBCRowObject)it4.next();
						supregCode = ro.getColumn("supregcode").asString();
					}
					if (supregCode != null) {
						try {
							result = new Integer(Integer.parseInt(supregCode + cellNameRRXX));
						} catch (NumberFormatException e) {
							// Supress error
						}
					}
				}
			}
		}

		return result;
	}
}
