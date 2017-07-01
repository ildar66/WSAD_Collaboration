package old.com.hps.july.sync;

import java.util.*;

/**
 * @author: Dmitry Dneprov
 *  ласс дл€ установки соответстви€ полей
 * между системами JOIN_DB и NRI
 */
public class ColumnMap {
    protected TreeMap keysMapJOIN_DB2NRI;
    protected TreeMap keysMapNRI2JOIN_DB;
    protected TreeMap columnMapJOIN_DB2NRI;
    protected TreeMap columnMapNRI2JOIN_DB;
    protected TreeMap predefinedColumnsNRI;
    protected TreeMap predefinedColumnsJOIN_DB;

    /**
     *  онструктор
     * ”наследованный конструктор должен определ€ть конкретное соответствие колонок
     */
    protected ColumnMap() {
        keysMapJOIN_DB2NRI = new TreeMap();
        keysMapNRI2JOIN_DB = new TreeMap();
        columnMapJOIN_DB2NRI = new TreeMap();
        columnMapNRI2JOIN_DB = new TreeMap();
        predefinedColumnsNRI = new TreeMap();
        predefinedColumnsJOIN_DB = new TreeMap();
    }

    /// ƒобавление соответстви€ колонок
    public void addMap(String columnJOIN_DB, String columnNRI, boolean isKeyColumn) {
        String JOIN_DBVal = null;
        String nriVal = null;
        if (columnJOIN_DB != null)
            JOIN_DBVal = columnJOIN_DB.toLowerCase();
        if (columnNRI != null)
            nriVal = columnNRI.toLowerCase();
        if (isKeyColumn) {
            if (JOIN_DBVal != null)
                keysMapJOIN_DB2NRI.put(JOIN_DBVal, nriVal);
            if (nriVal != null)
                keysMapNRI2JOIN_DB.put(nriVal, JOIN_DBVal);
        } else {
            if (JOIN_DBVal != null)
                columnMapJOIN_DB2NRI.put(JOIN_DBVal, nriVal);
            if (nriVal != null)
                columnMapNRI2JOIN_DB.put(nriVal, JOIN_DBVal);
        }
    }

    /// ƒобавление колонки с предопределенным значением
    public void addPredefinedColumnNRI(String argKey, String argValue) {
        predefinedColumnsNRI.put(argKey.toLowerCase(), argValue);
    }
}
