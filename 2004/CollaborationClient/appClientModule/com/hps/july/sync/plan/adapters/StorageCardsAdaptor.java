package com.hps.july.sync.plan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса Базовых станций.
 */
public class StorageCardsAdaptor extends DefaultCollaboration {

	private boolean loadIBP;

    protected static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //     NRI       MSSQL      isKey
            addMap("storagecard", "idcard", true);
			addMap("storageplace", "idbs", false);
            addMap("resource", "resource", false);
			addMap("model", "model", false);
			addMap("manucode", "manucode", false);
			addMap("name", "name", false);
			addMap("serialnumber", "serialnumber", false);
			addMap("qty", "qty", false);
			addMap("policy", "countpolicy", false);
			addMap("notes", "resnotes", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT s.storagecard, s.storageplace, s.resource, s.serialnumber, s.qty, ");
			buf.append("s.policy, r.model, r.manucode, r.name, r.notes ");
			buf.append("FROM storagecards s, resources r, basestations b, equipment e, positions p  ");
			buf.append(" WHERE s.storageplace = e.equipment AND p.storageplace = e.position AND ");
			buf.append("r.resource = s.resource AND ");
			if (loadIBP)
				buf.append("r.resourcesubtype = 7 AND ");
			else
				buf.append("r.resourcesubtype <> 7 AND ");
			buf.append(" p.regionid IN (1) AND s.closed = 'N' AND b.equipment = e.equipment AND ");
			buf.append(" e.state IN ('W', 'M') AND b.type IN ('S', 'C', 'G') AND ");
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
		buf.append("SELECT s.storagecard, s.storageplace, s.resource, s.serialnumber, s.qty, ");
		buf.append("s.policy, r.model, r.manucode, r.name, r.notes ");
		buf.append("FROM storagecards s, resources r, basestations b, equipment e, positions p  ");
		buf.append(" WHERE s.storageplace = e.equipment AND p.storageplace = e.position AND ");
		buf.append("r.resource = s.resource AND ");
		if (loadIBP)
			buf.append("r.resourcesubtype = 7 AND ");
		else
			buf.append("r.resourcesubtype <> 7 AND ");
		buf.append(" p.regionid IN (1) AND s.closed = 'N' AND b.equipment = e.equipment AND ");
		buf.append(" e.state IN ('W', 'M') AND b.type IN ('S', 'C', 'G') ");
		return buf.toString();
	}

	/// Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB - содержательная часть
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		prepareSelect();
		super.doFindDeletedInSourceDeleteInTarget();
	}

    public StorageCardsAdaptor(DB argConPlan, DB argConNRI, String argTableName, boolean argLoadIBP) {
		super(argConPlan, argConNRI, argTableName, "storagecards", null, new DetColumnMap(), argConNRI);
		loadIBP = argLoadIBP;
    }

}
