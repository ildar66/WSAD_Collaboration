package com.hps.july.core;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.util.StatementHelper;
import com.hps.july.util.TimeCounter;
import com.hps.july.util.Utils;
import org.springframework.context.ApplicationContext;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Простой адаптер синхронизации таблиц БД.
 * Применим в простых случаях.
 * Производит обновление только тех записей, которые изменились
 * с момента последнего обновления
 *
 * @author Shafigullin Ildar
 */
public class DefaultCollaboration implements Collaboration {
	protected final static long PROGRESS_UPDATE_TIME = 1 * 60 * 1000;

	protected String targetTable;
	protected String sourceTable;

	protected Connection targetDbConnection;
	protected Connection sourceDbConnection;
	protected Connection logDbConnection;

	protected ColumnMap columnMap;

	protected DB targetDb;
	protected DB sourceDb;
	protected DB logDb;

	protected PreparedStatement preparedSelectSourceDb = null;
	protected PreparedStatement preparedSelectTargetDb = null;
	protected PreparedStatement preparedInsertTargetDb = null;
	protected PreparedStatement preparedUpdateTargetDb = null;
	protected PreparedStatement preparedDeleteTargetDb = null;

	protected Query qry;

	protected java.util.Date startQueryTime;
	protected int maxQueryRecords;
	protected String nameLastUpdateDateColumnInSource = null;
	private boolean updateTaskState = true;

	private ApplicationContext context = null;

	public DefaultCollaboration(DB targetDb, DB sourseDb, String targetTable, String sourceTable, ColumnMap columnMap) {
		targetDbConnection = null;
		sourceDbConnection = null;

		this.targetDb =  targetDb;
		this.sourceDb = sourseDb;
		this.targetTable = targetTable;
		this.sourceTable = sourceTable;
		this.columnMap = columnMap;

		logDb = targetDb;
	}

	public DefaultCollaboration(DB targetDb, DB sourseDb, String targetTable,
							   String sourceTable, String lastUpdateDate_nameColumnJOIN_DB, ColumnMap columnMap) {
		targetDbConnection = null;
		sourceDbConnection = null;

		this.targetDb = targetDb;
		this.sourceDb = sourseDb;
		this.targetTable = targetTable;
		this.sourceTable = sourceTable;
		this.nameLastUpdateDateColumnInSource = lastUpdateDate_nameColumnJOIN_DB;
		this.columnMap = columnMap;

		logDb = this.targetDb;
	}

	public DefaultCollaboration( DB targerDb, DB sourseDb, String targetTable, String sourceTable,
								String lastUpdateDate_nameColumnJOIN_DB, ColumnMap columnMap, DB logDb) {
		targetDbConnection = null;
		sourceDbConnection = null;

		this.targetDb = targerDb;
		this.sourceDb = sourseDb;
		this.targetTable = targetTable;
		this.sourceTable = sourceTable;
		this.nameLastUpdateDateColumnInSource = lastUpdateDate_nameColumnJOIN_DB;
		this.columnMap = columnMap;
		this.logDb = logDb;
	}

	public DefaultCollaboration(DB targetDb, DB logDb) {
		this.targetDb = targetDb;
		this.logDb = logDb;
	}

	public DefaultCollaboration( DB targerDb, DB sourseDb, String targetTable, String sourceTable,
								String lastUpdateDate_nameColumnJOIN_DB, ColumnMap columnMap, DB logDb, boolean updateTask) {
		targetDbConnection = null;
		sourceDbConnection = null;

		this.targetDb = targerDb;
		this.sourceDb = sourseDb;
		this.targetTable = targetTable;
		this.sourceTable = sourceTable;
		this.nameLastUpdateDateColumnInSource = lastUpdateDate_nameColumnJOIN_DB;
		this.columnMap = columnMap;
		this.logDb = logDb;
		this.updateTaskState = updateTask;
	}

