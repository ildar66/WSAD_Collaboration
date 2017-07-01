package com.hps.july.util;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

public class Utils {
	public final static String DEFAULT_RATE = "DEFAULT_RATE";

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);


    public static void closeResultSet(ResultSet rs) {
        if (rs == null) {
            return;
        }

        try {
            rs.close();
        } catch (Exception e) {}
    }

    public static void closeStatement(Statement st) {
        if (st == null) {
            return;
        }

        try {
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Не могу закрыть targetDbConnection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /// Получение целой именнованной константы
	public static int getNamedValueInt(
		Connection con,
		Query qry,
		String argName) {
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(
			con,
			"SELECT intvalue FROM namedvalues WHERE id = ?",
			new Object[] { argName },
			0);
		Integer res = null;
		ListIterator it = rs.listIterator();
		if (it.hasNext()) {
			CDBCRowObject ro = (CDBCRowObject) it.next();
			res = (Integer) ro.getColumn("intvalue").asObject();
		} else {
			QueryProcessing.addLogMessage(con, qry,
				QueryProcessing.MSG_ERROR,
				"Не найдена системная константа: " + argName);
		}
		if (res != null)
			return res.intValue();
		else
			return 0;
	}

    public static String date2String(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date string2Date(String date) throws ParseException {
//        if (date.equals("CURRENT")) {
//            return (new java.util.Date().getTime());
//        }

        return SIMPLE_DATE_FORMAT.parse(date);
    }


    public static void setInt(PreparedStatement statement, int number, Integer parameter) throws SQLException {
        if (parameter == null) {
            statement.setNull(number, Types.INTEGER);
        } else {
            statement.setInt(number, parameter.intValue());
        }

    }

   public static void setDate(PreparedStatement statement, int number, Date date) throws SQLException {
        if (date == null) {
            statement.setNull(number, Types.DATE);
        } else {
            statement.setDate(number, new java.sql.Date(date.getTime()));
        }


   }

    private static int i = 0;
    public static Integer getId(String seqname, Connection connection) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("EXECUTE PROCEDURE getsequence(?)");
//        preparedStatement.setString(1, seqname);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        try {
//            if (resultSet.next()) {
//                return new Integer(resultSet.getInt(1));
//            } else {
//                throw new RuntimeException("strange getsequence result");
//            }
//        } finally{
//            resultSet.close();
//            preparedStatement.close();
//        }

        return new Integer(++i);
    }
}
