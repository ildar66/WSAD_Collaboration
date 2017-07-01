package com.hps.july.sync.optimize.adapters;

import java.sql.*;

import com.hps.july.core.DB;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса физ. лиц.
 */
public class PeopleAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("man", "id", true);
            addMap("lastname", "lastname", false);
			addMap("firstname", "firstname", false);
			addMap("middlename", "middlename", false);
            addMap("tabnum", "tabnum", false);
			addMap("phone1", "phone", false);
			addMap("email", "email", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

	private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT p.*, w.phone1, w.email FROM ");
			buf.append("people p, outer workers w ");
			buf.append(" WHERE w.man = p.man AND ");
			buf.append(" w.worker = (SELECT min(w2.worker) FROM workers w2 ");
			buf.append(" WHERE w2.man = w.man) AND p.man = ?");
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
		buf.append("SELECT p.*, w.phone1, w.email FROM ");
		buf.append("people p, outer workers w ");
		buf.append(" WHERE w.man = p.man AND ");
		buf.append(" w.worker = (SELECT min(w2.worker) FROM workers w2 ");
		buf.append(" WHERE w2.man = w.man) ");
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

    public PeopleAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nripeople", "people", null, new DetColumnMap(), argConNRI);
    }

}
