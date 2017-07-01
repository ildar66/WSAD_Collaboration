package com.hps.april.common.utils.jdbc;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author dimitry
 * Created on 16.01.2006
 */
public interface Mapper extends RowMapper {
	
	SqlProperties mapObject(Object object);

	/**
	 * @return
	 */
	String getTableName();

}
