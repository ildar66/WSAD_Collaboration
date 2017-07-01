package old.com.hps.july.sync;

import java.sql.*;

/**
 * @author Dmitry Dneprov
 * Простой адаптер обновления таблиц.
 * Применим в простых случаях.
 * Производит обновление всех записей
 */

public class SimpleAllSyncAdaptor extends SimpleAdaptor {

    protected SimpleAllSyncAdaptor(SyncConnection argConNRI, SyncConnection argConJOIN_DB,String argTableNameNRI, String argTableNameJOIN_DB) {
        super(argConNRI, argConJOIN_DB, argTableNameNRI, argTableNameJOIN_DB);
    }

    /// Сформировать SQL для определения изменившихся записей в JOIN_DB
    protected String makeChangesSQLJOIN_DB() {
        StringBuffer result = new StringBuffer();
        result.append("SELECT * FROM ");
        result.append(tableNameJOIN_DB);
        return result.toString();
    }

    /// Сформировать параметры SQL для определения изменившихся записей
    protected Object [] getChangesSQLParams() {
        return null;
    }
}

