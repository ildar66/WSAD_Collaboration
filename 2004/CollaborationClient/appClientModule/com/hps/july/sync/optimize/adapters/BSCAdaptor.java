package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ������������.
 */
public class BSCAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * �����������
         */
		DetColumnMap() {
            super();
            //     NRI       MSSQL      isKey
            addMap("storageplace", "id", true);
			addMap("position", "siteid", false);
            addMap("number", "number", false);
			addMap("brand", "brand", false);
			addMap("switch", "mscid", false);
			addMap("modified", "modified", false);
			addMap("modifiedby", "modifiedby", false);

            // �������, ���������������� � �������
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT w.*, e.position, s.*, o.shortname brand FROM ");
			buf.append("controllers w, equipment e, storageplaces s, outer (resources r, organizations o) ");
			buf.append(" WHERE w.equipment = e.equipment AND s.storageplace = e.equipment AND ");
			buf.append(" r.resource = w.resource AND o.organization = r.manufacturer AND ");
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

	/// ������������ SQL ��� ����������� ������������ ������� � JOIN_DB
	protected String getSqlChangeInSource() {
		prepareSelect();
		StringBuffer result = new StringBuffer();
		result.append("SELECT w.*, e.position, s.*, o.shortname brand FROM ");
		result.append("controllers w, equipment e, storageplaces s, outer (resources r, organizations o) ");
		result.append(" WHERE w.equipment = e.equipment AND s.storageplace = e.equipment AND ");
		result.append(" r.resource = w.resource AND o.organization = r.manufacturer ");
		java.sql.Timestamp lastdate = getLastUpdateDate();
		if (lastdate != null && nameLastUpdateDateColumnInSource != null) {
			result.append(" AND " + nameLastUpdateDateColumnInSource + " > ? ");
		}
		return result.toString();
	}

	/// �������� ��������� ���� �������� � JOIN_DB � ���������� � TARGET_DB - �������������� �����
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		prepareSelect();
		super.doFindDeletedInSourceDeleteInTarget();
	}

    public BSCAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nribsc", "controllers", "s.modified", new DetColumnMap(), argConNRI);
    }

}
