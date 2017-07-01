package com.hps.july.sync.kladr;

import com.hps.july.core.DB;
import com.hps.july.sync.kladr.model.Kladr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Date: 04.04.2007
 * Time: 18:19:40
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class KladrDAO {
    private DB db;
    private static final String[] EMPTY_PATTERN = new String[]{""};
    public static final String EMPTY_CODE = "0000000000000";

    public KladrDAO(DB db) {
        this.db = db;
    }

    public List getKladrListSortedByName(String pattern, String parentCode) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kl_kladr WHERE code LIKE ? AND code != ? AND recordStatus = 'A' ORDER BY NAME");

            preparedStatement.setString(1, pattern);
            preparedStatement.setString(2, parentCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchKladr(resultSet));
            }

            Collections.sort(result, new Comparator() {
                NameComparator nameComparator = new NameComparator();
                public int compare(Object o1, Object o2) {
                    Kladr kladr1 = (Kladr) o1;
                    Kladr kladr2 = (Kladr) o2;

                    return nameComparator.compare(kladr1.getName(), kladr2.getName());
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

    public List getKladrListSortedByKladr(String pattern, String parentCode) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kl_kladr WHERE code LIKE ? AND code != ? AND recordStatus = 'A' ORDER BY code");

            preparedStatement.setString(1, pattern);
            preparedStatement.setString(2, parentCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            List result = new ArrayList();
            while(resultSet.next()) {
                result.add(fetchKladr(resultSet));
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


    private Kladr fetchKladr(ResultSet resultSet) throws SQLException {
        Kladr kladr = new Kladr();

        kladr.setCode(resultSet.getString("CODE"));
        kladr.setName(resultSet.getString("NAME"));
        kladr.setStatus(resultSet.getString("STATUS"));
        kladr.setSocr(resultSet.getString("SOCR"));

        return kladr;
    }


    public static String getParent(String code) {
        if (!"000".equals(code.substring(8, 11))) {
            return code.substring(0, 8) + "000" + code.substring(11);
        }
        if (!"000".equals(code.substring(5, 8))) {
            return code.substring(0, 5) + "000" + code.substring(8);
        }
        if (!"000".equals(code.substring(2, 5))) {
            return code.substring(0, 2) + "000" + code.substring(5 );
        }

        return KladrDAO.EMPTY_CODE;
    }

    public static String[] getPattern(int level, String code) {
        switch(level) {
            case NriDAO.LEVEL_PROVINCE:
                return new String[]{"__00000000000"};
            case NriDAO.LEVEL_REGION:
                return new String[]{code.substring(0, 2) + "___" + "000" + "000" + "00",
                                    code.substring(0, 2) + "000" + "___" + "000" + "00",
                                    code.substring(0, 2) + "000" + "000" + "___" + "00"};
            case NriDAO.LEVEL_CITY:
                if ("000".equals(code.substring(2, 5))) {
                    if ("000".equals(code.substring(5, 8))) {
                        return EMPTY_PATTERN;
                    }

                    return new String[]{code.substring(0, 8) + "___" + "00"};
                } else {
                    return new String[]{code.substring(0, 5) + "000" + "___" + "00", code.substring(0, 5) + "___" + "000" + "00"};
                }
            case NriDAO.LEVEL_SUBCITY:
                if (!"000".equals(code.substring(2, 5)) && !"000".equals(code.substring(5, 8))) {
                    return new String[]{code.substring(0, 8) + "___" + "00"};
                }

                return EMPTY_PATTERN;
            default:
                throw new IllegalArgumentException("wrong level");
        }
    }

    public Kladr findByCode(String parentCode) {
        Connection connection = DB.getConnection(db);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM kl_kladr WHERE code = ? AND recordStatus = 'A'");

            preparedStatement.setString(1, parentCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return fetchKladr(resultSet);
            }

            return fetchKladr(resultSet);
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
}