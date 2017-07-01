package com.hps.july.sync.model.dao;

import java.sql.*;
import javax.sql.*;

import com.hps.july.sync.model.Page;

import java.util.*;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface DbsPositionDAO_Page {
	public Page findDbsPositionsPage(Connection con, int startAtRow, int howManyRows, String orderBy) throws SQLException;	
}
