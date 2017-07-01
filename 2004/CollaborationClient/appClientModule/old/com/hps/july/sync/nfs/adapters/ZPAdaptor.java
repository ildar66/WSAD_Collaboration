package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса контрактов.
 */
public class ZPAdaptor extends SimpleAdaptor {

    public class ZPMap extends ColumnMap {
        /**
         * Конструктор
         */
        ZPMap() {
            super();
            //         NFS              NRI            isKey
            addMap("po_header_id", "idZpNfs", true);
            addMap("vendor_id", "idVendorNfs", false);
            addMap("vendor_site_id", "idVendorSiteNfs", false);
            addMap("org_id", "idOrgNfs", false);
            addMap("zp_num", "numZp", false);
            addMap("contract_po_header_id", "idContractNfs", false);
            addMap("authorization_status", "stateZp", false);
            addMap("sum_without_vat", "sumWithoutVat", false);
            addMap("currency_code", "currZp", false);
            addMap("vat", "sumVat", false);
            addMap("comments", "comments", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
        }
    }

    public ZPAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_zp", "APPS.XX_NRI_ZP_VW");
        setColumnMap(new ZPMap());
    }
}
