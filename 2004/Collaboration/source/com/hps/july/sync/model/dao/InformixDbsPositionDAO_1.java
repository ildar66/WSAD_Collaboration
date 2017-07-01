package com.hps.july.sync.model.dao;

import java.sql.*;
import java.util.*;

import javax.sql.RowSet;
import com.hps.july.sync.rowset.*;

import com.hps.july.sync.model.valueobject.DbsPositionVO;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class InformixDbsPositionDAO_1 implements DbsPositionDAO {
	final private String tableName = "dbsPositions ";
	// select statement uses fields
	final private String fields =
		"idRecord, dampsID, gsmID, wlanID, name, name2, apparatType, containerType, placeType, apparatPlace, oporaPlace, isOurOpora, oporaType, antennaPlace, heightOpora, fioOtvExpl, tabNumOtvExpl, stateBS, dateDerrick, dateOnSiteReview, lastUpdMarshKarta, lastUpdListProhod, lastUpdPosition, flagWorkNri ";

	/**
	 * Constructor for InformixDbsPositionDAO.
	 */
	public InformixDbsPositionDAO_1() {
	}

	private Collection executeSelect(
		Connection con,
		DbsPositionVO projCriteria,
		int startAtRow,
		int howManyRows,
		String orderBy)
		throws SQLException {
		Collection list = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(getSearchSQLString(projCriteria, orderBy));
			ResultSet rs = pstmt.executeQuery();
			list = prepareResult(rs, startAtRow, howManyRows);
		} finally {
			pstmt.close();
		}
		return list;
	}

	private Collection prepareResult(ResultSet rs, int startAtRow, int howManyRows) throws SQLException {
		//LinkedList list = new LinkedList();
		ArrayList list = new ArrayList();
		setStartPosition(startAtRow, rs);
		if (howManyRows <= 0) {
			howManyRows = Integer.MAX_VALUE;
		}
		int processedRows = 0;

		while ((rs.next()) && (processedRows++ < howManyRows)) {
			Integer idRecord = (Integer) rs.getObject(DbsPositionVO.ID_RECORD);
			Integer gsmID = (Integer) rs.getObject(DbsPositionVO.GSM_ID);

			String name = rs.getString(DbsPositionVO.NAME);
			String apparatType = rs.getString(DbsPositionVO.APPARAT_TYPE);
			String apparatPlace = rs.getString(DbsPositionVO.APPARAT_PLACE);
			String oporaPlace = rs.getString(DbsPositionVO.OPORA_PLACE);

			DbsPositionVO vo = new DbsPositionVO(idRecord);
			vo.setGsmID(gsmID);

			vo.setName(name);
			vo.setApparatType(apparatType);
			vo.setApparatPlace(apparatPlace);
			vo.setOporaPlace(oporaPlace);

			list.add(vo);
		}
		return list;
	}


	/**
	 * @see com.hps.july.sync.model.dao.DbsPositionDAO#findDbsPositions(Connection, int, int, String)
	 */
	public Collection findDbsPositions(Connection con, int startAtRow, int howManyRows, String orderBy)
		throws SQLException {
		return executeSelect(con, null, startAtRow, howManyRows, orderBy);

	}

	private String getSearchSQLString(DbsPositionVO projCriteria, String orderBy) {
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT " + fields + "FROM " + tableName + "WHERE 1=1 ");
		/**
		// append additional conditions to where clause
		// depending on the values specified in 
		// projCriteria
		if (projCriteria.getId() != null) {
			selectStatement.append(" AND id = " + projCriteria.getId() + " ");
		}
		// check and add other fields to where clause...
		*/
		buff.append("order by " + orderBy);
		return buff.toString();
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
}
