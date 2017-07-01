package com.hps.july.sync.kladr;

import com.hps.july.core.DB;
import com.hps.july.sync.kladr.model.AdminRegion;
import com.hps.july.sync.kladr.model.Kladr;
import com.hps.july.sync.kladr.model.Position;
import com.hps.july.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Date: 04.04.2007
 * Time: 18:20:10
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class NriDAO {
    public static final int LEVEL_EMPTY = -1;
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_REGION = 1;
    public static final int LEVEL_CITY = 2;
    public static final int LEVEL_SUBCITY = 3;

    public static final int RUSSIAN_ID = 0;

    private DB db;
    private static final int TYPE_CITY = 1000;
    private static final int TYPE_REGION = 100;
    private static final int TYPE_PROVINCE = 3;

    public NriDAO(DB db) {
        this.db = db;
    }

    public List getAllKladrList(int parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE parent_regionid = ? AND recordStatus = 'A' ORDER BY regionname");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode IS NULL");
//            preparedStatement.setInt(1, parentRegionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchAdminRegion(resultSet));
            }

            Collections.sort(result, new Comparator() {
                NameComparator nameComparator = new NameComparator();
                public int compare(Object o1, Object o2) {
                    AdminRegion adminRegion1 = (AdminRegion) o1;
                    AdminRegion adminRegion2 = (AdminRegion) o2;

                    return nameComparator.compare(adminRegion1.getRegionName(), adminRegion2.getRegionName());
                }
            });

            return result;
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
                e.printStackTrace();
            }
        }
    }

    public List getKladrListSortedByName(int parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode IS NULL AND parent_regionid = ? AND recordStatus = 'A' ORDER BY regionname");
            preparedStatement.setInt(1, parentRegionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchAdminRegion(resultSet));
            }

            Collections.sort(result, new Comparator() {
                NameComparator nameComparator = new NameComparator();
                public int compare(Object o1, Object o2) {
                    AdminRegion adminRegion1 = (AdminRegion) o1;
                    AdminRegion adminRegion2 = (AdminRegion) o2;

                    return nameComparator.compare(adminRegion1.getRegionName(), adminRegion2.getRegionName());
                }
            });

            return result;
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
                e.printStackTrace();
            }
        }
    }

    public List getKladrListSortedByKladrCode(int parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode IS NOT NULL AND kladrcode LIKE '%00' AND parent_regionid = ?  AND recordStatus = 'A' ORDER BY kladrcode ");
            preparedStatement.setInt(1, parentRegionId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchAdminRegion(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
                e.printStackTrace();
            }
        }
    }

    private AdminRegion fetchAdminRegion(ResultSet resultSet) throws SQLException {
        AdminRegion adminRegion = new AdminRegion();

        adminRegion.setRegionId(new Integer(resultSet.getInt("regionid")));
        adminRegion.setRegionName(resultSet.getString("regionname"));

        int parentId = resultSet.getInt("parent_regionid");
        if (resultSet.wasNull()) {
            adminRegion.setParentRegionId(null);
        } else {
            adminRegion.setParentRegionId(new Integer(parentId));
        }

        adminRegion.setKladrCode(resultSet.getString("kladrcode"));

        return adminRegion;
   }

   public void setKladrCode(Integer regionId, String code) {
       System.out.println("KladrFacadeAdapter.setKladrCode");
       System.out.println("regionId = " + regionId);
       System.out.println("code = " + code);

       Connection connection = DB.getConnection(db);
       try {
           PreparedStatement statement = connection.prepareStatement("UPDATE adminregions SET kladrcode = ? WHERE regionid = ?");
           statement.setString(1, code);
           statement.setInt(2, regionId.intValue());

           statement.execute();
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

    public static int getNextLevel(int level) {
        if (level == LEVEL_SUBCITY) {
            return NriDAO.LEVEL_EMPTY;
        }

        return ++level;
    }

    public void deleteAdminRegion(Integer id) throws SQLException {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM adminregions WHERE regionid = ?");
            preparedStatement.setInt(1, id.intValue());
            preparedStatement.execute();
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new KladrException(e);
            }
        }
    }

    public AdminRegion insertAdminRegion(int parentRegionId, Kladr kladr, int level) {
        System.out.println("NriDAO.insertAdminRegion");
        System.out.println("kladr = " + kladr);
        System.out.println("parentRegionId = " + parentRegionId);

        return insertAdminRegion(kladr.getName() + " " + kladr.getSocr(), kladr.getCode(), getRegionType(level), parentRegionId);

    }

    public AdminRegion insertAdminRegion(String name, String code, int regionType, int parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = getInsertRegionStatement(connection);
            return insertAdminRegion(connection, preparedStatement, name, code, regionType, parentRegionId);
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

    private PreparedStatement getInsertRegionStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO adminregions(regionid, regionname, regiontype, parent_regionid, recordstatus, kladrcode) VALUES(?, ?, ?, ?, ?, ?)");
        return preparedStatement;
    }

    private AdminRegion insertAdminRegion(Connection connection, PreparedStatement preparedStatement, String name, String code, int regionType, int parentRegionId) {
        if (parentRegionId == -1) {
            return null;
        }

        try {
            Integer id = getId(connection);

            preparedStatement.setInt(1, id.intValue());
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, regionType);
            preparedStatement.setInt(4, parentRegionId);
            preparedStatement.setString(5, "A");
            preparedStatement.setString(6, code);

            preparedStatement.execute();

            return new AdminRegion(id, name, code, new Integer(parentRegionId));

        } catch (SQLException e) {
            throw new KladrException(e);
        }
    }

    private Integer getId() {
        Connection connection = DB.getConnection(db);
        try {
            return getId(connection);
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
            }
        }
    }

    private Integer getId(Connection connection) throws SQLException {
        return Utils.getId("tables.adminregions", connection);
    }

    public static int getRegionType(int level) {
        switch(level) {
            case NriDAO.LEVEL_PROVINCE:
                return 3;
            case NriDAO.LEVEL_REGION:
                return 100;
            case NriDAO.LEVEL_CITY:
            case NriDAO.LEVEL_SUBCITY:
                return 1000;
            default:
                throw new IllegalArgumentException("wrong level");
        }
    }

    public void markDeleted() {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE adminregions SET recordStatus = 'D' WHERE kladrcode = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT code FROM kl_kladr WHERE recordStatus = 'D'");

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

    public int getRussianId() {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT intvalue FROM namedvalues WHERE id = 'ADMINREGION_RUSSIA'");
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new IllegalStateException("Can't get constant");
            }

            return resultSet.getInt("intvalue");
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

    public List getRussianPositionList() {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, r.* " +
                                                                      "FROM positions p " +
                                                                      "JOIN regions r ON p.regionid = r.regionid " +
                                                                      "JOIN superregions s ON r.supregid = s.supregid " +
                                                                      "WHERE s.country = 1");
            ResultSet resultSet = statement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()){
                result.add(fetchPosition(resultSet));
            }

            return result;
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

    private Position fetchPosition(ResultSet resultSet) throws SQLException {
        Position position = new Position();

        position.setAddressSteet(resultSet.getString("address_street"));
        position.setStoragePlace(new Integer(resultSet.getInt("storageplace")));
        position.setAdminRegionId(new Integer(resultSet.getInt("settlementid")));
        position.setAdminRegionKladrCode(resultSet.getString("kladrcode"));

        return position;
    }

    public List getAllRegionWithNullKladr() {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode IS NULL AND recordstatus = 'A' ORDER BY regionid");
            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchAdminRegion(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new KladrException(e);
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                //ignore
                e.printStackTrace();
            }
        }
    }

    public void updateRegion(int regionId, int parentRegionId, int regionType, String kladr) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE adminregions SET parent_regionid = ?, regionType = ?, kladrcode = ? WHERE regionid = ?");

            updateStatement.setInt(1, parentRegionId);
            updateStatement.setInt(2, regionType);
            updateStatement.setString(3, kladr);
            updateStatement.setInt(4, regionId);

            updateStatement.execute();
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

    public AdminRegion findByParentAndName(int parentRegionId, String name) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT " +
                                                                            "    ar2.* " +
                                                                            "FROM adminregions ar1 " +
                                                                            "JOIN adminregions ar2 ON ar2.parent_regionid = ar1.parent_regionid " +
                                                                            "WHERE ar2.kladrcode IS NULL " +
                                                                            "  AND ar1.regionid = ? " +
                                                                            "  AND LOWER(ar2.regionname) LIKE LOWER(?)");

            selectStatement.setInt(1, parentRegionId);
            selectStatement.setString(2, name.trim());

            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            AdminRegion adminRegion = fetchAdminRegion(resultSet);

            //I know about ResultSet#isLast() function, but informix driver doesn't support it.
            if (resultSet.next()) {
                return null;
            }

            return adminRegion;

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

    public List getAllRegions() {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement select =  connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode LIKE '%00' AND recordstatus = 'A'");
            ResultSet resultSet = select.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchAdminRegion(resultSet));
            }

            return result;
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

    public AdminRegion findByKladrCode(String code) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE kladrcode = ? AND recordstatus = 'A'");
            selectStatement.setString(1, code);

            ResultSet resultSet = selectStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            return fetchAdminRegion(resultSet);
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

    public void updateRegion(int regionId, int parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE adminregions SET parent_regionid = ? WHERE regionid = ?");

            updateStatement.setInt(1, parentRegionId);
            updateStatement.setInt(2, regionId);

            updateStatement.execute();
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
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement select = connection.prepareStatement("SELECT " +
                                                                   "    a.regionid, (TRIM(k.name) || ' ' || TRIM(k.socr)) as name " +
                                                                   "FROM " +
                                                                   "     adminregions a " +
                                                                   "JOIN kl_kladr k ON a.kladrcode = k.code " +
                                                                   "WHERE " +
                                                                   "     a.regionname <> (TRIM(k.name) || ' ' || TRIM(k.socr)) " +
                                                                   " AND a.recordstatus = 'A' " +
                                                                   " AND k.recordstatus = 'A' ");
            ResultSet resultSet = select.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()){
                Object[] updateObject = new Object[2];
                updateObject[0] = new Integer(resultSet.getInt("regionid"));
                updateObject[1] = resultSet.getString("name").trim();

                result.add(updateObject);
            }

            resultSet.close();
            select.close();

            System.out.println("NEED UPDATE " + result.size());
            PreparedStatement update = connection.prepareStatement("UPDATE adminregions SET regionname = ? WHERE regionid = ?");
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
        List adminRegions = getAllRegions();
        Map regionByKlad = new HashMap();
        for (int i = 0; i < adminRegions.size(); i++) {
            AdminRegion adminRegion = (AdminRegion) adminRegions.get(i);
            regionByKlad.put(adminRegion.getKladrCode(), adminRegion);
        }

        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement select = connection.prepareStatement("SELECT " +
                                                                   "    code, (TRIM(k.name) || ' ' || TRIM(k.socr)) as name " +
                                                                   "FROM " +
                                                                   "            kl_kladr k " +
                                                                   "WHERE " +
                                                                   "    k.recordstatus = 'A' " +
                                                                   "AND k.code LIKE '%00' " +
                                                                   "AND NOT EXISTS (SELECT * FROM adminregions WHERE kladrcode = k.code) " +
                                                                   "ORDER BY code");
            ResultSet resultSet = select.executeQuery();

            PreparedStatement insert = getInsertRegionStatement(connection);
            int i = 0;
            while(resultSet.next()){
                if (++i % 100 == 0) {
                    System.out.println(i + " - processed");
                }

                AdminRegion newRegion = insertAdminRegion(connection, insert, resultSet.getString("name"), resultSet.getString("code"), getType(resultSet.getString("code")), getParentId(resultSet.getString("code"), regionByKlad));
                if (newRegion != null) {
                    regionByKlad.put(newRegion.getKladrCode(), newRegion);
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

    private int getParentId(String childCode, Map regionByKlad) {
        String parentCode = KladrDAO.getParent(childCode);

        AdminRegion adminRegion = (AdminRegion) regionByKlad.get(parentCode);
        if (adminRegion == null) {
            return -1;
        }

        return adminRegion.getRegionId().intValue();


    }

    private int getType(String code) {
        if (!"000".equals(code.substring(8, 11)) || !"000".equals(code.substring(5, 8))) {
            return TYPE_CITY;
        }

        if (!"000".equals(code.substring(2, 5))) {
            return TYPE_REGION;
        }

        return TYPE_PROVINCE;

    }

    public void setDeleted(Integer regionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE adminregions SET recordStatus = 'D' WHERE regionid = ?");
            updateStatement.setInt(1, regionId.intValue());
            updateStatement.execute();
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

    public boolean isRoot(AdminRegion adminRegion, Integer russianId) {
        if (russianId.equals(adminRegion.getParentRegionId())){
            return true;
        }

        if (adminRegion.getParentRegionId() == null) {
            return false;
        }

        return isRoot(findById(adminRegion.getParentRegionId()), russianId);
    }

    private AdminRegion findById(Integer parentRegionId) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM adminregions WHERE regionid = ?");
            selectStatement.setInt(1, parentRegionId.intValue());

            ResultSet resultSet = selectStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            return fetchAdminRegion(resultSet);
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
}