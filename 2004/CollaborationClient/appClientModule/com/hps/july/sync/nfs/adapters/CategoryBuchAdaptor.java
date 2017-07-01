package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Ildar
 * Адаптер для переноса поставщиков.
 */
public class CategoryBuchAdaptor extends DefaultCollaboration {

    private static class CategoryBuchMap extends ColumnMap {
        /**
         * Конструктор
         */
		CategoryBuchMap() {
            super();
            //         NFS              NRI            isKey
            addMap("category_id", "categorybuch", true);
            addMap("category_code", "categorybuchcode", false);
            addMap("description", "description", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public CategoryBuchAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_CategoryBuch", "APPS.XX_NRI_CATEGORIES_VW", null, new CategoryBuchMap());
    }
}
