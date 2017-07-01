/****** This code is generated automatically by IBM WebSphere Studio ******/
package WhitePages0;

import java.io.PrintStream;
import java.sql.*;
import java.util.Vector;
public class NTLTDatabaseAccessRuntime {
	private static int instCounter;
	private String id;
	private long beginTime;
	private Connection con;
	private static String driverName;
	private ResultSet resultSet;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private int nbOfRowsToFetch;
	private boolean traceOn;
	private int indent;
	private int isolationLevel;
	private boolean autoCommit;
	public NTLTDatabaseAccessRuntime() {
		beginTime = 0L;
		nbOfRowsToFetch = 0;
		traceOn = false;
		isolationLevel = Connection.TRANSACTION_READ_COMMITTED;
		autoCommit = true;
		instCounter++;
		setId("Id_" + instCounter);
		String p = System.getProperty("NTLTTRACE");
		if (p != null && p.toUpperCase().equals("Y"))
			setTraceOn(true);
		setTraceOn(true);
		initialize();
	}
	public int getNbOfRowsToFetch() {
		return nbOfRowsToFetch;
	}
	public void setNbOfRowsToFetch(int nbOfRowsToFetch) {
		this.nbOfRowsToFetch = nbOfRowsToFetch;
	}
	public int getIsolationLevel() {
		return isolationLevel;
	}
	public void setIsolationLevel(int isolationLevel) {
		this.isolationLevel = isolationLevel;
	}
	public boolean getAutoCommit() {
		return autoCommit;
	}
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	public void setTraceOn(boolean b) {
		traceOn = b;
		indent = 0;
	}
	private void insertIndentation() {
		for (int i = 0; i < indent; i++)
			System.out.print("  ");
	}
	private void trace(String mess) {
		if (traceOn) {
			insertIndentation();
			System.out.println(mess);
		}
	}
	private void beginMethodTrace(String mess) {
		if (traceOn) {
			insertIndentation();
			beginTime = System.currentTimeMillis();
			System.out.println("Connection " + getId() + "\nBegin " + mess);
			indent++;
		}
	}
	private void endMethodTrace(String mess) {
		if (traceOn) {
			indent--;
			insertIndentation();
			System.out.println(
				"End "
					+ mess
					+ " - [Time elapsed="
					+ (System.currentTimeMillis() - beginTime)
					+ "msec]\n");
		}
	}
	public Connection getConnection() {
		return null;
	}
	public void commit() throws SQLException {
		beginMethodTrace("commit()");
		if (con != null)
			try {
				con.commit();
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				throw ex;
			}
		endMethodTrace("commit()");
	}
	public void rollback() throws SQLException {
		beginMethodTrace("rollback()");
		if (con != null)
			try {
				con.rollback();
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				throw ex;
			}
		endMethodTrace("rollback()");
	}
	public Connection getConnection(String url, String uid, String pwd)
		throws SQLException {
		beginMethodTrace("getConnection(" + url + "," + uid + "," + pwd + ")");
		if (uid == null)
			uid = "";
		if (pwd == null)
			pwd = "";
		if (getDriverName() == null || getDriverName().equals("")) {
			System.err.println("Driver class name not specified");
			return null;
		}
		if (con == null) {
			trace("Connection attempt");
			try {
				Class.forName(getDriverName());
				con = DriverManager.getConnection(url, uid, pwd);
				con.setTransactionIsolation(isolationLevel);
				trace("Connection established");
			} catch (ClassNotFoundException e) {
				System.err.println("ClassNotFoundException: " + e.getMessage());
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				throw ex;
			}
		} else {
			trace("Connection already opened");
		}
		endMethodTrace("getConnection()");
		return con;
	}
	public void initialize() {
		beginMethodTrace("initialize()");
		endMethodTrace("initialize()");
	}
	public void releaseConnection() throws SQLException {
		beginMethodTrace("releaseConnection()");
		if (con == null)
			return;
		try {
			if (!autoCommit)
				con.commit();
			con.close();
			con = null;
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("releaseConnection()");
	}
	public Vector readAllWithPreparedStatement() throws SQLException {
		Vector resultRows = new Vector();
		beginMethodTrace("readAllWithPreparedStmt()");
		if (preparedStatement == null) {
			endMethodTrace("readAllWithPreparedStmt()");
			return resultRows;
		}
		try {
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			Vector aResultRow;
			for (; resultSet.next(); resultRows.add(aResultRow)) {
				aResultRow = new Vector();
				for (int i = 1; i <= numberOfColumns; i++)
					aResultRow.add(resultSet.getString(i));
			}
			resultSet.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("readAllWithPreparedStmt()");
		return resultRows;
	}
	public Vector readWithPreparedStatement(
		int position,
		int numberOfRowsToRead)
		throws SQLException {
		Vector resultRows = new Vector();
		beginMethodTrace(
			"readWithPreparedStatement("
				+ position
				+ ","
				+ numberOfRowsToRead
				+ ")");
		if (preparedStatement == null) {
			endMethodTrace("read()");
			return resultRows;
		}
		try {
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.last()) {
				endMethodTrace("read()");
				return resultRows;
			}
			if (position > resultSet.getRow()) {
				trace("Index too big.");
				endMethodTrace("read()");
				return resultRows;
			}
			resultSet.absolute(position);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int k = 1;
			for (boolean go_on = true; go_on && k <= numberOfRowsToRead; k++) {
				Vector aResultRow = new Vector();
				for (int i = 1; i <= numberOfColumns; i++)
					aResultRow.add(resultSet.getString(i));
				resultRows.add(aResultRow);
				go_on = resultSet.next();
			}
			resultSet.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("readWithPreparedStatement()");
		return resultRows;
	}
	public void prepareStatement(String stmtString) throws SQLException {
		beginMethodTrace("prepareStatement(" + stmtString + ")");
		try {
			preparedStatement = con.prepareStatement(stmtString, 1004, 1007);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("prepareStatement()");
	}
	public void closePreparedStatement() throws SQLException {
		beginMethodTrace("closePreparedStatement()");
		if (preparedStatement == null) {
			endMethodTrace("closePreparedStatement()");
			return;
		}
		try {
			preparedStatement.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("closePreparedStatement()");
	}
	public ResultSet executeQuery(String stmtString) throws SQLException {
		beginMethodTrace("executeQuery(" + stmtString + ")");
		try { /*statement = con.createStatement(1004, 1007);            statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);  */
			statement = con.createStatement();
			resultSet = statement.executeQuery(stmtString);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("executeQuery()");
		return resultSet;
	}
	public void executeQueryForUpdate(String stmtString) throws SQLException {
		beginMethodTrace("executeQueryForUpdate(" + stmtString + ")");
		try {
			if (resultSet != null)
				resultSet.close();
			statement = con.createStatement();
			boolean result = statement.execute(stmtString);
			trace("result = " + result);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("executeQueryForUpdate()");
	}
	public int getResultSetRowsNumber() throws SQLException {
		int counter = 0;
		beginMethodTrace("getResultSetRowsNumber()");
		if (resultSet == null) {
			endMethodTrace("getResultSetRowsNumber()");
			return counter;
		}
		try {
			if (!resultSet.last()) {
				endMethodTrace("getResultSetRowsNumber()");
				return counter;
			}
			counter = resultSet.getRow();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			throw ex;
		}
		endMethodTrace("getResultSetRowsNumber()");
		return counter;
	}
	public int executeUpdate(String stmtString) throws SQLException {
		int result = -1;
		beginMethodTrace("executeUpdate(" + stmtString + ")");
		try {
			if (resultSet != null)
				resultSet.close();
			Statement statement = con.createStatement();
			con.setAutoCommit(autoCommit);
			result = statement.executeUpdate(stmtString);
			trace("result = " + result);
			/*          if(autoCommit)               con.commit();*/
			statement.close();
		} catch (SQLException ex) {
			try {
				trace("result = " + result);
				con.rollback();
				System.err.println("SQLException: " + ex.getMessage());
				throw ex;
			} catch (SQLException ex2) {
				System.err.println("SQLException: " + ex.getMessage());
				throw ex;
			}
		}
		endMethodTrace("executeUpdate()");
		return result;
	}
	protected String getId() {
		return id;
	}
	protected void setId(String id) {
		this.id = id;
	}
	protected void finalize()
		throws Throwable { /*        releaseConnection(); */
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
}