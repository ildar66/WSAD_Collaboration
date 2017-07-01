/*
 * Created on 10.11.2006
 *
 */
package com.hps.july.sync.nritndatacomm;

import com.hps.july.core.DB;
import com.hps.july.core.RowDBObject;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author ABaykov
 *
 */
public class SitesForTnAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("N", "N", true);
			addMap("DT", "DT", false);
			addMap("BS_Numb", "BS_Numb", false);
			addMap("X", "X", false);
			addMap("Y", "Y", false);
			addMap("Mode", "Mode", false);
			//Колонки, предопределенные в таблице NRI
//			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public SitesForTnAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "Sites_For_TN", "Sites_For_TN_nri", null, new ResourceTypesMap(), dbSource);
	}

    /**
     * Add log message into MAPS_DATA DB.
     * That log need in TN system.
     *
     * @param objJOIN_db
     * @return
     */
    public boolean processRow(RowDBObject objJOIN_db) {
        Connection connection = null;
        try {
            connection = DB.getConnection(getTargetDb());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NRI_TransportNet([From], [FOR], DT_Set, DT_Get, Job_Name, Job_Type, N) " +
                                                                              "VALUES(?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, "NRI");
            preparedStatement.setString(2, "TN");
            preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
            preparedStatement.setString(5, "Collaboration");
            preparedStatement.setString(6, "RPL");
            preparedStatement.setInt(7, ((Integer)objJOIN_db.getKeysColumns().get("n")).intValue());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("SqlException message is - " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
					throw new RuntimeException("SqlException message is - " + e.getMessage());
                }
            }
        }
    }
}
