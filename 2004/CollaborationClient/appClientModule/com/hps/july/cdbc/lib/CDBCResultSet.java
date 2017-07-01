package com.hps.july.cdbc.lib;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
 
/**
 * Результаты отработки запроса.
 * Creation date: (10.03.2004 13:53:20)
 * @author: Dmitry Dneprov
 */
public class CDBCResultSet {
	HashMap headers = new HashMap();
	//LinkedList results = new LinkedList();
	ArrayList results = new ArrayList();
	private int calculatedColumnsCount;
	private boolean queryExecuted;
	private int errorCode;
	
/**
 * CDBCResultSet constructor.
 */
public CDBCResultSet() {
	super();
	calculatedColumnsCount = 0;
	queryExecuted = false;
	errorCode = 0;
}

/**
 * Добавляет вычисляемую колонку в результат.
 * Creation date: (10.03.2004 14:40:27)
 * @param colName String
 */
public void addCalculatedColumn(String colName) throws java.lang.IllegalStateException {
	if (queryExecuted)
		throw new java.lang.IllegalStateException("Query already executed");
	int index = headers.size();
	headers.put(colName, new Integer(index));
	calculatedColumnsCount++;
}
/**
 * Добавляет вычисляемые колонки в строку ответа.
 * Creation date: (16.03.2004 11:51:33)
 */
private void addCalculatedColumns(CDBCRowObject ro) throws java.sql.SQLException {
			for (int i = 0; i < calculatedColumnsCount; i++) {
				ro.addColumn(null);
			}
}

/**
 * Этот метод выполняет запрос к БД и выбирает результаты c ограничением выборки.
 * Creation date: (10.03.2004 14:24:04)
 */
public void executeQuery(Connection con, String acommand, Object [] aparams, int amaxRows) throws java.lang.IllegalStateException {
	if (queryExecuted)
		throw new java.lang.IllegalStateException("Query already executed");

	//System.out.println(	"CDBC SQL:" + acommand );
	//TimeCounter tc = new TimeCounter("CDBC");
	//tc.start();
	queryExecuted = true;
		
	try {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ResultSetMetaData rmd = null;
		try {
			pst  = con.prepareStatement(acommand);
			// Set query params
			setQueryParams(pst, aparams);
			
			rs = pst.executeQuery();
			rmd = rs.getMetaData();

			makeColumnNames(rmd);
			
			// Extract results
			int colCount = rmd.getColumnCount();
			int resRowsCount = 0;
			//tc.finish("Before firstnext");
			//tc.start();
			while (rs.next()) {
				CDBCRowObject ro = new CDBCRowObject(headers);
				addCalculatedColumns(ro);
				try {
					for (int i=1; i <= colCount; i++) 
						ro.addColumn(rs.getObject(i));
					results.add(ro);
					resRowsCount++;
				} catch (SQLException e) {
					System.out.println("ERROR SQLCode=" + e.getErrorCode() + ", message=" + e.getMessage());
					System.out.println(	"CDBC SQL:" + acommand );
				}
				if (amaxRows != 0 && resRowsCount >= amaxRows)
					break;
			}
			//tc.finish("After lastnext");
			setErrorCode(0);
		} finally {
			try {
				pst.close();
			} catch (Exception e) {
				// Ignore error
			}
		}
    } catch (SQLException e) {
		setErrorCode(e.getErrorCode());
        System.out.println("ERROR SQLCode=" + e.getErrorCode() + ", message=" + e.getMessage());
		System.out.println(	"CDBC SQL:" + acommand );
    } catch (Exception ee) {
		setErrorCode(-1);
        System.out.println("ERROR message=" + ee.getMessage());
	}
}

    /**
     * Этот метод выполняет запрос на обновление к БД.
     * Creation date: (10.03.2004 14:24:04)
     */
    public boolean executeUpdate(Connection con, String acommand, Object [] aparams) {
        boolean result = false;
        //System.out.println(	"CDBC SQL:" + acommand );
        //TimeCounter tc = new TimeCounter("CDBC");
        //tc.start();

        PreparedStatement pst = null;
        try {
            pst  = con.prepareStatement(acommand);
            // Set query params
            setQueryParams(pst, aparams);
            pst.executeUpdate();
            result = true;
            setErrorCode(0);
            //tc.finish("After update");
        } catch (SQLException e) {
        	setErrorCode(e.getErrorCode());
            System.out.println("ERROR SQLCode=" + e.getErrorCode() + ", message=" + e.getMessage());
        } catch (Exception ee) {
        	setErrorCode(-1);
            System.out.println("ERROR message=" + ee.getMessage());
        } finally {
            try {pst.close();} catch (Exception e) {}
        }
        return result;
    }

/**
 * Возвращает количество строк в результате.
 * Creation date: (10.03.2004 14:42:21)
 * @return int
 */
public int getRowsCount() {
	return results.size();
}
/**
 * Возвращает итератор для хождения по строкам запроса.
 * Creation date: (16.03.2004 11:51:33)
 */
 public ListIterator listIterator() {
		return results.listIterator();
	}
/**
 * Создает имена колонок из метаданных.
 * Creation date: (16.03.2004 11:51:33)
 */
private void makeColumnNames(ResultSetMetaData rmd) throws java.sql.SQLException {
			int colCount = rmd.getColumnCount();
			// Make column names
			for (int i = 0; i < colCount; i++) {
                String colName = rmd.getColumnName(i+1);
				if ("(expression)".equalsIgnoreCase(colName)) {
				   // This is returned from Stored Procedure - Change to 'colNNN'
				   String newColName = "col" + (i+1);
				   headers.put(newColName, new Integer(i + calculatedColumnsCount));
			   } else
			   		headers.put(colName.toLowerCase(), new Integer(i + calculatedColumnsCount));
			}
}
/**
 * Установка параметров запроса.
 * Creation date: (10.03.2004 14:58:39)
 * @param apst java.sql.PreparedStatement
 * @param aparams java.sql.Array
 */
private void setQueryParams(PreparedStatement apst, Object [] aparams) throws java.sql.SQLException {
	if (aparams != null) {
		for (int i=0; i< aparams.length; i++) {
			apst.setObject(i+1, aparams [i]);
		}
	}
}
	/**
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param i
	 */
	public void setErrorCode(int i) {
		errorCode = i;
	}

}

