package com.hps.july.sync.kladr;

import com.hps.july.core.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Date: 04.04.2007
 * Time: 16:37:47
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class StreetAdapter extends DefaultCollaboration {
    int count=0;

    public static class StreetMap extends ColumnMap {
        StreetMap() {
            super();
            //     DBF,     NRI,     isKey
            addMap("CODE", "CODE", true);
            addMap("NAME", "NAME", false);
            addMap("SOCR", "SOCR", false);
            addMap("ZIP_CODE", "INDEX", false);
            addMap("GNINMB", "GNINMB", false);
            addMap("UNO", "UNO", false);
            addMap("OCATD", "OCATD", false);
            addPredefinedColumnTargetDb("recordStatus", "A");
        }
    }

    public StreetAdapter(DB dbTarget, DB dbSource) {
        super(dbTarget, dbSource, "KL_STREET", "XXIFC.XX_PER_RU_STREET_LOOKUPS_V", null, new StreetMap(), dbTarget);
    }

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        Connection source = DB.getConnection(getSourceDb());
        Connection target = DB.getConnection(getTargetDb());

        long time = new Date().getTime();
        long count=0;
        try {
            PreparedStatement delete = target.prepareStatement("DELETE FROM kl_street");
            delete.execute();
            delete.close();

            PreparedStatement insert = target.prepareStatement("INSERT INTO informix.kl_street(code, name, socr, index, gninmb, uno, ocatd, recordstatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement select = source.prepareStatement("SELECT * FROM XXIFC.XX_PER_RU_STREET_LOOKUPS_V");

            ResultSet resultSet = select.executeQuery();
            while(resultSet.next()) {
                insert.setString(1, resultSet.getString("CODE"));
                insert.setString(2, resultSet.getString("NAME"));
                insert.setString(3, resultSet.getString("SOCR"));
                insert.setString(4, resultSet.getString("ZIP_CODE"));
                insert.setString(5, resultSet.getString("GNINMB"));
                insert.setString(6, resultSet.getString("UNO"));
                insert.setString(7, resultSet.getString("OCATD"));
                insert.setString(8, "A");

                insert.execute();
                count++;

                if ((new Date().getTime() - time) > 1000*60) {
                    System.out.println("count = " + count);
                    count = 0;
                    time = new Date().getTime();
                }
            }
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                target.close();
                source.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    public boolean processRow(RowDBObject objJOIN_db) {
        count++;
        if (count == 1000) {
            try {
                PreparedStatement preparedStatement = targetDbConnection.prepareStatement("UPDATE STATISTICS FOR TABLE KL_STREET");
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }

        return true;
    }
}
