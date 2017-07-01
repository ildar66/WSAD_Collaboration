package old.com.hps.july.sync;

import java.sql.*;
import java.util.*;
import com.hps.july.cdbc.lib.*;

/**
 * @author Dmitry Dneprov
 * Запрос на синхронизацию данных.
 */
public class Query {
	private SyncConnection con;
	private String reqType;
	private int queryId;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private int idApp;

	public static final String MSG_INFO = "I";
	public static final String MSG_ERROR = "E";
	public static final String MSG_WARNING = "W";

	/// Конструктор
	public Query(SyncConnection argCon) {
		con = argCon;
	}

	/*
	protected void finalize() throws Throwable {
	    super.finalize();
	}
	*/

	public boolean isQueryType(String argQueryType) {
		return getReqType().indexOf(argQueryType) > 0;
	}

	public boolean isDelQuery() {
		return getReqType().indexOf("DEL") == 0;
	}

	public void setReqType(String argReqType) {
		reqType = argReqType;
	}

	public String getReqType() {
		return reqType;
	}

	public void setQueryId(int argQueryId) {
		queryId = argQueryId;
	}

	public int getQueryId() {
		return queryId;
	}

	/// Дата начала интервала обработки записей
	public void setStartDate(java.sql.Date argStartDate) {
		startDate = argStartDate;
	}

	/// Дата начала интервала обработки записей
	public java.sql.Date getStartDate() {
		return startDate;
	}

	/// Дата окончания интервала обработки записей
	public void setEndDate(java.sql.Date argEndDate) {
		endDate = argEndDate;
	}

	/// Дата окончания интервала обработки записей
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/// Отметить начало обработки запроса
	public boolean startProcessing() {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql =
			"UPDATE JOIN_DB_query SET reqstate='O', starttime=? WHERE idquery=?";
		Connection acon = con.getConnection();
		if (!crs
			.executeUpdate(
				acon,
				sql,
				new Object[] {
					new java.sql.Date(new java.util.Date().getTime()),
					new Integer(getQueryId())}))
			result = false;
		if (!addLogMessage(MSG_INFO, "Начало обработки запроса"))
			result = false;
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Пометить запрос как обработанный успешно
	public boolean finishSuccess() {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql =
			"UPDATE JOIN_DB_query SET reqstate='Y', finishtime=? WHERE idquery=?";
		Connection acon = con.getConnection();
		if (!crs
			.executeUpdate(
				acon,
				sql,
				new Object[] {
					new java.sql.Date(new java.util.Date().getTime()),
					new Integer(getQueryId())}))
			result = false;
		if (!addLogMessage(MSG_INFO, "Успешное окончание обработки запроса"))
			result = false;
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Пометить запрос как обработанный с ошибкой
	public boolean finishError() {
		boolean result = true;
		CDBCResultSet crs = new CDBCResultSet();
		String sql =
			"UPDATE JOIN_DB_query SET reqstate='E', finishtime=? WHERE idquery=?";
		Connection acon = con.getConnection();
		if (!crs
			.executeUpdate(
				acon,
				sql,
				new Object[] {
					new java.sql.Date(new java.util.Date().getTime()),
					new Integer(getQueryId())}))
			result = false;
		if (!addLogMessage(MSG_INFO, "Ошибочное окончание обработки запроса"))
			result = false;
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Добавить сообщение в журнал
	public boolean addLogMessage(
		String argMessageType,
		String argMessageText) {
		CDBCResultSet crs = new CDBCResultSet();
		String sql =
			"INSERT INTO JOIN_DB_querylog(idquery, typemsg, txtmsg) VALUES (?, ?, ?)";
		Connection acon = con.getConnection();
		boolean result =
			crs.executeUpdate(
				acon,
				sql,
				new Object[] {
					new Integer(getQueryId()),
					argMessageType,
					argMessageText });
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * Returns the idApp.
	 * @return int
	 */
	public int getIdApp() {
		return idApp;
	}

	/**
	 * Sets the idApp.
	 * @param idApp The idApp to set
	 */
	public void setIdApp(int idApp) {
		this.idApp = idApp;
	}

}
