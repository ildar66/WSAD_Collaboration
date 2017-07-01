package com.hps.july.sync.plan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ������� �������.
 */
public class SectorsAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * �����������
         */
		DetColumnMap() {
            super();
            //     NRI       MSSQL      isKey
            addMap("id_sect", "idsect", true);
			addMap("equipment", "idbs", false);
            addMap("band", "band", false);
			addMap("num_sect", "num_sect", false);
			addMap("name_sect", "name_sect", false);
			addMap("trx_num", "trx_num", false);

            // �������, ���������������� � �������
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    private void prepareSelect() {
		if (preparedSelectSourceDb == null) {
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT s.id_sect, s.equipment, s.band, s.num_sect, s.name_sect, s.trx_num ");
			buf.append("FROM sectors s, basestations b, equipment e, positions p  ");
			buf.append(" WHERE s.equipment = e.equipment AND p.storageplace = e.position AND ");
			buf.append(" p.regionid IN (1) AND s.planfacttype = 1 AND b.equipment = e.equipment AND ");
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

	/// ������������ SQL ��� ����������� ������������ ������� � JOIN_DB
	protected String getSqlChangeInSource() {
		prepareSelect();
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT s.id_sect, s.equipment, s.band, s.num_sect, s.name_sect, s.trx_num ");
		buf.append("FROM sectors s, basestations b, equipment e, positions p  ");
		buf.append(" WHERE s.equipment = e.equipment AND p.storageplace = e.position AND ");
		buf.append(" p.regionid IN (1) AND s.planfacttype = 1 AND b.equipment = e.equipment AND ");
		buf.append(" e.state IN ('W', 'M') AND b.type IN ('S', 'C', 'G') ");
		return buf.toString();
	}

	/// �������� ��������� ���� �������� � JOIN_DB � ���������� � TARGET_DB - �������������� �����
	protected void doFindDeletedInSourceDeleteInTarget() throws CollaborationException {
		prepareSelect();
		super.doFindDeletedInSourceDeleteInTarget();
	}

    public SectorsAdaptor(DB argConPlan, DB argConNRI) {
        super(argConPlan, argConNRI, "sectors", "sectors", null, new DetColumnMap(), argConNRI);
    }

}
