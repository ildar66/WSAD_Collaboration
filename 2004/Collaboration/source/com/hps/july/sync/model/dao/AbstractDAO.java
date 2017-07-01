package com.hps.july.sync.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.RowSet;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class AbstractDAO {
	//основной метод:
	protected Collection executeSelect(Connection con, Object searchCriteria, int startAtRow, int howManyRows, String orderBy) throws SQLException {
		//System.out.println("AbstractPageDAO.executeSelect()");
		Collection list = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(getSearchSQLString(searchCriteria, orderBy));
			ResultSet rs = pstmt.executeQuery();
			list = prepareResult(rs, startAtRow, howManyRows);
		} finally {
			pstmt.close();
		}
		return list;
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

	protected Collection prepareResult(ResultSet rs, int startAtRow, int howManyRows) throws SQLException {
		//LinkedList list = new LinkedList();
		ArrayList list = new ArrayList();

		// determine the number of columns from the mete data
		int numberOfColumns = rs.getMetaData().getColumnCount();

		setStartPosition(startAtRow, rs);
		if (howManyRows <= 0) {
			howManyRows = Integer.MAX_VALUE;
		}
		int processedRows = 0;

		while ((rs.next()) && (processedRows++ < howManyRows)) {
			Object o = populateRow(rs, numberOfColumns);
			list.add(o);
		}
		return list;
	}

	protected Object populateRow(ResultSet rs, int numberOfColumns) throws SQLException {
		Object[] values = new Object[numberOfColumns];
		// Read values for current row and save
		// them in the values array
		for (int i = 0; i < numberOfColumns; i++) {
			Object columnValue = rs.getObject(i+1);
			values[i] = columnValue;
		}
		return values;
	}

	protected abstract String getSearchSQLString(Object projCriteria, String orderBy);

}