	/**
	 * Найти и заполнить поля объекта из системы JOIN_DB
	 * @param rowDBObject
	 * @return
	 * @throws CollaborationException
	 */
	protected RowDBObject findObjectJoinDb(RowDBObject rowDBObject) throws CollaborationException {
		RowDBObject result = null;
		ResultSet rs = null;
		String query = StatementHelper.getSelectSourceDb(sourceTable, columnMap);

		try {
			if (preparedSelectSourceDb == null) {
				preparedSelectSourceDb = sourceDbConnection.prepareStatement(query);
			}

			if (StatementHelper.setParams(preparedSelectSourceDb, columnMap.keysMap.keySet(), rowDBObject.getKeysColumns(), 1)) {
				rs = preparedSelectSourceDb.executeQuery();
				if (rs.next()) {
					if (!StatementHelper.populateColumns(rowDBObject, columnMap.columnMap.keySet(), rs)) {
						return null;
					}

					return rowDBObject;
				} else {
					// JOIN_DB Object not found - issue warning
					QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_WARNING, "Объект не найден в системе JOIN_DB по первичному ключу: " + rowDBObject.dump());
				}
			}

			return result;
		} catch (SQLException e) {
			System.out.println("Error executing SQL=" + query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			//e.printStackTrace(System.out);
			throw new CollaborationException("findObjectJoinDb");
		} finally{
			Utils.closeResultSet(rs);
		}
	}

	/**
	 * Найти и заполнить поля объект из системы JOIN_DB
	 */
	protected RowDBObject findObjectTargetDb(RowDBObject argObj) throws CollaborationException {
		RowDBObject result = null;
		ResultSet rs = null;
		String query = StatementHelper.getSelectTargetDb(targetTable, columnMap, argObj);

		try {
			if (preparedSelectTargetDb == null) {
				preparedSelectTargetDb = targetDbConnection.prepareStatement(query);
			}

			if (StatementHelper.setParams(preparedSelectTargetDb, columnMap.keysMap.keySet(), argObj.getKeysColumns(), 1)) {
				if (StatementHelper.setParams(preparedSelectTargetDb, columnMap.predefinedKeyColumns.keySet(), columnMap.predefinedKeyColumns, columnMap.keysMap.keySet().size() + 1))
					rs = preparedSelectTargetDb.executeQuery();
				if (rs.next()) {
					result = argObj;
					if (!StatementHelper.populateColumns(result, columnMap.columnMap.values(), rs))
						result = null;
				} else {
					// JOIN_DB Object not found - issue warning
					QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_WARNING, "Объект не найден в системе TARGET_DB по первичному ключу: " + argObj.dump());
				}
			}
		} catch (SQLException e) {
			System.out.println("Error executing SQL=" + query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			throw new CollaborationException("findObjectTargetDb");
		} finally{
			Utils.closeResultSet(rs);
		}

		return result;
	}

