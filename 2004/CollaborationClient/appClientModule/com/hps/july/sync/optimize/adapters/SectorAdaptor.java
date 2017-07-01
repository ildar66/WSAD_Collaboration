package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ��������.
 */
public class SectorAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * �����������
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("id_sect", "id", true);
			addMap("name_sect", "name", false);
			addMap("lac", "lac", false);
			addMap("trx_num", "trx_num", false);
			addMap("equipment", "btsid", false);
			addMap("modified", "modified", false);

            // �������, ���������������� � �������
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public SectorAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nrisector", "sectors", "modified", new DetColumnMap(), argConNRI);
    }

}
