package com.hps.july.sync.plan.adapters;

import java.sql.*;

import com.hps.july.core.CollaborationException;
import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса Базовых станций.
 */
public class BTSAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //     NRI       MSSQL      isKey
            addMap("equipment", "idbs", true);
            addMap("name", "name", false);
			addMap("number", "number", false);
			addMap("state", "bstate", false);
			addMap("sesdate", "seslicdate", false);
			addMap("respname", "respname", false);
			addMap("bscnumber", "bscnumber", false);
			addMap("bscname", "bscname", false);
			addMap("date_connect", "dateon900", false);
			addMap("date_connect2", "dateon1800", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT b.equipment, b.number, b.name, c.number bscnumber, sc.name bscname, ");
			buf.append("b.date_connect, b.date_connect2, e.state, ");
			buf.append("(SELECT v.ses FROM viewlicenciesonpos v WHERE v.position = p.storageplace) sesdate, ");
			buf.append("TRIM(pl.lastname) || ' ' || TRIM(pl.firstname) || ' ' || TRIM(pl.middlename) respname  ");
			buf.append("FROM basestations b, equipment e, positions p,  ");
			buf.append("outer (workresponsibility r, workers w, people pl), ");
			buf.append("outer (controllers c, storageplaces sc) ");
			buf.append(" WHERE b.equipment = e.equipment AND p.storageplace = e.position AND ");
			buf.append(" p.regionid IN (1) AND ");
			buf.append(" c.equipment = sc.storageplace AND c.equipment = b.controller AND ");
			buf.append(" r.storageplace = p.storageplace AND r.idresponsibility = 7 AND ");
			buf.append(" r.resptype = 'W' AND w.worker = r.worker AND pl.man = w.man AND ");
			buf.append(" e.state IN ('P', 'W', 'M') AND b.type IN ('S', 'C', 'G') AND b.equipment = ? ");
			//buf.append(generateClause(columnMap.getKeysMap().keySet(), " AND "));
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
		buf.append("SELECT b.equipment, b.number, b.name, c.number bscnumber, sc.name bscname, ");
		buf.append("b.date_connect, b.date_connect2, e.state, ");
		buf.append("(SELECT v.ses FROM viewlicenciesonpos v WHERE v.position = p.storageplace) sesdate, ");
		buf.append("TRIM(pl.lastname) || ' ' || TRIM(pl.firstname) || ' ' || TRIM(pl.middlename) respname  ");
		buf.append("FROM basestations b, equipment e, positions p,  ");
		buf.append("outer (workresponsibility r, workers w, people pl), ");
		buf.append("outer (controllers c, storageplaces sc) ");
		buf.append(" WHERE b.equipment = e.equipment AND p.storageplace = e.position AND ");
		buf.append(" p.regionid IN (1) AND ");
		buf.append(" c.equipment = sc.storageplace AND c.equipment = b.controller AND ");
		buf.append(" r.storageplace = p.storageplace AND r.idresponsibility = 7 AND ");
		buf.append(" r.resptype = 'W' AND w.worker = r.worker AND pl.man = w.man AND ");
		buf.append(" e.state IN ('P', 'W', 'M') AND b.type IN ('S', 'C', 'G')  ");
		return buf.toString();
	}

	/// Проверка изменения всех объектов в JOIN_DB и обновление в TARGET_DB - содержательная часть
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		prepareSelect();
		super.doFindDeletedInSourceDeleteInTarget();
	}

    public BTSAdaptor(DB argConPlan, DB argConNRI) {
        super(argConPlan, argConNRI, "basestations", "basestations", null, new DetColumnMap(), argConNRI);
    }

}
