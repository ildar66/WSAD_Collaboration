/****** This code is generated automatically by IBM WebSphere Studio ******/
package WhitePages0;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
public class CommonDataAccessBean extends HttpServlet {
	private boolean traceOn;
	private long beginTime;
	private int indent;
	protected Hashtable colName_Pos_MappingTab;
	protected Hashtable paramMarkers_Tab;
	protected Hashtable param_instVar_Mapping;
	protected String currentDomainActionDescriptor;
	protected Hashtable dadActionType;
	protected Hashtable dadStmtString;
	protected Vector primaryKeyVector;
	protected String keyParameter;
	protected String primaryKeyName;
	protected Vector newValuesForUpdation;
	protected static String userid;
	protected static String password;
	protected static String databaseUrl;
	protected static String driverName;
	protected String lockTableStatement;
	protected String selectForUpdateStatement;
	private ResultSet resultSet;
	private NTLTDatabaseAccessRuntime dao;
	public CommonDataAccessBean() {
		traceOn = false;
		beginTime = 0L;
		colName_Pos_MappingTab = new Hashtable();
		paramMarkers_Tab = new Hashtable();
		param_instVar_Mapping = new Hashtable();
		dadActionType = new Hashtable();
		dadStmtString = new Hashtable();
		primaryKeyVector = new Vector();
		newValuesForUpdation = new Vector();
		if (dao == null)
			dao = new NTLTDatabaseAccessRuntime();
		String p = System.getProperty("NTLTTRACE");
		if (p != null && p.toUpperCase().equals("Y"))
			setTraceOn(true);
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		setUserid(config.getInitParameter("userid"));
		setPassword(getInitParameter("password"));
		setDatabaseUrl(getInitParameter("database"));
		setDriverName(getInitParameter("driver"));
	}
	public boolean getTraceOn() {
		return traceOn;
	}
	public void setTraceOn(boolean traceOn) {
		this.traceOn = traceOn;
	}
	public String getKeyParameter() {
		return keyParameter;
	}
	public void setKeyParameter(String keyParameter) {
		this.keyParameter = keyParameter;
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
			System.out.println("\nBegin " + mess);
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
	protected String getWhereClauseString() {
		String result = " WHERE";
		PairNameValue pnv = null;
		for (int i = 0; i < primaryKeyVector.size(); i++) {
			pnv = (PairNameValue) primaryKeyVector.get(i);
			if (i > 0)
				result = result + " AND ";
			result =
				result
					+ " "
					+ pnv.getColumnName()
					+ " = '"
					+ pnv.getValue()
					+ "'";
		}
		return result;
	}
	protected String getPrimaryKeyName() {
		return "";
	}
	public String getDatabaseUrl() {
		return databaseUrl;
	}
	public void initContainerOfValuesToUpdate() {
		newValuesForUpdation.removeAllElements();
		newValuesForUpdation = new Vector();
	}
	public void initContainerOfPKValues() {
		primaryKeyVector.removeAllElements();
		primaryKeyVector = new Vector();
	}
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
		dao.setDriverName(driverName);
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void execute(String sqlStatementString) throws SQLException {
		try {
			dao.getConnection(databaseUrl, userid, password);
			resultSet = dao.executeQuery(sqlStatementString);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	public void executeUpdate(String sqlStatementString) throws SQLException {
		try {
			dao.getConnection(databaseUrl, userid, password);
			dao.executeUpdate(sqlStatementString);
			if (!dao.getAutoCommit())
				dao.commit();
		} catch (SQLException ex) {
			throw ex;
		}
	}
	protected String completeUpdateSQLStatement(String updateStatement) {
		String updStatement = "";
		String str1 = "set(";
		String str2 = " = (";
		PairNameValue pnv = null;
		for (int h = 0; h < newValuesForUpdation.size(); h++) {
			if (h > 0) {
				str1 = str1 + ",";
				str2 = str2 + ",";
			}
			pnv = (PairNameValue) newValuesForUpdation.elementAt(h);
			str1 = str1 + pnv.getColumnName();
			str2 = str2 + "'" + pnv.getValue() + "'";
		}
		str1 = str1 + ") ";
		str2 = str2 + ") ";
		updStatement = updateStatement + str1 + str2 + getWhereClauseString();
		return updStatement;
	}
	public void update(String updateStatement) throws SQLException {
		try {
			dao.getConnection(databaseUrl, userid, password);
			dao.executeUpdate(getLockTableStatement());
			dao.executeUpdate(updateStatement);
			if (!dao.getAutoCommit())
				dao.commit();
		} catch (SQLException ex) {
			throw ex;
		}
	}
	protected String completeDeleteSQLStatement(String deleteStatement) {
		return deleteStatement + getWhereClauseString();
	}
	public void delete(String deleteStatement) throws SQLException {
		try {
			dao.getConnection(databaseUrl, userid, password);
			dao.executeUpdate(getLockTableStatement());
			dao.executeUpdate(deleteStatement);
			if (!dao.getAutoCommit())
				dao.commit();
		} catch (SQLException ex) {
			throw ex;
		}
	}
	public void insert(String insertStatement) throws SQLException {
		try {
			dao.getConnection(databaseUrl, userid, password);
			dao.executeUpdate(insertStatement);
			if (!dao.getAutoCommit())
				dao.commit();
		} catch (SQLException ex) {
			throw ex;
		}
	}
	protected String completeInsertSQLStatement(String insertStatement) {
		String insStatement = "";
		String str1 = "(";
		String str2 = " VALUES (";
		PairNameValue pnv = null;
		for (int h = 0; h < newValuesForUpdation.size(); h++) {
			if (h > 0) {
				str1 = str1 + ",";
				str2 = str2 + ",";
			}
			pnv = (PairNameValue) newValuesForUpdation.elementAt(h);
			str1 = str1 + pnv.getColumnName();
			str2 = str2 + "'" + pnv.getValue() + "'";
		}
		str1 = str1 + ") ";
		str2 = str2 + ") ";
		insStatement = insertStatement + str1 + str2;
		return insStatement;
	}
	public boolean readNextRow() {
		boolean result = true;
		if (resultSet != null)
			try {
				result = resultSet.next();
			} catch (SQLException ex) {
				System.err.println("SQLException: " + ex.getMessage());
				result = false;
			}
		return result;
	}
	public void close() throws SQLException {
		try {
			if (dao != null)
				dao.releaseConnection();
		} catch (SQLException ex) {
			throw ex;
		}
	}
	public int getNumberOfRowsInResultSet() throws SQLException {
		try {
			if (dao != null)
				return dao.getResultSetRowsNumber();
			else
				return 0;
		} catch (SQLException ex) {
			throw ex;
		}
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void addValueToUpdate(String columnName, String value) {
		PairNameValue pnv = new PairNameValue(columnName, value);
		newValuesForUpdation.add(pnv);
	}
	public void addValueToPrimaryKey(String columnName, String value) {
		PairNameValue pnv = new PairNameValue(columnName, value);
		primaryKeyVector.add(pnv);
	}
	public String getLockTableStatement() {
		return lockTableStatement;
	}
	public int getPositionForFieldAndAction(String fieldName, String dadName) {
		int position = 0;
		Hashtable ht = (Hashtable) colName_Pos_MappingTab.get(dadName);
		position = ((Integer) ht.get(fieldName)).intValue();
		return position;
	}
	public String replaceInStringFirstOccOfArg1ByArg2(
		String source,
		String oldString,
		String newString) {
		String result = null;
		if (source == null || source == "")
			return "";
		if (oldString == null || oldString == "")
			return source;
		if (newString == null || newString == "")
			return source;
		int l1 = source.length();
		int l2 = oldString.length();
		int l3 = newString.length();
		int index = source.indexOf(oldString);
		if (index > -1)
			result =
				source.substring(0, index)
					+ newString
					+ source.substring(index + l2, l1 - index - l2);
		return result;
	}
	public String replaceInStringLastOccOfArg1ByArg2(
		String source,
		String oldString,
		String newString) {
		String result = null;
		if (source == null || source == "")
			return "";
		if (oldString == null || oldString == "")
			return source;
		if (newString == null || newString == "")
			return source;
		int l1 = source.length();
		int l2 = oldString.length();
		int l3 = newString.length();
		int index = source.lastIndexOf(oldString);
		if (index > -1)
			result =
				source.substring(0, index)
					+ newString
					+ source.substring(index + l2, l1 - index - l2);
		return result;
	}
	public String replaceInStringAllOccOfArg1ByArg2(
		String source,
		String oldString,
		String newString) {
		if (source == null || source == "")
			return "";
		if (oldString == null || oldString == "")
			return source;
		if (newString == null || newString == "")
			return source;
		String result = source;
		int l1 = result.length();
		int l2 = oldString.length();
		for (int index = result.indexOf(oldString);
			index > -1;
			index = result.indexOf(oldString, index + 1)) {
			String leftPart = result.substring(0, index);
			String rightPart = result.substring(index + l2, l1);
			if ((rightPart.length() > 0)
				&& (!Character.isJavaIdentifierPart(rightPart.charAt(0))))
				result = leftPart + newString + rightPart;
			else if (rightPart.equals(""))
				result = leftPart + newString;
			l1 = result.length();
		}
		return result;
	}
}