package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса контрактов.
 */
public class TaxesAdaptor extends SimpleAllSyncAdaptor {

    public class TaxesMap extends ColumnMap {
        /**
         * Конструктор
         */
        TaxesMap() {
            super();
            //         NFS              NRI            isKey
            addMap("tax_id", "idTaxRate", true);
            addMap("org_id", "idOrgNfs", false);
            addMap("vat_code", "vatCode", false);
            addMap("description", "description", false);
            addMap("tax_rate", "taxrate", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public TaxesAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_taxrates", "APPS.XX_NRI_VAT_VW");
        setColumnMap(new TaxesMap());
    }
}
