package com.hps.july.sync.optimize.adapters;

import java.sql.*;

import com.hps.july.core.DB;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ������.
 */
public class SiteAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * �����������
         */
		DetColumnMap() {
            super();
            //     NRI       MSSQL      isKey
            addMap("storageplace", "id", true);
            addMap("name", "name", false);
			addMap("address", "address", false);
			addMap("regionid", "branchid", false);
			addMap("posstate", "posstate", false);
			addMap("modified", "modified", false);
			addMap("modifiedby", "modifiedby", false);

            // �������, ���������������� � �������
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT * FROM ");
			buf.append("positions w, storageplaces s ");
			buf.append(" WHERE w.storageplace = s.storageplace ");
			buf.append(" AND s.storageplace = ? ");
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
		result.append("SELECT * FROM ");
		result.append("positions w, storageplaces s ");
		result.append(" WHERE w.storageplace = s.storageplace ");
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

    public SiteAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nrisite", "positions", "modified", new DetColumnMap(), argConNRI);
    }

}
