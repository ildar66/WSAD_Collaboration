package old.com.hps.july.sync;

import java.sql.*;
import java.util.*;
import com.hps.july.cdbc.lib.*;
import com.hps.july.util.TimeCounter;

/**
 * @author Dmitry Dneprov
 * Простой адаптер обновления таблиц.
 * Применим в простых случаях.
 * Производит обновление только тех записей, которые изменились
 * с момента последнего обновления
 */
public class SimpleAdaptor implements Adaptor {
    protected String tableNameNRI;
    protected String tableNameJOIN_DB;
    protected Connection conNRI;
    protected Connection conJOIN_DB;
    protected ColumnMap colMap;
    protected SyncConnection sconNRI;
    protected SyncConnection sconJOIN_DB;
    protected PreparedStatement preparedSelectJOIN_DB = null;
    protected PreparedStatement preparedSelectNRI = null;
    protected PreparedStatement preparedInsertNRI = null;
    protected PreparedStatement preparedUpdateNRI = null;
    protected PreparedStatement preparedDeleteNRI = null;
    protected Query qry;
    protected java.util.Date startQryTime;
    protected int maxQryRecords;
    protected final static long PROGRESS_UPDATE_TIME = 1 * 60 * 1000;

    protected SimpleAdaptor(SyncConnection argConNRI, SyncConnection argConJOIN_DB, String argTableNameNRI, String argTableNameJOIN_DB) {
        conNRI = null;
        conJOIN_DB = null;
        sconNRI = argConNRI;
        sconJOIN_DB = argConJOIN_DB;
        tableNameNRI = argTableNameNRI;
        tableNameJOIN_DB = argTableNameJOIN_DB;
    }

    public void setColumnMap(ColumnMap argMap) {
        colMap = argMap;
    }

    public ColumnMap getColumnMap() {
        return colMap;
    }

    /// Генерация предложения ПАРАМЕТР=?
    protected String generateClause(TreeMap params, String separator) {
        StringBuffer buf = new StringBuffer();
        Iterator it = params.keySet().iterator();
        boolean isFirst = true;
        while (it.hasNext()) {
            String key = (String)it.next();
            if (!isFirst) {
                buf.append(separator);
            }
            isFirst = false;
            buf.append(key);
            buf.append(" = ? ");
        }
        return buf.toString();
    }

    /// Генерация WHERE-предложения по ключевым колонкам в системе NRI
    protected String generateWhereClauseNRI(BusinessObject argObj) {
        return generateClause(argObj.keysNRI, " AND ");
    }

    /// Генерация WHERE-предложения по ключевым колонкам в системе JOIN_DB
    protected String generateWhereClauseJOIN_DB(BusinessObject argObj) {
        return generateClause(argObj.keysJOIN_DB, " AND ");
    }

    /// Генерация SET-предложения для основных колонок в системе NRI
    private String generateSetClauseNRI(BusinessObject argObj) {
        return generateClause(argObj.valuesNRI, " , ");
    }

    /// Генерация SET-предложения для предопределенных колонок в системе NRI
    private String generatePredefinedSetClauseNRI(BusinessObject argObj) {
        return generateClause(argObj.colMap.predefinedColumnsNRI, " , ");
    }

    /// Генерирует наименование или параметр колонки
    protected String generateColumnNameParam(TreeMap params, boolean argFirst, boolean isName) {
        StringBuffer buf = new StringBuffer();
        Iterator it = params.keySet().iterator();
        boolean isFirst = argFirst;
        while (it.hasNext()) {
            String key = (String)it.next();
            if (!isFirst) {
                buf.append(" , ");
            }
            isFirst = false;
            if (isName)
                buf.append(key);
            else
                buf.append("?");
        }
        return buf.toString();
    }

