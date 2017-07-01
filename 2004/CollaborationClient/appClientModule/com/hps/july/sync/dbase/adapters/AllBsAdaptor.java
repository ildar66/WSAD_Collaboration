package com.hps.july.sync.dbase.adapters;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.util.TimeCounter;
import com.hps.july.core.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AllBsAdaptor extends DefaultCollaboration {

	private static class AllBsMap extends ColumnMap {
		/**
		 * Конструктор ColumnMap
		 */
		AllBsMap() {
			super();
			// NRI(allPosForBelyakov), dBase(target), isKey
			addMap("Id", "Id", true);

			addMap("Gsm", "Gsm", false);
			addMap("Dcs", "Dcs", false);
			addMap("Damps", "Damps", false);
			addMap("Gsm_name", "Gsm_name", false);
			addMap("Damps_name", "Damps_name", false);
			addMap("Region", "Region", false);
			addMap("Address", "Address", false);
			addMap("Latitude", "Latitude", false);
			addMap("Longitude", "Longitude", false);
			addMap("Brief", "Brief", false);
			addMap("Responsib", "Responsib", false);
			addMap("Finder", "Finder", false);

		}
	}

	/**
	 * Constructor for AllBsAdaptor.
	 * @param sourseTargerDB
	 * @param sourseJoinDB
	 * @param sconLOG_DB
	 */
	public AllBsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "allbs", "allPosForBelyakov", null, new AllBsMap(), sconLOG_DB);
	}

	/**
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(Query)
	 */
	public boolean doBeforeTask(Query qry) {
		Statement st = null;
		ResultSet rs = null;
		try {
			//EXECUTE PROCEDURE getAllPosForBelyakov:
			st = getSourceDbConnection().createStatement();
			rs = st.executeQuery("EXECUTE PROCEDURE getAllPosForBelyakov(" + qry.getIdApp() + ")");
			if (rs.next()) {
				int resCode = rs.getInt(1);
				if (resCode != 0) {
					String resMsg = rs.getString(2);
					QueryProcessing.addLogMessage(getLogDbConnection(), getQuery(), QueryProcessing.MSG_ERROR, resMsg);
					return false;
				}
			}
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			//"SET EXCLUSIVE ON":
			st = getTargetDbConnection().createStatement();
			st.execute("SET EXCLUSIVE ON");
			//st.execute("USE allbs EXCLUSIVE");
			//удаляем allbs:
			st.executeUpdate("delete from allbs");
			//st = getTargetDbConnection().createStatement();
			st.executeUpdate("pack");
			//"SET EXCLUSIVE OFF":
			//st.execute("SET EXCLUSIVE OFF");
			//st.execute("CLOSE DATABASES");
			//st.execute("USE allbs SHARED");

			return true;
		} catch (SQLException e) {
			System.out.println("Error executing procedure getAllPosForBelyakov = ");
			System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			//System.out.println("finally ok!!"); //temp
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
			}
		}
		return false;
	}
	/// Обобщенная процедура установки SQL-параметров
	protected boolean setParams(PreparedStatement st, Collection params, HashMap hashMap, int argStartParam) {
		boolean result = true;
		Iterator it = params.iterator();
		int i = argStartParam;
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = hashMap.get(key);
			try {
				if (value != null)
					st.setObject(i, value);
				else {
					if (key.equals("gsm") || key.equals("dcs") || key.equals("damps") || key.equals("latitude") || key.equals("longitude")) {
						st.setDouble(i, 0);
						//System.out.println("set Types.NUM for " + key);
					} else {
						//st.setNull(i, Types.CHAR);
						//System.out.println("set Types.CHAR for " + key);
						st.setString(i, "");
					}
				}
			} catch (SQLException e) {
				result = false;
				//qry.addLogMessage(qry.MSG_ERROR, "Невозможно установить SQL параметр #" + i + ", key=" + key + ", value=" + value);
				System.out.println("AllBsAdaptor Cannot set SQL parameter #" + i + ", key=" + key + ", value=" + value);
				System.out.println("AllBsAdaptor SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
				//e.printStackTrace(System.out);
			}
			i++;
		}
		return result;
	}
	/**
	 * @see com.hps.july.core.Collaboration#findDeletedInSourseDeleteInTarget(com.hps.july.core.Query)
	 */
	public void findDeletedInSourseDeleteInTarget(Query qry) {
		QueryProcessing.startProcessing(getLogDbConnection(), qry);
		QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "findDeletedInSourseDeleteInTarget not Supported from AllBsAdaptor!!!");
		QueryProcessing.finishError(getLogDbConnection(), qry);
	}
	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#compareSourceAndTargetCounts()
	 */
	protected void compareSourceAndTargetCounts() {
		try {
			if ((getSourceDbConnection() != null) && (getTargetDbConnection() != null)) {
				CDBCResultSet countSet1 = new CDBCResultSet();
				CDBCResultSet countSet2 = new CDBCResultSet();
				countSet1.executeQuery(getSourceDbConnection(), "SELECT count(*) AS cnt FROM " + getSourceTable(), new Object[] {
				}, 0);
				countSet2.executeQuery(getTargetDbConnection(), "SELECT count(*) AS cnt FROM " + getTargetTable(), new Object[] {
				}, 0);
				ListIterator it1 = countSet1.listIterator();
				ListIterator it2 = countSet2.listIterator();
				if (it1.hasNext() && it2.hasNext()) {
					CDBCRowObject ro1 = (CDBCRowObject) it1.next();
					CDBCRowObject ro2 = (CDBCRowObject) it2.next();
					if (!ro1.getColumn("cnt").asObject().equals(ro2.getColumn("cnt").asObject())) {
						QueryProcessing.addLogMessage(
							getLogDbConnection(),
							getQuery(),
							QueryProcessing.MSG_WARNING,
							"Количество записей в системе JOIN_DB: "
								+ getSourceTable()
								+ " = "
								+ ro1.getColumn("cnt").asString()
								+ ", в системе TARGET_DB: "
								+ getTargetTable()
								+ " = "
								+ ro2.getColumn("cnt").asString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR WHILE COUNT(*): " + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.Collaboration#findChangesAndUpdate(com.hps.july.core.Query)
	 */
	public void findChangesAndUpdate(Query argQry) throws CollaborationException {
		setQuery(argQry);
		TimeCounter tc = new TimeCounter(getTargetTable());
		try {
			setSourceDbConnection(DB.getConnection(getSourceDb()));
			setTargetDbConnection(DB.getConnection(getTargetDb()));
			setLogDbConnection(DB.getConnection(getLogDb()));
			doFindChangesAndUpdate();
		} finally {
			try {
				getPreparedSelectSourceDb().close();
			} catch (Exception e) {
			}
			try {
				getPreparedSelectTargetDb().close();
			} catch (Exception e) {
			}
			try {
				getPreparedInsertTargetDb().close();
			} catch (Exception e) {
			}
			try {
				getPreparedUpdateTargetDb().close();
			} catch (Exception e) {
			}
			setPreparedSelectSourceDb(null);
			setPreparedSelectTargetDb(null);
			setPreparedInsertTargetDb(null);
			setPreparedUpdateTargetDb(null);
			try {
				getTargetDbConnection().commit();
				getTargetDbConnection().close();
			} catch (Exception e) {
				System.out.println("Не могу закрыть targetDbConnection: "+ e.getMessage());
				e.printStackTrace();
			}
			try {
				getSourceDbConnection().close();
			} catch (Exception e) {
				System.out.println("Не могу закрыть sourceDbConnection: "+ e.getMessage());
				e.printStackTrace();
			}
			try {
				getLogDbConnection().close();
			} catch (Exception e) {
				System.out.println("Не могу закрыть logDbConnection: "+ e.getMessage());
				e.printStackTrace();
			}
			setTargetDbConnection(null);
			setSourceDbConnection(null);
			setLogDbConnection(null);
		}
		tc.finish("Transfer");

	}

}
