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
public class InformixDbsPositionDAO_0 implements DbsPositionDAO {
	final private String tableName = "dbsPositions ";
	// select statement uses fields
	final private String fields =
		"idRecord, dampsID, gsmID, wlanID, name, name2, apparatType, containerType, placeType, apparatPlace, oporaPlace, isOurOpora, oporaType, antennaPlace, heightOpora, fioOtvExpl, tabNumOtvExpl, stateBS, dateDerrick, dateOnSiteReview, lastUpdMarshKarta, lastUpdListProhod, lastUpdPosition, flagWorkNri ";

	/**
	 * Constructor for InformixDbsPositionDAO.
	 */
	public InformixDbsPositionDAO_0() {
	}
	// the methods relevant to the ValueListHandler
	// are shown here.
	// See Data Access Object pattern for other details.
	private List executeSelect(Connection con, DbsPositionVO projCriteria, String orderBy) throws SQLException {
		List list = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(getSearchSQLString(projCriteria, orderBy));
			ResultSet rs = pstmt.executeQuery();
			list = prepareResult(rs);
		} finally {
			pstmt.close();
		}
		return list;
	}

	private RowSet executeSelect(
		Connection con,
		DbsPositionVO projCriteria,
		int startAtRow,
		int howManyRows,
		String orderBy)
		throws SQLException {
		ReadOnlyRowSet rowSet = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(getSearchSQLString(projCriteria, orderBy));
			ResultSet rs = pstmt.executeQuery();
			//rowSet = new CachedRowSet();
			rowSet = new ReadOnlyRowSet();
			rowSet.populate(rs, startAtRow, howManyRows);
		} finally {
			pstmt.close();
		}
		return rowSet;
	}

	private List prepareResult(ResultSet rs) throws SQLException {
		ArrayList list = new ArrayList();
		while (rs.next()) {
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
	 * @see com.hps.july.sync.model.dao.DbsPositionDAO#findDbsPositions(String)
	 */
	public Collection findDbsPositions(Connection con, String orderBy) throws SQLException {
		return executeSelect(con, null, orderBy);
	}

	/**
	 * @see com.hps.july.sync.model.dao.DbsPositionDAO#findDbsPositions(Connection, int, int, String)
	 */
	public Collection findDbsPositions(Connection con, int startAtRow, int howManyRows, String orderBy)
		throws SQLException {
		RowSetWrapperList results = null;
		RowSet rowSet = findDbsPositionsRORS(con, startAtRow, howManyRows, orderBy);
		results = new RowSetWrapperList(rowSet);
		return results;

	}

	/**
	 * @see com.hps.july.sync.model.dao.DbsPositionDAO#findDbsPositionsRORS(Connection, int, int, String)
	 */
	public RowSet findDbsPositionsRORS(Connection con, int startAtRow, int howManyRows, String orderBy)
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

}
