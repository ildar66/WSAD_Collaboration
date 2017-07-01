package com.hps.july.sync.session.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;
import com.hps.july.sync.model.valueobject.*;
/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReferencesFinderPersistence_0 {
	// sql datasource cached
	private DataSource ds = null;

	// environment variable key and value - must exist in bean environment 
	private final String DB_DATASOURCE_KEY = "session/dbdatasource";

	// SQL statements
	private final String DBC_ANTENNA_PLACES_SELECT_SQL =
		"SELECT idDbs, name, isUseRecode, idNri FROM dbsAntennaplaces ";
	private final String DBC_POSITIONS_SELECT_SQL =
		"SELECT idRecord, dampsID, gsmID, wlanID, name, name2, apparatType, containerType, placeType, apparatPlace, oporaPlace, isOurOpora, oporaType, antennaPlace, heightOpora, fioOtvExpl, tabNumOtvExpl, stateBS, dateDerrick, dateOnSiteReview, lastUpdMarshKarta, lastUpdListProhod, lastUpdPosition, flagWorkNri FROM dbsPositions ";

	/**
	 * Constructor for ReferencesFinderPersister.
	 */
	public ReferencesFinderPersistence_0() {
		initializeResources();
	}

	private void initializeResources() {
		try {
			
			InitialContext ctx = new InitialContext();
			String dbDatasource = (String) ctx.lookup("java:comp/env/" + DB_DATASOURCE_KEY);
			System.out.println("ReferencesFinderPersister datasource name: " + dbDatasource);
			ds = (DataSource) ctx.lookup(dbDatasource);
			// ds = (DataSource) ctx.lookup("jdbc/informix");
			System.out.println("ReferencesFinderPersister datasource OK");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DataSource getDatasource() throws SQLException {
		if (ds == null)
			throw new SQLException("Data source is null");
		else
			return ds;
	}

	public void freeResources() {
		System.out.println("ReferencesFinderPersister free resources");
		ds = null;
	}

	public Collection findDbsAntennaPlaces(String orderBy) {
		Vector result = new Vector();
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = getDatasource().getConnection();
			pstmt = conn.prepareStatement(DBC_ANTENNA_PLACES_SELECT_SQL + "order by " + orderBy);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer idDbs = (Integer) rs.getObject(DbsAntennaPlaceVO.ID_DBC);
				String name = rs.getString(DbsAntennaPlaceVO.NAME);
				String isUseRecode = rs.getString(DbsAntennaPlaceVO.IS_USE_RECODE);
				Integer idNri = (Integer) rs.getObject(DbsAntennaPlaceVO.ID_NRI);
				result.add(new DbsAntennaPlaceVO(idDbs, name, isUseRecode, idNri));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("SQLException while querying findDbsAntennaPlaces(): CODE=" + e.getErrorCode());
			return null;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	public Collection findDbsPositions(String orderBy) {
		Vector result = new Vector();
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = getDatasource().getConnection();
			pstmt = conn.prepareStatement(DBC_POSITIONS_SELECT_SQL + "order by " + orderBy);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer idRecord = (Integer) rs.getObject(DbsPositionVO.ID_RECORD);
				Integer gsmID = (Integer)rs.getObject(DbsPositionVO.GSM_ID);
				
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
				
				result.add(vo);
			}
			return result;
		} catch (SQLException e) {
			System.out.println("SQLException while querying findDbsPositions(): CODE=" + e.getErrorCode());
			return null;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}	

}
