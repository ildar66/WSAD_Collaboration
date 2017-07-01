package com.hps.july.sync.kladr;

import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;

/**
 * Date: 04.04.2007
 * Time: 16:38:50
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class AltnamesAdapter extends DefaultCollaboration {
    public static class ResourceTypesMap extends ColumnMap {
        ResourceTypesMap() {
            super();
            //     DBF,     NRI,     isKey
            addMap("OLDCODE", "OLDCODE", true);
            addMap("NEWCODE", "NEWCODE", true);
            addMap("CODE_LEVEL", "LEVEL", false);
            addPredefinedColumnTargetDb("recordStatus", "A");
        }
    }

    public AltnamesAdapter(DB dbTarget, DB dbSource) {
        super(dbTarget, dbSource, "KL_ALTNAMES", "XXIFC.XX_PER_RU_ADDRESS_ALTNAMES_V", null, new ResourceTypesMap(), dbTarget, false);
    }
}