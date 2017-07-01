package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса счетов.
 */
public class AccountsAdaptor extends SimpleAdaptor {

    public class AccountsMap extends ColumnMap {
        /**
         * Конструктор
         */
        AccountsMap() {
            super();
            //         NFS              NRI            isKey
            addMap("bank_account_id", "idAccountNfs", true);
            addMap("VENDOR_SITE_ID", "idVendorSiteNfs", true);
            addMap("org_id", "idOrgNfs", false);
            addMap("bank_branch_id", "idbanknfs", false);
            addMap("vendor_id", "idVendorNfs", false);
            addMap("bank_account_num", "accountNum", false);
            addMap("currency_code", "currAcc", false);
            addMap("inactive_date", "inactive_date", false);
            addMap("primary_flag", "primary_flag", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public AccountsAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_accounts", "APPS.XX_NRI_VENDOR_SITE_RS_VW");
        setColumnMap(new AccountsMap());
    }


     /// Сформировать SQL для определения изменившихся записей в NFS
     protected String makeChangesSQLNFS() {
         StringBuffer result = new StringBuffer();
         result.append("SELECT * FROM ");
         result.append(tableNameJOIN_DB);
         result.append(" WHERE vendor_site_id IS NOT NULL ");
         java.sql.Timestamp lastdate = getLastUpdateDate();
         if (lastdate != null) {
            result.append(" AND last_update_date > ? ");
         }
         return result.toString();
     }

    /*
    /// Сформировать параметры SQL для определения изменившихся записей
    protected Object [] getChangesSQLParams() {
        return new Object [] {};
    }
    */

}
