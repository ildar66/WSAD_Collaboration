package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;

import com.hps.july.cdbc.lib.CDBCResultSet;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса валют.
 */
public class BanksAdaptor extends SimpleAdaptor {

    public class BanksMap extends ColumnMap {
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
            addPredefinedColumnNRI("flagworknri", "N");
            addPredefinedColumnNRI("recordstatus", "A");
        }
    }

    public BanksAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "nfs_banks", "APPS.XX_NRI_BANKS_VW");
        setColumnMap(new BanksMap());
    }

    /// Установка связи с существующим объектом NRI
    public boolean makeRelationNRI(BusinessObject argObj) {
        boolean result = false;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conNRI.createStatement();
            String idBank = "" + ((java.math.BigDecimal)argObj.keysNRI.get("idbanknfs")).intValue();
            rs = st.executeQuery("EXECUTE PROCEDURE LnkBankNfs(" + idBank + ")");
            if (rs.next()) {
                int resCode = rs.getInt(1);
                if (resCode == -1) {
                    String resMsg = rs.getString(2);
                    qry.addLogMessage(Query.MSG_WARNING, resMsg);
                    result = true;
                } else if (resCode != 0) {
                    String resMsg = rs.getString(2);
                    qry.addLogMessage(Query.MSG_ERROR, resMsg);
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
