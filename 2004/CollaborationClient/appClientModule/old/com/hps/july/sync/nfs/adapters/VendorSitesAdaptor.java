package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса отделений поставщиков.
 */
public class VendorSitesAdaptor extends SimpleAdaptor {

    public class VendorSitesMap extends ColumnMap {
        /**
         * Конструктор
         */
        VendorSitesMap() {
            super();
            //         NFS              NRI            isKey
            addMap("VENDOR_SITE_ID", "idVendorSiteNfs", true);
            addMap("VENDOR_ID", "idVendorNfs", false);
            addMap("ORG_ID", "idOrgNfs", false);
            addMap("VENDOR_SITE_CODE", "vendorSiteCode", false);
            addMap("SITE_NAME", "name", false);
            addMap("MATCH_OPTION", "matchOption", false);
            addMap("addr", "address", false);
            addMap("kpp", "kpp", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public VendorSitesAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_vendorsites", "APPS.XX_NRI_VENDOR_SITES_VW");
        setColumnMap(new VendorSitesMap());
    }
}
