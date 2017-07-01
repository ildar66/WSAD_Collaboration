package com.hps.july.sync.kladr;

import com.hps.july.core.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Date: 03.05.2007
 * Time: 18:52:40
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class StreetDAO {
    private DB nri;

    public StreetDAO(DB nri) {
        this.nri = nri;
    }

    public void markDeletedWithNullKladr() {
        Connection connection = DB.getConnection(nri);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT streetid FROM streets WHERE NOT EXISTS(SELECT * FROM kl_street WHERE code = kladrstreet)");

            Set streets = new HashSet();
            ResultSet resultSet  = preparedStatement.executeQuery();
            while(resultSet.next()) {
                streets.add(new Integer(resultSet.getInt("streetid")));
            }
            preparedStatement.close();

            PreparedStatement updateStatement = connection.prepareStatement("UPDATE streets SET recordStatus = 'D' WHERE streetid = ?");
            Iterator iStreets = streets.iterator();
            while (iStreets.hasNext()) {
                Integer streetId = (Integer) iStreets.next();
                updateStatement.setInt(1, streetId.intValue());
            }

        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    public void markDeleted() {
        Connection connection = DB.getConnection(nri);
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE streets SET recordStatus = 'D' WHERE kladrstreet = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT code FROM kl_street WHERE recordStatus = 'D'");

            ResultSet resultSet  = preparedStatement.executeQuery();
            while(resultSet.next()) {
                updateStatement.setString(1, resultSet.getString("code"));
                updateStatement.execute();
            }
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    public void updateNames() {
        Connection connection = DB.getConnection(nri);
        try {
            PreparedStatement select = connection.prepareStatement("SELECT " +
                                                                    "s.streetid, TRIM(k.name)||' '||TRIM(k.socr) as name " +
                                                                    "FROM " +
                                                                    "     streets s " +
                                                                    "JOIN kl_street k ON s.kladrstreet = k.code " +
                                                                    "WHERE " +
                                                                    "    TRIM(k.name)||' '||TRIM(k.socr) <> s.streetname " +
                                                                    "AND s.recordstatus = 'A' " +
                                                                    "AND k.recordstatus = 'A'");
            ResultSet resultSet = select.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()){
                Object[] updateObject = new Object[2];
                updateObject[0] = new Integer(resultSet.getInt("streetid"));
                updateObject[1] = resultSet.getString("name").trim();

                result.add(updateObject);
            }

            resultSet.close();
            select.close();

            System.out.println("NEED UPDATE " + result.size());
            PreparedStatement update = connection.prepareStatement("UPDATE streets SET streetname = ? WHERE streetid = ?");
            for (int i = 0; i < result.size(); i++) {
                if (i % 1000 == 0) {
                    System.out.println(i + " - processed");
                }

                Object[] objects = (Object[]) result.get(i);

                update.setString(1, (String) objects[1]);
                update.setInt(2, ((Integer)objects[0]).intValue());

                update.execute();

            }
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    public void insertNew() {
        Connection connection = DB.getConnection(nri);
        try {
            PreparedStatement select = connection.prepareStatement("SELECT " +
                                                                   "    code, (TRIM(k.name) || ' ' || TRIM(k.socr)) as name " +
                                                                   "FROM " +
                                                                   "      kl_street k " +
                                                                   "WHERE " +
                                                                   "    k.recordstatus = 'A' " +
                                                                   "AND k.code LIKE '%00' " +
                                                                   "AND code NOT IN (SELECT kladrstreet FROM streets) " +
                                                                   "ORDER BY code");
            ResultSet resultSet = select.executeQuery();

            int i=0;
            PreparedStatement insert = connection.prepareStatement("INSERT INTO streets(streetid, adminregionid, streetname, kladrstreet, recordstatus) VALUES(?, ?, ?, ?, ?)");
            while(resultSet.next()){
                if (++i % 1000 == 0) {
                    System.out.println(i + " - processed");
                }

                Integer regionId = getAdminRegionId(connection, resultSet.getString("code"));
                if (regionId != null) {
                    insert.setInt(1, getId(connection));
                    insert.setInt(2, regionId.intValue());
                    insert.setString(3, resultSet.getString("name"));
                    insert.setString(4, resultSet.getString("code"));
                    insert.setString(5, "A");

                    insert.execute();
                } else {
                    System.out.println("CAN'T FIND PARENT FOR STREET " + resultSet.getString("code"));
                }
            }
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    private Integer getAdminRegionId(Connection connection, String code) throws SQLException {
        PreparedStatement select = null;
        try {
            select = connection.prepareStatement("SELECT regionid FROM adminregions WHERE kladrcode LIKE ? AND recordStatus = 'A'");
            select.setString(1, code.substring(0, 11) + "00");
            ResultSet resultSet = select.executeQuery();

            if (!resultSet.next()) {
                //throw new IllegalStateException("can't find AdminRegion for street");
                return null;
            }

            return new Integer(resultSet.getInt("regionid"));
        } finally{
            select.close();
        }

    }

    private int getId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("EXECUTE PROCEDURE getsequence(?)");
        preparedStatement.setString(1, "tables.streets");

        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("strange getsequence result");
            }
        } finally{
            resultSet.close();
            preparedStatement.close();
        }
    }
}
