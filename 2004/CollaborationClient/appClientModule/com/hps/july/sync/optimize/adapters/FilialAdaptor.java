package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса физ. лиц.
 */
public class FilialAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("regionid", "id", true);
            addMap("supregid", "regionid", false);
			addMap("regname", "name", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public FilialAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nribranch", "regions", null, new DetColumnMap(), argConNRI);
    }

}
