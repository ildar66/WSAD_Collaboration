package com.hps.april.common.utils.jdbc;

import java.util.List;

/**
 * @author dimitry
 * Created on 22.12.2005
 */
public interface SqlProperties {
	
	void add(String property);
	void add(String property, Object value);
	void add(String property, Object value, int type);
	
	void add(List properties, List values);
	void add(List properties, List values, List types);
	
	void add(String[] properties, Object[] values);
	void add(String[] properties, Object[] values, int[] types);
	
	void add(String property, SqlProperties properties);
	
	void addMultiple(String property, List multipleValues);
	void addMultiple(String property, List multipleValues, int type);

	void setSeparator(String separator);
	
	boolean isEmpty();
	
	String toString();
	Object[] toValueArray();
	int[] toTypeArray();
	
	void addMultiple(String property, String beanProperty, List valueList);
	
	List getPropertyList();
	
	List getTypeList();

	List getValueList();
	
	int size();
}
