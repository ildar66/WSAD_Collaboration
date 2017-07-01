package com.hps.july.core;

import java.util.Map;
import java.util.HashMap;

/**
 *  ласс дл€ установки соответстви€ полей
 * между системами JOIN_DB и TARGET_DB
 */
public class ColumnMap {
    protected Map keysMap;

    public Map getPredefinedColumns() {
        return predefinedColumns;
    }

    public Map getPredefinedKeyColumns() {
        return predefinedKeyColumns;
    }

    protected Map columnMap;
    protected Map predefinedColumns;
    protected Map predefinedKeyColumns;

    /**
     *  онструктор
     * ”наследованный конструктор должен определ€ть конкретное соответствие колонок
     */
    protected ColumnMap() {
        keysMap = new HashMap();
        columnMap = new HashMap();
        predefinedColumns = new HashMap();
        predefinedKeyColumns = new HashMap();
    }

    /**
     * ƒобавление соответстви€ колонок
     *
     * @param sourceColumn
     * @param targetColumn
     * @param isKeyColumn
     */
    public void addMap(String sourceColumn, String targetColumn, boolean isKeyColumn) {
        if (isKeyColumn) {
            keysMap.put(sourceColumn==null ? null : sourceColumn.toLowerCase(),
                        targetColumn==null ? null : targetColumn.toLowerCase());
        } else {
            columnMap.put(sourceColumn==null ? null : sourceColumn.toLowerCase(),
                          targetColumn==null ? null : targetColumn.toLowerCase());
        }
    }

    /**
     * ƒобавление колонки с предопределенным значением
     */
    public void addPredefinedColumnTargetDb(String argKey, Object argValue) {
        predefinedColumns.put(argKey.toLowerCase(), argValue);
    }

    /**
     * ƒобавление ключевой колонки с предопределенным значением
     * @param argKey
     * @param argValue
     */
    public void addPredefinedKeyColumnTargetDb(String argKey, Object argValue) {
        predefinedKeyColumns.put(argKey.toLowerCase(), argValue);
    }

    /**
     * Returns the columnMap.
     * @return HashMap
     */
    public Map getColumnMap() {
        return columnMap;
    }

    /**
     * Returns the keysMap.
     * @return HashMap
     */
    public Map getKeysMap() {
        return keysMap;
    }
}