	/**
	 * Проверка существования объекта в системе TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	protected boolean isExistObjectInTargetDb(RowDBObject argObj) throws CollaborationException {
		boolean result = false;
		ResultSet rs = null;
		String query = StatementHelper.getSelectTargetDb(targetTable, columnMap, argObj);
		try {
			if (preparedSelectTargetDb == null) {
				preparedSelectTargetDb = targetDbConnection.prepareStatement(query);
			}

			if (StatementHelper.setParams(preparedSelectTargetDb, columnMap.keysMap.keySet(), argObj.getKeysColumns(), 1)) {
				if (StatementHelper.setParams(preparedSelectTargetDb, columnMap.predefinedKeyColumns.keySet(), columnMap.predefinedKeyColumns, columnMap.keysMap.keySet().size() + 1))
					rs = preparedSelectTargetDb.executeQuery();
				if (rs.next())
					result = true;
			}
		} catch (SQLException e) {
			System.out.println("Error executing SQL=" +  query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			//e.printStackTrace(System.out);
			throw new CollaborationException("isExistObjectInTargetDb");
		} finally{
			Utils.closeResultSet(rs);
		}

		return result;
	}


	/**
	 * Проверка существования объекта в системе JOIN_DB
	 *
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	private boolean isExistObjectSourceDb(RowDBObject argObj) throws CollaborationException {
		ResultSet rs = null;
		String query = StatementHelper.getSelectSourceDb(sourceTable, columnMap);

		try {
			if (preparedSelectSourceDb == null) {
				preparedSelectSourceDb = sourceDbConnection.prepareStatement(query);
			}

			if (StatementHelper.setParams(preparedSelectSourceDb, columnMap.keysMap.values(), argObj.getKeysColumns(), 1)) {
					rs = preparedSelectSourceDb.executeQuery();
					if (rs.next())
						return true;
			}

			return false;
		} catch (SQLException e) {
			System.out.println("Error executing SQL=" + query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			//e.printStackTrace(System.out);
			throw new CollaborationException("isExistObjectSourceDb");
		} finally {
			Utils.closeResultSet(rs);
		}
	}

	/**
	 * Обновление записи в системе TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	protected boolean performUpdateTargetDb(RowDBObject argObj) throws CollaborationException {
		String query = StatementHelper.getUpdateTargetDb(targetTable, columnMap, argObj);
		try {
			if (preparedUpdateTargetDb == null) {
				preparedUpdateTargetDb = targetDbConnection.prepareStatement(query);
			}

			StatementHelper.setParams(preparedUpdateTargetDb, columnMap.columnMap.keySet(), argObj.getColumns(), 1);
			StatementHelper.setParams(preparedUpdateTargetDb, columnMap.predefinedColumns.keySet(), columnMap.predefinedColumns, columnMap.columnMap.keySet().size() + 1);
			StatementHelper.setParams(preparedUpdateTargetDb, columnMap.keysMap.keySet(), argObj.getKeysColumns(), columnMap.columnMap.keySet().size() + columnMap.predefinedColumns.keySet().size() + 1);
			StatementHelper.setParams(preparedUpdateTargetDb, columnMap.predefinedKeyColumns.keySet(), columnMap.predefinedKeyColumns, columnMap.columnMap.keySet().size() + columnMap.predefinedColumns.keySet().size() + columnMap.keysMap.keySet().size() + 1);

			preparedUpdateTargetDb.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error executing UPDATE SQL = " + query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			throw new CollaborationException("performUpdateTargetDb");
		}
	}

	/**
	 * Добавление объекта в систему TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	protected boolean insertInTarget(RowDBObject argObj) throws CollaborationException {
		String query = StatementHelper.getInsertTargetDb(targetTable, columnMap);
		try {
			if (preparedInsertTargetDb == null) {
				preparedInsertTargetDb = targetDbConnection.prepareStatement(query);
			}

			StatementHelper.setParams(preparedInsertTargetDb, columnMap.keysMap.keySet(), argObj.getKeysColumns(), 1);
			StatementHelper.setParams(preparedInsertTargetDb, columnMap.columnMap.keySet(), argObj.getColumns(), columnMap.keysMap.keySet().size() + 1);
			StatementHelper.setParams(preparedInsertTargetDb, columnMap.predefinedColumns.keySet(), columnMap.predefinedColumns, columnMap.keysMap.keySet().size() + columnMap.columnMap.keySet().size() + 1);
			StatementHelper.setParams(preparedInsertTargetDb, columnMap.predefinedKeyColumns.keySet(), columnMap.predefinedKeyColumns, columnMap.keysMap.keySet().size() + columnMap.columnMap.keySet().size() + columnMap.predefinedColumns.keySet().size() + 1);

			preparedInsertTargetDb.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Error executing INSERT SQL = " + query);
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			throw new CollaborationException("insertInTarget");
		}
	}

	/**
	 * Добавление/обновление объекта в системе TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	protected boolean updateTargetDb(RowDBObject argObj) throws CollaborationException {
		boolean result = true;
		if (isExistObjectInTargetDb(argObj)) {
			if (!performUpdateTargetDb(argObj)) {
				result = false;
			}
		} else {
			//dirty hack for SitesForTnAdapter... it don't touch other adapter
			if (!processRow(argObj)) {
				result = false;
			};

			if (!insertInTarget(argObj)) {
				//System.out.println("insertInTarget = false");//temp
				result = false;
			}
		}

		if (result) {
			if (!makeRelationInTargetDb(argObj)) {
				//System.out.println("makeRelationInTargetDb = false");//temp
				result = false;
			}
		}
		return result;
	}

	/**
	 * Установка связи с существующим объектом TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	protected boolean makeRelationInTargetDb(RowDBObject argObj) throws CollaborationException {
		return true;
	}

	/**
	 * Удаление объекта в системе TARGET_DB
	 * @param argObj
	 * @return
	 * @throws CollaborationException
	 */
	private boolean deleteInTarget(RowDBObject argObj) throws CollaborationException {
		PreparedStatement st = null;
		String query = StatementHelper.getUpdateTargetDb(targetTable, columnMap);
		try {
			st = targetDbConnection.prepareStatement(query);
			StatementHelper.setParams(st, columnMap.keysMap.values(), argObj.getKeysColumns(), 1);
			st.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("Error executing UPDATE SQL = " + query);
			//e.printStackTrace(System.out);
			throw new CollaborationException("deleteInTarget");
		} finally{
			Utils.closeStatement(st);
		}
	}

