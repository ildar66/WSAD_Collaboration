package com.hps.july.sync.kladr;

import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;

/**
 * Date: 04.04.2007
 * Time: 16:39:06
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class SocrbaseAdapter extends DefaultCollaboration {
    public static class ResourceTypesMap extends ColumnMap {
        ResourceTypesMap() {
            super();
            //     DBF,     NRI,     isKey
            addMap("ZIP_CODE", "LEVEL", false);
            addMap("SCNAME", "SCNAME", false);
            addMap("SOCRNAME", "SOCRNAME", false);
            addMap("KOD_T_ST", "KOD_T_ST", true);
            addPredefinedColumnTargetDb("recordStatus", "A");
        }
    }

    public SocrbaseAdapter(DB dbTarget, DB dbSource) {
        super(dbTarget, dbSource, "KL_SOCRBASE", "XXIFC.XX_PER_RU_ADDRES_ABBREVS_V", null, new ResourceTypesMap(), dbTarget, false);
    }
}
