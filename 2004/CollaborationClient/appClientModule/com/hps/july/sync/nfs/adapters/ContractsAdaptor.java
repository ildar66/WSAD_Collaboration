package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса контрактов.
 */
public class ContractsAdaptor extends DefaultCollaboration {

    private static class ContractsMap extends ColumnMap {
        /**
         * Конструктор
         */
        ContractsMap() {
            super();
            //         NFS              NRI            isKey
            addMap("po_header_id", "idContractNfs", true);
            addMap("vendor_id", "idVendorNfs", false);
            addMap("vendor_site_id", "idVendorSiteNfs", false);
            addMap("org_id", "idOrgNfs", false);
            addMap("contract_num", "numContractNfs", false);
            addMap("dog_num", "numContract", false);
            addMap("dog_amt", "sumContract", false);
            addMap("currency_code", "currContract", false);
            addMap("rate_date", "rateDate", false);
            addMap("rate", "rate", false);
            addMap("dog_date", "dateContract", false);
            addMap("sev_num", "sevNumContract", false);
            //addMap("person_id", "", false);
            addMap("terms_id", "termsPay", false);
            addMap("start_date", "startDate", false);
            addMap("end_date", "endDate", false);
            addMap("amount_limit", "sumLimit", false);
            addMap("authorization_status", "statusContract", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public ContractsAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_contracts", "APPS.XX_NRI_DOG_VW", "last_update_date", new ContractsMap());
    }
}
