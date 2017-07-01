package WhitePages1;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.lang.*;
import java.sql.SQLException;

///****** This code is generated automatically by IBM WebSphere Studio ******/

public class PeopleDataAccessBean extends CommonDataAccessBean {

	protected String currentDADName;
	public String searchString;
	public String nickname;
	public String last_name;
	public String location;
	public String category;
	public Integer client_id;
	public String bill;
	public String comments;

	public PeopleDataAccessBean() {
		super();
		lockTableStatement = "LOCK TABLE RECIPIENTS IN EXCLUSIVE MODE";
		initialize();
	}

	public void setNickname(String value) {
		nickname = value;
	}

	public String getNickname() {
		int index = getPositionForFieldAndAction("nickname", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setLast_name(String value) {
		last_name = value;
	}

	public String getLast_name() {
		int index = getPositionForFieldAndAction("last_name", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setLocation(String value) {
		location = value;
	}

	public String getLocation() {
		int index = getPositionForFieldAndAction("location", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setCategory(String value) {
		category = value;
	}

	public String getCategory() {
		int index = getPositionForFieldAndAction("category", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setClient_id(Integer value) {
		client_id = value;
	}

	public Integer getClient_id() {
		int index = getPositionForFieldAndAction("client_id", currentDADName);
		Integer result = null;
		if (getResultSet() != null) {
			try {
				result = new Integer(getResultSet().getInt(index));
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setBill(String value) {
		bill = value;
	}

	public String getBill() {
		int index = getPositionForFieldAndAction("bill", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void setComments(String value) {
		comments = value;
	}

	public String getComments() {
		int index = getPositionForFieldAndAction("comments", currentDADName);
		String result = null;
		if (getResultSet() != null) {
			try {
				result = getResultSet().getString(index);
			} catch (SQLException e) {
				System.out.println(
					"Problem retrieving value of column." + e.getMessage());
			}
		} //endif
		return result;
	}

	public void initialize() {
		Hashtable ht = null;
		//
		ht = new Hashtable();
		dadActionType.put("Select All", "select");
		dadStmtString.put("Select All", "SELECT * FROM ILDAR.RECIPIENTS");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(4));
		ht.put("category", new Integer(5));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(2));
		colName_Pos_MappingTab.put("Select All", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Read by fields for Action_3", "select");
		dadStmtString.put(
			"Read by fields for Action_3",
			"SELECT * FROM ILDAR.RECIPIENTS WHERE ILDAR.RECIPIENTS.BIC = :category");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(4));
		ht.put("category", new Integer(5));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(2));
		colName_Pos_MappingTab.put("Read by fields for Action_3", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Create", "insert");
		dadStmtString.put(
			"Create",
			"INSERT INTO ILDAR.RECIPIENTS (ID,NAME,INN,BIC,CLIENT_ID,BILL,COMMENTS) VALUES (:nickname,:last_name,:location,:category,:client_id,:bill,:comments)");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(2));
		ht.put("category", new Integer(4));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(5));
		colName_Pos_MappingTab.put("Create", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Read by fields for Action_1", "select");
		dadStmtString.put(
			"Read by fields for Action_1",
			"SELECT * FROM ILDAR.RECIPIENTS WHERE ILDAR.RECIPIENTS.NAME = :last_name");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(4));
		ht.put("category", new Integer(5));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(2));
		colName_Pos_MappingTab.put("Read by fields for Action_1", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Read", "read");
		dadStmtString.put(
			"Read",
			"SELECT * FROM ILDAR.RECIPIENTS WHERE ILDAR.RECIPIENTS.ID = :nickname");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(4));
		ht.put("category", new Integer(5));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(2));
		colName_Pos_MappingTab.put("Read", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Delete", "delete");
		dadStmtString.put(
			"Delete",
			"DELETE FROM ILDAR.RECIPIENTS WHERE ILDAR.RECIPIENTS.ID = :nickname");
		colName_Pos_MappingTab.put("Delete", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Update", "update");
		dadStmtString.put(
			"Update",
			" UPDATE ILDAR.RECIPIENTS SET NAME = :last_name, INN = :location, BIC = :category, CLIENT_ID = :client_id, BILL = :bill, COMMENTS = :comments WHERE ILDAR.RECIPIENTS.ID = :nickname");
		ht.put("location", new Integer(2));
		ht.put("last_name", new Integer(1));
		ht.put("category", new Integer(3));
		ht.put("comments", new Integer(6));
		ht.put("bill", new Integer(5));
		ht.put("client_id", new Integer(4));
		colName_Pos_MappingTab.put("Update", ht);
		//
		ht = new Hashtable();
		dadActionType.put("Read by fields for Action_5", "select");
		dadStmtString.put(
			"Read by fields for Action_5",
			"SELECT * FROM ILDAR.RECIPIENTS WHERE ILDAR.RECIPIENTS.INN = :location");
		ht.put("location", new Integer(3));
		ht.put("nickname", new Integer(1));
		ht.put("last_name", new Integer(4));
		ht.put("category", new Integer(5));
		ht.put("comments", new Integer(7));
		ht.put("bill", new Integer(6));
		ht.put("client_id", new Integer(2));
		colName_Pos_MappingTab.put("Read by fields for Action_5", ht);
		//
		param_instVar_Mapping.put("nickname", "nickname");
		param_instVar_Mapping.put("last_name", "last_name");
		param_instVar_Mapping.put("location", "location");
		param_instVar_Mapping.put("category", "category");
		param_instVar_Mapping.put("client_id", "client_id");
		param_instVar_Mapping.put("bill", "bill");
		param_instVar_Mapping.put("comments", "comments");
		param_instVar_Mapping.put("searchString", "searchString");
		//
		Vector v;
		v = new Vector();
		v.add("nickname");
		v.add("last_name");
		v.add("location");
		v.add("category");
		v.add("client_id");
		v.add("bill");
		v.add("comments");
		paramMarkers_Tab.put("Create", v);
		v = new Vector();
		v.add("nickname");
		v.add("last_name");
		v.add("location");
		v.add("category");
		v.add("client_id");
		v.add("bill");
		v.add("comments");
		paramMarkers_Tab.put("Update", v);
		v = new Vector();
		v.add("nickname");
		paramMarkers_Tab.put("Delete", v);
		v = new Vector();
		paramMarkers_Tab.put("Select All", v);
		v = new Vector();
		v.add("nickname");
		paramMarkers_Tab.put("Read", v);
		v = new Vector();
		v.add("last_name");
		paramMarkers_Tab.put("Read by fields for Action_1", v);
		v = new Vector();
		v.add("category");
		paramMarkers_Tab.put("Read by fields for Action_3", v);
		v = new Vector();
		v.add("location");
		paramMarkers_Tab.put("Read by fields for Action_5", v);
	}

	public int getPositionForFieldAndAction(String fieldName, String dadName) {
		int position = 0;
		Hashtable ht = (Hashtable) colName_Pos_MappingTab.get(dadName);
		position = ((Integer) ht.get(fieldName)).intValue();
		return position;
	}

	public String getLockTableStatement() {
		String result = "";
		//Too strong 
		result = "LOCK TABLE ADMBG.DEPARTMENT IN EXCLUSIVE MODE";
		//result = "SELECT * FROM ADMBG.DEPARTMENT " + getWhereClauseString() + " FOR UPDATE";
		return result;
	}

	public void executeCreate() throws SQLException {
		currentDADName = "Create";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.executeUpdate(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeUpdate() throws SQLException {
		currentDADName = "Update";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.executeUpdate(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeDelete() throws SQLException {
		currentDADName = "Delete";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.executeUpdate(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeSelectAll() throws SQLException {
		currentDADName = "Select All";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.execute(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeRead() throws SQLException {
		currentDADName = "Read";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.execute(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeReadByFieldForAction_1(String vallast_name)
		throws SQLException {
		this.last_name = vallast_name;
		currentDADName = "Read by fields for Action_1";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.execute(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeReadByFieldForAction_3(String valcategory)
		throws SQLException {
		this.category = valcategory;
		currentDADName = "Read by fields for Action_3";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.execute(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void executeReadByFieldForAction_5(String vallocation)
		throws SQLException {
		this.location = vallocation;
		currentDADName = "Read by fields for Action_5";
		//replace paramMarkers by their real values contained in the inst_vars
		String s = replaceParamMarkersInStmt(currentDADName);
		try {
			super.execute(s);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	protected String replaceParamMarkersInStmt(String actionName) {
		String stmt = (String) dadStmtString.get(actionName);
		Vector v = (Vector) paramMarkers_Tab.get(actionName);
		String paramMarker = null;
		String mappedInstVar = null;
		for (int i = 0; i < v.size(); i++) {
			paramMarker = (String) v.get(i);
			mappedInstVar =
				(String) param_instVar_Mapping.get((String) v.get(i));
			Object valueOfInstVar = getValueOfInstVar(mappedInstVar);
			if (valueOfInstVar == null) {
				stmt =
					replaceInStringAllOccOfArg1ByArg2(
						stmt,
						":" + paramMarker,
						"NULL");
				continue;
			}
			String addString = "";
			if ((valueOfInstVar instanceof String)
				|| (valueOfInstVar instanceof java.sql.Date)
				|| (valueOfInstVar instanceof java.sql.Time)
				|| (valueOfInstVar instanceof java.sql.Timestamp))
				addString = "'";
			stmt =
				replaceInStringAllOccOfArg1ByArg2(
					stmt,
					":" + paramMarker,
					addString + valueOfInstVar.toString() + addString);
		}
		return stmt;
	}

	protected Object getValueOfInstVar(String instVar) {
		try {
			Object var = "";
			if (instVar.equalsIgnoreCase("nickname"))
				var = nickname;
			if (instVar.equalsIgnoreCase("last_name"))
				var = last_name;
			if (instVar.equalsIgnoreCase("location"))
				var = location;
			if (instVar.equalsIgnoreCase("category"))
				var = category;
			if (instVar.equalsIgnoreCase("client_id"))
				var = client_id;
			if (instVar.equalsIgnoreCase("bill"))
				var = bill;
			if (instVar.equalsIgnoreCase("comments"))
				var = comments;
			if (instVar.equalsIgnoreCase("searchString"))
				var = searchString;
			if (var instanceof String)
				var = replaceApost((String) var);
			return var;
		} catch (NullPointerException exp) {
			return null;
		}
	}

	protected String replaceApost(String var) {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < var.length(); i++) {
			if (var.charAt(i) == '\'') {
				ret.append("''");
			} else {
				ret.append(var.charAt(i));
			}
		}
		return ret.toString();
	}

}

///**  **/