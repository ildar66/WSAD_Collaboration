package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса контрактов.
 */
public class SFAdaptor extends DefaultCollaboration {

    private static class SFMap extends ColumnMap {
        /**
         * Конструктор
         */
        SFMap() {
            super();
            //         NFS              NRI            isKey
            addMap("invoice_id", "idSfNfs", true);
            addMap("po_header_id", "idZpNfs", false);
            addMap("vendor_id", "idVendorNfs", false);
            addMap("vendor_site_id", "idVendorSiteNfs", false);
            addMap("vendor_id3", "idVendor3Nfs", false);
            addMap("vendor_site_id3", "idVendorSite3Nfs", false);
            addMap("org_id", "idOrgNfs", false);
            addMap("invoice_num", "numSf", false);
            addMap("relis_num", "numReliseSf", false);
            addMap("vat_code", "vatCode", false);
            addMap("payment_status_flag", "flagOpl", false);
            addMap("status", "stateSf", false);
            addMap("invoice_amount", "sumWithOutVAT", false);
            addMap("invoice_currency_code", "currSf", false);
            addMap("vat", "sumVat", false);
            addMap("BANK_ACCOUNT_ID", "idAccNfs", false);
            addMap("description", "description", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public SFAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_sf", "APPS.XX_NRI_SF_VW", "last_update_date", new SFMap());
    }
}
