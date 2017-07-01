package com.hps.july.sync.model.dao;

import java.sql.*;
import java.util.*;

import com.hps.july.sync.model.valueobject.DbsAntennaPlaceVO;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class InformixDbsAntennaPlaceDAO implements DbsAntennaPlaceDAO {
	final private String tableName = "dbsAntennaplaces ";
	// select statement uses fields
	final private String fields = "idDbs, name, isUseRecode, idNri ";
	/**
	 * Constructor for InformixDbsAntennaPlaceDAO.
	 */
	public InformixDbsAntennaPlaceDAO() {
	}
	// the methods relevant to the ValueListHandler
	// are shown here.
	// See Data Access Object pattern for other details.
	private List executeSelect(Connection con, DbsAntennaPlaceVO projCriteria, String orderBy) throws SQLException {
		List list = null;
		PreparedStatement pstmt = null;
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT " + fields + "FROM " + tableName + "where 1=1 ");
		/**
		// append additional conditions to where clause
		// depending on the values specified in 
		// projCriteria
		if (projCriteria.getIdDbs() != null) {
			selectStatement.append(" AND idDbs = " + projCriteria.getIdDbs() + " ");
		}
		// check and add other fields to where clause...
		*/
		buff.append("order by " + orderBy);
		try {
			pstmt = con.prepareStatement(buff.toString());
			ResultSet rs = pstmt.executeQuery();
			list = prepareResult(rs);
		} finally {
			pstmt.close();
		}
		return list;
	}

	private List prepareResult(ResultSet rs) throws SQLException {
		ArrayList list = new ArrayList();
		while (rs.next()) {
			Integer idDbs = (Integer) rs.getObject(DbsAntennaPlaceVO.ID_DBC);
			String name = rs.getString(DbsAntennaPlaceVO.NAME);
			String isUseRecode = rs.getString(DbsAntennaPlaceVO.IS_USE_RECODE);
			Integer idNri = (Integer) rs.getObject(DbsAntennaPlaceVO.ID_NRI);
			list.add(new DbsAntennaPlaceVO(idDbs, name, isUseRecode, idNri));
		}
		return list;
	}

	/**
	 * @see com.hps.july.sync.model.dao.DbsAntennaPlaceDAO#findDbsAntennaPlaces(String)
	 */
	public Collection findDbsAntennaPlaces(Connection con, String orderBy) throws SQLException {
		return executeSelect(con, null, orderBy);
	}

}
