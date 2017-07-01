package com.hps.july.core;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Shafigullin Ildar
 *
 * Процессор-обработчик запросов на синхронизацию данных.
 */
public class QueryProcessing {
	public static final String MSG_INFO = "I";
	public static final String MSG_ERROR = "E";
	public static final String MSG_WARNING = "W";

	/// Очистка старых выполнявшихся процессов
	public static void clearQueries(DB logDB) {
		CDBCResultSet datesSet = new CDBCResultSet();
		Connection acon = DB.getConnection(logDB);
		datesSet.executeUpdate(acon, "UPDATE JOIN_DB_query SET reqstate = 'R' WHERE reqstate = 'O'", null);
		try {
			acon.close();
		} catch (Exception e) {
		}
	}

	/// Нахождение самого древнего необработанного запроса
	public static Query findLatestQuery(DB logDB) {
		Query result = null;
		CDBCResultSet datesSet = new CDBCResultSet();
		Connection acon = DB.getConnection(logDB);
		DB.setLockModeWait(acon);
		datesSet.executeQuery(acon, "SELECT * FROM JOIN_DB_query WHERE reqstate = 'R' ORDER BY posttime ASC, idquery ASC", null, 0);
		ListIterator it = datesSet.listIterator();
		if (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject) it.next();
			result = new Query(ro.getColumn("reqtype").asString());
			Integer qryId = (Integer) ro.getColumn("idquery").asObject();
			result.setQueryId(qryId.intValue());
			result.setStartDate((java.sql.Date) ro.getColumn("selstartdate").asObject());
			result.setEndDate((java.sql.Date) ro.getColumn("selenddate").asObject());
            result.setParameters(ro.getColumn("reqparams").asString());
            Integer idApp = (Integer) ro.getColumn("idapp").asObject();
			result.setIdApp(idApp.intValue());
		}
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Отметить начало обработки запроса
	public static boolean startProcessing(DB logDB, Query query) {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "UPDATE JOIN_DB_query SET reqstate='O', starttime=? WHERE idquery=?";
		Connection acon = DB.getConnection(logDB);
		DB.setLockModeWait(acon);
		if (!crs.executeUpdate(acon, sql, new Object[] { new java.sql.Date(new java.util.Date().getTime()), new Integer(query.getQueryId())}))
			result = false;
		if (!addLogMessage(acon, query, MSG_INFO, "Начало обработки запроса"))
			result = false;
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Отметить начало обработки запроса
	public static boolean startProcessing(Connection acon, Query query) {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "UPDATE JOIN_DB_query SET reqstate='O', starttime=? WHERE idquery=?";
		if (!crs.executeUpdate(acon, sql, new Object[] { new java.sql.Date(new java.util.Date().getTime()), new Integer(query.getQueryId())}))
			result = false;
		if (!addLogMessage(acon, query, MSG_INFO, "Начало обработки запроса"))
			result = false;

		return result;
	}

    public static boolean finishSuccess(DB db, Query query) {
        Connection connection = DB.getConnection(db);
        try {
            return finishSuccess(connection, query);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
            }
        }

    }


    public static boolean finishSuccess(Connection acon, Query query) {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "UPDATE JOIN_DB_query SET reqstate='Y', finishtime=? WHERE idquery=?";
		if (!crs.executeUpdate(acon, sql, new Object[] { new java.sql.Date(new java.util.Date().getTime()), new Integer(query.getQueryId())}))
			result = false;
		if (!addLogMessage(acon, query, MSG_INFO, "Успешное окончание обработки запроса"))
			result = false;
		return result;
	}

	/// Пометить запрос как обработанный с ошибкой
	public static boolean finishError(DB logDB, Query query) {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "UPDATE JOIN_DB_query SET reqstate='E', finishtime=? WHERE idquery=?";
		Connection acon = DB.getConnection(logDB);
		DB.setLockModeWait(acon);
		if (!crs.executeUpdate(acon, sql, new Object[] { new java.sql.Date(new java.util.Date().getTime()), new Integer(query.getQueryId())}))
			result = false;
		if (!addLogMessage(acon, query, MSG_INFO, "Ошибочное окончание обработки запроса"))
			result = false;
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Пометить запрос как обработанный с ошибкой
	public static boolean finishError(Connection acon, Query query) {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "UPDATE JOIN_DB_query SET reqstate='E', finishtime=? WHERE idquery=?";
		if (!crs.executeUpdate(acon, sql, new Object[] { new java.sql.Date(new java.util.Date().getTime()), new Integer(query.getQueryId())}))
			result = false;
		if (!addLogMessage(acon, query, MSG_INFO, "Ошибочное окончание обработки запроса"))
			result = false;
		return result;
	}

	/// Добавить сообщение в журнал
	public static boolean addLogMessage(DB logDB, Query query, String argMessageType, String argMessageText) {
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "INSERT INTO JOIN_DB_querylog(idquery, typemsg, txtmsg) VALUES (?, ?, ?)";
		Connection acon = DB.getConnection(logDB);
		DB.setLockModeWait(acon);
		boolean result = crs.executeUpdate(acon, sql, new Object[] { new Integer(query.getQueryId()), argMessageType, argMessageText });
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Добавить сообщение в журнал
	public static boolean addLogMessage(Connection acon, Query query, String argMessageType, String argMessageText) {
		CDBCResultSet crs = new CDBCResultSet();
		String sql = "INSERT INTO JOIN_DB_querylog(idquery, typemsg, txtmsg) VALUES (?, ?, ?)";
		return crs.executeUpdate(acon, sql, new Object[] { new Integer(query.getQueryId()), argMessageType, argMessageText });
	}
	//существует ли выполняемый процесс с данным IdAPP:
	public static boolean isExistRunProcessForIdApp(DB logDB, int IdApp) {
        Connection acon = DB.getConnection(logDB);
        try {
            CDBCResultSet resSet = new CDBCResultSet();
            DB.setLockModeWait(acon);
            resSet.executeQuery(acon, "SELECT * FROM JOIN_DB_query WHERE reqstate='O' AND IdApp = " + IdApp, null, 0);
            ListIterator it = resSet.listIterator();
            if (it.hasNext()) {
                return true;
            }

            return false;
        } finally{
            try {
                acon.close();
            } catch (Exception e) {}
        }
	}
	//
	public static List findListQuery(DB logDB) {
		List result = new ArrayList();
		CDBCResultSet datesSet = new CDBCResultSet();
		Connection acon = DB.getConnection(logDB);
		DB.setLockModeWait(acon);
		datesSet.executeQuery(acon, "SELECT * FROM JOIN_DB_query WHERE reqstate = 'R' ORDER BY idapp ASC, idquery ASC", null, 0);
		ListIterator it = datesSet.listIterator();
		while(it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject) it.next();
			Query qry = new Query(ro.getColumn("reqtype").asString());
			Integer qryId = (Integer) ro.getColumn("idquery").asObject();
			qry.setQueryId(qryId.intValue());
			qry.setStartDate((java.sql.Date) ro.getColumn("selstartdate").asObject());
			qry.setEndDate((java.sql.Date) ro.getColumn("selenddate").asObject());
            qry.setParameters(ro.getColumn("reqparams").asString());
			Integer idApp = (Integer) ro.getColumn("idapp").asObject();
			qry.setIdApp(idApp.intValue());
			result.add(qry);
		}
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}
}
