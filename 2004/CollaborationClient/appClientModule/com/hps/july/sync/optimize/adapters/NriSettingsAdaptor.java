package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.CollaborationException;
import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса дат выгрузки таблиц.
 */
public class NriSettingsAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("tablename", "tablename", true);
            addMap("idapp", "idapp", true);
			addMap("updatetime", "updatetime", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

	private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT * FROM ");
			buf.append("join_db_nriupdate ");
			buf.append(" WHERE idapp = 14 AND ");
			buf.append(StatementHelper.generateClause(columnMap.getKeysMap().keySet(), " AND "));
			try {
				preparedSelectSourceDb = sourceDbConnection.prepareStatement(buf.toString());
				//System.out.println("preparedSelectSourceDb ="+buf.toString());//temp
			} catch (SQLException e) {
				System.out.println("Cannot Prepare SQL=" + buf.toString());
				e.printStackTrace(System.out);
			}
		}
	}

	/// Сформировать SQL для определения изменившихся записей в JOIN_DB
	protected String getSqlChangeInSource() {
		prepareSelect();
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM ");
		buf.append(" join_db_nriupdate ");
		buf.append(" WHERE idapp = 14 ");
		java.sql.Timestamp lastdate = getLastUpdateDate();
		if (lastdate != null && nameLastUpdateDateColumnInSource != null) {
			buf.append(" AND " + nameLastUpdateDateColumnInSource + " > ? ");
		}
		return buf.toString();
	}

	/// Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB - содержательная часть
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		prepareSelect();
		super.doFindDeletedInSourceDeleteInTarget();
	}

    public NriSettingsAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nrisettings", "join_db_nriupdate", null, new DetColumnMap(), argConNRI);
    }

}
