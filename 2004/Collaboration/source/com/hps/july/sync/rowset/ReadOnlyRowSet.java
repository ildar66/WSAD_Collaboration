package com.hps.july.sync.rowset;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.Calendar;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.RowSetListener;

/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReadOnlyRowSet implements RowSet, Serializable {
	private Object[] dataRows;

	/**
	 * Constructor for ReadOnlyRowSet.
	 */
	public ReadOnlyRowSet() {
		super();
	}

	/**
	 * @see javax.sql.RowSet#addRowSetListener(RowSetListener)
	 */
	public void addRowSetListener(RowSetListener arg0) {
	}

	/**
	 * @see javax.sql.RowSet#clearParameters()
	 */
	public void clearParameters() throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#execute()
	 */
	public void execute() throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#getCommand()
	 */
	public String getCommand() {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#getDataSourceName()
	 */
	public String getDataSourceName() {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#getEscapeProcessing()
	 */
	public boolean getEscapeProcessing() throws SQLException {
		return false;
	}

	/**
	 * @see javax.sql.RowSet#getMaxFieldSize()
	 */
	public int getMaxFieldSize() throws SQLException {
		return 0;
	}

	/**
	 * @see javax.sql.RowSet#getMaxRows()
	 */
	public int getMaxRows() throws SQLException {
		return 0;
	}

	/**
	 * @see javax.sql.RowSet#getPassword()
	 */
	public String getPassword() {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#getQueryTimeout()
	 */
	public int getQueryTimeout() throws SQLException {
		return 0;
	}

	/**
	 * @see javax.sql.RowSet#getTransactionIsolation()
	 */
	public int getTransactionIsolation() {
		return 0;
	}

	/**
	 * @see javax.sql.RowSet#getTypeMap()
	 */
	public Map getTypeMap() throws SQLException {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#getUrl()
	 */
	public String getUrl() throws SQLException {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#getUsername()
	 */
	public String getUsername() {
		return null;
	}

	/**
	 * @see javax.sql.RowSet#isReadOnly()
	 */
	public boolean isReadOnly() {
		return true;
	}

	/**
	 * @see javax.sql.RowSet#removeRowSetListener(RowSetListener)
	 */
	public void removeRowSetListener(RowSetListener arg0) {
	}

	/**
	 * @see javax.sql.RowSet#setArray(int, Array)
	 */
	public void setArray(int arg0, Array arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setAsciiStream(int, InputStream, int)
	 */
	public void setAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setBigDecimal(int, BigDecimal)
	 */
	public void setBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setBinaryStream(int, InputStream, int)
	 */
	public void setBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setBlob(int, Blob)
	 */
	public void setBlob(int arg0, Blob arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setBoolean(int, boolean)
	 */
	public void setBoolean(int arg0, boolean arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setByte(int, byte)
	 */
	public void setByte(int arg0, byte arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setBytes(int, byte[])
	 */
	public void setBytes(int arg0, byte[] arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setCharacterStream(int, Reader, int)
	 */
	public void setCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setClob(int, Clob)
	 */
	public void setClob(int arg0, Clob arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setCommand(String)
	 */
	public void setCommand(String arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setConcurrency(int)
	 */
	public void setConcurrency(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setDataSourceName(String)
	 */
	public void setDataSourceName(String arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setDate(int, Date)
	 */
	public void setDate(int arg0, Date arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setDate(int, Date, Calendar)
	 */
	public void setDate(int arg0, Date arg1, Calendar arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setDouble(int, double)
	 */
	public void setDouble(int arg0, double arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setEscapeProcessing(boolean)
	 */
	public void setEscapeProcessing(boolean arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setFloat(int, float)
	 */
	public void setFloat(int arg0, float arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setInt(int, int)
	 */
	public void setInt(int arg0, int arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setLong(int, long)
	 */
	public void setLong(int arg0, long arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setMaxFieldSize(int)
	 */
	public void setMaxFieldSize(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setMaxRows(int)
	 */
	public void setMaxRows(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setNull(int, int)
	 */
	public void setNull(int arg0, int arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setNull(int, int, String)
	 */
	public void setNull(int arg0, int arg1, String arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setObject(int, Object)
	 */
	public void setObject(int arg0, Object arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setObject(int, Object, int)
	 */
	public void setObject(int arg0, Object arg1, int arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setObject(int, Object, int, int)
	 */
	public void setObject(int arg0, Object arg1, int arg2, int arg3) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setPassword(String)
	 */
	public void setPassword(String arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setQueryTimeout(int)
	 */
	public void setQueryTimeout(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean arg0) throws SQLException {
		throw new SQLException("ReadOnlyRowSet: Method not supported");
	}

	/**
	 * @see javax.sql.RowSet#setRef(int, Ref)
	 */
	public void setRef(int arg0, Ref arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setShort(int, short)
	 */
	public void setShort(int arg0, short arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setString(int, String)
	 */
	public void setString(int arg0, String arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTime(int, Time)
	 */
	public void setTime(int arg0, Time arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTime(int, Time, Calendar)
	 */
	public void setTime(int arg0, Time arg1, Calendar arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTimestamp(int, Timestamp)
	 */
	public void setTimestamp(int arg0, Timestamp arg1) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTimestamp(int, Timestamp, Calendar)
	 */
	public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setType(int)
	 */
	public void setType(int arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setTypeMap(Map)
	 */
	public void setTypeMap(Map arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setUrl(String)
	 */
	public void setUrl(String arg0) throws SQLException {
	}

	/**
	 * @see javax.sql.RowSet#setUsername(String)
	 */
	public void setUsername(String arg0) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#next()
	 */
	public boolean next() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#close()
	 */
	public void close() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#wasNull()
	 */
	public boolean wasNull() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#getString(int)
	 */
	public String getString(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBoolean(int)
	 */
	public boolean getBoolean(int columnIndex) throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#getByte(int)
	 */
	public byte getByte(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getShort(int)
	 */
	public short getShort(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getInt(int)
	 */
	public int getInt(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getLong(int)
	 */
	public long getLong(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getFloat(int)
	 */
	public float getFloat(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getDouble(int)
	 */
	public double getDouble(int columnIndex) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getBigDecimal(int, int)
	 * @deprecated
	 */
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBytes(int)
	 */
	public byte[] getBytes(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getDate(int)
	 */
	public Date getDate(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTime(int)
	 */
	public Time getTime(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTimestamp(int)
	 */
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getAsciiStream(int)
	 */
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getUnicodeStream(int)
	 * @deprecated
	 */
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBinaryStream(int)
	 */
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getString(String)
	 */
	public String getString(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBoolean(String)
	 */
	public boolean getBoolean(String columnName) throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#getByte(String)
	 */
	public byte getByte(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getShort(String)
	 */
	public short getShort(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getInt(String)
	 */
	public int getInt(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getLong(String)
	 */
	public long getLong(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getFloat(String)
	 */
	public float getFloat(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getDouble(String)
	 */
	public double getDouble(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getBigDecimal(String, int)
	 * @deprecated
	 */
	public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBytes(String)
	 */
	public byte[] getBytes(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getDate(String)
	 */
	public Date getDate(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTime(String)
	 */
	public Time getTime(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTimestamp(String)
	 */
	public Timestamp getTimestamp(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getAsciiStream(String)
	 */
	public InputStream getAsciiStream(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getUnicodeStream(String)
	 * @deprecated
	 */
	public InputStream getUnicodeStream(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBinaryStream(String)
	 */
	public InputStream getBinaryStream(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#getCursorName()
	 */
	public String getCursorName() throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getMetaData()
	 */
	public ResultSetMetaData getMetaData() throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getObject(int)
	 */
	public Object getObject(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getObject(String)
	 */
	public Object getObject(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#findColumn(String)
	 */
	public int findColumn(String columnName) throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getCharacterStream(int)
	 */
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getCharacterStream(String)
	 */
	public Reader getCharacterStream(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBigDecimal(int)
	 */
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBigDecimal(String)
	 */
	public BigDecimal getBigDecimal(String columnName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#isBeforeFirst()
	 */
	public boolean isBeforeFirst() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#isAfterLast()
	 */
	public boolean isAfterLast() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#isFirst()
	 */
	public boolean isFirst() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#isLast()
	 */
	public boolean isLast() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#beforeFirst()
	 */
	public void beforeFirst() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#afterLast()
	 */
	public void afterLast() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#first()
	 */
	public boolean first() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#last()
	 */
	public boolean last() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#getRow()
	 */
	public int getRow() throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#absolute(int)
	 */
	public boolean absolute(int row) throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#relative(int)
	 */
	public boolean relative(int rows) throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#previous()
	 */
	public boolean previous() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#setFetchDirection(int)
	 */
	public void setFetchDirection(int direction) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#getFetchDirection()
	 */
	public int getFetchDirection() throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#setFetchSize(int)
	 */
	public void setFetchSize(int rows) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#getFetchSize()
	 */
	public int getFetchSize() throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getType()
	 */
	public int getType() throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#getConcurrency()
	 */
	public int getConcurrency() throws SQLException {
		return 0;
	}

	/**
	 * @see java.sql.ResultSet#rowUpdated()
	 */
	public boolean rowUpdated() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#rowInserted()
	 */
	public boolean rowInserted() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#rowDeleted()
	 */
	public boolean rowDeleted() throws SQLException {
		return false;
	}

	/**
	 * @see java.sql.ResultSet#updateNull(int)
	 */
	public void updateNull(int columnIndex) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBoolean(int, boolean)
	 */
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateByte(int, byte)
	 */
	public void updateByte(int columnIndex, byte x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateShort(int, short)
	 */
	public void updateShort(int columnIndex, short x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateInt(int, int)
	 */
	public void updateInt(int columnIndex, int x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateLong(int, long)
	 */
	public void updateLong(int columnIndex, long x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateFloat(int, float)
	 */
	public void updateFloat(int columnIndex, float x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateDouble(int, double)
	 */
	public void updateDouble(int columnIndex, double x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBigDecimal(int, BigDecimal)
	 */
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateString(int, String)
	 */
	public void updateString(int columnIndex, String x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBytes(int, byte[])
	 */
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateDate(int, Date)
	 */
	public void updateDate(int columnIndex, Date x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateTime(int, Time)
	 */
	public void updateTime(int columnIndex, Time x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateTimestamp(int, Timestamp)
	 */
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateAsciiStream(int, InputStream, int)
	 */
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBinaryStream(int, InputStream, int)
	 */
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateCharacterStream(int, Reader, int)
	 */
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateObject(int, Object, int)
	 */
	public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateObject(int, Object)
	 */
	public void updateObject(int columnIndex, Object x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateNull(String)
	 */
	public void updateNull(String columnName) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBoolean(String, boolean)
	 */
	public void updateBoolean(String columnName, boolean x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateByte(String, byte)
	 */
	public void updateByte(String columnName, byte x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateShort(String, short)
	 */
	public void updateShort(String columnName, short x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateInt(String, int)
	 */
	public void updateInt(String columnName, int x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateLong(String, long)
	 */
	public void updateLong(String columnName, long x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateFloat(String, float)
	 */
	public void updateFloat(String columnName, float x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateDouble(String, double)
	 */
	public void updateDouble(String columnName, double x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBigDecimal(String, BigDecimal)
	 */
	public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateString(String, String)
	 */
	public void updateString(String columnName, String x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBytes(String, byte[])
	 */
	public void updateBytes(String columnName, byte[] x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateDate(String, Date)
	 */
	public void updateDate(String columnName, Date x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateTime(String, Time)
	 */
	public void updateTime(String columnName, Time x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateTimestamp(String, Timestamp)
	 */
	public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateAsciiStream(String, InputStream, int)
	 */
	public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateBinaryStream(String, InputStream, int)
	 */
	public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateCharacterStream(String, Reader, int)
	 */
	public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateObject(String, Object, int)
	 */
	public void updateObject(String columnName, Object x, int scale) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateObject(String, Object)
	 */
	public void updateObject(String columnName, Object x) throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#insertRow()
	 */
	public void insertRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#updateRow()
	 */
	public void updateRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#deleteRow()
	 */
	public void deleteRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#refreshRow()
	 */
	public void refreshRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#cancelRowUpdates()
	 */
	public void cancelRowUpdates() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#moveToInsertRow()
	 */
	public void moveToInsertRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#moveToCurrentRow()
	 */
	public void moveToCurrentRow() throws SQLException {
	}

	/**
	 * @see java.sql.ResultSet#getStatement()
	 */
	public Statement getStatement() throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getObject(int, Map)
	 */
	public Object getObject(int i, Map map) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getRef(int)
	 */
	public Ref getRef(int i) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBlob(int)
	 */
	public Blob getBlob(int i) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getClob(int)
	 */
	public Clob getClob(int i) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getArray(int)
	 */
	public Array getArray(int i) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getObject(String, Map)
	 */
	public Object getObject(String colName, Map map) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getRef(String)
	 */
	public Ref getRef(String colName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getBlob(String)
	 */
	public Blob getBlob(String colName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getClob(String)
	 */
	public Clob getClob(String colName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getArray(String)
	 */
	public Array getArray(String colName) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getDate(int, Calendar)
	 */
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getDate(String, Calendar)
	 */
	public Date getDate(String columnName, Calendar cal) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTime(int, Calendar)
	 */
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTime(String, Calendar)
	 */
	public Time getTime(String columnName, Calendar cal) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTimestamp(int, Calendar)
	 */
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		return null;
	}

	/**
	 * @see java.sql.ResultSet#getTimestamp(String, Calendar)
	 */
	public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
		return null;
	}
	/** Populates the rowset without the first startRow rows 
	 * of the ResultSet and with a maximum number
	 * of rows specified by howManyRows 
	 */
	public void populate(ResultSet resultSet, int startRow, int howManyRows) throws SQLException {

		// miscellaneous code not shown for brevity:

		// Create a list to hold the row values
		List dataRows = new LinkedList();

		// determine the number of columns from the mete data
		int numberOfColumns = resultSet.getMetaData().getColumnCount();

		// Discard initial rows if beginAtRow was specified
		setStartPosition(startRow, resultSet);

		// if number of rows is unspecified, 
		// get all rows from resultset
		if (howManyRows <= 0) {
			howManyRows = Integer.MAX_VALUE;
		}
		int processedRows = 0;
		while ((resultSet.next()) && (processedRows++ < howManyRows)) {
			Object[] values = new Object[numberOfColumns];

			// Read values for current row and save
			// them in the values array
			for (int i = 0; i < numberOfColumns; i++) {
				Object columnValue = this.getColumnValue(resultSet, i);
				values[i] = columnValue;
			}

			// Add the array of values to the linked list
			dataRows.add(values);
		}
		this.dataRows = dataRows.toArray();
	}

	// sets the result set to start at the given row number
	private void setStartPosition(int startAtRow, ResultSet resultSet) throws SQLException {
		if (startAtRow > 0) {
			if (resultSet.getType() != ResultSet.TYPE_FORWARD_ONLY) {
				// Move the cursor using JDBC 2.0 API
				if (!resultSet.absolute(startAtRow)) {
					resultSet.last();
				}
			} else {
				// If the result set does not support JDBC 2.0
				// skip the first beginAtRow rows
				for (int i = 0; i < startAtRow; i++) {
					if (!resultSet.next()) {
						resultSet.last();
						break;
					}
				}
			}
		}
	}
	/**
	 * Reads a column value for the current row and
	 * create an appropriate java object to hold it.
	 * Return null if error reading value or for SQL null.
	 */
	private Object getColumnValue(ResultSet resultSet, int columnIndex) throws SQLException {
		return resultSet.getObject(columnIndex);
	}

}