	/**
	 * Поиск объекта в системе JOIN_DB
	 * и передача в систему TARGET_DB
	 */
	protected boolean findInSourceUpdateTarget(RowDBObject argObj) throws CollaborationException {
		try {
			RowDBObject objJOIN_DB = findObjectJoinDb(argObj);
			if (objJOIN_DB != null) {
				return updateTargetDb(objJOIN_DB);
			} else {
				// Object not found in JOIN_DB due to changes in dataset
				//   treat this fact as warning only
				return true;
			}
		} catch (CollaborationException e) {
			// Exception during transfer
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Поиск объекта в системе JOIN_DB
	 * и удаление в системе TARGET_DB в случае отсутствия в JOIN_DB
	 */
	private boolean checkSourceDeleteInTarget(RowDBObject argObj) throws CollaborationException {
		//System.out.println("Cheching existance of: " + argObj.dump());
		if (!isExistObjectSourceDb(argObj)) {
			//System.out.println("No isExistObjectSourceDb ="+ argObj);//temp
			//System.out.println("Deleting");
			return deleteInTarget(argObj);
		} else {
			//System.out.println("Yes isExistObjectSourceDb ="+ argObj);//temp
			//System.out.println("Skipping");
			return true;
		}
	}

	/**
	 * Заполнение ключевых полей объекта из CDBCRowObject
	 * @param resObj
	 * @param keys
	 * @param rs
	 */
	protected void populateKeysCDBC(RowDBObject resObj, Collection keys, CDBCRowObject rs) {
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String keyName = (String) it.next();
			Object value = rs.getColumn(keyName).asObject();
			//System.out.println("Populating: key=" + keyName + ", value=" + value.toString());
			resObj.addKeyColumn(keyName, value);
		}
	}


	/**
	 * Сформировать параметры SQL для определения изменившихся записей
	 * @return
	 */
	private Object[] getChangesSQLParams() {
		if (nameLastUpdateDateColumnInSource == null)
			return null;
		java.sql.Timestamp lastdate = getLastUpdateDate();
		if (lastdate != null)
			return new Object[] { lastdate };
		else
			return new Object[] {};
	}

	/**
	 * Получить время последнего обновления таблицы
	 * @return
	 */
	protected Timestamp getLastUpdateDate() {
		Timestamp result = null;
		CDBCResultSet datesSet = new CDBCResultSet();
		datesSet.executeQuery(logDbConnection, "SELECT * FROM join_db_nriupdate WHERE tablename = ? and idapp = ?", new Object[] {targetTable, new Integer(qry.getIdApp())}, 0);
		ListIterator it = datesSet.listIterator();
		if (it.hasNext()) {
			Object resDate = ((CDBCRowObject) it.next()).getColumn("updatetime").asObject();
			result = (java.sql.Timestamp) resDate;
		}
		return result;
	}

	/**
	 * Сформировать дату последнего обновления
	 * @return
	 */
	private Timestamp makeLastUpdateDate() {
		final long ONE_DAY_MSECS = 86400000;
		java.util.Date updDate = new java.util.Date();
		java.sql.Timestamp resDate = new java.sql.Timestamp(updDate.getTime() - ONE_DAY_MSECS);
		return resDate;
	}

	/**
	 * Обновление даты последнего обновления данных в системе TARGET_DB
	 */
	private void updateLastTransferDateInTarget() {
		String buf1 = "INSERT INTO join_db_nriupdate (tablename, idapp, updatetime) VALUES (?,?,?)";
		String buf2 = "UPDATE join_db_nriupdate SET updatetime = ? WHERE tablename = ? AND idapp = ?";

		CDBCResultSet crs = new CDBCResultSet();
		if (!crs.executeUpdate(logDbConnection, buf1, new Object[] {targetTable, new Integer(qry.getIdApp()), makeLastUpdateDate()})) {
			crs.executeUpdate(logDbConnection, buf2, new Object[] { makeLastUpdateDate(), targetTable, new Integer(qry.getIdApp())});
		}
	}

	/**
	 * Отметка успешного выполнения переноса
	 */
	protected void markSuccessTransfer() {
		if (updateTaskState) {
			updateLastTransferDateInTarget();
			finishSuccess();
			System.out.println("TRANSFER SUCCESS for Table " + targetTable);
		}
	}

	/**
	 * Отметка ошибки переноса данных
	 */
	protected void markErrorTransfer() {
		if (updateTaskState) {
			QueryProcessing.finishError(logDbConnection, qry);
			System.out.println("TRANSFER ERROR for Table " + targetTable);
		}
	}

	/**
	 * Отметка успешного выполнения удаления
	 */
	private void markSuccessDelete() {
		if (updateTaskState) {
			finishSuccess();
			System.out.println("DELETE SUCCESS for Table " + targetTable);
		}
	}

	/**
	 * Отметка ошибки переноса данных
	 */
	private void markErrorDelete() {
		if (updateTaskState) {
			QueryProcessing.finishError(logDbConnection, qry);
			System.out.println("DELETE ERROR for Table " + targetTable);
		}
	}

	/**
	 * Отметка времени выполнения запроса
	 * @param argMaxRecords
	 */
	private void startProgressIndicator(int argMaxRecords) {
		startQueryTime = new java.util.Date();
		maxQueryRecords = argMaxRecords;
	}

	/**
	 * Отметка времени выполнения запроса
	 * @param argCurRecord
	 */
	private void markProgressIndicator(int argCurRecord) {
		java.util.Date currQryTime;
		currQryTime = new java.util.Date();
		if ((currQryTime.getTime() - startQueryTime.getTime()) > PROGRESS_UPDATE_TIME) {
			int percent = 100 * argCurRecord / maxQueryRecords;
			System.out.println("PROGRESS for " + targetTable + ": " + percent + " %, " + argCurRecord + " of " + maxQueryRecords + " records transferred");
			startQueryTime = currQryTime;
		}
	}

	/**
	 * Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB - содержательная часть
	 * @throws CollaborationException
	 */
	protected void doFindChangesAndUpdate() throws CollaborationException {
		boolean result;
		int curRow = 0;

		if (sourceDbConnection != null && targetDbConnection != null) {
			startProcessing();
			result = doBeforeTask(qry);
			if (result) {
				CDBCResultSet changesSet = new CDBCResultSet();
				String changesSQL = getSqlChangeInSource();
				Object[] changesSQLParams = getChangesSQLParams();

				changesSet.executeQuery(sourceDbConnection, changesSQL, changesSQLParams, 0);

				ListIterator it = changesSet.listIterator();
				startProgressIndicator(changesSet.getRowsCount());
				while (it.hasNext()) {
					CDBCRowObject ro = (CDBCRowObject) it.next();
					RowDBObject objJOIN_DB = new RowDBObject();

					populateKeysCDBC(objJOIN_DB, columnMap.keysMap.keySet(), ro);

					if (!findInSourceUpdateTarget(objJOIN_DB)) {
						String msg = "Ошибка переноса данных: " + sourceTable + ", Ключевые поля: " + objJOIN_DB.dump();
						System.out.println(msg);
						QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_ERROR, msg);
						result = false;
					}

					curRow++;
					markProgressIndicator(curRow);
				}
			}
		} else {
			result = false;
		}

		if (result) {
			compareSourceAndTargetCounts();
			doPostTask(qry);
			markSuccessTransfer();
		} else {
			markErrorTransfer();
		}
	}

