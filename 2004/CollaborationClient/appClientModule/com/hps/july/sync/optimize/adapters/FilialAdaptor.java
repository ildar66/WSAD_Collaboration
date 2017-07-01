package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ���. ���.
 */
public class FilialAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * �����������
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("regionid", "id", true);
            addMap("supregid", "regionid", false);
			addMap("regname", "name", false);

            // �������, ���������������� � �������
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public FilialAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nribranch", "regions", null, new DetColumnMap(), argConNRI);
    }

}
