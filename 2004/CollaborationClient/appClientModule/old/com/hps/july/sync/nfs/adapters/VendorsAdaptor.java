package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса поставщиков.
 */
public class VendorsAdaptor extends SimpleAdaptor {

    public class VendorsMap extends ColumnMap {
        /**
         * Конструктор
         */
        VendorsMap() {
            super();
            //         NFS              NRI            isKey
            addMap("vendor_id", "idvendornfs", true);
            addMap("end_date_active", "end_date_active", false);
            addMap("vendor_name", "name", false);
            addMap("inn", "inn", false);
            addMap("vendor_type_lookup_code", "type", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public VendorsAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_vendors", "APPS.XX_NRI_VENDORS_VW");
        setColumnMap(new VendorsMap());
    }
}
