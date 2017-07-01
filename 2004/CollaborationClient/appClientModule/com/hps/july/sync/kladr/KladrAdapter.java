package com.hps.july.sync.kladr;

import com.hps.july.core.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Date;

/**
 * Date: 04.04.2007
 * Time: 16:38:29
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class KladrAdapter extends DefaultCollaboration {
    private int count=0;

    public static class ResourceTypesMap extends ColumnMap {
        ResourceTypesMap() {
            super();
            //     DBF,     NRI,     isKey
            addMap("CODE", "CODE", true);
            addMap("NAME", "NAME", false);
            addMap("SOCR", "SOCR", false);
            addMap("ZIP_CODE", "INDEX", false);
            addMap("GNINMB", "GNINMB", false);
            addMap("UNO", "UNO", false);
            addMap("OCATD", "OCATD", false);
            addMap("STATUS", "STATUS", false);
            addPredefinedColumnTargetDb("recordStatus", "A");
        }
    }

    public KladrAdapter(DB dbTarget, DB dbSource) {
        super(dbTarget, dbSource, "KL_KLADR", "XXIFC.XX_PER_RU_ADDRESS_LOOKUPS_V", null, new ResourceTypesMap(), dbTarget);
    }

    public boolean processRow(RowDBObject objJOIN_db) {
        count++;
        if (count == 1000) {
            try {
                PreparedStatement preparedStatement = targetDbConnection.prepareStatement("UPDATE STATISTICS FOR TABLE KL_KLADR");
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }

        return true;
    }

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        Connection source = DB.getConnection(getSourceDb());
        Connection target = DB.getConnection(getTargetDb());

        long time = new Date().getTime();
        long count=0;
        try {
            PreparedStatement delete = target.prepareStatement("DELETE FROM kl_kladr");
            delete.execute();
            delete.close();

            PreparedStatement insert = target.prepareStatement("INSERT INTO kl_kladr(code, name, socr, index, gninmb, uno, ocatd, status, recordstatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement select = source.prepareStatement("SELECT * FROM XXIFC.XX_PER_RU_ADDRESS_LOOKUPS_V");

            ResultSet resultSet = select.executeQuery();
            while(resultSet.next()) {
                insert.setString(1, resultSet.getString("CODE"));
                insert.setString(2, resultSet.getString("NAME"));
                insert.setString(3, resultSet.getString("SOCR"));
                insert.setString(4, resultSet.getString("ZIP_CODE"));
                insert.setString(5, resultSet.getString("GNINMB"));
                insert.setString(6, resultSet.getString("UNO"));
                insert.setString(7, resultSet.getString("OCATD"));
                insert.setString(8, resultSet.getString("STATUS"));
                insert.setString(9, "A");

                insert.execute();
                count++;

                if ((new Date().getTime() - time) > 1000*60) {
                    System.out.println("count = " + count);
                    count = 0;
                    time = new Date().getTime();
                }
            }


        } catch (SQLException e) {
        	e.printStackTrace();
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

}
