package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса валют.
 */
public class BanksAdaptor extends DefaultCollaboration {

    private static class BanksMap extends ColumnMap {
        /**
         * Конструктор
         */
        BanksMap() {
            super();
            //         NFS              NRI            isKey
            addMap("bank_branch_id", "idbanknfs", true);
            addMap("name", "name", false);
            addMap("bik", "bik", false);
            addMap("swift", "swift", false);
            addMap("corr_acct", "korschet", false);
            addMap("addr", "address", false);

            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public BanksAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_banks", "APPS.XX_NRI_BANKS_VW", "last_update_date", new BanksMap());
    }

    /// Установка связи с существующим объектом NRI
    public boolean makeRelationInTargetDb(RowDBObject argObj) {
        boolean result = false;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = getTargetDbConnection().createStatement();
            String idBank = "" + ((java.math.BigDecimal)argObj.getKeysColumns().get("bank_branch_id")).intValue();
            rs = st.executeQuery("EXECUTE PROCEDURE LnkBankNfs(" + idBank + ")");
            if (rs.next()) {
                int resCode = rs.getInt(1);
                if (resCode == -1) {
                    String resMsg = rs.getString(2);
                    QueryProcessing.addLogMessage(getTargetDbConnection(), getQuery(), QueryProcessing.MSG_WARNING, resMsg);
                    result = true;
                } else if (resCode != 0) {
                    String resMsg = rs.getString(2);
                    QueryProcessing.addLogMessage(getTargetDbConnection(), getQuery(), QueryProcessing.MSG_ERROR, resMsg);
                } else {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing procedure LnkBankNfs = ");
            System.out.println("ERROR: code=" + e.getErrorCode() + ", msg=" + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        try {rs.close();} catch (Exception e) {}
        try {st.close();} catch (Exception e) {}
        return result;
    }

}
