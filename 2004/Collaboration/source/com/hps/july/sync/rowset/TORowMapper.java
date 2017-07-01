package com.hps.july.sync.rowset;

import java.sql.*;
import javax.sql.*;

/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TORowMapper {
	// create Customer TO
	public static CustomerTO createCustomerTO(RowSet rowSet) {
		CustomerTO to = new CustomerTO();
		to.setId(getString(rowSet,0));
		to.setName(getString(rowSet,1));
		//...
		return to;
		}

	// create other TOs
	//...

	// implement primitive methods used by create methods
	protected static boolean wasNull(RowSet rowSet) {
		try {
			return rowSet.wasNull();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected static String getString(RowSet rowSet, int columnIndex) {
		try {
			return rowSet.getString(columnIndex);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected static boolean getBoolean(RowSet rowSet, int columnIndex) {
		try {
			return rowSet.getBoolean(columnIndex);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected static java.util.Date getDate(RowSet rowSet, int columnIndex) {
		try {
			return rowSet.getDate(columnIndex);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// Other primitive getXXX methods for all required
	// data types
	//...
}
