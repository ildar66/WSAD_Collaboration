package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса контрактов.
 */
public class PaysAdaptor extends SimpleAdaptor {

    public class PaysMap extends ColumnMap {
        /**
         * Конструктор
         */
        PaysMap() {
            super();
            //         NFS              NRI            isKey
            addMap("check_id", "idPayNfs", true);
            addMap("invoice_id", "idSfNfs", false);
            addMap("vendor_id", "idVendorNfs", false);
            addMap("vendor_site_id", "idVendorSiteNfs", false);
            addMap("org_id", "idOrgNfs", false);
            addMap("from_bank_branch_id", "idBankPayer", false);
            addMap("from_bank_rs", "numAccountPayer", false);
            addMap("to_bank_account_id", "idAccountRecipient", false);
            addMap("check_number", "numPay", false);
            addMap("status", "statePay", false);
            addMap("amount", "sumPay", false);
            addMap("currency_code", "currPay", false);
            addMap("nazn", "purpose", false);
            addMap("check_date", "datePay", false);
            addMap("amt_rur", "sumRubPay", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public PaysAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_pays", "APPS.XX_NRI_PP_VW");
        setColumnMap(new PaysMap());
    }
}
