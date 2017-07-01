package com.hps.july.sync.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.sql.RowSet;

import com.hps.july.sync.model.Page;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class PageDAO {
	//основной метод:
	protected Page executeSelect(Connection con, Object searchCriteria, int start, int count, String orderBy) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Page page = null;
		try {
			ps = con.prepareStatement(getSearchSQLString(searchCriteria, orderBy), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			// determine the number of columns from the mete data
			int numberOfColumns = rs.getMetaData().getColumnCount();
			
			if (start >= 0 && rs.absolute(start + 1)) {
				boolean hasNext = false;
				List items = new ArrayList();
				do {
					items.add(populateRow(rs, numberOfColumns));
				} while ((hasNext = rs.next()) && (--count > 0));
				page = new Page(items, start, hasNext);
			} else {
				page = Page.EMPTY_PAGE;
			}

		} finally {
			rs.close();
			ps.close();
		}
		return page;
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
