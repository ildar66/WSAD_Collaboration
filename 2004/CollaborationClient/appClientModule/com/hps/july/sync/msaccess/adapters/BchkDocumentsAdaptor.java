/*
 * Created on 27.01.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.msaccess.adapters;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.hps.july.cdbc.lib.*;
import com.hps.july.util.TimeCounter;
import com.hps.july.core.*;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BchkDocumentsAdaptor extends DefaultCollaboration {

	private static class BchkDocumentsMap extends ColumnMap {
		/**
		 * Конструктор ColumnMap
		 */
		BchkDocumentsMap() {
			super();
			// NRI(bchkdocuments), msAccess(Документы), isKey
			addMap("document", "document", true);
			addMap("bsnum", "bsnum", false);
			addMap("calcdate", "calcdate", false);
			addMap("afs1800date", "afs1800date", false);
			addMap("afs900date", "afs900date", false);
			addMap("bts1800date", "bts1800date", false);
			addMap("bts900date", "bts900date", false);
			addMap("cntrdate", "cntrdate", false);
		}
	}

	/**
	 * Constructor for BchkDocumentsAdaptor.
	 * @param sourseTargerDB
	 * @param sourseJoinDB
	 * @param sconLOG_DB
	 */
	public BchkDocumentsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "Документы", "bchkdocuments", "calcdate", new BchkDocumentsMap(), sconLOG_DB);
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#setParams(java.sql.PreparedStatement, java.util.Collection, java.util.HashMap, int)
	 */
	protected boolean setParams(PreparedStatement st, Collection params, HashMap hashMap, int argStartParam) {
		boolean result = true;
		Iterator it = params.iterator();
		int i = argStartParam;
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = hashMap.get(key);
			if (value != null && value.getClass() == String.class)
				value = ((String) value).trim();
			try {
				if (value != null)
					st.setObject(i, value);
				else {
					if (key.equals("afs1800date") || key.equals("afs900date") || key.equals("bts1800date") || key.equals("bts900date") || key.equals("cntrdate")) {
						//System.out.println("set Types.NUM for " + key);
						st.setNull(i, Types.TIMESTAMP);
					} else {
						st.setObject(i, value);
					}
				}
			} catch (SQLException e) {
				result = false;
				//qry.addLogMessage(qry.MSG_ERROR, "Невозможно установить SQL параметр #" + i + ", key=" + key + ", value=" + value);
				System.out.println("BchkDocumentsAdaptor: Cannot set SQL parameter #" + i + ", key=" + key + ", value=" + value);
				System.out.println("BchkDocumentsAdaptor: SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
				//e.printStackTrace(System.out);
			}
			i++;
		}
		return result;
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
				System.out.println("Не могу закрыть targetDbConnection: " + e.getMessage());
				e.printStackTrace();
			}
			try {
				getSourceDbConnection().close();
			} catch (Exception e) {
				System.out.println("Не могу закрыть sourceDbConnection: " + e.getMessage());
				e.printStackTrace();
			}
			try {
				getLogDbConnection().close();
			} catch (Exception e) {
				System.out.println("Не могу закрыть logDbConnection: " + e.getMessage());
				e.printStackTrace();
			}
			setTargetDbConnection(null);
			setSourceDbConnection(null);
			setLogDbConnection(null);
		}
		tc.finish("Transfer");

	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	public boolean doBeforeTask(Query qry) {
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			long current = System.currentTimeMillis();
			long time = current;
			if (getLastUpdateDate() != null) {
				time = getLastUpdateDate().getTime();
			} else {
				System.out.println("getLastUpdateDate() == null");
				//time = System.currentTimeMillis() + 2 * (24 * 60 * 60 * 1000); //плюс два дня;
				if (qry.getStartDate() == null) {
					System.out.println("getLastUpdateDate() == null поэтому введите setStartDate в таблицу informix.join_db_query");
				} else {
					time = qry.getStartDate().getTime();
				}
			}
			java.sql.Date dateStart = new java.sql.Date(time);
			System.out.println("BchkDocumentsAdaptor.doBeforeTask: цикл от даты последнего запуска = " + format.format(dateStart));
			//цикл от даты последнего запуска (getLastUpdateDate()) до сегодняшней даты:
			for (long i = time; time < current; time += 1 * (24 * 60 * 60 * 1000)) {
				java.sql.Date date = new java.sql.Date(time);
				String dateStr = format.format(date);
				//EXECUTE PROCEDURE bchkDocuments:
				if (st == null)
					st = getSourceDbConnection().createStatement();
				System.out.println("EXECUTE PROCEDURE bchkDocuments(" + qry.getQueryId() + ", '" + dateStr + "')");
				rs = st.executeQuery("EXECUTE PROCEDURE bchkDocuments(" + qry.getQueryId() + ", '" + dateStr + "')");
				if (rs.next()) {
					int resCode = rs.getInt(1);
					if (resCode != 0) {
						//String resMsg = rs.getString(2);
						String resMsg = "Error EXECUTE PROCEDURE bchkDocuments(" + qry.getQueryId() + ", " + date + ")";
						QueryProcessing.addLogMessage(getLogDbConnection(), getQuery(), QueryProcessing.MSG_ERROR, resMsg);
						result = false;
						break;
					} else {
						result = true;
					}
				}
			}
			if (result) {
				//выполняем удаление данных в "Документы" targetDB.
				format = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr = format.format(dateStart);
				ps = getTargetDbConnection().prepareStatement("select document from Документы where calcdate >=#" + dateStr + "#");
				rs = ps.executeQuery();
				while(rs.next()){
					int doc = rs.getInt(1);
					deletePositionsForDocument(doc);
				}
				ps = getTargetDbConnection().prepareStatement("delete from Документы where calcdate >=#" + dateStr + "#");
				int count = ps.executeUpdate();
				System.out.println("Pезультат удаления Документы =" + count + " строк"); //temp
			}
		} catch (SQLException e) {
			System.out.println("SQLException BchkDocumentsAdaptor.doBeforeTask: ");
			System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error BchkDocumentsAdaptor.doBeforeTask: ");
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return result;
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
	 * @see com.hps.july.core.DefaultCollaboration#insertInTarget(com.hps.july.core.RowDBObject)
	 */
	protected boolean insertInTarget(RowDBObject argObj) throws CollaborationException {
		boolean result = super.insertInTarget(argObj);
		if (result) {
			result = insertPositionsForDocument(argObj);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#performUpdateTargetDb(com.hps.july.core.RowDBObject)
	 */
	protected boolean performUpdateTargetDb(RowDBObject argObj) throws CollaborationException {
		boolean result = super.performUpdateTargetDb(argObj);
		if (result) {
			int doc = ((Integer)argObj.getKeysColumns().get("document")).intValue();
			result = deletePositionsForDocument(doc);
		}
		if (result) {
			result = insertPositionsForDocument(argObj);
		}
		return result;
	}

	//добавление позиций:
	private boolean insertPositionsForDocument(RowDBObject argObj) {
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			System.out.println("заполняем позиции для argObj.document=" + argObj.getKeysColumns().get("document")); //temp
			ps = getSourceDbConnection().prepareStatement("SELECT document, resourcename, qty, name, serials, comment, bsnum FROM bchkdocpositions WHERE document=" + argObj.getKeysColumns().get("document"));
			pst = getTargetDbConnection().prepareStatement("INSERT INTO Состав_оборудования (document, resourcename, qty, name, serials, comment, bsnum) VALUES (?,?,?,?,?,?,?)");
			ps.execute();
			rs = ps.getResultSet();
			int colCount = 7;
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++) {
					Object value = rs.getObject(i);
					if (value != null && value.getClass() == String.class)
						value = ((String) value).trim();
					pst.setObject(i, value);
				}
				pst.execute();
			}
			result = true;
		} catch (SQLException e) {
			System.out.println("SQLException BchkDocumentsAdaptor.insertPositionsForDocument: ");
			System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error BchkDocumentsAdaptor.insertPositionsForDocument: ");
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (pst != null)
					pst.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	//удаление позиций:
	private boolean deletePositionsForDocument(int document) {
		Statement ps = null;
		boolean result = false;
		try {
			System.out.println("удаляем позиции для argObj.document=" + document); //temp
			ps = getTargetDbConnection().createStatement();
			String sql = "DELETE FROM Состав_оборудования WHERE document=" + document;
			System.out.println("sql ="+ sql);
			ps.executeUpdate(sql);
			result = true;
		} catch (SQLException e) {
			System.out.println("SQLException BchkDocumentsAdaptor.deletePositionsForDocument: ");
			System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error BchkDocumentsAdaptor.deletePositionsForDocument: ");
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

}
