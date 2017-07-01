package com.hps.july.util;

import com.hps.july.core.ColumnMap;
import com.hps.july.core.RowDBObject;

import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.sql.*;

/**
 * Date: 05.04.2007
 * Time: 18:15:50
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class StatementHelper {
    public static String generateClauseWithPrefix(Collection params, String separator, String tablePrefix) {
        StringBuffer buf = new StringBuffer();
        Iterator it = params.iterator();
        boolean isFirst = true;
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key == null)
                continue; //new
            if (!isFirst) {
                buf.append(separator);
            }
            isFirst = false;
            buf.append(tablePrefix);
            buf.append(".");
            buf.append(key);
            buf.append(" = ? ");
        }
        return buf.toString();
    }

    public static String generateClause(Collection params, String separator) {
        StringBuffer buf = new StringBuffer();
        Iterator it = params.iterator();
        boolean isFirst = true;
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key == null)
                continue; //new
            if (!isFirst) {
                buf.append(separator);
            }
            isFirst = false;
            buf.append(key);
            buf.append(" = ? ");
        }
        return buf.toString();
    }

    /**
     * Генерирует наименование или параметр колонки
     */
    public static String generateColumnNameParam(Collection params, boolean argFirst, boolean isName) {
        StringBuffer buf = new StringBuffer();
        Iterator it = params.iterator();
        boolean isFirst = argFirst;
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key == null)
                continue; //new
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

    /**
     * Обобщенная процедура установки SQL-параметров
     * @param st
     * @param params
     * @param hashMap
     * @param argStartParam
     * @return
     */
    public static boolean setParams(PreparedStatement st, Collection params, Map hashMap, int argStartParam) {
        boolean result = true;
        Iterator it = params.iterator();
        int i = argStartParam;
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = hashMap.get(key);
            try {
                if (value != null)
                    st.setObject(i, value);
                else
                    st.setNull(i, Types.CHAR);
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

    /**
     * Заполнение ключевых полей бизнес-объекта из строки ResultSet
     */
    public static boolean populateKeys(RowDBObject resObj, Collection keys, ResultSet rs) {
        boolean result = true;
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String keyName = (String) it.next();
            try {
                resObj.addKeyColumn(keyName, rs.getObject(keyName));
            } catch (SQLException e) {
                result = false;
                System.out.println("ERROR: Cannot get column: " + keyName);
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());

                resObj.addKeyColumn(keyName, null);
            }
        }
        return result;
    }

    /**
     *  Заполнение полей бизнес-объекта из строки ResultSet
     * @param resObj
     * @param columns
     * @param rs
     * @return
     */
    public static boolean populateColumns(RowDBObject resObj, Collection columns, ResultSet rs) {
        boolean result = true;
        Iterator it = columns.iterator();
        while (it.hasNext()) {
            String colName = (String) it.next();
            try {
                resObj.addColumn(colName, rs.getObject(colName));
            } catch (SQLException e) {
                result = false;
                System.out.println("ERROR: Cannot get column: " + colName);
                System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
                //e.printStackTrace(System.out);
                resObj.addColumn(colName, null);
            }
        }

        return result;
    }

    /**
     * Генерация разделителя между основными и предопределенными колонками
     * @param argObj
     * @return
     */
    public static String generateSetPredefinedSeparatorTARGET_DB(RowDBObject argObj, ColumnMap colMap) {
        if ((colMap.getColumnMap().values().size() > 0) && (colMap.getPredefinedColumns().keySet().size() > 0)) {
            return " , ";
        }

        return "";
    }

    /**
     * Генерация разделителя между ключевыми и ключевыми предопределенными колонками
     * @param argObj
     * @return
     */
    public static String generateKeyPredefinedSeparatorTARGET_DB(RowDBObject argObj, ColumnMap colMap) {
        if (colMap.getPredefinedKeyColumns().keySet().size() > 0) {
            return " AND ";
        }

        return "";
    }

   /**
     * Сформировать SQL для определения изменившихся записей в JOIN_DB
     */
    public static String getSqlChangeInSource(String sourceTable, Timestamp lastdate, String lastUpdateDate_nameColumnJOIN_DB) {
        StringBuffer result = new StringBuffer();
        result.append("SELECT * FROM ");
        result.append(sourceTable);
        if (lastdate != null && lastUpdateDate_nameColumnJOIN_DB != null) {
            result.append(" WHERE ");
            result.append(lastUpdateDate_nameColumnJOIN_DB + " > ? ");
        }
        return result.toString();
    }

    public static String getSelectSourceDb(String sourceTable, ColumnMap columnMap) {
        return "SELECT * FROM " + sourceTable +  " WHERE " + StatementHelper.generateClause(columnMap.getKeysMap().keySet(), " AND ");
    }

    public static String getSelectTargetDb(String targetTable, ColumnMap columnMap, RowDBObject rowDBObject) {
        return  "SELECT * FROM " + targetTable + " WHERE " +
                StatementHelper.generateClause(columnMap.getKeysMap().values(), " AND ") +
                StatementHelper.generateKeyPredefinedSeparatorTARGET_DB(rowDBObject, columnMap) +
                StatementHelper.generateClause(columnMap.getPredefinedKeyColumns().keySet(), " AND ");
    }

    public static String  getUpdateTargetDb(String targetTable, ColumnMap columnMap, RowDBObject rowDBObject) {
        return " UPDATE " + targetTable + " SET " +
               StatementHelper.generateClause(columnMap.getColumnMap().values(), " , ") +
               StatementHelper.generateSetPredefinedSeparatorTARGET_DB(rowDBObject, columnMap) +
               StatementHelper.generateClause(columnMap.getPredefinedColumns().keySet(), " , ") +
               " WHERE " +
               StatementHelper.generateClause(columnMap.getKeysMap().values(), " AND ") +
               StatementHelper.generateKeyPredefinedSeparatorTARGET_DB(rowDBObject, columnMap) +
               StatementHelper.generateClause(columnMap.getPredefinedKeyColumns().keySet(), " AND ");

    }

    public static String getInsertTargetDb(String targetTable, ColumnMap columnMap) {
        return  "INSERT INTO " +
                targetTable +
                " ( " +
                StatementHelper.generateColumnNameParam(columnMap.getKeysMap().values(), true, true) +
                StatementHelper.generateColumnNameParam(columnMap.getColumnMap().values(), false, true) +
                StatementHelper.generateColumnNameParam(columnMap.getPredefinedColumns().keySet(), false, true) +
                StatementHelper.generateColumnNameParam(columnMap.getPredefinedKeyColumns().keySet(), false, true) +
                " ) VALUES ( " +
                StatementHelper.generateColumnNameParam(columnMap.getKeysMap().values(), true, false) +
                StatementHelper.generateColumnNameParam(columnMap.getColumnMap().values(), false, false) +
                //generateColumnNameParam(columnMap.predefinedColumns.values(), false, false));
                // Исправлено DD 21.04.05
                StatementHelper.generateColumnNameParam(columnMap.getPredefinedColumns().keySet(), false, false) +
                StatementHelper.generateColumnNameParam(columnMap.getPredefinedKeyColumns().keySet(), false, false) +
                " )";
                //System.out.println(buf.toString());

    }

    public static String getUpdateTargetDb(String targetTable, ColumnMap columnMap) {
        return "UPDATE " +  targetTable +  " SET recordstatus = 'D' WHERE " + StatementHelper.generateClause(columnMap.getKeysMap().values(), " AND ");
    }
}