	private void startProcessing() {
		if (updateTaskState) {
			QueryProcessing.startProcessing(logDbConnection, qry);
		}

	}

	protected String getSqlChangeInSource() {
		String changesSQL = StatementHelper.getSqlChangeInSource(sourceTable, getLastUpdateDate(), nameLastUpdateDateColumnInSource);
		return changesSQL;
	}

	public boolean processRow(RowDBObject objJOIN_db) {
		return true;
	}

	/**
	 * Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB
	 * @param argQry
	 * @throws CollaborationException
	 */
	public void findChangesAndUpdate(Query argQry) throws CollaborationException {
		qry = argQry;
		TimeCounter tc = new TimeCounter(targetTable);
		try {
			sourceDbConnection = DB.getConnection(sourceDb);
			targetDbConnection = DB.getConnection(targetDb);
			logDbConnection = DB.getConnection(logDb);
			doFindChangesAndUpdate();
		} finally {
			Utils.closeStatement(preparedSelectSourceDb);
			Utils.closeStatement(preparedSelectTargetDb);
			Utils.closeStatement(preparedInsertTargetDb);
			Utils.closeStatement(preparedUpdateTargetDb);

			preparedSelectSourceDb = null;
			preparedSelectTargetDb = null;
			preparedInsertTargetDb = null;
			preparedUpdateTargetDb = null;

			Utils.closeConnection(targetDbConnection);
			Utils.closeConnection(sourceDbConnection);
			Utils.closeConnection(logDbConnection);

			targetDbConnection = null;
			sourceDbConnection = null;
			logDbConnection = null;
		}
		tc.finish("Transfer");
	}