    /// Генерация имен колонок для ключевых колонок в системе NRI
    protected String generateKeyColumnNamesNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.keysNRI, true, true);
    }

    /// Генерация имен колонок для основных колонок в системе NRI
    private String generateValueColumnNamesNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.valuesNRI, false, true);
    }

    /// Генерация имен колонок для предопределенных колонок в системе NRI
    private String generatePredefinedColumnNamesNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.colMap.predefinedColumnsNRI, false, true);
    }

    /// Генерация параметров для ключевых колонок в системе NRI
    protected String generateKeyColumnParamsNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.keysNRI, true, false);
    }

    /// Генерация параметров для основных колонок в системе NRI
    private String generateValueColumnParamsNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.valuesNRI, false, false);
    }

    /// Генерация параметров для предопределенных колонок в системе NRI
    private String generatePredefinedColumnParamsNRI(BusinessObject argObj) {
        return generateColumnNameParam(argObj.colMap.predefinedColumnsNRI, false, false);
    }

    /// Обобщенная процедура установки SQL-параметров
    protected boolean setParams(PreparedStatement st, TreeMap params, int argStartParam) {
        boolean result = true;
        Iterator it = params.keySet().iterator();
        int i = argStartParam;
        while (it.hasNext()) {
            String key = (String)it.next();
            Object value = params.get(key);
            try {
                st.setObject(i, value);
            } catch (SQLException e) {
                result = false;
                //qry.addLogMessage(qry.MSG_ERROR, "Невозможно установить SQL параметр #" + i + ", key=" + key + ", value=" + value);
                System.out.println("Cannot set SQL parameter #" + i + ", key=" + key + ", value=" + value);
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
            }
            i++;
        }
        return result;
    }

    /// Установка SQL-параметров (?) для ключевых колонок в системе NRI
    protected boolean setKeysParamsNRI(PreparedStatement st, BusinessObject argObj, int argStartParam) {
        return setParams(st, argObj.keysNRI, argStartParam);
    }

    /// Установка SQL-параметров (?) для ключевых колонок в системе JOIN_DB
    protected boolean setKeysParamsJOIN_DB(PreparedStatement st, BusinessObject argObj, int argStartParam) {
        return setParams(st, argObj.keysJOIN_DB, argStartParam);
    }

    /// Установка SQL-параметров (?) для основных колонок (данные) в системе NRI
    protected boolean setValuesParamsNRI(PreparedStatement st, BusinessObject argObj, int argStartParam) {
        return setParams(st, argObj.valuesNRI, argStartParam);
    }

    /// Установка SQL-параметров (?) для предопределенных колонок в системе NRI
    protected boolean setPredefinedParamsNRI(PreparedStatement st, BusinessObject argObj, int argStartParam) {
        return setParams(st, argObj.colMap.predefinedColumnsNRI, argStartParam);
    }

    /// Заполнение ключевых полей бизнес-объекта из строки ResultSet
    protected boolean populateKeys(BusinessObject resObj, ResultSet rs) {
        boolean result = true;
        Iterator it = null;
        if (resObj instanceof NRIBusinessObject) {
            it = resObj.colMap.keysMapNRI2JOIN_DB.keySet().iterator();
        } else {
            it = resObj.colMap.keysMapJOIN_DB2NRI.keySet().iterator();
        }
        while (it.hasNext()) {
            String keyName = (String)it.next();
            try {
                resObj.addKeyColumn(keyName, rs.getObject(keyName));
            } catch (SQLException e) {
                result = false;
                System.out.println("ERROR: Cannot get column: " + keyName);
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
                resObj.addKeyColumn(keyName, null);
            }
        }
        return result;
    }

    /// Заполнение полей бизнес-объекта из строки ResultSet
    protected boolean populateValues(BusinessObject resObj, ResultSet rs) {
        boolean result = true;
        Iterator it = null;
        if (resObj instanceof NRIBusinessObject) {
            it = resObj.colMap.columnMapNRI2JOIN_DB.keySet().iterator();
        } else {
            it = resObj.colMap.columnMapJOIN_DB2NRI.keySet().iterator();
        }
        while (it.hasNext()) {
            String keyName = (String)it.next();
            try {
                resObj.addColumn(keyName, rs.getObject(keyName));
            } catch (SQLException e) {
                result = false;
                System.out.println("ERROR: Cannot get column: " + keyName);
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
                resObj.addColumn(keyName, null);
            }
        }
        return result;
    }

    /// Заполнение колонок Бизнем-Объекта из ResultSet
    protected boolean populateColumns(BusinessObject resObj, ResultSet rs) {
        boolean result = true;
        if (!populateKeys(resObj, rs))
            result = false;
        if (!populateValues(resObj, rs))
            result = false;
        return result;
    }

    /// Найти и заполнить поля объект из системы JOIN_DB
    protected BusinessObject findObjectJOIN_DB(BusinessObject argObj) {
        BusinessObject result = null;
        ResultSet rs = null;
        StringBuffer buf = new StringBuffer();
        if (preparedSelectJOIN_DB == null) {
            buf.append("SELECT * FROM ");
            buf.append(tableNameJOIN_DB);
            buf.append(" WHERE ");
            buf.append(generateWhereClauseJOIN_DB(argObj));
            try {
                preparedSelectJOIN_DB = conJOIN_DB.prepareStatement(buf.toString());
            } catch (SQLException e) {
                System.out.println("Cannot Prepare SQL=" + buf.toString());
                e.printStackTrace(System.out);
            }
        }
        if (setKeysParamsJOIN_DB(preparedSelectJOIN_DB, argObj, 1)) {
            try {
                if (preparedSelectJOIN_DB != null) {
                    rs = preparedSelectJOIN_DB.executeQuery();
                    if (rs != null) {
                        if (rs.next()) {
                            result = new JOIN_DBBusinessObject(argObj.colMap);
                            if (!populateColumns(result, rs))
                                result = null;
                        } else {
                            // JOIN_DB Object not found - issue warning
                            qry.addLogMessage(Query.MSG_WARNING, "Объект не найден в системе JOIN_DB по первичному ключу: " + argObj.dumpKeys(BusinessObject.KEYS_JOIN_DB));
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error executing SQL=" + buf.toString());
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        try {rs.close();} catch (Exception e) {}
        return result;
    }

    /// Проверка существования объекта в системе NRI
    protected boolean isExistObjectNRI(BusinessObject argObj) {
        boolean result = false;
        ResultSet rs = null;
        StringBuffer buf = new StringBuffer();
        if (preparedSelectNRI == null) {
            buf.append("SELECT * FROM ");
            buf.append(tableNameNRI);
            buf.append(" WHERE ");
            buf.append(generateWhereClauseNRI(argObj));
            try {
                preparedSelectNRI = conNRI.prepareStatement(buf.toString());
            } catch (SQLException e) {
                System.out.println("Cannot Prepare SQL=" + buf.toString());
                e.printStackTrace(System.out);
            }
        }
        if (setKeysParamsNRI(preparedSelectNRI, argObj, 1)) {
            try {
                rs = preparedSelectNRI.executeQuery();
                if (rs.next())
                    result = true;
            } catch (SQLException e) {
                System.out.println("Error executing SQL=" + buf.toString());
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        try {rs.close();} catch (Exception e) {}
        return result;
    }

    /// Проверка существования объекта в системе JOIN_DB
    protected boolean isExistObjectJOIN_DB(BusinessObject argObj) {
        boolean result = false;
        ResultSet rs = null;
        StringBuffer buf = new StringBuffer();
        if (preparedSelectJOIN_DB == null) {
            buf.append("SELECT * FROM ");
            buf.append(tableNameJOIN_DB);
            buf.append(" WHERE ");
            buf.append(generateWhereClauseJOIN_DB(argObj));
            try {
                preparedSelectJOIN_DB = conJOIN_DB.prepareStatement(buf.toString());
            } catch (SQLException e) {
                System.out.println("Cannot Prepare SQL=" + buf.toString());
                e.printStackTrace(System.out);
            }
        }
        if (setKeysParamsJOIN_DB(preparedSelectJOIN_DB, argObj, 1)) {
            try {
                rs = preparedSelectJOIN_DB.executeQuery();
                if (rs.next())
                    result = true;
            } catch (SQLException e) {
                System.out.println("Error executing SQL=" + buf.toString());
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        try {rs.close();} catch (Exception e) {}
        return result;
    }

    /// Генерация разделителя между основными и предопределенными колонками
    protected String generateSetPredefinedSeparatorNRI(BusinessObject argObj) {
        if ( (argObj.valuesNRI.size() > 0) && (argObj.colMap.predefinedColumnsNRI.size() > 0) )
            return " , ";
        return "";
    }

    /// Обновление записи в системе NRI
    protected boolean performUpdateNRI(BusinessObject argObj) {
        boolean result = false;
        StringBuffer buf = new StringBuffer();
        if (preparedUpdateNRI == null) {
            buf.append("UPDATE ");
            buf.append(tableNameNRI);
            buf.append(" SET ");
            buf.append(generateSetClauseNRI(argObj));
            buf.append(generateSetPredefinedSeparatorNRI(argObj));
            buf.append(generatePredefinedSetClauseNRI(argObj));
            buf.append(" WHERE ");
            buf.append(generateWhereClauseNRI(argObj));
            try {
                preparedUpdateNRI = conNRI.prepareStatement(buf.toString());
            } catch (SQLException e) {
                System.out.println("Cannot Prepare UPDATE SQL=" + buf.toString());
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        setValuesParamsNRI(preparedUpdateNRI, argObj, 1);
        setPredefinedParamsNRI(preparedUpdateNRI, argObj, argObj.valuesNRI.size()+1);
        setKeysParamsNRI(preparedUpdateNRI, argObj, argObj.valuesNRI.size() + argObj.colMap.predefinedColumnsNRI.size() + 1);
        try {
            preparedUpdateNRI.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Error executing UPDATE SQL = " + buf.toString());
            System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
            //e.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        return result;
    }

    /// Добавление объекта в систему NRI
    protected boolean performInsertNRI(BusinessObject argObj) {
        boolean result = false;
        StringBuffer buf = new StringBuffer();
        if (preparedInsertNRI == null) {
            buf.append("INSERT INTO ");
            buf.append(tableNameNRI);
            buf.append(" ( ");
            buf.append(generateKeyColumnNamesNRI(argObj));
            buf.append(generateValueColumnNamesNRI(argObj));
            buf.append(generatePredefinedColumnNamesNRI(argObj));
            buf.append(" ) VALUES ( ");
            buf.append(generateKeyColumnParamsNRI(argObj));
            buf.append(generateValueColumnParamsNRI(argObj));
            buf.append(generatePredefinedColumnParamsNRI(argObj));
            buf.append(" )");
            try {
                preparedInsertNRI = conNRI.prepareStatement(buf.toString());
            } catch (SQLException e) {
                System.out.println("Cannot Prepare INSERT SQL=" + buf.toString());
                e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        setKeysParamsNRI(preparedInsertNRI, argObj, 1);
        setValuesParamsNRI(preparedInsertNRI, argObj, argObj.keysNRI.size()+1);
        setPredefinedParamsNRI(preparedInsertNRI, argObj, argObj.keysNRI.size()+argObj.valuesNRI.size()+1);
        try {
            preparedInsertNRI.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Error executing INSERT SQL = " + buf.toString());
            System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
            //e.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        return result;
    }

    /// Добавление/обновление объекта в системе NRI
    public boolean updateObjectNRI(BusinessObject argObj) {
        boolean result = true;
        if (isExistObjectNRI(argObj)) {
            if (!performUpdateNRI(argObj))
                result = false;
        } else {
            if (!performInsertNRI(argObj))
                result = false;
        }
        if (result) {
            if (!makeRelationNRI(argObj))
                result = false;
        }
        return result;
    }

    /// Установка связи с существующим объектом NRI
    public boolean makeRelationNRI(BusinessObject argObj) {
        return true;
    }

    /// Удаление объекта в системе NRI
    public boolean deleteObjectNRI(BusinessObject argObj) {
        boolean result = false;
        PreparedStatement st = null;
        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE ");
        buf.append(tableNameNRI);
        buf.append(" SET recordstatus = 'D' ");
        buf.append(" WHERE ");
        buf.append(generateWhereClauseNRI(argObj));
        try {
            st = conNRI.prepareStatement(buf.toString());
        } catch (SQLException e) {
            System.out.println("Cannot Prepare UPDATE SQL=" + buf.toString());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        setKeysParamsNRI(st, argObj, 1);
        try {
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Error executing UPDATE SQL = " + buf.toString());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        try {st.close();} catch (Exception e) {}
        return result;
    }

    /**
     * Поиск объекта в системе JOIN_DB
     * и передача в систему NRI
     */
    protected boolean findJOIN_DBUpdateNRI(BusinessObject argObj) {
        boolean result = false;
        BusinessObject objJOIN_DB = findObjectJOIN_DB(argObj);
        if (objJOIN_DB != null)
            result = updateObjectNRI(objJOIN_DB);
        else {
            // Object not found in JOIN_DB due to changes in dataset
            //   treat this fact as warning only
            result = true;
        }
        return result;
    }

    /**
     * Поиск объекта в системе JOIN_DB
     * и удаление в системе NRI в случае отсутствия в JOIN_DB
     */
    protected boolean checkJOIN_DBDeleteNRI(BusinessObject argObj) {
        boolean result = false;
        if (!isExistObjectJOIN_DB(argObj))
            result = deleteObjectNRI(argObj);
        else {
            result = true;
        }
        return result;
    }

    /// Заполнение ключевых полей объекта из CDBCRowObject
    protected void populateKeysCDBC(BusinessObject resObj, CDBCRowObject rs) {
        Iterator it = null;
        if (resObj instanceof NRIBusinessObject) {
            it = resObj.colMap.keysMapNRI2JOIN_DB.keySet().iterator();
        } else {
            it = resObj.colMap.keysMapJOIN_DB2NRI.keySet().iterator();
        }
        while (it.hasNext()) {
            String keyName = (String)it.next();
            Object value = rs.getColumn(keyName).asObject();
            //System.out.println("Populating: key=" + keyName + ", value=" + value.toString());
            resObj.addKeyColumn(keyName, value);
        }
    }

    /// Сформировать SQL для определения изменившихся записей в JOIN_DB
    protected String makeChangesSQLJOIN_DB() {
        StringBuffer result = new StringBuffer();
        result.append("SELECT * FROM ");
        result.append(tableNameJOIN_DB);
        java.sql.Timestamp lastdate = getLastUpdateDate();
        if (lastdate != null) {
            result.append(" WHERE ");
            result.append(" last_update_date > ? ");
        }
        return result.toString();
    }

    /// Сформировать параметры SQL для определения изменившихся записей
    protected Object [] getChangesSQLParams() {
        java.sql.Timestamp lastdate = getLastUpdateDate();
        if (lastdate != null)
            return new Object [] {lastdate};
        else
            return new Object [] {};
    }

    /// Получить время последнего обновления таблицы
    protected java.sql.Timestamp getLastUpdateDate() {
        java.sql.Timestamp result = null;
        CDBCResultSet datesSet = new CDBCResultSet();
        datesSet.executeQuery(conNRI, "SELECT * FROM join_db_nriupdate WHERE tablename = ? and idapp = ?", new Object [] {tableNameNRI, new Integer(qry.getIdApp())}, 0);
        ListIterator it = datesSet.listIterator();
        if (it.hasNext()) {
           Object resDate = ((CDBCRowObject)it.next()).getColumn("updatetime").asObject();
           result = (java.sql.Timestamp)resDate;
        }
        return result;
    }

    /// Сформировать дату последнего обновления
    protected java.sql.Timestamp makeLastUpdateDate() {
        final long ONE_DAY_MSECS = 86400000;
        java.util.Date updDate = new java.util.Date();
        java.sql.Timestamp resDate = new java.sql.Timestamp(updDate.getTime()-ONE_DAY_MSECS);
        return resDate;
    }

    /// Обновление даты последнего обновления данных в системе NRI
    protected void updateLastTransferDateNRI() {
        PreparedStatement st = null;
        String buf1 = "INSERT INTO join_db_nriupdate (tablename, idapp, updatetime) VALUES (?,?,?)";
        String buf2 = "UPDATE join_db_nriupdate SET updatetime = ? WHERE tablename = ? AND idapp = ?";

        CDBCResultSet crs = new CDBCResultSet();
        if (!crs.executeUpdate(conNRI, buf1, new Object[] {tableNameNRI, new Integer(qry.getIdApp()), makeLastUpdateDate()})) {
            crs.executeUpdate(conNRI, buf2, new Object[] {makeLastUpdateDate(), tableNameNRI, new Integer(qry.getIdApp())});
        }
    }

    /// Отметка успешного выполнения переноса
    protected void markSuccessTransfer() {
        updateLastTransferDateNRI();
        qry.finishSuccess();
        System.out.println("TRANSFER SUCCESS for Table " + tableNameNRI);
    }

    /// Отметка ошибки переноса данных
    protected void markErrorTransfer() {
        qry.finishError();
        System.out.println("TRANSFER ERROR for Table " + tableNameNRI);
    }

    /// Отметка успешного выполнения удаления
    protected void markSuccessDelete() {
        qry.finishSuccess();
        System.out.println("DELETE SUCCESS for Table " + tableNameNRI);
    }

    /// Отметка ошибки переноса данных
    protected void markErrorDelete() {
        qry.finishError();
        System.out.println("DELETE ERROR for Table " + tableNameNRI);
    }

    /// Отметка времени выполнения запроса
    protected void startProgressIndicator(int argMaxRecords) {
        startQryTime = new java.util.Date();
        maxQryRecords = argMaxRecords;
    }

    /// Отметка времени выполнения запроса
    protected void markProgressIndicator(int argCurRecord) {
        java.util.Date currQryTime;
        currQryTime = new java.util.Date();
        if ( (currQryTime.getTime() - startQryTime.getTime()) > PROGRESS_UPDATE_TIME ) {
            int percent = 100 * argCurRecord / maxQryRecords;
            System.out.println("PROGRESS for " + tableNameNRI + ": " + percent + " %, " + argCurRecord + " of " + maxQryRecords + " records transferred");
            startQryTime = currQryTime;
        }
    }

    /// Проверка изменения всех объектов в JOIN_DB и обновление в NRI - содержательная часть
    protected void dofindChangesJOIN_DBUpdateNRI() {
        boolean result = true;
        int curRow = 0;
        if ( (conJOIN_DB != null) && (conNRI != null)) {
            qry.startProcessing();
            CDBCResultSet changesSet = new CDBCResultSet();
            String changesSQL = makeChangesSQLJOIN_DB();
            Object [] changesSQLParams = getChangesSQLParams();
            changesSet.executeQuery(conJOIN_DB, changesSQL, changesSQLParams, 0);
            ListIterator it = changesSet.listIterator();
            startProgressIndicator(changesSet.getRowsCount());
            while (it.hasNext()) {
                CDBCRowObject ro = (CDBCRowObject)it.next();
                BusinessObject objJOIN_DB = new JOIN_DBBusinessObject(getColumnMap());
                populateKeysCDBC(objJOIN_DB, ro);
                if (!findJOIN_DBUpdateNRI(objJOIN_DB)) {
                    String msg = "Ошибка переноса данных: " + tableNameJOIN_DB + ", Ключевые поля: " + objJOIN_DB.dumpKeys(BusinessObject.KEYS_JOIN_DB);
                    System.out.println(msg);
                    qry.addLogMessage(Query.MSG_ERROR, msg);
                    result = false;
                }
                curRow++;
                markProgressIndicator(curRow);
            }
        } else {
            result = false;
        }
        compareJOIN_DB2NRICounts();
        if (result)
            markSuccessTransfer();
        else
            markErrorTransfer();
    }

    /// Проверка изменения всех объектов в JOIN_DB и обновление в NRI
    public void findChangesJOIN_DBUpdateNRI(Query argQry) {
        qry = argQry;
        TimeCounter tc = new TimeCounter(tableNameNRI);
        try {
            conJOIN_DB = sconJOIN_DB.getConnection();
            conNRI = sconNRI.getConnection();
            dofindChangesJOIN_DBUpdateNRI();
        } finally {
            try {preparedSelectJOIN_DB.close();} catch (Exception e) {}
            try {preparedSelectNRI.close();} catch (Exception e) {}
            try {preparedInsertNRI.close();} catch (Exception e) {}
            try {preparedUpdateNRI.close();} catch (Exception e) {}
            preparedSelectJOIN_DB = null;
            preparedSelectNRI = null;
            preparedInsertNRI = null;
            preparedUpdateNRI = null;
            try {conNRI.close();} catch (Exception e) {}
            try {conJOIN_DB.close();} catch (Exception e) {}
            conNRI = null;
            conJOIN_DB = null;
        }
        tc.finish("Transfer");
    }

    /// Сформировать SQL для определения всех записей в NRI
    protected String makeAllSQLNRI() {
        StringBuffer result = new StringBuffer();
        result.append("SELECT * FROM ");
        result.append(tableNameNRI);
        return result.toString();
    }

    /// Проверка изменения всех объектов в JOIN_DB и обновление в NRI - содержательная часть
    protected void dofindDeletedJOIN_DBDeleteNRI() {
        boolean result = true;
        int curRow = 0;
        if ( (conJOIN_DB != null) && (conNRI != null)) {
            qry.startProcessing();
            CDBCResultSet allSet = new CDBCResultSet();
            String allSQL = makeAllSQLNRI();
            allSet.executeQuery(conNRI, allSQL, null, 0);
            ListIterator it = allSet.listIterator();
            startProgressIndicator(allSet.getRowsCount());
            while (it.hasNext()) {
                CDBCRowObject ro = (CDBCRowObject)it.next();
                BusinessObject objNRI = new NRIBusinessObject(getColumnMap());
                populateKeysCDBC(objNRI, ro);
                if (!checkJOIN_DBDeleteNRI(objNRI)) {
                    String msg = "Ошибка удаления данных: " + tableNameNRI + ", Ключевые поля: " + objNRI.dumpKeys(BusinessObject.KEYS_NRI);
                    System.out.println(msg);
                    qry.addLogMessage(Query.MSG_ERROR, msg);
                    result = false;
                }
                curRow++;
                markProgressIndicator(curRow);
            }
        } else {
            result = false;
        }
        compareJOIN_DB2NRICounts();
        if (result)
            markSuccessDelete();
        else
            markErrorDelete();
    }

    /// Поиск записей, удаленных в JOIN_DB и их удаление в NRI
    public void findDeletedJOIN_DBDeleteNRI(Query argQry) {
        qry = argQry;
        TimeCounter tc = new TimeCounter(tableNameNRI);
        try {
            conJOIN_DB = sconJOIN_DB.getConnection();
            conNRI = sconNRI.getConnection();
            dofindDeletedJOIN_DBDeleteNRI();
        } finally {
            try {preparedSelectJOIN_DB.close();} catch (Exception e) {}
            try {preparedSelectNRI.close();} catch (Exception e) {}
            try {preparedDeleteNRI.close();} catch (Exception e) {}
            preparedSelectJOIN_DB = null;
            preparedSelectNRI = null;
            preparedDeleteNRI = null;
            try {conNRI.close();} catch (Exception e) {}
            try {conJOIN_DB.close();} catch (Exception e) {}
            conNRI = null;
            conJOIN_DB = null;
        }
        tc.finish("Delete");
    }


    /// Проверка совпадения количества записей в системах JOIN_DB и NRI
    protected void compareJOIN_DB2NRICounts() {
        try {
            if ( (conJOIN_DB != null) && (conNRI != null)) {
                CDBCResultSet countSet1 = new CDBCResultSet();
                CDBCResultSet countSet2 = new CDBCResultSet();
                countSet1.executeQuery(conJOIN_DB, "SELECT count(*) AS cnt FROM " + tableNameJOIN_DB, new Object[]{}, 0);
                countSet2.executeQuery(conNRI, "SELECT count(*) AS cnt FROM " + tableNameNRI + " WHERE recordstatus='A'", new Object[]{}, 0);
                ListIterator it1 = countSet1.listIterator();
                ListIterator it2 = countSet2.listIterator();
                if (it1.hasNext() && it2.hasNext()) {
                    CDBCRowObject ro1 = (CDBCRowObject)it1.next();
                    CDBCRowObject ro2 = (CDBCRowObject)it2.next();
                    if (!ro1.getColumn("cnt").asObject().equals(ro2.getColumn("cnt").asObject())) {
                        qry.addLogMessage(Query.MSG_WARNING, "Количество записей в системе JOIN_DB: " + tableNameJOIN_DB + " = " + ro1.getColumn("cnt").asString() +
                                ", в системе NRI: " + tableNameNRI + " = " + ro2.getColumn("cnt").asString() );
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR WHILE COUNT(*): " + e.getMessage());
        }
    }
}


