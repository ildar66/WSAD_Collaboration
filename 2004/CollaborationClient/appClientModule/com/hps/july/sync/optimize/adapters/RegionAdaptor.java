package com.hps.july.sync.optimize.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса регионов.
 */
public class RegionAdaptor extends DefaultCollaboration {

    private static class DetColumnMap extends ColumnMap {
        /**
         * Конструктор
         */
		DetColumnMap() {
            super();
            //         NRI              MSSQL      isKey
            addMap("supregid", "id", true);
            addMap("supregname", "name", false);

            // Колонки, предопределенные в таблице
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public RegionAdaptor(DB argConOptimize, DB argConNRI) {
        super(argConOptimize, argConNRI, "nriregion", "superregions", null, new DetColumnMap(), argConNRI);
    }

}