	/**
	 * Сформировать SQL для определения всех записей в TARGET_DB
	 * @return
	 */
	private String getSelectAllInTarget() {
		StringBuffer result = new StringBuffer();
		result.append("SELECT * FROM ");
		result.append(targetTable);
		return result.toString();
	}

	/**
	 * Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB - содержательная часть
	 * @throws CollaborationException
	 */
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		boolean result = true;
		int curRow = 0;
		if ((sourceDbConnection != null) && (targetDbConnection != null)) {
			startProcessing();
			CDBCResultSet allSet = new CDBCResultSet();
			String allSQL = getSelectAllInTarget();
			allSet.executeQuery(targetDbConnection, allSQL, null, 0);
			ListIterator it = allSet.listIterator();
			startProgressIndicator(allSet.getRowsCount());
			while (it.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject) it.next();
				RowDBObject objTARGET_DB = new RowDBObject();
				populateKeysCDBC(objTARGET_DB, columnMap.keysMap.values(), ro);
				if (!checkSourceDeleteInTarget(objTARGET_DB)) {
					String msg = "Ошибка удаления данных: " + targetTable + ", Ключевые поля: " + objTARGET_DB.dump();
					System.out.println(msg);
					QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_ERROR, msg);
					result = false;
				}
				curRow++;
				markProgressIndicator(curRow);
			}
		} else {
			result = false;
		}
		compareSourceAndTargetCounts();
		if (result)
			markSuccessDelete();
		else
			markErrorDelete();
	}

	/**
	 * Поиск записей, удаленных в JOIN_DB и их удаление в TARGET_DB
	 * @param argQry
	 * @throws CollaborationException
	 */
	public void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException {
		qry = argQry;
		TimeCounter tc = new TimeCounter(targetTable);
		try {
			sourceDbConnection = DB.getConnection(sourceDb);
			targetDbConnection = DB.getConnection(targetDb);
			logDbConnection = DB.getConnection(logDb);
			doFindDeletedInSourceDeleteInTarget();
		} finally {
			Utils.closeStatement(preparedSelectSourceDb);
			Utils.closeStatement(preparedSelectTargetDb);
			Utils.closeStatement(preparedDeleteTargetDb);
			preparedSelectSourceDb = null;
			preparedSelectTargetDb = null;
			preparedDeleteTargetDb = null;

			Utils.closeConnection(targetDbConnection);
			Utils.closeConnection(sourceDbConnection);
			Utils.closeConnection(logDbConnection);

			targetDbConnection = null;
			sourceDbConnection = null;
			logDbConnection = null;
		}
		tc.finish("Delete");
	}

	/**
	 *Проверка совпадения количества записей в системах JOIN_DB и TARGET_DB
	 */
	protected void compareSourceAndTargetCounts() {
		if ((sourceDbConnection != null) && (targetDbConnection != null)) {
			CDBCResultSet countSet1 = new CDBCResultSet();
			CDBCResultSet countSet2 = new CDBCResultSet();

			countSet1.executeQuery(sourceDbConnection, "SELECT count(*) AS cnt FROM " + sourceTable, new Object[] {}, 0);
			countSet2.executeQuery(targetDbConnection, "SELECT count(*) AS cnt FROM " + targetTable + " WHERE recordstatus='A'", new Object[] {}, 0);

			ListIterator it1 = countSet1.listIterator();
			ListIterator it2 = countSet2.listIterator();
			if (it1.hasNext() && it2.hasNext()) {
				CDBCRowObject ro1 = (CDBCRowObject) it1.next();
				CDBCRowObject ro2 = (CDBCRowObject) it2.next();
				if (!ro1.getColumn("cnt").asObject().equals(ro2.getColumn("cnt").asObject())) {
					QueryProcessing.addLogMessage( logDbConnection, qry, QueryProcessing.MSG_WARNING,
												   "Количество записей в системе JOIN_DB: "
												 + sourceTable + " = " + ro1.getColumn("cnt").asString()
												 + ", в системе TARGET_DB: "
												 + targetTable + " = " + ro2.getColumn("cnt").asString());
				}
			}
		}
	}

	public void doTask(Query argQry) {
		qry = argQry;
		boolean result = false;
		TimeCounter tc = new TimeCounter("doTask process");
		try {
			targetDbConnection = DB.getConnection(targetDb);
			logDbConnection = DB.getConnection(logDb);
			startProcessing();
			if (targetDbConnection != null) {
				result = doBeforeTask(qry);
				System.out.println("SImpleCollaborattion: after doBeforeTask["+result+"]");
			}
			if (result) {
				updateLastTransferDateInTarget();
				finishSuccess();
				doPostTask(qry);
				System.out.println("SImpleCollaborattion: after finishSuccess["+result+"]");
			} else {
				QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_ERROR, "ERROR doTask!!!");
				finishError();
				System.out.println("SImpleCollaborattion: after finishError["+result+"]");
			}
		} catch (Exception e) {
			String msg = "ERROR doTask!!!";
			QueryProcessing.addLogMessage(logDbConnection, qry, QueryProcessing.MSG_ERROR, msg);
			finishError();
			System.out.println(msg);
			e.printStackTrace();
		} finally {
			Utils.closeConnection(targetDbConnection);
			Utils.closeConnection(logDbConnection);

			targetDbConnection = null;
			logDbConnection = null;
		}
		tc.finish("");
	}

	private void finishError() {
		if (updateTaskState) {
			QueryProcessing.finishError(logDbConnection, qry);
		}
	}

	private void finishSuccess() {
		if (updateTaskState) {
			QueryProcessing.finishSuccess(logDbConnection, qry);
		}
	}

	protected String getSourceTable() {
		return sourceTable;
	}

	protected Connection getTargetDbConnection() {
		return targetDbConnection;
	}

	protected Query getQuery() {
		return qry;
	}

	protected PreparedStatement getPreparedSelectSourceDb() {
		return preparedSelectSourceDb;
	}

	protected void setPreparedSelectSourceDb(PreparedStatement preparedSelectSourceDb) {
		this.preparedSelectSourceDb = preparedSelectSourceDb;
	}

	protected Connection getSourceDbConnection() {
		return sourceDbConnection;
	}

	protected ColumnMap getColumnMap() {
		return columnMap;
	}

	protected boolean doPostTask(Query qry) {
		return true;
	}

	protected boolean doBeforeTask(Query qry) {
		return true;
	}

	protected PreparedStatement getPreparedSelectTargetDb() {
		return preparedSelectTargetDb;
	}

	protected void setPreparedSelectTargetDb(PreparedStatement preparedSelectTargetDb) {
		this.preparedSelectTargetDb = preparedSelectTargetDb;
	}

	protected String getTargetTable() {
		return targetTable;
	}

	protected PreparedStatement getPreparedInsertTargetDb() {
		return preparedInsertTargetDb;
	}

	protected void setPreparedInsertTargetDb(PreparedStatement preparedInsertTargetDb) {
		this.preparedInsertTargetDb = preparedInsertTargetDb;
	}

	protected PreparedStatement getPreparedUpdateTargetDb() {
		return preparedUpdateTargetDb;
	}

	protected void setPreparedUpdateTargetDb(PreparedStatement preparedUpdateTargetDb) {
		this.preparedUpdateTargetDb = preparedUpdateTargetDb;
	}

	protected Connection getLogDbConnection() {
		return logDbConnection;
	}


	protected void setQuery(Query query) {
		qry = query;
	}

	protected DB getSourceDb() {
		return sourceDb;
	}

	protected DB getLogDb() {
		return logDb;
	}

	protected DB getTargetDb() {
		return targetDb;
	}

	protected void setSourceDbConnection(Connection connection) {
		sourceDbConnection = connection;
	}

	protected void setLogDbConnection(Connection connection) {
		logDbConnection = connection;
	}

	protected void setTargetDbConnection(Connection connection) {
		targetDbConnection = connection;
	}

	protected PreparedStatement getPreparedDeleteTargetDb() {
		return preparedDeleteTargetDb;
	}

	protected void setPreparedDeleteTargetDb(PreparedStatement statement) {
		preparedDeleteTargetDb = statement;
	}


}
