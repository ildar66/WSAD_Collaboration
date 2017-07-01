package com.hps.july.sync.model.dao;

import java.sql.*;
import java.util.Collection;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface DbsAntennaPlaceDAO {
	public Collection findDbsAntennaPlaces(Connection con, String orderBy)  throws SQLException;
}
